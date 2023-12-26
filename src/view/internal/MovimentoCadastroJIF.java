/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
///  jComboBox_Natureza formulario jLabel_Calculado
package view.internal;

import Interface.Principal;
import Consulta.JDialogBuscaProduto;
import Consulta.JDialogComplementar;
import ModeloBeans.Beans_Nota;
import ModeloBeans.ModeloTabela;
import ConectaBanco.ConexaoBD;
import Consulta.JDialogAuxilioChave;
import ModeloDao.Dao_Nota;
import ModeloDao.Dao_Transporte;
import ModeloBeans.Beans_Transporte;
import com.toedter.calendar.JDateChooser;
import controller.MovimentoCadastroController;

import java.awt.Color;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Natureza;
import utils.ControleCores;

/**
 *
 * @author edson
 */
/// pree
public class MovimentoCadastroJIF extends javax.swing.JInternalFrame {

    ConexaoBD conex_Fornecedor = new ConexaoBD();
    ConexaoBD conex_Natureza = new ConexaoBD();
    ConexaoBD conex_MeuTotal = new ConexaoBD();
    ConexaoBD conex_Transporte = new ConexaoBD();

    ConexaoBD conex = new ConexaoBD();

    ConexaoBD conexApaga = new ConexaoBD();
    ConexaoBD conexInsere = new ConexaoBD();

    ConexaoBD conexSaldo = new ConexaoBD();

    Dao_Nota dao = new Dao_Nota();
    Beans_Nota beans = new Beans_Nota();

    Beans_Transporte beanst = new Beans_Transporte();
    Dao_Transporte daot = new Dao_Transporte();

    Principal menu;
    JDialogComplementar Complementar = new JDialogComplementar(menu, rootPaneCheckingEnabled);
    JDialogBuscaProduto BP = new JDialogBuscaProduto(menu, rootPaneCheckingEnabled);
//DialogCalculado CC = new JDialogCalculado(menu, rootPaneCheckingEnabled);
    int id_referencia;
    int id_nota;
    String MinhaNatureza;
    String MinhaIdNota;
    String SelecaoReferencia_Movimento;

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    Date data = null;

    String MeuTotal;
    int ComboFocu = 0;
    int ComboFocuNatureza = 0;

    MovimentoCadastroController movimentoCadastroController;

    /**
     * Creates new form MovimentoJIF
     */
    public MovimentoCadastroJIF() {
        movimentoCadastroController = new MovimentoCadastroController();
        initComponents();

        movimentoCadastroController.mudarCorPaineis(this);
        movimentoCadastroController.carregarNatureza(this);

    }

//    
//
//    public void BuscaProdutoDescricao() {
//        Id_Prod_Ent = (0);
//        jLabel_IdProduto.setText("0");
//        String FormatoReal = null;
//        String descricao;
//        String edicao;
//
//        String MinhaBusca = (jTextField_Busca_Produto_Nota.getText());
//        MinhaBusca = MinhaBusca.replace("Nº ", "");
//        MinhaBusca = MinhaBusca.replace("N° ", "");
//        MinhaBusca = MinhaBusca.replace("Nº", "");//  N°
//        MinhaBusca = MinhaBusca.replace("N°", "");
//        System.out.println("MinhaBusca " + MinhaBusca);
//        int Id_Prod_comparar;
//        conex.conexao();
//        conex.executaSql("select * from produto inner join unidade on  idunid=id_unidade where   (coalesce((sis_prod)) ||' '||coalesce((tipo_prod))||' '||coalesce((nome_prod))||' '||coalesce((edicao_prod))) ilike '%" + MinhaBusca + "%' and stprod=1  order by id_prod asc");
//        try {
//            conex.rs.last();/// un_prod
//            String SelecaaoSaldo = (conex.rs.getString("saldo_prod"));
//            System.out.println("Interface.Movimento.MovimentoJIFAlterado.BuscaProdutoId()    A\n " + SelecaaoSaldo);
//            if (SelecaaoSaldo == null | SelecaaoSaldo.equals("null")) {
//                SelecaaoSaldo = "0";
//                System.out.println("saldo null");
//            } else {
//                SelecaaoSaldo = SelecaaoSaldo.replace(".", ".").replace(",", ".");
//                System.out.println("saldo certo");
//                Double num4 = Double.parseDouble(String.valueOf(SelecaaoSaldo));
//                BigDecimal df = new BigDecimal(num4);
//                NumberFormat nf = NumberFormat.getInstance();
////                 nf.setMinimumFractionDigits(3);
//                nf.setMaximumFractionDigits(4);
//                FormatoReal = nf.format(df);
//            }
//
//            System.out.println("BuscaProdutoDescricao OK " + " " + FormatoReal);
//            String SelecaaoUnid = (conex.rs.getString("sigla_unidade"));
//            jLabelMeuSaldoProduto.setText(FormatoReal + " / " + SelecaaoUnid);
//            ValorMoeda = (conex.rs.getString("valor_prod_ex"));
//            ValorReal = (Double.parseDouble(conex.rs.getString("valor_prod")));
//            Id_Prod_Ent = (Integer.parseInt(conex.rs.getString("sis_prod")));
//            edicao = conex.rs.getString("edicao_prod");
//
//            if (edicao.equals(null) | edicao.equals("") | edicao.equals(" ")) {
//                edicao = "";
//            } else {
//                edicao = " N° " + edicao;
//            }
//
//            descricao = " " + conex.rs.getString("tipo_prod") + " " + conex.rs.getString("nome_prod") + edicao;
//            descricao = descricao.toUpperCase();
//            System.out.println("BuscaProdutoDescricao OK " + " " + descricao);
//            conex.rs.first();
//            Id_Prod_comparar = (Integer.parseInt(conex.rs.getString("sis_prod")));
//            if (Id_Prod_comparar == Id_Prod_Ent) {
//                jLabel_IdProduto.setText(String.valueOf(Id_Prod_Ent));
//                jTextField_Busca_Produto_Nota.setText(descricao);
//                System.out.println(" BuscaProdutoDescricao() " + "É igual");
//            } else {
//                System.out.println(" BuscaProdutoDescricao() " + "Não é igual");
//                BP.recebe_nome(MinhaBusca);
//                jLabelMeuSaldoProduto.setText("");
//                BP.setVisible(true);
//            }
//        } catch (SQLException ex) {
//            System.out.println(" BuscaProdutoDescricao() " + ex);
//
//        }
//        conex.desconecta();
//
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnPrincipal = new javax.swing.JPanel();
        pnTopo = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cbNatureza = new javax.swing.JComboBox<>();
        dataNota = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNota = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtChave = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cbCliente = new javax.swing.JComboBox<>();
        btnDataAtual = new javax.swing.JButton();
        btnHoraAtual = new javax.swing.JButton();
        txtHora = new javax.swing.JTextField();
        lblInformacoes = new javax.swing.JLabel();
        jLabel_Status_Visualizar = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnDados = new javax.swing.JPanel();
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
        pnTransporte = new javax.swing.JPanel();
        jLabel_Titulo1 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jComboBox_modalidade = new javax.swing.JComboBox<>();
        jComboBox_transportadora = new javax.swing.JComboBox<>();
        vol_motorista = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        vol_uf = new javax.swing.JTextField();
        vol_placa = new javax.swing.JTextField();
        jLabel_Titulo2 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        vol_quantidade = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        vol_especie = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        vol_peso_liquido = new javax.swing.JTextField();
        vol_peso_bruto = new javax.swing.JTextField();
        vol_numeracao = new javax.swing.JTextField();
        pnInformacao = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea_Observacao = new javax.swing.JTextArea();
        btnExcluir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        jRadioButton_ativa_Vizualizar = new javax.swing.JRadioButton();
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

        pnPrincipal.setBackground(new java.awt.Color(0, 255, 204));

        pnTopo.setBackground(new java.awt.Color(0, 255, 204));
        pnTopo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Natureza da Operação :");

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Cliente / Fornecedor:");

