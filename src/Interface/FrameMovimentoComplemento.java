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
public class FrameMovimentoComplemento extends javax.swing.JFrame {

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

    /**
     * Creates new form FrameMovimentoComplemento
     */
    public FrameMovimentoComplemento() {
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
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(204, 255, 0));

        jLabel_Texto_Produto1.setText("Produto :");

        jLabelMeuSaldoProduto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton_Adicionar_Produto_Nota.setForeground(new java.awt.Color(255, 51, 51));
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
        jButton_Fechar_Nota.setToolTipText("Limpa Produto");
        jButton_Fechar_Nota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Fechar_NotaActionPerformed(evt);
            }
        });

        jButton_Excluir_Movimento_Nota.setForeground(new java.awt.Color(255, 51, 51));
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jComboBox_Natureza_Int, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox_Natureza_Tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox_Fornecedor_Int, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_data_variavel, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_Fragmento_Variavel, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_Varieavel_SaldoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                .addComponent(jComboBox_Situacao, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel_Texto_Produto1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_Busca_Produto_Nota, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
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
                                .addComponent(jButton_Excluir_Movimento_Nota))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel_Status_Visualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_Carrega_Nota, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_Carrega_Natureza, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 956, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton_Adicionar_Produto_Nota, jButton_Excluir_Movimento_Nota, jButton_Fechar_Nota});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelMeuSaldoProduto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Adicionar_Produto_Nota, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Fechar_Nota, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Excluir_Movimento_Nota, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_Quantidade_Nota, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_Busca_Produto_Nota, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox_Natureza_Int, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox_Natureza_Tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox_Fornecedor_Int, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField_data_variavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField_Fragmento_Variavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField_Varieavel_SaldoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_Complemento, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel_Setor, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel_IdProduto)
                        .addComponent(jLabel_IdMovimento)
                        .addComponent(jLabel_Calculado))
                    .addComponent(jComboBox_Situacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Status_Visualizar)
                    .addComponent(jTextField_Carrega_Nota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_Carrega_Natureza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel20))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel_Texto_Produto1)))
                .addGap(327, 327, 327))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel_Calculado, jLabel_IdMovimento});

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

    private void jComboBox_SituacaoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_SituacaoItemStateChanged
        jLabel_Status_Visualizar.setText("Status : " + jComboBox_Situacao.getSelectedItem());
    }//GEN-LAST:event_jComboBox_SituacaoItemStateChanged

    private void jComboBox_SituacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_SituacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_SituacaoActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
MovimentoCadastroJIF.jButton_Fechar_Nota.doClick();
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(FrameMovimentoComplemento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameMovimentoComplemento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameMovimentoComplemento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameMovimentoComplemento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameMovimentoComplemento().setVisible(true);
            }
        });
    }

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

    public void PreencherTabela() {
        PreencherTabelaMovimento();
//        PreencherTabelaComplemento();
//        PreencherTabelaProdutoPalete();
    }

    public void PreencherTabelaMovimento() {
        jLabelMeuSaldoProduto.setText("");
        id_referencia=Integer.parseInt(jTextField_Carrega_Nota.getText());
        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{"Item", "Id", "Descrição", "Unid", "Qtd", "Calc", "V.Unit", "V.Total", ""};// "Usuario", "Registro"
        conexTabela.conexao();
        conexTabela.executaSql2("SELECT * FROM movproduto inner join produto on id_prod_ent=sis_prod \n"
                + " inner join unidade on idunid= id_unidade where nota_mov='" + id_referencia + "' and modo_mov='2' and stprod = 1 and stmovimento=1 or stmovimento=4  order by id_mov asc ");
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
       id_referencia=Integer.parseInt( jTextField_Carrega_Nota.getText());
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
        Mbeans.setModo_mov(2);// PRIMARIO
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
        String descricao;
        String edicao;
        String IdAlterar = jLabel_IdMovimento.getText();
        conex_Movimento.conexao();
        conex_Movimento.executaSql("SELECT * FROM movproduto  inner join produto on id_prod_ent=sis_prod where id_mov ='" + IdAlterar + "'and stprod=1 and stmovimento=1  order by id_mov asc"); /// and status_prod='ATIVO' and status_mov='ATIVO'

        try {
            conex_Movimento.rs.first();
            edicao = conex_Movimento.rs.getString("edicao_prod");
            if (edicao.equals(null) | edicao.equals("") | edicao.equals(" ")) {
                edicao = "";
            } else {
                edicao = " N° " + edicao;
            }

            descricao = " " + conex_Movimento.rs.getString("tipo_prod") + " " + conex_Movimento.rs.getString("nome_prod") + edicao;
            descricao = descricao.toUpperCase();
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

    public void RecebeDados(String a, String b, String c, String d) {
        jTextField_Carrega_Nota.setText(a);
        jTextField_Carrega_Natureza.setText(b);
        jComboBox_Natureza_Tipo.addItem(c);
        jComboBox_Natureza_Tipo.setSelectedItem(c);
        jComboBox_Natureza_Int.addItem(d);
                jComboBox_Natureza_Int.setSelectedItem(d);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Adicionar_Produto_Nota;
    private javax.swing.JButton jButton_Excluir_Movimento_Nota;
    private javax.swing.JButton jButton_Fechar_Nota;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTableListaProduto_Nota;
    public static javax.swing.JTextField jTextField_Busca_Produto_Nota;
    private javax.swing.JTextField jTextField_Carrega_Natureza;
    private javax.swing.JTextField jTextField_Carrega_Nota;
    private javax.swing.JTextField jTextField_Fragmento_Variavel;
    public static javax.swing.JTextField jTextField_Quantidade_Nota;
    private javax.swing.JTextField jTextField_Varieavel_SaldoProduto;
    private javax.swing.JTextField jTextField_data_variavel;
    // End of variables declaration//GEN-END:variables
}
