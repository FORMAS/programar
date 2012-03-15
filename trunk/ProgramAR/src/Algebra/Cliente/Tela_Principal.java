package Algebra.Cliente;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.*;

// Referenced classes of package Algebra.Cliente:
//            CriaConexao, ExecutaAlgebra, FormataAlgebraTexto, ParametroAlgebraTexto

public class Tela_Principal extends JFrame{
	
	private static final long serialVersionUID = 1L;
	public static String local;
    private String ListaBancos[] = {
        "Padr\343o"
    };
    
    private JPanel jContentPaneConsulta;
    private JPanel jContentPaneConexao;
    private JMenuBar jJMenuBarPrincipal;
    private JMenu jMenuOpcao;
    private JMenuItem jMenuItemConexaoBanco;
    private JMenuItem jMenuItemConsultaBanco;
    private JPanel jPanelBancodeDados;
    private JPanel jPanelConexao;
    private JPanel jPanelConexaoFields;
    private JLabel jLabelEscolhaBanco;
    private JLabel jLabelServidor;
    private JLabel jLabelUsuario;
    private JLabel jLabelSenha;
    private JLabel jLabelSchema;
    private JTextField JTextFieldServidor;
    private JTextField JTextFieldUsuario;
    private JPasswordField JTextFieldSenha;
    private JTextField JTextFieldSchema;
    private JButton jButtonConexaoOK;
    private JButton jButtonConexaoCancel;
    private JComboBox<String> jComboBoxBanco;
    private JPanel jPanelAlgebraRelacional;
    private JRadioButton jRadioButtonAlgebraRelacional;
    private JScrollPane jScrollPaneAlgebraRelacional;
    private JTabbedPane jtabbedpaneAlgerbaRelacional;
    private JTextPane jTextPaneAlgebraRelacional;
    private JPanel jPanelbotoesOperacoes;
    private JButton jButtonSelecao;
    private JButton jButtonProjecao;
    private JButton jButtonRenomear;
    private JButton jButtonUniao;
    private JButton jButtonIntersecao;
    private JButton jButtonDiferenca;
    private JButton jButtonProdutoCartesiano;
    private JButton jButtonJoinNatural;
    private JButton jButtonRecursao;
    private JButton jButtonJoin;
    private JButton jButtonAgregacao;
    private JButton jButtonOuterJoinTotal;
    private JButton jButtonOuterJoinEsquerda;
    private JButton jButtonOuterJoinDireita;
    private JButton jButtonDivisao;
    private JPanel jPanel;
    private JPanel jPanelAlgebraAcoes;
    private JButton jButtonAlgebraLimpar;
    private JButton jButtonAlgebraExecutar;
    private JPanel jPanelCalculoRelacional;
    private JRadioButton jRadioButtonCalculoRelacional;
    private JScrollPane jScrollPaneCalculoRelacional;
    private JTextArea jTextAreaCalculoRelacional;
    private JPanel jPanelCalculoAcoes;
    private JButton jButtonCalculoLimpar;
    private JButton jButtonCalculoExecutar;
    private JPanel jPanelSQL;
    private JRadioButton jRadioButtonSQL;
    private JScrollPane jScrollPaneSQL;
    private JTextArea jTextAreaSQL;
    private JPanel jPanelSQLAcoes;
    private JPanel jPanelConexaoAcoes;
    private JButton jButtonSQLLimpar;
    private JButton jButtonSQLExecutar;
    private ButtonGroup radioGroup;
    private JScrollPane jScrollPaneResultado;
    private JTable jTableResultado;
    private JPanel jPanelResultado;
    private DefaultTableModel model;
    private JPanel jPanelAlgebraExpressao;
    private boolean tela_consulta;
    private CriaConexao criaconexao;
    int vetor_caracteres[] = {
        10, 32, 34, 39, 40, 41, 44, 46, 48, 49, 
        50, 51, 52, 53, 54, 55, 56, 57, 60, 61, 
        62, 65, 66, 67, 68, 69, 70, 71, 72, 73, 
        74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 
        84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 
        100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 
        110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 
        120, 121, 122
    };
    
    
    public Tela_Principal(){
    	
        jContentPaneConsulta = null;
        jContentPaneConexao = null;
        jJMenuBarPrincipal = null;
        jMenuOpcao = null;
        jMenuItemConexaoBanco = null;
        jMenuItemConsultaBanco = null;
        jPanelBancodeDados = null;
        jPanelConexao = null;
        jPanelConexaoFields = null;
        jLabelEscolhaBanco = null;
        jLabelServidor = null;
        jLabelUsuario = null;
        jLabelSenha = null;
        jLabelSchema = null;
        JTextFieldServidor = null;
        JTextFieldUsuario = null;
        JTextFieldSenha = null;
        JTextFieldSchema = null;
        jButtonConexaoOK = null;
        jButtonConexaoCancel = null;
        jComboBoxBanco = null;
        jPanelAlgebraRelacional = null;
        jRadioButtonAlgebraRelacional = null;
        jScrollPaneAlgebraRelacional = null;
        jtabbedpaneAlgerbaRelacional = null;
        jTextPaneAlgebraRelacional = null;
        jPanelbotoesOperacoes = null;
        jButtonSelecao = null;
        jButtonProjecao = null;
        jButtonRenomear = null;
        jButtonUniao = null;
        jButtonIntersecao = null;
        jButtonDiferenca = null;
        jButtonProdutoCartesiano = null;
        jButtonJoinNatural = null;
        jButtonRecursao = null;
        jButtonJoin = null;
        jButtonAgregacao = null;
        jButtonOuterJoinTotal = null;
        jButtonOuterJoinEsquerda = null;
        jButtonOuterJoinDireita = null;
        jButtonDivisao = null;
        jPanel = null;
        jPanelAlgebraAcoes = null;
        jButtonAlgebraLimpar = null;
        jButtonAlgebraExecutar = null;
        jPanelCalculoRelacional = null;
        jRadioButtonCalculoRelacional = null;
        jScrollPaneCalculoRelacional = null;
        jTextAreaCalculoRelacional = null;
        jPanelCalculoAcoes = null;
        jButtonCalculoLimpar = null;
        jButtonCalculoExecutar = null;
        jPanelSQL = null;
        jRadioButtonSQL = null;
        jScrollPaneSQL = null;
        jTextAreaSQL = null;
        jPanelSQLAcoes = null;
        jPanelConexaoAcoes = null;
        jButtonSQLLimpar = null;
        jButtonSQLExecutar = null;
        jScrollPaneResultado = null;
        jTableResultado = null;
        jPanelResultado = null;
        model = null;
        jPanelAlgebraExpressao = null;
        tela_consulta = true;
        criaconexao = null;
        criaconexao = new CriaConexao();
        initialize();
    }
    
    private void initialize(){
    	setResizable(false);
        setJMenuBar(getJJMenuBarPrincipal());
        setContentPane(getJContentPaneConsulta());
        setTitle("ProgramAR - Programa de ensino da Algera Relacional");
        setVisible(true);
    	    	
        if(tela_consulta){
            jContentPaneConexao = null;
            setSize(810, 800);
            setPreferredSize(new Dimension(3429, 700));
            EnableAlgebra(false);
            EnableCalculo(false);
            EnableSQL(false);
        } else{
            jContentPaneConsulta = null;
            setSize(810, 720);
            setPreferredSize(new Dimension(3700, 700));
        }
    }

    
    
    public class Botao_Handler implements ActionListener{

