/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDao;

import ModeloBeans.Beans_Setor;
import ModeloBeans.Beans_Unidade;
import ConectaBanco.ConexaoBD;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author edson
 */
public class Dao_Setor {

    ConexaoBD conex = new ConexaoBD();
    Beans_Setor beans = new Beans_Setor();

    public void Salvar(Beans_Setor beans) {
//        preencherUnidade(cadProd.getUnprod());
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" INSERT INTO setor("
                    + "             id_referencia, sigla_setor, desc_setor, registro_setor, "
                    + "            usuario_setor, stsetor)"
                    + "    VALUES (?, ?, ?, ?, ?, ?);");

            pst.setInt(1, beans.getId_referencia());
            pst.setString(2, beans.getSigla_setor());
            pst.setString(3, beans.getDesc_setor());
            pst.setString(4, beans.getRegistro_setor());
            pst.setString(5, beans.getUsuario_setor());
            pst.setInt(6, beans.getStatus_setor());

            pst.execute();
//            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso. ");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar . \n\n" + ex);
        }
        conex.desconecta();
    }

    public void Alterar(Beans_Setor beans) {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" UPDATE setor"
                    + "   SET stsetor=?"
                    + " WHERE id_setor=?;");

            pst.setInt(1, beans.getStatus_setor());

            pst.setInt(2, beans.getId_setor());

            pst.execute();
            JOptionPane.showMessageDialog(null, "Alterado");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Alterar\n" + ex);
        }
    }

    public void Excluir(Beans_Setor beans) {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" UPDATE setor"
                    + "   SET stsetor=?, usuario_setor=?, registro_setor=?"
                    + " WHERE id_setor=?;");

            pst.setInt(1, beans.getStatus_setor());
            pst.setString(2, beans.getUsuario_setor());
            pst.setString(3, beans.getRegistro_setor());

            pst.setInt(4, beans.getId_setor());

            pst.execute();
//            JOptionPane.showMessageDialog(null, "Excuido");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir . \n" + ex);
        }
        conex.desconecta();
    }

}
