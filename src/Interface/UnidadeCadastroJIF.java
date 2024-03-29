/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import ConectaBanco.ConexaoBD;
import ModeloBeans.Beans_Unidade;
import ModeloBeans.ModeloTabela;
import ModeloDao.Dao_Unidade;
import java.awt.Color;
import static java.awt.Color.red;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author edson
 */
public class UnidadeCadastroJIF extends javax.swing.JInternalFrame {

    Beans_Unidade BUnis = new Beans_Unidade();
    Dao_Unidade DUnid = new Dao_Unidade();
    ConexaoBD conex = new ConexaoBD();
    String UltimaId;
    int NumeroUltimaId;

    int id_referencia;
    int id_unidade;
    int flag = 1;

    String MostraTabela = null;
    String ordem = "asc";
    int fragmento = 0;

    /**
     * Creates new form JIFTEste
     */
    public UnidadeCadastroJIF() {
        initComponents();
        remover_Ico();
//        PreencheUnidade();
        // setPosicao();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton_Novo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Lista = new javax.swing.JTable();
        jButton_Salvar = new javax.swing.JButton();
        jButton_Cancelar = new javax.swing.JButton();
        jButton_Excluir = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField_Sigla = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField_Descricao = new javax.swing.JTextField();
        jRadioButton_Sim = new javax.swing.JRadioButton();
        jRadioButton_Nao = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jRadioButtonOrderm = new javax.swing.JRadioButtonMenuItem();

        jLabel4.setText("jLabel4");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastro de unidade");
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

        jPanel4.setBackground(new java.awt.Color(0, 255, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton_Novo.setText("Novo");
        jButton_Novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_NovoActionPerformed(evt);
            }
        });

        jTable_Lista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable_Lista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_ListaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable_ListaMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Lista);

        jButton_Salvar.setText("Salvar");
        jButton_Salvar.setEnabled(false);
        jButton_Salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SalvarActionPerformed(evt);
            }
        });
        jButton_Salvar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton_SalvarKeyPressed(evt);
            }
        });

        jButton_Cancelar.setText("Cancelar");
        jButton_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CancelarActionPerformed(evt);
            }
        });

        jButton_Excluir.setText("Excluir");
        jButton_Excluir.setEnabled(false);
        jButton_Excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ExcluirActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Lista");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBackground(new java.awt.Color(0, 255, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sigla");

        jTextField_Sigla.setEnabled(false);
        jTextField_Sigla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_SiglaKeyPressed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Descrição");

        jTextField_Descricao.setEnabled(false);
        jTextField_Descricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_DescricaoKeyPressed(evt);
            }
        });

        jRadioButton_Sim.setText("sim");
        jRadioButton_Sim.setEnabled(false);
        jRadioButton_Sim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_SimActionPerformed(evt);
            }
        });

        jRadioButton_Nao.setSelected(true);
        jRadioButton_Nao.setText("não");
        jRadioButton_Nao.setEnabled(false);
        jRadioButton_Nao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_NaoActionPerformed(evt);
            }
        });

        jLabel5.setText("Fragmentado:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField_Descricao, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                            .addComponent(jTextField_Sigla))
                        .addGap(10, 10, 10))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton_Sim)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton_Nao)
                        .addGap(0, 20, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_Sigla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_Descricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton_Sim)
                    .addComponent(jRadioButton_Nao)
                    .addComponent(jLabel5))
                .addGap(13, 13, 13))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton_Cancelar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_Salvar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_Novo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_Excluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Novo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(6, 6, 6))
        );

        jMenu2.setText("Edit");

        jRadioButtonOrderm.setSelected(true);
        jRadioButtonOrderm.setText("Ordem");
        jMenu2.add(jRadioButtonOrderm);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        this.setVisible(false);
    }//GEN-LAST:event_formInternalFrameClosing

    private void jButton_NovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_NovoActionPerformed
        CarregaUltimo();
        flag = 1;
        jButton_Novo.setEnabled(false);
        jButton_Excluir.setEnabled(false);
        jButton_Salvar.setEnabled(true);
        jTextField_Sigla.setEnabled(true);
        jTextField_Descricao.setEnabled(true);
        PreencheTabela();
        jTextField_Descricao.setText(null);
        jTextField_Sigla.setText(null);
        jTextField_Sigla.requestFocus();
        jRadioButton_Nao.setEnabled(true);
        jRadioButton_Sim.setEnabled(true);
    }//GEN-LAST:event_jButton_NovoActionPerformed

    private void jTable_ListaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_ListaMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {
        } else {
            flag = 2;
            jTextField_Sigla.setEnabled(true);
            jTextField_Descricao.setEnabled(true);
            jTextField_Sigla.requestFocus();
            jButton_Salvar.setEnabled(true);
            jButton_Excluir.setEnabled(true);
            jButton_Novo.setEnabled(false);
            jRadioButton_Nao.setEnabled(true);
            jRadioButton_Sim.setEnabled(true);
            String TipoUsuario = Principal.jLabelTipoUsuario.getText();
            if (TipoUsuario == "Manutenção") {
                id_unidade = (int) jTable_Lista.getValueAt(jTable_Lista.getSelectedRow(), 7);
                id_referencia = (int) jTable_Lista.getValueAt(jTable_Lista.getSelectedRow(), 0);
                jTextField_Sigla.setText((String) jTable_Lista.getValueAt(jTable_Lista.getSelectedRow(), 2));
                jTextField_Descricao.setText((String) jTable_Lista.getValueAt(jTable_Lista.getSelectedRow(), 3));
            } else {
                id_unidade = (int) jTable_Lista.getValueAt(jTable_Lista.getSelectedRow(), 6);
                id_referencia = (int) jTable_Lista.getValueAt(jTable_Lista.getSelectedRow(), 0);
                jTextField_Sigla.setText((String) jTable_Lista.getValueAt(jTable_Lista.getSelectedRow(), 1));
                jTextField_Descricao.setText((String) jTable_Lista.getValueAt(jTable_Lista.getSelectedRow(), 2));
            }
        }
    }//GEN-LAST:event_jTable_ListaMouseClicked

    private void jTable_ListaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_ListaMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable_ListaMouseEntered

    private void jButton_SalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SalvarActionPerformed
        VerificarCamposVazios();
    }//GEN-LAST:event_jButton_SalvarActionPerformed

    private void jButton_SalvarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton_SalvarKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
