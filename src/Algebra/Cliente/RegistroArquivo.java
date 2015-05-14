package Algebra.Cliente;

import java.io.*;

public class RegistroArquivo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String servidor;
    private String usuario;
    private String senha;
    private String schema;
    
    public RegistroArquivo(String ser, String usu, String sen, String sch){
        setservidor(ser);
        setusuario(usu);
        setsenha(sen);
        setschema(sch);
    }

    public void setservidor(String ser){
        servidor = ser;
    }

    public void setusuario(String usu){
        usuario = usu;
    }

    public void setsenha(String sen){
        senha = sen;
    }

    public void setschema(String sch){
        schema = sch;
    }

    public String getservidor(){
        return servidor;
    }

    public String getusuario(){
        return usuario;
    }

    public String getsenha(){
        return senha;
    }

    public String getschema(){
        return schema;
    }

    public void read(RandomAccessFile file) throws IOException{
        setservidor(padname(file));
        setusuario(padname(file));
        setsenha(padname(file));
        setschema(padname(file));
    }

    private String padname(RandomAccessFile file) throws IOException {
        char name[] = new char[50];
        for(int i = 0; i < name.length; i++){
            char temp = file.readChar();
            name[i] = temp;
        }

        return (new String(name)).replace('\0', ' ').trim();
    }

    public void write(RandomAccessFile file) throws IOException{
        writename(file, getservidor());
        writename(file, getusuario());
        writename(file, getsenha());
        writename(file, getschema());
    }

    private void writename(RandomAccessFile file, String name) throws IOException {
        StringBuffer buffer = null;
        if(name != null)
            buffer = new StringBuffer(name);
        else
            buffer = new StringBuffer(50);
        buffer.setLength(50);
        file.writeChars(buffer.toString());
    }

    public static int size(){
        return 400;
    }
}
