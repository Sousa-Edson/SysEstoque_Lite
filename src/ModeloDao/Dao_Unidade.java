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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Unidade;
import view.UnidadeCadastroJIF;

/**
 *
 * @author edson
 */
public class Dao_Unidade {

    ConexaoBD conex = new ConexaoBD();
    Beans_Unidade unidadeOld = new Beans_Unidade();

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
            Salvar_Unidade(unidadeOld);
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

    /*corrigidos abaixo desta linha*/
    public List<Unidade> listarUnidades() {
        List<Unidade> unidades = new ArrayList<>();
 
        conex.conexao();
        conex.executaSql2("SELECT id_unidade, id_referenciaunidade, sigla_unidade, desc_unidade, registro_unidade, \n"
                + "       usuario_unidade,fragmento_unidade,stunid \n"
                + "  FROM unidade where stunid =1 order by id_referenciaunidade desc ");

        try {
            conex.rs.first();
            do {
                unidades.add(new Unidade(conex.rs.getInt("id_referenciaunidade"),
                        conex.rs.getInt("id_unidade"),
                        conex.rs.getInt("stunid"), 
                        conex.rs.getInt("fragmento_unidade"),
                        conex.rs.getString("sigla_unidade"),
                        conex.rs.getString("desc_unidade"),
                        conex.rs.getString("registro_unidade"),
                        conex.rs.getString("usuario_unidade")));

            } while (conex.rs.next());
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeCadastroJIF.class.getName()).log(Level.SEVERE, null, ex);
        }
        conex.desconecta();
        return unidades;
    }

}