        public void actionPerformed(ActionEvent event){
            SimpleAttributeSet set = new SimpleAttributeSet();
            StyledDocument doc = getJTextPaneAlgebraRelacional().getStyledDocument();
            
            StyleConstants.setFontFamily(set, "Algebra");

            StyleConstants.setFontSize(set, 15);
            StyleConstants.setForeground(set, Color.BLUE);
            
            if(event.getSource() == getJButtonSelecao())
                try
                {
                    doc.insertString(getJTextPaneAlgebraRelacional().getCaretPosition(), (new Character('\u038E')).toString(), set);
                    getJTextPaneAlgebraRelacional().requestFocus();
                    return;
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(getJContentPaneConsulta(), "Erro na inser\347\343o do operador.", "Erro", 0);
                }
            
            if(event.getSource() == getJButtonProjecao())
                try
                {
                    doc.insertString(getJTextPaneAlgebraRelacional().getCaretPosition(), (new Character('\u038F')).toString(), set);
                    getJTextPaneAlgebraRelacional().requestFocus();
                    return;
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(getJContentPaneConsulta(), "Erro na inser\347\343o do operador.", "Erro", 0);
                }
            
            if(event.getSource() == getJButtonUniao())
                try
                {
                    doc.insertString(getJTextPaneAlgebraRelacional().getCaretPosition(), (new Character('\u0390')).toString(), set);
                    getJTextPaneAlgebraRelacional().requestFocus();
                    return;
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(getJContentPaneConsulta(), "Erro na inser\347\343o do operador.", "Erro", 0);
                }
            
            if(event.getSource() == getJButtonIntersecao())
                try
                {
                    doc.insertString(getJTextPaneAlgebraRelacional().getCaretPosition(), (new Character('\u0391')).toString(), set);
                    getJTextPaneAlgebraRelacional().requestFocus();
                    return;
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(getJContentPaneConsulta(), "Erro na inser\347\343o do operador.", "Erro", 0);
                }
            
            if(event.getSource() == getJButtonDiferenca())
                try
                {
                    doc.insertString(getJTextPaneAlgebraRelacional().getCaretPosition(), (new Character('\u0392')).toString(), set);
                    getJTextPaneAlgebraRelacional().requestFocus();
                    return;
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(getJContentPaneConsulta(), "Erro na inser\347\343o do operador.", "Erro", 0);
                }
            
            if(event.getSource() == getJButtonProdutoCartesiano())
                try
                {
                    doc.insertString(getJTextPaneAlgebraRelacional().getCaretPosition(), (new Character('\u0393')).toString(), set);
                    getJTextPaneAlgebraRelacional().requestFocus();
                    return;
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(getJContentPaneConsulta(), "Erro na inser\347\343o do operador.", "Erro", 0);
                }
            
            if(event.getSource() == getJButtonJoinNatural())
                try
                {
                    doc.insertString(getJTextPaneAlgebraRelacional().getCaretPosition(), (new Character('\u039B')).toString(), set);
                    getJTextPaneAlgebraRelacional().requestFocus();
                    return;
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(getJContentPaneConsulta(), "Erro na inser\347\343o do operador.", "Erro", 0);
                }
            
            if(event.getSource() == getJButtonRecursao())
                try
                {
                    doc.insertString(getJTextPaneAlgebraRelacional().getCaretPosition(), (new Character('\u03B1')).toString(), set);
                    getJTextPaneAlgebraRelacional().requestFocus();
                    return;
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(getJContentPaneConsulta(), "Erro na inser\347\343o do operador.", "Erro", 0);
                }
            
            if(event.getSource() == getJButtonOuterJoinTotal())
                try
                {
                    doc.insertString(getJTextPaneAlgebraRelacional().getCaretPosition(), (new Character('\u0395')).toString(), set);
                    getJTextPaneAlgebraRelacional().requestFocus();
                    return;
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(getJContentPaneConsulta(), "Erro na inser\347\343o do operador.", "Erro", 0);
                }
            
            if(event.getSource() == getJButtonOuterJoinEsquerda())
                try
                {
                    doc.insertString(getJTextPaneAlgebraRelacional().getCaretPosition(), (new Character('\u0396')).toString(), set);
                    getJTextPaneAlgebraRelacional().requestFocus();
                    return;
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(getJContentPaneConsulta(), "Erro na inser\347\343o do operador.", "Erro", 0);
                }
            
            if(event.getSource() == getJButtonOuterJoinDireita())
                try
                {
                    doc.insertString(getJTextPaneAlgebraRelacional().getCaretPosition(), (new Character('\u0397')).toString(), set);
                    getJTextPaneAlgebraRelacional().requestFocus();
                    return;
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(getJContentPaneConsulta(), "Erro na inser\347\343o do operador.", "Erro", 0);
                }
            
            if(event.getSource() == getJButtonRenomear())
                try
                {
                    doc.insertString(getJTextPaneAlgebraRelacional().getCaretPosition(), (new Character('\u0398')).toString(), set);
                    getJTextPaneAlgebraRelacional().requestFocus();
                    return;
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(getJContentPaneConsulta(), "Erro na inser\347\343o do operador.", "Erro", 0);
                }
            
            if(event.getSource() == getJButtonDivisao())
                try
                {
                    doc.insertString(getJTextPaneAlgebraRelacional().getCaretPosition(), (new Character('\u0399')).toString(), set);
                    getJTextPaneAlgebraRelacional().requestFocus();
                    return;
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(getJContentPaneConsulta(), "Erro na inser\347\343o do operador.", "Erro", 0);
                }
            
            if(event.getSource() == getJButtonJoin())
                try
                {
                    doc.insertString(getJTextPaneAlgebraRelacional().getCaretPosition(), (new Character('\u0394')).toString(), set);
                    getJTextPaneAlgebraRelacional().requestFocus();
                    return;
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(getJContentPaneConsulta(), "Erro na inser\347\343o do operador.", "Erro", 0);
                }
            
            if(event.getSource() == getJButtonAgregacao())
                try
                {
                    doc.insertString(getJTextPaneAlgebraRelacional().getCaretPosition(), (new Character('\u039A')).toString(), set);
                    getJTextPaneAlgebraRelacional().requestFocus();
                    return;
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(getJContentPaneConsulta(), "Erro na inser\347\343o do operador.", "Erro", 0);
                }
            
            if(event.getSource() == getJButtonAlgebraLimpar())
            {
                getJTextPaneAlgebraRelacional().setText("");
                getJTextPaneAlgebraRelacional().requestFocus();
            }
            
            if(event.getSource() == getJButtonAlgebraExecutar())
                if(jComboBoxBanco.getSelectedIndex() != -1)
                {
                    String sch = jComboBoxBanco.getSelectedItem().toString();
                    ExecutaAlgebra executaalgebra = new ExecutaAlgebra(getJTextPaneAlgebraRelacional().getText().trim(), sch.trim());
                    if(executaalgebra.msgerro.length() != 0)
                    {
                        JOptionPane.showMessageDialog(getJContentPaneConsulta(), executaalgebra.msgerro, "Erro", 0);
                    } else
                    {
                        getJTextAreaSQL().setText(executaalgebra.resultado);
                        executaSQL();
                    }
                } else
                {
                    JOptionPane.showMessageDialog(getJContentPaneConsulta(), "Schema n\343o selecionado.", "Erro", 0);
                    return;
                }
            
            if(event.getSource() == getJButtonCalculoLimpar())
            {
                getJTextAreaCalculoRelacional().setText("");
                getJTextAreaCalculoRelacional().requestFocus();
            }
            
            if(event.getSource() != getJButtonCalculoExecutar());
            
            if(event.getSource() == getJButtonSQLLimpar())
            {
                limpaResultado();
                getJTextAreaSQL().setText("");
                getJTextAreaSQL().requestFocus();
            }
            
            if(event.getSource() == getJButtonSQLExecutar())
                executaSQL();
            
            if(event.getSource() == getJMenuItemConexaoBanco())
            {
                tela_consulta = false;
                initialize();
            }
            
            if(event.getSource() == getJMenuItemConsultaBanco())
            {
                tela_consulta = true;
                initialize();
            }
            
            if(event.getSource() == getJButtonConexaoOK())
                verificaconexao();
            
            if(event.getSource() == getJButtonConexaoCancel())
            {
                tela_consulta = true;
                initialize();
            }
        }

        public Botao_Handler()
        {
        }
    }

