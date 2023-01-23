/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDao;

import ModeloBeans.Beans_Movimento;
import ModeloBeans.Beans_Nota;
import ModeloBeans.BeansPalete;
import ConectaBanco.ConexaoBD;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author edson
 */
public class Dao_Palete {

    ConexaoBD conex = new ConexaoBD();
    BeansPalete beans = new BeansPalete();

    public void SalvarTemp(BeansPalete beans) {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(""
                    + "INSERT INTO paletetemp( pctpad, altura ,lastro , pctavu, totpct, unavu, totparc,"
                    + " mov_palete,  nota_palete,qtd_palete, stpalete, usuario_palete, registro_palete,verifica_principal, id_palete )"
                    + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?, ?,?)");
            pst.setString(1, beans.getPctpad());
            pst.setString(2, beans.getAltura());
            pst.setString(3, beans.getLastro());
            pst.setString(4, beans.getPctavu());
            pst.setString(5, beans.getTotpct());
            pst.setString(6, beans.getUnavu());
            pst.setString(7, beans.getTotparc());
            pst.setString(8, beans.getMov_palete());
            pst.setString(9, beans.getNota_palete());
            pst.setString(10, beans.getQtd_palete());
            pst.setInt(11, beans.getStpalete());
            pst.setString(12, beans.getUsuario());
            pst.setString(13, beans.getRegistro());
            pst.setInt(14, beans.getVerifica_principal());
             pst.setString(15, beans.getId_palete());
            pst.execute();
            System.out.println("Salvo palete");
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar" + ex);
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar . \n\n" + ex);
        }
        conex.desconecta();
    }

    public void AlterarTemp(BeansPalete beans) {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" UPDATE paletetemp\n"
                    + "   SET  pctpad=?, lastro=?, altura=?, pctavu=?, totpct=?, \n"
                    + "       unavu=?, totparc=?, mov_palete=?, qtd_palete=?, nota_palete=?, \n"
                    + "       stpalete=?, usuario_palete=?, registro_palete=?,verifica_principal=?  \n"
                    + "       \n"
                    + " WHERE idlinha=?");

            pst.setString(1, beans.getPctpad());
            pst.setString(2, beans.getLastro());
            pst.setString(3, beans.getAltura());
            pst.setString(4, beans.getPctavu());
            pst.setString(5, beans.getTotpct());
            
            pst.setString(6, beans.getUnavu());
            pst.setString(7, beans.getTotparc());
            pst.setString(8, beans.getMov_palete());
            pst.setString(9, beans.getQtd_palete());
            pst.setString(10, beans.getNota_palete());
            
            pst.setInt(11, beans.getStpalete());
            pst.setString(12, beans.getUsuario());
            pst.setString(13, beans.getRegistro());
            pst.setInt(14, beans.getVerifica_principal());
             pst.setInt(15, beans.getIdlinha());
            pst.execute();
//            JOptionPane.showMessageDialog(null, "Alterado");
            System.out.println("Alterado " + beans.getIdlinha());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Alterar\n" + ex);
        }
        conex.desconecta();
    }

    public void AlterarTemp2(BeansPalete beans) {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" UPDATE paletetemp SET  status_palete=? WHERE id_palete=? ");

            pst.setString(1, beans.getStatus_palete());

            pst.setString(2, beans.getId_palete());

            pst.execute();
//            JOptionPane.showMessageDialog(null, "Alterado");
            System.out.println("Alterado ");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Alterar\n" + ex);
        }
        conex.desconecta();
    }

    public void ExcluirTempLista(BeansPalete beans) {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" UPDATE paletetemp SET  stpalete=?, verifica_principal=? WHERE mov_palete=? ");
            pst.setInt(1,beans.getStpalete());
             pst.setInt(2,beans.getVerifica_principal());
            pst.setString(3, beans.getMov_palete());
            pst.execute();
            System.out.println("Excluido ");
        } catch (SQLException ex) {
            System.out.println("Erro  " + ex);
            JOptionPane.showMessageDialog(null, "Erro ao Excluir . \\n" + ex);
        }
        conex.desconecta();
    }
    
      public void ExcluirTempLinha(BeansPalete beans) {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" UPDATE paletetemp SET  stpalete=? , verifica_principal=? WHERE idlinha=? ");
            pst.setInt(1,beans.getStpalete());
            pst.setInt(2,beans.getVerifica_principal());
             pst.setInt(3, beans.getIdlinha());
            pst.execute();
            System.out.println("Excluido ");
        } catch (SQLException ex) {
            System.out.println("Erro  " + ex);
            JOptionPane.showMessageDialog(null, "Erro ao Excluir . \\n" + ex);
        }
        conex.desconecta();
    }

}