        cbNatureza.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbNaturezaItemStateChanged(evt);
            }
        });
        cbNatureza.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cbNaturezaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                cbNaturezaFocusLost(evt);
            }
        });
        cbNatureza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNaturezaActionPerformed(evt);
            }
        });
        cbNatureza.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbNaturezaKeyPressed(evt);
            }
        });

        dataNota.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                dataNotaHierarchyChanged(evt);
            }
        });
        dataNota.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                dataNotaAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                dataNotaAncestorMoved(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        dataNota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dataNotaMouseClicked(evt);
            }
        });
        dataNota.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dataNotaPropertyChange(evt);
            }
        });
        dataNota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dataNotaKeyPressed(evt);
            }
        });
        dataNota.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                dataNotaVetoableChange(evt);
            }
        });

        jLabel3.setText("Data :");

        jLabel4.setText("Hora :");

        txtNota.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNotaFocusLost(evt);
            }
        });
        txtNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNotaActionPerformed(evt);
            }
        });
        txtNota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNotaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNotaKeyReleased(evt);
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Nota:");

        txtChave.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtChaveFocusLost(evt);
            }
        });
        txtChave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtChaveActionPerformed(evt);
            }
        });
        txtChave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtChaveKeyPressed(evt);
            }
        });

        jLabel7.setText("Chave :");

        cbCliente.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbClienteItemStateChanged(evt);
            }
        });
        cbCliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cbClienteFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                cbClienteFocusLost(evt);
            }
        });
        cbCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbClienteActionPerformed(evt);
            }
        });
        cbCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbClienteKeyPressed(evt);
            }
        });

        btnDataAtual.setForeground(new java.awt.Color(255, 51, 51));
        btnDataAtual.setToolTipText("Atualizar Campos");
        btnDataAtual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataAtualActionPerformed(evt);
            }
        });
        btnDataAtual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDataAtualKeyPressed(evt);
            }
        });

        btnHoraAtual.setForeground(new java.awt.Color(255, 51, 51));
        btnHoraAtual.setToolTipText("Limpa Relogio");
        btnHoraAtual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoraAtualActionPerformed(evt);
            }
        });
        btnHoraAtual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnHoraAtualKeyPressed(evt);
            }
        });

        txtHora.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtHoraFocusLost(evt);
            }
        });
        txtHora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoraActionPerformed(evt);
            }
        });
        txtHora.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtHoraKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHoraKeyReleased(evt);
            }
        });

        lblInformacoes.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblInformacoes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInformacoes.setText(" ");
        lblInformacoes.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout pnTopoLayout = new javax.swing.GroupLayout(pnTopo);
        pnTopo.setLayout(pnTopoLayout);
        pnTopoLayout.setHorizontalGroup(
            pnTopoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTopoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnTopoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(pnTopoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnTopoLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(pnTopoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbNatureza, 0, 349, Short.MAX_VALUE)
                            .addComponent(cbCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnTopoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnHoraAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(btnDataAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnTopoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnTopoLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnTopoLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)))
                        .addGroup(pnTopoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dataNota, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnTopoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtChave, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblInformacoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnTopoLayout.setVerticalGroup(
            pnTopoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTopoLayout.createSequentialGroup()
                .addGroup(pnTopoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnTopoLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(pnTopoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbNatureza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(7, 7, 7)
                        .addGroup(pnTopoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)))
                    .addGroup(pnTopoLayout.createSequentialGroup()
                        .addGroup(pnTopoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dataNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnDataAtual, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE))
                        .addGap(9, 9, 9)
                        .addGroup(pnTopoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnTopoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnHoraAtual, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnTopoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(txtChave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lblInformacoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnTopoLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnDataAtual, btnHoraAtual, dataNota, jLabel3, jLabel4, txtHora});

        jLabel_Status_Visualizar.setText("Status :");
        jLabel_Status_Visualizar.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTabbedPane1.setFont(new java.awt.Font("Fira Sans", 1, 13)); // NOI18N

        pnDados.setBackground(new java.awt.Color(0, 255, 204));

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

        javax.swing.GroupLayout pnDadosLayout = new javax.swing.GroupLayout(pnDados);
        pnDados.setLayout(pnDadosLayout);
        pnDadosLayout.setHorizontalGroup(
            pnDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDadosLayout.createSequentialGroup()
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
            .addGroup(pnDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnDadosLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane4)
                    .addContainerGap()))
        );

        pnDadosLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton_Adicionar_Produto_Nota, jButton_Excluir_Movimento_Nota, jButton_Fechar_Nota});

        pnDadosLayout.setVerticalGroup(
            pnDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField_Busca_Produto_Nota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel_Texto_Produto1)
                        .addComponent(jTextField_Quantidade_Nota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel20)
                        .addComponent(jButton_Adicionar_Produto_Nota, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_Fechar_Nota, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_Excluir_Movimento_Nota, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelMeuSaldoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(197, Short.MAX_VALUE))
            .addGroup(pnDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnDadosLayout.createSequentialGroup()
                    .addGap(37, 37, 37)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pnDadosLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton_Adicionar_Produto_Nota, jButton_Excluir_Movimento_Nota, jButton_Fechar_Nota, jLabel20, jLabelMeuSaldoProduto, jLabel_Texto_Produto1, jTextField_Busca_Produto_Nota, jTextField_Quantidade_Nota});

        jTabbedPane1.addTab("Dados", pnDados);

        pnTransporte.setBackground(new java.awt.Color(0, 255, 204));

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

        vol_motorista.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                vol_motoristaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                vol_motoristaFocusLost(evt);
            }
        });

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Motorista :");

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("UF");

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

        jLabel_Titulo2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel_Titulo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Titulo2.setText("Volumes transportados");
        jLabel_Titulo2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Quantidade");

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Espécie");

        vol_especie.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                vol_especieFocusLost(evt);
            }
        });

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Numeração");

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Peso Bruto");

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Peso Liquido");

        vol_numeracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vol_numeracaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnTransporteLayout = new javax.swing.GroupLayout(pnTransporte);
        pnTransporte.setLayout(pnTransporteLayout);
        pnTransporteLayout.setHorizontalGroup(
            pnTransporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTransporteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnTransporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_Titulo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_Titulo2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnTransporteLayout.createSequentialGroup()
                        .addGroup(pnTransporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnTransporteLayout.createSequentialGroup()
                                .addGroup(pnTransporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                    .addComponent(vol_quantidade, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnTransporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(vol_especie, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnTransporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(vol_numeracao, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnTransporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(vol_peso_bruto)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnTransporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(vol_peso_liquido, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnTransporteLayout.createSequentialGroup()
                                .addGroup(pnTransporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(vol_motorista, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnTransporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(vol_placa, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnTransporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(vol_uf, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnTransporteLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox_modalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox_transportadora, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 275, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pnTransporteLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel23, jLabel25, jLabel26, jLabel27, jLabel30, vol_especie, vol_numeracao, vol_peso_bruto, vol_peso_liquido, vol_quantidade});

        pnTransporteLayout.setVerticalGroup(
            pnTransporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTransporteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_Titulo1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnTransporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(pnTransporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pnTransporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(jComboBox_transportadora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jComboBox_modalidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(pnTransporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnTransporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)
                        .addComponent(jLabel21))
                    .addGroup(pnTransporteLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnTransporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(vol_motorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vol_placa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vol_uf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_Titulo2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnTransporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnTransporteLayout.createSequentialGroup()
                        .addGroup(pnTransporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(7, 7, 7)
                        .addGroup(pnTransporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(vol_peso_bruto)
                            .addComponent(vol_peso_liquido)
                            .addComponent(vol_especie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vol_quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vol_numeracao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnTransporteLayout.createSequentialGroup()
                        .addGroup(pnTransporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel27))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(45, 45, 45))
        );

        pnTransporteLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBox_modalidade, jComboBox_transportadora, jLabel10, jLabel24});

        pnTransporteLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel19, jLabel21, jLabel28});

        jTabbedPane1.addTab("Transportadora/Volumes", pnTransporte);

        pnInformacao.setBackground(new java.awt.Color(0, 255, 204));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Observações");
        jLabel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextArea_Observacao.setColumns(20);
        jTextArea_Observacao.setRows(5);
        jTextArea_Observacao.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPane7.setViewportView(jTextArea_Observacao);

        javax.swing.GroupLayout pnInformacaoLayout = new javax.swing.GroupLayout(pnInformacao);
        pnInformacao.setLayout(pnInformacaoLayout);
        pnInformacaoLayout.setHorizontalGroup(
            pnInformacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1114, Short.MAX_VALUE)
            .addGroup(pnInformacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnInformacaoLayout.createSequentialGroup()
                    .addGap(4, 4, 4)
                    .addGroup(pnInformacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 1106, Short.MAX_VALUE))
                    .addGap(4, 4, 4)))
        );
        pnInformacaoLayout.setVerticalGroup(
            pnInformacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 226, Short.MAX_VALUE)
            .addGroup(pnInformacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnInformacaoLayout.createSequentialGroup()
                    .addGap(6, 6, 6)
                    .addComponent(jLabel16)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jTabbedPane1.addTab("Informações Adicionais", pnInformacao);

        btnExcluir.setBackground(new java.awt.Color(255, 51, 0));
        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-excluir-24.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.setEnabled(false);
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-cancelar-24.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-salvar-24.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        jRadioButton_ativa_Vizualizar.setText("Ativa vizualizar");
        jRadioButton_ativa_Vizualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_ativa_VizualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnPrincipalLayout = new javax.swing.GroupLayout(pnPrincipal);
        pnPrincipal.setLayout(pnPrincipalLayout);
        pnPrincipalLayout.setHorizontalGroup(
            pnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnTopo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1)
                    .addGroup(pnPrincipalLayout.createSequentialGroup()
                        .addComponent(btnSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jRadioButton_ativa_Vizualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_Status_Visualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnPrincipalLayout.setVerticalGroup(
            pnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Status_Visualizar)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar)
                    .addComponent(btnExcluir)
                    .addComponent(jRadioButton_ativa_Vizualizar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnTopo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

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
                .addComponent(pnPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

//    public void LimpaCampoProduto() {
//        Id_Prod_Ent = 0;
//        jTextField_Busca_Produto_Nota.setText(null);
//        jTextField_Quantidade_Nota.setText(null);
//        jTextField_Busca_Produto_Nota.requestFocus();
//        jLabel_IdProduto.setText("0");
//        jLabel_Calculado.setText("");
//        jLabel_Complemento.setText("");
//        jLabel_Setor.setText("");
//        jTextField_Busca_Produto_Nota.setBackground(Color.white);
//        jTextField_Quantidade_Nota.setBackground(Color.white);
//        PreencherTabela();
//    }
//
//    public void BotaoAdicionarProduto() {
//        String s = jTextField_Busca_Produto_Nota.getText();
//        String q = jTextField_Quantidade_Nota.getText();
//        if (jLabel_IdProduto.getText().equals("0")) {
//            if (s.matches("^[0-9]*$")) {
//                jLabel_IdProduto.setText(jTextField_Busca_Produto_Nota.getText());
//            } else {
//
//            }
//        } else {
//        }
//        if (jLabel_IdProduto.getText().equals("0") | jLabel_IdProduto.getText().equals("") | jLabel_IdProduto.getText().equals(null)) { /// |jLabel_IdProduto.getText() == ""
//            JOptionPane.showMessageDialog(rootPane, "if");
//        } else {
//            BuscaProdutoId();
//            if (jTextField_Quantidade_Nota.getText().equals("")
//                    | jTextField_Quantidade_Nota.getText().equals(" ")
//                    | jTextField_Quantidade_Nota.getText().equals(null)) {
//                jTextField_Quantidade_Nota.requestFocus();
//                jTextField_Quantidade_Nota.setBackground(Color.red);
//            } else {
//                jTextField_Quantidade_Nota.setBackground(null);
//                jTextField_Quantidade_Nota.setText(jTextField_Quantidade_Nota.getText().replace(" ", ""));
//                q = jTextField_Quantidade_Nota.getText();
//                if (q.matches("^[0-9,.]*$")) {
//                    System.out.println("Interface.Movimento.MovimentoJIF.jButton_Adicionar_Produto_NotaActionPerformed()" + Referencia_Movimento);
//                    EventoCalculaQuantidade_X_Valor();
//                    BotaoSalvarMovimento();
//                    LimpaCampoProduto();
//                } else {
//                    jTextField_Quantidade_Nota.requestFocus();
//                    jTextField_Quantidade_Nota.setBackground(Color.red);
//                }
//            }
//        }
//        PreencherTabela();
//
//    }
//    public void PreencherTabela() {
//        PreencherTabelaMovimento();
//        PreencherTabelaComplemento();
//        PreencherTabelaProdutoPalete();
//    }
//    public void PreencherTabelaMovimento() {
//        jLabelMeuSaldoProduto.setText("");
//        ArrayList dados = new ArrayList();
//        String[] colunas = new String[]{"Item", "Id", "Descrição", "Unid", "Qtd", "Calc", "V.Unit", "V.Total", ""};// "Usuario", "Registro"
//        conexTabela.conexao();
//        conexTabela.executaSql2("SELECT * FROM movproduto inner join produto on id_prod_ent=sis_prod \n"
//                + " inner join unidade on idunid= id_unidade where nota_mov='" + id_referencia + "' and modo_mov='1' "
//                + "and stprod = 1 and stmovimento=1 or stmovimento=4  order by id_mov asc ");
//        try {
//            conexTabela.rs.first();
//            do {
//                String descricao;
//                String edicao;
//                String texto_movimento;
//                edicao = conexTabela.rs.getString("edicao_prod");
//                if (edicao.equals(null) | edicao.equals("") | edicao.equals(" ")) {
//                    edicao = "";
//                } else {
//                    edicao = " N° " + edicao;
//                }
//                descricao = " " + conexTabela.rs.getString("tipo_prod") + " " + conexTabela.rs.getString("nome_prod") + edicao;
//                descricao = descricao.toUpperCase();
//                texto_movimento = "" + descricao + "  " + conexTabela.rs.getString("complemento_mov") + "  " + conexTabela.rs.getString("destino_mov");;
//                dados.add(new Object[]{
//                    conexTabela.rs.getInt("id_mov"), conexTabela.rs.getInt("id_prod_ent"), texto_movimento, conexTabela.rs.getString("sigla_unidade"),
//                    conexTabela.rs.getString("qtd_prod_ex"), conexTabela.rs.getString("qtd_calc_ex"), conexTabela.rs.getString("valor_moeda"),
//                    conexTabela.rs.getString("total_mov"), conexTabela.rs.getString("sistema_mov")});
//            } while (conexTabela.rs.next());
//            System.out.println("preencher tabela ok");
//        } catch (SQLException ex) {
//            System.out.println("erro tabela\n" + ex);
//        }
//        ModeloTabela modelo = new ModeloTabela(dados, colunas);
//
//        jTableListaProduto_Nota.setModel(modelo);
//        jTableListaProduto_Nota.getColumnModel().getColumn(0).setPreferredWidth(40);
//        jTableListaProduto_Nota.getColumnModel().getColumn(0).setResizable(true);
//        jTableListaProduto_Nota.getColumnModel().getColumn(1).setPreferredWidth(40);
//        jTableListaProduto_Nota.getColumnModel().getColumn(1).setResizable(true);
//        jTableListaProduto_Nota.getColumnModel().getColumn(2).setPreferredWidth(550);
//        jTableListaProduto_Nota.getColumnModel().getColumn(2).setResizable(true);
//        jTableListaProduto_Nota.getColumnModel().getColumn(3).setPreferredWidth(60);
//        jTableListaProduto_Nota.getColumnModel().getColumn(3).setResizable(true);
//        jTableListaProduto_Nota.getColumnModel().getColumn(4).setPreferredWidth(60);
//        jTableListaProduto_Nota.getColumnModel().getColumn(4).setResizable(true);
//        jTableListaProduto_Nota.getColumnModel().getColumn(5).setPreferredWidth(60);
//        jTableListaProduto_Nota.getColumnModel().getColumn(5).setResizable(true);
//        jTableListaProduto_Nota.getColumnModel().getColumn(6).setPreferredWidth(80);
//        jTableListaProduto_Nota.getColumnModel().getColumn(6).setResizable(true);
//        jTableListaProduto_Nota.getColumnModel().getColumn(7).setPreferredWidth(120);
//        jTableListaProduto_Nota.getColumnModel().getColumn(7).setResizable(true);
//        jTableListaProduto_Nota.getColumnModel().getColumn(8).setPreferredWidth(00);
//        jTableListaProduto_Nota.getColumnModel().getColumn(8).setResizable(true);
//        jTableListaProduto_Nota.getTableHeader().setReorderingAllowed(false);
//        jTableListaProduto_Nota.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//        jTableListaProduto_Nota.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        conexTabela.desconecta();
//    }
//
//    public void PreencherTabelaComplemento() {
//
//        ArrayList dados = new ArrayList();
//        String[] colunas = new String[]{"Item", "Id", "Descrição", "Unid", "Qtd", "Calc", "V.Unit", "V.Total", ""};// "Usuario", "Registro"
//        conexTabela.conexao();
//        conexTabela.executaSql2("SELECT * FROM movproduto \n"
//                + " inner join produto on id_prod_ent=sis_prod \n"
//                + " inner join unidade on idunid= id_unidade\n"
//                + "where nota_mov='" + id_referencia + "'\n"
//                + "  and stprod = 1 and modo_mov='2'  and stmovimento=1 or stmovimento=4 order by id_mov asc ");
//        try {
//            conexTabela.rs.first();
//            do {
//                String descricao;
//                String edicao;
//                String texto_movimento;
//                edicao = conexTabela.rs.getString("edicao_prod");
//                if (edicao.equals(null) | edicao.equals("") | edicao.equals(" ")) {
//                    edicao = "";
//                } else {
//                    edicao = " N° " + edicao;
//                }
//                descricao = " " + conexTabela.rs.getString("tipo_prod") + " " + conexTabela.rs.getString("nome_prod") + edicao;
//                descricao = descricao.toUpperCase();
//                texto_movimento = "" + descricao + "  " + conexTabela.rs.getString("complemento_mov") + "  " + conexTabela.rs.getString("destino_mov");;
//                dados.add(new Object[]{
//                    conexTabela.rs.getInt("id_mov"), conexTabela.rs.getInt("id_prod_ent"), texto_movimento, conexTabela.rs.getString("sigla_unidade"),
//                    conexTabela.rs.getString("qtd_prod_ex"), conexTabela.rs.getString("qtd_calc_ex"), conexTabela.rs.getString("valor_moeda"),
//                    conexTabela.rs.getString("total_mov"), conexTabela.rs.getString("sistema_mov")});
//            } while (conexTabela.rs.next());
//            System.out.println("preencher tabela ok");
//        } catch (SQLException ex) {
//            System.out.println("erro tabela\n" + ex);
//        }
//        ModeloTabela modelo = new ModeloTabela(dados, colunas);
//
//        jTableListaProduto_Complemento.setModel(modelo);
//        jTableListaProduto_Complemento.getColumnModel().getColumn(0).setPreferredWidth(40);
//        jTableListaProduto_Complemento.getColumnModel().getColumn(0).setResizable(true);
//        jTableListaProduto_Complemento.getColumnModel().getColumn(1).setPreferredWidth(40);
//        jTableListaProduto_Complemento.getColumnModel().getColumn(1).setResizable(true);
//        jTableListaProduto_Complemento.getColumnModel().getColumn(2).setPreferredWidth(550);
//        jTableListaProduto_Complemento.getColumnModel().getColumn(2).setResizable(true);
//        jTableListaProduto_Complemento.getColumnModel().getColumn(3).setPreferredWidth(60);
//        jTableListaProduto_Complemento.getColumnModel().getColumn(3).setResizable(true);
//        jTableListaProduto_Complemento.getColumnModel().getColumn(4).setPreferredWidth(60);
//        jTableListaProduto_Complemento.getColumnModel().getColumn(4).setResizable(true);
//        jTableListaProduto_Complemento.getColumnModel().getColumn(5).setPreferredWidth(60);
//        jTableListaProduto_Complemento.getColumnModel().getColumn(5).setResizable(true);
//        jTableListaProduto_Complemento.getColumnModel().getColumn(6).setPreferredWidth(80);
//        jTableListaProduto_Complemento.getColumnModel().getColumn(6).setResizable(true);
//        jTableListaProduto_Complemento.getColumnModel().getColumn(7).setPreferredWidth(120);
//        jTableListaProduto_Complemento.getColumnModel().getColumn(7).setResizable(true);
//        jTableListaProduto_Complemento.getColumnModel().getColumn(8).setPreferredWidth(00);
//        jTableListaProduto_Complemento.getColumnModel().getColumn(8).setResizable(true);
//        jTableListaProduto_Complemento.getTableHeader().setReorderingAllowed(false);
//        jTableListaProduto_Complemento.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//        jTableListaProduto_Complemento.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        conexTabela.desconecta();
//    }
//
//    public void PreencherTabelaProdutoPalete() {
//        BloqueiaCamposPalete();
//        ArrayList dados = new ArrayList();
//        String[] colunas = new String[]{"Item", "Descrição", "Unid", "Qtd", ""};
//        conexTabela.conexao();
//        conexTabela.executaSql2(""
//                + "SELECT * FROM movproduto \n"
//                + " inner join produto on id_prod_ent=sis_prod \n"
//                + " inner join unidade on idunid= id_unidade\n"
//                + "where nota_mov='" + id_referencia + "'\n"
//                + "and modo_mov='1' and stprod = 1  and stmovimento=1 or stmovimento=4 order by id_mov asc ");
//        try {
//            conexTabela.rs.first();
//            do {
//                String descricao;
//                String edicao;
//                String texto_movimento;
//                edicao = conexTabela.rs.getString("edicao_prod");
//                if (edicao.equals(null) | edicao.equals("") | edicao.equals(" ")) {
//                    descricao = conexTabela.rs.getString("tipo_prod") + " " + conexTabela.rs.getString("nome_prod");
//                } else {
//                    descricao = conexTabela.rs.getString("tipo_prod") + " " + conexTabela.rs.getString("nome_prod") + " " + conexTabela.rs.getString("edicao_prod");
//                }
//                texto_movimento = "" + descricao + "  " + conexTabela.rs.getString("complemento_mov") + "  " + conexTabela.rs.getString("destino_mov");;
//                dados.add(new Object[]{
//                    conexTabela.rs.getInt("id_mov"), texto_movimento, conexTabela.rs.getString("sigla_unidade"),
//                    conexTabela.rs.getString("qtd_prod_ex"), conexTabela.rs.getString("sistema_mov")});
//            } while (conexTabela.rs.next()); ///// total_mov     usuario_mov
//            System.out.println("PreencherTabelaProdutoPalete ok");
//        } catch (SQLException ex) {
//            System.out.println("PreencherTabelaProdutoPalete erro " + ex);
//        }
//        ModeloTabela modelo = new ModeloTabela(dados, colunas);
//
//        jTableListaProduto_Palete_Produto.setModel(modelo);
//        jTableListaProduto_Palete_Produto.getColumnModel().getColumn(0).setPreferredWidth(40);
//        jTableListaProduto_Palete_Produto.getColumnModel().getColumn(0).setResizable(true);
//        jTableListaProduto_Palete_Produto.getColumnModel().getColumn(1).setPreferredWidth(530);
//        jTableListaProduto_Palete_Produto.getColumnModel().getColumn(1).setResizable(true);
//        jTableListaProduto_Palete_Produto.getColumnModel().getColumn(2).setPreferredWidth(60);
//        jTableListaProduto_Palete_Produto.getColumnModel().getColumn(2).setResizable(true);
//        jTableListaProduto_Palete_Produto.getColumnModel().getColumn(3).setPreferredWidth(60);
//        jTableListaProduto_Palete_Produto.getColumnModel().getColumn(3).setResizable(true);
//        jTableListaProduto_Palete_Produto.getColumnModel().getColumn(4).setPreferredWidth(00);
//        jTableListaProduto_Palete_Produto.getColumnModel().getColumn(4).setResizable(true);
//        jTableListaProduto_Palete_Produto.getTableHeader().setReorderingAllowed(false);
//        jTableListaProduto_Palete_Produto.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//        jTableListaProduto_Palete_Produto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//
//        conexTabela.desconecta();
//
//    }
//
//    public void PreencherTabelaProdutoPalete_2() {
//        String Sistema_mov_para_palete = "" + jTableListaProduto_Palete_Produto.getValueAt(jTableListaProduto_Palete_Produto.getSelectedRow(), 4);
//        System.out.println("Interface.MovimentoCadastroJIF.PreencherTabelaProdutoPalete_2()    +Sistema_mov_para_palete " + Sistema_mov_para_palete);
////        BloqueiaCamposPalete();
//        ArrayList dados = new ArrayList();
//        String[] colunas = new String[]{"Item", "Descrição", "Unid", "Qtd", ""};
//        conexTabela.conexao();
//        conexTabela.executaSql2(""
//                + "SELECT * FROM movproduto \n"
//                + " inner join produto on id_prod_ent=sis_prod \n"
//                + " inner join unidade on idunid= id_unidade\n"
//                + "where nota_mov='" + id_referencia + "'\n"
//                + " and modo_mov='1' and stprod = 1 and sistema_mov = '" + SelecaoReferencia_Movimento + "' and stmovimento=1 or stmovimento=4  order by id_mov asc ");
//        try {
//            conexTabela.rs.first();
//            do {
//                String descricao;
//                String edicao;
//                String texto_movimento;
//                edicao = conexTabela.rs.getString("edicao_prod");
//                if (edicao.equals(null) | edicao.equals("") | edicao.equals(" ")) {
//                    descricao = conexTabela.rs.getString("tipo_prod") + " " + conexTabela.rs.getString("nome_prod");
//                } else {
//                    descricao = conexTabela.rs.getString("tipo_prod") + " " + conexTabela.rs.getString("nome_prod") + " " + conexTabela.rs.getString("edicao_prod");
//                }
//                texto_movimento = "" + descricao + "  " + conexTabela.rs.getString("complemento_mov") + "  " + conexTabela.rs.getString("destino_mov");;
//                dados.add(new Object[]{
//                    conexTabela.rs.getInt("id_mov"), texto_movimento, conexTabela.rs.getString("sigla_unidade"),
//                    conexTabela.rs.getString("qtd_prod_ex"), conexTabela.rs.getString("sistema_mov")});
//            } while (conexTabela.rs.next()); ///// total_mov     usuario_mov
//            System.out.println("PreencherTabelaProdutoPalete ok   77777777777 ");
//        } catch (SQLException ex) {
//            System.out.println("PreencherTabelaProdutoPalete erro  77777777777 " + ex);
//        }
//        ModeloTabela modelo = new ModeloTabela(dados, colunas);
//
//        jTableListaProduto_Palete_Produto.setModel(modelo);
//        jTableListaProduto_Palete_Produto.getColumnModel().getColumn(0).setPreferredWidth(40);
//        jTableListaProduto_Palete_Produto.getColumnModel().getColumn(0).setResizable(true);
//        jTableListaProduto_Palete_Produto.getColumnModel().getColumn(1).setPreferredWidth(230);
//        jTableListaProduto_Palete_Produto.getColumnModel().getColumn(1).setResizable(true);
//        jTableListaProduto_Palete_Produto.getColumnModel().getColumn(2).setPreferredWidth(60);
//        jTableListaProduto_Palete_Produto.getColumnModel().getColumn(2).setResizable(true);
//        jTableListaProduto_Palete_Produto.getColumnModel().getColumn(3).setPreferredWidth(60);
//        jTableListaProduto_Palete_Produto.getColumnModel().getColumn(3).setResizable(true);
//        jTableListaProduto_Palete_Produto.getColumnModel().getColumn(4).setPreferredWidth(00);
//        jTableListaProduto_Palete_Produto.getColumnModel().getColumn(4).setResizable(true);
//        jTableListaProduto_Palete_Produto.getTableHeader().setReorderingAllowed(false);
//        jTableListaProduto_Palete_Produto.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//        jTableListaProduto_Palete_Produto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//
//        conexTabela.desconecta();
//
//    }
//
//    public void EventoUptadeMovimento() {
//        conex.conexao();
//        try {
//            if (MinhaNatureza == "ENTRADA") {
//                java.sql.PreparedStatement pst = conex.con.prepareStatement(" UPDATE movproduto SET qtd_mov=qtd_prod  WHERE sistema_mov='" + Referencia_Movimento + "'");
//                pst.execute();
//            } else if (MinhaNatureza == "SAIDA") {
//                java.sql.PreparedStatement pst = conex.con.prepareStatement(" UPDATE movproduto   SET qtd_mov=-qtd_mov WHERE sistema_mov='" + Referencia_Movimento + "'");
//                pst.execute();
//            } else {
//                JOptionPane.showMessageDialog(null, "ERRO movproduto");
//            }
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Erro ao Alterar\n" + ex);
//        }
//        conex.desconecta();
//    }
//
//    public void BotaoSalvarMovimento() {
//        if (FlagAlterar == 1) {
//            Mbeans.setStatus_mov(2);
//            int refint = Integer.parseInt(Referencia_Movimento);
//            Mbeans.setId_mov((refint));
//            System.out.println("Referencia_Movimento    -               " + Referencia_Movimento);
//            Mbeans.setIntmodotbl(0);
//            Mdao.AlterarMovProduto(Mbeans);
//            FlagAlterar = 0;
//            System.out.println("Alterar2    OK");
//        } else {
//            System.out.println("Referencia_Movimento=MenuPrincipal.jLabel_Movendo.getText();" + Referencia_Movimento);
//            verificador01 = verificador01 + 1;
//            System.out.println("verificador01  --  " + verificador01);
//        }
//        Mbeans.setId_prod_ent(Id_Prod_Ent);
//        Mbeans.setData_mov(Principal.jLabel_Data.getText());///// refe
//        Mbeans.setNota_mov(id_referencia);
//        Referencia_Movimento = Principal.jLabel_Data2.getText() + Principal.jLabel_Hora.getText() + id_referencia;
//        Referencia_Movimento = Referencia_Movimento.replace("/", "").replace(":", "").replace(" ", "");
//        Referencia_Movimento = Referencia_Movimento + verificador01;
//        Mbeans.setSistema_mov((Referencia_Movimento));
//        System.out.println("Referencia_Movimento " + Referencia_Movimento);
//
//        String MinhaQtdMov = (jTextField_Quantidade_Nota.getText());
//        MinhaQtdMov = MinhaQtdMov.replace(",", ".");
//        if (MinhaNatureza == "ENTRADA") {
//            Mbeans.setQtd_mov(Double.parseDouble(MinhaQtdMov));
//        } else if (MinhaNatureza == "SAIDA") {
//            Mbeans.setQtd_mov((Double.parseDouble("-" + MinhaQtdMov)));
//        } else {
//            Mbeans.setQtd_mov(Double.parseDouble(MinhaQtdMov));
//        }
//
//        Mbeans.setQtd_prod(Double.parseDouble(MinhaQtdMov));
//        Mbeans.setQtd_prod_ex((jTextField_Quantidade_Nota.getText()));
//        if (jLabel_Calculado.getText() == "") {
//            jLabel_Calculado.setText(jTextField_Quantidade_Nota.getText());
//        } else {
//        }
//        String MinhaQtdCalc = (jLabel_Calculado.getText());
//        MinhaQtdCalc = MinhaQtdCalc.replace(",", ".");
//        Mbeans.setQtd_calc(Double.parseDouble(MinhaQtdCalc));
//        Mbeans.setQtd_calc_ex((jLabel_Calculado.getText()));
//        Mbeans.setValor_real((ValorReal));
//        Mbeans.setValor_moeda(ValorMoeda);
//        Mbeans.setDestino_mov((jLabel_Setor.getText()));
//        jLabel_Complemento.setText(jLabel_Complemento.getText().toUpperCase());
//        Mbeans.setComplemento_mov((jLabel_Complemento.getText()));
//        Mbeans.setRegistro_mov(Principal.jLabel_Data.getText() + " " + Principal.jLabel_Hora.getText());
//        Mbeans.setStatus_mov(1);
//        Mbeans.setVolume("VOL");
//        Mbeans.setUsuario_mov(Principal.jLabelNomeUsuario.getText());
//        Mbeans.setModo_mov(1);// PRIMARIO
//        Mbeans.setTotal_mov(resultado_Calculo_Formatado);// TOTAL
//        Mbeans.setIntmodotbl(1);
//        Mdao.SalvarMovProduto(Mbeans);
//
//    }
//
//    public void EventoCalculaQuantidade_X_Valor() {
//
//        try {
//            double soma, num1 = 0, num2 = 0;
//            num1 = Double.parseDouble(jTextField_Quantidade_Nota.getText().replace(",", "."));
//            num2 = (ValorReal);
//            soma = num1 * num2;
//            String resultado = String.valueOf(soma);
//            resultado_Calculo_Valor = (resultado);
//            BigDecimal valor = new BigDecimal(resultado_Calculo_Valor);
//            NumberFormat nf = NumberFormat.getCurrencyInstance();
//            String formatado = nf.format(valor);
//            resultado_Calculo_Formatado = (formatado); //            
//        } catch (Exception erro) {
//            JOptionPane.showMessageDialog(null, "   erro:\n verifique\n " + erro);
//        }
//    }
//
//    public void PreencheDadosAlterar() {
//        String IdAlterar = jLabel_IdMovimento.getText();
//        System.out.println("************************ " + IdAlterar);
//        String descricao;
//        String edicao;
//        String FormatoReal = null;
//        Id_Prod_Ent = Integer.parseInt(jLabel_IdProduto.getText());
//
//        conex_Movimento.conexao();
//        conex_Movimento.executaSql(""
//                + "SELECT * FROM movproduto  inner join produto on id_prod_ent=sis_prod \n"
//                + "inner join unidade on  idunid=id_unidade where id_mov ='" + IdAlterar + "'and stprod=1 and stmovimento=1 \n"
//                + " order by id_mov asc"
//                + ""
//                + "");
//        try {
//            conex_Movimento.rs.last();
//            String Fragmento_Variavel;
//            int Fragmento_Variavel_Int = (conex_Movimento.rs.getInt("fragmento_unidade"));
//            if (Fragmento_Variavel_Int == 1) {
//                Fragmento_Variavel = "1";
//            } else {
//                Fragmento_Variavel = "0";
//            }
//            jTextField_Fragmento_Variavel.setText(Fragmento_Variavel);
//            System.out.println("Fragmento_Variavel   -   " + Fragmento_Variavel);
//            String Fragmento_Variavel_SaldoProduto = (conex_Movimento.rs.getString("saldo_prod"));
//            jTextField_Varieavel_SaldoProduto.setText(Fragmento_Variavel_SaldoProduto);
//            System.out.println("Fragmento_Variavel_SaldoProduto   -   " + Fragmento_Variavel_SaldoProduto);
//            String SelecaaoSaldo = (conex_Movimento.rs.getString("saldo_prod"));
//            if (SelecaaoSaldo == null) {
//                System.out.println("selecao saldo null");
//            } else if (SelecaaoSaldo.equals("null")) {
//                System.out.println("selecao saldo null");
//            } else {
//                System.out.println("selecao saldo Ok");
//                System.out.println("Interface.Movimento.MovimentoJIFAlterado.BuscaProdutoId()    A\n " + SelecaaoSaldo);
//                SelecaaoSaldo = SelecaaoSaldo.replace(".", ".").replace(",", ".");
//                System.out.println("Interface.Movimento.MovimentoJIFAlterado.BuscaProdutoId()   B \n " + SelecaaoSaldo);
//                Double num4 = Double.parseDouble(String.valueOf(SelecaaoSaldo));
//                System.out.println("Interface.Movimento.MovimentoJIFAlterado.BuscaProdutoId()    C\n " + num4);
//                BigDecimal df = new BigDecimal(num4);
//                NumberFormat nf = NumberFormat.getInstance();
//                nf.setMaximumFractionDigits(4);
//                FormatoReal = nf.format(df);
//            }
//
//            System.out.println("Interface.Movimento.MovimentoJIFAlterado.BuscaProdutoId()    D\n " + FormatoReal);
//            String SelecaaoUnid = (conex_Movimento.rs.getString("sigla_unidade"));
//            jLabelMeuSaldoProduto.setText(FormatoReal + " / " + SelecaaoUnid);
//            ValorMoeda = (conex_Movimento.rs.getString("valor_prod_ex"));
//            ValorReal = (Double.parseDouble(conex_Movimento.rs.getString("valor_prod")));
//            edicao = conex_Movimento.rs.getString("edicao_prod");
//            if (edicao.equals(null) | edicao.equals("") | edicao.equals(" ")) {
//                edicao = "";
//            } else {
//                edicao = " N° " + edicao;
//            }
//            descricao = " " + conex_Movimento.rs.getString("tipo_prod") + " " + conex_Movimento.rs.getString("nome_prod") + edicao;
//            descricao = descricao.toUpperCase();
//            jTextField_Busca_Produto_Nota.setText(descricao);
//            Id_Prod_Ent = (Integer.parseInt(conex_Movimento.rs.getString("sis_prod")));
//            jLabel_IdProduto.setText(String.valueOf(Id_Prod_Ent));
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
//        } catch (SQLException ex) {
//            System.out.println("BuscaProdutoId() " + ex);
//            jLabelMeuSaldoProduto.setText("");
//            Id_Prod_Ent = (0);
//            jLabel_IdProduto.setText("0");
//            System.out.println("BuscaProdutoId() " + ex);
////                BP.recebe_nome(jTextField_Busca_Produto_Nota.getText());
////                BP.setVisible(true);
//
//        }
//
//        conex_Movimento.desconecta();
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
//    }
//    public void PreencheTabelaPalete() {
//        jbALis.setEnabled(false);
//        jbALin.setEnabled(false);
//        System.out.println("PreencheTabelaPalete() Referencia_Movimento : " + SelecaoReferencia_Movimento);
//        conex.conexao();
//        ArrayList dados = new ArrayList();
//        String[] colunas = new String[]{"Linha", "Pct Padr.", "Lastro", "Altura", "Pct Avu", "Qtd Pct", "Un. Avu.", "Total"};
//// "No",
//        conex.executaSql2("select row_number() OVER (PARTITION by 0) as contador,pctpad ,  lastro ,  altura ,  pctavu ,  totpct ,  unavu ,  totparc , \n"
//                + " mov_palete ,  qtd_palete ,id_palete, idlinha from paletetemp\n"
//                + " where mov_palete='" + SelecaoReferencia_Movimento + "'  and  stpalete=1 order by idlinha asc");//// and verifica_temp is null
//        try {
//            conex.rs.first();
//            do {
//                String pacote = (conex.rs.getString("pctpad"));
//                String lastro = (conex.rs.getString("lastro"));
//                String altura = (conex.rs.getString("altura"));
//                String avulso = (conex.rs.getString("pctavu"));
//                String totalpacote = (conex.rs.getString("totpct"));
//                String uniavulso = (conex.rs.getString("unavu"));
//                String totalfinal = (conex.rs.getString("totparc"));
//
//                pacote = pacote.replace(".0", "").replace(".", ",");
//                lastro = lastro.replace(".0", "").replace(".", ",");
//                altura = altura.replace(".0", "").replace(".", ",");
//                avulso = avulso.replace(".0", "").replace(".", ",");
//                totalpacote = totalpacote.replace(".0", "").replace(".", ",");
//                uniavulso = uniavulso.replace(".0", "").replace(".", ",");
//                totalfinal = totalfinal.replace(".0", "").replace(".", ",");
//                dados.add(new Object[]{conex.rs.getInt("idlinha"), pacote, lastro,
//                    altura, avulso, totalpacote,
//                    uniavulso, totalfinal});
//
//            } while (conex.rs.next());
//        } catch (SQLException ex) {
//            System.err.println("Erro na tabela palete  444444 : \n" + ex);
//        }
//        ModeloTabela modelo = new ModeloTabela(dados, colunas);
//
//        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
//        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
//        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
//        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
//        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
//        direita.setHorizontalAlignment(SwingConstants.RIGHT);
//
//        jTablePalete.setModel(modelo);
//
//        jTablePalete.getColumnModel().getColumn(0).setPreferredWidth(60);
//        jTablePalete.getColumnModel().getColumn(0).setResizable(true);
////        jTablePalllet.getColumnModel().getColumn(0).setCellRenderer(centralizado);
//        jTablePalete.getColumnModel().getColumn(1).setPreferredWidth(60);
//        jTablePalete.getColumnModel().getColumn(1).setResizable(true);
////        jTablePalete.getColumnModel().getColumn(1).setCellRenderer(centralizado);
//        jTablePalete.getColumnModel().getColumn(2).setPreferredWidth(60);
//        jTablePalete.getColumnModel().getColumn(2).setResizable(true);
//        jTablePalete.getColumnModel().getColumn(3).setPreferredWidth(60);
//        jTablePalete.getColumnModel().getColumn(3).setResizable(true);
//        jTablePalete.getColumnModel().getColumn(4).setPreferredWidth(60);
//        jTablePalete.getColumnModel().getColumn(4).setResizable(true);
//        jTablePalete.getColumnModel().getColumn(5).setPreferredWidth(60);
//        jTablePalete.getColumnModel().getColumn(5).setResizable(true);
//        jTablePalete.getColumnModel().getColumn(6).setPreferredWidth(60);
//        jTablePalete.getColumnModel().getColumn(6).setResizable(true);
//        jTablePalete.getColumnModel().getColumn(7).setPreferredWidth(80);
//        jTablePalete.getColumnModel().getColumn(7).setResizable(true);
//
//        jTablePalete.getTableHeader().setReorderingAllowed(false);
//        jTablePalete.setAutoResizeMode(jTablePalete.AUTO_RESIZE_OFF);
//        jTablePalete.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        conex.desconecta();
//    }
//
//    public void SomaTotalPalete() {
//
//        conex.conexao();
//        conex.executaSql2("SELECT sum(cast( cast(replace(totparc,',','.' )as text) as float) )AS totparc,paletetemp.mov_palete FROM paletetemp "
//                + "where mov_palete='" + SelecaoReferencia_Movimento + "'and stpalete='1'"
//                + " GROUP BY  paletetemp.mov_palete "
//                + "  order by mov_palete asc");//// and verifica_temp is null
//        try {
//            conex.rs.first();
//            CalcTotalFinal = (conex.rs.getString("totparc"));
//            Double soma9 = (conex.rs.getDouble("totparc"));
//            System.err.println("Calculado : " + CalcTotalFinal);
//            CalcTotalFinal = ((String.format("%.4f", soma9)));
//            CalcTotalFinal = CalcTotalFinal.replace(",0000", "");
//            System.err.println("ok  SomaTotalPalete totparc : " + SelecaoReferencia_Movimento);
//            System.err.println(" ** Calculado : " + CalcTotalFinal + " **");
//            jLabel_Informacoes.setText("  Total palete = " + CalcTotalFinal);
//        } catch (SQLException ex) {
//            System.err.println("Erro SomaTotalPalete totparc : " + SelecaoReferencia_Movimento + "   -   /n" + ex);
//        }
//        conex.desconecta();
//    }
//
//    public void SomaLinhaPaleteExibir() {
//        double soma, soma2, num1 = 0, num2 = 0, num3 = 0, pac = 0, pacavu = 0, avu = 0;
//        try {
//            if (jtPctPad.getText().isEmpty()) {
//                jtPctPad.requestFocus();
//            }
//            if (jtLast.getText().isEmpty()) {
//                jtLast.setText("0");
//            }
//            if (jtAlt.getText().isEmpty()) {
//                jtAlt.setText("0");
//            }
//            if (jtPctAvu.getText().isEmpty()) {
//                jtPctAvu.setText("0");
//            }
//            if (jtUnAvu.getText().isEmpty()) {
//                jtUnAvu.setText("0");
//            } else {
//            }
//            num1 = Double.parseDouble(jtAlt.getText().replace(",", "."));
//            num2 = Double.parseDouble(jtLast.getText().replace(",", "."));
//            num3 = Double.parseDouble(jtPctAvu.getText().replace(",", "."));
//            avu = Double.parseDouble(jtUnAvu.getText().replace(",", "."));
//            pacavu = Double.parseDouble(jtPctAvu.getText().replace(",", "."));
//            pac = Double.parseDouble(jtPctPad.getText().replace(",", "."));
//            soma = num1 * num2 + num3;
//            String resultado = Double.toString(soma);
//            jtTotPct.setText((String.format("%.4f", soma)));
//            jtTotPct.setText(jtTotPct.getText().replace(",0000", ""));
//            soma2 = soma * pac + avu;
//            jtTotParc.setText((String.format("%.4f", soma2)));
//            jtTotParc.setText(jtTotParc.getText().replace(",0000", ""));
//            System.err.println("soma: " + soma);
//            System.err.println("soma2: " + soma2);
//            double valor_decimal = ((soma2 * 100));
//            System.err.println("soma3: " + valor_decimal);
//        } catch (Exception erro) {
//            JOptionPane.showMessageDialog(null, "Ocorreu um erro verifique\n: " + erro);
//        }
//    }
//
//    public void SalvaPalete() {
//        beansp.setUsuario(menu.jLabelNomeUsuario.getText());
//        beansp.setRegistro(menu.jLabel_Data.getText() + " - " + menu.jLabel_Hora.getText());
//        beansp.setPctpad(jtPctPad.getText());
//        beansp.setLastro(jtLast.getText());
//        beansp.setAltura(jtAlt.getText());
//        beansp.setPctavu(jtPctAvu.getText());
//        beansp.setUnavu(jtUnAvu.getText());
//        beansp.setTotpct(jtTotPct.getText());
//        beansp.setTotparc(jtTotParc.getText());
//        beansp.setMov_palete(SelecaoReferencia_Movimento);
//        beansp.setNota_palete(String.valueOf(id_referencia));
//        if (FlagPalete == 1) {
//            beansp.setVerifica_principal(2);
//            beansp.setStpalete(1);
//            beansp.setIdlinha(Integer.parseInt(Id_Linha));
//            daop.AlterarTemp(beansp);
//            FlagPalete = 0;
//        } else {
//            Principal.jButton3.doClick();
//            beansp.setId_palete(Principal.jLabel_Gerador_De_Codigo.getText());
//            beansp.setVerifica_principal(1);
//            beansp.setStpalete(1);
//            daop.SalvarTemp(beansp);
//        }
//
//        System.err.println("SelecaoReferencia_Movimento : " + SelecaoReferencia_Movimento);
//    }
//
//    public void DesbloqueiaCamposPalete() {
//        jtAlt.setEnabled(!false);
//        jtLast.setEnabled(!false);
//        jtPctAvu.setEnabled(!false);
//        jtUnAvu.setEnabled(!false);
//        jtTotPct.setEnabled(false);
//        jtTotParc.setEnabled(false);
//        jtPctPad.setEnabled(!false);
//        FlagPalete = 0;
//        jbALis.setEnabled(false);
//        jbALin.setEnabled(false);
//        jbAdicionar.setEnabled(!false);
//        jbLimpar.setEnabled(!false);
//        jButton_Bandeira1.setEnabled(!false);
//        jTablePalete.setEnabled(true);
//    }
//
//    public void BloqueiaCamposPalete() {
//        jtAlt.setEnabled(false);
//        jtLast.setEnabled(false);
//        jtPctAvu.setEnabled(false);
//        jtUnAvu.setEnabled(false);
//        jtTotPct.setEnabled(false);
//        jtTotParc.setEnabled(false);
//        jtPctPad.setEnabled(false);
//        FlagPalete = 0;
//        jbALis.setEnabled(false);
//        jbALin.setEnabled(false);
//        jbAdicionar.setEnabled(false);
//        jbLimpar.setEnabled(false);
//        jButton_Bandeira1.setEnabled(false);
//    }
//
//    public void limpaCamposPalete() {
//        jtAlt.setText("");
//        jtLast.setText("");
//        jtPctAvu.setText("");
//        jtUnAvu.setText("");
//        jtTotPct.setText("");
//        jtTotParc.setText("");
//        jtPctPad.setText("");
//        jtPctPad.requestFocus();
//        FlagPalete = 0;
//        jbALis.setEnabled(false);
//        jbALin.setEnabled(false);
//    }
//
//    public void EventoFechaMovimento() {
//        jLabelMeuSaldoProduto.setText("");
//        MovimentoBancoTemporarioApagar();
//        LimpaTransporte();
//        BloqueiaCamposPalete();
//        jTablePalete.setEnabled(false);
//        LimparTabela();
//        AtualizaListaMovimentos();
//        jComboBox_Situacao.setSelectedItem("1-CALCULADO");
//        Principal.jLabelCodigoTela.setText("FechaMovimentoCadastro");
//        Principal.jButton1.doClick();
//        jTextArea_Observacao.setText("");
//        Principal.jLabelCodigoTela.setText("AtualizaTudo");
//        Principal.jButton1.doClick();
//    }
//
//    public void AtualizaListaMovimentos() {
//        try {
//            System.out.println("Interface.Movimento.MovimentoJIFAlterado.AtualizaListaMovimentos()     4\n");
//        } catch (Error err) {
//            System.out.println("Interface.Movimento.MovimentoJIFAlterado.AtualizaListaMovimentos()     4\n" + err);
//        }
//
//        try {
//        } catch (Error err) {
//            System.out.println("Interface.Movimento.MovimentoJIFAlterado.AtualizaListaMovimentos()    1\n" + err);
//        }
//        try {
////            JIF_Consultas.jTextField_Buscar.requestFocus();
//        } catch (Error err) {
//            System.out.println("Interface.Movimento.MovimentoJIFAlterado.AtualizaListaMovimentos()     2\n" + err);
//        }
//        try {
////            ListaMovimentoJIF.jTextField_Busca.requestFocus();
//        } catch (Error err) {
//            System.out.println("Interface.Movimento.MovimentoJIFAlterado.AtualizaListaMovimentos()     3\n" + err);
//        }
//
//    }

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

        AtualizarTudo();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
//        PreencherTabela();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        int resposta = 0;
        resposta = JOptionPane.showConfirmDialog(rootPane, "Deseja realmente fazer uma cópia ? ");
        if (resposta == JOptionPane.YES_OPTION) {
//            MinhaCopia = "executando";
//            ExecutaCopia();
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

//    public void ExecutaCopiaUpdate() {
//        if (MinhaCopia.equals(null) | MinhaCopia.equals("") | MinhaCopia.equals("null")) {
//            System.out.println(".............................aqui MovimentoBancoTemporarioAlterar sistema_mov null");
//        } else {
//            conex.conexao();
//            conex.executaSql(""
//                    + "SELECT * FROM movproduto  where nota_mov='" + id_referencia + "'"
//                    + "and status_mov='ATIVO'   order by id_mov asc "
//                    + "");
//        }
//        try {
//            conex.rs.first();
//            int numero = 1;
//            do {
//                numero = numero + 1;
//            } while (conex.rs.next());
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(rootPane, "erro\nExecutaCopiaUpdate\n" + ex);
//            System.out.println("erro\nExecutaCopiaUpdate\n" + ex);
//        }
//        conex.desconecta();
//    }
//
//    public void VerificaSeTemPalete() {
//        conex2.conexao();
//        conex2.executaSql("SELECT * FROM palete WHERE mov_palete='" + MinhaIdSistema + "'  ");
//        System.err.println("mov_palete            MinhaIdSistema"
//                + "11"
//                + ""
//                + "=  " + MinhaIdSistema);
//        try {
//            do {
//                conex2.rs.first();
//                String MeuMovPalete = (conex2.rs.getString("mov_palete"));
//
//                java.sql.PreparedStatement pst = conex2.con.prepareStatement(""
//                        + "insert into palete(  pctpad, lastro, altura, pctavu, totpct, unavu, totparc, \n"
//                        + "       mov_palete, qtd_palete, nota_palete,"
//                        + "verifica_principal, status_palete, usuario_palete, registro_palete\n"
//                        + "          )"
//                        + "SELECT  pctpad, lastro, altura, pctavu, totpct, unavu, totparc, \n"
//                        + "    '" + MeuMovPaleteReferencia + "',  qtd_palete, '" + id_referencia + "'"
//                        + ", '" + id_referencia + "' ,'ATIVO', usuario_palete, registro_palete      \n"
//                        + "  FROM palete where mov_palete='" + MeuMovPalete + "'  and status_palete='ATIVO' "
//                        + "order by idlinha asc ");
//                pst.execute();
//                System.out.println("MeuMovPalete " + MeuMovPalete);
//            } while (conex.rs.next());
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(rootPane, "erro\nVerificaSeTemPalete\n" + ex);
//        }
//
//        conex2.desconecta();
//    }
//
//    public void ExecutaCopia() {
//        id_referenciUltimo = id_referencia;
////        MinhsaIdNota = "Novo";
//        CarregaUltimo();
//        MinhaIdNota = "Novo";
//        System.out.println("##### MinhaIdNota = '" + id_referenciUltimo + "'  [ Novo] '" + id_referencia + "'  ");
//        MovimentoBancoTemporarioAlterado();
//        ExecutaCopiaUpdate();
//        VerificaEFazUpdatePalete();
//        this.setTitle("Cadastro Movimento [ " + MinhaNatureza + " ] Id Referencia : " + id_referencia);
//        PreencherTabela();
//        jComboBox_Situacao.setSelectedItem("1-CALCULADO");
//    }
//    public void VerificaEFazUpdatePalete() {
//        conex.conexao();
//        try {
//            java.sql.PreparedStatement pst = conex.con.prepareStatement("UPDATE palete\n"
//                    + "   SET \n"
//                    + "       verifica_principal=cast (nota_palete as integer)");
//            pst.execute();
//            System.out.println("UPDATE palete 2   OK");
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "UPDATE palete 2   ERRO" + ex);
//            System.out.println("UPDATE palete     2        ERRO" + ex);
//        }
//        conex.desconecta();
//    }
//    public void EventoSalvaPalete() {
//        if (SelecaoReferencia_Movimento == null) {
//        } else if (jtPctPad.getText().isEmpty()) {
//        } else {
//            SomaLinhaPaleteExibir();
//            SalvaPalete();
//            PreencheTabelaPalete();
//            SomaTotalPalete();
//            System.out.println("Normal ate aqui");
//            ContaTotalLinhas();
//            System.out.println("Normal ate aqui    tudo ok");
//            if (jtPctPad.getText().isEmpty()) {
//                jtPctPad.requestFocus();
//            } else {
//                jtLast.requestFocus();
//            }
//        }
//    }
//    public void ContaTotalLinhas() {
//        System.out.println("UContaTotalLinhas  SelecaoReferencia_Movimento   - " + SelecaoReferencia_Movimento);
//        if (SelecaoReferencia_Movimento.isEmpty()) {
//            JOptionPane.showMessageDialog(rootPane, "linha vazia");
//        } else {
//            String busca = SelecaoReferencia_Movimento;
//            conex.conexao();
//            conex.executaSql2(" select count(mov_palete) as linha,mov_palete  FROM paletetemp where stpalete=1 and mov_palete='" + busca + "' "
//                    + " GROUP BY paletetemp.mov_palete"
//                    + "");
//            try {
//                conex.rs.first();
//                ContadorLinha = String.valueOf(conex.rs.getInt("linha"));
//
//                try {
//                    java.sql.PreparedStatement pst = conex.con.prepareStatement(""
//                            + "UPDATE paletetemp   SET  qtd_palete= '" + ContadorLinha + "' where mov_palete='" + busca + "' \n"
//                            + "");
//                    pst.execute();
//                    System.out.println("UPDATE palete contador de linhas  OK  " + ContadorLinha);
//                } catch (SQLException ex) {
//                    System.out.println("UPDATE palete       contador de linhas      ERRO" + ex);
//                }
//                try {
//                    java.sql.PreparedStatement pst = conex.con.prepareStatement(""
//                            + "UPDATE palete   SET  qtd_palete= '" + ContadorLinha + "' where mov_palete='" + busca + "' \n"
//                            + "");
//                    pst.execute();
//                    System.out.println("UPDATE palete contador de linhas  OK  " + ContadorLinha);
//                } catch (SQLException ex) {
//                    System.out.println("UPDATE palete       contador de linhas      ERRO" + ex);
//                }
//            } catch (SQLException ex) {
//                System.out.println("UPDATE palete       contador de linhas      ERRO" + ex);
//                ContadorLinha = "0";
//            }
//            conex.desconecta();
//        }
//    }
    public void EnviaDados(String A, String B, String C) {
    }

    private void txtHoraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHoraKeyPressed

        if (txtHora.getText().length() >= 5) {

        }

    }//GEN-LAST:event_txtHoraKeyPressed

    private void txtHoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoraActionPerformed

    private void txtHoraFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHoraFocusLost
        if (txtHora.getText().isEmpty()) {
            txtHora.setText("  :  ");
        } else if (txtHora.getText().equals("  :  ")) {
            txtHora.setText("  :  ");
        } else {
//            AjustaMinhaHora();
        }
    }//GEN-LAST:event_txtHoraFocusLost

//    public void AjustaMinhaHora() {
//        String s = jTextField_Hora.getText().replace(":", "");
//        if (s.matches("^[a-zA-ZÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü]*$")) {
//            System.out.println("Interface.Movimento.MovimentoJIFAlterado.AjustaMinhaHora()      ERRRROOOOOOOO");
//            jTextField_Hora.setBackground(Color.red);
//        } else if (s.matches("^[0-9]*$")) {
//            if (s.length() > 3) {
//                if (s.length() == 4) {
//                    String formatado = s.substring(0, 2) + ":" + s.substring(2, 4);
//                    System.err.println("    ddddd   " + formatado); ////   (11)4168-3085
//                    jTextField_Hora.setText(formatado);
//                } else if (s.length() == 6) {
//                    String formatado = s.substring(0, 2) + ":" + s.substring(2, 4) + ":" + s.substring(4, 6);
//                    System.err.println("    ddddd   " + formatado); ////   (11)4168-3085
//                    jTextField_Hora.setText(formatado);
//                } else if (s.length() < 6) {
//                } else if (s.length() > 7 | s.length() == 3 | s.length() == 5) {
//                    System.err.println("    eeee   erro");
//                    jTextField_Hora.setText("");
//                    jTextField_Hora.requestFocus();
//                    jTextField_Hora.setBackground(Color.red);
//                } else {
//                    System.err.println("    ddddd   erro");
//                    jTextField_Hora.setText("");
//                    jTextField_Hora.requestFocus();
//                }
//                jTextField_Hora.setBackground(Color.white);
//            }
//        }
//    }

    private void btnHoraAtualKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnHoraAtualKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHoraAtualKeyPressed

    private void btnHoraAtualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoraAtualActionPerformed
        if (txtHora.getText().isEmpty()) {
            txtHora.setText(Principal.jLabel_Hora.getText());
            String Relogio = (Principal.jLabel_Hora.getText());
//            String MenuMinhaHoraSistema = (String.format("%1$tM:%1$tS", Relogio)); /// %1$tM:%1$tS
            txtHora.setText(Relogio);
        } else {
            txtHora.setText(null);
        }
    }//GEN-LAST:event_btnHoraAtualActionPerformed

    private void btnDataAtualKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDataAtualKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDataAtualKeyPressed

    private void btnDataAtualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataAtualActionPerformed
        if (dataNota.getDate() == (null)) {
            try {
                data = formato.parse(Principal.jLabel_Data.getText());
                dataNota.setDate(data);
            } catch (ParseException ex) {
                //                Logger.getLogger(MovimentoJIF.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            dataNota.setDate(null);
        }
        ManipulaData();
    }//GEN-LAST:event_btnDataAtualActionPerformed

    private void cbClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbClienteKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            txtHora.requestFocus();
        }
        if (evt.getKeyCode() == evt.VK_F5) {
            PreencherFornecedor();
        }
    }//GEN-LAST:event_cbClienteKeyPressed

    private void cbClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbClienteActionPerformed

    private void txtChaveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChaveKeyPressed
        if (evt.getKeyCode() == evt.VK_F2) {
            System.out.println("Interface.Movimento.MovimentoJIFAlterado.jTextField_Chave_NotaKeyPressed()\n F12");

        }
        if (evt.getKeyCode() == evt.VK_ENTER) {
        }
        if (evt.getKeyCode() == evt.VK_F3) {

        }
        if (evt.getKeyCode() == evt.VK_F12) {
            JDialogAuxilioChave conf = new JDialogAuxilioChave(menu, rootPaneCheckingEnabled);
            conf.RecebeFornecedor("" + cbCliente.getSelectedItem());
            if (conf == null) {
                // TelaSalProd = new ModSaldoProd();
                conf.setVisible(true);
                conf.setResizable(false);

            } else {
                conf.setVisible(true);
                conf.setResizable(false);
            }
        }
    }//GEN-LAST:event_txtChaveKeyPressed

    private void txtChaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtChaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtChaveActionPerformed

    private void txtChaveFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtChaveFocusLost
        if (txtChave.getText().isEmpty()) {
        } else {
        }
    }//GEN-LAST:event_txtChaveFocusLost

    private void txtNotaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNotaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNotaKeyReleased

    private void txtNotaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNotaKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            if (txtChave.isEnabled()) {
                txtChave.requestFocus();
            } else {
            }
        }
    }//GEN-LAST:event_txtNotaKeyPressed

    private void txtNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNotaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNotaActionPerformed

    private void txtNotaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNotaFocusLost

    }//GEN-LAST:event_txtNotaFocusLost

    private void dataNotaVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_dataNotaVetoableChange

    }//GEN-LAST:event_dataNotaVetoableChange

    private void dataNotaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dataNotaKeyPressed

    }//GEN-LAST:event_dataNotaKeyPressed

    private void dataNotaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dataNotaPropertyChange
        ManipulaData();
    }//GEN-LAST:event_dataNotaPropertyChange

    private void dataNotaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataNotaMouseClicked
        ManipulaData();        // TODO add your handling code here:
    }//GEN-LAST:event_dataNotaMouseClicked

    private void dataNotaAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_dataNotaAncestorAdded
    }//GEN-LAST:event_dataNotaAncestorAdded

    private void dataNotaAncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_dataNotaAncestorMoved
    }//GEN-LAST:event_dataNotaAncestorMoved

    private void dataNotaHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_dataNotaHierarchyChanged
    }//GEN-LAST:event_dataNotaHierarchyChanged

    private void cbNaturezaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbNaturezaKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            dataNota.requestFocus();
        }
        if (evt.getKeyCode() == evt.VK_F5) {
//            PreencherNatureza();
        }
    }//GEN-LAST:event_cbNaturezaKeyPressed

    private void cbNaturezaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbNaturezaFocusLost
