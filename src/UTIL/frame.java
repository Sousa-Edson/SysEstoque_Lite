/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTIL;

import ConectaBanco.ConexaoBD;
import ConectaBanco.ConexaoBD_2;
import ConectaBanco.Conexao_mysql;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author edson
 */
public class frame extends javax.swing.JFrame {

    ConexaoBD_2 conex = new ConexaoBD_2();
    ConexaoBD conex_post = new ConexaoBD();
    String descricao;
    String edicao;
    String valor;
    String id;

    /**
     * Creates new form frame
     */
    public frame() {
        initComponents();
    }

    public void salvaBanco() {
        System.out.println("UTIL.frame.salvaBanco()" + id);
//        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" "
                    + "INSERT INTO celke.tbl_produtos (cod, nome, valor) VALUES ('" + id + "', '" + descricao + "',' " + valor + "');"
                    + "");
            pst.execute();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro \n" + ex);
        }
//        conex.desconecta();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(jButton2))
                .addContainerGap(285, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addContainerGap(212, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
   conex.conexao();
        id = "1";
        descricao = "IMPRESSO CARTA FICHA NÂO CF/SE751707 ";
        valor = "R$ 12,00";
        salvaBanco();
           conex.desconecta();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        preencherTabela();
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frame().setVisible(true);
            }
        });
    }

    public void preencherTabela() {

        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{"Id", "Produto", "Saldo", "Unid", "Valor Unit.", "Observação", "Registro", "Id Linha"};
         conex.conexao();
        conex_post.conexao();
        conex_post.executaSql("select * from produto "
                + "inner join unidade on  idunid=id_referenciaunidade "
                + "where  stunid =1 and stprod =1 "
                + "order by sis_prod  asc  ");
        try {
            conex_post.rs.first();
            do {
                edicao = conex_post.rs.getString("edicao_prod");

                String datahora = conex_post.rs.getString("usu_prod") + " " + conex_post.rs.getString("data_reg") + " " + conex_post.rs.getString("hora_reg");
                if (edicao.equals(null) | edicao.equals("") | edicao.equals(" ")) {
                    edicao = "";
                } else {
                    edicao = "-" + edicao;
                }

                descricao = " " + conex_post.rs.getString("tipo_prod") + " " + conex_post.rs.getString("nome_prod");
                descricao = descricao.toUpperCase();

                valor = conex_post.rs.getString("valor_prod_ex");
                id = conex_post.rs.getString("sis_prod");
                System.out.println("id-" + id + " " + "nome-" + descricao + " " + "valor-" + valor);
                salvaBanco();
            } while (conex_post.rs.next());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "preenchertabela\n" + ex);

        }
         conex.desconecta();
        conex_post.desconecta();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    // End of variables declaration//GEN-END:variables
}
