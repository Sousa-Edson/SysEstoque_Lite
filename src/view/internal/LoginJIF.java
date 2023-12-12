/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.internal;

import ConectaBanco.ConexaoBD;
import UTIL.UsuarioLogado;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.sql.SQLException;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import view.MenuPrincipal;

/**
 *
 * @author edson
 */
public class LoginJIF extends javax.swing.JInternalFrame {
 
    ConexaoBD con = new ConexaoBD(); 
    String nome, senha, tipo;
    int id;

    /**
     * Creates new form JIFTEste
     */
    public LoginJIF() {
        initComponents();
        remover_Ico(); 
        jTextField1.requestFocus(); 
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
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jLabel_Exibir = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setBorder(null);

        jPanel1.setBackground(new java.awt.Color(255, 51, 102));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Controle-de-acesso-biometria500.png"))); // NOI18N

        jTextField1.setText("edson");
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jPasswordField1.setText("98");
        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyPressed(evt);
            }
        });

        jButton1.setText("Acessar");
        jButton1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton1FocusLost(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });

        jLabel_Exibir.setForeground(new java.awt.Color(255, 255, 153));
        jLabel_Exibir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Exibir.setText(" ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Sys Estoque");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Versão 6.0");

        jLabel2.setFont(new java.awt.Font("Fira Sans", 1, 13)); // NOI18N
        jLabel2.setText("Senha");

        jLabel5.setFont(new java.awt.Font("Fira Sans", 1, 13)); // NOI18N
        jLabel5.setText("Login");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(523, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel_Exibir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addGap(0, 217, Short.MAX_VALUE)))
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(214, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(60, 60, 60))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 301, Short.MAX_VALUE)
                            .addComponent(jLabel_Exibir))
                        .addComponent(jLabel1))
                    .addContainerGap(19, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        jButton1.doClick();
    }//GEN-LAST:event_jButton1KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        BotaoAcesso();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton1FocusLost
        jButton1.setBackground(Color.lightGray);
    }//GEN-LAST:event_jButton1FocusLost

    private void jButton1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton1FocusGained
        jButton1.setBackground(Color.green);
    }//GEN-LAST:event_jButton1FocusGained

    private void jPasswordField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            jButton1.requestFocus();
        }
    }//GEN-LAST:event_jPasswordField1KeyPressed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            jPasswordField1.requestFocus();
        }
    }//GEN-LAST:event_jTextField1KeyPressed

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

    public void BotaoAcesso() {
        if (jTextField1.getText().equals("imput")) {
//            menu.jLabel_Usuario.setText("Sistema");
//            menu.jLabel_Tipo.setText("Manutenção");
//            preenche_Empresa_Id();
//            menu.Painel_principal.remove(this);
//            menu.Painel_principal.setEnabled(true);
//            menu.PapelParede.requestFocus();
            MenuPrincipal.jLabelTipoUsuario.setText("Manutenção");
            MenuPrincipal.jLabelNomeUsuario.setText("Sistema");
//            preenche_Empresa_Id();

//            this.dispose();
            MenuPrincipal.jMenuItem_EscondeMenu.doClick();
            jTextField1.setText(null);
            jPasswordField1.setText(null);
            this.setVisible(false);
            this.dispose();
        } else {
//            preenche_Empresa_Id();
            verificaBanco();

        }

    }

    public void verificaBanco() {

        con.conexao();
        try {
//            con.executaSql("select* from usuario where desc_usuario ilike '%" + jTextField1.getText() + "%' ");
            con.executaSql("select* from usuario where desc_usuario = '" + jTextField1.getText() + "' ");
            con.rs.first();
            if (jTextField1.getText().isEmpty()) {
                jTextField1.requestFocus();
            } else {
                nome = (con.rs.getString("desc_usuario"));
                senha = (con.rs.getString("senha_usuario"));
                tipo = (con.rs.getString("sigla_usuario"));
                id = (con.rs.getInt("id_usuario"));
//            JOptionPane.showMessageDialog(rootPane, nome); String nome , senha,tipo
                jTextField1.setText(nome);
//                abrePrincipal();
                jLabel_Exibir.setText(" ");
                String SenhaDigitada = jPasswordField1.getText();
                if (senha.equals(SenhaDigitada)) {
                    MenuPrincipal.jLabelTipoUsuario.setText(tipo);
                    MenuPrincipal.jLabelNomeUsuario.setText(nome);

                    UsuarioLogado.setId(id);
                    UsuarioLogado.setNome(nome);
                    UsuarioLogado.setTipo(tipo);
//                    preenche_Empresa_Id();
//                    menu.jLabel_Papel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Acesso/online-1905876_1280.jpg")));
//                    menu.jLabel_Fundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Acesso/online-1905876_1280.jpg")));
//                    menu.jLabel_Papel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interface/camaro-vermelho-wallpaper.jpg")));
//                    menu.jLabel_Fundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interface/camaro-vermelho-wallpaper.jpg")));
                    this.setVisible(false);
                    jLabel_Exibir.setText(" ");
                    jTextField1.setText(null);
                    jPasswordField1.setText(null);
                    MenuPrincipal.jMenuItem_EscondeMenu.doClick();
                    this.dispose();
                } else {
                    jLabel_Exibir.setText(" Senha incorreta");
                    jPasswordField1.requestFocus();
                }
            }

        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(rootPane, "Usuario não encontrado.");
            jLabel_Exibir.setText("Usuario não encontrado.");
//            JOptionPane.showMessageDialog(rootPane, "Erro usuario");
            jTextField1.requestFocus();
            nome = "";
            senha = "";
        }
        con.desconecta();

    }

