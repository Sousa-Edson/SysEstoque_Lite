/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDao;

import ModeloBeans.Beans_Natureza;
import ConectaBanco.ConexaoBD;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author edson
 */
public class Dao_Natureza {

    ConexaoBD conex = new ConexaoBD();
    Beans_Natureza beans = new Beans_Natureza();

    public void Salvar(Beans_Natureza beans) {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" INSERT INTO natureza("
                    + "             id_referencianatureza, tipo_natureza, desc_natureza, registro_natureza,"
                    + " usuario_natureza, stnat)"
                    + "    VALUES (?, ?, ?, ?, ?, ?);");

            pst.setInt(1, beans.getId_referencia());
            pst.setString(2, beans.getTipo_natureza());
            pst.setString(3, beans.getDesc_natureza());
            pst.setString(4, beans.getRegistro_natureza());
            pst.setString(5, beans.getUsuario_natureza());
            pst.setInt(6, beans.getStatus_natureza());

            pst.execute();
//            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso. ");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar . \n\n" + ex);
        }
        conex.desconecta();
    }

    public void Alterar(Beans_Natureza beans) {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" UPDATE natureza"
                    + "   SET stnat=?"
                    + " WHERE id_natureza=?;");

            pst.setInt(1, beans.getStatus_natureza());

            pst.setInt(2, beans.getId_natureza());

            pst.execute();
            JOptionPane.showMessageDialog(null, "Alterado");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Alterar\n" + ex);
        }
        conex.desconecta();
    }

    public void Excluir(Beans_Natureza beans) {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" UPDATE natureza"
                    + "   SET stnat=?, usuario_natureza=?, registro_natureza=?"
                    + " WHERE id_natureza=?;");

            pst.setInt(1, beans.getStatus_natureza());
            pst.setString(2, beans.getUsuario_natureza());
            pst.setString(3, beans.getRegistro_natureza());

            pst.setInt(4, beans.getId_natureza());

            pst.execute();
//            JOptionPane.showMessageDialog(null, "Excuido");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir . \\n" + ex);
        }
        conex.desconecta();
    }

}
