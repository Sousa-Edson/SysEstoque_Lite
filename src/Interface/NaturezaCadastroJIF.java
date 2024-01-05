/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import ModeloBeans.Beans_Natureza;
import ModeloBeans.ModeloTabela;
import ConectaBanco.ConexaoBD;
import ModeloDao.Dao_Natureza;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 *
 * @author edson
 */
public class NaturezaCadastroJIF extends javax.swing.JInternalFrame {

    Beans_Natureza beans = new Beans_Natureza();
    Dao_Natureza dao = new Dao_Natureza();
    ConexaoBD conex = new ConexaoBD();

    int id_referencia;
    int id_unidade;
    int flag = 1;

    String MostraTabela = null;
    String ordem = "asc";

    /**
     * Creates new form UnidadeJIF
     */
    public NaturezaCadastroJIF() {
        initComponents();
        remover_Ico();
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

    public void CarregaUltimo() {
        conex.conexao();
        conex.executaSql2("SELECT  id_referencianatureza  FROM natureza where id_referencianatureza is not null and id_referencianatureza !=0 order by id_referencianatureza asc  ");
        try {
            conex.rs.last();
            id_referencia = conex.rs.getInt("id_referencianatureza");
//            JOptionPane.showMessageDialog(rootPane, id_referencia);
            id_referencia = id_referencia + 1;
//            JOptionPane.showMessageDialog(rootPane, id_referencia);
        } catch (SQLException ex) {
//            Logger.getLogger(UnidadeJIF.class.getName()).log(Level.SEVERE, null, ex);
            id_referencia = 1;
        }
//        JOptionPane.showMessageDialog(rootPane, id_referencia);
        conex.desconecta();
    }

    public void PreencheTabela() {
        String TipoUsuario = "";  //.jLabelTipoUsuario.getText();
        if (TipoUsuario == "Manutenção") {
            PreencheTabela2();
        } else {
            PreencheTabela3();
        }
    }

    public void PreencheTabela3() {
        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{"Codigo", "Tipo", "Descrição", "Registro", "Usuario", "Id"};
        conex.conexao();

        if (jMenuItem_Ordem.isSelected()) {
            conex.executaSql2("SELECT * FROM natureza  where stnat=1 order by id_referencianatureza desc ");
        } else {
            conex.executaSql2("SELECT * FROM natureza  where stnat=1 order by id_referencianatureza asc ");
        }
        try {
            conex.rs.first();
            do {
                String status_natureza_ex = null;

                dados.add(new Object[]{conex.rs.getInt("id_referencianatureza"),
                    conex.rs.getString("tipo_natureza"), conex.rs.getString("desc_natureza"), conex.rs.getString("registro_natureza"),
                    conex.rs.getString("usuario_natureza"), conex.rs.getInt("id_natureza")});
            } while (conex.rs.next());
        } catch (SQLException ex) {
            Logger.getLogger(NaturezaCadastroJIF.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erro   " + ex);
        }
        ModeloTabela modelo = new ModeloTabela(dados, colunas);
        jTable_Lista.setModel(modelo);
        jTable_Lista.getColumnModel().getColumn(0).setPreferredWidth(60);
        jTable_Lista.getColumnModel().getColumn(0).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTable_Lista.getColumnModel().getColumn(1).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(2).setPreferredWidth(200);
        jTable_Lista.getColumnModel().getColumn(2).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(3).setPreferredWidth(80);
        jTable_Lista.getColumnModel().getColumn(3).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(4).setPreferredWidth(80);
        jTable_Lista.getColumnModel().getColumn(4).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(5).setPreferredWidth(00);
        jTable_Lista.getColumnModel().getColumn(5).setResizable(true);
//        jTable_Lista.getColumnModel().getColumn(6).setPreferredWidth(00);
//        jTable_Lista.getColumnModel().getColumn(6).setResizable(true);
        jTable_Lista.getTableHeader().setReorderingAllowed(false);
        jTable_Lista.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable_Lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        conex.desconecta();

    }

    public void PreencheTabela2() {
        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{"Codigo", "Status", "Tipo", "Descrição", "Registro", "Usuario", "Id"};
        conex.conexao();

        if (jMenuItem_Ordem.isSelected()) {
            conex.executaSql2("SELECT * FROM natureza order by id_referencianatureza desc ");
        } else {
            conex.executaSql2("SELECT * FROM natureza  order by id_referencianatureza asc ");
        }
        try {
            conex.rs.first();
            do {
                String status_natureza_ex = null;
                int status_natureza;
                status_natureza = conex.rs.getInt("stnat");
                if (status_natureza == 1) {
                    status_natureza_ex = "ATIVO";
                } else if (status_natureza == 2) {
                    status_natureza_ex = "ALTERADO";
                } else if (status_natureza == 3) {
                    status_natureza_ex = "EXCLUIDO";
                } else {
                    status_natureza_ex = "INDEFINIDO";
                }
                dados.add(new Object[]{conex.rs.getInt("id_referencianatureza"), status_natureza_ex,
                    conex.rs.getString("tipo_natureza"), conex.rs.getString("desc_natureza"), conex.rs.getString("registro_natureza"),
                    conex.rs.getString("usuario_natureza"), conex.rs.getInt("id_natureza")});
            } while (conex.rs.next());
        } catch (SQLException ex) {
            Logger.getLogger(NaturezaCadastroJIF.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erro   " + ex);
        }
        ModeloTabela modelo = new ModeloTabela(dados, colunas);
        jTable_Lista.setModel(modelo);
        jTable_Lista.getColumnModel().getColumn(0).setPreferredWidth(60);
        jTable_Lista.getColumnModel().getColumn(0).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(1).setPreferredWidth(80);
        jTable_Lista.getColumnModel().getColumn(1).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable_Lista.getColumnModel().getColumn(2).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(3).setPreferredWidth(200);
        jTable_Lista.getColumnModel().getColumn(3).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(4).setPreferredWidth(80);
        jTable_Lista.getColumnModel().getColumn(4).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(5).setPreferredWidth(80);
        jTable_Lista.getColumnModel().getColumn(5).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(6).setPreferredWidth(00);
        jTable_Lista.getColumnModel().getColumn(6).setResizable(true);
        jTable_Lista.getTableHeader().setReorderingAllowed(false);
        jTable_Lista.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable_Lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Lista = new javax.swing.JTable();
        jButton_Salvar = new javax.swing.JButton();
        jButton_Cancelar = new javax.swing.JButton();
        jButton_Excluir = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField_Descricao = new javax.swing.JTextField();
        JCTipo = new javax.swing.JComboBox<>();
        jButton_Novo = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem_Ordem = new javax.swing.JRadioButtonMenuItem();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Natureza da Operação");

        jPanel1.setBackground(new java.awt.Color(0, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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
        jLabel1.setText("Tipo Natureza");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Descrição Natureza");

        jTextField_Descricao.setEnabled(false);
        jTextField_Descricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_DescricaoKeyPressed(evt);
            }
        });

        JCTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ENTRADA", "SAIDA" }));
        JCTipo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        JCTipo.setEnabled(false);
        JCTipo.setFocusTraversalPolicyProvider(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                    .addComponent(jTextField_Descricao)
                    .addComponent(JCTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(1, 1, 1)
                .addComponent(JCTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_Descricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton_Novo.setText("Novo");
        jButton_Novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_NovoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton_Novo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_Salvar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_Cancelar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_Excluir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Novo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 33, Short.MAX_VALUE)))
                .addGap(6, 6, 6))
        );

        jMenu2.setText("Edit");

        jMenuItem_Ordem.setText("Ordem");
        jMenuItem_Ordem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_OrdemActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem_Ordem);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_SalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SalvarActionPerformed
        VerificarCamposVazios();

    }//GEN-LAST:event_jButton_SalvarActionPerformed
    public void VerificarCamposVazios() {
        VerificarCamposCheios();
        if (jTextField_Descricao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Campo(s) vazio(s)");
        } else {
            EventoSalvar();
        }
    }

    public void VerificarCamposCheios() {

        if (jTextField_Descricao.getText().length() >= 50) {
            jTextField_Descricao.setText(null);
            jTextField_Descricao.requestFocus();
        }

    }

    public void EventoSalvar() {
//        jTextField_Sigla.setSelectedItem(jTextField_Sigla.getSelectedItem());
        jTextField_Descricao.setText(jTextField_Descricao.getText().toUpperCase());
        jButton_Novo.setEnabled(true);
        jButton_Salvar.setEnabled(false);
        jButton_Excluir.setEnabled(false);
        JCTipo.setEnabled(false);
        jTextField_Descricao.setEnabled(false);
        beans.setTipo_natureza((String) JCTipo.getSelectedItem());
        beans.setDesc_natureza(jTextField_Descricao.getText());
        beans.setUsuario_natureza("");  //.jLabelNomeUsuario.getText());
        if (flag == 2) {
            beans.setId_natureza(id_unidade);
            beans.setStatus_natureza(2);
            dao.Alterar(beans);
        } else {
        }
        beans.setStatus_natureza(1);
        beans.setRegistro_natureza("");  //.jLabel_Data.getText() + " " + "");  //.jLabel_Hora.getText());

        beans.setId_referencia(id_referencia);
        dao.Salvar(beans);
        PreencheTabela();
        jTextField_Descricao.setText(null);
        JCTipo.setSelectedItem("ENTRADA");
    }

    private void jButton_NovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_NovoActionPerformed
        CarregaUltimo();
        flag = 1;
        jButton_Novo.setEnabled(false);
        jButton_Excluir.setEnabled(false);
        jButton_Salvar.setEnabled(true);
        JCTipo.setEnabled(true);
        jTextField_Descricao.setEnabled(true);
        PreencheTabela();
        jTextField_Descricao.setText(null);
        JCTipo.setSelectedItem("ENTRADA");
        JCTipo.requestFocus();

    }//GEN-LAST:event_jButton_NovoActionPerformed

    private void jButton_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CancelarActionPerformed
        PreencheTabela();
        flag = 1;
        jButton_Novo.setEnabled(true);
        jButton_Salvar.setEnabled(false);
        jTextField_Descricao.setText(null);
        JCTipo.setSelectedItem("ENTRADA");
        JCTipo.setEnabled(false);
        jTextField_Descricao.setEnabled(false);
        jButton_Excluir.setEnabled(false);

    }//GEN-LAST:event_jButton_CancelarActionPerformed

    private void jTable_ListaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_ListaMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {
        } else {
            flag = 2;
            JCTipo.setEnabled(true);
            jTextField_Descricao.setEnabled(true);
            JCTipo.requestFocus();
            jButton_Salvar.setEnabled(true);
            jButton_Excluir.setEnabled(true);
            jButton_Novo.setEnabled(false);
            String TipoUsuario = "";
//            Principal.jLabelTipoUsuario.getText();
            if (TipoUsuario == "Manutenção") {
                id_unidade = (int) jTable_Lista.getValueAt(jTable_Lista.getSelectedRow(), 6);
                id_referencia = (int) jTable_Lista.getValueAt(jTable_Lista.getSelectedRow(), 0);
                JCTipo.setSelectedItem((String) jTable_Lista.getValueAt(jTable_Lista.getSelectedRow(), 2));
                jTextField_Descricao.setText((String) jTable_Lista.getValueAt(jTable_Lista.getSelectedRow(), 3));
            } else {
                id_unidade = (int) jTable_Lista.getValueAt(jTable_Lista.getSelectedRow(), 5);
                id_referencia = (int) jTable_Lista.getValueAt(jTable_Lista.getSelectedRow(), 0);
                JCTipo.setSelectedItem((String) jTable_Lista.getValueAt(jTable_Lista.getSelectedRow(), 1));
                jTextField_Descricao.setText((String) jTable_Lista.getValueAt(jTable_Lista.getSelectedRow(), 2));
            }

        }
    }//GEN-LAST:event_jTable_ListaMouseClicked

    private void jButton_ExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ExcluirActionPerformed

        jButton_Novo.setEnabled(true);
        jButton_Salvar.setEnabled(false);
        jButton_Excluir.setEnabled(false);
        jTextField_Descricao.setText(null);
        JCTipo.setEnabled(false);
        jTextField_Descricao.setEnabled(false);

        JCTipo.setSelectedItem("ENTRADA");
        beans.setStatus_natureza(3);
