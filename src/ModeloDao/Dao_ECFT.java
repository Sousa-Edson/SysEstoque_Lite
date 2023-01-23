/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDao;

import ModeloBeans.Beans_ECFT;
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
public class Dao_ECFT {

    ConexaoBD conex = new ConexaoBD();
    Beans_ECFT beans = new Beans_ECFT();

    public void Salvar(Beans_ECFT beans) {
//        preencherUnidade(cadProd.getUnprod());
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement("INSERT INTO ecft(\n"
                    + "             sis_ecft, ecft_tipo, ecft_nome, ecft_cnpj, ecft_inscricao, \n"
                    + "            ecft_descricao, ecft_telefone, ecft_endereco, ecft_no, ecft_cep, \n"
                    + "            ecft_complemento, ecft_bairro, ecft_cidade, ecft_observacao, \n"
                    + "            ecft_usuario, stecft, ecft_registro)\n"
                    + "    VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?);");

            pst.setInt(1, beans.getSis_EFCT());
            pst.setString(2, beans.getEcft_tipo());
            pst.setString(3, beans.getEcft_nome());
            pst.setString(4, beans.getEcft_cnpj());
            pst.setString(5, beans.getEcft_inscricao());
            pst.setString(6, beans.getEcft_descricao());
            pst.setString(7, beans.getEcft_telefone());

            pst.setString(8, beans.getEcft_endereco());
            pst.setString(9, beans.getEcft_no());
            pst.setString(10, beans.getEcft_cep());
            pst.setString(11, beans.getEcft_complemento());
            pst.setString(12, beans.getEcft_bairro());
            pst.setString(13, beans.getEcft_cidade());
            pst.setString(14, beans.getEcft_observacao());
            pst.setString(15, beans.getEcft_usuario());
            pst.setInt(16, beans.getStecft());
            pst.setString(17, beans.getEcft_registro());
            pst.execute();
//            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso. ");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar . \n\n" + ex);
        }
        conex.desconecta();
    }

    public void Alterar(Beans_ECFT beans) {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" UPDATE ecft"
                    + "   SET stecft=?"
                    + " WHERE ecft_id=?;");

            pst.setInt(1, beans.getStecft());

            pst.setInt(2, beans.getEcft_id());

            pst.execute();
//            JOptionPane.showMessageDialog(null, "Alterado");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Alterar\n" + ex);
        }
        conex.desconecta();
    }

    public void Excluir(Beans_ECFT beans) {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" UPDATE ecft"
                    + "   SET stecft=?, ecft_usuario=?, ecft_registro=?"
                    + " WHERE ecft_id=?;");

            pst.setInt(1, beans.getStecft());
            pst.setString(2, beans.getEcft_usuario());
            pst.setString(3, beans.getEcft_registro());

            pst.setInt(4, beans.getEcft_id());

            pst.execute();
//            JOptionPane.showMessageDialog(null, "Excuido");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir . \n" + ex);
        }
        conex.desconecta();
    }

}
