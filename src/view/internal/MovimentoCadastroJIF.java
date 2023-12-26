/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
///  jComboBox_Natureza formulario jLabel_Calculado
package view.internal;

import Interface.*;
import Interface.Principal;
import Consulta.JDialogBuscaProduto;
import Consulta.JDialogComplementar;
import ModeloBeans.BeansPalete;
import ModeloBeans.Beans_Movimento;
import ModeloBeans.Beans_Nota;
import ModeloBeans.ModeloTabela;
import ConectaBanco.ConexaoBD;
import Consulta.JDialogAuxilioChave;
import ModeloDao.Dao_Movimento;
import ModeloDao.Dao_Nota;
import ModeloDao.Dao_Transporte;
import ModeloBeans.Beans_Transporte;
import ModeloDao.Dao_Palete;

import java.awt.Color;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author edson
 */
/// pree
public class MovimentoCadastroJIF extends javax.swing.JInternalFrame {

    ConexaoBD conex_Fornecedor = new ConexaoBD();
    ConexaoBD conex_Natureza = new ConexaoBD();
    ConexaoBD conex_SelecionarNatureza = new ConexaoBD();
    ConexaoBD conex_SelecionarFornecedor = new ConexaoBD();
    ConexaoBD conex_Movimento = new ConexaoBD();
    ConexaoBD conex_MeuTotal = new ConexaoBD();
    ConexaoBD conex_Transporte = new ConexaoBD();
    ConexaoBD conex_Palete = new ConexaoBD();
    ConexaoBD conex_Palete2 = new ConexaoBD();

    ConexaoBD conex = new ConexaoBD();
    ConexaoBD conex2 = new ConexaoBD();
    ConexaoBD conexApaga = new ConexaoBD();
    ConexaoBD conexInsere = new ConexaoBD();
    ConexaoBD conexTabela = new ConexaoBD();
    ConexaoBD conexSaldo = new ConexaoBD();

    Dao_Nota dao = new Dao_Nota();
    Beans_Nota beans = new Beans_Nota();
    Dao_Movimento Mdao = new Dao_Movimento();
    Beans_Movimento Mbeans = new Beans_Movimento();
    Beans_Transporte beanst = new Beans_Transporte();
    Dao_Transporte daot = new Dao_Transporte();
    BeansPalete beansp = new BeansPalete();
    Dao_Palete daop = new Dao_Palete();
    Principal menu;
    JDialogComplementar Complementar = new JDialogComplementar(menu, rootPaneCheckingEnabled);
    JDialogBuscaProduto BP = new JDialogBuscaProduto(menu, rootPaneCheckingEnabled);
//DialogCalculado CC = new JDialogCalculado(menu, rootPaneCheckingEnabled);
    int id_referencia;
    int id_referenciUltimo;
    int id_nota;
    int MinhaNaturezaInt;
    int SelecionarNaturezaInt;
    String MinhaNatureza;
    String MinhaIdNota;
    String MinhaData;

    int MeuFornecedorInt;
    String MeuFornecedor;

    String Referencia_Movimento;
    String SelecaoReferencia_Movimento;

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    Date data = null;

    int Id_Prod_Ent = 0;
    int FlagAlterar = 0;
    double ValorReal = 0;
    String ValorMoeda = null;

    String resultado_Calculo_Valor;
    String resultado_Calculo_Formatado;

    int FlagPalete = 0;
    String Id_Palete, Id_Linha;
    String QuantidadePalete;
    String CalcTotalFinal = "";
    String MeuTotal;
    String ContadorLinha;
    String MinhaCopia = "null";
    String MeuMovPaleteReferencia;
    String MinhaIdMov, MinhaIdSistema;
    int IdMinhaLinha;
    int verificador01 = 0;
    int ComboFocu = 0;
    int ComboFocuNatureza = 0;
    int ComboFocuFornecedor = 0;

    /**
     * Creates new form MovimentoJIF
     */
    public MovimentoCadastroJIF() {
        initComponents();

        vol_transportadora.setVisible(false);
        jLabel_Complemento.setVisible(false);
        jLabel_Setor.setVisible(false);
        jLabel_IdProduto.setVisible(false);
        jLabel_IdMovimento.setVisible(false);
        jLabel_Calculado.setVisible(false);
        jComboBox_Situacao.setVisible(false);
        jComboBox_transportadoraInt.setVisible(false);
        jTextField_data_variavel.setVisible(false);
        jTextField_Fragmento_Variavel.setVisible(false);
        jTextField_Varieavel_SaldoProduto.setVisible(false);
        jComboBox_Fornecedor_Int.setVisible(false);
        jComboBox_Natureza_Tipo.setVisible(false);
        jComboBox_Natureza_Int.setVisible(false);
        
    }
 

    public void EventoBuscaProduto() {
        //        jTextField_Busca_Produto_Nota.setText(jTextField_Busca_Produto_Nota.getText().toUpperCase());
        String s = jTextField_Busca_Produto_Nota.getText();
        if (s.matches("^[a-zA-ZÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü]*$")) {
            if (jLabel_IdProduto.getText() == "0") {
                BuscaProdutoDescricao();
            } else {

            }
        } else if (s.matches("^[0-9]*$")) {
            jLabel_IdProduto.setText(s);
            BuscaProdutoId();
        } else {
            if (jLabel_IdProduto.getText() == "0") {
                BuscaProdutoDescricao();
            } else {
            }
        }
        jTextField_Quantidade_Nota.requestFocus();
    }

