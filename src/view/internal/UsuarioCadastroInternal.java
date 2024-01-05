/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.internal;

import Interface.*;
import ModeloBeans.ModeloTabela;
import ConectaBanco.ConexaoBD;
import ModeloDao.Dao_Usuario;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 *
 * @author edson
 */
public class UsuarioCadastroInternal extends javax.swing.JInternalFrame {

//    Beans_Usuario BEANS = new Beans_Usuario();
    Dao_Usuario DAO = new Dao_Usuario();
    ConexaoBD conex = new ConexaoBD();

    int id_referencia;
    int id;
    int flag = 1;

    String MostraTabela = null;
    String ordem = "asc";

    /**
     * Creates new form UnidadeJIF
     */
    public UsuarioCadastroInternal() {
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
        conex.executaSql2("SELECT  id_referencia  FROM usuario where id_referencia is not null and id_referencia !=0 order by id_referencia asc ");
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
//        String TipoUsuario = Principal.jLabelTipoUsuario.getText();
//        if (TipoUsuario == "Manutenção") {
//            PreencheTabela3();
//        } else {
//            PreencheTabela2();
//        }
    }

    public void PreencheTabela3() {
        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{"Codigo", "Status", "Tipo", "Descrição", "Registro", "Usuario", "Senha", "Id"};

        jLabel5.setText("Confirmação");
        this.setTitle("Usuario");

        if (jMenuItem_Ordem.isSelected()) {
            ordem = "";
        } else {
            ordem = "asc";
        }
        conex.conexao();

        if (ordem.equals("asc")) {
            conex.executaSql2("SELECT *  FROM usuario  order by id_referencia asc ");
        } else {
            conex.executaSql2("SELECT *  FROM usuario   order by id_referencia desc ");
        }
        try {
            conex.rs.first();
            do {
                String senha = " " + conex.rs.getString("senha_usuario");
                String UsuarioCadastro = null;
                int UsuarioCadastroNum = conex.rs.getInt("stusu");
                if (UsuarioCadastroNum == 1) {
                    UsuarioCadastro = "ATIVO";
                } else if (UsuarioCadastroNum == 2) {
                    UsuarioCadastro = "ALTERADO";
                } else if (UsuarioCadastroNum == 3) {
                    UsuarioCadastro = "EXCLUIDO";
                } else {
                    UsuarioCadastro = "INDEFINIDO";
                }
                dados.add(new Object[]{conex.rs.getInt("id_referencia"), UsuarioCadastro,
                    conex.rs.getString("sigla_usuario"), conex.rs.getString("desc_usuario"), conex.rs.getString("registro_usuario"),
                    conex.rs.getString("usuario_usuario"), senha, conex.rs.getInt("id_usuario")});

            } while (conex.rs.next());
        } catch (SQLException ex) {
//            Logger.getLogger(UsuarioJIF.class.getName()).log(Level.SEVERE, null, ex);
        }
        ModeloTabela modelo = new ModeloTabela(dados, colunas);

        jTable_Lista.setModel(modelo);
        jTable_Lista.getColumnModel().getColumn(0).setPreferredWidth(60);
        jTable_Lista.getColumnModel().getColumn(0).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(1).setPreferredWidth(80);
        jTable_Lista.getColumnModel().getColumn(1).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable_Lista.getColumnModel().getColumn(2).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(3).setPreferredWidth(150);
        jTable_Lista.getColumnModel().getColumn(3).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(4).setPreferredWidth(120);
        jTable_Lista.getColumnModel().getColumn(4).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(5).setPreferredWidth(80);
        jTable_Lista.getColumnModel().getColumn(5).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(6).setPreferredWidth(00);
        jTable_Lista.getColumnModel().getColumn(6).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(7).setPreferredWidth(00);
        jTable_Lista.getColumnModel().getColumn(7).setResizable(false);

        jTable_Lista.getTableHeader().setReorderingAllowed(false);
        jTable_Lista.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable_Lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        conex.desconecta();

    }