//        beans.setUsuario_natureza(Principal.jLabelNomeUsuario.getText());
//        beans.setRegistro_natureza(Principal.jLabel_Data.getText() + " " + Principal.jLabel_Hora.getText());
        beans.setId_natureza(id_unidade);
        dao.Excluir(beans);
        PreencheTabela();
    }//GEN-LAST:event_jButton_ExcluirActionPerformed

    private void jTable_ListaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_ListaMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable_ListaMouseEntered

    private void jTextField_DescricaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_DescricaoKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            jButton_Salvar.requestFocus();
        }
    }//GEN-LAST:event_jTextField_DescricaoKeyPressed

    private void jButton_SalvarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton_SalvarKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            VerificarCamposVazios();
        }
    }//GEN-LAST:event_jButton_SalvarKeyPressed

    private void jMenuItem_OrdemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_OrdemActionPerformed
        PreencheTabela();
    }//GEN-LAST:event_jMenuItem_OrdemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> JCTipo;
    private javax.swing.JButton jButton_Cancelar;
    private javax.swing.JButton jButton_Excluir;
    private javax.swing.JButton jButton_Novo;
    private javax.swing.JButton jButton_Salvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JRadioButtonMenuItem jMenuItem_Ordem;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Lista;
    private javax.swing.JTextField jTextField_Descricao;
    // End of variables declaration//GEN-END:variables
}