    public class Radio_Handler
        implements ItemListener
    {

        public void itemStateChanged(ItemEvent event)
        {
            if(event.getSource() == getJRadioButtonAlgebraRelacional())
            {
                EnableAlgebra(true);
                EnableCalculo(false);
                EnableSQL(false);
                getJTextPaneAlgebraRelacional().requestFocus();
            }
            
            event.getSource();
            getJRadioButtonCalculoRelacional();
            
            if(event.getSource() == getJRadioButtonSQL())
            {
                EnableAlgebra(false);
                EnableCalculo(false);
                EnableSQL(true);
                getJTextAreaSQL().requestFocus();
            }
        }

        public Radio_Handler()
        {
        }
    }


    private JMenuBar getJJMenuBarPrincipal()
    {
        if(jJMenuBarPrincipal == null)
        {
            jJMenuBarPrincipal = new JMenuBar();
            jJMenuBarPrincipal.add(getJMenuOpcao());
        }
        return jJMenuBarPrincipal;
    }

    private JMenu getJMenuOpcao()
    {
        if(jMenuOpcao == null)
        {
            jMenuOpcao = new JMenu("Op\347\365es");
            jMenuOpcao.add(getJMenuItemConexaoBanco());
            jMenuOpcao.add(getJMenuItemConsultaBanco());
            Botao_Handler BotaoHandler = new Botao_Handler();
            getJMenuItemConexaoBanco().addActionListener(BotaoHandler);
            getJMenuItemConsultaBanco().addActionListener(BotaoHandler);
        }
        return jMenuOpcao;
    }

    private JMenuItem getJMenuItemConexaoBanco()
    {
        if(jMenuItemConexaoBanco == null)
            jMenuItemConexaoBanco = new JMenuItem("Criar Conex\343o com Banco de Dados");
        return jMenuItemConexaoBanco;
    }

    private JMenuItem getJMenuItemConsultaBanco()
    {
        if(jMenuItemConsultaBanco == null)
            jMenuItemConsultaBanco = new JMenuItem("Consulta ao Banco de Dados");
        return jMenuItemConsultaBanco;
    }

    private JPanel getJPanelBancodeDados()
    {
        if(jPanelBancodeDados == null)
        {
            FlowLayout flowLayout = new FlowLayout();
            flowLayout.setAlignment(0);
            jLabelEscolhaBanco = new JLabel();
            jLabelEscolhaBanco.setText("Escolha o Schema: ");
            jLabelEscolhaBanco.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            jLabelEscolhaBanco.setHorizontalAlignment(2);
            jPanelBancodeDados = new JPanel();
            jPanelBancodeDados.setLayout(flowLayout);
            jPanelBancodeDados.setBorder(BorderFactory.createTitledBorder(null, "Banco de Dados", 0, 0, new Font("Dialog", 1, 12), new Color(51, 51, 51)));
            jPanelBancodeDados.setPreferredSize(new Dimension(790, 63));
            jPanelBancodeDados.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            jPanelBancodeDados.add(jLabelEscolhaBanco, null);
            jPanelBancodeDados.add(getJComboBoxBanco(), null);
        }
        return jPanelBancodeDados;
    }

    private JPanel getJPanelConexao()
    {
        if(jPanelConexao == null)
        {
            FlowLayout flowLayout = new FlowLayout();
            flowLayout.setAlignment(0);
            jLabelServidor = new JLabel();
            jLabelServidor.setText("Servidor: ");
            jLabelServidor.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            jLabelServidor.setHorizontalAlignment(2);
            jLabelUsuario = new JLabel();
            jLabelUsuario.setText("Usuario: ");
            jLabelUsuario.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            jLabelUsuario.setHorizontalAlignment(2);
            jLabelSenha = new JLabel();
            jLabelSenha.setText("Senha: ");
            jLabelSenha.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            jLabelSenha.setHorizontalAlignment(2);
            jLabelSchema = new JLabel();
            jLabelSchema.setText("Schema: ");
            jLabelSchema.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            jLabelSchema.setHorizontalAlignment(2);
            JTextFieldServidor = new JTextField();
            JTextFieldServidor.setText("localhost");
            JTextFieldServidor.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            JTextFieldServidor.setHorizontalAlignment(2);
            JTextFieldUsuario = new JTextField();
            JTextFieldUsuario.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            JTextFieldUsuario.setHorizontalAlignment(2);
            JTextFieldSenha = new JPasswordField();
            JTextFieldSenha.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            JTextFieldSenha.setHorizontalAlignment(2);
            JTextFieldSchema = new JTextField();
            JTextFieldSchema.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            JTextFieldSchema.setHorizontalAlignment(2);
            jPanelConexao = new JPanel();
            jPanelConexao.setLayout(flowLayout);
            jPanelConexao.setBorder(BorderFactory.createTitledBorder(null, "Conectar ao Banco de Dados", 0, 0, new Font("Dialog", 1, 12), new Color(51, 51, 51)));
            jPanelConexao.setPreferredSize(new Dimension(790, 150));
            jPanelConexao.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            jPanelConexao.add(getJPanelConexaoFields(), null);
            jPanelConexao.add(getJPanelConexaoAcoes(), null);
        }
        return jPanelConexao;
    }

    private JPanel getJPanelConexaoFields()
    {
        if(jPanelConexaoFields == null)
        {
            GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
            gridBagConstraints1.gridy = 0;
            gridBagConstraints1.gridx = 0;
            GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
            gridBagConstraints2.gridy = 1;
            gridBagConstraints2.gridx = 0;
            GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
            gridBagConstraints3.gridy = 2;
            gridBagConstraints3.gridx = 0;
            GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
            gridBagConstraints4.gridy = 3;
            gridBagConstraints4.gridx = 0;
            GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
            gridBagConstraints5.gridy = 0;
            gridBagConstraints5.gridx = 1;
            GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
            gridBagConstraints6.gridy = 1;
            gridBagConstraints6.gridx = 1;
            GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
            gridBagConstraints7.gridy = 2;
            gridBagConstraints7.gridx = 1;
            GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
            gridBagConstraints8.gridy = 3;
            gridBagConstraints8.gridx = 1;
            jLabelServidor = new JLabel();
            jLabelServidor.setText("Servidor:  ");
            jLabelServidor.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            jLabelServidor.setHorizontalAlignment(2);
            jLabelUsuario = new JLabel();
            jLabelUsuario.setText("Usuario:  ");
            jLabelUsuario.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            jLabelUsuario.setHorizontalAlignment(2);
            jLabelSenha = new JLabel();
            jLabelSenha.setText("Senha:  ");
            jLabelSenha.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            jLabelSenha.setHorizontalAlignment(2);
            jLabelSchema = new JLabel();
            jLabelSchema.setText("Schema:  ");
            jLabelSchema.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            jLabelSchema.setHorizontalAlignment(2);
            JTextFieldServidor = new JTextField(20);
            JTextFieldServidor.setText("localhost");
            JTextFieldServidor.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            JTextFieldServidor.setHorizontalAlignment(2);
            JTextFieldUsuario = new JTextField(20);
            JTextFieldUsuario.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            JTextFieldUsuario.setHorizontalAlignment(2);
            JTextFieldSenha = new JPasswordField(20);
            JTextFieldSenha.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            JTextFieldSenha.setHorizontalAlignment(2);
            JTextFieldSchema = new JTextField(20);
            JTextFieldSchema.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            JTextFieldSchema.setHorizontalAlignment(2);
            jPanelConexaoFields = new JPanel();
            jPanelConexaoFields.setLayout(new GridBagLayout());
            jPanelConexaoFields.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            jPanelConexaoFields.setPreferredSize(new Dimension(700, 80));
            jPanelConexaoFields.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            jPanelConexaoFields.add(jLabelServidor, gridBagConstraints1);
            jPanelConexaoFields.add(jLabelUsuario, gridBagConstraints2);
            jPanelConexaoFields.add(jLabelSenha, gridBagConstraints3);
            jPanelConexaoFields.add(jLabelSchema, gridBagConstraints4);
            jPanelConexaoFields.add(JTextFieldServidor, gridBagConstraints5);
            jPanelConexaoFields.add(JTextFieldUsuario, gridBagConstraints6);
            jPanelConexaoFields.add(JTextFieldSenha, gridBagConstraints7);
            jPanelConexaoFields.add(JTextFieldSchema, gridBagConstraints8);
        }
        return jPanelConexaoFields;
    }