    public void EncontrarUltimo() {

        conex.conexao();
        conex.executaSql("SELECT *  FROM produto   where stprod=1   order by id_prod asc");
        try {
            conex.rs.last();
            Id_Prod_Ent = (Integer.parseInt(conex.rs.getString("sis_prod")));
            jLabel_IdProduto.setText((conex.rs.getString("sis_prod")));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "BuscaProdutoId()\n" + ex);
            Id_Prod_Ent = (0);
            jLabel_IdProduto.setText("0");

        }
        conex.desconecta();
    }

    public void BuscaProdutoId() {
        String descricao;
        String edicao;
        String FormatoReal = null;
        Id_Prod_Ent = Integer.parseInt(jLabel_IdProduto.getText());
        if (Id_Prod_Ent == 0) {
        } else {
            conex.conexao();
            conex.executaSql("SELECT *  FROM produto inner join unidade on  idunid=id_unidade  where sis_prod = '" + Id_Prod_Ent + "'  and stprod=1   order by id_prod asc");
            try {
                conex.rs.last();
                String Fragmento_Variavel;
                int Fragmento_Variavel_Int = (conex.rs.getInt("fragmento_unidade"));
                if (Fragmento_Variavel_Int == 1) {
                    Fragmento_Variavel = "1";
                } else {
                    Fragmento_Variavel = "0";
                }
                jTextField_Fragmento_Variavel.setText(Fragmento_Variavel);
                System.out.println("Fragmento_Variavel   -   " + Fragmento_Variavel);
                String Fragmento_Variavel_SaldoProduto = (conex.rs.getString("saldo_prod"));
                jTextField_Varieavel_SaldoProduto.setText(Fragmento_Variavel_SaldoProduto);
                System.out.println("Fragmento_Variavel_SaldoProduto   -   " + Fragmento_Variavel_SaldoProduto);
                String SelecaaoSaldo = (conex.rs.getString("saldo_prod"));
                if (SelecaaoSaldo == null) {
                    System.out.println("selecao saldo null");
                } else if (SelecaaoSaldo.equals("null")) {
                    System.out.println("selecao saldo null");
                } else {
                    System.out.println("selecao saldo Ok");
                    System.out.println("Interface.Movimento.MovimentoJIFAlterado.BuscaProdutoId()    A\n " + SelecaaoSaldo);
                    SelecaaoSaldo = SelecaaoSaldo.replace(".", ".").replace(",", ".");
                    System.out.println("Interface.Movimento.MovimentoJIFAlterado.BuscaProdutoId()   B \n " + SelecaaoSaldo);
                    Double num4 = Double.parseDouble(String.valueOf(SelecaaoSaldo));
                    System.out.println("Interface.Movimento.MovimentoJIFAlterado.BuscaProdutoId()    C\n " + num4);
                    BigDecimal df = new BigDecimal(num4);
                    NumberFormat nf = NumberFormat.getInstance();
                    nf.setMaximumFractionDigits(4);
                    FormatoReal = nf.format(df);
                }

                System.out.println("Interface.Movimento.MovimentoJIFAlterado.BuscaProdutoId()    D\n " + FormatoReal);
                String SelecaaoUnid = (conex.rs.getString("sigla_unidade"));
                jLabelMeuSaldoProduto.setText(FormatoReal + " / " + SelecaaoUnid);
                ValorMoeda = (conex.rs.getString("valor_prod_ex"));
                ValorReal = (Double.parseDouble(conex.rs.getString("valor_prod")));
                edicao = conex.rs.getString("edicao_prod");
                if (edicao.equals(null) | edicao.equals("") | edicao.equals(" ")) {
                    edicao = "";
                } else {
                    edicao = " N° " + edicao;
                }
                descricao = " " + conex.rs.getString("tipo_prod") + " " + conex.rs.getString("nome_prod") + edicao;
                descricao = descricao.toUpperCase();
                jTextField_Busca_Produto_Nota.setText(descricao);
                Id_Prod_Ent = (Integer.parseInt(conex.rs.getString("sis_prod")));
                jLabel_IdProduto.setText(String.valueOf(Id_Prod_Ent));
            } catch (SQLException ex) {
                System.out.println("BuscaProdutoId() " + ex);
                jLabelMeuSaldoProduto.setText("");
                Id_Prod_Ent = (0);
                jLabel_IdProduto.setText("0");
                System.out.println("BuscaProdutoId() " + ex);
                BP.recebe_nome(jTextField_Busca_Produto_Nota.getText());
                BP.setVisible(true);

            }

            conex.desconecta();
        }
    }

    public void BuscaProdutoDescricao() {
        Id_Prod_Ent = (0);
        jLabel_IdProduto.setText("0");
        String FormatoReal = null;
        String descricao;
        String edicao;

        String MinhaBusca = (jTextField_Busca_Produto_Nota.getText());
        MinhaBusca = MinhaBusca.replace("Nº ", "");
        MinhaBusca = MinhaBusca.replace("N° ", "");
        MinhaBusca = MinhaBusca.replace("Nº", "");//  N°
        MinhaBusca = MinhaBusca.replace("N°", "");
        System.out.println("MinhaBusca " + MinhaBusca);
        int Id_Prod_comparar;
        conex.conexao();
        conex.executaSql("select * from produto inner join unidade on  idunid=id_unidade where   (coalesce((sis_prod)) ||' '||coalesce((tipo_prod))||' '||coalesce((nome_prod))||' '||coalesce((edicao_prod))) ilike '%" + MinhaBusca + "%' and stprod=1  order by id_prod asc");
        try {
            conex.rs.last();/// un_prod
            String SelecaaoSaldo = (conex.rs.getString("saldo_prod"));
            System.out.println("Interface.Movimento.MovimentoJIFAlterado.BuscaProdutoId()    A\n " + SelecaaoSaldo);
            if (SelecaaoSaldo == null | SelecaaoSaldo.equals("null")) {
                SelecaaoSaldo = "0";
                System.out.println("saldo null");
            } else {
                SelecaaoSaldo = SelecaaoSaldo.replace(".", ".").replace(",", ".");
                System.out.println("saldo certo");
                Double num4 = Double.parseDouble(String.valueOf(SelecaaoSaldo));
                BigDecimal df = new BigDecimal(num4);
                NumberFormat nf = NumberFormat.getInstance();
//                 nf.setMinimumFractionDigits(3);
                nf.setMaximumFractionDigits(4);
                FormatoReal = nf.format(df);
            }

            System.out.println("BuscaProdutoDescricao OK " + " " + FormatoReal);
            String SelecaaoUnid = (conex.rs.getString("sigla_unidade"));
            jLabelMeuSaldoProduto.setText(FormatoReal + " / " + SelecaaoUnid);
            ValorMoeda = (conex.rs.getString("valor_prod_ex"));
            ValorReal = (Double.parseDouble(conex.rs.getString("valor_prod")));
            Id_Prod_Ent = (Integer.parseInt(conex.rs.getString("sis_prod")));
            edicao = conex.rs.getString("edicao_prod");

            if (edicao.equals(null) | edicao.equals("") | edicao.equals(" ")) {
                edicao = "";
            } else {
                edicao = " N° " + edicao;
            }

            descricao = " " + conex.rs.getString("tipo_prod") + " " + conex.rs.getString("nome_prod") + edicao;
            descricao = descricao.toUpperCase();
            System.out.println("BuscaProdutoDescricao OK " + " " + descricao);
            conex.rs.first();
            Id_Prod_comparar = (Integer.parseInt(conex.rs.getString("sis_prod")));
            if (Id_Prod_comparar == Id_Prod_Ent) {
                jLabel_IdProduto.setText(String.valueOf(Id_Prod_Ent));
                jTextField_Busca_Produto_Nota.setText(descricao);
                System.out.println(" BuscaProdutoDescricao() " + "É igual");
            } else {
                System.out.println(" BuscaProdutoDescricao() " + "Não é igual");
                BP.recebe_nome(MinhaBusca);
                jLabelMeuSaldoProduto.setText("");
                BP.setVisible(true);
            }
        } catch (SQLException ex) {
            System.out.println(" BuscaProdutoDescricao() " + ex);

        }
        conex.desconecta();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jComboBox_Natureza = new javax.swing.JComboBox<>();
        jComboBox_Tipo_Fornecedor = new javax.swing.JComboBox<>();
        jDateChooser_Data_Nota = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox_Documento = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jTextField_Nota = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField_Chave_Nota = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jComboBox_Fornecedor = new javax.swing.JComboBox<>();
        jButton_Atualizar = new javax.swing.JButton();
        jButton_Limpa_Relogio = new javax.swing.JButton();
        jTextField_Hora = new javax.swing.JTextField();
        jRadioButton_ativa_Vizualizar = new javax.swing.JRadioButton();
        jLabel_Informacoes = new javax.swing.JLabel();
        jLabel_Status_Visualizar = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel_Texto_Produto1 = new javax.swing.JLabel();
        jLabelMeuSaldoProduto = new javax.swing.JLabel();
        jButton_Adicionar_Produto_Nota = new javax.swing.JButton();
        jButton_Fechar_Nota = new javax.swing.JButton();
        jButton_Excluir_Movimento_Nota = new javax.swing.JButton();
        jTextField_Quantidade_Nota = new javax.swing.JTextField();
        jTextField_Busca_Produto_Nota = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableListaProduto_Nota = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel_Titulo = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableListaProduto_Complemento = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel_Titulo1 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jComboBox_modalidade = new javax.swing.JComboBox<>();
        jComboBox_transportadora = new javax.swing.JComboBox<>();
        jRadioButton1 = new javax.swing.JRadioButton();
        vol_motorista = new javax.swing.JTextField();
        jLabel_Complemento = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel_Calculado = new javax.swing.JLabel();
        jLabel_IdMovimento = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel_Setor = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        vol_uf = new javax.swing.JTextField();
        vol_placa = new javax.swing.JTextField();
        jComboBox_Situacao = new javax.swing.JComboBox<>();
        jLabel_IdProduto = new javax.swing.JLabel();
        jLabel_exibe_transporte = new javax.swing.JLabel();
        jComboBox_transportadoraInt = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea_Observacao = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        vol_peso_bruto = new javax.swing.JTextField();
        vol_transportadora = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel_Titulo2 = new javax.swing.JLabel();
        vol_numeracao = new javax.swing.JTextField();
        vol_peso_liquido = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        vol_quantidade = new javax.swing.JTextField();
        vol_especie = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jComboBox_Natureza_Int = new javax.swing.JComboBox<>();
        jComboBox_Natureza_Tipo = new javax.swing.JComboBox<>();
        jComboBox_Fornecedor_Int = new javax.swing.JComboBox<>();
        jTextField_data_variavel = new javax.swing.JTextField();
        jTextField_Fragmento_Variavel = new javax.swing.JTextField();
        jTextField_Varieavel_SaldoProduto = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTableListaProduto_Palete_Produto = new javax.swing.JTable();
        jbALin = new javax.swing.JButton();
        jbLimpar = new javax.swing.JButton();
        jbAdicionar = new javax.swing.JButton();
        jButton_Bandeira1 = new javax.swing.JButton();
        jbALis = new javax.swing.JButton();
        jtTotPct = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jtPctAvu = new javax.swing.JTextField();
        jtAlt = new javax.swing.JTextField();
        jtLast = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jtPctPad = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jtTotParc = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jtUnAvu = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTablePalete = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jLabel_Exibe_para_Palete = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 255, 204));

        jButton1.setText("Salvar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Excluir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(0, 255, 204));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setText("Natureza da Operação :");

        jLabel13.setText("Cliente / Fornecedor:");

        jComboBox_Natureza.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_NaturezaItemStateChanged(evt);
            }
        });
        jComboBox_Natureza.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jComboBox_NaturezaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jComboBox_NaturezaFocusLost(evt);
            }
        });
        jComboBox_Natureza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_NaturezaActionPerformed(evt);
            }
        });
        jComboBox_Natureza.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox_NaturezaKeyPressed(evt);
            }
        });

        jComboBox_Tipo_Fornecedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FORN/CLIE", "FORNECEDOR", "CLIENTE", "OUTRO" }));
        jComboBox_Tipo_Fornecedor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_Tipo_FornecedorItemStateChanged(evt);
            }
        });
        jComboBox_Tipo_Fornecedor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jComboBox_Tipo_FornecedorFocusLost(evt);
            }
        });
        jComboBox_Tipo_Fornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_Tipo_FornecedorActionPerformed(evt);
            }
        });
        jComboBox_Tipo_Fornecedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox_Tipo_FornecedorKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox_Tipo_FornecedorKeyReleased(evt);
            }
        });

        jDateChooser_Data_Nota.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                jDateChooser_Data_NotaHierarchyChanged(evt);
            }
        });
        jDateChooser_Data_Nota.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                jDateChooser_Data_NotaAncestorMoved(evt);
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jDateChooser_Data_NotaAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jDateChooser_Data_Nota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jDateChooser_Data_NotaMouseClicked(evt);
            }
        });
        jDateChooser_Data_Nota.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser_Data_NotaPropertyChange(evt);
            }
        });
        jDateChooser_Data_Nota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jDateChooser_Data_NotaKeyPressed(evt);
            }
        });
        jDateChooser_Data_Nota.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                jDateChooser_Data_NotaVetoableChange(evt);
            }
        });

        jLabel3.setText("Data :");

        jLabel4.setText("Hora :");

        jComboBox_Documento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nota", "Protocolo", "Avulso", "Não definido" }));
        jComboBox_Documento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_DocumentoItemStateChanged(evt);
            }
        });
        jComboBox_Documento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jComboBox_DocumentoFocusLost(evt);
            }
        });
        jComboBox_Documento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_DocumentoActionPerformed(evt);
            }
        });
        jComboBox_Documento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox_DocumentoKeyPressed(evt);
            }
        });

        jLabel5.setText("Documento: ");

        jTextField_Nota.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_NotaFocusLost(evt);
            }
        });
        jTextField_Nota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_NotaActionPerformed(evt);
            }
        });
        jTextField_Nota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_NotaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_NotaKeyReleased(evt);
            }
        });

        jLabel6.setText("N° :");

        jTextField_Chave_Nota.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_Chave_NotaFocusLost(evt);
            }
        });
        jTextField_Chave_Nota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Chave_NotaActionPerformed(evt);
            }
        });
        jTextField_Chave_Nota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_Chave_NotaKeyPressed(evt);
            }
        });

        jLabel7.setText("Chave :");

        jComboBox_Fornecedor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_FornecedorItemStateChanged(evt);
            }
        });
        jComboBox_Fornecedor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jComboBox_FornecedorFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jComboBox_FornecedorFocusLost(evt);
            }
        });
        jComboBox_Fornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_FornecedorActionPerformed(evt);
            }
        });
        jComboBox_Fornecedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox_FornecedorKeyPressed(evt);
            }
        });

        jButton_Atualizar.setForeground(new java.awt.Color(255, 51, 51));
        jButton_Atualizar.setToolTipText("Atualizar Campos");
        jButton_Atualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AtualizarActionPerformed(evt);
            }
        });
        jButton_Atualizar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton_AtualizarKeyPressed(evt);
            }
        });

        jButton_Limpa_Relogio.setForeground(new java.awt.Color(255, 51, 51));
        jButton_Limpa_Relogio.setToolTipText("Limpa Relogio");
        jButton_Limpa_Relogio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Limpa_RelogioActionPerformed(evt);
            }
        });
        jButton_Limpa_Relogio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton_Limpa_RelogioKeyPressed(evt);
            }
        });

        jTextField_Hora.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_HoraFocusLost(evt);
            }
        });
        jTextField_Hora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_HoraActionPerformed(evt);
            }
        });
        jTextField_Hora.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_HoraKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_HoraKeyReleased(evt);
            }
        });

        jRadioButton_ativa_Vizualizar.setText("Ativa vizualizar");
        jRadioButton_ativa_Vizualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_ativa_VizualizarActionPerformed(evt);
            }
        });

        jLabel_Informacoes.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel_Informacoes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Informacoes.setText(" ");
        jLabel_Informacoes.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(10, 10, 10)
                                .addComponent(jComboBox_Natureza, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton_ativa_Vizualizar))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox_Tipo_Fornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox_Fornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_Limpa_Relogio, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jButton_Atualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser_Data_Nota, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Hora, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox_Documento, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_Nota, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_Chave_Nota)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_Informacoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox_Natureza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(jRadioButton_ativa_Vizualizar))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox_Tipo_Fornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox_Fornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateChooser_Data_Nota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton_Atualizar, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(jTextField_Hora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton_Limpa_Relogio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox_Documento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField_Nota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel6)
                        .addComponent(jTextField_Chave_Nota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel_Informacoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel8Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton_Atualizar, jButton_Limpa_Relogio, jDateChooser_Data_Nota, jLabel3, jLabel4, jTextField_Hora});

        jLabel_Status_Visualizar.setText("Status :");
        jLabel_Status_Visualizar.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBackground(new java.awt.Color(0, 255, 204));

        jLabel_Texto_Produto1.setText("Produto :");

        jLabelMeuSaldoProduto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton_Adicionar_Produto_Nota.setForeground(new java.awt.Color(255, 51, 51));
        jButton_Adicionar_Produto_Nota.setText("Inserir");
        jButton_Adicionar_Produto_Nota.setToolTipText("Inclui Produto");
        jButton_Adicionar_Produto_Nota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Adicionar_Produto_NotaActionPerformed(evt);
            }
        });
        jButton_Adicionar_Produto_Nota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton_Adicionar_Produto_NotaKeyPressed(evt);
            }
        });

        jButton_Fechar_Nota.setForeground(new java.awt.Color(255, 51, 51));
        jButton_Fechar_Nota.setText("Limpar");
        jButton_Fechar_Nota.setToolTipText("Limpa Produto");
        jButton_Fechar_Nota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Fechar_NotaActionPerformed(evt);
            }
        });

        jButton_Excluir_Movimento_Nota.setForeground(new java.awt.Color(255, 51, 51));
        jButton_Excluir_Movimento_Nota.setText("Excluir");
        jButton_Excluir_Movimento_Nota.setToolTipText("Exclui Produto");
        jButton_Excluir_Movimento_Nota.setEnabled(false);
        jButton_Excluir_Movimento_Nota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Excluir_Movimento_NotaActionPerformed(evt);
            }
        });

        jTextField_Quantidade_Nota.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_Quantidade_NotaFocusLost(evt);
            }
        });
        jTextField_Quantidade_Nota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_Quantidade_NotaKeyPressed(evt);
            }
        });

        jTextField_Busca_Produto_Nota.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_Busca_Produto_NotaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_Busca_Produto_NotaFocusLost(evt);
            }
        });
        jTextField_Busca_Produto_Nota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_Busca_Produto_NotaKeyPressed(evt);
            }
        });

        jLabel20.setText("Qtd :");

        jTableListaProduto_Nota.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableListaProduto_Nota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableListaProduto_NotaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTableListaProduto_NotaMouseEntered(evt);
            }
        });
        jScrollPane4.setViewportView(jTableListaProduto_Nota);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_Texto_Produto1)
                .addGap(8, 8, 8)
                .addComponent(jTextField_Busca_Produto_Nota, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_Quantidade_Nota, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelMeuSaldoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_Adicionar_Produto_Nota, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_Fechar_Nota)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_Excluir_Movimento_Nota)
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane4)
                    .addContainerGap()))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton_Adicionar_Produto_Nota, jButton_Excluir_Movimento_Nota, jButton_Fechar_Nota});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField_Busca_Produto_Nota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel_Texto_Produto1)
                        .addComponent(jTextField_Quantidade_Nota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel20)
                        .addComponent(jButton_Adicionar_Produto_Nota, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_Fechar_Nota, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_Excluir_Movimento_Nota, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelMeuSaldoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(184, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(37, 37, 37)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton_Adicionar_Produto_Nota, jButton_Excluir_Movimento_Nota, jButton_Fechar_Nota, jLabel20, jLabelMeuSaldoProduto, jLabel_Texto_Produto1, jTextField_Busca_Produto_Nota, jTextField_Quantidade_Nota});

        jTabbedPane1.addTab("Dados", jPanel2);

        jPanel3.setBackground(new java.awt.Color(0, 255, 204));

        jLabel_Titulo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel_Titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Titulo.setText("Cadastro de Complementos");
        jLabel_Titulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton6.setText("Adicionar novo produto");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton5.setText("Adicionar novo complemento");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTableListaProduto_Complemento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableListaProduto_Complemento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableListaProduto_ComplementoMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTableListaProduto_Complemento);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_Titulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1034, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_Titulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Complemento", jPanel3);

        jPanel4.setBackground(new java.awt.Color(0, 255, 204));

        jLabel_Titulo1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel_Titulo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Titulo1.setText("Transportadora");
        jLabel_Titulo1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel24.setText("Transportadora :");

        jLabel10.setText("Modalidade do Frete :");

        jComboBox_modalidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0-NÃO DEFINIDO", "1-DESTINATARIO", "2-REMETENTE", "3-TRANSPORTADORA", "4-DEVOLUÇÃO", "5-OUTRO" }));
        jComboBox_modalidade.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_modalidadeItemStateChanged(evt);
            }
        });

        jComboBox_transportadora.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NÃO DEFINIDO" }));
        jComboBox_transportadora.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_transportadoraItemStateChanged(evt);
            }
        });
        jComboBox_transportadora.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jComboBox_transportadoraFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jComboBox_transportadoraFocusLost(evt);
            }
        });
        jComboBox_transportadora.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox_transportadoraKeyPressed(evt);
            }
        });

        jRadioButton1.setText("Ativa / desativa");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        vol_motorista.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                vol_motoristaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                vol_motoristaFocusLost(evt);
            }
        });

        jLabel_Complemento.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Motorista :");

        jLabel_Calculado.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel_IdMovimento.setText("0");
        jLabel_IdMovimento.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("UF");

        jLabel_Setor.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Placa Veiculo");

        vol_uf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                vol_ufFocusLost(evt);
            }
        });

        vol_placa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                vol_placaFocusLost(evt);
            }
        });
        vol_placa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                vol_placaKeyReleased(evt);
            }
        });

        jComboBox_Situacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1-CALCULADO", "2-PRONTO", "3-AGUARDANDO", "4-ENVIADO", "5-DEVOLVIDO", "6-OUTRO" }));
        jComboBox_Situacao.setEnabled(false);
        jComboBox_Situacao.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_SituacaoItemStateChanged(evt);
            }
        });
        jComboBox_Situacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_SituacaoActionPerformed(evt);
            }
        });

        jLabel_IdProduto.setText("0");
        jLabel_IdProduto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel_exibe_transporte.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_Titulo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_exibe_transporte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(vol_motorista, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(vol_placa, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vol_uf, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_Complemento, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_Setor, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_IdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_IdMovimento, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_Calculado, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(jComboBox_Situacao, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 70, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox_modalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox_transportadora, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox_transportadoraInt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jRadioButton1)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_Titulo1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel24)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboBox_transportadora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox_transportadoraInt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jComboBox_modalidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)
                        .addComponent(jLabel21))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel_Complemento, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel_Setor, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel_IdProduto)
                                .addComponent(jLabel_IdMovimento)
                                .addComponent(jLabel_Calculado))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(vol_motorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(vol_placa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(vol_uf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jComboBox_Situacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_exibe_transporte, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBox_modalidade, jComboBox_transportadora, jLabel10, jLabel24, jRadioButton1});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel19, jLabel21, jLabel28});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBox_Situacao, jLabel_Calculado, jLabel_Complemento, jLabel_IdMovimento, jLabel_IdProduto, jLabel_Setor});

        jTabbedPane1.addTab("Transportadora", jPanel4);

        jPanel5.setBackground(new java.awt.Color(0, 255, 204));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Observações");
        jLabel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextArea_Observacao.setColumns(20);
        jTextArea_Observacao.setRows(5);
        jTextArea_Observacao.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPane7.setViewportView(jTextArea_Observacao);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1054, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(4, 4, 4)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 1046, Short.MAX_VALUE))
                    .addGap(4, 4, 4)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 215, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addGap(6, 6, 6)
                    .addComponent(jLabel16)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jTabbedPane1.addTab("Informações Adicionais", jPanel5);

        jPanel6.setBackground(new java.awt.Color(0, 255, 204));

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Peso Bruto");

        vol_transportadora.setText("1");
        vol_transportadora.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                vol_transportadoraFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                vol_transportadoraFocusLost(evt);
            }
        });

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Peso Liquido");

        jLabel_Titulo2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel_Titulo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Titulo2.setText("Volumes transportados");
        jLabel_Titulo2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        vol_numeracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vol_numeracaoActionPerformed(evt);
            }
        });

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Quantidade");

        vol_especie.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                vol_especieFocusLost(evt);
            }
        });

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Espécie");

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Numeração");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(vol_quantidade, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(vol_especie, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(vol_numeracao, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(vol_peso_bruto)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(vol_peso_liquido, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addComponent(vol_transportadora, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(261, Short.MAX_VALUE))
                    .addComponent(jLabel_Titulo2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField_Fragmento_Variavel, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_data_variavel, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jComboBox_Natureza_Int, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox_Natureza_Tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox_Fornecedor_Int, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField_Varieavel_SaldoProduto))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel23, jLabel25, jLabel26, jLabel27, jLabel30, vol_especie, vol_numeracao, vol_peso_bruto, vol_peso_liquido, vol_quantidade, vol_transportadora});

        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_Titulo2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(vol_peso_bruto)
                            .addComponent(vol_peso_liquido)
                            .addComponent(vol_numeracao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vol_transportadora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vol_especie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vol_quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_Natureza_Int, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_Natureza_Tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_Fornecedor_Int, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_data_variavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_Fragmento_Variavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_Varieavel_SaldoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        jTabbedPane1.addTab("Volumes", jPanel6);

        jPanel7.setBackground(new java.awt.Color(0, 255, 204));

        jTableListaProduto_Palete_Produto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableListaProduto_Palete_Produto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableListaProduto_Palete_ProdutoMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(jTableListaProduto_Palete_Produto);

        jbALin.setBackground(new java.awt.Color(255, 0, 0));
        jbALin.setText("Apagar Linha");
        jbALin.setEnabled(false);
        jbALin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbALinActionPerformed(evt);
            }
        });

        jbLimpar.setText("Limpar");
        jbLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbLimparActionPerformed(evt);
            }
        });

        jbAdicionar.setText("Adicionar");
        jbAdicionar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jbAdicionarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jbAdicionarFocusLost(evt);
            }
        });
        jbAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAdicionarActionPerformed(evt);
            }
        });
        jbAdicionar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jbAdicionarKeyPressed(evt);
            }
        });

        jButton_Bandeira1.setForeground(new java.awt.Color(0, 204, 51));
        jButton_Bandeira1.setText("Lançar ");
        jButton_Bandeira1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Bandeira1ActionPerformed(evt);
            }
        });

        jbALis.setBackground(new java.awt.Color(255, 0, 0));
        jbALis.setText("Apagar Lista");
        jbALis.setEnabled(false);
        jbALis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbALisActionPerformed(evt);
            }
        });

        jtTotPct.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtTotPct.setEnabled(false);
        jtTotPct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtTotPctActionPerformed(evt);
            }
        });

        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("Pct Padrão");

        jtPctAvu.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtPctAvu.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtPctAvuFocusLost(evt);
            }
        });
        jtPctAvu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtPctAvuKeyPressed(evt);
            }
        });

        jtAlt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtAlt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtAltFocusLost(evt);
            }
        });
        jtAlt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtAltKeyPressed(evt);
            }
        });

        jtLast.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtLast.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtLastFocusLost(evt);
            }
        });
        jtLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtLastActionPerformed(evt);
            }
        });
        jtLast.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtLastKeyPressed(evt);
            }
        });

        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("Total de Pcts");

        jtPctPad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtPctPad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtPctPadFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtPctPadFocusLost(evt);
            }
        });
        jtPctPad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtPctPadKeyPressed(evt);
            }
        });

        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("Total palete");

        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("Pct Avulso");

        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("Un Avulso");

        jtTotParc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtTotParc.setEnabled(false);

        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Altura");

        jtUnAvu.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtUnAvu.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtUnAvuFocusLost(evt);
            }
        });
        jtUnAvu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtUnAvuActionPerformed(evt);
            }
        });
        jtUnAvu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtUnAvuKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtUnAvuKeyReleased(evt);
            }
        });

        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("Lastro");

        jTablePalete.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTablePalete.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTablePalete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablePaleteMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(jTablePalete);

        jButton4.setText("Filtrar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel_Exibe_para_Palete.setText("   ");
        jLabel_Exibe_para_Palete.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbALis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_Bandeira1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbALin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_Exibe_para_Palete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtPctPad)
                            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtLast, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                            .addComponent(jtAlt))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtPctAvu, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtTotPct)
                            .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtUnAvu, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtTotParc)
                            .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton_Bandeira1, jbALin, jbALis, jbAdicionar, jbLimpar});

        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtPctPad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel36)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtLast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel37)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtAlt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel40)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtUnAvu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel38)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtPctAvu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel41)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtTotParc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtTotPct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel_Exibe_para_Palete, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbAdicionar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbLimpar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbALis)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbALin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Bandeira1)))
                .addContainerGap())
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton_Bandeira1, jbALin, jbALis, jbAdicionar, jbLimpar});

        jTabbedPane1.addTab("Paletes", jPanel7);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel_Status_Visualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2, jButton3});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jLabel_Status_Visualizar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton3, jLabel_Status_Visualizar});

        jMenu1.setText("File");

        jMenuItem4.setText("Repete ultimo");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem5.setText("Sair");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItem1.setText("Atualizar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setText("Atualiza tabela");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("Gerar copia");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void LimpaCampoProduto() {
        Id_Prod_Ent = 0;
        jTextField_Busca_Produto_Nota.setText(null);
        jTextField_Quantidade_Nota.setText(null);
        jTextField_Busca_Produto_Nota.requestFocus();
        jLabel_IdProduto.setText("0");
        jLabel_Calculado.setText("");
        jLabel_Complemento.setText("");
        jLabel_Setor.setText("");
        jTextField_Busca_Produto_Nota.setBackground(Color.white);
        jTextField_Quantidade_Nota.setBackground(Color.white);
        PreencherTabela();
    }

    public void BotaoAdicionarProduto() {
        String s = jTextField_Busca_Produto_Nota.getText();
        String q = jTextField_Quantidade_Nota.getText();
        if (jLabel_IdProduto.getText().equals("0")) {
            if (s.matches("^[0-9]*$")) {
                jLabel_IdProduto.setText(jTextField_Busca_Produto_Nota.getText());
            } else {

            }
        } else {
        }
        if (jLabel_IdProduto.getText().equals("0") | jLabel_IdProduto.getText().equals("") | jLabel_IdProduto.getText().equals(null)) { /// |jLabel_IdProduto.getText() == ""
            JOptionPane.showMessageDialog(rootPane, "if");
        } else {
            BuscaProdutoId();
            if (jTextField_Quantidade_Nota.getText().equals("")
                    | jTextField_Quantidade_Nota.getText().equals(" ")
                    | jTextField_Quantidade_Nota.getText().equals(null)) {
                jTextField_Quantidade_Nota.requestFocus();
                jTextField_Quantidade_Nota.setBackground(Color.red);
            } else {
                jTextField_Quantidade_Nota.setBackground(null);
                jTextField_Quantidade_Nota.setText(jTextField_Quantidade_Nota.getText().replace(" ", ""));
                q = jTextField_Quantidade_Nota.getText();
                if (q.matches("^[0-9,.]*$")) {
                    System.out.println("Interface.Movimento.MovimentoJIF.jButton_Adicionar_Produto_NotaActionPerformed()" + Referencia_Movimento);
                    EventoCalculaQuantidade_X_Valor();
                    BotaoSalvarMovimento();
                    LimpaCampoProduto();
                } else {
                    jTextField_Quantidade_Nota.requestFocus();
                    jTextField_Quantidade_Nota.setBackground(Color.red);
                }
            }
        }
        PreencherTabela();

    }

    public void PreencherTabela() {
        PreencherTabelaMovimento();
        PreencherTabelaComplemento();
        PreencherTabelaProdutoPalete();
    }

    public void PreencherTabelaMovimento() {
        jLabelMeuSaldoProduto.setText("");
        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{"Item", "Id", "Descrição", "Unid", "Qtd", "Calc", "V.Unit", "V.Total", ""};// "Usuario", "Registro"
        conexTabela.conexao();
        conexTabela.executaSql2("SELECT * FROM movproduto inner join produto on id_prod_ent=sis_prod \n"
                + " inner join unidade on idunid= id_unidade where nota_mov='" + id_referencia + "' and modo_mov='1' "
                + "and stprod = 1 and stmovimento=1 or stmovimento=4  order by id_mov asc ");
        try {
            conexTabela.rs.first();
            do {
                String descricao;
                String edicao;
                String texto_movimento;
                edicao = conexTabela.rs.getString("edicao_prod");
                if (edicao.equals(null) | edicao.equals("") | edicao.equals(" ")) {
                    edicao = "";
                } else {
                    edicao = " N° " + edicao;
                }
                descricao = " " + conexTabela.rs.getString("tipo_prod") + " " + conexTabela.rs.getString("nome_prod") + edicao;
                descricao = descricao.toUpperCase();
                texto_movimento = "" + descricao + "  " + conexTabela.rs.getString("complemento_mov") + "  " + conexTabela.rs.getString("destino_mov");;
                dados.add(new Object[]{
                    conexTabela.rs.getInt("id_mov"), conexTabela.rs.getInt("id_prod_ent"), texto_movimento, conexTabela.rs.getString("sigla_unidade"),
                    conexTabela.rs.getString("qtd_prod_ex"), conexTabela.rs.getString("qtd_calc_ex"), conexTabela.rs.getString("valor_moeda"),
                    conexTabela.rs.getString("total_mov"), conexTabela.rs.getString("sistema_mov")});
            } while (conexTabela.rs.next());
            System.out.println("preencher tabela ok");
        } catch (SQLException ex) {
            System.out.println("erro tabela\n" + ex);
        }
        ModeloTabela modelo = new ModeloTabela(dados, colunas);

        jTableListaProduto_Nota.setModel(modelo);
        jTableListaProduto_Nota.getColumnModel().getColumn(0).setPreferredWidth(40);
        jTableListaProduto_Nota.getColumnModel().getColumn(0).setResizable(true);
        jTableListaProduto_Nota.getColumnModel().getColumn(1).setPreferredWidth(40);
        jTableListaProduto_Nota.getColumnModel().getColumn(1).setResizable(true);
        jTableListaProduto_Nota.getColumnModel().getColumn(2).setPreferredWidth(550);
        jTableListaProduto_Nota.getColumnModel().getColumn(2).setResizable(true);
        jTableListaProduto_Nota.getColumnModel().getColumn(3).setPreferredWidth(60);
        jTableListaProduto_Nota.getColumnModel().getColumn(3).setResizable(true);
        jTableListaProduto_Nota.getColumnModel().getColumn(4).setPreferredWidth(60);
        jTableListaProduto_Nota.getColumnModel().getColumn(4).setResizable(true);
        jTableListaProduto_Nota.getColumnModel().getColumn(5).setPreferredWidth(60);
        jTableListaProduto_Nota.getColumnModel().getColumn(5).setResizable(true);
        jTableListaProduto_Nota.getColumnModel().getColumn(6).setPreferredWidth(80);
        jTableListaProduto_Nota.getColumnModel().getColumn(6).setResizable(true);
        jTableListaProduto_Nota.getColumnModel().getColumn(7).setPreferredWidth(120);
        jTableListaProduto_Nota.getColumnModel().getColumn(7).setResizable(true);
        jTableListaProduto_Nota.getColumnModel().getColumn(8).setPreferredWidth(00);
        jTableListaProduto_Nota.getColumnModel().getColumn(8).setResizable(true);
        jTableListaProduto_Nota.getTableHeader().setReorderingAllowed(false);
        jTableListaProduto_Nota.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTableListaProduto_Nota.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        conexTabela.desconecta();
    }

    public void PreencherTabelaComplemento() {

        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{"Item", "Id", "Descrição", "Unid", "Qtd", "Calc", "V.Unit", "V.Total", ""};// "Usuario", "Registro"
        conexTabela.conexao();
        conexTabela.executaSql2("SELECT * FROM movproduto \n"
                + " inner join produto on id_prod_ent=sis_prod \n"
                + " inner join unidade on idunid= id_unidade\n"
                + "where nota_mov='" + id_referencia + "'\n"
                + "  and stprod = 1 and modo_mov='2'  and stmovimento=1 or stmovimento=4 order by id_mov asc ");
        try {
            conexTabela.rs.first();
            do {
                String descricao;
                String edicao;
                String texto_movimento;
                edicao = conexTabela.rs.getString("edicao_prod");
                if (edicao.equals(null) | edicao.equals("") | edicao.equals(" ")) {
                    edicao = "";
                } else {
                    edicao = " N° " + edicao;
                }
                descricao = " " + conexTabela.rs.getString("tipo_prod") + " " + conexTabela.rs.getString("nome_prod") + edicao;
                descricao = descricao.toUpperCase();
                texto_movimento = "" + descricao + "  " + conexTabela.rs.getString("complemento_mov") + "  " + conexTabela.rs.getString("destino_mov");;
                dados.add(new Object[]{
                    conexTabela.rs.getInt("id_mov"), conexTabela.rs.getInt("id_prod_ent"), texto_movimento, conexTabela.rs.getString("sigla_unidade"),
                    conexTabela.rs.getString("qtd_prod_ex"), conexTabela.rs.getString("qtd_calc_ex"), conexTabela.rs.getString("valor_moeda"),
                    conexTabela.rs.getString("total_mov"), conexTabela.rs.getString("sistema_mov")});
            } while (conexTabela.rs.next());
            System.out.println("preencher tabela ok");
        } catch (SQLException ex) {
            System.out.println("erro tabela\n" + ex);
        }
        ModeloTabela modelo = new ModeloTabela(dados, colunas);

        jTableListaProduto_Complemento.setModel(modelo);
        jTableListaProduto_Complemento.getColumnModel().getColumn(0).setPreferredWidth(40);
        jTableListaProduto_Complemento.getColumnModel().getColumn(0).setResizable(true);
        jTableListaProduto_Complemento.getColumnModel().getColumn(1).setPreferredWidth(40);
        jTableListaProduto_Complemento.getColumnModel().getColumn(1).setResizable(true);
        jTableListaProduto_Complemento.getColumnModel().getColumn(2).setPreferredWidth(550);
        jTableListaProduto_Complemento.getColumnModel().getColumn(2).setResizable(true);
        jTableListaProduto_Complemento.getColumnModel().getColumn(3).setPreferredWidth(60);
        jTableListaProduto_Complemento.getColumnModel().getColumn(3).setResizable(true);
        jTableListaProduto_Complemento.getColumnModel().getColumn(4).setPreferredWidth(60);
        jTableListaProduto_Complemento.getColumnModel().getColumn(4).setResizable(true);
        jTableListaProduto_Complemento.getColumnModel().getColumn(5).setPreferredWidth(60);
        jTableListaProduto_Complemento.getColumnModel().getColumn(5).setResizable(true);
        jTableListaProduto_Complemento.getColumnModel().getColumn(6).setPreferredWidth(80);
        jTableListaProduto_Complemento.getColumnModel().getColumn(6).setResizable(true);
        jTableListaProduto_Complemento.getColumnModel().getColumn(7).setPreferredWidth(120);
        jTableListaProduto_Complemento.getColumnModel().getColumn(7).setResizable(true);
        jTableListaProduto_Complemento.getColumnModel().getColumn(8).setPreferredWidth(00);
        jTableListaProduto_Complemento.getColumnModel().getColumn(8).setResizable(true);
        jTableListaProduto_Complemento.getTableHeader().setReorderingAllowed(false);
        jTableListaProduto_Complemento.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTableListaProduto_Complemento.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        conexTabela.desconecta();
    }

    public void PreencherTabelaProdutoPalete() {
        BloqueiaCamposPalete();
        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{"Item", "Descrição", "Unid", "Qtd", ""};
        conexTabela.conexao();
        conexTabela.executaSql2(""
                + "SELECT * FROM movproduto \n"
                + " inner join produto on id_prod_ent=sis_prod \n"
                + " inner join unidade on idunid= id_unidade\n"
                + "where nota_mov='" + id_referencia + "'\n"
                + "and modo_mov='1' and stprod = 1  and stmovimento=1 or stmovimento=4 order by id_mov asc ");
        try {
            conexTabela.rs.first();
            do {
                String descricao;
                String edicao;
                String texto_movimento;
                edicao = conexTabela.rs.getString("edicao_prod");
                if (edicao.equals(null) | edicao.equals("") | edicao.equals(" ")) {
                    descricao = conexTabela.rs.getString("tipo_prod") + " " + conexTabela.rs.getString("nome_prod");
                } else {
                    descricao = conexTabela.rs.getString("tipo_prod") + " " + conexTabela.rs.getString("nome_prod") + " " + conexTabela.rs.getString("edicao_prod");
                }
                texto_movimento = "" + descricao + "  " + conexTabela.rs.getString("complemento_mov") + "  " + conexTabela.rs.getString("destino_mov");;
                dados.add(new Object[]{
                    conexTabela.rs.getInt("id_mov"), texto_movimento, conexTabela.rs.getString("sigla_unidade"),
                    conexTabela.rs.getString("qtd_prod_ex"), conexTabela.rs.getString("sistema_mov")});
            } while (conexTabela.rs.next()); ///// total_mov     usuario_mov
            System.out.println("PreencherTabelaProdutoPalete ok");
        } catch (SQLException ex) {
            System.out.println("PreencherTabelaProdutoPalete erro " + ex);
        }
        ModeloTabela modelo = new ModeloTabela(dados, colunas);

        jTableListaProduto_Palete_Produto.setModel(modelo);
        jTableListaProduto_Palete_Produto.getColumnModel().getColumn(0).setPreferredWidth(40);
        jTableListaProduto_Palete_Produto.getColumnModel().getColumn(0).setResizable(true);
        jTableListaProduto_Palete_Produto.getColumnModel().getColumn(1).setPreferredWidth(530);
        jTableListaProduto_Palete_Produto.getColumnModel().getColumn(1).setResizable(true);
        jTableListaProduto_Palete_Produto.getColumnModel().getColumn(2).setPreferredWidth(60);
        jTableListaProduto_Palete_Produto.getColumnModel().getColumn(2).setResizable(true);
        jTableListaProduto_Palete_Produto.getColumnModel().getColumn(3).setPreferredWidth(60);
        jTableListaProduto_Palete_Produto.getColumnModel().getColumn(3).setResizable(true);
        jTableListaProduto_Palete_Produto.getColumnModel().getColumn(4).setPreferredWidth(00);
        jTableListaProduto_Palete_Produto.getColumnModel().getColumn(4).setResizable(true);
        jTableListaProduto_Palete_Produto.getTableHeader().setReorderingAllowed(false);
        jTableListaProduto_Palete_Produto.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTableListaProduto_Palete_Produto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        conexTabela.desconecta();

    }

    public void PreencherTabelaProdutoPalete_2() {
        String Sistema_mov_para_palete = "" + jTableListaProduto_Palete_Produto.getValueAt(jTableListaProduto_Palete_Produto.getSelectedRow(), 4);
        System.out.println("Interface.MovimentoCadastroJIF.PreencherTabelaProdutoPalete_2()    +Sistema_mov_para_palete " + Sistema_mov_para_palete);
//        BloqueiaCamposPalete();
        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{"Item", "Descrição", "Unid", "Qtd", ""};
        conexTabela.conexao();
        conexTabela.executaSql2(""
                + "SELECT * FROM movproduto \n"
                + " inner join produto on id_prod_ent=sis_prod \n"
                + " inner join unidade on idunid= id_unidade\n"
                + "where nota_mov='" + id_referencia + "'\n"
                + " and modo_mov='1' and stprod = 1 and sistema_mov = '" + SelecaoReferencia_Movimento + "' and stmovimento=1 or stmovimento=4  order by id_mov asc ");
        try {
            conexTabela.rs.first();
            do {
                String descricao;
                String edicao;
                String texto_movimento;
                edicao = conexTabela.rs.getString("edicao_prod");
                if (edicao.equals(null) | edicao.equals("") | edicao.equals(" ")) {
                    descricao = conexTabela.rs.getString("tipo_prod") + " " + conexTabela.rs.getString("nome_prod");
                } else {
                    descricao = conexTabela.rs.getString("tipo_prod") + " " + conexTabela.rs.getString("nome_prod") + " " + conexTabela.rs.getString("edicao_prod");
                }
                texto_movimento = "" + descricao + "  " + conexTabela.rs.getString("complemento_mov") + "  " + conexTabela.rs.getString("destino_mov");;
                dados.add(new Object[]{
                    conexTabela.rs.getInt("id_mov"), texto_movimento, conexTabela.rs.getString("sigla_unidade"),
                    conexTabela.rs.getString("qtd_prod_ex"), conexTabela.rs.getString("sistema_mov")});
            } while (conexTabela.rs.next()); ///// total_mov     usuario_mov
            System.out.println("PreencherTabelaProdutoPalete ok   77777777777 ");
        } catch (SQLException ex) {
            System.out.println("PreencherTabelaProdutoPalete erro  77777777777 " + ex);
        }
        ModeloTabela modelo = new ModeloTabela(dados, colunas);

        jTableListaProduto_Palete_Produto.setModel(modelo);
        jTableListaProduto_Palete_Produto.getColumnModel().getColumn(0).setPreferredWidth(40);
        jTableListaProduto_Palete_Produto.getColumnModel().getColumn(0).setResizable(true);
        jTableListaProduto_Palete_Produto.getColumnModel().getColumn(1).setPreferredWidth(230);
        jTableListaProduto_Palete_Produto.getColumnModel().getColumn(1).setResizable(true);
        jTableListaProduto_Palete_Produto.getColumnModel().getColumn(2).setPreferredWidth(60);
        jTableListaProduto_Palete_Produto.getColumnModel().getColumn(2).setResizable(true);
        jTableListaProduto_Palete_Produto.getColumnModel().getColumn(3).setPreferredWidth(60);
        jTableListaProduto_Palete_Produto.getColumnModel().getColumn(3).setResizable(true);
        jTableListaProduto_Palete_Produto.getColumnModel().getColumn(4).setPreferredWidth(00);
        jTableListaProduto_Palete_Produto.getColumnModel().getColumn(4).setResizable(true);
        jTableListaProduto_Palete_Produto.getTableHeader().setReorderingAllowed(false);
        jTableListaProduto_Palete_Produto.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTableListaProduto_Palete_Produto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        conexTabela.desconecta();

    }

    public void EventoUptadeMovimento() {
        conex.conexao();
        try {
            if (MinhaNatureza == "ENTRADA") {
                java.sql.PreparedStatement pst = conex.con.prepareStatement(" UPDATE movproduto SET qtd_mov=qtd_prod  WHERE sistema_mov='" + Referencia_Movimento + "'");
                pst.execute();
            } else if (MinhaNatureza == "SAIDA") {
                java.sql.PreparedStatement pst = conex.con.prepareStatement(" UPDATE movproduto   SET qtd_mov=-qtd_mov WHERE sistema_mov='" + Referencia_Movimento + "'");
                pst.execute();
            } else {
                JOptionPane.showMessageDialog(null, "ERRO movproduto");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Alterar\n" + ex);
        }
        conex.desconecta();
    }

    public void BotaoSalvarMovimento() {
        if (FlagAlterar == 1) {
            Mbeans.setStatus_mov(2);
            int refint = Integer.parseInt(Referencia_Movimento);
            Mbeans.setId_mov((refint));
            System.out.println("Referencia_Movimento    -               " + Referencia_Movimento);
            Mbeans.setIntmodotbl(0);
            Mdao.AlterarMovProduto(Mbeans);
            FlagAlterar = 0;
            System.out.println("Alterar2    OK");
        } else {
            System.out.println("Referencia_Movimento=MenuPrincipal.jLabel_Movendo.getText();" + Referencia_Movimento);
            verificador01 = verificador01 + 1;
            System.out.println("verificador01  --  " + verificador01);
        }
        Mbeans.setId_prod_ent(Id_Prod_Ent);
        Mbeans.setData_mov(Principal.jLabel_Data.getText());///// refe
        Mbeans.setNota_mov(id_referencia);
        Referencia_Movimento = Principal.jLabel_Data2.getText() + Principal.jLabel_Hora.getText() + id_referencia;
        Referencia_Movimento = Referencia_Movimento.replace("/", "").replace(":", "").replace(" ", "");
        Referencia_Movimento = Referencia_Movimento + verificador01;
        Mbeans.setSistema_mov((Referencia_Movimento));
        System.out.println("Referencia_Movimento " + Referencia_Movimento);

        String MinhaQtdMov = (jTextField_Quantidade_Nota.getText());
        MinhaQtdMov = MinhaQtdMov.replace(",", ".");
        if (MinhaNatureza == "ENTRADA") {
            Mbeans.setQtd_mov(Double.parseDouble(MinhaQtdMov));
        } else if (MinhaNatureza == "SAIDA") {
            Mbeans.setQtd_mov((Double.parseDouble("-" + MinhaQtdMov)));
        } else {
            Mbeans.setQtd_mov(Double.parseDouble(MinhaQtdMov));
        }

        Mbeans.setQtd_prod(Double.parseDouble(MinhaQtdMov));
        Mbeans.setQtd_prod_ex((jTextField_Quantidade_Nota.getText()));
        if (jLabel_Calculado.getText() == "") {
            jLabel_Calculado.setText(jTextField_Quantidade_Nota.getText());
        } else {
        }
        String MinhaQtdCalc = (jLabel_Calculado.getText());
        MinhaQtdCalc = MinhaQtdCalc.replace(",", ".");
        Mbeans.setQtd_calc(Double.parseDouble(MinhaQtdCalc));
        Mbeans.setQtd_calc_ex((jLabel_Calculado.getText()));
        Mbeans.setValor_real((ValorReal));
        Mbeans.setValor_moeda(ValorMoeda);
        Mbeans.setDestino_mov((jLabel_Setor.getText()));
        jLabel_Complemento.setText(jLabel_Complemento.getText().toUpperCase());
        Mbeans.setComplemento_mov((jLabel_Complemento.getText()));
        Mbeans.setRegistro_mov(Principal.jLabel_Data.getText() + " " + Principal.jLabel_Hora.getText());
        Mbeans.setStatus_mov(1);
        Mbeans.setVolume("VOL");
        Mbeans.setUsuario_mov(Principal.jLabelNomeUsuario.getText());
        Mbeans.setModo_mov(1);// PRIMARIO
        Mbeans.setTotal_mov(resultado_Calculo_Formatado);// TOTAL
        Mbeans.setIntmodotbl(1);
        Mdao.SalvarMovProduto(Mbeans);

    }

    public void EventoCalculaQuantidade_X_Valor() {

        try {
            double soma, num1 = 0, num2 = 0;
            num1 = Double.parseDouble(jTextField_Quantidade_Nota.getText().replace(",", "."));
            num2 = (ValorReal);
            soma = num1 * num2;
            String resultado = String.valueOf(soma);
            resultado_Calculo_Valor = (resultado);
            BigDecimal valor = new BigDecimal(resultado_Calculo_Valor);
            NumberFormat nf = NumberFormat.getCurrencyInstance();
            String formatado = nf.format(valor);
            resultado_Calculo_Formatado = (formatado); //            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "   erro:\n verifique\n " + erro);
        }
    }

    public void PreencheDadosAlterar() {
        String IdAlterar = jLabel_IdMovimento.getText();
        System.out.println("************************ " + IdAlterar);
        String descricao;
        String edicao;
        String FormatoReal = null;
        Id_Prod_Ent = Integer.parseInt(jLabel_IdProduto.getText());

        conex_Movimento.conexao();
        conex_Movimento.executaSql(""
                + "SELECT * FROM movproduto  inner join produto on id_prod_ent=sis_prod \n"
                + "inner join unidade on  idunid=id_unidade where id_mov ='" + IdAlterar + "'and stprod=1 and stmovimento=1 \n"
                + " order by id_mov asc"
                + ""
                + "");
        try {
            conex_Movimento.rs.last();
            String Fragmento_Variavel;
            int Fragmento_Variavel_Int = (conex_Movimento.rs.getInt("fragmento_unidade"));
            if (Fragmento_Variavel_Int == 1) {
                Fragmento_Variavel = "1";
            } else {
                Fragmento_Variavel = "0";
            }
            jTextField_Fragmento_Variavel.setText(Fragmento_Variavel);
            System.out.println("Fragmento_Variavel   -   " + Fragmento_Variavel);
            String Fragmento_Variavel_SaldoProduto = (conex_Movimento.rs.getString("saldo_prod"));
            jTextField_Varieavel_SaldoProduto.setText(Fragmento_Variavel_SaldoProduto);
            System.out.println("Fragmento_Variavel_SaldoProduto   -   " + Fragmento_Variavel_SaldoProduto);
            String SelecaaoSaldo = (conex_Movimento.rs.getString("saldo_prod"));
            if (SelecaaoSaldo == null) {
                System.out.println("selecao saldo null");
            } else if (SelecaaoSaldo.equals("null")) {
                System.out.println("selecao saldo null");
            } else {
                System.out.println("selecao saldo Ok");
                System.out.println("Interface.Movimento.MovimentoJIFAlterado.BuscaProdutoId()    A\n " + SelecaaoSaldo);
                SelecaaoSaldo = SelecaaoSaldo.replace(".", ".").replace(",", ".");
                System.out.println("Interface.Movimento.MovimentoJIFAlterado.BuscaProdutoId()   B \n " + SelecaaoSaldo);
                Double num4 = Double.parseDouble(String.valueOf(SelecaaoSaldo));
                System.out.println("Interface.Movimento.MovimentoJIFAlterado.BuscaProdutoId()    C\n " + num4);
                BigDecimal df = new BigDecimal(num4);
                NumberFormat nf = NumberFormat.getInstance();
                nf.setMaximumFractionDigits(4);
                FormatoReal = nf.format(df);
            }

            System.out.println("Interface.Movimento.MovimentoJIFAlterado.BuscaProdutoId()    D\n " + FormatoReal);
            String SelecaaoUnid = (conex_Movimento.rs.getString("sigla_unidade"));
            jLabelMeuSaldoProduto.setText(FormatoReal + " / " + SelecaaoUnid);
            ValorMoeda = (conex_Movimento.rs.getString("valor_prod_ex"));
            ValorReal = (Double.parseDouble(conex_Movimento.rs.getString("valor_prod")));
            edicao = conex_Movimento.rs.getString("edicao_prod");
            if (edicao.equals(null) | edicao.equals("") | edicao.equals(" ")) {
                edicao = "";
            } else {
                edicao = " N° " + edicao;
            }
            descricao = " " + conex_Movimento.rs.getString("tipo_prod") + " " + conex_Movimento.rs.getString("nome_prod") + edicao;
            descricao = descricao.toUpperCase();
            jTextField_Busca_Produto_Nota.setText(descricao);
            Id_Prod_Ent = (Integer.parseInt(conex_Movimento.rs.getString("sis_prod")));
            jLabel_IdProduto.setText(String.valueOf(Id_Prod_Ent));
            jTextField_Quantidade_Nota.setText(conex_Movimento.rs.getString("qtd_prod_ex"));
            jLabel_Calculado.setText(conex_Movimento.rs.getString("qtd_calc_ex"));
            jLabel_IdProduto.setText(conex_Movimento.rs.getString("id_prod_ent"));
            jTextField_Busca_Produto_Nota.setText(descricao);
            jTextField_Busca_Produto_Nota.setBackground(Color.yellow);
            jTextField_Quantidade_Nota.setBackground(Color.yellow);
            jLabel_Complemento.setText(conex_Movimento.rs.getString("complemento_mov"));
            jLabel_Setor.setText(conex_Movimento.rs.getString("destino_mov"));
            System.out.print("............................   " + descricao + "   " + jLabel_Complemento.getText() + "   " + jLabel_Setor.getText());

        } catch (SQLException ex) {
            System.out.println("BuscaProdutoId() " + ex);
            jLabelMeuSaldoProduto.setText("");
            Id_Prod_Ent = (0);
            jLabel_IdProduto.setText("0");
            System.out.println("BuscaProdutoId() " + ex);
//                BP.recebe_nome(jTextField_Busca_Produto_Nota.getText());
//                BP.setVisible(true);

        }

        conex_Movimento.desconecta();

//        String descricao;
//        String edicao; String FormatoReal = null; String Fragmento_Variavel;
//        String IdAlterar = jLabel_IdMovimento.getText();
//        System.out.println("************************ "+IdAlterar);
//        conex_Movimento.conexao();
//        conex_Movimento.executaSql("SELECT * FROM movproduto  inner join produto on id_prod_ent=sis_prod \n"
//                + "inner join unidade on  idunid=id_unidade where id_mov ='" + IdAlterar + "'and stprod=1 and stmovimento=1 "
//                        + " order by id_mov asc"); /// and status_prod='ATIVO' and status_mov='ATIVO'
//a
//        try {
//            conex_Movimento.rs.first();
//            edicao = conex_Movimento.rs.getString("edicao_prod");
//            if (edicao.equals(null) | edicao.equals("") | edicao.equals(" ")) {
//                edicao = "";
//            } else {
//                edicao = " N° " + edicao;
//            }
//           
////            int Fragmento_Variavel_Int = (conex.rs.getInt("fragmento_unidade"));
////            if (Fragmento_Variavel_Int == 1) {
////                Fragmento_Variavel = "1";
////            } else {
////                Fragmento_Variavel = "0";
////            }
////            jTextField_Fragmento_Variavel.setText(Fragmento_Variavel);
//            descricao = " " + conex_Movimento.rs.getString("tipo_prod") + " " + conex_Movimento.rs.getString("nome_prod") + edicao;
//            descricao = descricao.toUpperCase();
//            jTextField_Quantidade_Nota.setText(conex_Movimento.rs.getString("qtd_prod_ex"));
//            jLabel_Calculado.setText(conex_Movimento.rs.getString("qtd_calc_ex"));
//            jLabel_IdProduto.setText(conex_Movimento.rs.getString("id_prod_ent"));
//            jTextField_Busca_Produto_Nota.setText(descricao);
//            jTextField_Busca_Produto_Nota.setBackground(Color.yellow);
//            jTextField_Quantidade_Nota.setBackground(Color.yellow);
//            jLabel_Complemento.setText(conex_Movimento.rs.getString("complemento_mov"));
//            jLabel_Setor.setText(conex_Movimento.rs.getString("destino_mov"));
//            System.out.print("............................   " + descricao + "   " + jLabel_Complemento.getText() + "   " + jLabel_Setor.getText());
//       
////             System.out.println("Fragmento_Variavel_SaldoProduto   -   " + Fragmento_Variavel_SaldoProduto);
//                String SelecaaoSaldo = (conex.rs.getString("saldo_prod"));
//                if (SelecaaoSaldo == null) {
//                    System.out.println("selecao saldo null");
//                } else if (SelecaaoSaldo.equals("null")) {
//                    System.out.println("selecao saldo null");
//                } else {
//                    System.out.println("selecao saldo Ok");
//                    System.out.println("Interface.Movimento.MovimentoJIFAlterado.BuscaProdutoId()    A\n " + SelecaaoSaldo);
//                    SelecaaoSaldo = SelecaaoSaldo.replace(".", ".").replace(",", ".");
//                    System.out.println("Interface.Movimento.MovimentoJIFAlterado.BuscaProdutoId()   B \n " + SelecaaoSaldo);
//                    Double num4 = Double.parseDouble(String.valueOf(SelecaaoSaldo));
//                    System.out.println("Interface.Movimento.MovimentoJIFAlterado.BuscaProdutoId()    C\n " + num4);
//                    BigDecimal df = new BigDecimal(num4);
//                    NumberFormat nf = NumberFormat.getInstance();
//                    nf.setMaximumFractionDigits(4);
//                    FormatoReal = nf.format(df);
//                }
//
//                System.out.println("Interface.Movimento.MovimentoJIFAlterado.BuscaProdutoId()    D\n " + FormatoReal);
//                String SelecaaoUnid = (conex.rs.getString("sigla_unidade"));
//                jLabelMeuSaldoProduto.setText(FormatoReal + " / " + SelecaaoUnid);
//        
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(rootPane,"Alterar"+ ex);
//            jTextField_Busca_Produto_Nota.setBackground(Color.red);
//        }
//        conex_Movimento.desconecta();
    }

    public void PreencheTabelaPalete() {
        jbALis.setEnabled(false);
        jbALin.setEnabled(false);
        System.out.println("PreencheTabelaPalete() Referencia_Movimento : " + SelecaoReferencia_Movimento);
        conex.conexao();
        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{"Linha", "Pct Padr.", "Lastro", "Altura", "Pct Avu", "Qtd Pct", "Un. Avu.", "Total"};
// "No",
        conex.executaSql2("select row_number() OVER (PARTITION by 0) as contador,pctpad ,  lastro ,  altura ,  pctavu ,  totpct ,  unavu ,  totparc , \n"
                + " mov_palete ,  qtd_palete ,id_palete, idlinha from paletetemp\n"
                + " where mov_palete='" + SelecaoReferencia_Movimento + "'  and  stpalete=1 order by idlinha asc");//// and verifica_temp is null
        try {
            conex.rs.first();
            do {
                String pacote = (conex.rs.getString("pctpad"));
                String lastro = (conex.rs.getString("lastro"));
                String altura = (conex.rs.getString("altura"));
                String avulso = (conex.rs.getString("pctavu"));
                String totalpacote = (conex.rs.getString("totpct"));
                String uniavulso = (conex.rs.getString("unavu"));
                String totalfinal = (conex.rs.getString("totparc"));

                pacote = pacote.replace(".0", "").replace(".", ",");
                lastro = lastro.replace(".0", "").replace(".", ",");
                altura = altura.replace(".0", "").replace(".", ",");
                avulso = avulso.replace(".0", "").replace(".", ",");
                totalpacote = totalpacote.replace(".0", "").replace(".", ",");
                uniavulso = uniavulso.replace(".0", "").replace(".", ",");
                totalfinal = totalfinal.replace(".0", "").replace(".", ",");
                dados.add(new Object[]{conex.rs.getInt("idlinha"), pacote, lastro,
                    altura, avulso, totalpacote,
                    uniavulso, totalfinal});

            } while (conex.rs.next());
        } catch (SQLException ex) {
            System.err.println("Erro na tabela palete  444444 : \n" + ex);
        }
        ModeloTabela modelo = new ModeloTabela(dados, colunas);

        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        direita.setHorizontalAlignment(SwingConstants.RIGHT);

        jTablePalete.setModel(modelo);

        jTablePalete.getColumnModel().getColumn(0).setPreferredWidth(60);
        jTablePalete.getColumnModel().getColumn(0).setResizable(true);
//        jTablePalllet.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        jTablePalete.getColumnModel().getColumn(1).setPreferredWidth(60);
        jTablePalete.getColumnModel().getColumn(1).setResizable(true);
//        jTablePalete.getColumnModel().getColumn(1).setCellRenderer(centralizado);
        jTablePalete.getColumnModel().getColumn(2).setPreferredWidth(60);
        jTablePalete.getColumnModel().getColumn(2).setResizable(true);
        jTablePalete.getColumnModel().getColumn(3).setPreferredWidth(60);
        jTablePalete.getColumnModel().getColumn(3).setResizable(true);
        jTablePalete.getColumnModel().getColumn(4).setPreferredWidth(60);
        jTablePalete.getColumnModel().getColumn(4).setResizable(true);
        jTablePalete.getColumnModel().getColumn(5).setPreferredWidth(60);
        jTablePalete.getColumnModel().getColumn(5).setResizable(true);
        jTablePalete.getColumnModel().getColumn(6).setPreferredWidth(60);
        jTablePalete.getColumnModel().getColumn(6).setResizable(true);
        jTablePalete.getColumnModel().getColumn(7).setPreferredWidth(80);
        jTablePalete.getColumnModel().getColumn(7).setResizable(true);

        jTablePalete.getTableHeader().setReorderingAllowed(false);
        jTablePalete.setAutoResizeMode(jTablePalete.AUTO_RESIZE_OFF);
        jTablePalete.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        conex.desconecta();
    }

    public void SomaTotalPalete() {

        conex.conexao();
        conex.executaSql2("SELECT sum(cast( cast(replace(totparc,',','.' )as text) as float) )AS totparc,paletetemp.mov_palete FROM paletetemp "
                + "where mov_palete='" + SelecaoReferencia_Movimento + "'and stpalete='1'"
                + " GROUP BY  paletetemp.mov_palete "
                + "  order by mov_palete asc");//// and verifica_temp is null
        try {
            conex.rs.first();
            CalcTotalFinal = (conex.rs.getString("totparc"));
            Double soma9 = (conex.rs.getDouble("totparc"));
            System.err.println("Calculado : " + CalcTotalFinal);
            CalcTotalFinal = ((String.format("%.4f", soma9)));
            CalcTotalFinal = CalcTotalFinal.replace(",0000", "");
            System.err.println("ok  SomaTotalPalete totparc : " + SelecaoReferencia_Movimento);
            System.err.println(" ** Calculado : " + CalcTotalFinal + " **");
            jLabel_Informacoes.setText("  Total palete = "+CalcTotalFinal);
        } catch (SQLException ex) {
            System.err.println("Erro SomaTotalPalete totparc : " + SelecaoReferencia_Movimento + "   -   /n" + ex);
        }
        conex.desconecta();
    }

    public void SomaLinhaPaleteExibir() {
        double soma, soma2, num1 = 0, num2 = 0, num3 = 0, pac = 0, pacavu = 0, avu = 0;
        try {
            if (jtPctPad.getText().isEmpty()) {
                jtPctPad.requestFocus();
            }
            if (jtLast.getText().isEmpty()) {
                jtLast.setText("0");
            }
            if (jtAlt.getText().isEmpty()) {
                jtAlt.setText("0");
            }
            if (jtPctAvu.getText().isEmpty()) {
                jtPctAvu.setText("0");
            }
            if (jtUnAvu.getText().isEmpty()) {
                jtUnAvu.setText("0");
            } else {
            }
            num1 = Double.parseDouble(jtAlt.getText().replace(",", "."));
            num2 = Double.parseDouble(jtLast.getText().replace(",", "."));
            num3 = Double.parseDouble(jtPctAvu.getText().replace(",", "."));
            avu = Double.parseDouble(jtUnAvu.getText().replace(",", "."));
            pacavu = Double.parseDouble(jtPctAvu.getText().replace(",", "."));
            pac = Double.parseDouble(jtPctPad.getText().replace(",", "."));
            soma = num1 * num2 + num3;
            String resultado = Double.toString(soma);
            jtTotPct.setText((String.format("%.4f", soma)));
            jtTotPct.setText(jtTotPct.getText().replace(",0000", ""));
            soma2 = soma * pac + avu;
            jtTotParc.setText((String.format("%.4f", soma2)));
            jtTotParc.setText(jtTotParc.getText().replace(",0000", ""));
            System.err.println("soma: " + soma);
            System.err.println("soma2: " + soma2);
            double valor_decimal = ((soma2 * 100));
            System.err.println("soma3: " + valor_decimal);
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro verifique\n: " + erro);
        }
    }

    public void SalvaPalete() {
        beansp.setUsuario(menu.jLabelNomeUsuario.getText());
        beansp.setRegistro(menu.jLabel_Data.getText() + " - " + menu.jLabel_Hora.getText());
        beansp.setPctpad(jtPctPad.getText());
        beansp.setLastro(jtLast.getText());
        beansp.setAltura(jtAlt.getText());
        beansp.setPctavu(jtPctAvu.getText());
        beansp.setUnavu(jtUnAvu.getText());
        beansp.setTotpct(jtTotPct.getText());
        beansp.setTotparc(jtTotParc.getText());
        beansp.setMov_palete(SelecaoReferencia_Movimento);
        beansp.setNota_palete(String.valueOf(id_referencia));
        if (FlagPalete == 1) {
            beansp.setVerifica_principal(2);
            beansp.setStpalete(1);
            beansp.setIdlinha(Integer.parseInt(Id_Linha));
            daop.AlterarTemp(beansp);
            FlagPalete = 0;
        } else {
            Principal.jButton3.doClick();
            beansp.setId_palete(Principal.jLabel_Gerador_De_Codigo.getText());
            beansp.setVerifica_principal(1);
            beansp.setStpalete(1);
            daop.SalvarTemp(beansp);
        }

        System.err.println("SelecaoReferencia_Movimento : " + SelecaoReferencia_Movimento);
    }

    public void DesbloqueiaCamposPalete() {
        jtAlt.setEnabled(!false);
        jtLast.setEnabled(!false);
        jtPctAvu.setEnabled(!false);
        jtUnAvu.setEnabled(!false);
        jtTotPct.setEnabled(false);
        jtTotParc.setEnabled(false);
        jtPctPad.setEnabled(!false);
        FlagPalete = 0;
        jbALis.setEnabled(false);
        jbALin.setEnabled(false);
        jbAdicionar.setEnabled(!false);
        jbLimpar.setEnabled(!false);
        jButton_Bandeira1.setEnabled(!false);
        jTablePalete.setEnabled(true);
    }

    public void BloqueiaCamposPalete() {
        jtAlt.setEnabled(false);
        jtLast.setEnabled(false);
        jtPctAvu.setEnabled(false);
        jtUnAvu.setEnabled(false);
        jtTotPct.setEnabled(false);
        jtTotParc.setEnabled(false);
        jtPctPad.setEnabled(false);
        FlagPalete = 0;
        jbALis.setEnabled(false);
        jbALin.setEnabled(false);
        jbAdicionar.setEnabled(false);
        jbLimpar.setEnabled(false);
        jButton_Bandeira1.setEnabled(false);
    }

    public void limpaCamposPalete() {
        jtAlt.setText("");
        jtLast.setText("");
        jtPctAvu.setText("");
        jtUnAvu.setText("");
        jtTotPct.setText("");
        jtTotParc.setText("");
        jtPctPad.setText("");
        jtPctPad.requestFocus();
        FlagPalete = 0;
        jbALis.setEnabled(false);
        jbALin.setEnabled(false);
    }

    public void EventoFechaMovimento() {
        jLabelMeuSaldoProduto.setText("");
        MovimentoBancoTemporarioApagar();
        LimpaTransporte();
        BloqueiaCamposPalete();
        jTablePalete.setEnabled(false);
        LimparTabela();
        AtualizaListaMovimentos();
        jComboBox_Situacao.setSelectedItem("1-CALCULADO");
        Principal.jLabelCodigoTela.setText("FechaMovimentoCadastro");
        Principal.jButton1.doClick();
        jTextArea_Observacao.setText("");
        Principal.jLabelCodigoTela.setText("AtualizaTudo");
        Principal.jButton1.doClick();
    }

    public void AtualizaListaMovimentos() {
        try {
            System.out.println("Interface.Movimento.MovimentoJIFAlterado.AtualizaListaMovimentos()     4\n");
        } catch (Error err) {
            System.out.println("Interface.Movimento.MovimentoJIFAlterado.AtualizaListaMovimentos()     4\n" + err);
        }

        try {
        } catch (Error err) {
            System.out.println("Interface.Movimento.MovimentoJIFAlterado.AtualizaListaMovimentos()    1\n" + err);
        }
        try {
//            JIF_Consultas.jTextField_Buscar.requestFocus();
        } catch (Error err) {
            System.out.println("Interface.Movimento.MovimentoJIFAlterado.AtualizaListaMovimentos()     2\n" + err);
        }
        try {
//            ListaMovimentoJIF.jTextField_Busca.requestFocus();
        } catch (Error err) {
            System.out.println("Interface.Movimento.MovimentoJIFAlterado.AtualizaListaMovimentos()     3\n" + err);
        }

    }

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

        AtualizarTudo();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ComboFocu = 1;
        ComboFocuFornecedor = 1;
        ComboFocuNatureza = 1;
        String selecaoN = (String) jComboBox_Natureza.getSelectedItem();
        if (selecaoN == null) {
        } else {
            if (ComboFocuNatureza == 0) {
            } else {
                jComboBox_Natureza_Int.setSelectedIndex(jComboBox_Natureza.getSelectedIndex());
                jComboBox_Natureza_Tipo.setSelectedIndex(jComboBox_Natureza.getSelectedIndex());
                System.out.println("==========  String " + jComboBox_Natureza.getSelectedItem());
                System.out.println("==========  Int " + jComboBox_Natureza.getSelectedItem());
                System.out.println("==========  Int " + jComboBox_Natureza_Int.getSelectedIndex());
                MinhaNatureza = "" + jComboBox_Natureza_Tipo.getSelectedItem();
                this.setTitle("Cadastro Movimento [ " + MinhaNatureza + " ] Id Referencia : " + id_referencia);
            }
        }

        String selecaoF = (String) jComboBox_Fornecedor.getSelectedItem();
        if (selecaoF == null) {
        } else {
            if (ComboFocuFornecedor == 0) {
            } else {
                jComboBox_Fornecedor_Int.setSelectedIndex(jComboBox_Fornecedor.getSelectedIndex());
                System.out.println("==========  String " + jComboBox_Fornecedor.getSelectedItem());
                System.out.println("==========  Int " + jComboBox_Fornecedor.getSelectedItem());
                System.out.println("==========  Int " + jComboBox_Fornecedor_Int.getSelectedIndex());
            }
        }

        String selecaoT = (String) jComboBox_transportadora.getSelectedItem();
        if (selecaoT == null) {
            jLabel_exibe_transporte.setText(null);
            vol_transportadora.setText("1");
//             jComboBox_transportadoraInt.setSelectedIndex(1);
        } else {
            if (ComboFocu == 0) {
            } else {
                jComboBox_transportadoraInt.setSelectedIndex(jComboBox_transportadora.getSelectedIndex());
                System.out.println("==========  String " + jComboBox_transportadora.getSelectedItem());
                System.out.println("==========  Int " + jComboBox_transportadoraInt.getSelectedItem());
                System.out.println("==========  Int " + jComboBox_transportadoraInt.getSelectedIndex());
                //            exibe_transporte();//preenche_Transporte();
            }
        }

        if (jComboBox_Natureza.getSelectedItem() == (null) | jComboBox_Situacao.getSelectedItem() == (null) | jComboBox_Tipo_Fornecedor.getSelectedItem() == (null) | jComboBox_modalidade.getSelectedItem() == (null) | jComboBox_transportadora.getSelectedItem() == (null)) {
            JOptionPane.showMessageDialog(rootPane, "Campos vazios verifique");
        } else if (jComboBox_Natureza.getSelectedItem() == " " | jComboBox_Natureza.getSelectedItem().equals(" ")) {
            JOptionPane.showMessageDialog(rootPane, "Verifique");
        } else if (jComboBox_Situacao.getSelectedItem() == " " | jComboBox_Situacao.getSelectedItem().equals(" ")) {
            JOptionPane.showMessageDialog(rootPane, "Verifique");
        } else if (MinhaNatureza == null | MinhaNatureza.equals(null)) {
            JOptionPane.showMessageDialog(rootPane, "Campos vazios verifique 000000");
        } else {
            BotaoSalvar();
            LimpaSistema2();
            MovimentoBancoTemporarioApagar();
            LimpaTransporte();
            MovimentoBancoTemporarioApagar();
            ExecutaSaldo();
            EventoFechaMovimento();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public void MovimentoBancoTemporarioAlterado() {

    }

    public void ExecutaSaldo() {
        String MeuExecutaSaldo = String.valueOf(id_referencia);
        System.out.println("aquiExecutaSaldo()        Verificando            " + MeuExecutaSaldo);
        conexSaldo.conexao();
        try {
            java.sql.PreparedStatement pst = conexSaldo.con.prepareStatement("update produto as prod set \n"
                    + "saldo_prod=( select  sum(qtd_mov) as qtd_prod  from movprodutobase as base\n"
                    + " where id_prod_ent=sis_prod and stmovimento=1 and stsaldo=1 \n"
                    + " GROUP BY   prod.sis_prod order by sis_prod asc )\n"
                    + " from movprodutobase as base\n"
                    + "where prod.sis_prod=base.id_prod_ent and nota_mov='" + MeuExecutaSaldo + "' and base.stsaldo=1");
            pst.execute();
            System.out.println("aquiExecutaSaldo() ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ExecutaSaldo -- \n" + ex);
        }
        conexSaldo.desconecta();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (MinhaIdNota == "Novo") {
            LimpaSistema();
        } else {
            CarregaFormulario();
            LimpaCampoProduto();
            FlagAlterar = 0;
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int resposta = 0;
        resposta = JOptionPane.showConfirmDialog(rootPane, "Deseja realmente excluir/ativar ? ");
        if (resposta == JOptionPane.YES_OPTION) {
            Mbeans.setNota_mov(id_referencia);
            Mbeans.setRegistro_mov(Principal.jLabel_Data.getText() + " " + Principal.jLabel_Hora.getText());
            Mbeans.setStatus_mov(4);
            Mbeans.setUsuario_mov(Principal.jLabelNomeUsuario.getText());
            Mdao.ExcluirMovProdutoBase(Mbeans);
            MovimentoBancoTemporarioApagar();
            LimpaTransporte();
            BotaoExcluir();
            ExecutaSaldo();
            EventoFechaMovimento();
            this.dispose();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        PreencherTabela();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        int resposta = 0;
        resposta = JOptionPane.showConfirmDialog(rootPane, "Deseja realmente fazer uma cópia ? ");
        if (resposta == JOptionPane.YES_OPTION) {
            MinhaCopia = "executando";
            ExecutaCopia();
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    public void ExecutaCopiaUpdate() {
        if (MinhaCopia.equals(null) | MinhaCopia.equals("") | MinhaCopia.equals("null")) {
            System.out.println(".............................aqui MovimentoBancoTemporarioAlterar sistema_mov null");
        } else {
            conex.conexao();
            conex.executaSql(""
                    + "SELECT * FROM movproduto  where nota_mov='" + id_referencia + "'"
                    + "and status_mov='ATIVO'   order by id_mov asc "
                    + "");
        }
        try {
            conex.rs.first();
            int numero = 1;
            do {
                numero = numero + 1;
            } while (conex.rs.next());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "erro\nExecutaCopiaUpdate\n" + ex);
            System.out.println("erro\nExecutaCopiaUpdate\n" + ex);
        }
        conex.desconecta();
    }

    public void VerificaSeTemPalete() {
        conex2.conexao();
        conex2.executaSql("SELECT * FROM palete WHERE mov_palete='" + MinhaIdSistema + "'  ");
        System.err.println("mov_palete            MinhaIdSistema"
                + "11"
                + ""
                + "=  " + MinhaIdSistema);
        try {
            do {
                conex2.rs.first();
                String MeuMovPalete = (conex2.rs.getString("mov_palete"));

                java.sql.PreparedStatement pst = conex2.con.prepareStatement(""
                        + "insert into palete(  pctpad, lastro, altura, pctavu, totpct, unavu, totparc, \n"
                        + "       mov_palete, qtd_palete, nota_palete,"
                        + "verifica_principal, status_palete, usuario_palete, registro_palete\n"
                        + "          )"
                        + "SELECT  pctpad, lastro, altura, pctavu, totpct, unavu, totparc, \n"
                        + "    '" + MeuMovPaleteReferencia + "',  qtd_palete, '" + id_referencia + "'"
                        + ", '" + id_referencia + "' ,'ATIVO', usuario_palete, registro_palete      \n"
                        + "  FROM palete where mov_palete='" + MeuMovPalete + "'  and status_palete='ATIVO' "
                        + "order by idlinha asc ");
                pst.execute();
                System.out.println("MeuMovPalete " + MeuMovPalete);
            } while (conex.rs.next());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "erro\nVerificaSeTemPalete\n" + ex);
        }

        conex2.desconecta();
    }

    public void ExecutaCopia() {
        id_referenciUltimo = id_referencia;
//        MinhsaIdNota = "Novo";
        CarregaUltimo();
        MinhaIdNota = "Novo";
        System.out.println("##### MinhaIdNota = '" + id_referenciUltimo + "'  [ Novo] '" + id_referencia + "'  ");
        MovimentoBancoTemporarioAlterado();
        ExecutaCopiaUpdate();
        VerificaEFazUpdatePalete();
        this.setTitle("Cadastro Movimento [ " + MinhaNatureza + " ] Id Referencia : " + id_referencia);
        PreencherTabela();
        jComboBox_Situacao.setSelectedItem("1-CALCULADO");
    }

    public void VerificaEFazUpdatePalete() {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement("UPDATE palete\n"
                    + "   SET \n"
                    + "       verifica_principal=cast (nota_palete as integer)");
            pst.execute();
            System.out.println("UPDATE palete 2   OK");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "UPDATE palete 2   ERRO" + ex);
            System.out.println("UPDATE palete     2        ERRO" + ex);
        }
        conex.desconecta();
    }

    public void EventoSalvaPalete() {
        if (SelecaoReferencia_Movimento == null) {
        } else if (jtPctPad.getText().isEmpty()) {
        } else {
            SomaLinhaPaleteExibir();
            SalvaPalete();
            PreencheTabelaPalete();
            SomaTotalPalete();
            System.out.println("Normal ate aqui");
            ContaTotalLinhas();
            System.out.println("Normal ate aqui    tudo ok");
            if (jtPctPad.getText().isEmpty()) {
                jtPctPad.requestFocus();
            } else {
                jtLast.requestFocus();
            }
        }
    }

    public void ContaTotalLinhas() {
        System.out.println("UContaTotalLinhas  SelecaoReferencia_Movimento   - " + SelecaoReferencia_Movimento);
        if (SelecaoReferencia_Movimento.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "linha vazia");
        } else {
            String busca = SelecaoReferencia_Movimento;
            conex.conexao();
            conex.executaSql2(" select count(mov_palete) as linha,mov_palete  FROM paletetemp where stpalete=1 and mov_palete='" + busca + "' "
                    + " GROUP BY paletetemp.mov_palete"
                    + "");
            try {
                conex.rs.first();
                ContadorLinha = String.valueOf(conex.rs.getInt("linha"));

                try {
                    java.sql.PreparedStatement pst = conex.con.prepareStatement(""
                            + "UPDATE paletetemp   SET  qtd_palete= '" + ContadorLinha + "' where mov_palete='" + busca + "' \n"
                            + "");
                    pst.execute();
                    System.out.println("UPDATE palete contador de linhas  OK  " + ContadorLinha);
                } catch (SQLException ex) {
                    System.out.println("UPDATE palete       contador de linhas      ERRO" + ex);
                }
                try {
                    java.sql.PreparedStatement pst = conex.con.prepareStatement(""
                            + "UPDATE palete   SET  qtd_palete= '" + ContadorLinha + "' where mov_palete='" + busca + "' \n"
                            + "");
                    pst.execute();
                    System.out.println("UPDATE palete contador de linhas  OK  " + ContadorLinha);
                } catch (SQLException ex) {
                    System.out.println("UPDATE palete       contador de linhas      ERRO" + ex);
                }
            } catch (SQLException ex) {
                System.out.println("UPDATE palete       contador de linhas      ERRO" + ex);
                ContadorLinha = "0";
            }
            conex.desconecta();
        }
    }

    public void EnviaDados(String A, String B, String C) {
    }

    private void jTextField_HoraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_HoraKeyPressed

        if (jTextField_Hora.getText().length() >= 5) {

        }

    }//GEN-LAST:event_jTextField_HoraKeyPressed

    private void jTextField_HoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_HoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_HoraActionPerformed

    private void jTextField_HoraFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_HoraFocusLost
        if (jTextField_Hora.getText().isEmpty()) {
            jTextField_Hora.setText("  :  ");
        } else if (jTextField_Hora.getText().equals("  :  ")) {
            jTextField_Hora.setText("  :  ");
        } else {
            AjustaMinhaHora();
        }
    }//GEN-LAST:event_jTextField_HoraFocusLost

    public void AjustaMinhaHora() {
        String s = jTextField_Hora.getText().replace(":", "");
        if (s.matches("^[a-zA-ZÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü]*$")) {
            System.out.println("Interface.Movimento.MovimentoJIFAlterado.AjustaMinhaHora()      ERRRROOOOOOOO");
            jTextField_Hora.setBackground(Color.red);
        } else if (s.matches("^[0-9]*$")) {
            if (s.length() > 3) {
                if (s.length() == 4) {
                    String formatado = s.substring(0, 2) + ":" + s.substring(2, 4);
                    System.err.println("    ddddd   " + formatado); ////   (11)4168-3085
                    jTextField_Hora.setText(formatado);
                } else if (s.length() == 6) {
                    String formatado = s.substring(0, 2) + ":" + s.substring(2, 4) + ":" + s.substring(4, 6);
                    System.err.println("    ddddd   " + formatado); ////   (11)4168-3085
                    jTextField_Hora.setText(formatado);
                } else if (s.length() < 6) {
                } else if (s.length() > 7 | s.length() == 3 | s.length() == 5) {
                    System.err.println("    eeee   erro");
                    jTextField_Hora.setText("");
                    jTextField_Hora.requestFocus();
                    jTextField_Hora.setBackground(Color.red);
                } else {
                    System.err.println("    ddddd   erro");
                    jTextField_Hora.setText("");
                    jTextField_Hora.requestFocus();
                }
                jTextField_Hora.setBackground(Color.white);
            }
        }
    }

    private void jButton_Limpa_RelogioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton_Limpa_RelogioKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_Limpa_RelogioKeyPressed

    private void jButton_Limpa_RelogioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Limpa_RelogioActionPerformed
        if (jTextField_Hora.getText().isEmpty()) {
            jTextField_Hora.setText(Principal.jLabel_Hora.getText());
            String Relogio = (Principal.jLabel_Hora.getText());
//            String MenuMinhaHoraSistema = (String.format("%1$tM:%1$tS", Relogio)); /// %1$tM:%1$tS
            jTextField_Hora.setText(Relogio);
        } else {
            jTextField_Hora.setText(null);
        }
    }//GEN-LAST:event_jButton_Limpa_RelogioActionPerformed

    private void jButton_AtualizarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton_AtualizarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_AtualizarKeyPressed

    private void jButton_AtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AtualizarActionPerformed
        if (jDateChooser_Data_Nota.getDate() == (null)) {
            try {
                data = formato.parse(Principal.jLabel_Data.getText());
                jDateChooser_Data_Nota.setDate(data);
            } catch (ParseException ex) {
                //                Logger.getLogger(MovimentoJIF.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            jDateChooser_Data_Nota.setDate(null);
        }
        ManipulaData();
    }//GEN-LAST:event_jButton_AtualizarActionPerformed

    private void jComboBox_FornecedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox_FornecedorKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            jTextField_Hora.requestFocus();
        }
        if (evt.getKeyCode() == evt.VK_F5) {
            PreencherFornecedor();
        }
    }//GEN-LAST:event_jComboBox_FornecedorKeyPressed

    private void jComboBox_FornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_FornecedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_FornecedorActionPerformed

    private void jTextField_Chave_NotaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_Chave_NotaKeyPressed
        if (evt.getKeyCode() == evt.VK_F2) {
            System.out.println("Interface.Movimento.MovimentoJIFAlterado.jTextField_Chave_NotaKeyPressed()\n F12");

        }
        if (evt.getKeyCode() == evt.VK_ENTER) {
        }
        if (evt.getKeyCode() == evt.VK_F3) {

        }
        if (evt.getKeyCode() == evt.VK_F12) {
            JDialogAuxilioChave conf = new JDialogAuxilioChave(menu, rootPaneCheckingEnabled);
            conf.RecebeFornecedor("" + jComboBox_Fornecedor.getSelectedItem());
            if (conf == null) {
                // TelaSalProd = new ModSaldoProd();
                conf.setVisible(true);
                conf.setResizable(false);

            } else {
                conf.setVisible(true);
                conf.setResizable(false);
            }
        }
    }//GEN-LAST:event_jTextField_Chave_NotaKeyPressed

    private void jTextField_Chave_NotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Chave_NotaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Chave_NotaActionPerformed

    private void jTextField_Chave_NotaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_Chave_NotaFocusLost
        if (jTextField_Chave_Nota.getText().isEmpty()) {
        } else {
        }
    }//GEN-LAST:event_jTextField_Chave_NotaFocusLost

    private void jTextField_NotaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_NotaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_NotaKeyReleased

    private void jTextField_NotaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_NotaKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            if (jTextField_Chave_Nota.isEnabled()) {
                jTextField_Chave_Nota.requestFocus();
            } else {
            }
        }
    }//GEN-LAST:event_jTextField_NotaKeyPressed

    private void jTextField_NotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_NotaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_NotaActionPerformed

    private void jTextField_NotaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_NotaFocusLost
        String nomeNota = jTextField_Nota.getText();
        if (jComboBox_Documento.getSelectedItem() == "Nota") {
            if (jTextField_Nota.getText().isEmpty()) {

            } else {
            }
        } else if (jTextField_Nota.getText().isEmpty()) {
        } else if (jComboBox_Documento.getSelectedItem().equals("Protocolo")) {
            jTextField_Chave_Nota.setText("Protocolo N°:" + jTextField_Nota.getText());
        } else {
        }
    }//GEN-LAST:event_jTextField_NotaFocusLost

    private void jComboBox_DocumentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox_DocumentoKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            jTextField_Nota.requestFocus();
        }
    }//GEN-LAST:event_jComboBox_DocumentoKeyPressed

    private void jComboBox_DocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_DocumentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_DocumentoActionPerformed

    private void jComboBox_DocumentoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox_DocumentoFocusLost
        //
    }//GEN-LAST:event_jComboBox_DocumentoFocusLost

    private void jComboBox_DocumentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_DocumentoItemStateChanged

        String nome = "Não definido";
        String nomeNota = jTextField_Nota.getText();
        if (jComboBox_Documento.getSelectedItem() == (null)) {
        } else if (jComboBox_Documento.getSelectedItem().equals("Não definido")) {
            jTextField_Nota.setText(nome);
            jTextField_Chave_Nota.setText(nome);
            jTextField_Nota.setEnabled(false);
            jTextField_Chave_Nota.setEnabled(false);
        } else if (jComboBox_Documento.getSelectedItem().equals("Protocolo")) {
            jTextField_Nota.setEnabled(true);
            jTextField_Chave_Nota.setEnabled(false);
            jTextField_Nota.requestFocus();
        } else if (jComboBox_Documento.getSelectedItem().equals("Nota")) {
            jTextField_Nota.setEnabled(true);
            jTextField_Chave_Nota.setEnabled(true);
            jTextField_Nota.requestFocus();
        } else {
            jTextField_Nota.setEnabled(true);
            jTextField_Chave_Nota.setEnabled(false);
            jTextField_Nota.requestFocus();
        }
    }//GEN-LAST:event_jComboBox_DocumentoItemStateChanged

    private void jDateChooser_Data_NotaVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_jDateChooser_Data_NotaVetoableChange

    }//GEN-LAST:event_jDateChooser_Data_NotaVetoableChange

    private void jDateChooser_Data_NotaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateChooser_Data_NotaKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            jComboBox_Tipo_Fornecedor.requestFocus();
        }
    }//GEN-LAST:event_jDateChooser_Data_NotaKeyPressed

    private void jDateChooser_Data_NotaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser_Data_NotaPropertyChange
        ManipulaData();
    }//GEN-LAST:event_jDateChooser_Data_NotaPropertyChange

    private void jDateChooser_Data_NotaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser_Data_NotaMouseClicked
        ManipulaData();        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser_Data_NotaMouseClicked

    private void jDateChooser_Data_NotaAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jDateChooser_Data_NotaAncestorAdded
    }//GEN-LAST:event_jDateChooser_Data_NotaAncestorAdded

    private void jDateChooser_Data_NotaAncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jDateChooser_Data_NotaAncestorMoved
    }//GEN-LAST:event_jDateChooser_Data_NotaAncestorMoved

    private void jDateChooser_Data_NotaHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_jDateChooser_Data_NotaHierarchyChanged
    }//GEN-LAST:event_jDateChooser_Data_NotaHierarchyChanged

    private void jComboBox_Tipo_FornecedorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox_Tipo_FornecedorKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_Tipo_FornecedorKeyReleased

    private void jComboBox_Tipo_FornecedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox_Tipo_FornecedorKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            jComboBox_Fornecedor.requestFocus();
        }
    }//GEN-LAST:event_jComboBox_Tipo_FornecedorKeyPressed

    private void jComboBox_Tipo_FornecedorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox_Tipo_FornecedorFocusLost
        PreencherFornecedor();
    }//GEN-LAST:event_jComboBox_Tipo_FornecedorFocusLost

    private void jComboBox_Tipo_FornecedorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_Tipo_FornecedorItemStateChanged
        PreencherFornecedor();
    }//GEN-LAST:event_jComboBox_Tipo_FornecedorItemStateChanged

    private void jComboBox_NaturezaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox_NaturezaKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            jDateChooser_Data_Nota.requestFocus();
        }
        if (evt.getKeyCode() == evt.VK_F5) {
            PreencherNatureza();
        }
    }//GEN-LAST:event_jComboBox_NaturezaKeyPressed

    private void jComboBox_NaturezaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox_NaturezaFocusLost
