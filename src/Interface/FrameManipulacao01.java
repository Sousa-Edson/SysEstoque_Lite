/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import ConectaBanco.ConexaoBD;
import Consulta.JDialogBuscaProduto;
import Consulta.JDialogBuscaProduto2;
import Consulta.JDialogComplementar;
import Consulta.JDialogComplementar2;
import static Interface.MovimentoCadastroJIF.jComboBox_Situacao;
import static Interface.MovimentoCadastroJIF.jLabelMeuSaldoProduto;
import static Interface.MovimentoCadastroJIF.jLabel_Calculado;
import static Interface.MovimentoCadastroJIF.jLabel_Complemento;
import static Interface.MovimentoCadastroJIF.jLabel_IdMovimento;
import static Interface.MovimentoCadastroJIF.jLabel_IdProduto;
import static Interface.MovimentoCadastroJIF.jLabel_Setor;
import static Interface.MovimentoCadastroJIF.jTextField_Busca_Produto_Nota;
import static Interface.MovimentoCadastroJIF.jTextField_Quantidade_Nota;
import ModeloBeans.BeansPalete;
import ModeloBeans.Beans_Movimento;
import ModeloBeans.Beans_Nota;
import ModeloBeans.Beans_Transporte;
import ModeloBeans.ModeloTabela;
import ModeloDao.Dao_Movimento;
import ModeloDao.Dao_Nota;
import ModeloDao.Dao_Palete;
import ModeloDao.Dao_Transporte;
import java.awt.Color;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

/**
 *
 * @author edson
 */
/// preencher
public class FrameManipulacao01 extends javax.swing.JFrame {

    ConexaoBD conex_Fornecedor = new ConexaoBD();
    ConexaoBD conex_Natureza = new ConexaoBD();
    ConexaoBD conex_SelecionarNatureza = new ConexaoBD();
    ConexaoBD conex_SelecionarFornecedor = new ConexaoBD();
    ConexaoBD conex_Movimento = new ConexaoBD();
    ConexaoBD conex_MeuTotal = new ConexaoBD();
    ConexaoBD conex_Transporte = new ConexaoBD();
    ConexaoBD conex_Palete = new ConexaoBD();

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
    JDialogComplementar2 Complementar = new JDialogComplementar2(menu, rootPaneCheckingEnabled);
    JDialogBuscaProduto2 BP = new JDialogBuscaProduto2(menu, rootPaneCheckingEnabled);

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
    String stnota, stmovimento, stproduto, modo;

    /**
     * Creates new form FrameMovimentoComplemento
     */
    public FrameManipulacao01() {
//        vol_transportadora.setVisible(false);

        initComponents();
        jLabel_Complemento.setVisible(false);
        jLabel_Setor.setVisible(false);
        jLabel_IdProduto.setVisible(false);
        jLabel_IdMovimento.setVisible(false);
        jLabel_Calculado.setVisible(false);
        jComboBox_Situacao.setVisible(false);
//        jComboBox_transportadoraInt.setVisible(false);
        jTextField_data_variavel.setVisible(false);
        jTextField_Fragmento_Variavel.setVisible(false);
        jTextField_Varieavel_SaldoProduto.setVisible(false);
        jComboBox_Fornecedor_Int.setVisible(false);
        jComboBox_Natureza_Tipo.setVisible(false);
        jComboBox_Natureza_Int.setVisible(false);
        jTextField_Carrega_Natureza.setVisible(false);
        jTextField_Carrega_Nota.setVisible(false);
        jLabel_Status_Visualizar.setVisible(false);
        jPanel1.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jPanel2 = new javax.swing.JPanel();
        jComboBox_Natureza_Int = new javax.swing.JComboBox<>();
        jComboBox_Natureza_Tipo = new javax.swing.JComboBox<>();
        jComboBox_Fornecedor_Int = new javax.swing.JComboBox<>();
        jTextField_data_variavel = new javax.swing.JTextField();
        jTextField_Fragmento_Variavel = new javax.swing.JTextField();
        jTextField_Varieavel_SaldoProduto = new javax.swing.JTextField();
        jLabel_Complemento = new javax.swing.JLabel();
        jLabel_Setor = new javax.swing.JLabel();
        jLabel_IdProduto = new javax.swing.JLabel();
        jLabel_IdMovimento = new javax.swing.JLabel();
        jLabel_Calculado = new javax.swing.JLabel();
        jComboBox_Situacao = new javax.swing.JComboBox<>();
        jLabel_Status_Visualizar = new javax.swing.JLabel();
        jTextField_Carrega_Nota = new javax.swing.JTextField();
        jTextField_Carrega_Natureza = new javax.swing.JTextField();
        jTextField_Busca = new javax.swing.JTextField();
        jLabel_Texto_Produto5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel_Texto_Produto2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel_Texto_Produto3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel_Texto_Produto4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel_Texto_Produto1 = new javax.swing.JLabel();
        jTextField_Busca_Produto_Nota = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jTextField_Quantidade_Nota = new javax.swing.JTextField();
        jLabelMeuSaldoProduto = new javax.swing.JLabel();
        jButton_Excluir_Movimento_Nota = new javax.swing.JButton();
        jButton_Fechar_Nota = new javax.swing.JButton();
        jButton_Adicionar_Produto_Nota = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jTextField_Quantidade_Calculado = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableListaProduto_Nota = new javax.swing.JTable();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jCheckBoxMenuItem_Modo01 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem_Modo02 = new javax.swing.JCheckBoxMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem2 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem3 = new javax.swing.JCheckBoxMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jCheckBoxMenuItem4 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem5 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem6 = new javax.swing.JCheckBoxMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jCheckBoxMenuItem7 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem8 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem9 = new javax.swing.JCheckBoxMenuItem();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Frame manipulacao 01");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(204, 0, 255));

        jLabel_Complemento.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel_Setor.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel_IdProduto.setText("0");
        jLabel_IdProduto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel_IdMovimento.setText("0");
        jLabel_IdMovimento.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel_Calculado.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

        jLabel_Status_Visualizar.setText("Status :");
        jLabel_Status_Visualizar.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel_Texto_Produto5.setText("Busca :");

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel_Texto_Produto2.setText("Id produto:");

        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField2FocusLost(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });

        jLabel_Texto_Produto3.setText("Mov:");

        jLabel_Texto_Produto4.setText("OS:");

        jLabel_Texto_Produto1.setText("Produto :");

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

        jLabelMeuSaldoProduto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton_Excluir_Movimento_Nota.setForeground(new java.awt.Color(255, 51, 51));
        jButton_Excluir_Movimento_Nota.setText("Excluir");
        jButton_Excluir_Movimento_Nota.setToolTipText("Exclui Produto");
        jButton_Excluir_Movimento_Nota.setEnabled(false);
        jButton_Excluir_Movimento_Nota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Excluir_Movimento_NotaActionPerformed(evt);
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

        jButton_Adicionar_Produto_Nota.setForeground(new java.awt.Color(255, 51, 51));
        jButton_Adicionar_Produto_Nota.setText("Adicionar");
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

        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Modo 01");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setText("Modo 02");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jTextField_Quantidade_Calculado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_Quantidade_CalculadoFocusLost(evt);
            }
        });
        jTextField_Quantidade_Calculado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_Quantidade_CalculadoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel_Texto_Produto1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_Texto_Produto2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_Texto_Produto3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_Texto_Produto4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 215, Short.MAX_VALUE)
                        .addComponent(jButton_Adicionar_Produto_Nota, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Fechar_Nota)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Excluir_Movimento_Nota))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_Busca_Produto_Nota, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_Quantidade_Nota, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_Quantidade_Calculado, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(jLabelMeuSaldoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton_Excluir_Movimento_Nota, jButton_Fechar_Nota});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jTextField2, jTextField3, jTextField4});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_Adicionar_Produto_Nota, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_Fechar_Nota, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_Excluir_Movimento_Nota, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_Texto_Produto2)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_Texto_Produto3)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_Texto_Produto4)
                            .addComponent(jRadioButton1)
                            .addComponent(jRadioButton2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField_Busca_Produto_Nota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel_Texto_Produto1))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField_Quantidade_Nota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField_Quantidade_Calculado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel20))
                    .addComponent(jLabelMeuSaldoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabelMeuSaldoProduto, jTextField_Quantidade_Nota});

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel_Texto_Produto5)
                                .addGap(14, 14, 14)
                                .addComponent(jTextField_Busca, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel_Status_Visualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel_Complemento, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_Setor, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_IdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_IdMovimento, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_Calculado, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox_Situacao, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox_Natureza_Int, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox_Natureza_Tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox_Fornecedor_Int, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField_data_variavel, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_Fragmento_Variavel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_Varieavel_SaldoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField_Carrega_Nota, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_Carrega_Natureza, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_Busca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_Texto_Produto5)
                    .addComponent(jButton1)
                    .addComponent(jLabel_Status_Visualizar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_Complemento, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel_Setor, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel_IdProduto)
                        .addComponent(jLabel_IdMovimento)
                        .addComponent(jLabel_Calculado))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox_Situacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox_Natureza_Int, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox_Natureza_Tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox_Fornecedor_Int, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField_data_variavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField_Fragmento_Variavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField_Varieavel_SaldoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField_Carrega_Nota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField_Carrega_Natureza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel_Calculado, jLabel_IdMovimento});

        jMenu3.setText("Modo");

        jCheckBoxMenuItem_Modo01.setSelected(true);
        jCheckBoxMenuItem_Modo01.setText("Modo 01");
        jCheckBoxMenuItem_Modo01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem_Modo01ActionPerformed(evt);
            }
        });
        jMenu3.add(jCheckBoxMenuItem_Modo01);

        jCheckBoxMenuItem_Modo02.setText("Modo 02");
        jCheckBoxMenuItem_Modo02.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem_Modo02ActionPerformed(evt);
            }
        });
        jMenu3.add(jCheckBoxMenuItem_Modo02);

        jMenuBar2.add(jMenu3);

        jMenu4.setText("Visualizar");

        jMenu5.setText("Nota");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("Ativo");
        jCheckBoxMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem1ActionPerformed(evt);
            }
        });
        jMenu5.add(jCheckBoxMenuItem1);

        jCheckBoxMenuItem2.setText("Alterado");
        jCheckBoxMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem2ActionPerformed(evt);
            }
        });
        jMenu5.add(jCheckBoxMenuItem2);

        jCheckBoxMenuItem3.setText("Excluido");
        jCheckBoxMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem3ActionPerformed(evt);
            }
        });
        jMenu5.add(jCheckBoxMenuItem3);

        jMenu4.add(jMenu5);

        jMenu6.setText("Movimento");

        jCheckBoxMenuItem4.setSelected(true);
        jCheckBoxMenuItem4.setText("Ativo");
        jCheckBoxMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem4ActionPerformed(evt);
            }
        });
        jMenu6.add(jCheckBoxMenuItem4);

        jCheckBoxMenuItem5.setText("Alterado");
        jCheckBoxMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem5ActionPerformed(evt);
            }
        });
        jMenu6.add(jCheckBoxMenuItem5);

        jCheckBoxMenuItem6.setText("Excluido");
        jCheckBoxMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem6ActionPerformed(evt);
            }
        });
        jMenu6.add(jCheckBoxMenuItem6);

        jMenu4.add(jMenu6);

        jMenu7.setText("Produto");

        jCheckBoxMenuItem7.setSelected(true);
        jCheckBoxMenuItem7.setText("Ativo");
        jCheckBoxMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem7ActionPerformed(evt);
            }
        });
        jMenu7.add(jCheckBoxMenuItem7);

        jCheckBoxMenuItem8.setText("Alterado");
        jCheckBoxMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem8ActionPerformed(evt);
            }
        });
        jMenu7.add(jCheckBoxMenuItem8);

        jCheckBoxMenuItem9.setText("Excluido");
        jCheckBoxMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem9ActionPerformed(evt);
            }
        });
        jMenu7.add(jCheckBoxMenuItem9);

        jMenu4.add(jMenu7);

        jMenuBar2.add(jMenu4);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
