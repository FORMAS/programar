package Algebra.Cliente;

import java.awt.Color;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class ParametroAlgebraTexto
{
    String operador;
    String normal;
    String sobrescrito;
    String erro;
    String renomear_relacao;
    String texto;
    SimpleAttributeSet set;

    public ParametroAlgebraTexto()
    {
        operador = new String("operador");
        normal = new String("normal");
        sobrescrito = new String("sobrescrito");
        erro = new String("erro");
        renomear_relacao = new String("renomear_relacao");
        texto = null;
        set = null;
    }

    public void settexto(String tx)
    {
        if(texto == null)
            texto = new String();
        texto = tx;
    }

    public void settipo(String id)
    {
        if(set == null)
            set = new SimpleAttributeSet();

        if(id.equals(operador))
        {
        	StyleConstants.setFontFamily(set, "Algebra");
            StyleConstants.setFontSize(set, 15);
            StyleConstants.setForeground(set, Color.BLUE);
        } else
        if(id.equals(normal))
        {
            StyleConstants.setFontFamily(set, "Algebra");
            StyleConstants.setFontSize(set, 15);
            StyleConstants.setForeground(set, Color.BLACK);
        } else
        if(id.equals(sobrescrito))
        {
            StyleConstants.setFontFamily(set, "Algebra");
            StyleConstants.setFontSize(set, 10);
            StyleConstants.setForeground(set, Color.BLACK);
        } else
        if(id.equals(erro))
        {
            StyleConstants.setFontFamily(set, "Algebra");
            StyleConstants.setFontSize(set, 15);
            StyleConstants.setForeground(set, Color.RED);
        } else
        if(id.equals(renomear_relacao))
        {
            StyleConstants.setFontFamily(set, "Algebra");
            StyleConstants.setFontSize(set, 15);
            StyleConstants.setItalic(set, true);
            StyleConstants.setForeground(set, Color.BLACK);
        } else
        {
            System.out.println("n\343o achou id");
        }
    }
    

    public String gettexto()
    {
        return texto;
    }

    public SimpleAttributeSet getset()
    {
        return set;
    }

}