//    public void preenche_Empresa() {
//
//        conex_Empresa.conexao();
//        conex_Empresa.executaSql("SELECT ecft_id, sis_ecft, ecft_tipo, ecft_nome,        ecft_descricao  FROM ecft where ecft_tipo= 'EMPRESA' and stecft = 1 order by sis_ecft desc "); /// where fc_tipo like '" + busca_fornecedor + "' 
//        try {
//            conex_Empresa.rs.first();
//            jComboBox1.removeAllItems();
////            jComboBox_transportadora.addItem("NÃO DEFINIDO");
//            do {
//                jComboBox1.addItem(conex_Empresa.rs.getString("ecft_nome"));
//
//            } while (conex_Empresa.rs.next());
//
//        } catch (SQLException ex) {
//            jComboBox1.addItem("NÃO DEFINIDO");
//            jComboBox1.setSelectedItem("NÃO DEFINIDO");
//        }
//        conex_Empresa.desconecta();
//    }
//
//    public void preenche_Empresa_Id() {
//        Empresa_Id = (String) jComboBox1.getSelectedItem();
////        JOptionPane.showMessageDialog(rootPane, Empresa_Id);
//        conex_Empresa.conexao();
//        conex_Empresa.executaSql("SELECT ecft_id, sis_ecft, ecft_tipo, ecft_nome,ecft_descricao  FROM ecft where ecft_nome='" + Empresa_Id + "' and ecft_tipo= 'EMPRESA' and stecft = 1 order by sis_ecft asc"); /// where fc_tipo like '" + busca_fornecedor + "' 
//        try {
//            conex_Empresa.rs.first();
//            Empresa_Id_Menu = (conex_Empresa.rs.getString("sis_ecft"));
//
//        } catch (SQLException ex) {
//            Empresa_Id_Menu = "0";
//        }
////        MenuPrincipal.jLabel_NomeEmpresa1.setText(Empresa_Nome_Menu);
//        MenuPrincipal.jLabel_Empresa.setText(Empresa_Id_Menu);
//        conex_Empresa.desconecta();
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel_Exibir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
