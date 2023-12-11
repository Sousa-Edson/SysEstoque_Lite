/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import ConectaBanco.ConexaoBD;
import java.sql.PreparedStatement;
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
public class UnidadeDao {

    private final ConexaoBD conex = new ConexaoBD();

    public void salvarUnidade(Unidade unidade) {
          conex.conexao();
        try (PreparedStatement pst = conex.con.prepareStatement(
                "INSERT INTO unidade (id_referenciaunidade, sigla_unidade, desc_unidade, registro_unidade, "
                + "usuario_unidade, stunid, fragmento_unidade) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            pst.setInt(1, unidade.getId_referencia());
            pst.setString(2, unidade.getSigla_unidade());
            pst.setString(3, unidade.getDesc_unidade());
            pst.setString(4, unidade.getRegistro_unidade());
            pst.setString(5, unidade.getUsuario_unidade());
            pst.setInt(6, unidade.getStatus_unidade());
            pst.setInt(7, unidade.getFragmento_unidade());
            pst.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar unidade.\nSalvar_Unidade\n" + ex);

        } finally {
            conex.desconecta();
        }
    }

    public void alterarUnidade(Unidade unidade) {
          conex.conexao();
        try (PreparedStatement pst = conex.con.prepareStatement(
                "UPDATE unidade SET stunid=? WHERE id_unidade=?")) {
            pst.setInt(1, unidade.getStatus_unidade());
            pst.setInt(2, unidade.getId_unidade());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Alterado");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Alterar_Unidade\n" + ex);
        } finally {
            conex.desconecta();
        }
    }

    public void excluirUnidade(Unidade unidade) {
          conex.conexao();
        try (PreparedStatement pst = conex.con.prepareStatement(
                "UPDATE unidade SET stunid=?, usuario_unidade=?, registro_unidade=? WHERE id_unidade=?")) {
            pst.setInt(1, unidade.getStatus_unidade());
            pst.setString(2, unidade.getUsuario_unidade());
            pst.setString(3, unidade.getRegistro_unidade());
            pst.setInt(4, unidade.getId_unidade());
            pst.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir/Atualizar produto.\n" + ex);
        } finally {
            conex.desconecta();
        }
    }

    public List<Unidade> listarUnidades() {
        List<Unidade> unidades = new ArrayList<>();
        conex.conexao();
        conex.executaSql2("SELECT id_unidade, id_referenciaunidade, sigla_unidade, desc_unidade, "
                + "registro_unidade, usuario_unidade, fragmento_unidade, stunid "
                + "FROM unidade WHERE stunid = 1 ORDER BY id_referenciaunidade DESC");

        try {
            while (conex.rs.next()) {
                unidades.add(new Unidade(conex.rs.getInt("id_referenciaunidade"),
                        conex.rs.getInt("id_unidade"),
                        conex.rs.getInt("stunid"),
                        conex.rs.getInt("fragmento_unidade"),
                        conex.rs.getString("sigla_unidade"),
                        conex.rs.getString("desc_unidade"),
                        conex.rs.getString("registro_unidade"),
                        conex.rs.getString("usuario_unidade")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeCadastroJIF.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.desconecta();
        }
        return unidades;
    }

}
