package Algebra.Cliente;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

// Referenced classes of package Algebra.Cliente:
//            RegistroArquivo

public class CriaConexao
{

    public CriaConexao()
    {
        filename = Tela_Principal.local+"/databases.txt";
        output = null;
        input = null;
        try
        {
            Criaarquivo();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Erro na cria\347\343o do arquivo.", "Erro", 0);
        }
    }

    public void Criaarquivo()
        throws IOException
    {
        File file = new File(filename);
        if(!file.exists())
            file.createNewFile();
    }

    public Connection getConnection(String sch)
        throws Exception
    {
        boolean sair = false;
        RegistroArquivo registro = new RegistroArquivo("", "", "", "");
        String url = new String();
        while(!sair) 
            try
            {
                registro.read(getinput());
                if(sch.equals(registro.getschema().trim()))
                {
                    sair = true;
                    closeinput();
                }
            }
            catch(EOFException endofException)
            {
                sair = true;
                closeinput();
            }
            catch(IOException ioException)
            {
                JOptionPane.showMessageDialog(null, "Erro na leitura do arquivo.", "Erro", 0);
                closeinput();
                sair = true;
            }
        url = "jdbc:mysql://" + registro.getservidor().trim() + "/" + registro.getschema().trim();
        Class.forName("org.gjt.mm.mysql.Driver").newInstance();
        return DriverManager.getConnection(url, registro.getusuario().trim(), registro.getsenha().trim());
    }

    public boolean testConnection(String servidor_aux, String usuario_aux, char[] senha_aux, String schema_aux)
    {
        String url = "jdbc:mysql://" + servidor_aux + ":3306/" + schema_aux;
        String username = usuario_aux;
        String password = "";
        
        for(char c : senha_aux){
        	password += c;
        }
        if(password.length() == 0)
            password = null;
        
        try
        {
            Class.forName("org.gjt.mm.mysql.Driver").newInstance();
            System.out.println(url+"\n"+password+"\n"+username);
            DriverManager.getConnection(url, username, password);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
            return false;
        }
        return true;
    }

    public void gravarConnection(String servidor_aux, String usuario_aux, String senha_aux, String schema_aux)
    {
        RegistroArquivo registro = new RegistroArquivo(servidor_aux, usuario_aux, senha_aux, schema_aux);
        try
        {
            int i = buscaindice(schema_aux);
            getoutput().seek(i * RegistroArquivo.size());
            registro.write(getoutput());
            closeoutput();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Erro na grava\347\343o do arquivo.", "Erro", 0);
            return;
        }
    }

    public String[] getschemas()
    {
        String schemas[] = new String[100];
        boolean sair = false;
        RegistroArquivo registro = new RegistroArquivo("", "", "", "");
        int i = 0;
        while(!sair) 
            try
            {
                registro.read(getinput());
                schemas[i] = registro.getschema();
                i++;
            }
            catch(EOFException endofException)
            {
                sair = true;
                closeinput();
            }
            catch(IOException ioException)
            {
                JOptionPane.showMessageDialog(null, "Erro na leitura do arquivo.", "Erro", 0);
                closeinput();
                sair = true;
            }
        return schemas;
    }

    public int buscaindice(String sch)
    {
        boolean sair = false;
        RegistroArquivo registro = new RegistroArquivo("", "", "", "");
        int i = -1;
        while(!sair) 
            try
            {
                i++;
                registro.read(getinput());
                if(sch.equals(registro.getschema().trim()))
                {
                    sair = true;
                    closeinput();
                }
            }
            catch(EOFException endofException)
            {
                sair = true;
                closeinput();
            }
            catch(IOException ioException)
            {
                JOptionPane.showMessageDialog(null, "Erro na leitura do arquivo.", "Erro", 0);
                closeinput();
                sair = true;
            }
        return i;
    }

    public RandomAccessFile getoutput()
    {
        if(output == null)
            try
            {
                output = new RandomAccessFile(filename, "rw");
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, "Erro na cria\347\343o do arquivo de output.", "Erro", 0);
            }
        return output;
    }

    public RandomAccessFile getinput()
    {
        if(input == null)
            try
            {
                input = new RandomAccessFile(filename, "r");
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, "Erro na cria\347\343o do arquivo de input.", "Erro", 0);
            }
        return input;
    }

    private void closeoutput()
    {
        if(output != null)
            try
            {
                output.seek(0L);
                output.close();
                output = null;
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, "Erro ao fechar do arquivo de output.", "Erro", 0);
            }
    }

    private void closeinput()
    {
        if(input != null)
            try
            {
                input.seek(0L);
                input.close();
                input = null;
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, "Erro ao fechar do arquivo de input.", "Erro", 0);
            }
    }

    private String filename;
    private RandomAccessFile output;
    private RandomAccessFile input;
}