//        SelecionarNatureza();
        //        if (jLabel_OS.getText() == "Novo") {
        //            preencheNatureza_Tipo();
        //        } else {
        //        }
    }//GEN-LAST:event_jComboBox_NaturezaFocusLost

    private void jComboBox_NaturezaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_NaturezaItemStateChanged
        String selecao = (String) jComboBox_Natureza.getSelectedItem();
        if (selecao == null) {
        } else {
            if (ComboFocuNatureza == 0) {
            } else {
                ManipulaNatureza();
            }
        }
    }//GEN-LAST:event_jComboBox_NaturezaItemStateChanged

    public void ManipulaNatureza() {
        jComboBox_Natureza_Int.setSelectedIndex(jComboBox_Natureza.getSelectedIndex());
        jComboBox_Natureza_Tipo.setSelectedIndex(jComboBox_Natureza.getSelectedIndex());
        System.out.println("==========  String " + jComboBox_Natureza.getSelectedItem());
        System.out.println("==========  Int " + jComboBox_Natureza.getSelectedItem());
        System.out.println("==========  Int " + jComboBox_Natureza_Int.getSelectedIndex());
        MinhaNatureza = "" + jComboBox_Natureza_Tipo.getSelectedItem();
        this.setTitle("Cadastro Movimento [ " + MinhaNatureza + " ] Id Referencia : " + id_referencia);
    }

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
CarregaSistemaRepeteUltimo();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jRadioButton_ativa_VizualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_ativa_VizualizarActionPerformed
//        SalvaReferenciado();
    }//GEN-LAST:event_jRadioButton_ativa_VizualizarActionPerformed

    private void jComboBox_FornecedorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_FornecedorItemStateChanged
        String selecao = (String) jComboBox_Fornecedor.getSelectedItem();
        if (selecao == null) {
        } else {
            if (ComboFocuFornecedor == 0) {
            } else {
                jComboBox_Fornecedor_Int.setSelectedIndex(jComboBox_Fornecedor.getSelectedIndex());
                System.out.println("==========  String " + jComboBox_Fornecedor.getSelectedItem());
                System.out.println("==========  Int " + jComboBox_Fornecedor.getSelectedItem());
                System.out.println("==========  Int " + jComboBox_Fornecedor_Int.getSelectedIndex());
            }
        }

    }//GEN-LAST:event_jComboBox_FornecedorItemStateChanged

    private void jComboBox_FornecedorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox_FornecedorFocusLost