    public void PreencheTabela2() {
        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{"Codigo", "Tipo", "Descrição", "Registro", "Usuario", "Senha", "Id"};

        jLabel5.setText("Confirmação");
        this.setTitle("Usuario");

        if (jMenuItem_Ordem.isSelected()) {
            ordem = "";
        } else {
            ordem = "asc";
        }
        conex.conexao();

        if (ordem.equals("asc")) {
            conex.executaSql2("SELECT *  FROM usuario  where stusu=1 order by id_referencia asc ");
        } else {
            conex.executaSql2("SELECT *  FROM usuario  where stusu=1  order by id_referencia desc ");
        }
        try {
            conex.rs.first();
            do {
                String senha = " " + conex.rs.getString("senha_usuario");
                dados.add(new Object[]{conex.rs.getInt("id_referencia"),
                    conex.rs.getString("sigla_usuario"), conex.rs.getString("desc_usuario"), conex.rs.getString("registro_usuario"),
                    conex.rs.getString("usuario_usuario"), senha, conex.rs.getInt("id_usuario")});

            } while (conex.rs.next());
        } catch (SQLException ex) {
//            Logger.getLogger(UsuarioJIF.class.getName()).log(Level.SEVERE, null, ex);
        }
        ModeloTabela modelo = new ModeloTabela(dados, colunas);

        jTable_Lista.setModel(modelo);
        jTable_Lista.getColumnModel().getColumn(0).setPreferredWidth(60);
        jTable_Lista.getColumnModel().getColumn(0).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTable_Lista.getColumnModel().getColumn(1).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(2).setPreferredWidth(150);
        jTable_Lista.getColumnModel().getColumn(2).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(3).setPreferredWidth(120);
        jTable_Lista.getColumnModel().getColumn(3).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(4).setPreferredWidth(80);
        jTable_Lista.getColumnModel().getColumn(4).setResizable(true);
        jTable_Lista.getColumnModel().getColumn(5).setPreferredWidth(00);
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
        jTextFieldNome = new javax.swing.JTextField();
        jComboBoxTipo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPasswordSenha = new javax.swing.JPasswordField();
        jPasswordConfirmacao = new javax.swing.JPasswordField();
        jButton_Novo = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem_Ordem = new javax.swing.JRadioButtonMenuItem();

        setBackground(new java.awt.Color(0, 255, 204));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Usuario");

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
        jLabel1.setText("Tipo");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Nome");

        jTextFieldNome.setEnabled(false);
        jTextFieldNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldNomeKeyPressed(evt);
            }
        });

        jComboBoxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Usuario", "Manutenção", "Administrador", "Consulta" }));
        jComboBoxTipo.setEnabled(false);
        jComboBoxTipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBoxTipoKeyPressed(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Senha");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Confirmação");

        jPasswordSenha.setEnabled(false);
        jPasswordSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordSenhaKeyPressed(evt);
            }
        });

        jPasswordConfirmacao.setEnabled(false);
        jPasswordConfirmacao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordConfirmacaoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxTipo, 0, 187, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldNome)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addComponent(jPasswordSenha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addComponent(jPasswordConfirmacao))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPasswordSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPasswordConfirmacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(57, 57, 57))
        );

        jButton_Novo.setText("Novo");
        jButton_Novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_NovoActionPerformed(evt);
            }
        });
        jButton_Novo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton_NovoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton_Excluir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_Cancelar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                            .addComponent(jButton_Salvar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_Novo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton_Novo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 152, Short.MAX_VALUE)))
                .addContainerGap())
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
        String a = jPasswordSenha.getText();
        String b = jPasswordConfirmacao.getText();
        if (a.equals(b)) {
            VerificarCamposCheios();
            if (jTextFieldNome.getText().isEmpty() | jPasswordConfirmacao.getText().isEmpty() | jPasswordSenha.getText().isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Campo(s) vazio(s)");
            } else {
                EventoSalvar();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Verifique senha\nSenhas não são iguais.");
            jPasswordSenha.requestFocus();
            jPasswordConfirmacao.setText(null);
            jPasswordSenha.setText(null);
        }

    }

    public void VerificarCamposCheios() {

        if (jPasswordSenha.getText().length() >= 10) {
            jPasswordSenha.setText(null);
            jPasswordSenha.requestFocus();
        }
        if (jPasswordConfirmacao.getText().length() >= 10) {
            jPasswordConfirmacao.setText(null);
            jPasswordConfirmacao.requestFocus();
        }
        if (jTextFieldNome.getText().length() >= 11) {
            jTextFieldNome.setText(null);
            jTextFieldNome.requestFocus();
        }

    }

    public void EventoSalvar() {
//        jComboBoxTipo.setText(jComboBoxTipo.getText().toUpperCase());
//        jTextFieldNome.setText(jTextFieldNome.getText().toUpperCase());
        jButton_Novo.setEnabled(true);
        jButton_Salvar.setEnabled(false);
        jButton_Excluir.setEnabled(false);
        jComboBoxTipo.setEnabled(false);
        jTextFieldNome.setEnabled(false);
        jPasswordConfirmacao.setEnabled(false);
        jPasswordSenha.setEnabled(false);
//        BEANS.setSigla_usuario((String) jComboBoxTipo.getSelectedItem());
//        BEANS.setDesc_usuario(jTextFieldNome.getText());
//        BEANS.setSenha_usuario(jPasswordSenha.getText());

        if (flag == 2) {
//            BEANS.setId_usuario(id);
//            BEANS.setStatus_usuario(2);
//            DAO.Alterar(BEANS);
        } else {
        }
//        BEANS.setStatus_usuario(1);
//        BEANS.setRegistro_usuario(Principal.jLabel_Data.getText() + " " + Principal.jLabel_Hora.getText());
//        BEANS.setUsuario_usuario(Principal.jLabelNomeUsuario.getText());

//        BEANS.setId_referencia(id_referencia);
//        DAO.Salvar(BEANS);
        PreencheTabela();
        jTextFieldNome.setText(null);
        jPasswordSenha.setText(null);
        jPasswordConfirmacao.setText(null);
        jButton_Novo.requestFocus();

    }

    private void jButton_NovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_NovoActionPerformed
        BotaoNovo();
    }//GEN-LAST:event_jButton_NovoActionPerformed

    public void BotaoNovo() {

        CarregaUltimo();
        flag = 1;
        jButton_Novo.setEnabled(false);
        jButton_Excluir.setEnabled(false);
        jButton_Salvar.setEnabled(true);
        jComboBoxTipo.setEnabled(true);
        jPasswordConfirmacao.setEnabled(true);
        jPasswordSenha.setEnabled(true);
        jTextFieldNome.setEnabled(true);
        PreencheTabela();
        jTextFieldNome.setText(null);
        jPasswordSenha.setText(null);
        jPasswordConfirmacao.setText(null);
        jComboBoxTipo.setSelectedItem("Usuario");

        jComboBoxTipo.requestFocus();
    }

    private void jButton_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CancelarActionPerformed
        PreencheTabela();
        flag = 1;
        jButton_Novo.setEnabled(true);
        jButton_Salvar.setEnabled(false);
        jTextFieldNome.setText(null);
        jPasswordSenha.setText(null);
        jPasswordConfirmacao.setText(null);
        jPasswordSenha.setEnabled(false);
        jPasswordConfirmacao.setEnabled(false);
        jComboBoxTipo.setEnabled(false);
        jTextFieldNome.setEnabled(false);
        jPasswordConfirmacao.setEnabled(false);
        jPasswordSenha.setEnabled(false);
        jButton_Excluir.setEnabled(false);

    }//GEN-LAST:event_jButton_CancelarActionPerformed

    private void jTable_ListaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_ListaMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {
        } else {
            flag = 2;
            jComboBoxTipo.setEnabled(true);
            jTextFieldNome.setEnabled(true);
            jComboBoxTipo.requestFocus();
            jButton_Salvar.setEnabled(true);
            jButton_Excluir.setEnabled(true);
            jButton_Novo.setEnabled(false);
            jPasswordConfirmacao.setEnabled(true);
            jPasswordSenha.setEnabled(true);
            String TipoUsuario = "";
//                    Principal.jLabelTipoUsuario.getText();
            if (TipoUsuario == "Manutenção") {
                id = (int) jTable_Lista.getValueAt(jTable_Lista.getSelectedRow(), 7);
                id_referencia = (int) jTable_Lista.getValueAt(jTable_Lista.getSelectedRow(), 0);
                jComboBoxTipo.setSelectedItem((String) jTable_Lista.getValueAt(jTable_Lista.getSelectedRow(), 2));
                jTextFieldNome.setText((String) jTable_Lista.getValueAt(jTable_Lista.getSelectedRow(), 3));
                String senha = ((String) jTable_Lista.getValueAt(jTable_Lista.getSelectedRow(), 6));
                senha = senha.replace(" ", "");
                jPasswordSenha.setText(senha);
                jPasswordConfirmacao.setText(senha);
                this.setTitle("Usuario Id: " + id_referencia);
            } else {
                id = (int) jTable_Lista.getValueAt(jTable_Lista.getSelectedRow(), 6);
                id_referencia = (int) jTable_Lista.getValueAt(jTable_Lista.getSelectedRow(), 0);
                jComboBoxTipo.setSelectedItem((String) jTable_Lista.getValueAt(jTable_Lista.getSelectedRow(), 1));
                jTextFieldNome.setText((String) jTable_Lista.getValueAt(jTable_Lista.getSelectedRow(), 2));
                String senha = ((String) jTable_Lista.getValueAt(jTable_Lista.getSelectedRow(), 5));
                senha = senha.replace(" ", "");
                jPasswordSenha.setText(senha);
                jPasswordConfirmacao.setText(senha);
                this.setTitle("Usuario Id: " + id_referencia);
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
            jPasswordConfirmacao.setEnabled(false);
            jPasswordSenha.setEnabled(false);
            jPasswordConfirmacao.setText(null);
            jPasswordSenha.setText(null);
            jTextFieldNome.setText(null);
            jComboBoxTipo.setEnabled(false);
            jTextFieldNome.setEnabled(false);
            jPasswordConfirmacao.setEnabled(false);
            jPasswordSenha.setEnabled(false);

            jPasswordSenha.setText(null);
            jPasswordConfirmacao.setText(null);

//            BEANS.setUsuario_usuario("");
//                    Principal.jLabelNomeUsuario.getText());
//            BEANS.setId_usuario(id);
//            BEANS.setRegistro_usuario(Principal.jLabel_Data.getText() + " " + Principal.jLabel_Hora.getText());
//            BEANS.setStatus_usuario(3);
//            DAO.Excluir(BEANS);
        }
        PreencheTabela();
    }//GEN-LAST:event_jButton_ExcluirActionPerformed

    private void jTable_ListaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_ListaMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable_ListaMouseEntered

    private void jTextFieldNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNomeKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            jPasswordSenha.requestFocus();
        }
        if (evt.getKeyCode() == evt.VK_LEFT) {
            jComboBoxTipo.requestFocus();
        }
        if (evt.getKeyCode() == evt.VK_RIGHT) {
            jPasswordSenha.requestFocus();
        }
        if (evt.getKeyCode() == evt.VK_ESCAPE) {
//            this.setVisible(false);
//            Principal.Painel_principal.remove(this);
        }
    }//GEN-LAST:event_jTextFieldNomeKeyPressed

    private void jButton_SalvarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton_SalvarKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            VerificarCamposVazios();
        }
        if (evt.getKeyCode() == evt.VK_ESCAPE) {
//            this.setVisible(false);
//            Principal.Painel_principal.remove(this);
        }
    }//GEN-LAST:event_jButton_SalvarKeyPressed

    private void jMenuItem_OrdemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_OrdemActionPerformed
        PreencheTabela();
    }//GEN-LAST:event_jMenuItem_OrdemActionPerformed

    private void jComboBoxTipoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxTipoKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            jTextFieldNome.requestFocus();
        }
        if (evt.getKeyCode() == evt.VK_RIGHT) {
            jTextFieldNome.requestFocus();
        }
        if (evt.getKeyCode() == evt.VK_ESCAPE) {
//            this.setVisible(false);
//            Principal.Painel_principal.remove(this);
        }
    }//GEN-LAST:event_jComboBoxTipoKeyPressed

    private void jPasswordSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordSenhaKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            jPasswordConfirmacao.requestFocus();
        }
        if (evt.getKeyCode() == evt.VK_LEFT) {
            jTextFieldNome.requestFocus();
        }
        if (evt.getKeyCode() == evt.VK_RIGHT) {
            jPasswordConfirmacao.requestFocus();
        }
        if (evt.getKeyCode() == evt.VK_ESCAPE) {
//            this.setVisible(false);
//            MenuPrincipal.Painel_principal.remove(this);
        }

    }//GEN-LAST:event_jPasswordSenhaKeyPressed

    private void jPasswordConfirmacaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordConfirmacaoKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            jButton_Salvar.requestFocus();
        }
        if (evt.getKeyCode() == evt.VK_LEFT) {
            jPasswordSenha.requestFocus();
        }
        if (evt.getKeyCode() == evt.VK_RIGHT) {
            jButton_Salvar.requestFocus();
        }
        if (evt.getKeyCode() == evt.VK_ESCAPE) {
//            this.setVisible(false);
//            MenuPrincipal.Painel_principal.remove(this);
        }
    }//GEN-LAST:event_jPasswordConfirmacaoKeyPressed

    private void jButton_NovoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton_NovoKeyPressed
        if (evt.getKeyCode() == evt.VK_ESCAPE) {
//            this.setVisible(false);
//            MenuPrincipal.Painel_principal.remove(this);
        }
        if (evt.getKeyCode() == evt.VK_ENTER) {
            BotaoNovo();
        }
    }//GEN-LAST:event_jButton_NovoKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Cancelar;
    private javax.swing.JButton jButton_Excluir;
    private javax.swing.JButton jButton_Novo;
    private javax.swing.JButton jButton_Salvar;
    private javax.swing.JComboBox<String> jComboBoxTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JRadioButtonMenuItem jMenuItem_Ordem;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordConfirmacao;
    private javax.swing.JPasswordField jPasswordSenha;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Lista;
    private javax.swing.JTextField jTextFieldNome;
    // End of variables declaration//GEN-END:variables
}