    private JComboBox<String> getJComboBoxBanco()
    {
        if(jComboBoxBanco == null)
            jComboBoxBanco = new JComboBox<String>();
        ListaBancos = criaconexao.getschemas();
        for(int i = 0; i < ListaBancos.length; i++)
            if(ListaBancos[i] != null)
                jComboBoxBanco.addItem(ListaBancos[i]);

        return jComboBoxBanco;
    }

    private JPanel getJPanelAlgebraRelacional()
    {
        if(jPanelAlgebraRelacional == null)
        {
            jPanelAlgebraRelacional = new JPanel();
            jPanelAlgebraRelacional.setLayout(new FlowLayout(0));
            jPanelAlgebraRelacional.setBorder(BorderFactory.createTitledBorder(null, "Algebra Relacional", 0, 0, new Font("Dialog", 1, 12), new Color(51, 51, 51)));
            jPanelAlgebraRelacional.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            //jPanelAlgebraRelacional.setPreferredSize(new Dimension(790, 200));
            jPanelAlgebraRelacional.setPreferredSize(new Dimension(790, 200));
            jPanelAlgebraRelacional.add(getJPanelAlgebraExpressao(), null);
            jPanelAlgebraRelacional.add(getJPanelbotoesOperacoes(), null);
            jPanelAlgebraRelacional.add(getJPanel(), null);
        }
        return jPanelAlgebraRelacional;
    }

    private JRadioButton getJRadioButtonAlgebraRelacional()
    {
        if(jRadioButtonAlgebraRelacional == null)
        {
            jRadioButtonAlgebraRelacional = new JRadioButton();
            jRadioButtonAlgebraRelacional.setVerticalAlignment(0);
            jRadioButtonAlgebraRelacional.setToolTipText("Clique para habilitar Algebra Relacional");
            jRadioButtonAlgebraRelacional.setText("    Express\343o: ");
            jRadioButtonAlgebraRelacional.setVerticalTextPosition(3);
            Radio_Handler RadioHandler = new Radio_Handler();
            jRadioButtonAlgebraRelacional.addItemListener(RadioHandler);
        }
        return jRadioButtonAlgebraRelacional;
    }

    private JScrollPane getJScrollPaneAlgebraRelacional()
    {
        if(jScrollPaneAlgebraRelacional == null)
        {
            jScrollPaneAlgebraRelacional = new JScrollPane(getJTabbedPane());
            jScrollPaneAlgebraRelacional.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            //jScrollPaneAlgebraRelacional.setPreferredSize(new Dimension(445, 115));
            jScrollPaneAlgebraRelacional.setPreferredSize(new Dimension(530, 80));
            jScrollPaneAlgebraRelacional.setFont(new Font("Dialog", 0, 21));
        }
        return jScrollPaneAlgebraRelacional;
    }

    private JTabbedPane getJTabbedPane()
    {
        if(jtabbedpaneAlgerbaRelacional == null)
        {
            jtabbedpaneAlgerbaRelacional = new JTabbedPane();
            jtabbedpaneAlgerbaRelacional.addTab("Algebra Relacional", getJTextPaneAlgebraRelacional());
        }
        return jtabbedpaneAlgerbaRelacional;
    }

    private JTextPane getJTextPaneAlgebraRelacional()
    {
        if(jTextPaneAlgebraRelacional == null)
        {
            jTextPaneAlgebraRelacional = new JTextPane();
            jTextPaneAlgebraRelacional.setFont(getFont(15));
            jTextPaneAlgebraRelacional.addKeyListener(new KeyAdapter() {

                public void keyPressed(KeyEvent e)
                {
                    if(verifica_caracter(e.getKeyChar()) & (jTextPaneAlgebraRelacional.getText().length() != 0))
                    {
                        int posicao_carret = jTextPaneAlgebraRelacional.getCaretPosition();
                        String texto_aux = new String();
                        texto_aux = jTextPaneAlgebraRelacional.getText();
                        jTextPaneAlgebraRelacional.setText("");
                        ParametroAlgebraTexto parametroalgebra = null;
                        FormataAlgebraTexto formataalgebratexto = new FormataAlgebraTexto(texto_aux);
                        StyledDocument doc = jTextPaneAlgebraRelacional.getStyledDocument();
                        int posicao = 0;
                        for(int i = 0; i < formataalgebratexto.gettamanhoparametro(); i++)
                        {
                            parametroalgebra = formataalgebratexto.getparametro(i);
                            try
                            {
                                doc.insertString(posicao, parametroalgebra.gettexto(), parametroalgebra.getset());
                                posicao += parametroalgebra.gettexto().length();
                            }
                            catch(Exception e2)
                            {
                                JOptionPane.showMessageDialog(getJContentPaneConsulta(), "Erro na inser\347\343o do caracter.", "Erro", 0);
                            }
                        }

                        jTextPaneAlgebraRelacional.setCaretPosition(posicao_carret);
                    }
                }

            }
);
        }
        return jTextPaneAlgebraRelacional;
    }

    public boolean verifica_caracter(int val)
    {
        for(int i = 0; i < vetor_caracteres.length; i++)
        {
            if(vetor_caracteres[i] == val)
                return true;
            if(val < vetor_caracteres[i])
                return false;
        }

        return false;
    }

    private JPanel getJPanelbotoesOperacoes()
    {
        if(jPanelbotoesOperacoes == null)
        {
            GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
            gridBagConstraints11.gridy = 4;
            gridBagConstraints11.gridx = 2;
            GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
            gridBagConstraints10.gridy = 4;
            gridBagConstraints10.gridx = 1;
            GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
            gridBagConstraints9.gridy = 4;
            gridBagConstraints9.gridx = 0;
            GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
            gridBagConstraints8.gridy = 2;
            gridBagConstraints8.gridx = 3;
            GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
            gridBagConstraints7.gridy = 2;
            gridBagConstraints7.gridx = 1;
            GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
            gridBagConstraints6.gridy = 2;
            gridBagConstraints6.gridx = 0;
            GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
            gridBagConstraints5.gridy = 1;
            gridBagConstraints5.gridx = 3;
            GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
            gridBagConstraints41.gridy = 1;
            gridBagConstraints41.gridx = 2;
            GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
            gridBagConstraints31.gridy = 1;
            gridBagConstraints31.gridx = 1;
            GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
            gridBagConstraints21.gridy = 1;
            gridBagConstraints21.gridx = 0;
            GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
            gridBagConstraints4.gridy = 0;
            gridBagConstraints4.gridx = 3;
            GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
            gridBagConstraints3.gridy = 0;
            gridBagConstraints3.gridx = 2;
            GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
            gridBagConstraints2.gridy = 2;
            gridBagConstraints2.gridx = 2;
            GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
            gridBagConstraints1.gridy = 0;
            gridBagConstraints1.gridwidth = 1;
            gridBagConstraints1.gridx = 1;
            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridx = 0;
            jPanelbotoesOperacoes = new JPanel();
            jPanelbotoesOperacoes.setBorder(BorderFactory.createTitledBorder(null, "Opera\347\365es", 0, 0, new Font("Dialog", 1, 12), new Color(51, 51, 51)));
            jPanelbotoesOperacoes.setPreferredSize(new Dimension(210, 165));
            jPanelbotoesOperacoes.setLayout(new GridBagLayout());
            jPanelbotoesOperacoes.add(getJButtonSelecao(), gridBagConstraints);
            jPanelbotoesOperacoes.add(getJButtonProjecao(), gridBagConstraints1);
            jPanelbotoesOperacoes.add(getJButtonRenomear(), gridBagConstraints2);
            jPanelbotoesOperacoes.add(getJButtonUniao(), gridBagConstraints3);
            jPanelbotoesOperacoes.add(getJButtonIntersecao(), gridBagConstraints4);
            jPanelbotoesOperacoes.add(getJButtonDiferenca(), gridBagConstraints21);
            jPanelbotoesOperacoes.add(getJButtonProdutoCartesiano(), gridBagConstraints31);
            jPanelbotoesOperacoes.add(getJButtonJoin(), gridBagConstraints41);
            jPanelbotoesOperacoes.add(getJButtonOuterJoinTotal(), gridBagConstraints5);
            jPanelbotoesOperacoes.add(getJButtonOuterJoinEsquerda(), gridBagConstraints6);
            jPanelbotoesOperacoes.add(getJButtonOuterJoinDireita(), gridBagConstraints7);
            jPanelbotoesOperacoes.add(getJButtonDivisao(), gridBagConstraints8);
            jPanelbotoesOperacoes.add(getJButtonJoinNatural(), gridBagConstraints9);
            jPanelbotoesOperacoes.add(getJButtonAgregacao(), gridBagConstraints10);
            jPanelbotoesOperacoes.add(getJButtonRecursao(), gridBagConstraints11);
            Botao_Handler BotaoHandler = new Botao_Handler();
            getJButtonSelecao().addActionListener(BotaoHandler);
            getJButtonProjecao().addActionListener(BotaoHandler);
            getJButtonRenomear().addActionListener(BotaoHandler);
            getJButtonUniao().addActionListener(BotaoHandler);
            getJButtonIntersecao().addActionListener(BotaoHandler);
            getJButtonDiferenca().addActionListener(BotaoHandler);
            getJButtonProdutoCartesiano().addActionListener(BotaoHandler);
            getJButtonJoin().addActionListener(BotaoHandler);
            getJButtonOuterJoinTotal().addActionListener(BotaoHandler);
            getJButtonOuterJoinEsquerda().addActionListener(BotaoHandler);
            getJButtonOuterJoinDireita().addActionListener(BotaoHandler);
            getJButtonDivisao().addActionListener(BotaoHandler);
            getJButtonJoinNatural().addActionListener(BotaoHandler);
            getJButtonAgregacao().addActionListener(BotaoHandler);
            getJButtonRecursao().addActionListener(BotaoHandler);
        }
        return jPanelbotoesOperacoes;
    }