//        SelecionarFornecedor();
    }//GEN-LAST:event_jComboBox_FornecedorFocusLost

    private void jButton_Adicionar_Produto_NotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Adicionar_Produto_NotaActionPerformed
        BotaoAdicionarProduto();
    }//GEN-LAST:event_jButton_Adicionar_Produto_NotaActionPerformed

    private void jButton_Adicionar_Produto_NotaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton_Adicionar_Produto_NotaKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            BotaoAdicionarProduto();
        }
    }//GEN-LAST:event_jButton_Adicionar_Produto_NotaKeyPressed

    private void jButton_Fechar_NotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Fechar_NotaActionPerformed
        ;
        LimpaCampoProduto();
        FlagAlterar = 0;
    }//GEN-LAST:event_jButton_Fechar_NotaActionPerformed

    private void jButton_Excluir_Movimento_NotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Excluir_Movimento_NotaActionPerformed

        int resposta = 0;
//        String status = jLabel_Status_Nota.getText();
        resposta = JOptionPane.showConfirmDialog(rootPane, "Deseja realmente excluir/ativar ? ");
        if (resposta == JOptionPane.YES_OPTION) {
            Mbeans.setId_mov(Integer.parseInt(jLabel_IdMovimento.getText()));
            Mbeans.setRegistro_mov(Principal.jLabel_Data.getText() + " " + Principal.jLabel_Hora.getText());
            Mbeans.setStatus_mov(3);
            Mbeans.setUsuario_mov(Principal.jLabelNomeUsuario.getText());
            Mdao.ExcluirMovProduto(Mbeans);
            System.out.println("Interface.MovimentoCadastroJIF.jButton_Excluir_Movimento_NotaActionPerformed()     ");
            LimpaCampoProduto();
            FlagAlterar = 0;
        }

        jButton_Excluir_Movimento_Nota.setEnabled(false);
    }//GEN-LAST:event_jButton_Excluir_Movimento_NotaActionPerformed

    private void jTextField_Quantidade_NotaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_Quantidade_NotaFocusLost
        int S_Natureza_Int = 0;
        System.out.println("jTextField_Fragmento_Variavel - " + jTextField_Fragmento_Variavel.getText());
        if (jLabel_Calculado.getText().isEmpty() | jLabel_Calculado.getText().equals(" ")) {
            jLabel_Calculado.setText(jTextField_Quantidade_Nota.getText());
        } else if (jLabel_Calculado.getText().equals("0") | jLabel_Calculado.getText() == "0") {
            jLabel_Calculado.setText(jTextField_Quantidade_Nota.getText());
        } else {
        }
        String s = jTextField_Quantidade_Nota.getText();
        if (jTextField_Fragmento_Variavel.getText().equals("1")) {
//            JOptionPane.showMessageDialog(jLabel_Complemento, "1");
            if (s.matches("^[a-zA-ZÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü]*$")) {
//          jLabel4.setText("letra");
                System.out.println("jTextField_Quantidade_Nota             ==               a");
            } else if (s.matches("^[0-9]*$")) {
                jTextField_Quantidade_Nota.setText(jTextField_Quantidade_Nota.getText() + ",000");
                System.out.println("jTextField_Quantidade_Nota             ==               b");
//             jLabel4.setText("numero");
            } else if (s.matches("^[0-9,.]*$")) {
                System.out.println("jTextField_Quantidade_Nota             ==               c");
                String MeuValor = jTextField_Quantidade_Nota.getText();
                MeuValor = MeuValor.replace("R$", "").replace(" ", "").replace(".", "").replace(",", ".");
                Double num41 = (Double.parseDouble(MeuValor));
                BigDecimal df1 = new BigDecimal(num41);
                NumberFormat nf1 = NumberFormat.getInstance();// getCurrencyInstance
                nf1.setMinimumFractionDigits(3);
                nf1.setMaximumFractionDigits(3);
                String FormatoValorProd = nf1.format(df1);

                jTextField_Quantidade_Nota.setText(FormatoValorProd);
                System.out.println("c   -     " + FormatoValorProd);
            } else {
                jTextField_Quantidade_Nota.setText(jTextField_Quantidade_Nota.getText() + ",000");
//            jLabel4.setText("erro");
                System.out.println("jTextField_Quantidade_Nota             ==               d");
            }
        } else {
            if (jTextField_Fragmento_Variavel.getText().equals("0")) {
//            JOptionPane.showMessageDialog(jLabel_Complemento, "1");
                if (s.matches("^[a-zA-ZÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü]*$")) {
//          jLabel4.setText("letra");
                    System.out.println(" jTextField_Fragmento_Variavel     ==     a");
                } else if (s.matches("^[0-9]*$")) {
                    jTextField_Quantidade_Nota.setText(jTextField_Quantidade_Nota.getText().replace(",000", "").replace(",00", "").replace(",0", "").replace(",", ""));
//             jLabel4.setText("numero");
                    System.out.println(" jTextField_Fragmento_Variavel     ==     b");
                } else if (s.matches("^[0-9,]*$")) {
                    jTextField_Quantidade_Nota.setText(jTextField_Quantidade_Nota.getText().replace(",000", "").replace(",00", "").replace(",0", "").replace(",", ""));
                    System.out.println(" jTextField_Fragmento_Variavel     ==     c");
                } else {
//                jTextField_Quantidade_Nota.setText(jTextField_Quantidade_Nota.getText() + ",000");
//            jLabel4.setText("erro");
                    System.out.println(" jTextField_Fragmento_Variavel     ==     d");
                }
//            JOptionPane.showMessageDialog(jLabel_Complemento, "0");
            }
        }
        String VerQuantidade = jTextField_Quantidade_Nota.getText().replace(" ", "").replace(".", "").replace(",", ".");
        System.out.println("Natureza ------------------------------------------  " + jComboBox_Natureza_Int.getSelectedItem());