//        SelecionarNatureza();
        //        if (jLabel_OS.getText() == "Novo") {
        //            preencheNatureza_Tipo();
        //        } else {
        //        }
    }//GEN-LAST:event_cbNaturezaFocusLost

    private void cbNaturezaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbNaturezaItemStateChanged
     
    }//GEN-LAST:event_cbNaturezaItemStateChanged

    public void ManipulaNatureza() {

    }

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        CarregaSistemaRepeteUltimo();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jRadioButton_ativa_VizualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_ativa_VizualizarActionPerformed
//        SalvaReferenciado();
    }//GEN-LAST:event_jRadioButton_ativa_VizualizarActionPerformed

    private void cbClienteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbClienteItemStateChanged


    }//GEN-LAST:event_cbClienteItemStateChanged

    private void cbClienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbClienteFocusLost
//        SelecionarFornecedor();
    }//GEN-LAST:event_cbClienteFocusLost
//    public void LancaNaTabelaMovimento() {
//        conex_Palete2.conexao();
//        System.err.println("**** LancaNaTabelaMovimento - CalcTotalFinal " + CalcTotalFinal);
//        System.err.println("**** LancaNaTabelaMovimento - SelecaoReferencia_Movimento " + SelecaoReferencia_Movimento);
//        try {
//            java.sql.PreparedStatement pst = conex_Palete2.con.prepareStatement(" UPDATE movproduto"
//                    + "   SET  qtd_calc='" + CalcTotalFinal + "', qtd_calc_ex='" + CalcTotalFinal + "'"
//                    + " WHERE stmovimento=1 and sistema_mov='" + SelecaoReferencia_Movimento + "'  ");
//            pst.execute();
//            System.out.print("LancaNaTabelaPalete modificado    UPDATE   ok");
//        } catch (SQLException ex) {
//            System.out.print("LancaNaTabelaPalete  modificado   UPDATE   erro   " + ex);
//        }
//
//        conex_Palete2.desconecta();
//    }
//
//    public void LancaNaTabelaPalete() {
//        conex_Palete.conexao();
//        try {
//            java.sql.PreparedStatement pst = conex_Palete.con.prepareStatement(""
//                    + "UPDATE palete as p\n"
//                    + "   SET  stpalete='2'\n"
//                    + "from paletetemp as t\n"
//                    + "where t.id_palete=p.id_palete and t.verifica_principal='2' ");
//            pst.execute();
//            System.out.print("LancaNaTabelaPalete modificado    UPDATE   ok");
//        } catch (SQLException ex) {
//            System.out.print("LancaNaTabelaPalete  modificado   UPDATE   erro   " + ex);
//        }
//
//        try {
//            java.sql.PreparedStatement pst = conex_Palete.con.prepareStatement(""
//                    + "INSERT INTO palete  (\n"
//                    + "             pctpad, lastro, altura, pctavu, totpct, unavu, totparc, \n"
//                    + "            mov_palete, qtd_palete, nota_palete, verifica_principal, usuario_palete, \n"
//                    + "            registro_palete, id_palete, stpalete)\n"
//                    + " SELECT  pctpad, lastro, altura, pctavu, totpct, unavu, totparc, \n"
//                    + "       mov_palete, qtd_palete, nota_palete, verifica_principal, usuario_palete, \n"
//                    + "       registro_palete, id_palete, stpalete\n"
//                    + "  FROM paletetemp\n"
//                    + "  where mov_palete=mov_palete and verifica_principal='2' ");
//            pst.execute();
//            System.out.print("LancaNaTabelaPalete    INSERT    ok");
//        } catch (SQLException ex) {
//            System.out.print("LancaNaTabelaPalete    INSERT    erro   " + ex);
//        }
//        try {
//            java.sql.PreparedStatement pst = conex_Palete.con.prepareStatement(""
//                    + "INSERT INTO palete  (\n"
//                    + "             pctpad, lastro, altura, pctavu, totpct, unavu, totparc, \n"
//                    + "            mov_palete, qtd_palete, nota_palete, verifica_principal, usuario_palete, \n"
//                    + "            registro_palete, id_palete, stpalete)\n"
//                    + " SELECT  pctpad, lastro, altura, pctavu, totpct, unavu, totparc, \n"
//                    + "       mov_palete, qtd_palete, nota_palete, verifica_principal, usuario_palete, \n"
//                    + "       registro_palete, id_palete, stpalete\n"
//                    + "  FROM paletetemp\n"
//                    + "  where mov_palete=mov_palete and verifica_principal='1' ");
//            pst.execute();
//            System.out.print("LancaNaTabelaPalete    INSERT    ok");
//        } catch (SQLException ex) {
//            System.out.print("LancaNaTabelaPalete    INSERT    erro   " + ex);
//        }
//
//        try {
//            java.sql.PreparedStatement pst = conex_Palete.con.prepareStatement(""
//                    + "UPDATE palete as p\n"
//                    + "   SET  stpalete='3'\n"
//                    + "from paletetemp as t\n"
//                    + "where t.id_palete=p.id_palete and t.verifica_principal='3' ");
//            pst.execute();
//            System.out.print("LancaNaTabelaPalete modificado  excluido  UPDATE   ok");
//        } catch (SQLException ex) {
//            System.out.print("LancaNaTabelaPalete  modificado  excluido  UPDATE   erro   " + ex);
//        }
//
//        try {
//            java.sql.PreparedStatement pst = conex_Palete.con.prepareStatement(""
//                    + "UPDATE palete as p\n"
//                    + "   SET  stpalete='3'\n"
//                    + "from paletetemp as t\n"
//                    + "where t.mov_palete=p.mov_palete and t.verifica_principal='3' ");
//            pst.execute();
//            System.out.print("LancaNaTabelaPalete modificado  excluido  UPDATE   ok");
//        } catch (SQLException ex) {
//            System.out.print("LancaNaTabelaPalete  modificado  excluido  UPDATE   erro   " + ex);
//        }
//
//        conex_Palete.desconecta();
//    }

    private void cbNaturezaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbNaturezaFocusGained
//        SelecionarNaturezaInt = 1;
        ComboFocuNatureza = 1;
    }//GEN-LAST:event_cbNaturezaFocusGained

    private void txtHoraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHoraKeyReleased
//        AjustaMinhaHora();
    }//GEN-LAST:event_txtHoraKeyReleased

    private void cbNaturezaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNaturezaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbNaturezaActionPerformed

    private void cbClienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbClienteFocusGained
//        ComboFocuFornecedor = 1;

    }//GEN-LAST:event_cbClienteFocusGained

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        LimpaSistema();
//        LimpaCampoProduto();
    }//GEN-LAST:event_formInternalFrameClosing

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
//        EventoFechaMovimento();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
//        produtoController.excluirProduto(this);
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        //    produtoController.limparCampos(this);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        //    produtoController.salvar(this);
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void vol_especieFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_vol_especieFocusLost
        vol_especie.setText(vol_especie.getText().toUpperCase());
    }//GEN-LAST:event_vol_especieFocusLost

    private void vol_numeracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vol_numeracaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vol_numeracaoActionPerformed

    private void vol_placaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vol_placaKeyReleased
        AjustaMinhaPlaca();
    }//GEN-LAST:event_vol_placaKeyReleased

    private void vol_placaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_vol_placaFocusLost
        //aqui
        AjustaMinhaPlaca();
        vol_placa.setText(vol_placa.getText().toUpperCase());
    }//GEN-LAST:event_vol_placaFocusLost

    private void vol_ufFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_vol_ufFocusLost
        AjustaMinhaUf();
        vol_uf.setText(vol_uf.getText().toUpperCase());
    }//GEN-LAST:event_vol_ufFocusLost

    private void vol_motoristaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_vol_motoristaFocusLost
        vol_motorista.setText(vol_motorista.getText().toUpperCase());
        vol_motorista.setBackground(Color.WHITE);
    }//GEN-LAST:event_vol_motoristaFocusLost

    private void vol_motoristaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_vol_motoristaFocusGained
        //        vol_motorista.setBackground(Color.cyan);
    }//GEN-LAST:event_vol_motoristaFocusGained

    private void jComboBox_transportadoraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox_transportadoraKeyPressed
        if (evt.getKeyCode() == evt.VK_F5) {
            PreencherTransporte();
        }
    }//GEN-LAST:event_jComboBox_transportadoraKeyPressed

    private void jComboBox_transportadoraFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox_transportadoraFocusLost

    }//GEN-LAST:event_jComboBox_transportadoraFocusLost

    private void jComboBox_transportadoraFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox_transportadoraFocusGained
        ComboFocu = 1;

    }//GEN-LAST:event_jComboBox_transportadoraFocusGained

    private void jComboBox_transportadoraItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_transportadoraItemStateChanged

        String selecao = (String) jComboBox_transportadora.getSelectedItem();

        if (selecao == null) {
//            jLabel_exibe_transporte.setText(null);
//            vol_transportadora.setText("1");
            //             jComboBox_transportadoraInt.setSelectedIndex(1);
        } else {
            if (ComboFocu == 0) {
            } else {
//                jComboBox_transportadoraInt.setSelectedIndex(jComboBox_transportadora.getSelectedIndex());
//                System.out.println("==========  String " + jComboBox_transportadora.getSelectedItem());
//                System.out.println("==========  Int " + jComboBox_transportadoraInt.getSelectedItem());
//                System.out.println("==========  Int " + jComboBox_transportadoraInt.getSelectedIndex());
                //            exibe_transporte();//preenche_Transporte();
            }
        }
    }//GEN-LAST:event_jComboBox_transportadoraItemStateChanged

    private void jComboBox_modalidadeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_modalidadeItemStateChanged
        //        manipula_transporte();
    }//GEN-LAST:event_jComboBox_modalidadeItemStateChanged

    private void jTableListaProduto_NotaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableListaProduto_NotaMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableListaProduto_NotaMouseEntered

    private void jTableListaProduto_NotaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableListaProduto_NotaMouseClicked

        if (jTableListaProduto_Nota.isEnabled()) {
            String busca_Lista = "" + jTableListaProduto_Nota.getValueAt(jTableListaProduto_Nota.getSelectedRow(), 8);
            //            jLabel_Id_Mov.setText((busca_Lista));
            //            jLabelIdMovi.setText((busca_Lista));
            String busca_item = "" + jTableListaProduto_Nota.getValueAt(jTableListaProduto_Nota.getSelectedRow(), 0);
//            jLabel_IdMovimento.setText(busca_item);
            //            Evento_Busca_Movimento_Nota();
            //            flag_salvar = 2;
            jTextField_Quantidade_Nota.requestFocus();
            jButton_Excluir_Movimento_Nota.setEnabled(true);
            //            jButton_Adicionar_Produto_Nota.setText("Alterar produto");
//            PreencheDadosAlterar();
//            FlagAlterar = 1;
//            Referencia_Movimento = "" + jTableListaProduto_Nota.getValueAt(jTableListaProduto_Nota.getSelectedRow(), 0);
            //                        JOptionPane.showMessageDialog(rootPane, Referencia_Movimento);
        } else {
        }
    }//GEN-LAST:event_jTableListaProduto_NotaMouseClicked

    private void jTextField_Busca_Produto_NotaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_Busca_Produto_NotaKeyPressed

        if (evt.getKeyCode() == evt.VK_F12) {
            Complementar.PreencherSetor();

            //            CD.RecebeDadosMovimentoAlterado(MinhaNatureza, jLabel_Complemento.getText(), jLabel_Setor.getText());
            Complementar.VerificaModo(MinhaNatureza);
//            Complementar.RecebeDados(jLabel_Complemento.getText(), jLabel_Setor.getText(), jLabel_Calculado.getText());
            Complementar.setVisible(true);

        }
        if (evt.getKeyCode() == evt.VK_F2) {
            String BuscaNome = jTextField_Busca_Produto_Nota.getText();
            BP.recebe_nome(BuscaNome);
            BP.setVisible(true);
        }
        if (evt.getKeyCode() == evt.VK_ENTER) {
//            EventoBuscaProduto();
            if (jTextField_Busca_Produto_Nota.getText().isEmpty()) {
//                jLabel_IdProduto.setText("0");
            } else {
            }
        }
    }//GEN-LAST:event_jTextField_Busca_Produto_NotaKeyPressed

    private void jTextField_Busca_Produto_NotaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_Busca_Produto_NotaFocusLost

    }//GEN-LAST:event_jTextField_Busca_Produto_NotaFocusLost

    private void jTextField_Busca_Produto_NotaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_Busca_Produto_NotaFocusGained
        // evento_Verifica();
    }//GEN-LAST:event_jTextField_Busca_Produto_NotaFocusGained

    private void jTextField_Quantidade_NotaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_Quantidade_NotaKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            if (jTextField_Quantidade_Nota.getText().isEmpty()) {
                jTextField_Busca_Produto_Nota.requestFocus();
            } else {
                jButton_Adicionar_Produto_Nota.requestFocus();
            }
        }