    private JButton getJButtonSelecao()
    {
        if(jButtonSelecao == null)
        {
            jButtonSelecao = new JButton();
            jButtonSelecao.setPreferredSize(new Dimension(50, 30));
            jButtonSelecao.setFont(getFont(19));
            jButtonSelecao.setToolTipText("Sele\347\343o");
            jButtonSelecao.setText((new Character('\u038E')).toString());
        }
        return jButtonSelecao;
    }

    private JButton getJButtonProjecao()
    {
        if(jButtonProjecao == null)
        {
            jButtonProjecao = new JButton();
            jButtonProjecao.setPreferredSize(new Dimension(50, 30));
            jButtonProjecao.setFont(getFont(13));
            jButtonProjecao.setToolTipText("Proje\347\343o");
            jButtonProjecao.setText((new Character('\u038F')).toString());
        }
        return jButtonProjecao;
    }

    private JButton getJButtonRenomear()
    {
        if(jButtonRenomear == null)
        {
            jButtonRenomear = new JButton();
            jButtonRenomear.setPreferredSize(new Dimension(50, 30));
            jButtonRenomear.setToolTipText("Renomear");
            jButtonRenomear.setFont(getFont(17));
            jButtonRenomear.setText((new Character('\u0398')).toString());
        }
        return jButtonRenomear;
    }

    private JButton getJButtonUniao()
    {
        if(jButtonUniao == null)
        {
            jButtonUniao = new JButton();
            jButtonUniao.setPreferredSize(new Dimension(50, 30));
            jButtonUniao.setFont(getFont(13));
            jButtonUniao.setToolTipText("Uni\343o");
            jButtonUniao.setText((new Character('\u0390')).toString());
        }
        return jButtonUniao;
    }

    private JButton getJButtonIntersecao()
    {
        if(jButtonIntersecao == null)
        {
            jButtonIntersecao = new JButton();
            jButtonIntersecao.setPreferredSize(new Dimension(50, 30));
            jButtonIntersecao.setFont(getFont(14));
            jButtonIntersecao.setToolTipText("Interse\347\343o");
            jButtonIntersecao.setText((new Character('\u0391')).toString());
            jButtonIntersecao.setEnabled(false);
        }
        return jButtonIntersecao;
    }

    private JButton getJButtonDiferenca()
    {
        if(jButtonDiferenca == null)
        {
            jButtonDiferenca = new JButton();
            jButtonDiferenca.setFont(getFont(15));
            jButtonDiferenca.setToolTipText("Diferen\347a");
            jButtonDiferenca.setPreferredSize(new Dimension(50, 30));
            jButtonDiferenca.setText((new Character('\u0392')).toString());
            jButtonDiferenca.setEnabled(false);
        }
        return jButtonDiferenca;
    }

    private JButton getJButtonProdutoCartesiano()
    {
        if(jButtonProdutoCartesiano == null)
        {
            jButtonProdutoCartesiano = new JButton();
            jButtonProdutoCartesiano.setPreferredSize(new Dimension(50, 30));
            jButtonProdutoCartesiano.setFont(getFont(13));
            jButtonProdutoCartesiano.setToolTipText("Produto Cartesiano");
            jButtonProdutoCartesiano.setText((new Character('\u0393')).toString());
        }
        return jButtonProdutoCartesiano;
    }

    private JButton getJButtonJoinNatural()
    {
        if(jButtonJoinNatural == null)
        {
            jButtonJoinNatural = new JButton();
            jButtonJoinNatural.setPreferredSize(new Dimension(50, 30));
            jButtonJoinNatural.setToolTipText("Join Natural");
            jButtonJoinNatural.setFont(getFont(15));
            jButtonJoinNatural.setText((new Character('\u039B')).toString());
        }
        return jButtonJoinNatural;
    }

    private JButton getJButtonJoin()
    {
        if(jButtonJoin == null)
        {
            jButtonJoin = new JButton();
            jButtonJoin.setPreferredSize(new Dimension(50, 30));
            jButtonJoin.setToolTipText("Join");
            jButtonJoin.setFont(getFont(15));
            jButtonJoin.setText((new Character('\u0394')).toString());
        }
        return jButtonJoin;
    }

    private JButton getJButtonAgregacao()
    {
        if(jButtonAgregacao == null)
        {
            jButtonAgregacao = new JButton();
            jButtonAgregacao.setPreferredSize(new Dimension(50, 30));
            jButtonAgregacao.setToolTipText("Agrega\347\343o");
            jButtonAgregacao.setFont(getFont(15));
            jButtonAgregacao.setText((new Character('\u039A')).toString());
        }
        return jButtonAgregacao;
    }

    private JButton getJButtonRecursao()
    {
        if(jButtonRecursao == null)
        {
            jButtonRecursao = new JButton();
            jButtonRecursao.setPreferredSize(new Dimension(50, 30));
            jButtonRecursao.setToolTipText("Recurs\343o");
            jButtonRecursao.setFont(getFont(15));
            jButtonRecursao.setText((new Character('\u03B1')).toString());
        }
        return jButtonRecursao;
    }

    private JButton getJButtonOuterJoinTotal()
    {
        if(jButtonOuterJoinTotal == null)
        {
            jButtonOuterJoinTotal = new JButton();
            jButtonOuterJoinTotal.setPreferredSize(new Dimension(50, 30));
            jButtonOuterJoinTotal.setToolTipText("Outer Join Total");
            jButtonOuterJoinTotal.setFont(getFont(11));
            jButtonOuterJoinTotal.setText((new Character('\u0395')).toString());
        }
        return jButtonOuterJoinTotal;
    }

    private JButton getJButtonOuterJoinEsquerda()
    {
        if(jButtonOuterJoinEsquerda == null)
        {
            jButtonOuterJoinEsquerda = new JButton();
            jButtonOuterJoinEsquerda.setPreferredSize(new Dimension(50, 30));
            jButtonOuterJoinEsquerda.setToolTipText("Outer Join a Esquerda");
            jButtonOuterJoinEsquerda.setFont(getFont(11));
            jButtonOuterJoinEsquerda.setText((new Character('\u0396')).toString());
        }
        return jButtonOuterJoinEsquerda;
    }

