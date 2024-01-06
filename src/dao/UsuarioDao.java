/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import ConectaBanco.ConexaoBD;
import ModeloBeans.ModeloTabela;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import model.Usuario;

/**
 *
 * @author edson
 */
public class UsuarioDao {

    private final ConexaoBD conex;

    public UsuarioDao() {
        conex = new ConexaoBD();
    }

    public void salvar(Usuario usuario) {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" INSERT INTO usuario("
                    + "             id_referencia, sigla_usuario, desc_usuario, registro_usuario, "
                    + "            usuario_usuario, stusu,senha_usuario)"
                    + "    VALUES (?, ?, ?, ?, ?, ?,?);");

            pst.setInt(1, usuario.getId_referencia());
            pst.setString(2, usuario.getSigla_usuario());
            pst.setString(3, usuario.getDesc_usuario());
            pst.setString(4, usuario.getRegistro_usuario());
            pst.setString(5, usuario.getUsuario_usuario());
            pst.setInt(6, usuario.getStatus_usuario());
            pst.setString(7, usuario.getSenha_usuario());
            pst.execute();
//            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso. ");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar . \n\n" + ex);
        }
        conex.desconecta();
    }
    
    public void atualizar(Usuario usuario) {
    conex.conexao();
    try {
        // Modifique a query para realizar um update
        java.sql.PreparedStatement pst = conex.con.prepareStatement("UPDATE usuario SET "
                + "id_referencia = ?, "
                + "sigla_usuario = ?, "
                + "desc_usuario = ?, "
                + "registro_usuario = ?, "
                + "usuario_usuario = ?, "
                + "stusu = ?, "
                + "senha_usuario = ? "
                + "WHERE id_usuario = ?");

        // Preencha os valores na query
        pst.setInt(1, usuario.getId_referencia());
        pst.setString(2, usuario.getSigla_usuario());
        pst.setString(3, usuario.getDesc_usuario());
        pst.setString(4, usuario.getRegistro_usuario());
        pst.setString(5, usuario.getUsuario_usuario());
        pst.setInt(6, usuario.getStatus_usuario());
        pst.setString(7, usuario.getSenha_usuario());

        // Defina o ID do usuário para a cláusula WHERE
        pst.setInt(8, usuario.getId_usuario());

        pst.executeUpdate();
        // JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso. ");
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Erro ao atualizar. \n\n" + ex);
    } finally {
        conex.desconecta();
    }
}


    public void alterar(Usuario usuario) {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" UPDATE usuario"
                    + "   SET stusu=?"
                    + " WHERE id_usuario=?;");

            pst.setInt(1, usuario.getStatus_usuario());

            pst.setInt(2, usuario.getId_usuario());

            pst.execute();
            JOptionPane.showMessageDialog(null, "Alterado");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Alterar\n" + ex);
        }
    }

    public void excluir(Usuario usuario) {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" UPDATE usuario"
                    + "   SET  stusu=?,usuario_usuario=?, registro_usuario=?"
                    + " WHERE id_usuario=?;");

            pst.setInt(1, usuario.getStatus_usuario());
            pst.setString(2, usuario.getUsuario_usuario());
            pst.setString(3, usuario.getRegistro_usuario());

            pst.setInt(4, usuario.getId_usuario());

            pst.execute();
//            JOptionPane.showMessageDialog(null, "Excuido");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir . \n" + ex);
        }
        conex.desconecta();
    }

    public int carregaUltimo() {
        int id_referencia = 0;
        conex.conexao();
        conex.executaSql2("SELECT  id_referencia  FROM usuario where id_referencia is not null and id_referencia !=0 order by id_referencia asc ");
        try {
            conex.rs.last();
            id_referencia = conex.rs.getInt("id_referencia");
            id_referencia = id_referencia + 1;
        } catch (SQLException ex) {
            id_referencia = 1;
            System.err.println("Erro:: " + ex.getMessage());
        }
        conex.desconecta();
        return id_referencia;
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        conex.conexao();
        conex.executaSql2("SELECT  id_referencia, sigla_usuario, desc_usuario, registro_usuario, "
                + "            usuario_usuario, stusu,senha_usuario,id_usuario  "
                + "FROM usuario WHERE stusu = 1 ORDER BY id_usuario DESC");

        try {
            while (conex.rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId_referencia(conex.rs.getInt("id_referencia"));
                usuario.setId_referencia(conex.rs.getInt("id_usuario"));
                usuario.setStatus_usuario(conex.rs.getInt("stusu"));

                usuario.setSigla_usuario(conex.rs.getString("sigla_usuario"));
                usuario.setDesc_usuario(conex.rs.getString("desc_usuario"));
                usuario.setRegistro_usuario(conex.rs.getString("registro_usuario"));
                usuario.setUsuario_usuario(conex.rs.getString("usuario_usuario"));
                usuario.setSenha_usuario(conex.rs.getString("senha_usuario"));

                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            System.out.println("Erro:: " + ex);

        } finally {
            conex.desconecta();
        }
        return usuarios;
    }

    public Usuario obterUsuarioPorId(int idUsuario) {
        System.out.println("idUsuario" + idUsuario);
        conex.conexao();
        String query
                = "SELECT  id_referencia, sigla_usuario, desc_usuario, registro_usuario,  "
                + "  usuario_usuario, stusu,senha_usuario,id_usuario  FROM usuario WHERE id_usuario = ? ;";

        try (PreparedStatement pst = conex.con.prepareStatement(query)) {
            pst.setInt(1, idUsuario);

            try (ResultSet resultado = pst.executeQuery()) {
                if (resultado.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId_referencia(resultado.getInt("id_referencia"));
                    usuario.setId_usuario(resultado.getInt("id_usuario"));
                    usuario.setStatus_usuario(resultado.getInt("stusu"));

                    usuario.setSigla_usuario(resultado.getString("sigla_usuario"));
                    usuario.setDesc_usuario(resultado.getString("desc_usuario"));
                    usuario.setRegistro_usuario(resultado.getString("registro_usuario"));
                    usuario.setUsuario_usuario(resultado.getString("usuario_usuario"));
                    usuario.setSenha_usuario(resultado.getString("senha_usuario"));

                    return usuario;
                } else {
                    System.out.println("ERRO????");
                    // Não foi encontrada nenhuma nota com o ID fornecido
                    return null;
                }
            }
        } catch (SQLException ex) {
            System.out.println("obterUsuarioPorId:: " + ex.getMessage());
        } finally {
            conex.desconecta();
        }

        // Retorna null se a usuario não for encontrada
        return null;
    }

}
