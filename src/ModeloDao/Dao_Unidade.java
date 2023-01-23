/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDao;

import ModeloBeans.Beans_Unidade;
import ConectaBanco.ConexaoBD;
import java.awt.Component;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author edson
 */
public class Dao_Unidade {

    ConexaoBD conex = new ConexaoBD();
    Beans_Unidade unidade = new Beans_Unidade();

    public void Salvar_Unidade(Beans_Unidade unidade) {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" INSERT INTO unidade("
                    + "             id_referenciaunidade, sigla_unidade, desc_unidade, registro_unidade, "
                    + "            usuario_unidade, stunid,fragmento_unidade)"
                    + "    VALUES (?, ?, ?, ?, ?, ?,?);");
            pst.setInt(1, unidade.getId_referencia());
            pst.setString(2, unidade.getSigla_unidade());
            pst.setString(3, unidade.getDesc_unidade());
            pst.setString(4, unidade.getRegistro_unidade());
            pst.setString(5, unidade.getUsuario_unidade());
            pst.setInt(6, unidade.getStatus_unidade());
             pst.setInt(7, unidade.getFragmento_unidade());
            pst.execute();
//            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso. ");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar unidade. \nSalvar_Unidade\n" + ex);
            VerificaResposta();
        }
        conex.desconecta();
    }

    public void VerificaResposta() {
        int resposta = 0;
        Component rootPane = null;
        resposta = JOptionPane.showConfirmDialog(rootPane, "Deseja realmente incluir ? ");
        {
            Salvar_Unidade(unidade);
        }
    }

    public void Alterar_Unidade(Beans_Unidade unidade) {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" UPDATE unidade"
                    + "   SET stunid=?"
                    + " WHERE id_unidade=?;");

            pst.setInt(1, unidade.getStatus_unidade());

            pst.setInt(2, unidade.getId_unidade());

            pst.execute();
            JOptionPane.showMessageDialog(null, "Alterado");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Alterar_Unidade\n" + ex);
        }
        conex.desconecta();
    }

    public void Excluir_Unidade(Beans_Unidade unidade) {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" UPDATE unidade"
                    + "   SET stunid=?, usuario_unidade=?, registro_unidade=?"
                    + " WHERE id_unidade=?;");

            pst.setInt(1, unidade.getStatus_unidade());
            pst.setString(2, unidade.getUsuario_unidade());
            pst.setString(3, unidade.getRegistro_unidade());

            pst.setInt(4, unidade.getId_unidade());

            pst.execute();
//            JOptionPane.showMessageDialog(null, "Excuido");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir /  atualizar produto. \\n" + ex);
        }
        conex.desconecta();
    }

}