//            VerificarCamposVazios();
        }
    }//GEN-LAST:event_jButton_SalvarKeyPressed

    private void jButton_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CancelarActionPerformed
        PreencheTabela();
//        flag = 1;
        jButton_Novo.setEnabled(true);
        jButton_Salvar.setEnabled(false);
        jTextField_Descricao.setText(null);
        jTextField_Sigla.setText(null);
        jTextField_Sigla.setEnabled(false);
        jTextField_Descricao.setEnabled(false);
        jButton_Excluir.setEnabled(false);
        jRadioButton_Nao.setEnabled(false);
        jRadioButton_Sim.setEnabled(false);
    }//GEN-LAST:event_jButton_CancelarActionPerformed

    private void jButton_ExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ExcluirActionPerformed
        int resposta = 0;
        resposta = JOptionPane.showConfirmDialog(rootPane, "Deseja realmente excluir ? ");
        if (resposta == JOptionPane.YES_OPTION) {
            jButton_Novo.setEnabled(true);
            jButton_Salvar.setEnabled(false);
            jButton_Excluir.setEnabled(false);
            jTextField_Descricao.setText(null);
            jTextField_Sigla.setText(null);
            jTextField_Sigla.setEnabled(false);
            jTextField_Descricao.setEnabled(false);

            BUnis.setRegistro_unidade(Principal.jLabel_Data.getText() + " " + Principal.jLabel_Hora.getText());
            BUnis.setStatus_unidade(3);
            BUnis.setUsuario_unidade(Principal.jLabelNomeUsuario.getText());
            BUnis.setId_unidade(id_unidade);
            DUnid.Excluir_Unidade(BUnis);
        } else {
        }
        PreencheTabela();
    }//GEN-LAST:event_jButton_ExcluirActionPerformed

    private void jTextField_SiglaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_SiglaKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            jTextField_Descricao.requestFocus();
        }