//        if (evt.getKeyCode() == evt.VK_F12) {
//            if (jLabel_Calculado.getText().equals("")) {
//                jLabel_Calculado.setText(jTextField_Quantidade_Nota.getText());
//            } else {
//            }
//            Complementar.VerificaModo(MinhaNatureza);
//            Complementar.RecebeDados(jLabel_Complemento.getText(), jLabel_Setor.getText(), jLabel_Calculado.getText());
//            Complementar.setVisible(true);
//        }
        if (evt.getKeyCode() == evt.VK_DOWN) {
            //            jFormattedTextFieldCep.requestFocus();
        }
        if (evt.getKeyCode() == evt.VK_UP) {
            //            jTextField_Busca_Produto_PRO.requestFocus();
        }
    }//GEN-LAST:event_jTextField_Quantidade_NotaKeyPressed

    private void jTextField_Quantidade_NotaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_Quantidade_NotaFocusLost
//        int S_Natureza_Int = 0;
//        System.out.println("jTextField_Fragmento_Variavel - " + jTextField_Fragmento_Variavel.getText());
////        if (jLabel_Calculado.getText().isEmpty() | jLabel_Calculado.getText().equals(" ")) {
////            jLabel_Calculado.setText(jTextField_Quantidade_Nota.getText());
////        } else if (jLabel_Calculado.getText().equals("0") | jLabel_Calculado.getText() == "0") {
////            jLabel_Calculado.setText(jTextField_Quantidade_Nota.getText());
////        } else {
////        }
//        String s = jTextField_Quantidade_Nota.getText();
//        if (jTextField_Fragmento_Variavel.getText().equals("1")) {
//            //            JOptionPane.showMessageDialog(jLabel_Complemento, "1");
//            if (s.matches("^[a-zA-ZÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü]*$")) {
//                //          jLabel4.setText("letra");
//                System.out.println("jTextField_Quantidade_Nota             ==               a");
//            } else if (s.matches("^[0-9]*$")) {
//                jTextField_Quantidade_Nota.setText(jTextField_Quantidade_Nota.getText() + ",000");
//                System.out.println("jTextField_Quantidade_Nota             ==               b");
//                //             jLabel4.setText("numero");
//            } else if (s.matches("^[0-9,.]*$")) {
//                System.out.println("jTextField_Quantidade_Nota             ==               c");
//                String MeuValor = jTextField_Quantidade_Nota.getText();
//                MeuValor = MeuValor.replace("R$", "").replace(" ", "").replace(".", "").replace(",", ".");
//                Double num41 = (Double.parseDouble(MeuValor));
//                BigDecimal df1 = new BigDecimal(num41);
//                NumberFormat nf1 = NumberFormat.getInstance();// getCurrencyInstance
//                nf1.setMinimumFractionDigits(3);
//                nf1.setMaximumFractionDigits(3);
//                String FormatoValorProd = nf1.format(df1);
//
//                jTextField_Quantidade_Nota.setText(FormatoValorProd);
//                System.out.println("c   -     " + FormatoValorProd);
//            } else {
//                jTextField_Quantidade_Nota.setText(jTextField_Quantidade_Nota.getText() + ",000");
//                //            jLabel4.setText("erro");
//                System.out.println("jTextField_Quantidade_Nota             ==               d");
//            }
//        } else {
//            if (jTextField_Fragmento_Variavel.getText().equals("0")) {
//                //            JOptionPane.showMessageDialog(jLabel_Complemento, "1");
//                if (s.matches("^[a-zA-ZÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü]*$")) {
//                    //          jLabel4.setText("letra");
//                    System.out.println(" jTextField_Fragmento_Variavel     ==     a");
//                } else if (s.matches("^[0-9]*$")) {
//                    jTextField_Quantidade_Nota.setText(jTextField_Quantidade_Nota.getText().replace(",000", "").replace(",00", "").replace(",0", "").replace(",", ""));
//                    //             jLabel4.setText("numero");
//                    System.out.println(" jTextField_Fragmento_Variavel     ==     b");
//                } else if (s.matches("^[0-9,]*$")) {
//                    jTextField_Quantidade_Nota.setText(jTextField_Quantidade_Nota.getText().replace(",000", "").replace(",00", "").replace(",0", "").replace(",", ""));
//                    System.out.println(" jTextField_Fragmento_Variavel     ==     c");
//                } else {
//                    //                jTextField_Quantidade_Nota.setText(jTextField_Quantidade_Nota.getText() + ",000");
//                    //            jLabel4.setText("erro");
//                    System.out.println(" jTextField_Fragmento_Variavel     ==     d");
//                }
//                //            JOptionPane.showMessageDialog(jLabel_Complemento, "0");
//            }
//        }
//        String VerQuantidade = jTextField_Quantidade_Nota.getText().replace(" ", "").replace(".", "").replace(",", ".");
//        System.out.println("Natureza ------------------------------------------  " + jComboBox_Natureza_Int.getSelectedItem());
//        //         try{
//            String S_Natureza = "" + jComboBox_Natureza_Int.getSelectedItem();
//            S_Natureza_Int = Integer.parseInt(S_Natureza);
//            //         }
//        //         catch( Exception ex){JOptionPane.showMessageDialog(jLabel_Complemento, "erro "+ex);}
//        System.out.println("Natureza int  " + S_Natureza_Int + "    S_Natureza    " + S_Natureza + " jComboBox_Natureza ==  " + jComboBox_Natureza.getSelectedItem());
//        if (VerQuantidade.isEmpty()) {
//            System.out.println("Resultado     -  vazio  ");
//        } else if (VerQuantidade.equals("null") | VerQuantidade.equals(null)) {
//            System.out.println("Resultado     -  null  ");
//        } else if (S_Natureza_Int == 1) {
//            System.out.println("Interface.MovimentoCadastroJIF.jTextField_Quantidade_NotaFocusLost()      -            Tipo Entrada ok");
//        } else {
//            System.out.println("jTextField_Varieavel_SaldoProduto - " + jTextField_Varieavel_SaldoProduto.getText());
////            Double DQuantidade = Double.parseDouble(VerQuantidade);
////            Double DSaldo = Double.parseDouble(jTextField_Varieavel_SaldoProduto.getText());
////            Double Resultado;
////            Resultado = DSaldo - DQuantidade;
////            System.err.println("Resultado     -    " + Resultado);
////            if (Resultado == 0.0) {
////                JOptionPane.showMessageDialog(jLabel_Complemento, "Zerando saldo produto");
////            } else if (DQuantidade > DSaldo) {
////                JOptionPane.showMessageDialog(jLabel_Complemento, "Saldo produto insuficiente");
////            } else {
////            }
////            jTextField_Varieavel_SaldoProduto.setText("");
//        }
    }//GEN-LAST:event_jTextField_Quantidade_NotaFocusLost

    private void jButton_Excluir_Movimento_NotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Excluir_Movimento_NotaActionPerformed