    private JButton getJButtonOuterJoinDireita()
    {
        if(jButtonOuterJoinDireita == null)
        {
            jButtonOuterJoinDireita = new JButton();
            jButtonOuterJoinDireita.setPreferredSize(new Dimension(50, 30));
            jButtonOuterJoinDireita.setFont(getFont(11));
            jButtonOuterJoinDireita.setToolTipText("Outer Join a Direita");
            jButtonOuterJoinDireita.setText((new Character('\u0397')).toString());
        }
        return jButtonOuterJoinDireita;
    }

    private JButton getJButtonDivisao()
    {
        if(jButtonDivisao == null)
        {
            jButtonDivisao = new JButton();
            jButtonDivisao.setToolTipText("Divis\343o");
            jButtonDivisao.setFont(getFont(19));
            jButtonDivisao.setPreferredSize(new Dimension(50, 30));
            jButtonDivisao.setText((new Character('\u0399')).toString());
            jButtonDivisao.setEnabled(false);
        }
        return jButtonDivisao;
    }
    
    public Font getFont(int tamanho){  
        Font font = null;
        try{  
        	DataInputStream din = new DataInputStream(new BufferedInputStream(getClass().getResourceAsStream("Algebra.ttf")));
        	font = Font.createFont( Font.TRUETYPE_FONT , din );
        }catch( Exception e ){  
           System.out.println( e.getMessage() );  
        }  
        font = font.deriveFont( Font.PLAIN , tamanho );  
        return font;  
    }  

    private JPanel getJPanel()
    {
        if(jPanel == null)
        {
            jPanel = new JPanel();
            jPanel.setLayout(new GridBagLayout());
        }
        return jPanel;
    }

    private JPanel getJPanelAlgebraAcoes()
    {
        if(jPanelAlgebraAcoes == null)
        {
            GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
            gridBagConstraints10.gridy = 0;
            gridBagConstraints10.gridx = 3;
            GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
            gridBagConstraints9.gridy = 0;
            gridBagConstraints9.gridx = 1;
            jPanelAlgebraAcoes = new JPanel();
            jPanelAlgebraAcoes.setLayout(new GridBagLayout());
            jPanelAlgebraAcoes.setPreferredSize(new Dimension(550, 35));
            jPanelAlgebraAcoes.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            jPanelAlgebraAcoes.add(getJButtonAlgebraLimpar(), gridBagConstraints9);
            jPanelAlgebraAcoes.add(getJButtonAlgebraExecutar(), gridBagConstraints10);
            Botao_Handler BotaoHandler = new Botao_Handler();
            getJButtonAlgebraLimpar().addActionListener(BotaoHandler);
            getJButtonAlgebraExecutar().addActionListener(BotaoHandler);
        }
        return jPanelAlgebraAcoes;
    }

    private JButton getJButtonAlgebraLimpar()
    {
        if(jButtonAlgebraLimpar == null)
        {
            jButtonAlgebraLimpar = new JButton();
            //jButtonAlgebraLimpar.setPreferredSize(new Dimension(90, 25));
            jButtonAlgebraLimpar.setPreferredSize(new Dimension(90, 40));
            jButtonAlgebraLimpar.setText("Limpar");
        }
        return jButtonAlgebraLimpar;
    }

    private JButton getJButtonAlgebraExecutar()
    {
        if(jButtonAlgebraExecutar == null)
        {
            jButtonAlgebraExecutar = new JButton();
            //jButtonAlgebraExecutar.setPreferredSize(new Dimension(90, 25));
            jButtonAlgebraExecutar.setPreferredSize(new Dimension(90, 40));
            jButtonAlgebraExecutar.setText("Executar");
        }
        return jButtonAlgebraExecutar;
    }

    private JPanel getJPanelCalculoRelacional()
    {
        if(jPanelCalculoRelacional == null)
        {
            jPanelCalculoRelacional = new JPanel();
            jPanelCalculoRelacional.setLayout(new FlowLayout(0));
            //jPanelCalculoRelacional.setPreferredSize(new Dimension(790, 100));
            jPanelCalculoRelacional.setPreferredSize(new Dimension(790, 130));
            jPanelCalculoRelacional.setBorder(BorderFactory.createTitledBorder(null, "Calculo Relacional", 0, 0, new Font("Dialog", 1, 12), new Color(51, 51, 51)));
            jPanelCalculoRelacional.add(getJRadioButtonCalculoRelacional(), null);
            jPanelCalculoRelacional.add(getJScrollPaneCalculoRelacional(), null);
            jPanelCalculoRelacional.add(getJPanelCalculoAcoes(), null);
        }
        return jPanelCalculoRelacional;
    }

    private JRadioButton getJRadioButtonCalculoRelacional()
    {
        if(jRadioButtonCalculoRelacional == null)
        {
            jRadioButtonCalculoRelacional = new JRadioButton();
            jRadioButtonCalculoRelacional.setVerticalAlignment(0);
            jRadioButtonCalculoRelacional.setToolTipText("Clique para habilitar Calculo Relacional");
            jRadioButtonCalculoRelacional.setText("    Express\343o: ");
            jRadioButtonCalculoRelacional.setVerticalTextPosition(3);
            jRadioButtonCalculoRelacional.setEnabled(false);
            Radio_Handler RadioHandler = new Radio_Handler();
            jRadioButtonCalculoRelacional.addItemListener(RadioHandler);
        }
        return jRadioButtonCalculoRelacional;
    }

    private JScrollPane getJScrollPaneCalculoRelacional()
    {
        if(jScrollPaneCalculoRelacional == null)
        {
            jScrollPaneCalculoRelacional = new JScrollPane();
            jScrollPaneCalculoRelacional.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            //jScrollPaneCalculoRelacional.setPreferredSize(new Dimension(660, 30));
            jScrollPaneCalculoRelacional.setPreferredSize(new Dimension(770, 30));
            jScrollPaneCalculoRelacional.setViewportView(getJTextAreaCalculoRelacional());
            jScrollPaneCalculoRelacional.setFont(new Font("Dialog", 0, 12));
        }
        return jScrollPaneCalculoRelacional;
    }

    private JTextArea getJTextAreaCalculoRelacional()
    {
        if(jTextAreaCalculoRelacional == null)
        {
            jTextAreaCalculoRelacional = new JTextArea();
            jTextAreaCalculoRelacional.setFont(new Font("Dialog", 0, 12));
        }
        return jTextAreaCalculoRelacional;
    }

    private JPanel getJPanelCalculoAcoes()
    {
        if(jPanelCalculoAcoes == null)
        {
            GridBagConstraints gridBagConstraints101 = new GridBagConstraints();
            gridBagConstraints101.gridy = 0;
            gridBagConstraints101.gridx = 3;
            GridBagConstraints gridBagConstraints91 = new GridBagConstraints();
            gridBagConstraints91.gridy = 0;
            gridBagConstraints91.gridx = 1;
            jPanelCalculoAcoes = new JPanel();
            jPanelCalculoAcoes.setLayout(new GridBagLayout());
            jPanelCalculoAcoes.setPreferredSize(new Dimension(557, 35));
            //jPanelCalculoAcoes.setPreferredSize(new Dimension(557, 100));
            jPanelCalculoAcoes.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            jPanelCalculoAcoes.add(getJButtonCalculoLimpar(), gridBagConstraints91);
            jPanelCalculoAcoes.add(getJButtonCalculoExecutar(), gridBagConstraints101);
            Botao_Handler BotaoHandler = new Botao_Handler();
            getJButtonCalculoLimpar().addActionListener(BotaoHandler);
            getJButtonCalculoExecutar().addActionListener(BotaoHandler);
        }
        return jPanelCalculoAcoes;
    }

