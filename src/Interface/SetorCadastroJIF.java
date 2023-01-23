/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Interface.Principal;
import ModeloBeans.Beans_Setor;
import ModeloBeans.ModeloTabela;
import ConectaBanco.ConexaoBD;
import ModeloDao.Dao_Setor;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
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
public class SetorCadastroJIF extends javax.swing.JInternalFrame {

    Beans_Setor BEANS = new Beans_Setor();
    Dao_Setor DAO = new Dao_Setor();
    ConexaoBD conex = new ConexaoBD();

    int id_referencia;
    int id;
    int flag = 1;

    String MostraTabela = null;
    String ordem = "asc";

    /**
     * Creates new form UnidadeJIF
     */
    public SetorCadastroJIF() {
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
        conex.executaSql2("SELECT  id_referencia  FROM setor where id_referencia is not null and id_referencia !=0 order by id_referencia asc  ");
        try {
            conex.rs.last();
            id_referencia = conex.rs.getInt("id_referencia");
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
        String TipoUsuario = Principal.jLabelTipoUsuario.getText();
        if (TipoUsuario == "Manutenção") {
            PreencheTabela3();
        } else {
            PreencheTabela2();
        }
    }

    public void PreencheTabela2() {
        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{"Codigo", "Sigla", "Descrição", "Registro", "Usuario", "Id"};

        if (jMenuItem_Ordem.isSelected()) {
            ordem = "";
        } else {
            ordem = "asc";
        }
        conex.conexao();
//status_setor
        if (ordem.equals("asc")) {
            conex.executaSql2("SELECT *  FROM setor where stsetor=1    order by id_referencia asc ");
        } else {
            conex.executaSql2("SELECT *  FROM setor where stsetor=1   order by id_referencia desc ");
        }
        try {
            conex.rs.first();
            do {

                dados.add(new Object[]{conex.rs.getInt("id_referencia"),
                    conex.rs.getString("sigla_setor"), conex.rs.getString("desc_setor"), conex.rs.getString("registro_setor"),
                    conex.rs.getString("usuario_setor"), conex.rs.getInt("id_setor")});

            } while (conex.rs.next());
        } catch (SQLException ex) {
            Logger.getLogger(SetorCadastroJIF.class.getName()).log(Level.SEVERE, null, ex);
        }
        ModeloTabela modelo = new ModeloTabela(dados, colunas);

        jTable_Lista.setModel(modelo);
        jTable_Lista.getColumnModel().getColumn(0).setPreferredWidth(60);
        jTable_Lista.getColumnModel().getColumn(0).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(1).setPreferredWidth(140);
        jTable_Lista.getColumnModel().getColumn(1).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(2).setPreferredWidth(140);
        jTable_Lista.getColumnModel().getColumn(2).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(3).setPreferredWidth(140);
        jTable_Lista.getColumnModel().getColumn(3).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(4).setPreferredWidth(80);
        jTable_Lista.getColumnModel().getColumn(4).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(5).setPreferredWidth(00);
        jTable_Lista.getColumnModel().getColumn(5).setResizable(true);
//        jTable_Lista.getColumnModel().getColumn(6).setPreferredWidth(00);
//        jTable_Lista.getColumnModel().getColumn(6).setResizable(true);
//        jTable_Lista.getColumnModel().getColumn(7).setPreferredWidth(80);
//        jTable_Lista.getColumnModel().getColumn(7).setResizable(true);

        jTable_Lista.getTableHeader().setReorderingAllowed(false);
        jTable_Lista.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable_Lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        conex.desconecta();

    }

    public void PreencheTabela3() {
        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{"Codigo", "Status", "Sigla", "Descrição", "Registro", "Usuario", "Id"};

        if (jMenuItem_Ordem.isSelected()) {
            ordem = "";
        } else {
            ordem = "asc";
        }
        conex.conexao();
//status_setor
        if (ordem.equals("asc")) {
            conex.executaSql2("SELECT *  FROM setor   order by id_referencia asc ");
        } else {
            conex.executaSql2("SELECT *  FROM setor   order by id_referencia desc ");
        }
        try {
            conex.rs.first();
            do {
                String status_setor;
                int status;
                status = conex.rs.getInt("stsetor");
                if (status == 1) {
                    status_setor = "ATIVO";
                } else if (status == 2) {
                    status_setor = "ALTERADO";
                } else if (status == 3) {
                    status_setor = "EXCLUIDO";
                } else {
                    status_setor = "INDEFINIDO";
                }
                dados.add(new Object[]{conex.rs.getInt("id_referencia"), status_setor,
                    conex.rs.getString("sigla_setor"), conex.rs.getString("desc_setor"), conex.rs.getString("registro_setor"),
                    conex.rs.getString("usuario_setor"), conex.rs.getInt("id_setor")});

            } while (conex.rs.next());
        } catch (SQLException ex) {
            Logger.getLogger(SetorCadastroJIF.class.getName()).log(Level.SEVERE, null, ex);
        }
        ModeloTabela modelo = new ModeloTabela(dados, colunas);

        jTable_Lista.setModel(modelo);
        jTable_Lista.getColumnModel().getColumn(0).setPreferredWidth(60);
        jTable_Lista.getColumnModel().getColumn(0).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTable_Lista.getColumnModel().getColumn(1).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(2).setPreferredWidth(140);
        jTable_Lista.getColumnModel().getColumn(2).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(3).setPreferredWidth(140);
        jTable_Lista.getColumnModel().getColumn(3).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(4).setPreferredWidth(140);
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
        jTextField_Sigla = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField_Descricao = new javax.swing.JTextField();
        jButton_Novo = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem_Ordem = new javax.swing.JRadioButtonMenuItem();

        setBackground(new java.awt.Color(0, 255, 204));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Setor");

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_Sigla)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField_Descricao))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_Sigla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                    .addComponent(jButton_Excluir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_Cancelar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                    .addComponent(jButton_Salvar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_Novo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
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
                .addGap(1, 1, 1))
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
        if (jTextField_Descricao.getText().isEmpty() | jTextField_Sigla.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Campo(s) vazio(s)");
        } else {
            EventoSalvar();
            
        }
    }

    public void VerificarCamposCheios() {

        if (jTextField_Sigla.getText().length() >= 20) {
            jTextField_Sigla.setText(null);
            jTextField_Sigla.requestFocus();
        }
        if (jTextField_Descricao.getText().length() >= 20) {
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
        BEANS.setSigla_setor(jTextField_Sigla.getText());
        BEANS.setDesc_setor(jTextField_Descricao.getText());

        if (flag == 2) {
            BEANS.setId_setor(id);
            BEANS.setStatus_setor(2);
            DAO.Alterar(BEANS);
        } else {
        }
        BEANS.setStatus_setor(1);
        BEANS.setRegistro_setor(Principal.jLabel_Data.getText() + " " + Principal.jLabel_Hora.getText());
        BEANS.setUsuario_setor(Principal.jLabelNomeUsuario.getText());

        BEANS.setId_referencia(id_referencia);
        DAO.Salvar(BEANS);
        PreencheTabela();
        jTextField_Descricao.setText(null);
        jTextField_Sigla.setText(null);
        Principal.jLabelCodigoTela.setText("AtualizaTudo");
      Principal.jButton1.doClick();
    }

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

    }//GEN-LAST:event_jButton_NovoActionPerformed

    private void jButton_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CancelarActionPerformed
        PreencheTabela();
        flag = 1;
        jButton_Novo.setEnabled(true);
        jButton_Salvar.setEnabled(false);
        jTextField_Descricao.setText(null);
        jTextField_Sigla.setText(null);
        jTextField_Sigla.setEnabled(false);
        jTextField_Descricao.setEnabled(false);
        jButton_Excluir.setEnabled(false);

    }//GEN-LAST:event_jButton_CancelarActionPerformed

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
            String TipoUsuario = Principal.jLabelTipoUsuario.getText();
            if (TipoUsuario == "Manutenção") {
                id = (int) jTable_Lista.getValueAt(jTable_Lista.getSelectedRow(), 6);
                id_referencia = (int) jTable_Lista.getValueAt(jTable_Lista.getSelectedRow(), 0);
                jTextField_Sigla.setText((String) jTable_Lista.getValueAt(jTable_Lista.getSelectedRow(), 2));
                jTextField_Descricao.setText((String) jTable_Lista.getValueAt(jTable_Lista.getSelectedRow(), 3));
            } else {
                id = (int) jTable_Lista.getValueAt(jTable_Lista.getSelectedRow(), 5);
                id_referencia = (int) jTable_Lista.getValueAt(jTable_Lista.getSelectedRow(), 0);
                jTextField_Sigla.setText((String) jTable_Lista.getValueAt(jTable_Lista.getSelectedRow(), 1));
                jTextField_Descricao.setText((String) jTable_Lista.getValueAt(jTable_Lista.getSelectedRow(), 2));
            }
        }

    }//GEN-LAST:event_jTable_ListaMouseClicked

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

            BEANS.setRegistro_setor(Principal.jLabel_Data.getText() + " " + Principal.jLabel_Hora.getText());
            BEANS.setUsuario_setor(Principal.jLabelNomeUsuario.getText());

            BEANS.setId_setor(id);
            BEANS.setStatus_setor(3);
            DAO.Excluir(BEANS);
        }
        PreencheTabela();
    }//GEN-LAST:event_jButton_ExcluirActionPerformed

    private void jTable_ListaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_ListaMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable_ListaMouseEntered

    private void jTextField_SiglaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_SiglaKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            jTextField_Descricao.requestFocus();
        }
        VerificarCamposCheios();
    }//GEN-LAST:event_jTextField_SiglaKeyPressed

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
    private javax.swing.JTextField jTextField_Sigla;
    // End of variables declaration//GEN-END:variables
}