//        int resposta = 0;
//        //        String status = jLabel_Status_Nota.getText();
//        resposta = JOptionPane.showConfirmDialog(rootPane, "Deseja realmente excluir/ativar ? ");
//        if (resposta == JOptionPane.YES_OPTION) {
//            Mbeans.setId_mov(Integer.parseInt(jLabel_IdMovimento.getText()));
//            Mbeans.setRegistro_mov(Principal.jLabel_Data.getText() + " " + Principal.jLabel_Hora.getText());
//            Mbeans.setStatus_mov(3);
//            Mbeans.setUsuario_mov(Principal.jLabelNomeUsuario.getText());
//            Mdao.ExcluirMovProduto(Mbeans);
//            System.out.println("Interface.MovimentoCadastroJIF.jButton_Excluir_Movimento_NotaActionPerformed()     ");
//            LimpaCampoProduto();
//            FlagAlterar = 0;
//        }
        jButton_Excluir_Movimento_Nota.setEnabled(false);
    }//GEN-LAST:event_jButton_Excluir_Movimento_NotaActionPerformed

    private void jButton_Fechar_NotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Fechar_NotaActionPerformed

    }//GEN-LAST:event_jButton_Fechar_NotaActionPerformed

    private void jButton_Adicionar_Produto_NotaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton_Adicionar_Produto_NotaKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