    private JButton getJButtonCalculoLimpar()
    {
        if(jButtonCalculoLimpar == null)
        {
            jButtonCalculoLimpar = new JButton();
            //jButtonCalculoLimpar.setPreferredSize(new Dimension(90, 25));
            jButtonCalculoLimpar.setPreferredSize(new Dimension(90, 40));
            jButtonCalculoLimpar.setText("Limpar");
        }
        return jButtonCalculoLimpar;
    }

    private JButton getJButtonCalculoExecutar()
    {
        if(jButtonCalculoExecutar == null)
        {
            jButtonCalculoExecutar = new JButton();
            //jButtonCalculoExecutar.setPreferredSize(new Dimension(90, 25));
            jButtonCalculoExecutar.setPreferredSize(new Dimension(90, 40));
            jButtonCalculoExecutar.setText("Executar");
        }
        return jButtonCalculoExecutar;
    }

    private JPanel getJPanelSQL()
    {
        if(jPanelSQL == null)
        {
            jPanelSQL = new JPanel();
            jPanelSQL.setLayout(new FlowLayout(0));
            //jPanelSQL.setPreferredSize(new Dimension(790, 130));
            jPanelSQL.setPreferredSize(new Dimension(790, 150));
            jPanelSQL.setBorder(BorderFactory.createTitledBorder(null, "SQL", 0, 0, new Font("Dialog", 1, 12), new Color(51, 51, 51)));
            jPanelSQL.add(getJRadioButtonSQL(), null);
            jPanelSQL.add(getJScrollPaneSQL(), null);
            jPanelSQL.add(getJPanelSQLAcoes(), null);
        }
        return jPanelSQL;
    }

    private JRadioButton getJRadioButtonSQL()
    {
        if(jRadioButtonSQL == null)
        {
            jRadioButtonSQL = new JRadioButton();
            jRadioButtonSQL.setVerticalAlignment(0);
            jRadioButtonSQL.setToolTipText("Clique para habilitar SQL");
            jRadioButtonSQL.setText("    Express\343o: ");
            jRadioButtonSQL.setVerticalTextPosition(3);
            Radio_Handler RadioHandler = new Radio_Handler();
            jRadioButtonSQL.addItemListener(RadioHandler);
        }
        return jRadioButtonSQL;
    }

    private JScrollPane getJScrollPaneSQL()
    {
        if(jScrollPaneSQL == null)
        {
            jScrollPaneSQL = new JScrollPane();
            jScrollPaneSQL.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            //jScrollPaneSQL.setPreferredSize(new Dimension(660, 55));
            jScrollPaneSQL.setPreferredSize(new Dimension(770, 45));
            jScrollPaneSQL.setViewportView(getJTextAreaSQL());
            jScrollPaneSQL.setFont(new Font("Dialog", 0, 12));
        }
        return jScrollPaneSQL;
    }

    private JTextArea getJTextAreaSQL()
    {
        if(jTextAreaSQL == null)
        {
            jTextAreaSQL = new JTextArea();
            jTextAreaSQL.setFont(new Font("Dialog", 0, 12));
        }
        return jTextAreaSQL;
    }

    private JPanel getJPanelSQLAcoes()
    {
        if(jPanelSQLAcoes == null)
        {
            GridBagConstraints gridBagConstraints1011 = new GridBagConstraints();
            gridBagConstraints1011.gridy = 0;
            gridBagConstraints1011.gridx = 3;
            GridBagConstraints gridBagConstraints911 = new GridBagConstraints();
            gridBagConstraints911.gridy = 0;
            gridBagConstraints911.gridx = 1;
            jPanelSQLAcoes = new JPanel();
            jPanelSQLAcoes.setLayout(new GridBagLayout());
            jPanelSQLAcoes.setPreferredSize(new Dimension(557, 35));
            jPanelSQLAcoes.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            jPanelSQLAcoes.add(getJButtonSQLLimpar(), gridBagConstraints911);
            jPanelSQLAcoes.add(getJButtonSQLExecutar(), gridBagConstraints1011);
            Botao_Handler BotaoHandler = new Botao_Handler();
            getJButtonSQLLimpar().addActionListener(BotaoHandler);
            getJButtonSQLExecutar().addActionListener(BotaoHandler);
        }
        return jPanelSQLAcoes;
    }

    private JPanel getJPanelConexaoAcoes()
    {
        if(jPanelConexaoAcoes == null)
        {
            GridBagConstraints gridBagConstraints1012 = new GridBagConstraints();
            gridBagConstraints1012.gridy = 0;
            gridBagConstraints1012.gridx = 3;
            GridBagConstraints gridBagConstraints912 = new GridBagConstraints();
            gridBagConstraints912.gridy = 0;
            gridBagConstraints912.gridx = 1;
            jPanelConexaoAcoes = new JPanel();
            jPanelConexaoAcoes.setLayout(new GridBagLayout());
            jPanelConexaoAcoes.setPreferredSize(new Dimension(700, 35));
            jPanelConexaoAcoes.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            jPanelConexaoAcoes.add(getJButtonConexaoOK(), gridBagConstraints912);
            jPanelConexaoAcoes.add(getJButtonConexaoCancel(), gridBagConstraints1012);
            Botao_Handler BotaoHandler = new Botao_Handler();
            getJButtonConexaoOK().addActionListener(BotaoHandler);
            getJButtonConexaoCancel().addActionListener(BotaoHandler);
        }
        return jPanelConexaoAcoes;
    }

    private JButton getJButtonSQLLimpar()
    {
        if(jButtonSQLLimpar == null)
        {
            jButtonSQLLimpar = new JButton();
            //jButtonSQLLimpar.setPreferredSize(new Dimension(90, 25));
            jButtonSQLLimpar.setPreferredSize(new Dimension(90, 40));
            jButtonSQLLimpar.setText("Limpar");
        }
        return jButtonSQLLimpar;
    }

    private JButton getJButtonConexaoOK()
    {
        if(jButtonConexaoOK == null)
        {
            jButtonConexaoOK = new JButton();
            //jButtonConexaoOK.setPreferredSize(new Dimension(90, 25));
            jButtonConexaoOK.setPreferredSize(new Dimension(90, 40));
            jButtonConexaoOK.setText("OK");
        }
        return jButtonConexaoOK;
    }

    private JButton getJButtonConexaoCancel()
    {
        if(jButtonConexaoCancel == null)
        {
            jButtonConexaoCancel = new JButton();
            //jButtonConexaoCancel.setPreferredSize(new Dimension(90, 25));
            jButtonConexaoCancel.setPreferredSize(new Dimension(90, 40));
            jButtonConexaoCancel.setText("Cancelar");
        }
        return jButtonConexaoCancel;
    }

    private JButton getJButtonSQLExecutar()
    {
        if(jButtonSQLExecutar == null)
        {
            jButtonSQLExecutar = new JButton();
            //jButtonSQLExecutar.setPreferredSize(new Dimension(90, 25));
            jButtonSQLExecutar.setPreferredSize(new Dimension(90, 40));
            jButtonSQLExecutar.setText("Executar");
        }
        return jButtonSQLExecutar;
    }

    private JScrollPane getJScrollPaneResultado()
    {
        if(jScrollPaneResultado == null)
        {
            jScrollPaneResultado = new JScrollPane();
            jScrollPaneResultado.setPreferredSize(new Dimension(770, 110));
            jScrollPaneResultado.setViewportView(getJTableResultado());
            jScrollPaneResultado.setVisible(true);
        }
        return jScrollPaneResultado;
    }

    private JTable getJTableResultado()
    {
        if(jTableResultado == null)
        {
            model = new DefaultTableModel();
            jTableResultado = new JTable(model);
        }
        return jTableResultado;
    }

    private JPanel getJPanelResultado()
    {
        if(jPanelResultado == null)
        {
            jPanelResultado = new JPanel();
            jPanelResultado.setLayout(new FlowLayout(0));
            jPanelResultado.setPreferredSize(new Dimension(790, 150));
            jPanelResultado.setBorder(BorderFactory.createTitledBorder(null, "Resultado", 0, 0, new Font("Dialog", 1, 12), new Color(51, 51, 51)));
            jPanelResultado.add(getJScrollPaneResultado(), null);
        }
        return jPanelResultado;
    }

