/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acesso;

import Interface.Principal;
import static java.lang.Thread.sleep;

/**
 *
 * @author DNA
 */
public class JDialogCarregando extends javax.swing.JDialog {

    Principal tela;

    /**
     * Creates new form JDialogCarregando
     */
    public JDialogCarregando(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
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
        jProgressBar = new javax.swing.JProgressBar();
        jLabelCarregamento = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SysEstoque  carregando...");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 51, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setForeground(new java.awt.Color(204, 255, 204));

        jProgressBar.setStringPainted(true);
        jProgressBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jProgressBarMouseClicked(evt);
            }
        });

        jLabelCarregamento.setText("...");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabelCarregamento, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 54, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelCarregamento)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        run2();

    }//GEN-LAST:event_formWindowOpened

    private void jProgressBarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jProgressBarMouseClicked
        //Principal tela;
        tela = new Principal();
        tela.setVisible(true);
        fechaCarregamento();
        tela.toFront();
        this.dispose();
    }//GEN-LAST:event_jProgressBarMouseClicked

    public void run2() {

        new Thread() {
//            MenuPrincipal tela = new MenuPrincipal();
            public void run() {
                // Principal tela;
//                MenuPrincipal tela = new MenuPrincipal();
                for (int i = 0; i < 102; i++) {
                    try {
                        sleep(30);
                        jProgressBar.setValue(i);

                        if (jProgressBar.getValue() <= 25) {
                            jLabelCarregamento.setText("Carregando Sistema...");
                        } else if (jProgressBar.getValue() <= 50) {
                            jLabelCarregamento.setText("Carregando Banco de dados...");
                        } else if (jProgressBar.getValue() <= 75) {
                            jLabelCarregamento.setText("Carregando Tabelas...");
                        } else if (jProgressBar.getValue() <= 90) {
                            jLabelCarregamento.setText("Aguarde sistema abrindo...");
                        } else if (jProgressBar.getValue() == 95) {
                            jLabelCarregamento.setText("O sistema foi carregado...");
                            tela = new Principal();
                            tela.setVisible(true);
                            fechaCarregamento();
                            tela.toFront();
                        } else {
                            jLabelCarregamento.setText("O sistema foi carregado...");

//                            tela.jLabelUsuario.setText(labelUsuario.getText());
//                            tela.jLabelTipo.setText(labelTipo.getText());
//                            tela.setVisible(true); /// verificar depois e ver o que fazer
//                            fechaCarregamento();
                        }
                    } catch (InterruptedException ex) {
                    }
                }

//                tela.jLabelUsuario.setText(labelUsuario.getText());
//                tela.jLabelTipo.setText(labelTipo.getText());
//                tela.setVisible(true);
            }
        }.start();

    }

    public void fechaCarregamento() {
        this.dispose();
    }

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
            java.util.logging.Logger.getLogger(JDialogCarregando.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDialogCarregando.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDialogCarregando.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDialogCarregando.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDialogCarregando dialog = new JDialogCarregando(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelCarregamento;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar;
    // End of variables declaration//GEN-END:variables
}