//            BotaoAdicionarProduto();
        }
    }//GEN-LAST:event_jButton_Adicionar_Produto_NotaKeyPressed

    private void jButton_Adicionar_Produto_NotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Adicionar_Produto_NotaActionPerformed
//        BotaoAdicionarProduto();
    }//GEN-LAST:event_jButton_Adicionar_Produto_NotaActionPerformed

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
//            InsereDadosNota();
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
//            InsereDadosNota();
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
//            MinhaNaturezaInt = conex.rs.getInt("id_natureza");
            id_nota = conex.rs.getInt("id_nota");
            id_referencia = conex.rs.getInt("id_referencianota");
            jTextArea_Observacao.setText(conex.rs.getString("nota_observacao"));
            MinhaNatureza = conex.rs.getString("tipo_natureza");
            cbNatureza.setSelectedItem(DescNatureza);
//            jComboBox_Tipo_Fornecedor.setSelectedItem(conex.rs.getString("ecft_tipo"));
            cbCliente.setSelectedItem(conex.rs.getString("ecft_nome"));
            System.out.println("Interface.MovimentoCadastroJIF.CarregaSistema()" + conex.rs.getString("ecft_nome"));
//            jComboBox_Documento.setSelectedItem(conex.rs.getString("nota_documento"));
//            jComboBox_Situacao.setSelectedItem(conex.rs.getString("nota_situacao"));
            String nota_situacao_Visualizar = (conex.rs.getString("nota_situacao"));
            if (nota_situacao_Visualizar.equals("7-ENTRADA") | nota_situacao_Visualizar.equals("ENTRADA")) {
                jLabel_Status_Visualizar.setText("      ");
            } else {
                jLabel_Status_Visualizar.setText("Status : " + conex.rs.getString("nota_situacao"));
            }

            txtNota.setText(conex.rs.getString("nota_nota"));
            txtChave.setText(conex.rs.getString("nota_chave"));// 
            txtHora.setText(conex.rs.getString("nota_hora"));
            String insereData = (conex.rs.getString("nota_data"));

            try {
                data = formato.parse(insereData);
            } catch (ParseException exd) {
                System.out.println("erro data " + exd);
            }
            dataNota.setDate(data);
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

        txtNota.setText(null);
        txtChave.setText(null);
        txtHora.setText(null);
//            data = formato.parse(null);
        data = null;
        dataNota.setDate(data);
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

        txtChave.setText(null);
        txtHora.setText(null);
        txtNota.setText(null);
        data = null;
        dataNota.setDate(data);
        LimpaTransporte();
        lblInformacoes.setText("");
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

