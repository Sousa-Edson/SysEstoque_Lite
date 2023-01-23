/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDao;

import ModeloBeans.Beans_Setor;
import ModeloBeans.Beans_Unidade;
import ModeloBeans.Beans_Usuario;
import ConectaBanco.ConexaoBD;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author edson
 */
public class Dao_Usuario {

    ConexaoBD conex = new ConexaoBD();
    Beans_Usuario beans = new Beans_Usuario();

    public void Salvar(Beans_Usuario beans) {
//        preencherUnidade(cadProd.getUnprod());
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" INSERT INTO usuario("
                    + "             id_referencia, sigla_usuario, desc_usuario, registro_usuario, "
                    + "            usuario_usuario, stusu,senha_usuario)"
                    + "    VALUES (?, ?, ?, ?, ?, ?,?);");

            pst.setInt(1, beans.getId_referencia());
            pst.setString(2, beans.getSigla_usuario());
            pst.setString(3, beans.getDesc_usuario());
            pst.setString(4, beans.getRegistro_usuario());
            pst.setString(5, beans.getUsuario_usuario());
            pst.setInt(6, beans.getStatus_usuario());
            pst.setString(7, beans.getSenha_usuario());
            pst.execute();
//            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso. ");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar . \n\n" + ex);
        }
        conex.desconecta();
    }

    public void Alterar(Beans_Usuario beans) {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" UPDATE usuario"
                    + "   SET stusu=?"
                    + " WHERE id_usuario=?;");

            pst.setInt(1, beans.getStatus_usuario());

            pst.setInt(2, beans.getId_usuario());

            pst.execute();
            JOptionPane.showMessageDialog(null, "Alterado");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Alterar\n" + ex);
        }
    }

    public void Excluir(Beans_Usuario beans) {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" UPDATE usuario"
                    + "   SET  stusu=?,usuario_usuario=?, registro_usuario=?"
                    + " WHERE id_usuario=?;");

            pst.setInt(1, beans.getStatus_usuario());
            pst.setString(2, beans.getUsuario_usuario());
            pst.setString(3, beans.getRegistro_usuario());

            pst.setInt(4, beans.getId_usuario());

            pst.execute();
//            JOptionPane.showMessageDialog(null, "Excuido");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir . \n" + ex);
        }
        conex.desconecta();
    }

}