//        VerificarCamposCheios();
    }//GEN-LAST:event_jTextField_SiglaKeyPressed

    private void jTextField_DescricaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_DescricaoKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            jButton_Salvar.requestFocus();
        }
    }//GEN-LAST:event_jTextField_DescricaoKeyPressed

    private void jRadioButton_NaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_NaoActionPerformed
        if (jRadioButton_Nao.isSelected()) {
            jRadioButton_Sim.setSelected(false);
        } else {
            jRadioButton_Sim.setSelected(true);
        }
    }//GEN-LAST:event_jRadioButton_NaoActionPerformed

    private void jRadioButton_SimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_SimActionPerformed
        if (jRadioButton_Sim.isSelected()) {
            jRadioButton_Nao.setSelected(false);
        } else {
            jRadioButton_Nao.setSelected(true);
        }
    }//GEN-LAST:event_jRadioButton_SimActionPerformed

    public void EventoLimpar() {
//        jTextFieldBusca.setText("");
//        jTextFieldBusca.requestFocus();
        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{};
        ModeloTabela modelo = new ModeloTabela(dados, colunas);
//        jTable_Produto.setModel(modelo);

    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    public void remover_Ico() {
        this.setFrameIcon(null);

        // hack to remove system menu in Windows
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        Container north = (Container) ui.getNorthPane();
        north.remove(0);
        north.validate();
        north.repaint();
    }

    public void BuscaUltimaId() {
        conex.conexao();
        conex.executaSql2("select * from produto order by sis_prod  desc ");
        try {
            conex.rs.first();
            NumeroUltimaId = (conex.rs.getInt("sis_prod") + 1);
            UltimaId = String.valueOf(NumeroUltimaId);
            System.out.println("Interface.ProdutoListaJIF.BuscaUltimaId()" + UltimaId);
//            jLabel_Sistema2.setText(UltimaId);
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeCadastroJIF.class.getName()).log(Level.SEVERE, null, ex);
            UltimaId = "1";
        }
        conex.desconecta();
    }

    public void PreencheTabela() {
        String TipoUsuario = Principal.jLabelTipoUsuario.getText();
        if (TipoUsuario == "Manutenção") {
            PreencheTabela3();
        } else {
            PreencheTabela2();
        }

    }

    public void PreencheTabela2() {
        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{"Codigo", "Sigla", "Descrição", "Fragmentado", "Registro", "Usuario", "Id"};
        String MostraTabela;
        String ordem;
        MostraTabela = "ATIVO";
        int VerFrag = 0;
        String FragNome;
        if (jRadioButtonOrderm.isSelected()) {
            ordem = "";
        } else {
            ordem = "asc";
        }
        conex.conexao();

        if (ordem.equals("asc")) {
            conex.executaSql2("SELECT id_unidade, id_referenciaunidade, sigla_unidade, desc_unidade, registro_unidade, \n"
                    + "       usuario_unidade,fragmento_unidade\n"
                    + "  FROM unidade  where stunid =1 order by id_referenciaunidade asc ");
        } else {
            conex.executaSql2("SELECT id_unidade, id_referenciaunidade, sigla_unidade, desc_unidade, registro_unidade, \n"
                    + "       usuario_unidade,fragmento_unidade\n"
                    + "  FROM unidade where stunid =1 order by id_referenciaunidade desc ");
        }
        try {
            conex.rs.first();

            do {
                VerFrag = conex.rs.getInt("fragmento_unidade");
                if (VerFrag == 0) {
                    FragNome = "NÃO";
                } else {
                    FragNome = "SIM";
                }
                dados.add(new Object[]{conex.rs.getInt("id_referenciaunidade"),
                    conex.rs.getString("sigla_unidade"), conex.rs.getString("desc_unidade"), FragNome,
                    conex.rs.getString("registro_unidade"), conex.rs.getString("usuario_unidade"), conex.rs.getInt("id_unidade")});

            } while (conex.rs.next());
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeCadastroJIF.class.getName()).log(Level.SEVERE, null, ex);
        }
        ModeloTabela modelo = new ModeloTabela(dados, colunas);

        jTable_Lista.setModel(modelo);
        jTable_Lista.getColumnModel().getColumn(0).setPreferredWidth(60);
        TableCellRenderer tcr = new check();
        jTable_Lista.getColumnModel().getColumn(0).setCellRenderer(tcr);
        jTable_Lista.getColumnModel().getColumn(0).setResizable(true);
        TableCellEditor tce = null;
        jTable_Lista.getColumnModel().getColumn(0).setCellEditor(tce);
        jTable_Lista.getColumnModel().getColumn(1).setPreferredWidth(80);
        jTable_Lista.getColumnModel().getColumn(1).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable_Lista.getColumnModel().getColumn(2).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(3).setPreferredWidth(100);
        jTable_Lista.getColumnModel().getColumn(3).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(4).setPreferredWidth(100);
        jTable_Lista.getColumnModel().getColumn(4).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(5).setPreferredWidth(80);
        jTable_Lista.getColumnModel().getColumn(5).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(6).setPreferredWidth(00);
        jTable_Lista.getColumnModel().getColumn(6).setResizable(true);