//    public void InsereDadosNota() {
//        if (jDateChooser_Data_Nota.getDate() == (null)) {
//            MinhaData = ("");
//        } else {
//            data = jDateChooser_Data_Nota.getDate();
//            MinhaData = (formato.format(data));
//        }
//        beans.setId_referencia(id_referencia);
//        beans.setNota_operacao(MinhaNatureza);
//        beans.setNota_registro(Principal.jLabel_Data.getText() + " " + Principal.jLabel_Hora.getText());
//        beans.setNota_status(1);
//        beans.setNota_total(MeuTotal); // ver total
//        beans.setNota_usuario(Principal.jLabelNomeUsuario.getText());
//        beans.setNota_data(MinhaData);
////        beans.setNatureza((String) jComboBox_Natureza.getSelectedItem());//
//        String ConvertNatureza = "" + jComboBox_Natureza_Int.getSelectedItem();
//        MinhaNaturezaInt = (Integer.parseInt(ConvertNatureza));
//        beans.setNatureza(MinhaNaturezaInt);
////        beans.setNota_documento((String) jComboBox_Documento.getSelectedItem());
////        beans.setNota_fornecedor((String) jComboBox_Fornecedor.getSelectedItem());
//        String MeuFornecedorString = "" + jComboBox_Fornecedor_Int.getSelectedItem();
//        MeuFornecedorInt = Integer.parseInt(MeuFornecedorString);
//        beans.setNota_fornecedor(MeuFornecedorInt);
//        MinhaNatureza = "" + jComboBox_Natureza_Tipo.getSelectedItem();
//        if (MinhaNatureza.equals("ENTRADA")) {
//            beans.setNota_situacao(("ENTRADA"));
//        } else {
////            beans.setNota_situacao((String) jComboBox_Situacao.getSelectedItem());
//        }
//        beans.setNota_chave(jTextField_Chave_Nota.getText());
//        beans.setNota_nota(jTextField_Nota.getText());
//        beans.setNota_hora(jTextField_Hora.getText());
//        jTextArea_Observacao.setText(jTextArea_Observacao.getText().toUpperCase());
//        beans.setNota_observacao(jTextArea_Observacao.getText());
//
//        beans.setModalidade("" + jComboBox_modalidade.getSelectedItem());
//        beans.setMotorista(vol_motorista.getText());
//        beans.setTransportadora("" + jComboBox_transportadora.getSelectedItem());
//        beans.setPlaca(vol_placa.getText());
//        beans.setPesoliquido(vol_peso_liquido.getText());
//        beans.setPesobruto(vol_peso_bruto.getText());
//        beans.setUf(vol_uf.getText());
//        beans.setEspecie(vol_especie.getText());
//        beans.setNumeracao(vol_numeracao.getText());
//        beans.setQuantidade(vol_quantidade.getText());
////        String IntTransportadora = "" + jComboBox_transportadoraInt.getSelectedItem();
////        beans.setMotoristaint(Integer.parseInt(IntTransportadora));
//        String IntEmpresa = menu.jLabel_Empresa.getText();
//        beans.setEmpresaint(Integer.parseInt(IntEmpresa));
//        beans.setDatavariavel(jTextField_data_variavel.getText());
//    }
    public void AtualizarTudo() {
//        PreencherFornecedor();
//        PreencherNatureza();
//        PreencherTransporte();
        LimparTabela();
        SelecaoReferencia_Movimento = null;
//        limpaCamposPalete();
//        jLabel_preenche_texto.setText("");
//        BloqueiaCamposPalete();
    }

    private void LimparTabela() {
        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{};
        ModeloTabela modelo = new ModeloTabela(dados, colunas);
//        jTablePalete.setModel(modelo);
        jTableListaProduto_Nota.setModel(modelo);
//        jTableListaProduto_Complemento.setModel(modelo);
//        jTableListaProduto_Palete_Produto.setModel(modelo);
    }

    private void LimparTabelaPalete() {
        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{};
        ModeloTabela modelo = new ModeloTabela(dados, colunas);
//        jTablePalete.setModel(modelo);
//        jTableListaProduto_Nota.setModel(modelo);
//        jTableListaProduto_Complemento.setModel(modelo);
//        jTableListaProduto_Palete_Produto.setModel(modelo);
    }

    public void PreencherFornecedor() {
//        String busca_fornecedor = (String) jComboBox_Tipo_Fornecedor.getSelectedItem();
        String busca_tudo = "FORN/CLIE";
//        if (jLabel_OS.getText() == "Novo") {
//            jComboBox_Fornecedor.removeAllItems();
//        } else {
//        }
        conex_Fornecedor.conexao();
//        if (busca_fornecedor == "FORN/CLIE") {
//            conex_Fornecedor.executaSql("select * from ecft where ecft_tipo != 'EMPRESA' and stecft=1 order by ecft_id asc");
//        } else if (busca_fornecedor == "OUTRO") {
//            conex_Fornecedor.executaSql("select * from ecft where ecft_tipo like '" + busca_fornecedor + "' and stecft=1 order by ecft_id asc");
//        } else {
//            conex_Fornecedor.executaSql("select * from ecft where ecft_tipo like '" + busca_fornecedor + "'or ecft_tipo like '" + busca_tudo + "' and stecft=1 order by ecft_id asc");
//        }
        try {
            conex_Fornecedor.rs.first();
            cbCliente.removeAllItems();
            cbCliente.addItem(" ");
//            jComboBox_Fornecedor_Int.removeAllItems();
//            jComboBox_Fornecedor_Int.addItem("0");
            do {
                cbCliente.addItem(conex_Fornecedor.rs.getString("ecft_nome"));
//                jComboBox_Fornecedor_Int.addItem(conex_Fornecedor.rs.getString("sis_ecft"));
                cbCliente.setBackground(Color.GRAY);
            } while (conex_Fornecedor.rs.next());
        } catch (SQLException ex) {
            cbCliente.setBackground(Color.red);
        }
//        jComboBox_Fornecedor.setSelectedItem(" ");
        conex_Fornecedor.desconecta();
        cbCliente.setBackground(Color.yellow);

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

            lblInformacoes.setText("<html><b> Total " + FormatoReala + "  </b><br>    " + FormatoReal + "</html>");
            System.err.println("PreencherMeuTotal()                  CERTO ");
            System.err.println(" Total   -           FormatoReala" + FormatoReala + "    FormatoReal    " + FormatoReal);
            MeuTotal = FormatoReal;
            System.err.println(" MeuTotal    -     FormatoReal          -        " + FormatoReal);
        } catch (SQLException ex) {
            System.err.println("PreencherMeuTotal()                  ERRO ");
            lblInformacoes.setText(" Total " + "  0    " + "R$ 0,00");
            MeuTotal = "0";
        }
        conex_MeuTotal.desconecta();

    }

//    public void PreencherNatureza() {
//
//        conex_Natureza.conexao();
//        conex_Natureza.executaSql("SELECT id_natureza, id_referencianatureza, tipo_natureza, desc_natureza, registro_natureza, \n"
//                + "       usuario_natureza\n"
//                + "  FROM natureza  where stnat=1  order by id_referencianatureza asc");
//        try {
//            conex_Natureza.rs.first();
//            cbNatureza.removeAllItems();
//            cbNatureza.addItem(" ");
//
//            do {
//                String DescNatureza = (conex_Natureza.rs.getString("desc_natureza"));
//                String IdNatureza = (conex_Natureza.rs.getString("id_referencianatureza"));
//                String TipoNatureza = (conex_Natureza.rs.getString("tipo_natureza"));
//                DescNatureza = DescNatureza + " | " + IdNatureza;
//                cbNatureza.addItem(DescNatureza);
//
//                cbNatureza.setBackground(Color.LIGHT_GRAY);
//            } while (conex_Natureza.rs.next());
//        } catch (SQLException ex) {
//            cbNatureza.setBackground(Color.red);
//        }
//        conex_Natureza.desconecta();
//        cbNatureza.setSelectedItem(" ");
//        cbNatureza.setBackground(Color.yellow);
//    }

    public void PreencherTransporte() {
        ComboFocu = 0;
        conex_Transporte.conexao();
        conex_Transporte.executaSql("select * from ecft where ecft_tipo = 'TRANSPORTE' and stecft=1 order by ecft_id asc");
        try {
            conex_Transporte.rs.first();
            jComboBox_transportadora.removeAllItems();
//            jComboBox_transportadoraInt.removeAllItems();
//            jComboBox_transportadora.addItem("NÃO DEFINIDO");
//            jComboBox_transportadoraInt.addItem("0");
            do {
                jComboBox_transportadora.addItem(conex_Transporte.rs.getString("ecft_nome"));
//                jComboBox_transportadoraInt.addItem(conex_Transporte.rs.getString("sis_ecft"));
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
//        jComboBox_transportadoraInt.setSelectedItem("0");
    }

    public void RecebeIdNota(String IdNota) {
        MinhaIdNota = IdNota;
//        JOptionPane.showMessageDialog(rootPane, MinhaIdNota);
    }

    public void RecebeReferenciaMovimento(String ReferenciaMovi) {
//        Referencia_Movimento = ReferenciaMovi;
//        JOptionPane.showMessageDialog(rootPane, Referencia_Movimento); jComboBox_Situacao
    }

    public void RecebeDescricaoBusca(String MinhaBusca) {
        jTextField_Busca_Produto_Nota.setText(MinhaBusca);
    }

    public void ManipulaData() {
        String MenuMinhaData;
        Date dataSistema = dataNota.getDate();
        if (dataSistema == null) {
        } else {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
            MenuMinhaData = (formato.format(dataSistema));
//            jTextField_data_variavel.setText(MenuMinhaData);
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
//            MinhaNaturezaInt = conex.rs.getInt("id_natureza");
            MinhaNatureza = conex.rs.getString("tipo_natureza");
            cbNatureza.setSelectedItem(DescNatureza);
//            jComboBox_Tipo_Fornecedor.setSelectedItem(conex.rs.getString("ecft_tipo"));
            cbCliente.setSelectedItem(conex.rs.getString("ecft_nome"));
//            jComboBox_Documento.setSelectedItem(conex.rs.getString("nota_documento"));
//            jComboBox_Situacao.setSelectedItem(conex.rs.getString("nota_situacao"));
            String nota_situacao_Visualizar = (conex.rs.getString("nota_situacao"));
            if (nota_situacao_Visualizar.equals("7-ENTRADA") | nota_situacao_Visualizar.equals("ENTRADA")) {
                jLabel_Status_Visualizar.setText("      ");
            } else {
                jLabel_Status_Visualizar.setText("Status : " + conex.rs.getString("nota_situacao"));
            }
            txtNota.setText(conex.rs.getString("nota_nota"));
            txtChave.setText(conex.rs.getString("nota_chave"));// 
            txtHora.setText(conex.rs.getString("nota_hora"));
            String insereData = (conex.rs.getString("nota_data"));
            try {
                data = formato.parse(insereData);
            } catch (ParseException exd) {
                System.out.println("erro data " + exd);
            }
            dataNota.setDate(data);
        } catch (SQLException ex) {
            System.out.println("erro  CarregaSistema   " + ex);
        }
        conex.desconecta();
        this.setTitle("Cadastro Movimento [ " + MinhaNatureza + " ] Id Referencia : " + id_referencia);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDataAtual;
    public static javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnHoraAtual;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cbCliente;
    public static javax.swing.JComboBox<Natureza> cbNatureza;
    private com.toedter.calendar.JDateChooser dataNota;
    private javax.swing.JButton jButton_Adicionar_Produto_Nota;
    private javax.swing.JButton jButton_Excluir_Movimento_Nota;
    public static javax.swing.JButton jButton_Fechar_Nota;
    private javax.swing.JComboBox<String> jComboBox_modalidade;
    private javax.swing.JComboBox<String> jComboBox_transportadora;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    public static javax.swing.JLabel jLabelMeuSaldoProduto;
    private javax.swing.JLabel jLabel_Status_Visualizar;
    public static javax.swing.JLabel jLabel_Texto_Produto1;
    private javax.swing.JLabel jLabel_Titulo1;
    private javax.swing.JLabel jLabel_Titulo2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JRadioButton jRadioButton_ativa_Vizualizar;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableListaProduto_Nota;
    private javax.swing.JTextArea jTextArea_Observacao;
    public static javax.swing.JTextField jTextField_Busca_Produto_Nota;
    public static javax.swing.JTextField jTextField_Quantidade_Nota;
    private javax.swing.JLabel lblInformacoes;
    private javax.swing.JPanel pnDados;
    private javax.swing.JPanel pnInformacao;
    private javax.swing.JPanel pnPrincipal;
    private javax.swing.JPanel pnTopo;
    private javax.swing.JPanel pnTransporte;
    public static javax.swing.JTextField txtChave;
    private javax.swing.JTextField txtHora;
    private javax.swing.JTextField txtNota;
    private javax.swing.JTextField vol_especie;
    private javax.swing.JTextField vol_motorista;
    private javax.swing.JTextField vol_numeracao;
    private javax.swing.JTextField vol_peso_bruto;
    private javax.swing.JTextField vol_peso_liquido;
    private javax.swing.JTextField vol_placa;
    private javax.swing.JTextField vol_quantidade;
    private javax.swing.JTextField vol_uf;
    // End of variables declaration//GEN-END:variables

    public JPanel getPnDados() {
        return pnDados;
    }

    public JPanel getPnInformacao() {
        return pnInformacao;
    }

    public JPanel getPnPrincipal() {
        return pnPrincipal;
    }

    public JPanel getPnTopo() {
        return pnTopo;
    }

    public JPanel getPnTransporte() {
        return pnTransporte;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public JButton getBtnDataAtual() {
        return btnDataAtual;
    }

    public static JButton getBtnExcluir() {
        return btnExcluir;
    }

    public JButton getBtnHoraAtual() {
        return btnHoraAtual;
    }

    public JButton getBtnSalvar() {
        return btnSalvar;
    }

    public JComboBox<String> getCbCliente() {
        return cbCliente;
    }

     

    public JDateChooser getDataNota() {
        return dataNota;
    }

    public static JTextField getTxtChave() {
        return txtChave;
    }

    public JTextField getTxtHora() {
        return txtHora;
    }

    public JTextField getTxtNota() {
        return txtNota;
    }

    public static JComboBox<Natureza> getCbNatureza() {
        return cbNatureza;
    }

}