    private JPanel getJPanelAlgebraExpressao()
    {
        if(jPanelAlgebraExpressao == null)
        {
            jPanelAlgebraExpressao = new JPanel();
            jPanelAlgebraExpressao.setLayout(new FlowLayout(0));
            jPanelAlgebraExpressao.setPreferredSize(new Dimension(560, 155));
            jPanelAlgebraExpressao.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            jPanelAlgebraExpressao.add(getJRadioButtonAlgebraRelacional());
            jPanelAlgebraExpressao.add(getJScrollPaneAlgebraRelacional());
            jPanelAlgebraExpressao.add(getJPanelAlgebraAcoes());
        }
        return jPanelAlgebraExpressao;
    }

    public static void main()
    {
    	 
        SwingUtilities.invokeLater(new Runnable() {

            public void run()
            {
            	Tela_Principal thisClass = new Tela_Principal();
                thisClass.setDefaultCloseOperation(3);
                thisClass.setVisible(true);
            }

        }
);
    }

    private JPanel getJContentPaneConsulta()
    {
        if(jContentPaneConsulta == null)
        {
            jContentPaneConsulta = new JPanel();
            jContentPaneConsulta.setLayout(new FlowLayout(0));
            jContentPaneConsulta.add(getJPanelBancodeDados(), null);
            jContentPaneConsulta.add(getJPanelAlgebraRelacional(), null);
            jContentPaneConsulta.add(getJPanelCalculoRelacional(), null);
            jContentPaneConsulta.add(getJPanelSQL(), null);
            jContentPaneConsulta.add(getJPanelResultado(), null);
            radioGroup = new ButtonGroup();
            radioGroup.add(getJRadioButtonAlgebraRelacional());
            radioGroup.add(getJRadioButtonCalculoRelacional());
            radioGroup.add(getJRadioButtonSQL());
        }
        return jContentPaneConsulta;
    }

    private JPanel getJContentPaneConexao()
    {
        if(jContentPaneConexao == null)
        {
            jContentPaneConexao = new JPanel();
            jContentPaneConexao.setLayout(new FlowLayout(0));
            jContentPaneConexao.add(getJPanelConexao(), null);
        }
        return jContentPaneConexao;
    }

    private void EnableAlgebra(boolean habilita)
    {
        getJScrollPaneAlgebraRelacional().setEnabled(habilita);
        getJTextPaneAlgebraRelacional().setEnabled(habilita);
        getJTabbedPane().setEnabled(habilita);
        getJButtonSelecao().setEnabled(habilita);
        getJButtonProjecao().setEnabled(habilita);
        getJButtonUniao().setEnabled(habilita);
        getJButtonProdutoCartesiano().setEnabled(habilita);
        getJButtonJoinNatural().setEnabled(habilita);
        getJButtonRecursao().setEnabled(habilita);
        getJButtonOuterJoinTotal().setEnabled(habilita);
        getJButtonOuterJoinEsquerda().setEnabled(habilita);
        getJButtonOuterJoinDireita().setEnabled(habilita);
        getJButtonRenomear().setEnabled(habilita);
        getJButtonJoin().setEnabled(habilita);
        getJButtonAgregacao().setEnabled(habilita);
        getJButtonAlgebraLimpar().setEnabled(habilita);
        getJButtonAlgebraExecutar().setEnabled(habilita);
    }

    private void EnableCalculo(boolean habilita)
    {
        getJScrollPaneCalculoRelacional().setEnabled(habilita);
        getJTextAreaCalculoRelacional().setEnabled(habilita);
        getJButtonCalculoLimpar().setEnabled(habilita);
        getJButtonCalculoExecutar().setEnabled(habilita);
    }

    private void EnableSQL(boolean habilita)
    {
        getJScrollPaneSQL().setEnabled(habilita);
        getJTextAreaSQL().setEnabled(habilita);
        getJButtonSQLLimpar().setEnabled(habilita);
        getJButtonSQLExecutar().setEnabled(habilita);
    }

    private void verificaconexao()
    {
        if(JTextFieldServidor.getText().length() == 0)
        {
            JOptionPane.showMessageDialog(getJContentPaneConexao(), "O campo Servidor \351 de preenchimento obrigat\363rio.", "Info", 1);
            return;
        }
        if(JTextFieldUsuario.getText().length() == 0)
        {
            JOptionPane.showMessageDialog(getJContentPaneConexao(), "O campo Usu\341rio \351 de preenchimento obrigat\363rio.", "Info", 1);
            return;
        }
        if(JTextFieldSchema.getText().length() == 0)
        {
            JOptionPane.showMessageDialog(getJContentPaneConexao(), "O campo Schema \351 de preenchimento obrigat\363rio.", "Info", 1);
            return;
        }
        if(!criaconexao.testConnection(JTextFieldServidor.getText(), JTextFieldUsuario.getText(), JTextFieldSenha.getPassword(), JTextFieldSchema.getText()))
        {
            JOptionPane.showMessageDialog(getJContentPaneConexao(), "Erro na conex\343o.", "Erro", 0);
            return;
        }
        criaconexao.gravarConnection(JTextFieldServidor.getText(), JTextFieldUsuario.getText(), JTextFieldSenha.getText(), JTextFieldSchema.getText());
        jComboBoxBanco.removeAllItems();
        ListaBancos = criaconexao.getschemas();
        for(int i = 0; i < ListaBancos.length; i++)
            if(ListaBancos[i] != null)
                jComboBoxBanco.insertItemAt(ListaBancos[i], i);

        JOptionPane.showMessageDialog(getJContentPaneConexao(), "Conex\343o criada com sucesso!", "Info", 1);
    }

    private void limpaResultado()
    {
        Vector<String> columnHeads = new Vector<String>();
        Vector<Vector<String>> rows = new Vector<Vector<String>>();
        model.setDataVector(rows, columnHeads);
        model.fireTableDataChanged();
        getJScrollPaneResultado().setVisible(false);
    }

    private void displayResultSet(ResultSet rs)
        throws SQLException
    {
        boolean moreRecords = rs.next();
        if(!moreRecords)
        {
            JOptionPane.showMessageDialog(this, "Consulta n\343o retornou nenhuma tupla");
            return;
        }
        Vector<String> columnHeads = new Vector<String>();
        Vector<Vector<String>> rows = new Vector<Vector<String>>();
        try
        {
            ResultSetMetaData rsmd = rs.getMetaData();
            for(int i = 1; i <= rsmd.getColumnCount(); i++)
                columnHeads.addElement(rsmd.getColumnLabel(i));

            do
            {
                rs.wasNull();
                rows.addElement(getNextRow(rs, rsmd));
            } while(rs.next());
            model.setDataVector(rows, columnHeads);
            model.fireTableDataChanged();
            getJScrollPaneResultado().setVisible(true);
        }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
        }
    }

    private Vector<String> getNextRow(ResultSet rs, ResultSetMetaData rsmd)
        throws SQLException
    {
        Vector<String> currentRow = new Vector<String>();
        for(int i = 1; i <= rsmd.getColumnCount(); i++)
        {
            String st = rs.getString(i);
            if(st != null)
                currentRow.addElement(st);
            else
                currentRow.addElement("NULL");
        }

        return currentRow;
    }

    private void executaSQL()
    {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try
        {
            if(jComboBoxBanco.getSelectedIndex() != -1)
            {
                String sch = jComboBoxBanco.getSelectedItem().toString();
                conn = criaconexao.getConnection(sch);
                stmt = conn.createStatement();
                String Query = getJTextAreaSQL().getText();
                rs = stmt.executeQuery(Query);
                displayResultSet(rs);
            } else
            {
                JOptionPane.showMessageDialog(getJContentPaneConsulta(), "Schema n\343o selecionado.", "Erro", 0);
                return;
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(getJContentPaneConsulta(), e.getMessage());
        }
    }
}