//        jTable_Lista.getColumnModel().getColumn(7).setPreferredWidth(80);
//        jTable_Lista.getColumnModel().getColumn(7).setResizable(true);
        jTable_Lista.getTableHeader().setReorderingAllowed(false);
        jTable_Lista.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable_Lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        conex.desconecta();

    }

    class check extends JCheckBox implements TableCellRenderer {

        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
           
            
             
             
            return this;
        }
    }

    public void PreencheTabela3() {
        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{"Codigo", "Status", "Sigla", "Fragmentado", "Descrição", "Registro", "Usuario", "Id"};
        String MostraTabela;
        String ordem;
        MostraTabela = "ATIVO";
        if (jRadioButtonOrderm.isSelected()) {
            ordem = "";
        } else {
            ordem = "asc";
        }
        conex.conexao();

        if (ordem.equals("asc")) {
            conex.executaSql2("SELECT stunid,id_unidade, id_referenciaunidade, sigla_unidade, desc_unidade, registro_unidade, \n"
                    + "       usuario_unidade,fragmento_unidade\n"
                    + "  FROM unidade   order by id_referenciaunidade asc ");
        } else {
            conex.executaSql2("SELECT stunid,id_unidade, id_referenciaunidade, sigla_unidade, desc_unidade, registro_unidade, \n"
                    + "       usuario_unidade,fragmento_unidade\n"
                    + "  FROM unidade order by id_referenciaunidade desc ");
        }
        try {
            conex.rs.first();
            do {
                int VerFrag = 0;
                String FragNome;
                String statusex;
                int status;
                status = conex.rs.getInt("stunid");
                if (status == 1) {
                    statusex = "ATIVO";
                } else if (status == 2) {
                    statusex = "ALTERADO";
                } else if (status == 3) {
                    statusex = "EXCLUIDO";
                } else {
                    statusex = "INDEFINIDO";
                }
                VerFrag = conex.rs.getInt("fragmento_unidade");
                if (VerFrag == 0) {
                    FragNome = "NÃO";
                } else {
                    FragNome = "SIM";
                }
                dados.add(new Object[]{conex.rs.getInt("id_referenciaunidade"), statusex,
                    conex.rs.getString("sigla_unidade"), conex.rs.getString("desc_unidade"), FragNome,
                    conex.rs.getString("registro_unidade"), conex.rs.getString("usuario_unidade"), conex.rs.getInt("id_unidade")});

            } while (conex.rs.next());
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeCadastroJIF.class.getName()).log(Level.SEVERE, null, ex);
        }
        ModeloTabela modelo = new ModeloTabela(dados, colunas);

        jTable_Lista.setModel(modelo);
        jTable_Lista.getColumnModel().getColumn(0).setPreferredWidth(60);
        jTable_Lista.getColumnModel().getColumn(0).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(1).setPreferredWidth(80);
        jTable_Lista.getColumnModel().getColumn(1).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable_Lista.getColumnModel().getColumn(2).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(3).setPreferredWidth(100);
        jTable_Lista.getColumnModel().getColumn(3).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(4).setPreferredWidth(100);
        jTable_Lista.getColumnModel().getColumn(4).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(5).setPreferredWidth(100);
        jTable_Lista.getColumnModel().getColumn(5).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(6).setPreferredWidth(80);
        jTable_Lista.getColumnModel().getColumn(6).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(7).setPreferredWidth(00);
        jTable_Lista.getColumnModel().getColumn(7).setResizable(true);
        jTable_Lista.getTableHeader().setReorderingAllowed(false);
        jTable_Lista.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable_Lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        conex.desconecta();

    }

    public void VerificarCamposVazios() {
        VerificarCamposCheios();
        if (jTextField_Descricao.getText().isEmpty() | jTextField_Sigla.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Campo(s) vazio(s)");
        } else {
            EventoSalvar();
        }
    }

    public void VerificarCamposCheios() {

        if (jTextField_Sigla.getText().length() >= 5) {
            jTextField_Sigla.setText(null);
            jTextField_Sigla.requestFocus();
        }
        if (jTextField_Descricao.getText().length() >= 11) {
            jTextField_Descricao.setText(null);
            jTextField_Descricao.requestFocus();
        }

    }

    public void EventoSalvar() {
        jTextField_Sigla.setText(jTextField_Sigla.getText().toUpperCase());
        jTextField_Descricao.setText(jTextField_Descricao.getText().toUpperCase());
        jButton_Novo.setEnabled(true);
        jButton_Salvar.setEnabled(false);
        jButton_Excluir.setEnabled(false);
        jTextField_Sigla.setEnabled(false);
        jTextField_Descricao.setEnabled(false);
        jRadioButton_Nao.setEnabled(false);
        jRadioButton_Sim.setEnabled(false);
        BUnis.setSigla_unidade(jTextField_Sigla.getText());
        BUnis.setDesc_unidade(jTextField_Descricao.getText());

        if (flag == 2) {
            BUnis.setId_unidade(id_unidade);
            BUnis.setStatus_unidade(2);
            DUnid.Alterar_Unidade(BUnis);
        } else {
            System.out.println("Interface.UnidadeCadastroJIF.EventoSalvar() sem id " + id_unidade);
        }

        if (jRadioButton_Nao.isSelected()) {
            fragmento = 0;
        } else if (jRadioButton_Sim.isSelected()) {
            fragmento = 1;
        } else {
            JOptionPane.showMessageDialog(rootPane, "Selecione sim ou não !");
        }
        BUnis.setFragmento_unidade(fragmento);
        BUnis.setStatus_unidade(1);
        BUnis.setRegistro_unidade(Principal.jLabel_Data.getText() + " " + Principal.jLabel_Hora.getText());
        BUnis.setUsuario_unidade(Principal.jLabelNomeUsuario.getText());
        System.out.println("Interface.UnidadeCadastroJIF.EventoSalvar() referencia " + id_referencia);
        BUnis.setId_referencia(id_referencia);
        DUnid.Salvar_Unidade(BUnis);
        PreencheTabela();
        jTextField_Descricao.setText(null);
        jTextField_Sigla.setText(null);
    }

    public void CarregaUltimo() {
        conex.conexao();
        conex.executaSql2("SELECT  id_referenciaunidade  FROM unidade where id_referenciaunidade is not null and id_referenciaunidade !=0 order by id_referenciaunidade asc  ");
        try {
            conex.rs.last();
            id_referencia = conex.rs.getInt("id_referenciaunidade");
//            JOptionPane.showMessageDialog(rootPane, id_referencia);
            id_referencia = id_referencia + 1;
//            JOptionPane.showMessageDialog(rootPane, id_referencia);
        } catch (SQLException ex) {
//            Logger.getLogger(UnidadeJIF.class.getName()).log(Level.SEVERE, null, ex);
            id_referencia = 1;
        }
//        JOptionPane.showMessageDialog(rootPane, id_referencia);
        conex.desconecta();
        System.out.println("Interface.UnidadeCadastroJIF.CarregaUltimo()  " + id_referencia);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Cancelar;
    private javax.swing.JButton jButton_Excluir;
    private javax.swing.JButton jButton_Novo;
    private javax.swing.JButton jButton_Salvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButtonMenuItem jRadioButtonOrderm;
    private javax.swing.JRadioButton jRadioButton_Nao;
    private javax.swing.JRadioButton jRadioButton_Sim;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Lista;
    private javax.swing.JTextField jTextField_Descricao;
    private javax.swing.JTextField jTextField_Sigla;
    // End of variables declaration//GEN-END:variables
}