//         try{
        String S_Natureza = "" + jComboBox_Natureza_Int.getSelectedItem();
        S_Natureza_Int = Integer.parseInt(S_Natureza);
//         }
//         catch( Exception ex){JOptionPane.showMessageDialog(jLabel_Complemento, "erro "+ex);}
        System.out.println("Natureza int  " + S_Natureza_Int + "    S_Natureza    " + S_Natureza + " jComboBox_Natureza ==  " + jComboBox_Natureza.getSelectedItem());
        if (VerQuantidade.isEmpty()) {
            System.out.println("Resultado     -  vazio  ");
        } else if (VerQuantidade.equals("null") | VerQuantidade.equals(null)) {
            System.out.println("Resultado     -  null  ");
        } else if (S_Natureza_Int == 1) {
            System.out.println("Interface.MovimentoCadastroJIF.jTextField_Quantidade_NotaFocusLost()      -            Tipo Entrada ok");
        } else {
            System.out.println("jTextField_Varieavel_SaldoProduto - " + jTextField_Varieavel_SaldoProduto.getText());
            Double DQuantidade = Double.parseDouble(VerQuantidade);
            Double DSaldo = Double.parseDouble(jTextField_Varieavel_SaldoProduto.getText());
            Double Resultado;
            Resultado = DSaldo - DQuantidade;
            System.err.println("Resultado     -    " + Resultado);
            if (Resultado == 0.0) {
                JOptionPane.showMessageDialog(jLabel_Complemento, "Zerando saldo produto");
            } else if (DQuantidade > DSaldo) {
                JOptionPane.showMessageDialog(jLabel_Complemento, "Saldo produto insuficiente");
            } else {
            }
            jTextField_Varieavel_SaldoProduto.setText("");
        }
    }//GEN-LAST:event_jTextField_Quantidade_NotaFocusLost

    private void jTextField_Quantidade_NotaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_Quantidade_NotaKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            if (jTextField_Quantidade_Nota.getText().isEmpty()) {
                jTextField_Busca_Produto_Nota.requestFocus();
            } else {
                jButton_Adicionar_Produto_Nota.requestFocus();
            }
        }

        if (evt.getKeyCode() == evt.VK_F12) {
            if (jLabel_Calculado.getText().equals("")) {
                jLabel_Calculado.setText(jTextField_Quantidade_Nota.getText());
            } else {
            }
            Complementar.VerificaModo(MinhaNatureza);
            Complementar.RecebeDados(jLabel_Complemento.getText(), jLabel_Setor.getText(), jLabel_Calculado.getText());
            Complementar.setVisible(true);
        }

        if (evt.getKeyCode() == evt.VK_DOWN) {
            //            jFormattedTextFieldCep.requestFocus();
        }
        if (evt.getKeyCode() == evt.VK_UP) {
            //            jTextField_Busca_Produto_PRO.requestFocus();
        }
    }//GEN-LAST:event_jTextField_Quantidade_NotaKeyPressed

    private void jTextField_Busca_Produto_NotaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_Busca_Produto_NotaFocusGained
        // evento_Verifica();
    }//GEN-LAST:event_jTextField_Busca_Produto_NotaFocusGained

    private void jTextField_Busca_Produto_NotaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_Busca_Produto_NotaFocusLost

    }//GEN-LAST:event_jTextField_Busca_Produto_NotaFocusLost

    private void jTextField_Busca_Produto_NotaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_Busca_Produto_NotaKeyPressed

        if (evt.getKeyCode() == evt.VK_F12) {
            Complementar.PreencherSetor();

            //            CD.RecebeDadosMovimentoAlterado(MinhaNatureza, jLabel_Complemento.getText(), jLabel_Setor.getText());
            Complementar.VerificaModo(MinhaNatureza);
            Complementar.RecebeDados(jLabel_Complemento.getText(), jLabel_Setor.getText(), jLabel_Calculado.getText());
            Complementar.setVisible(true);

        }
        if (evt.getKeyCode() == evt.VK_F2) {
            String BuscaNome = jTextField_Busca_Produto_Nota.getText();
            BP.recebe_nome(BuscaNome);
            BP.setVisible(true);
        }
        if (evt.getKeyCode() == evt.VK_ENTER) {
            EventoBuscaProduto();
            if (jTextField_Busca_Produto_Nota.getText().isEmpty()) {
                jLabel_IdProduto.setText("0");
            } else {
            }
        }
    }//GEN-LAST:event_jTextField_Busca_Produto_NotaKeyPressed

    private void jTableListaProduto_NotaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableListaProduto_NotaMouseClicked

        if (jTableListaProduto_Nota.isEnabled()) {
            String busca_Lista = "" + jTableListaProduto_Nota.getValueAt(jTableListaProduto_Nota.getSelectedRow(), 8);
            //            jLabel_Id_Mov.setText((busca_Lista));
            //            jLabelIdMovi.setText((busca_Lista));
            String busca_item = "" + jTableListaProduto_Nota.getValueAt(jTableListaProduto_Nota.getSelectedRow(), 0);
            jLabel_IdMovimento.setText(busca_item);
            //            Evento_Busca_Movimento_Nota();
            //            flag_salvar = 2;
            jTextField_Quantidade_Nota.requestFocus();
            jButton_Excluir_Movimento_Nota.setEnabled(true);
            //            jButton_Adicionar_Produto_Nota.setText("Alterar produto");
            PreencheDadosAlterar();
            FlagAlterar = 1;
            Referencia_Movimento = "" + jTableListaProduto_Nota.getValueAt(jTableListaProduto_Nota.getSelectedRow(), 0);
//                        JOptionPane.showMessageDialog(rootPane, Referencia_Movimento);
        } else {
        }
    }//GEN-LAST:event_jTableListaProduto_NotaMouseClicked

    private void jTableListaProduto_NotaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableListaProduto_NotaMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableListaProduto_NotaMouseEntered

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        this.jTabbedPane1.setSelectedIndex(0);

        PreencherTabela();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        ManipulaNatureza();
        FrameMovimentoComplemento FrameMovimentoComplemento = new FrameMovimentoComplemento();
        FrameMovimentoComplemento.RecebeDados(String.valueOf(id_referencia), MinhaNatureza, "" + jComboBox_Natureza_Tipo.getSelectedItem(), "" + jComboBox_Natureza_Int.getSelectedItem());
        FrameMovimentoComplemento.PreencherTabela();
        FrameMovimentoComplemento.setVisible(true);
        PreencherTabela();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTableListaProduto_ComplementoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableListaProduto_ComplementoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableListaProduto_ComplementoMouseClicked

    private void jComboBox_modalidadeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_modalidadeItemStateChanged
        //        manipula_transporte();
    }//GEN-LAST:event_jComboBox_modalidadeItemStateChanged

    private void jComboBox_transportadoraItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_transportadoraItemStateChanged

        String selecao = (String) jComboBox_transportadora.getSelectedItem();

        if (selecao == null) {
            jLabel_exibe_transporte.setText(null);
            vol_transportadora.setText("1");
//             jComboBox_transportadoraInt.setSelectedIndex(1);
        } else {
            if (ComboFocu == 0) {
            } else {
                jComboBox_transportadoraInt.setSelectedIndex(jComboBox_transportadora.getSelectedIndex());
                System.out.println("==========  String " + jComboBox_transportadora.getSelectedItem());
                System.out.println("==========  Int " + jComboBox_transportadoraInt.getSelectedItem());
                System.out.println("==========  Int " + jComboBox_transportadoraInt.getSelectedIndex());
                //            exibe_transporte();//preenche_Transporte();
            }
        }
    }//GEN-LAST:event_jComboBox_transportadoraItemStateChanged

    private void jComboBox_transportadoraFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox_transportadoraFocusLost

    }//GEN-LAST:event_jComboBox_transportadoraFocusLost

    private void jComboBox_transportadoraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox_transportadoraKeyPressed
        if (evt.getKeyCode() == evt.VK_F5) {
            PreencherTransporte();
        }
    }//GEN-LAST:event_jComboBox_transportadoraKeyPressed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        if (jRadioButton1.isSelected()) {
            jComboBox_modalidade.setEnabled(true);
            jComboBox_transportadora.setEnabled(true);
            jLabel10.setEnabled(true);
            jLabel24.setEnabled(true);
            //            preenche_Transporte_Busca();
            String selecao = (String) jComboBox_transportadora.getSelectedItem();
            if (selecao == ("NÃO DEFINIDO")) {
                //                preenche_Transporte_Novo();
            }
            //            preenche_Transporte_Busca();
        } else {

            jComboBox_modalidade.setEnabled(false);
            jComboBox_transportadora.setEnabled(false);
            jLabel10.setEnabled(false);
            jLabel24.setEnabled(false);
            jLabel_exibe_transporte.setText(null);
        }
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void vol_motoristaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_vol_motoristaFocusGained
        //        vol_motorista.setBackground(Color.cyan);
    }//GEN-LAST:event_vol_motoristaFocusGained

    private void vol_motoristaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_vol_motoristaFocusLost
        vol_motorista.setText(vol_motorista.getText().toUpperCase());
        vol_motorista.setBackground(Color.WHITE);
    }//GEN-LAST:event_vol_motoristaFocusLost

    private void vol_ufFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_vol_ufFocusLost
        AjustaMinhaUf();
        vol_uf.setText(vol_uf.getText().toUpperCase());
    }//GEN-LAST:event_vol_ufFocusLost

    private void vol_placaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_vol_placaFocusLost
        //aqui
        AjustaMinhaPlaca();
        vol_placa.setText(vol_placa.getText().toUpperCase());
    }//GEN-LAST:event_vol_placaFocusLost

    private void jComboBox_SituacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_SituacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_SituacaoActionPerformed

    private void vol_transportadoraFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_vol_transportadoraFocusGained
        //        vol_transportadora.setBackground(Color.cyan);
    }//GEN-LAST:event_vol_transportadoraFocusGained

    private void vol_transportadoraFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_vol_transportadoraFocusLost
        String tam = vol_transportadora.getText();
        if (tam.length() <= 2) {
            return;
        } else {
            // jLabelAlerta.setText("Máximo de 30 caracteres para o jTextField");
            //            JOptionPane.showMessageDialog(rootPane, "Máximo de 2 caracteres para o jTextField");
            vol_transportadora.requestFocus();

            //            return str;
        }
        vol_transportadora.setText(vol_transportadora.getText().toUpperCase());
        vol_transportadora.setBackground(null);
    }//GEN-LAST:event_vol_transportadoraFocusLost

    private void vol_numeracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vol_numeracaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vol_numeracaoActionPerformed

    private void vol_especieFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_vol_especieFocusLost
        vol_especie.setText(vol_especie.getText().toUpperCase());
    }//GEN-LAST:event_vol_especieFocusLost

    private void jTableListaProduto_Palete_ProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableListaProduto_Palete_ProdutoMouseClicked
      jLabel_Informacoes.setText("");  if (jTableListaProduto_Palete_Produto.isEnabled()) {
            String busca_Lista = "" + jTableListaProduto_Palete_Produto.getValueAt(jTableListaProduto_Palete_Produto.getSelectedRow(), 3);
            SelecaoReferencia_Movimento = "" + jTableListaProduto_Palete_Produto.getValueAt(jTableListaProduto_Palete_Produto.getSelectedRow(), 4);
            System.out.println("PreencheTabelaPalete()  " + SelecaoReferencia_Movimento);
            jLabel_Exibe_para_Palete.setText("" + jTableListaProduto_Palete_Produto.getValueAt(jTableListaProduto_Palete_Produto.getSelectedRow(), 3) + " / " + jTableListaProduto_Palete_Produto.getValueAt(jTableListaProduto_Palete_Produto.getSelectedRow(), 2));
            InsereNaTabela();
            PreencheTabelaPalete();
            DesbloqueiaCamposPalete();
            limpaCamposPalete();
            jtPctPad.requestFocus();
//            jLabel_preenche_texto.setText("Quantidade : " + busca_Lista);
            QuantidadePalete = ("Quantidade : " + busca_Lista);
            SomaTotalPalete();
            PreencherTabelaProdutoPalete_2();
        } else {
        }
    }//GEN-LAST:event_jTableListaProduto_Palete_ProdutoMouseClicked

    private void jbALinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbALinActionPerformed
        int resposta = 0;
        System.out.println("Interface.MovimentoCadastroJIF.jbALinActionPerformed()     " + Id_Palete);
        if (Id_Linha.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "SELECIONE UM ITEM A SER EXCLUIDO.");
        } else {
            resposta = JOptionPane.showConfirmDialog(rootPane, "Deseja realmente APAGAR A ITEM ? ");
            if (resposta == JOptionPane.YES_OPTION) {
                beansp.setStpalete(3);
                beansp.setVerifica_principal(3);
                beansp.setIdlinha(Integer.parseInt(Id_Linha));
                daop.ExcluirTempLinha(beansp);
                FlagPalete = 0;
                PreencheTabelaPalete();
                System.out.println("excluido item ");
                limpaCamposPalete();
                ContaTotalLinhas();
            }
        }
    }//GEN-LAST:event_jbALinActionPerformed

    private void jbLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbLimparActionPerformed
        PreencheTabelaPalete();
        limpaCamposPalete();

    }//GEN-LAST:event_jbLimparActionPerformed

    private void jbAdicionarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jbAdicionarFocusGained
        //        jbAdicionar.setBackground(Color.green);
    }//GEN-LAST:event_jbAdicionarFocusGained

    private void jbAdicionarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jbAdicionarFocusLost
        jbAdicionar.setBackground(Color.GRAY);
    }//GEN-LAST:event_jbAdicionarFocusLost

    private void jbAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAdicionarActionPerformed
        EventoSalvaPalete();
    }//GEN-LAST:event_jbAdicionarActionPerformed

    private void jbAdicionarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jbAdicionarKeyPressed
        //        adicionaLinha();
        if (jtPctPad.getText().isEmpty()) {
            jtPctPad.requestFocus();
        } else {
            jtLast.requestFocus();
        }
        if (evt.getKeyCode() == evt.VK_ENTER) {
            EventoSalvaPalete();
        }
        if (evt.getKeyCode() == evt.VK_F12) {
        }
    }//GEN-LAST:event_jbAdicionarKeyPressed

    private void jButton_Bandeira1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Bandeira1ActionPerformed
        SomaTotalPalete();
        ContaTotalLinhas();
        LancaNaTabelaPalete();
        jLabel_Exibe_para_Palete.setText("");
        PreencherTabelaProdutoPalete();
        LimparTabelaPalete();
        limpaCamposPalete();
        LancaNaTabelaMovimento();
        PreencherTabela();
        jLabel_Informacoes.setText("");

    }//GEN-LAST:event_jButton_Bandeira1ActionPerformed
    public void LancaNaTabelaMovimento() {
        conex_Palete2.conexao();
        System.err.println("**** LancaNaTabelaMovimento - CalcTotalFinal " + CalcTotalFinal);
        System.err.println("**** LancaNaTabelaMovimento - SelecaoReferencia_Movimento " + SelecaoReferencia_Movimento);
        try {
            java.sql.PreparedStatement pst = conex_Palete2.con.prepareStatement(" UPDATE movproduto"
                    + "   SET  qtd_calc='" + CalcTotalFinal + "', qtd_calc_ex='" + CalcTotalFinal + "'"
                    + " WHERE stmovimento=1 and sistema_mov='" + SelecaoReferencia_Movimento + "'  ");
            pst.execute();
            System.out.print("LancaNaTabelaPalete modificado    UPDATE   ok");
        } catch (SQLException ex) {
            System.out.print("LancaNaTabelaPalete  modificado   UPDATE   erro   " + ex);
        }

        conex_Palete2.desconecta();
    }

    public void LancaNaTabelaPalete() {
        conex_Palete.conexao();
        try {
            java.sql.PreparedStatement pst = conex_Palete.con.prepareStatement(""
                    + "UPDATE palete as p\n"
                    + "   SET  stpalete='2'\n"
                    + "from paletetemp as t\n"
                    + "where t.id_palete=p.id_palete and t.verifica_principal='2' ");
            pst.execute();
            System.out.print("LancaNaTabelaPalete modificado    UPDATE   ok");
        } catch (SQLException ex) {
            System.out.print("LancaNaTabelaPalete  modificado   UPDATE   erro   " + ex);
        }

        try {
            java.sql.PreparedStatement pst = conex_Palete.con.prepareStatement(""
                    + "INSERT INTO palete  (\n"
                    + "             pctpad, lastro, altura, pctavu, totpct, unavu, totparc, \n"
                    + "            mov_palete, qtd_palete, nota_palete, verifica_principal, usuario_palete, \n"
                    + "            registro_palete, id_palete, stpalete)\n"
                    + " SELECT  pctpad, lastro, altura, pctavu, totpct, unavu, totparc, \n"
                    + "       mov_palete, qtd_palete, nota_palete, verifica_principal, usuario_palete, \n"
                    + "       registro_palete, id_palete, stpalete\n"
                    + "  FROM paletetemp\n"
                    + "  where mov_palete=mov_palete and verifica_principal='2' ");
            pst.execute();
            System.out.print("LancaNaTabelaPalete    INSERT    ok");
        } catch (SQLException ex) {
            System.out.print("LancaNaTabelaPalete    INSERT    erro   " + ex);
        }
        try {
            java.sql.PreparedStatement pst = conex_Palete.con.prepareStatement(""
                    + "INSERT INTO palete  (\n"
                    + "             pctpad, lastro, altura, pctavu, totpct, unavu, totparc, \n"
                    + "            mov_palete, qtd_palete, nota_palete, verifica_principal, usuario_palete, \n"
                    + "            registro_palete, id_palete, stpalete)\n"
                    + " SELECT  pctpad, lastro, altura, pctavu, totpct, unavu, totparc, \n"
                    + "       mov_palete, qtd_palete, nota_palete, verifica_principal, usuario_palete, \n"
                    + "       registro_palete, id_palete, stpalete\n"
                    + "  FROM paletetemp\n"
                    + "  where mov_palete=mov_palete and verifica_principal='1' ");
            pst.execute();
            System.out.print("LancaNaTabelaPalete    INSERT    ok");
        } catch (SQLException ex) {
            System.out.print("LancaNaTabelaPalete    INSERT    erro   " + ex);
        }

        try {
            java.sql.PreparedStatement pst = conex_Palete.con.prepareStatement(""
                    + "UPDATE palete as p\n"
                    + "   SET  stpalete='3'\n"
                    + "from paletetemp as t\n"
                    + "where t.id_palete=p.id_palete and t.verifica_principal='3' ");
            pst.execute();
            System.out.print("LancaNaTabelaPalete modificado  excluido  UPDATE   ok");
        } catch (SQLException ex) {
            System.out.print("LancaNaTabelaPalete  modificado  excluido  UPDATE   erro   " + ex);
        }

        try {
            java.sql.PreparedStatement pst = conex_Palete.con.prepareStatement(""
                    + "UPDATE palete as p\n"
                    + "   SET  stpalete='3'\n"
                    + "from paletetemp as t\n"
                    + "where t.mov_palete=p.mov_palete and t.verifica_principal='3' ");
            pst.execute();
            System.out.print("LancaNaTabelaPalete modificado  excluido  UPDATE   ok");
        } catch (SQLException ex) {
            System.out.print("LancaNaTabelaPalete  modificado  excluido  UPDATE   erro   " + ex);
        }

        conex_Palete.desconecta();
    }

    private void jbALisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbALisActionPerformed
        int resposta = 0;
        System.out.println("Interface.MovimentoCadastroJIF.jbALisActionPerformed()   " + SelecaoReferencia_Movimento);
        if (SelecaoReferencia_Movimento.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "SELECIONE UM ITEM A SER EXCLUIDO.");
        } else if (SelecaoReferencia_Movimento.equals(null)) {
            JOptionPane.showMessageDialog(rootPane, "SELECIONE UM ITEM A SER EXCLUIDO./n NULL");
        } else {
            resposta = JOptionPane.showConfirmDialog(rootPane, "Deseja realmente APAGAR A LISTA ? ");
            if (resposta == JOptionPane.YES_OPTION) {
                beansp.setStpalete(3);
                beansp.setVerifica_principal(3);
                beansp.setMov_palete(SelecaoReferencia_Movimento);
                daop.ExcluirTempLista(beansp);
                FlagPalete = 0;
                PreencheTabelaPalete();
                System.out.println("excluido lista ");
                limpaCamposPalete();
            }
        }
    }//GEN-LAST:event_jbALisActionPerformed

    private void jtTotPctActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtTotPctActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtTotPctActionPerformed

    private void jtPctAvuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtPctAvuFocusLost
        if (jtPctAvu.getText().isEmpty()) {
            jtPctAvu.setText("0");
        } else {
            //            jtPctAvu.setText(jtPctAvu.getText().replace(",", "."));
        }
    }//GEN-LAST:event_jtPctAvuFocusLost

    private void jtPctAvuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtPctAvuKeyPressed

        if (evt.getKeyCode() == evt.VK_ENTER) {
            if (jtPctAvu.getText().isEmpty()) {
                jtPctAvu.setText("0");
            } else {
                //                jtPctAvu.setText(jtPctAvu.getText().replace(",", "."));
            }

            jtUnAvu.requestFocus();
            SomaLinhaPaleteExibir();
        }
        if (evt.getKeyCode() == evt.VK_DOWN) {
            jtUnAvu.requestFocus();
        }
        if (evt.getKeyCode() == evt.VK_UP) {
            jtAlt.requestFocus();
        }
    }//GEN-LAST:event_jtPctAvuKeyPressed

    private void jtAltFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtAltFocusLost
        if (jtAlt.getText().isEmpty()) {
            jtAlt.setText("0");
        } else {
            //            jtAlt.setText(jtAlt.getText().replace(",", "."));
        }
    }//GEN-LAST:event_jtAltFocusLost

    private void jtAltKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtAltKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            if (jtAlt.getText().isEmpty()) {
                jtAlt.setText("0");
            } else {
                //                jtAlt.setText(jtAlt.getText().replace(",", "."));
            }
            jtPctAvu.requestFocus();
            SomaLinhaPaleteExibir();
        }
        if (evt.getKeyCode() == evt.VK_DOWN) {
            jtPctAvu.requestFocus();
        }
        if (evt.getKeyCode() == evt.VK_UP) {
            jtLast.requestFocus();
        }
    }//GEN-LAST:event_jtAltKeyPressed

    private void jtLastFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtLastFocusLost
        if (jtLast.getText().isEmpty()) {
            jtLast.setText("0");
        } else {
            //            jtLast.setText(jtLast.getText().replace(",", "."));
        }
    }//GEN-LAST:event_jtLastFocusLost

    private void jtLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtLastActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtLastActionPerformed

    private void jtLastKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtLastKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            if (jtLast.getText().isEmpty()) {
                jtLast.setText("0");
            } else {
                //                jtLast.setText(jtLast.getText().replace(",", "."));
            }

            jtAlt.requestFocus();
            SomaLinhaPaleteExibir();
        }
        if (evt.getKeyCode() == evt.VK_F12) {

        }
        if (evt.getKeyCode() == evt.VK_DOWN) {
            jtAlt.requestFocus();
        }
        if (evt.getKeyCode() == evt.VK_UP) {
            jtPctPad.requestFocus();
        }
    }//GEN-LAST:event_jtLastKeyPressed

    private void jtPctPadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtPctPadFocusGained
        //        if (jLabel_Id_mov_palete.getText().isEmpty()) {
        //        } else {
        //
        //        }
    }//GEN-LAST:event_jtPctPadFocusGained

    private void jtPctPadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtPctPadFocusLost
        if (jtPctPad.getText().isEmpty()) {
            jtPctPad.setText("1");
        } else {
            //            jtPctPad.setText(jtPctPad.getText().replace(",", "."));

        }
    }//GEN-LAST:event_jtPctPadFocusLost

    private void jtPctPadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtPctPadKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            if (jtPctPad.getText().isEmpty()) {
                jtPctPad.setText("0");
            } else {
                //                jtPctPad.setText(jtPctPad.getText().replace(",", "."));
            }

            jtLast.requestFocus();
        }
        if (evt.getKeyCode() == evt.VK_DOWN) {
            jtLast.requestFocus();
        }
        if (evt.getKeyCode() == evt.VK_UP) {

        }
    }//GEN-LAST:event_jtPctPadKeyPressed

    private void jtUnAvuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtUnAvuFocusLost
        if (jtUnAvu.getText().isEmpty()) {
            jtUnAvu.setText("0");
        } else {
            //            jtUnAvu.setText(jtUnAvu.getText().replace(",", "."));
        }
    }//GEN-LAST:event_jtUnAvuFocusLost

    private void jtUnAvuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtUnAvuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtUnAvuActionPerformed

    private void jtUnAvuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtUnAvuKeyPressed

        if (evt.getKeyCode() == evt.VK_ENTER) {
            if (jtUnAvu.getText().isEmpty()) {
                jtUnAvu.setText("0");
            } else {
                //                jtUnAvu.setText(jtUnAvu.getText().replace(",", "."));
            }

            jbAdicionar.requestFocus();
            SomaLinhaPaleteExibir();
        }
        if (evt.getKeyCode() == evt.VK_DOWN) {
            jbAdicionar.requestFocus();
        }
        if (evt.getKeyCode() == evt.VK_UP) {
            jtPctAvu.requestFocus();
        }
    }//GEN-LAST:event_jtUnAvuKeyPressed

    private void jtUnAvuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtUnAvuKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jtUnAvuKeyReleased

    private void jTablePaleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePaleteMouseClicked
        if (jTablePalete.isEnabled()) {
            String nome = "" + jTablePalete.getValueAt(jTablePalete.getSelectedRow(), 0);
            String idLinha = "" + jTablePalete.getValueAt(jTablePalete.getSelectedRow(), 0);
            String pctpad = "" + jTablePalete.getValueAt(jTablePalete.getSelectedRow(), 1);
            String lastro = "" + jTablePalete.getValueAt(jTablePalete.getSelectedRow(), 2);
            String altura = "" + jTablePalete.getValueAt(jTablePalete.getSelectedRow(), 3);
            String pctavu = "" + jTablePalete.getValueAt(jTablePalete.getSelectedRow(), 4);
            String totpct = "" + jTablePalete.getValueAt(jTablePalete.getSelectedRow(), 5);
            String unavu = "" + jTablePalete.getValueAt(jTablePalete.getSelectedRow(), 6);
            String totparc = "" + jTablePalete.getValueAt(jTablePalete.getSelectedRow(), 7);
            Id_Linha = "" + jTablePalete.getValueAt(jTablePalete.getSelectedRow(), 0);//9
            jtPctPad.setText((pctpad));
            jtAlt.setText((altura));
            jtLast.setText((lastro));
            jtPctAvu.setText((pctavu));
            jtTotPct.setText((totpct));
            jtUnAvu.setText((unavu));
            jtTotParc.setText((totparc));
            FlagPalete = 1;
            jbALis.setEnabled(true);
            jbALin.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "VERIFIQUE TABELA");
        }
    }//GEN-LAST:event_jTablePaleteMouseClicked

    private void jComboBox_NaturezaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox_NaturezaFocusGained