//MovimentoCadastroJIF.jButton_Fechar_Nota.doClick();
    }//GEN-LAST:event_formWindowClosing

    private void jCheckBoxMenuItem_Modo01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem_Modo01ActionPerformed
        if (jCheckBoxMenuItem_Modo01.isSelected()) {
            jCheckBoxMenuItem_Modo01.setSelected(true);
            jCheckBoxMenuItem_Modo02.setSelected(false);
        } else {
            jCheckBoxMenuItem_Modo02.setSelected(true);
            jCheckBoxMenuItem_Modo01.setSelected(false);
        }
        PreencherTabela();
    }//GEN-LAST:event_jCheckBoxMenuItem_Modo01ActionPerformed

    private void jCheckBoxMenuItem_Modo02ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem_Modo02ActionPerformed
        if (jCheckBoxMenuItem_Modo02.isSelected()) {
            jCheckBoxMenuItem_Modo02.setSelected(true);
            jCheckBoxMenuItem_Modo01.setSelected(false);
        } else {
            jCheckBoxMenuItem_Modo01.setSelected(true);
            jCheckBoxMenuItem_Modo02.setSelected(false);
        }
        PreencherTabela();
    }//GEN-LAST:event_jCheckBoxMenuItem_Modo02ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        LimpaCampoProduto();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox_SituacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_SituacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_SituacaoActionPerformed

    private void jComboBox_SituacaoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_SituacaoItemStateChanged
        jLabel_Status_Visualizar.setText("Status : " + jComboBox_Situacao.getSelectedItem());
    }//GEN-LAST:event_jComboBox_SituacaoItemStateChanged

    private void jTableListaProduto_NotaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableListaProduto_NotaMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableListaProduto_NotaMouseEntered

    private void jTableListaProduto_NotaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableListaProduto_NotaMouseClicked
       String busca_nome = "" + jTableListaProduto_Nota.getValueAt(jTableListaProduto_Nota.getSelectedRow(), 2);
       jTextField_Busca.setText(busca_nome);
        System.err.println("busca_nome = "+busca_nome);
        if (evt.getClickCount() == 2) {
            jPanel1.setVisible(true);
            if (jTableListaProduto_Nota.isEnabled()) {
                String busca_Lista = "" + jTableListaProduto_Nota.getValueAt(jTableListaProduto_Nota.getSelectedRow(), 8);
                //            jLabel_Id_Mov.setText((busca_Lista));
                //            jLabelIdMovi.setText((busca_Lista));
                String busca_item = "" + jTableListaProduto_Nota.getValueAt(jTableListaProduto_Nota.getSelectedRow(), 0);
                jLabel_IdMovimento.setText(busca_item);
                System.out.println("Interface.FrameManipulacao01.jTableListaProduto_NotaMouseClicked()   " + busca_item);
                //            Evento_Busca_Movimento_Nota();
                //            flag_salvar = 2;
                //  jTextField_Quantidade_Nota.requestFocus();
                jButton_Excluir_Movimento_Nota.setEnabled(true);
                //            jButton_Adicionar_Produto_Nota.setText("Alterar produto");
                PreencheDadosAlterar();
                FlagAlterar = 1;
                Referencia_Movimento = "" + jTableListaProduto_Nota.getValueAt(jTableListaProduto_Nota.getSelectedRow(), 0);
                //                        JOptionPane.showMessageDialog(rootPane, Referencia_Movimento);
            } else {
            }
            jPanel3.setVisible(false);
        }
    }//GEN-LAST:event_jTableListaProduto_NotaMouseClicked

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

        if (evt.getKeyCode() == evt.VK_F12) {
            if (jLabel_Calculado.getText().equals("")) {
                jLabel_Calculado.setText(jTextField_Quantidade_Nota.getText());
            } else {
            }
        }

        if (evt.getKeyCode() == evt.VK_DOWN) {
            //            jFormattedTextFieldCep.requestFocus();
        }
        if (evt.getKeyCode() == evt.VK_UP) {
            //            jTextField_Busca_Produto_PRO.requestFocus();
        }
    }//GEN-LAST:event_jTextField_Quantidade_NotaKeyPressed

    private void jTextField_Quantidade_NotaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_Quantidade_NotaFocusLost
        int S_Natureza_Int = 0;
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
//        String S_Natureza = "" + jComboBox_Natureza_Int.getSelectedItem();
//        S_Natureza_Int = Integer.parseInt(S_Natureza);
        //         }
        //         catch( Exception ex){JOptionPane.showMessageDialog(jLabel_Complemento, "erro "+ex);}
        //        System.out.println("Natureza int  " + S_Natureza_Int + "    S_Natureza    " + S_Natureza + " jComboBox_Natureza ==  " + jComboBox_Natureza.getSelectedItem());
        if (VerQuantidade.isEmpty()) {
            System.out.println("Resultado     -  vazio  ");
        } else if (VerQuantidade.equals("null") | VerQuantidade.equals(null)) {
            System.out.println("Resultado     -  null  ");
        } else if (S_Natureza_Int == 1) {
            System.out.println("Interface.MovimentoCadastroJIF.jTextField_Quantidade_NotaFocusLost()      -            Tipo Entrada ok");
        } else {
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
        }
    }//GEN-LAST:event_jTextField_Quantidade_NotaFocusLost

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

    private void jButton_Fechar_NotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Fechar_NotaActionPerformed
        ;
        LimpaCampoProduto();
        FlagAlterar = 0;
    }//GEN-LAST:event_jButton_Fechar_NotaActionPerformed

    private void jButton_Adicionar_Produto_NotaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton_Adicionar_Produto_NotaKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            BotaoAdicionarProduto();
        }
    }//GEN-LAST:event_jButton_Adicionar_Produto_NotaKeyPressed

    private void jButton_Adicionar_Produto_NotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Adicionar_Produto_NotaActionPerformed
        BotaoAdicionarProduto();
    }//GEN-LAST:event_jButton_Adicionar_Produto_NotaActionPerformed

    private void jCheckBoxMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem1ActionPerformed
        if (jCheckBoxMenuItem1.isSelected()) {
            jCheckBoxMenuItem1.setSelected(true);
            jCheckBoxMenuItem2.setSelected(false);
            jCheckBoxMenuItem3.setSelected(false);
        } else {
            jCheckBoxMenuItem2.setSelected(true);
            jCheckBoxMenuItem3.setSelected(false);
            jCheckBoxMenuItem1.setSelected(false);
        }
        PreencherTabela();
    }//GEN-LAST:event_jCheckBoxMenuItem1ActionPerformed

    private void jCheckBoxMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem4ActionPerformed
        if (jCheckBoxMenuItem4.isSelected()) {
            jCheckBoxMenuItem4.setSelected(true);
            jCheckBoxMenuItem5.setSelected(false);
            jCheckBoxMenuItem6.setSelected(false);
        } else {
            jCheckBoxMenuItem5.setSelected(true);
            jCheckBoxMenuItem6.setSelected(false);
            jCheckBoxMenuItem4.setSelected(false);
        }
        PreencherTabela();
    }//GEN-LAST:event_jCheckBoxMenuItem4ActionPerformed

    private void jCheckBoxMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem5ActionPerformed
        if (jCheckBoxMenuItem5.isSelected()) {
            jCheckBoxMenuItem5.setSelected(true);
            jCheckBoxMenuItem4.setSelected(false);
            jCheckBoxMenuItem6.setSelected(false);
        } else {
            jCheckBoxMenuItem6.setSelected(true);
            jCheckBoxMenuItem6.setSelected(false);
            jCheckBoxMenuItem5.setSelected(false);
        }
        PreencherTabela();
    }//GEN-LAST:event_jCheckBoxMenuItem5ActionPerformed

    private void jCheckBoxMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem6ActionPerformed
        if (jCheckBoxMenuItem6.isSelected()) {
            jCheckBoxMenuItem6.setSelected(true);
            jCheckBoxMenuItem5.setSelected(false);
            jCheckBoxMenuItem4.setSelected(false);
        } else {
            jCheckBoxMenuItem4.setSelected(true);
            jCheckBoxMenuItem6.setSelected(false);
            jCheckBoxMenuItem5.setSelected(false);
        }
        PreencherTabela();
    }//GEN-LAST:event_jCheckBoxMenuItem6ActionPerformed

    private void jCheckBoxMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem2ActionPerformed
        if (jCheckBoxMenuItem2.isSelected()) {
            jCheckBoxMenuItem2.setSelected(true);
            jCheckBoxMenuItem1.setSelected(false);
            jCheckBoxMenuItem3.setSelected(false);
        } else {
            jCheckBoxMenuItem1.setSelected(true);
            jCheckBoxMenuItem3.setSelected(false);
            jCheckBoxMenuItem2.setSelected(false);
        }
        PreencherTabela();
    }//GEN-LAST:event_jCheckBoxMenuItem2ActionPerformed

    private void jCheckBoxMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem3ActionPerformed
        if (jCheckBoxMenuItem3.isSelected()) {
            jCheckBoxMenuItem3.setSelected(true);
            jCheckBoxMenuItem1.setSelected(false);
            jCheckBoxMenuItem2.setSelected(false);
        } else {
            jCheckBoxMenuItem1.setSelected(true);
            jCheckBoxMenuItem3.setSelected(false);
            jCheckBoxMenuItem2.setSelected(false);
        }
        PreencherTabela();
    }//GEN-LAST:event_jCheckBoxMenuItem3ActionPerformed

    private void jCheckBoxMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem7ActionPerformed
        if (jCheckBoxMenuItem7.isSelected()) {
            jCheckBoxMenuItem7.setSelected(true);
            jCheckBoxMenuItem8.setSelected(false);
            jCheckBoxMenuItem9.setSelected(false);
        } else {
            jCheckBoxMenuItem8.setSelected(true);
            jCheckBoxMenuItem7.setSelected(false);
            jCheckBoxMenuItem9.setSelected(false);
        }
        PreencherTabela();
    }//GEN-LAST:event_jCheckBoxMenuItem7ActionPerformed

    private void jCheckBoxMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem8ActionPerformed
        if (jCheckBoxMenuItem8.isSelected()) {
            jCheckBoxMenuItem8.setSelected(true);
            jCheckBoxMenuItem7.setSelected(false);
            jCheckBoxMenuItem9.setSelected(false);
        } else {
            jCheckBoxMenuItem7.setSelected(true);
            jCheckBoxMenuItem8.setSelected(false);
            jCheckBoxMenuItem9.setSelected(false);
        }
        PreencherTabela();
    }//GEN-LAST:event_jCheckBoxMenuItem8ActionPerformed

    private void jCheckBoxMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem9ActionPerformed
        if (jCheckBoxMenuItem9.isSelected()) {
            jCheckBoxMenuItem9.setSelected(true);
            jCheckBoxMenuItem8.setSelected(false);
            jCheckBoxMenuItem7.setSelected(false);
        } else {
            jCheckBoxMenuItem7.setSelected(true);
            jCheckBoxMenuItem8.setSelected(false);
            jCheckBoxMenuItem9.setSelected(false);
        }
        PreencherTabela();
    }//GEN-LAST:event_jCheckBoxMenuItem9ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        if (jRadioButton1.isSelected()) {
            jRadioButton1.setSelected(true);
            jRadioButton2.setSelected(false);
        } else {
            jRadioButton1.setSelected(false);
            jRadioButton2.setSelected(true);
        }
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        if (jRadioButton2.isSelected()) {
            jRadioButton2.setSelected(true);
            jRadioButton1.setSelected(false);
        } else {
            jRadioButton2.setSelected(false);
            jRadioButton1.setSelected(true);
        }
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jTextField_Quantidade_CalculadoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_Quantidade_CalculadoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Quantidade_CalculadoFocusLost

    private void jTextField_Quantidade_CalculadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_Quantidade_CalculadoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Quantidade_CalculadoKeyPressed

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        String s = jTextField2.getText();
        if (evt.getKeyCode() == evt.VK_ENTER) {
            if (s.matches("^[0-9]*$")) {
                jLabel_IdProduto.setText(s);
                BuscaProdutoId();
            } else {
            }
        }
    }//GEN-LAST:event_jTextField2KeyPressed

    private void jTextField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusLost
        String s = jTextField2.getText();
        if (s.matches("^[0-9]*$")) {
            jLabel_IdProduto.setText(s);
            BuscaProdutoId();
        } else {
        }       // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2FocusLost

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameManipulacao01.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameManipulacao01.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameManipulacao01.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameManipulacao01.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameManipulacao01().setVisible(true);
            }
        });
    }

    public void LimpaCampoProduto() {
        Id_Prod_Ent = 0;
        jTextField_Busca_Produto_Nota.setText(null);
        jTextField_Quantidade_Nota.setText(null);
        jTextField_Quantidade_Calculado.setText(null);
        jTextField_Busca_Produto_Nota.requestFocus();
        jLabel_IdProduto.setText("0");
        jLabel_Calculado.setText("");
        jLabel_Complemento.setText("");
        jLabel_Setor.setText("");
        jTextField_Busca_Produto_Nota.setBackground(Color.white);
        jTextField_Quantidade_Nota.setBackground(Color.white);
        PreencherTabela();
    }

    public void PreencherTabela() {
        PreencherTabelaMovimento();
        jPanel3.setVisible(true);
//        PreencherTabelaComplemento();
//        PreencherTabelaProdutoPalete();
    }

    public void verificaSelecao() {
        if (jCheckBoxMenuItem1.isSelected()) {
            stnota = "1";
        } else if (jCheckBoxMenuItem3.isSelected()) {
            stnota = "2";
        } else if (jCheckBoxMenuItem3.isSelected()) {
            stnota = "3";
        } else {
            stnota = "1";
        }
        if (jCheckBoxMenuItem4.isSelected()) {
            stmovimento = "1";
        } else if (jCheckBoxMenuItem5.isSelected()) {
            stmovimento = "2";
        } else if (jCheckBoxMenuItem6.isSelected()) {
            stmovimento = "3";
        } else {
            stmovimento = "1";
        }
        if (jCheckBoxMenuItem7.isSelected()) {
            stproduto = "1";
        } else if (jCheckBoxMenuItem8.isSelected()) {
            stproduto = "2";
        } else if (jCheckBoxMenuItem9.isSelected()) {
            stproduto = "3";
        } else {
            stproduto = "1";
        }
        if (jCheckBoxMenuItem_Modo01.isSelected()) {
            modo = "1";
        } else if (jCheckBoxMenuItem_Modo02.isSelected()) {
            modo = "2";
        } else {
            modo = "1";
            System.out.print("erro vazio");
        }
    }

    public void PreencherTabelaMovimento() {
        verificaSelecao();
        jPanel1.setVisible(false);
        jLabelMeuSaldoProduto.setText("");
//        id_referencia = Integer.parseInt(jTextField_Carrega_Nota.getText());
        // "+jTextField_Busca.getText()+"
        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{"Id mov", "Id  produto", "Descrição", "Unid", "Qtd", "Calc", "V.Unit", "V.Total", "", "StNota", "Nota Os", "StMovimento", "Modo", "StProduto"};// "Usuario", "Registro"
        conexTabela.conexao();

        conexTabela.executaSql2("select * from nota inner join ecft on fornecedorint=ecft_id\n"
                + "inner join natureza on naturezaint=id_natureza\n"
                + "inner join movprodutobase on nota_mov = id_referencianota\n"
                + "inner join produto on id_prod_ent=sis_prod\n"
                + "inner join unidade on idunid = id_referenciaunidade\n"
                + "where (coalesce((id_referenciaNota)) ||' '||coalesce(UPPER(tipo_prod))||' '||\n"
                + "coalesce(UPPER(nome_prod)) ||' '|| coalesce(UPPER(edicao_prod))||' '|| coalesce(UPPER(edicao_prod))||' '||coalesce((nota_documento))||' '||\n"
                + "coalesce(UPPER(nota_nota)) ||' '||  coalesce(UPPER(nota_observacao))||' '|| coalesce((nota_data)) )\n"
                + "ilike '%" + jTextField_Busca.getText() + "%' and stnota ='" + stnota + "' and modo_mov='" + modo + "' "
                + "and stprod='" + stproduto + "' and stmovimento='" + stmovimento + "' and stunid=1 order by id_referenciaNota  desc ");

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
                descricao = " " + conexTabela.rs.getString("tipo_prod") + " " + conexTabela.rs.getString("nome_prod") +" " + edicao;
                descricao = descricao.toUpperCase();
                texto_movimento = "" + descricao + " " + conexTabela.rs.getString("complemento_mov") + " " + conexTabela.rs.getString("destino_mov");;
                dados.add(new Object[]{
                    conexTabela.rs.getInt("id_mov"), conexTabela.rs.getInt("id_prod_ent"), texto_movimento, conexTabela.rs.getString("sigla_unidade"),
                    conexTabela.rs.getString("qtd_prod_ex"), conexTabela.rs.getString("qtd_calc_ex"), conexTabela.rs.getString("valor_moeda"),
                    conexTabela.rs.getString("total_mov"), conexTabela.rs.getString("sistema_mov"),
                    conexTabela.rs.getString("stnota"), conexTabela.rs.getString("id_referenciaNota"),
                    conexTabela.rs.getString("stmovimento"), conexTabela.rs.getString("modo_mov"), conexTabela.rs.getString("stprod")});
            } while (conexTabela.rs.next());
            System.out.println("preencher tabela ok");
        } catch (SQLException ex) {
            System.out.println("erro tabela\n" + ex);
            if (jCheckBoxMenuItem_Modo01.isSelected()) {
                jCheckBoxMenuItem_Modo02.setSelected(true);
                jCheckBoxMenuItem_Modo01.setSelected(false);
            } else if (jCheckBoxMenuItem_Modo02.isSelected()) {
                jCheckBoxMenuItem_Modo01.setSelected(true);
                jCheckBoxMenuItem_Modo02.setSelected(false);
            } else {
                jCheckBoxMenuItem_Modo02.setSelected(true);
                jCheckBoxMenuItem_Modo01.setSelected(false);
            }
        }
        ModeloTabela modelo = new ModeloTabela(dados, colunas);

        jTableListaProduto_Nota.setModel(modelo);
        jTableListaProduto_Nota.getColumnModel().getColumn(0).setPreferredWidth(60);
        jTableListaProduto_Nota.getColumnModel().getColumn(0).setResizable(true);
        jTableListaProduto_Nota.getColumnModel().getColumn(1).setPreferredWidth(80);
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
        jTableListaProduto_Nota.getColumnModel().getColumn(9).setPreferredWidth(00);
        jTableListaProduto_Nota.getColumnModel().getColumn(9).setResizable(true);
        jTableListaProduto_Nota.getColumnModel().getColumn(10).setPreferredWidth(40);
        jTableListaProduto_Nota.getColumnModel().getColumn(10).setResizable(true);
        jTableListaProduto_Nota.getColumnModel().getColumn(11).setPreferredWidth(00);
        jTableListaProduto_Nota.getColumnModel().getColumn(11).setResizable(true);
        jTableListaProduto_Nota.getColumnModel().getColumn(12).setPreferredWidth(00);
        jTableListaProduto_Nota.getColumnModel().getColumn(12).setResizable(true);
        jTableListaProduto_Nota.getColumnModel().getColumn(13).setPreferredWidth(00);
        jTableListaProduto_Nota.getColumnModel().getColumn(13).setResizable(true);
        jTableListaProduto_Nota.getTableHeader().setReorderingAllowed(false);
        jTableListaProduto_Nota.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTableListaProduto_Nota.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        conexTabela.desconecta();
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
                    ExecutaSaldo();
                    LimpaCampoProduto();
                } else {
                    jTextField_Quantidade_Nota.requestFocus();
                    jTextField_Quantidade_Nota.setBackground(Color.red);
                }
            }
        }
        PreencherTabela();

    }

    public void ExecutaSaldo() {
        String MeuExecutaSaldo = jTextField4.getText();
        System.out.println("aquiExecutaSaldo()        Verificando            " + MeuExecutaSaldo);
        conexSaldo.conexao();
        try {
            java.sql.PreparedStatement pst = conexSaldo.con.prepareStatement("update produto as prod set \n"
                    + "saldo_prod=( select  sum(qtd_mov) as qtd_prod  from movprodutobase as base\n"
                    + " where id_prod_ent=sis_prod and stmovimento=1 \n" ///and stsaldo=1 
                    + " GROUP BY   prod.sis_prod order by sis_prod asc )\n"
                    + " from movprodutobase as base\n"
                    + "where prod.sis_prod=base.id_prod_ent and nota_mov='" + MeuExecutaSaldo + "' ");////and base.stsaldo=1
            pst.execute();
            System.out.println("aquiExecutaSaldo() ok " + MeuExecutaSaldo);
            JOptionPane.showMessageDialog(null, "aquiExecutaSaldo() ok  " + MeuExecutaSaldo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ExecutaSaldo -- \n" + ex);
        }
        conexSaldo.desconecta();
    }

    public void BotaoSalvarMovimento() {
        if (FlagAlterar == 1) {
            Mbeans.setStatus_mov(2);
            int refint = Integer.parseInt(Referencia_Movimento);
            Mbeans.setId_mov((refint));
            System.out.println("Referencia_Movimento    -               " + Referencia_Movimento);
            Mbeans.setIntmodotbl(0);
            Mdao.AlterarMovProdutoBase(Mbeans);
            FlagAlterar = 0;
            System.out.println("Alterar2    OK");
        } else {
            System.out.println("Referencia_Movimento=MenuPrincipal.jLabel_Movendo.getText();" + Referencia_Movimento);
            verificador01 = verificador01 + 1;
            System.out.println("verificador01  --  " + verificador01);
        }
        Mbeans.setId_prod_ent(Id_Prod_Ent);
        Mbeans.setData_mov(Principal.jLabel_Data.getText());///// refe
//        id_referencia = Integer.parseInt(jTextField_Carrega_Nota.getText());
        id_referencia = Integer.parseInt(jTextField4.getText());
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
//        String MinhaQtdCalc = (jLabel_Calculado.getText());
        String MinhaQtdCalc = (jTextField_Quantidade_Calculado.getText());
        MinhaQtdCalc = MinhaQtdCalc.replace(",", ".");
        Mbeans.setQtd_calc(Double.parseDouble(MinhaQtdCalc));
//        Mbeans.setQtd_calc_ex((jLabel_Calculado.getText()));
        Mbeans.setQtd_calc_ex((jTextField_Quantidade_Calculado.getText()));
        Mbeans.setValor_real((ValorReal));
        Mbeans.setValor_moeda(ValorMoeda);
        Mbeans.setDestino_mov((jLabel_Setor.getText()));
        jLabel_Complemento.setText(jLabel_Complemento.getText().toUpperCase());
        Mbeans.setComplemento_mov((jLabel_Complemento.getText()));
        Mbeans.setRegistro_mov(Principal.jLabel_Data.getText() + " " + Principal.jLabel_Hora.getText());
        Mbeans.setStatus_mov(1);
        Mbeans.setVolume("VOL");
        Mbeans.setUsuario_mov(Principal.jLabelNomeUsuario.getText());
        int Modo;
        if (jRadioButton1.isSelected()) {
            Modo = 1;
        } else if (jRadioButton2.isSelected()) {
            Modo = 2;
        } else {
            Modo = 1;
        }
        Mbeans.setModo_mov(Modo);// PRIMARIO
        Mbeans.setTotal_mov(resultado_Calculo_Formatado);// TOTAL
        Mbeans.setIntmodotbl(1);
        Mdao.SalvarMovProdutoBase(Mbeans);

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
        verificaSelecao();
        String descricao;
        String edicao;
        String FormatoReal = null;
        String IdAlterar = jLabel_IdMovimento.getText();
        System.out.println("-----------------" + jLabel_IdMovimento.getText());
        conex_Movimento.conexao();
        conex_Movimento.executaSql("SELECT * FROM movprodutobase  "
                + "inner join produto on id_prod_ent=sis_prod  "
                + "inner join nota on nota_mov = id_referencianota "
                + "inner join natureza on naturezaint=id_natureza "
                + "inner join unidade on idunid = id_referenciaunidade "
                + " where id_mov ='" + IdAlterar + "'"
                + "and stprod='" + stproduto + "' and stmovimento='" + stmovimento + "' and stunid=1  order by id_mov asc"); /// and status_prod='ATIVO' and status_mov='ATIVO'

        try {
            conex_Movimento.rs.first();
            edicao = conex_Movimento.rs.getString("edicao_prod");
            if (edicao.equals(null) | edicao.equals("") | edicao.equals(" ")) {
                edicao = "";
            } else {
                edicao = " N° " + edicao;
            }

            MinhaNatureza = conex_Movimento.rs.getString("tipo_natureza");
            System.out.println("------------------------- " + MinhaNatureza);
            descricao = " " + conex_Movimento.rs.getString("tipo_prod") + " " + conex_Movimento.rs.getString("nome_prod") + edicao;
            descricao = descricao.toUpperCase();
            jTextField_Quantidade_Nota.setText(conex_Movimento.rs.getString("qtd_prod_ex"));
            jLabel_Calculado.setText(conex_Movimento.rs.getString("qtd_calc_ex"));
            jTextField_Quantidade_Calculado.setText(conex_Movimento.rs.getString("qtd_calc_ex"));
            jLabel_IdProduto.setText(conex_Movimento.rs.getString("id_prod_ent"));
            jTextField2.setText(conex_Movimento.rs.getString("id_prod_ent"));
            jTextField3.setText(conex_Movimento.rs.getString("id_mov"));
            jTextField4.setText(conex_Movimento.rs.getString("nota_mov"));
            id_referencia = Integer.parseInt(conex_Movimento.rs.getString("nota_mov")); // aqui ver a os
            jTextField_Busca_Produto_Nota.setText(descricao);
            jTextField_Busca_Produto_Nota.setBackground(Color.yellow);
            jTextField_Quantidade_Nota.setBackground(Color.yellow);
            jLabel_Complemento.setText(conex_Movimento.rs.getString("complemento_mov"));
            jLabel_Setor.setText(conex_Movimento.rs.getString("destino_mov"));
            System.out.print("............................   " + descricao + "   " + jLabel_Complemento.getText() + "   " + jLabel_Setor.getText());

            jTextField2.requestFocus();
            jTextField_Quantidade_Nota.requestFocus();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
            jTextField_Busca_Produto_Nota.setBackground(Color.red);
        }
        conex_Movimento.desconecta();

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
            conex.executaSql("SELECT *  FROM produto inner join unidade on  idunid=id_referenciaunidade  where sis_prod = '" + Id_Prod_Ent + "'  and stprod=1   order by id_prod asc");
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

    public void RecebeDados(String a, String b, String c, String d) {
        jTextField_Carrega_Nota.setText(a);
        jTextField_Carrega_Natureza.setText(b);
        jComboBox_Natureza_Tipo.addItem(c);
        jComboBox_Natureza_Tipo.setSelectedItem(c);
        jComboBox_Natureza_Int.addItem(d);
        jComboBox_Natureza_Int.setSelectedItem(d);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton_Adicionar_Produto_Nota;
    private javax.swing.JButton jButton_Excluir_Movimento_Nota;
    private javax.swing.JButton jButton_Fechar_Nota;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem2;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem3;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem4;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem5;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem6;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem7;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem8;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem9;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem_Modo01;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem_Modo02;
    private javax.swing.JComboBox<String> jComboBox_Fornecedor_Int;
    private javax.swing.JComboBox<String> jComboBox_Natureza_Int;
    private javax.swing.JComboBox<String> jComboBox_Natureza_Tipo;
    public static javax.swing.JComboBox<String> jComboBox_Situacao;
    private javax.swing.JLabel jLabel20;
    public static javax.swing.JLabel jLabelMeuSaldoProduto;
    public static javax.swing.JLabel jLabel_Calculado;
    public static javax.swing.JLabel jLabel_Complemento;
    public static javax.swing.JLabel jLabel_IdMovimento;
    public static javax.swing.JLabel jLabel_IdProduto;
    public static javax.swing.JLabel jLabel_Setor;
    private javax.swing.JLabel jLabel_Status_Visualizar;
    public static javax.swing.JLabel jLabel_Texto_Produto1;
    public static javax.swing.JLabel jLabel_Texto_Produto2;
    public static javax.swing.JLabel jLabel_Texto_Produto3;
    public static javax.swing.JLabel jLabel_Texto_Produto4;
    public static javax.swing.JLabel jLabel_Texto_Produto5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTableListaProduto_Nota;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField_Busca;
    public static javax.swing.JTextField jTextField_Busca_Produto_Nota;
    private javax.swing.JTextField jTextField_Carrega_Natureza;
    private javax.swing.JTextField jTextField_Carrega_Nota;
    private javax.swing.JTextField jTextField_Fragmento_Variavel;
    public static javax.swing.JTextField jTextField_Quantidade_Calculado;
    public static javax.swing.JTextField jTextField_Quantidade_Nota;
    private javax.swing.JTextField jTextField_Varieavel_SaldoProduto;
    private javax.swing.JTextField jTextField_data_variavel;
    // End of variables declaration//GEN-END:variables
}
