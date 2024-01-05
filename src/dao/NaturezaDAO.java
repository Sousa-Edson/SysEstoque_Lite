/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import ConectaBanco.ConexaoBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Natureza;

/**
 *
 * @author edson
 */
public class NaturezaDAO {

    private final ConexaoBD conex;

    public NaturezaDAO() {
        conex = new ConexaoBD();
    }

    public Natureza obterUmaNaturezaPorId(int id) {
        conex.conexao();
        String query = "SELECT id_natureza,id_referencianatureza, tipo_natureza, desc_natureza, registro_natureza, "
                + "usuario_natureza, stnat FROM natureza where id_natureza =? ";

        try (PreparedStatement pst = conex.con.prepareStatement(query)) {
            pst.setInt(1, id);

            try (ResultSet resultado = pst.executeQuery()) {
                if (resultado.next()) {
                    Natureza natureza = new Natureza();
                    natureza.setId_natureza(resultado.getInt("id_natureza"));
                    natureza.setId_referencia(resultado.getInt("id_referencianatureza"));
                    natureza.setTipo_natureza(resultado.getString("tipo_natureza"));
                    natureza.setDesc_natureza(resultado.getString("desc_natureza"));
                    natureza.setRegistro_natureza(resultado.getString("registro_natureza"));
                    natureza.setUsuario_natureza(resultado.getString("usuario_natureza"));
                    natureza.setStatus_natureza(resultado.getInt("stnat"));
                    return natureza;
                } else {
                    // Não foi encontrada nenhuma nota com o ID fornecido
                    return null;
                }
            }
        } catch (SQLException ex) {
            // Lidar com a exceção
            ex.printStackTrace(); // Considere usar um mecanismo de log apropriado
            return null;
        }
    }

    public List<Natureza> listarNaturezas() {
        conex.conexao();
        List<Natureza> naturezas = new ArrayList<>();

        String sql = "SELECT id_natureza,id_referencianatureza, tipo_natureza, desc_natureza, registro_natureza, "
                + "usuario_natureza, stnat FROM natureza";

        try (PreparedStatement stmt = conex.preparaSql(sql)) {
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                Natureza natureza = new Natureza();
                natureza.setId_natureza(resultado.getInt("id_natureza"));
                natureza.setId_referencia(resultado.getInt("id_referencianatureza"));
                natureza.setTipo_natureza(resultado.getString("tipo_natureza"));
                natureza.setDesc_natureza(resultado.getString("desc_natureza"));
                natureza.setRegistro_natureza(resultado.getString("registro_natureza"));
                natureza.setUsuario_natureza(resultado.getString("usuario_natureza"));
                natureza.setStatus_natureza(resultado.getInt("stnat"));

                naturezas.add(natureza);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conex.desconecta();
        }

        return naturezas;
    }

    public List<Natureza> listarNaturezasAtivas() {
        conex.conexao();
        List<Natureza> naturezas = new ArrayList<>();

        String sql = "SELECT id_natureza,id_referencianatureza, tipo_natureza, desc_natureza, registro_natureza, "
                + "usuario_natureza, stnat FROM natureza WHERE stnat=1 ";

        try (PreparedStatement stmt = conex.preparaSql(sql)) {
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                Natureza natureza = new Natureza();
                natureza.setId_natureza(resultado.getInt("id_natureza"));
                natureza.setId_referencia(resultado.getInt("id_referencianatureza"));
                natureza.setTipo_natureza(resultado.getString("tipo_natureza"));
                natureza.setDesc_natureza(resultado.getString("desc_natureza"));
                natureza.setRegistro_natureza(resultado.getString("registro_natureza"));
                natureza.setUsuario_natureza(resultado.getString("usuario_natureza"));
                natureza.setStatus_natureza(resultado.getInt("stnat"));

                naturezas.add(natureza);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conex.desconecta();
        }

        return naturezas;
    }

    //------
    public int carregaUltimo() {
        int id_referencia = 0;
        conex.conexao();
        conex.executaSql2("SELECT  id_referencianatureza  FROM natureza where id_referencianatureza is not null and id_referencianatureza !=0 order by id_referencianatureza asc  ");
        try {
            conex.rs.last();
            id_referencia = conex.rs.getInt("id_referencianatureza");
            id_referencia = id_referencia + 1;
        } catch (SQLException ex) {
            id_referencia = 1;
        }
        conex.desconecta();
        return id_referencia;
    }

    public void salvar(Natureza natureza) {
        natureza.setId_referencia(carregaUltimo());
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" INSERT INTO natureza("
                    + "             id_referencianatureza, tipo_natureza, desc_natureza, registro_natureza,"
                    + " usuario_natureza, stnat)"
                    + "    VALUES (?, ?, ?, ?, ?, ?);");

            pst.setInt(1, natureza.getId_referencia());
            pst.setString(2, natureza.getTipo_natureza());
            pst.setString(3, natureza.getDesc_natureza());
            pst.setString(4, natureza.getRegistro_natureza());
            pst.setString(5, natureza.getUsuario_natureza());
            pst.setInt(6, natureza.getStatus_natureza());
 System.out.println("salvar:: "+natureza);
//            pst.execute();
//            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso. ");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar . \n\n" + ex);
        }
        conex.desconecta();
    }

    public void atualizar(Natureza natureza) {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(
                    "UPDATE natureza SET tipo_natureza=?, desc_natureza=?, registro_natureza=?, "
                    + "usuario_natureza=?, stnat=? , id_referencianatureza=? WHERE id_natureza=?");

            pst.setString(1, natureza.getTipo_natureza());
            pst.setString(2, natureza.getDesc_natureza());
            pst.setString(3, natureza.getRegistro_natureza());
            pst.setString(4, natureza.getUsuario_natureza());
            pst.setInt(5, natureza.getStatus_natureza());
            pst.setInt(6, natureza.getId_referencia());
            pst.setInt(7, natureza.getId_natureza());
            System.out.println("atualizar:: "+natureza);
            int rowsUpdated = pst.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Registro atualizado com sucesso.");
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum registro foi atualizado.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar o registro. \n\n" + ex);
        } finally {
            conex.desconecta();
        }
    }
  public void marcarComoDeletado(Natureza natureza) {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(
                    "UPDATE natureza SET  stnat=?   WHERE id_natureza=?");
 
            pst.setInt(1, natureza.getStatus_natureza()); 
            pst.setInt(2, natureza.getId_natureza());
            System.out.println("marcarComoDeletado:: "+natureza);
            int rowsUpdated = pst.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Registro marcarComoDeletado com sucesso.");
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum registro foi marcarComoDeletado.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao marcarComoDeletado o registro. \n\n" + ex);
        } finally {
            conex.desconecta();
        }
    }
}