//        SelecionarNaturezaInt = 1;
        ComboFocuNatureza = 1;
    }//GEN-LAST:event_jComboBox_NaturezaFocusGained

    private void jComboBox_Tipo_FornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_Tipo_FornecedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_Tipo_FornecedorActionPerformed

    private void jTextField_HoraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_HoraKeyReleased
        AjustaMinhaHora();
    }//GEN-LAST:event_jTextField_HoraKeyReleased

    private void vol_placaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vol_placaKeyReleased
        AjustaMinhaPlaca();
    }//GEN-LAST:event_vol_placaKeyReleased

    private void jComboBox_transportadoraFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox_transportadoraFocusGained
        ComboFocu = 1;


    }//GEN-LAST:event_jComboBox_transportadoraFocusGained

    private void jComboBox_NaturezaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_NaturezaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_NaturezaActionPerformed

    private void jComboBox_FornecedorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox_FornecedorFocusGained
        ComboFocuFornecedor = 1;

    }//GEN-LAST:event_jComboBox_FornecedorFocusGained

    private void jComboBox_SituacaoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_SituacaoItemStateChanged
        jLabel_Status_Visualizar.setText("Status : " + jComboBox_Situacao.getSelectedItem());
    }//GEN-LAST:event_jComboBox_SituacaoItemStateChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        jLabel_Exibe_para_Palete.setText("");
        PreencherTabelaProdutoPalete();
        LimparTabelaPalete();
        limpaCamposPalete();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        LimpaSistema();
        LimpaCampoProduto();
    }//GEN-LAST:event_formInternalFrameClosing

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        EventoFechaMovimento();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    public void AjustaMinhaUf() {
        String s = vol_uf.getText().replace(".", "").replace("-", "");
        String formatadoA, formatadoC;
        formatadoA = s.substring(0, 2);
        System.out.println("formatadoA     " + formatadoA);
        if (formatadoA.matches("^[a-zA-ZÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü]*$")) {
            formatadoC = formatadoA;
        } else {
            System.out.println("if uf   erase ");
            formatadoC = "";
            vol_uf.requestFocus();
        }
        System.err.println("    uf   " + formatadoC);
        vol_uf.setText(formatadoC);
    }

    public void AjustaMinhaPlaca() {

        String s = vol_placa.getText().replace(".", "").replace("-", "");
        String formatadoA, formatadoB, formatadoC;
        int contar = s.length();
        if (contar <= 4) {
        } else {
            formatadoA = s.substring(0, 3);
            System.out.println("formatadoA     " + formatadoA);
            formatadoB = s.substring(3, 7);
            System.out.println("formatadoB     " + formatadoB);
            if (formatadoA.matches("^[a-zA-ZÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü]*$")) {
                formatadoA = formatadoA;
            } else {
                formatadoA = "erro A";
            }
            if (formatadoB.matches("^[0-9]*$")) {
                formatadoB = formatadoB;
            } else {
                formatadoB = "erro B";
            }
            if (formatadoB == "erro B" | formatadoA == "erro A") {
                System.out.println("if    erase ");
                formatadoC = "";
                vol_placa.requestFocus();
            } else {
                formatadoC = formatadoA + "-" + formatadoB;
            }
            System.err.println("    placa   " + formatadoC);
            vol_placa.setText(formatadoC);
        }
    }

    public void AjustaMeuCep() {
        String s = vol_placa.getText().replace(".", "").replace("-", "");
        if (s.matches("^[a-zA-ZÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü]*$")) {
            System.out.println("Ajuste deu ................................ERRRROOOOOOOO");
            vol_placa.setBackground(Color.red);
        } else if (s.length() > 10) {
            System.err.println("    eeee   erro");
            vol_placa.setText("");
            vol_placa.requestFocus();
        } else if (s.length() > 9) {
            System.err.println("    eeee   14    erro");
            vol_placa.setText("");
            vol_placa.requestFocus();
        } else if (s.matches("^[0-9]*$")) {
            // www-1234    7
            if (s.length() == 8) {
                String formatado = s.substring(0, 2) + "." + s.substring(2, 5) + "-" + s.substring(5, 8);
                System.err.println("    ddddd   " + formatado); ////   06.415-240
                vol_placa.setText(formatado);
            } else if (s.length() < 8) {
            } else if (s.length() > 8) {
                System.err.println("    eeee   erro");
                vol_placa.setText("");
                vol_placa.requestFocus();
            } else {
                System.err.println("    ddddd   erro");
                vol_placa.setText("");
                vol_placa.requestFocus();
            }
            vol_placa.setBackground(null);
        }
    }

    public void BotaoExcluir() {
        beans.setNota_status(3);
        beans.setId_nota(id_nota);
        dao.Excluir(beans);
        // this.dispose();
    }

    public void BotaoSalvar() {
        PreencherMeuTotal();
//        SelecionarNatureza();
        if (MinhaIdNota == "novo") {
            CarregaUltimo();
            InsereDadosNota();
            dao.Salvar(beans);
            SalvarMovimentoBanco();
//            JOptionPane.showMessageDialog(rootPane, "Entrou novo");
        } else {
//            JOptionPane.showMessageDialog(rootPane, "Entrou alterar");
            beans.setNota_status(2);
//            beans.setId_nota(id_nota);
            beans.setId_referencia(id_referencia);
            dao.Alterar2(beans);
//            CarregaUltimo();
            InsereDadosNota();
            dao.Salvar(beans);
            SalvarMovimentoBanco();
        }
//        SalvarTransporte();
    }

    public void SalvarTransporte() {
        beanst.setModalidade((String) jComboBox_modalidade.getSelectedItem());
        beanst.setTransportadora((String) jComboBox_transportadora.getSelectedItem());
        beanst.setMotorista(vol_motorista.getText());

        String placa = (vol_placa.getText());
        if (placa.equals("   -    ")) {
            beanst.setPlaca("");
        } else {
            beanst.setPlaca(vol_placa.getText());
        }
        String uf = (vol_uf.getText());
        if (uf.equals("  ")) {
            beanst.setUf("");
        } else {
            beanst.setUf(vol_uf.getText());
        }

        beanst.setQuantidade(vol_quantidade.getText());
        beanst.setEspecie(vol_especie.getText());
        beanst.setNumeracao(vol_numeracao.getText());
        beanst.setPesobruto(vol_peso_bruto.getText());
        beanst.setPesoliquido(vol_peso_liquido.getText());

        beanst.setOsnota(id_referencia);
        beanst.setOnline(0);
        daot.Alterar(beanst);
        beanst.setOnline(1);
        daot.Salvar(beanst);
    }

    public void SalvarMovimentoBanco() {
        MovimentoUpdatePrimario();
//        MovimentoBancoUpdateTodos();
        MovimentoBancoInsert();
    }

    public void MovimentoUpdatePrimario() {
        String busca = String.valueOf(id_referencia);
        conex.conexao();
        try {
            if (MinhaNatureza.equals("ENTRADA")) {
                java.sql.PreparedStatement pst = conex.con.prepareStatement("UPDATE movproduto as base SET qtd_mov=qtd_prod "
                        + " WHERE  base.nota_mov='" + busca + "'");
                pst.execute();
                System.out.println(" MovimentoUpdatePrimario -- MinhaNatureza ENTRADA - ok ");
            } else if (MinhaNatureza.equals("SAIDA")) {
                java.sql.PreparedStatement pst = conex.con.prepareStatement("UPDATE movproduto as base SET qtd_mov=-qtd_prod "
                        + " WHERE  base.nota_mov='" + busca + "'");
                pst.execute();
                System.out.println("MovimentoUpdatePrimario -- MinhaNatureza SAIDA - ok ");
            } else {
                System.out.println(" MovimentoUpdatePrimario -- MinhaNatureza ????????   -- ERRO");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao MovimentoUpdatePrimario() - " + ex);
        }
        conex.desconecta();
    }

    public void MovimentoBancoUpdateTodos() {
        String busca = String.valueOf(id_referencia);

        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement("UPDATE movprodutobase as base\n"
                    + "      SET stmovimento=2 from movproduto as temp\n"
                    + " WHERE base.stmovimento = 3 and base.nota_mov='" + busca + "'");
            pst.execute();
//            JOptionPane.showMessageDialog(rootPane, " MovimentoBancoUpdateTodos "+id_referencia);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao MovimentoBancoUpdateTodos() . \\n" + ex);
        }
        conex.desconecta();
    }

    public void MovimentoBancoTemporarioApagar() {

        conexApaga.conexao();
        try {
            java.sql.PreparedStatement pst = conexApaga.con.prepareStatement("TRUNCATE TABLE movproduto RESTART IDENTITY;");
            pst.execute();
            System.out.println("aqui movimento temporario apagar ok");
//            JOptionPane.showMessageDialog(rootPane, " MovimentoBancoTemporarioApagar ");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao MovimentoBancoTemporarioApagar() . \\n" + ex);
        }
        conexApaga.desconecta();
    }

    public void MovimentoBancoTemporario() {
        int busca = id_referencia;
        conexInsere.conexao();
        try {
            java.sql.PreparedStatement pst = conexInsere.con.prepareStatement(" INSERT INTO movproduto  (\n"
                    + "             id_prod_ent, data_mov, nota_mov, sistema_mov, qtd_mov, \n"
                    + "            qtd_prod, qtd_prod_ex, qtd_calc, qtd_calc_ex, valor_real, valor_moeda, \n"
                    + "            destino_mov, complemento_mov, registro_mov, status_mov, volume, \n"
                    + "            usuario_mov, modo_mov, total_mov)\n"
                    + "    SELECT  id_prod_ent, data_mov, nota_mov, sistema_mov, qtd_mov, \n"
                    + "       qtd_prod, qtd_prod_ex, qtd_calc, qtd_calc_ex, valor_real, valor_moeda, \n"
                    + "       destino_mov, complemento_mov, registro_mov, status_mov, volume, \n"
                    + "       usuario_mov, modo_mov, total_mov\n"
                    + "  FROM movprodutobase"
                    + " WHERE status_mov='ATIVO' and nota_mov='" + busca + "'");//// status_mov='ATIVO' and

            pst.execute();
            System.out.println(".............................................MovimentoBancoTemporario.......................");
//            JOptionPane.showMessageDialog(null, "MovimentoBancoTemporario");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao MovimentoBancoTemporario() . \\n" + ex);
        }
        conexInsere.desconecta();
    }

    public void MovimentoBancoInsert() {
        int busca = id_referencia;
        conex.conexao();
        try {
            java.sql.PreparedStatement pst1 = conex.con.prepareStatement("UPDATE movprodutobase as w\n"
                    + "   SET id_prod_ent=base.id_prod_ent, data_mov=base.data_mov, nota_mov=base.nota_mov, qtd_mov=base.qtd_mov, qtd_prod=base.qtd_prod, \n"
                    + "       qtd_prod_ex=base.qtd_prod_ex, qtd_calc=base.qtd_calc, qtd_calc_ex=base.qtd_calc_ex, valor_real=base.valor_real, valor_moeda=base.valor_moeda, \n"
                    + "       destino_mov=base.destino_mov, complemento_mov=base.complemento_mov, registro_mov=base.registro_mov, volume=base.volume, usuario_mov=base.usuario_mov, \n"
                    + "       modo_mov=base.modo_mov, total_mov=base.total_mov, sistema_mov=base.sistema_mov, stmovimento=base.stmovimento\n"
                    + "       FROM movproduto as base\n"
                    + " WHERE base.idmovtemp = w.id_mov ");
            pst1.execute();
            System.out.println("update \n ----------------------------------------------------------\n OK ");
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" INSERT INTO movprodutobase  (\n"
                    + "             id_prod_ent, data_mov, nota_mov, sistema_mov, qtd_mov, \n"
                    + "            qtd_prod, qtd_prod_ex, qtd_calc, qtd_calc_ex, valor_real, valor_moeda, \n"
                    + "            destino_mov, complemento_mov, registro_mov, stmovimento, volume, \n"
                    + "            usuario_mov, modo_mov, total_mov,stsaldo)\n"
                    + "    SELECT  id_prod_ent, data_mov, nota_mov, sistema_mov, qtd_mov, \n"
                    + "       qtd_prod, qtd_prod_ex, qtd_calc, qtd_calc_ex, valor_real, valor_moeda, \n"
                    + "       destino_mov, complemento_mov, registro_mov, stmovimento, volume, \n"
                    + "       usuario_mov, modo_mov, total_mov,1\n"
                    + "  FROM movproduto"
                    + " WHERE  nota_mov='" + busca + "' and idmovtemp is null and stmovimento=1 and intmodotbl=1");//// status_mov='ATIVO' and

            pst.execute();
            System.out.println("insert \n -------------------------------------------------------\n OK ");
        } catch (SQLException ex) {
            System.out.println("Interface.MovimentoCadastroJIF.MovimentoBancoInsert  -- erro " + ex);
            JOptionPane.showMessageDialog(null, "Erro ao MovimentoBancoInsert() . \\n" + ex);
        }
        conex.desconecta();
    }

    public void CarregaFormulario() {
        LimparTabela(); // aqui limpa a tabela
        System.err.println("CarregaFormulario inicio");
        if (MinhaIdNota == "novo") {
            System.err.println("CarregaFormulario  novo");
            SelecionarNaturezaInt = 0;
            jButton3.setEnabled(false);
            AtualizarTudo();
            CarregaUltimo();
//            LimpaTransporte();
            LimpaSistema2();
//            PreencherFornecedor();
//            PreencherNatureza();
            jComboBox_modalidade.setEnabled(false);
            jComboBox_transportadora.setEnabled(false);
            jLabel10.setEnabled(false);
            jLabel24.setEnabled(false);
            jLabel_exibe_transporte.setText(null);
            MinhaNatureza = "";
            jLabel_Status_Visualizar.setText("      ");
        } else {
            System.err.println("CarregaFormulario editar");
//            SelecionarNaturezaInt=1;
            CarregaSistema();
//            
            jButton3.setEnabled(true);

        }
        this.setTitle("Cadastro Movimento [ " + MinhaNatureza + " ] Id Referencia : " + id_referencia);
//        PreencherTabela();
        this.jTabbedPane1.setSelectedIndex(0);
//        PreencherMeuTotal();
//           JOptionPane.showMessageDialog(rootPane, id_referencia);
        System.err.println("CarregaFormulario fim");
    }

    public void CarregaSistema() {
        System.out.println("-------- CarregaSistema ---------- " + MinhaIdNota);
        conex.conexao();
        conex.executaSql2("SELECT  id_referencianatureza,id_natureza,stnota,id_nota, id_referencianota,nota_observacao,desc_natureza,tipo_natureza,"
                + "ecft_nome,ecft_tipo,nota_documento,nota_situacao,nota_nota,nota_chave,nota_hora,nota_data,"
                + "modalidade, transportadora, motorista, placa, \n"
                + "       uf, quantidade, especie, numeracao, pesobruto, pesoliquido\n"
                + " FROM NOTA \n"
                + "inner join ecft on fornecedorint=sis_ecft \n"
                + "inner join natureza on naturezaint=id_referencianatureza\n"
                + "where id_nota is not null and id_nota !=0 and "
                + "id_nota ='" + MinhaIdNota + "' order by id_nota asc");
        try {
            conex.rs.last();
            String DescNatureza = (conex.rs.getString("desc_natureza"));
            String IdNatureza = (conex.rs.getString("id_referencianatureza"));
//            DescNatureza = IdNatureza + " " + DescNatureza;
            DescNatureza = DescNatureza + " | " + IdNatureza;
            MinhaNaturezaInt = conex.rs.getInt("id_natureza");
            id_nota = conex.rs.getInt("id_nota");
            id_referencia = conex.rs.getInt("id_referencianota");
            jTextArea_Observacao.setText(conex.rs.getString("nota_observacao"));
            MinhaNatureza = conex.rs.getString("tipo_natureza");
            jComboBox_Natureza.setSelectedItem(DescNatureza);
            jComboBox_Tipo_Fornecedor.setSelectedItem(conex.rs.getString("ecft_tipo"));
            jComboBox_Fornecedor.setSelectedItem(conex.rs.getString("ecft_nome"));
            System.out.println("Interface.MovimentoCadastroJIF.CarregaSistema()" + conex.rs.getString("ecft_nome"));
            jComboBox_Documento.setSelectedItem(conex.rs.getString("nota_documento"));
            jComboBox_Situacao.setSelectedItem(conex.rs.getString("nota_situacao"));
            String nota_situacao_Visualizar = (conex.rs.getString("nota_situacao"));
            if (nota_situacao_Visualizar.equals("7-ENTRADA") | nota_situacao_Visualizar.equals("ENTRADA")) {
                jLabel_Status_Visualizar.setText("      ");
            } else {
                jLabel_Status_Visualizar.setText("Status : " + conex.rs.getString("nota_situacao"));
            }

            jTextField_Nota.setText(conex.rs.getString("nota_nota"));
            jTextField_Chave_Nota.setText(conex.rs.getString("nota_chave"));// 
            jTextField_Hora.setText(conex.rs.getString("nota_hora"));
            String insereData = (conex.rs.getString("nota_data"));

            try {
                data = formato.parse(insereData);
            } catch (ParseException exd) {
                System.out.println("erro data " + exd);
            }
            jDateChooser_Data_Nota.setDate(data);
//            CarregaTransporte();
            jComboBox_modalidade.setSelectedItem((conex.rs.getString("modalidade")));
            jComboBox_transportadora.setSelectedItem(conex.rs.getString("transportadora"));
            String placa = (conex.rs.getString("placa"));
            if (placa == ("   -    ")) {
                vol_placa.setText(null);
            } else {
                vol_placa.setText(placa);
            }
            String uf = (conex.rs.getString("uf"));
            if (uf == ("   -    ")) {
                vol_uf.setText(null);
            } else {
                vol_uf.setText(uf);
            }
            vol_motorista.setText(conex.rs.getString("motorista"));
            vol_quantidade.setText(conex.rs.getString("quantidade"));
            vol_especie.setText(conex.rs.getString("especie"));
            vol_numeracao.setText(conex.rs.getString("numeracao"));
            vol_peso_bruto.setText(conex.rs.getString("pesobruto"));
            vol_peso_liquido.setText(conex.rs.getString("pesoliquido"));

        } catch (SQLException ex) {
            System.out.println("erro  CarregaSistema   " + ex);
//            JOptionPane.showMessageDialog(rootPane, "erro" + ex);
//            LimpaSistema();
        }
//        JOptionPane.showMessageDialog(rootPane, id_referencia);
        conex.desconecta();
        this.setTitle("Cadastro Movimento [ " + MinhaNatureza + " ] Id Referencia : " + id_referencia);
    }

    public void LimpaSistema() {

        jTextField_Nota.setText(null);
        jTextField_Chave_Nota.setText(null);
        jTextField_Hora.setText(null);
//            data = formato.parse(null);
        data = null;
        jDateChooser_Data_Nota.setDate(data);
        LimpaTransporte();

    }

    public void LimpaTransporte() {
        jComboBox_modalidade.setSelectedItem("0-NÃO DEFINIDO");
        jComboBox_transportadora.setSelectedItem("NÃO DEFINIDO");
        vol_placa.setText(null);
        vol_uf.setText(null);
        vol_motorista.setText("");
        vol_quantidade.setText("");
        vol_especie.setText("");
        vol_numeracao.setText("");
        vol_peso_bruto.setText("");
        vol_peso_liquido.setText("");
    }

    public void LimpaSistema2() {

        jTextField_Chave_Nota.setText(null);
        jTextField_Hora.setText(null);
        jTextField_Nota.setText(null);
        data = null;
        jDateChooser_Data_Nota.setDate(data);
        LimpaTransporte();
        jLabel_Informacoes.setText("");
        System.out.println("LimpaSistema2  -- ");
    }

    public void CarregaUltimo() {
        conex.conexao();
        conex.executaSql2("SELECT  id_referencianota  FROM NOTA where id_referencianota is not null and id_referencianota !=0 order by id_referencianota asc");
        try {
            conex.rs.last();
            id_referencia = conex.rs.getInt("id_referencianota");
            id_referencia = id_referencia + 1;
            System.out.println("Interface.Movimento.MovimentoJIFAlterado.CarregaUltimo() \n " + id_referencia);
        } catch (SQLException ex) {
            id_referencia = 1;
        }
        conex.desconecta();
    }

    public void InsereDadosNota() {
        if (jDateChooser_Data_Nota.getDate() == (null)) {
            MinhaData = ("");
        } else {
            data = jDateChooser_Data_Nota.getDate();
            MinhaData = (formato.format(data));
        }
        beans.setId_referencia(id_referencia);
        beans.setNota_operacao(MinhaNatureza);
        beans.setNota_registro(Principal.jLabel_Data.getText() + " " + Principal.jLabel_Hora.getText());
        beans.setNota_status(1);
        beans.setNota_total(MeuTotal); // ver total
        beans.setNota_usuario(Principal.jLabelNomeUsuario.getText());
        beans.setNota_data(MinhaData);
//        beans.setNatureza((String) jComboBox_Natureza.getSelectedItem());//
        String ConvertNatureza = "" + jComboBox_Natureza_Int.getSelectedItem();
        MinhaNaturezaInt = (Integer.parseInt(ConvertNatureza));
        beans.setNatureza(MinhaNaturezaInt);
        beans.setNota_documento((String) jComboBox_Documento.getSelectedItem());
//        beans.setNota_fornecedor((String) jComboBox_Fornecedor.getSelectedItem());
        String MeuFornecedorString = "" + jComboBox_Fornecedor_Int.getSelectedItem();
        MeuFornecedorInt = Integer.parseInt(MeuFornecedorString);
        beans.setNota_fornecedor(MeuFornecedorInt);
        MinhaNatureza = "" + jComboBox_Natureza_Tipo.getSelectedItem();
        if (MinhaNatureza.equals("ENTRADA")) {
            beans.setNota_situacao(("ENTRADA"));
        } else {
            beans.setNota_situacao((String) jComboBox_Situacao.getSelectedItem());
        }
        beans.setNota_chave(jTextField_Chave_Nota.getText());
        beans.setNota_nota(jTextField_Nota.getText());
        beans.setNota_hora(jTextField_Hora.getText());
        jTextArea_Observacao.setText(jTextArea_Observacao.getText().toUpperCase());
        beans.setNota_observacao(jTextArea_Observacao.getText());

        beans.setModalidade("" + jComboBox_modalidade.getSelectedItem());
        beans.setMotorista(vol_motorista.getText());
        beans.setTransportadora("" + jComboBox_transportadora.getSelectedItem());
        beans.setPlaca(vol_placa.getText());
        beans.setPesoliquido(vol_peso_liquido.getText());
        beans.setPesobruto(vol_peso_bruto.getText());
        beans.setUf(vol_uf.getText());
        beans.setEspecie(vol_especie.getText());
        beans.setNumeracao(vol_numeracao.getText());
        beans.setQuantidade(vol_quantidade.getText());
        String IntTransportadora = "" + jComboBox_transportadoraInt.getSelectedItem();
        beans.setMotoristaint(Integer.parseInt(IntTransportadora));
        String IntEmpresa = menu.jLabel_Empresa.getText();
        beans.setEmpresaint(Integer.parseInt(IntEmpresa));
        beans.setDatavariavel(jTextField_data_variavel.getText());
    }

    public void AtualizarTudo() {
//        PreencherFornecedor();
//        PreencherNatureza();
//        PreencherTransporte();
        LimparTabela();
        SelecaoReferencia_Movimento = null;
        limpaCamposPalete();
//        jLabel_preenche_texto.setText("");
        BloqueiaCamposPalete();
    }

    private void LimparTabela() {
        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{};
        ModeloTabela modelo = new ModeloTabela(dados, colunas);
        jTablePalete.setModel(modelo);
        jTableListaProduto_Nota.setModel(modelo);
        jTableListaProduto_Complemento.setModel(modelo);
        jTableListaProduto_Palete_Produto.setModel(modelo);
    }

    private void LimparTabelaPalete() {
        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{};
        ModeloTabela modelo = new ModeloTabela(dados, colunas);
        jTablePalete.setModel(modelo);
//        jTableListaProduto_Nota.setModel(modelo);
//        jTableListaProduto_Complemento.setModel(modelo);
//        jTableListaProduto_Palete_Produto.setModel(modelo);
    }

    public void PreencherFornecedor() {
        String busca_fornecedor = (String) jComboBox_Tipo_Fornecedor.getSelectedItem();
        String busca_tudo = "FORN/CLIE";
//        if (jLabel_OS.getText() == "Novo") {
//            jComboBox_Fornecedor.removeAllItems();
//        } else {
//        }
        conex_Fornecedor.conexao();
        if (busca_fornecedor == "FORN/CLIE") {
            conex_Fornecedor.executaSql("select * from ecft where ecft_tipo != 'EMPRESA' and stecft=1 order by ecft_id asc");
        } else if (busca_fornecedor == "OUTRO") {
            conex_Fornecedor.executaSql("select * from ecft where ecft_tipo like '" + busca_fornecedor + "' and stecft=1 order by ecft_id asc");
        } else {
            conex_Fornecedor.executaSql("select * from ecft where ecft_tipo like '" + busca_fornecedor + "'or ecft_tipo like '" + busca_tudo + "' and stecft=1 order by ecft_id asc");
        }
        try {
            conex_Fornecedor.rs.first();
            jComboBox_Fornecedor.removeAllItems();
            jComboBox_Fornecedor.addItem(" ");
            jComboBox_Fornecedor_Int.removeAllItems();
            jComboBox_Fornecedor_Int.addItem("0");
            do {
                jComboBox_Fornecedor.addItem(conex_Fornecedor.rs.getString("ecft_nome"));
                jComboBox_Fornecedor_Int.addItem(conex_Fornecedor.rs.getString("sis_ecft"));
                jComboBox_Fornecedor.setBackground(Color.GRAY);
            } while (conex_Fornecedor.rs.next());
        } catch (SQLException ex) {
            jComboBox_Fornecedor.setBackground(Color.red);
        }
//        jComboBox_Fornecedor.setSelectedItem(" ");
        conex_Fornecedor.desconecta();
        jComboBox_Fornecedor.setBackground(Color.yellow);

    }

    public void PreencherMeuTotal() {
        String MeuValor;
        int MinhaOs = id_referencia;
        System.err.println("PreencherMeuTotal()                  INICIO ");
        conex_MeuTotal.conexao();

        conex_MeuTotal.executaSql("SELECT sum(qtd_prod) as qtd_prod , sum(qtd_calc) as calc_mov ,sum(valor_real*qtd_prod ) as valor_real ,nota_mov  from movproduto  where nota_mov='" + MinhaOs + "' and stmovimento = 1 and modo_mov='1' GROUP BY   movproduto.nota_mov order by nota_mov asc");
        try {
            conex_MeuTotal.rs.first();
            MeuTotal = (conex_MeuTotal.rs.getString("qtd_prod"));
            MeuValor = (conex_MeuTotal.rs.getString("valor_real"));
            System.err.println("PreencherMeuTotal()                  INICIO " + MeuValor);
            Double num4 = Double.parseDouble(String.valueOf(MeuValor));
            BigDecimal df = new BigDecimal(num4);
            NumberFormat nf = NumberFormat.getCurrencyInstance();
            nf.setMinimumFractionDigits(2);
            nf.setMaximumFractionDigits(2);
            String FormatoReal = nf.format(df);

            Double num4a = Double.parseDouble(String.valueOf(MeuTotal));
            BigDecimal dfa = new BigDecimal(num4a);
            NumberFormat nfa = NumberFormat.getInstance();
            nfa.setMinimumFractionDigits(3);
            nfa.setMaximumFractionDigits(3);
            String FormatoReala = nfa.format(dfa);
            FormatoReala = FormatoReala.replace(",0000", "");

            jLabel_Informacoes.setText("<html><b> Total " + FormatoReala + "  </b><br>    " + FormatoReal + "</html>");
            System.err.println("PreencherMeuTotal()                  CERTO ");
            System.err.println(" Total   -           FormatoReala" + FormatoReala + "    FormatoReal    " + FormatoReal);
            MeuTotal = FormatoReal;
            System.err.println(" MeuTotal    -     FormatoReal          -        " + FormatoReal);
        } catch (SQLException ex) {
            System.err.println("PreencherMeuTotal()                  ERRO ");
            jLabel_Informacoes.setText(" Total " + "  0    " + "R$ 0,00");
            MeuTotal = "0";
        }
        conex_MeuTotal.desconecta();

    }

    public void PreencherNatureza() {

        conex_Natureza.conexao();
        conex_Natureza.executaSql("SELECT id_natureza, id_referencianatureza, tipo_natureza, desc_natureza, registro_natureza, \n"
                + "       usuario_natureza\n"
                + "  FROM natureza  where stnat=1  order by id_referencianatureza asc");
        try {
            conex_Natureza.rs.first();
            jComboBox_Natureza.removeAllItems();
            jComboBox_Natureza.addItem(" ");
            jComboBox_Natureza_Int.removeAllItems();
            jComboBox_Natureza_Int.addItem("0");
            jComboBox_Natureza_Tipo.removeAllItems();
            jComboBox_Natureza_Tipo.addItem("");
            do {
                String DescNatureza = (conex_Natureza.rs.getString("desc_natureza"));
                String IdNatureza = (conex_Natureza.rs.getString("id_referencianatureza"));
                String TipoNatureza = (conex_Natureza.rs.getString("tipo_natureza"));
                DescNatureza = DescNatureza + " | " + IdNatureza;
                jComboBox_Natureza.addItem(DescNatureza);
                jComboBox_Natureza_Int.addItem(IdNatureza);
                jComboBox_Natureza_Tipo.addItem(TipoNatureza);
                jComboBox_Natureza.setBackground(Color.LIGHT_GRAY);
            } while (conex_Natureza.rs.next());
        } catch (SQLException ex) {
            jComboBox_Natureza.setBackground(Color.red);
        }
        conex_Natureza.desconecta();
        jComboBox_Natureza.setSelectedItem(" ");
        jComboBox_Natureza.setBackground(Color.yellow);
    }

    public void PreencherTransporte() {
        ComboFocu = 0;
        conex_Transporte.conexao();
        conex_Transporte.executaSql("select * from ecft where ecft_tipo = 'TRANSPORTE' and stecft=1 order by ecft_id asc");
        try {
            conex_Transporte.rs.first();
            jComboBox_transportadora.removeAllItems();
            jComboBox_transportadoraInt.removeAllItems();
//            jComboBox_transportadora.addItem("NÃO DEFINIDO");
//            jComboBox_transportadoraInt.addItem("0");
            do {
                jComboBox_transportadora.addItem(conex_Transporte.rs.getString("ecft_nome"));
                jComboBox_transportadoraInt.addItem(conex_Transporte.rs.getString("sis_ecft"));
                jComboBox_transportadora.setBackground(Color.GRAY);
            } while (conex_Transporte.rs.next());
            System.out.println("PreencherTransporte ok ");
            ComboFocu = 1;
        } catch (SQLException ex) {
            jComboBox_transportadora.setBackground(Color.red);
            System.out.println("PreencherTransporte erro " + ex);
        }
        conex_Transporte.desconecta();
        jComboBox_transportadora.setSelectedItem("NÃO DEFINIDO");
        jComboBox_transportadoraInt.setSelectedItem("0");
    }

    public void RecebeIdNota(String IdNota) {
        MinhaIdNota = IdNota;
//        JOptionPane.showMessageDialog(rootPane, MinhaIdNota);
    }

    public void RecebeReferenciaMovimento(String ReferenciaMovi) {
        Referencia_Movimento = ReferenciaMovi;
//        JOptionPane.showMessageDialog(rootPane, Referencia_Movimento); jComboBox_Situacao
    }

    public void RecebeDescricaoBusca(String MinhaBusca) {
        jTextField_Busca_Produto_Nota.setText(MinhaBusca);
    }

    public void ManipulaData() {
        String MenuMinhaData;
        Date dataSistema = jDateChooser_Data_Nota.getDate();
        if (dataSistema == null) {
        } else {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
            MenuMinhaData = (formato.format(dataSistema));
            jTextField_data_variavel.setText(MenuMinhaData);
            System.out.println("ManipulaData   " + MenuMinhaData);
        }
    }

    public void InsereNaTabela() {
        conex.conexao();
        try {
            java.sql.PreparedStatement pstUpdate = conex.con.prepareStatement("TRUNCATE TABLE paletetemp RESTART IDENTITY;");
            pstUpdate.execute();
//            JOptionPane.showMessageDialog(null, "OK");
        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "ERRO" + ex);
        }
        try {
            java.sql.PreparedStatement pstUpdate = conex.con.prepareStatement("INSERT INTO paletetemp(\n"
                    + "             pctpad, lastro, altura, pctavu, totpct, unavu, totparc, \n"
                    + "            mov_palete, qtd_palete, nota_palete, verifica_principal, stpalete, \n" /// status_palete,
                    + "            usuario_palete, registro_palete, id_palete)\n"
                    + "    SELECT  pctpad, lastro, altura, pctavu, totpct, unavu, totparc, \n"
                    + "       mov_palete, qtd_palete, nota_palete, '0',  stpalete, \n"
                    + "       usuario_palete, registro_palete, id_palete\n"
                    + "  FROM palete where mov_palete ='" + SelecaoReferencia_Movimento + "' and stpalete=1 order by idlinha asc ");
            pstUpdate.execute();// '" + MinhaOs + "' 
            //  JOptionPane.showMessageDialog(null, "OK");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO insere" + ex);
        }

        conex.desconecta();
    }

    
    
      public void CarregaSistemaRepeteUltimo() {
        conex.conexao();
        conex.executaSql2("SELECT  id_referencianatureza,id_natureza,stnota,id_nota, id_referencianota,nota_observacao,desc_natureza,tipo_natureza,"
                + "ecft_nome,ecft_tipo,nota_documento,nota_situacao,nota_nota,nota_chave,nota_hora,nota_data,"
                + "modalidade, transportadora, motorista, placa, \n"
                + "       uf, quantidade, especie, numeracao, pesobruto, pesoliquido\n"
                + " FROM NOTA \n"
                + "inner join ecft on fornecedorint=sis_ecft \n"
                + "inner join natureza on naturezaint=id_referencianatureza\n"
                + "where id_nota is not null and id_nota !=0  " // and
                + " order by id_nota asc");//id_nota ='" + MinhaIdNota + "'
        try {
            conex.rs.last();
            String DescNatureza = (conex.rs.getString("desc_natureza"));
            String IdNatureza = (conex.rs.getString("id_referencianatureza"));
            DescNatureza = DescNatureza + " | " + IdNatureza;
            MinhaNaturezaInt = conex.rs.getInt("id_natureza");
            MinhaNatureza = conex.rs.getString("tipo_natureza");
            jComboBox_Natureza.setSelectedItem(DescNatureza);
            jComboBox_Tipo_Fornecedor.setSelectedItem(conex.rs.getString("ecft_tipo"));
            jComboBox_Fornecedor.setSelectedItem(conex.rs.getString("ecft_nome"));
            jComboBox_Documento.setSelectedItem(conex.rs.getString("nota_documento"));
            jComboBox_Situacao.setSelectedItem(conex.rs.getString("nota_situacao"));
            String nota_situacao_Visualizar = (conex.rs.getString("nota_situacao"));
            if (nota_situacao_Visualizar.equals("7-ENTRADA") | nota_situacao_Visualizar.equals("ENTRADA")) {
                jLabel_Status_Visualizar.setText("      ");
            } else {
                jLabel_Status_Visualizar.setText("Status : " + conex.rs.getString("nota_situacao"));
            }
            jTextField_Nota.setText(conex.rs.getString("nota_nota"));
            jTextField_Chave_Nota.setText(conex.rs.getString("nota_chave"));// 
            jTextField_Hora.setText(conex.rs.getString("nota_hora"));
            String insereData = (conex.rs.getString("nota_data"));
            try {
                data = formato.parse(insereData);
            } catch (ParseException exd) {
                System.out.println("erro data " + exd);
            }
            jDateChooser_Data_Nota.setDate(data);
        } catch (SQLException ex) {
            System.out.println("erro  CarregaSistema   " + ex);
        }
        conex.desconecta();
        this.setTitle("Cadastro Movimento [ " + MinhaNatureza + " ] Id Referencia : " + id_referencia);
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton_Adicionar_Produto_Nota;
    private javax.swing.JButton jButton_Atualizar;
    private javax.swing.JButton jButton_Bandeira1;
    private javax.swing.JButton jButton_Excluir_Movimento_Nota;
    public static javax.swing.JButton jButton_Fechar_Nota;
    private javax.swing.JButton jButton_Limpa_Relogio;
    private javax.swing.JComboBox<String> jComboBox_Documento;
    private javax.swing.JComboBox<String> jComboBox_Fornecedor;
    private javax.swing.JComboBox<String> jComboBox_Fornecedor_Int;
    public static javax.swing.JComboBox<String> jComboBox_Natureza;
    private javax.swing.JComboBox<String> jComboBox_Natureza_Int;
    private javax.swing.JComboBox<String> jComboBox_Natureza_Tipo;
    public static javax.swing.JComboBox<String> jComboBox_Situacao;
    private javax.swing.JComboBox<String> jComboBox_Tipo_Fornecedor;
    private javax.swing.JComboBox<String> jComboBox_modalidade;
    private javax.swing.JComboBox<String> jComboBox_transportadora;
    private javax.swing.JComboBox<String> jComboBox_transportadoraInt;
    private com.toedter.calendar.JDateChooser jDateChooser_Data_Nota;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    public static javax.swing.JLabel jLabelMeuSaldoProduto;
    public static javax.swing.JLabel jLabel_Calculado;
    public static javax.swing.JLabel jLabel_Complemento;
    private javax.swing.JLabel jLabel_Exibe_para_Palete;
    public static javax.swing.JLabel jLabel_IdMovimento;
    public static javax.swing.JLabel jLabel_IdProduto;
    private javax.swing.JLabel jLabel_Informacoes;
    public static javax.swing.JLabel jLabel_Setor;
    private javax.swing.JLabel jLabel_Status_Visualizar;
    public static javax.swing.JLabel jLabel_Texto_Produto1;
    private javax.swing.JLabel jLabel_Titulo;
    private javax.swing.JLabel jLabel_Titulo1;
    private javax.swing.JLabel jLabel_Titulo2;
    private javax.swing.JLabel jLabel_exibe_transporte;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton_ativa_Vizualizar;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableListaProduto_Complemento;
    private javax.swing.JTable jTableListaProduto_Nota;
    private javax.swing.JTable jTableListaProduto_Palete_Produto;
    private javax.swing.JTable jTablePalete;
    private javax.swing.JTextArea jTextArea_Observacao;
    public static javax.swing.JTextField jTextField_Busca_Produto_Nota;
    public static javax.swing.JTextField jTextField_Chave_Nota;
    public static javax.swing.JTextField jTextField_Fragmento_Variavel;
    private javax.swing.JTextField jTextField_Hora;
    private javax.swing.JTextField jTextField_Nota;
    public static javax.swing.JTextField jTextField_Quantidade_Nota;
    private javax.swing.JTextField jTextField_Varieavel_SaldoProduto;
    private javax.swing.JTextField jTextField_data_variavel;
    private javax.swing.JButton jbALin;
    private javax.swing.JButton jbALis;
    private javax.swing.JButton jbAdicionar;
    private javax.swing.JButton jbLimpar;
    private javax.swing.JTextField jtAlt;
    private javax.swing.JTextField jtLast;
    private javax.swing.JTextField jtPctAvu;
    private javax.swing.JTextField jtPctPad;
    private javax.swing.JTextField jtTotParc;
    private javax.swing.JTextField jtTotPct;
    private javax.swing.JTextField jtUnAvu;
    private javax.swing.JTextField vol_especie;
    private javax.swing.JTextField vol_motorista;
    private javax.swing.JTextField vol_numeracao;
    private javax.swing.JTextField vol_peso_bruto;
    private javax.swing.JTextField vol_peso_liquido;
    private javax.swing.JTextField vol_placa;
    private javax.swing.JTextField vol_quantidade;
    private javax.swing.JTextField vol_transportadora;
    private javax.swing.JTextField vol_uf;
    // End of variables declaration//GEN-END:variables
}
