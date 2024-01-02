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
import model.Item;

/**
 *
 * @author edson
 */
public class ItemDAO {

    private final ConexaoBD conex;

    public ItemDAO() {
        conex = new ConexaoBD();
    }

    public void salvarItem(Item item) {
        conex.conexao();
        // Use PreparedStatement para evitar SQL injection
        String sql = "INSERT INTO movprodutobase ( id_prod_ent, nota_mov, modo_mov, stmovimento, stsaldo,  qtd_mov, "
                + "qtd_prod, qtd_calc, valor_real, sistema_mov, data_mov, qtd_prod_ex, qtd_calc_ex, valor_moeda, destino_mov, "
                + "complemento_mov, registro_mov, volume, usuario_mov, total_mov) "
                + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = conex.con.prepareStatement(sql)) {
            preparedStatement.setInt(1, item.getId_prod_ent());
            preparedStatement.setInt(2, item.getNota_mov());
            preparedStatement.setInt(3, item.getModo_mov());
            preparedStatement.setInt(4, item.getStatus_mov());
            preparedStatement.setInt(5, item.getStsaldo());
            preparedStatement.setDouble(6, item.getQtd_mov());
            preparedStatement.setDouble(7, item.getQtd_prod());
            preparedStatement.setDouble(8, item.getQtd_calc());
            preparedStatement.setDouble(9, item.getValor_real());
            preparedStatement.setString(10, item.getSistema_mov());
            preparedStatement.setString(11, item.getData_mov());
            preparedStatement.setString(12, item.getQtd_prod_ex());
            preparedStatement.setString(13, item.getQtd_calc_ex());
            preparedStatement.setString(14, item.getValor_moeda());
            preparedStatement.setString(15, item.getDestino_mov());
            preparedStatement.setString(16, item.getComplemento_mov());
            preparedStatement.setString(17, item.getRegistro_mov());
            preparedStatement.setString(18, item.getVolume());
            preparedStatement.setString(19, item.getUsuario_mov());
            preparedStatement.setString(20, item.getTotal_mov());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Trate a exceção apropriadamente no seu código real
        }
    }

    public List<Item> obterItensPorIdNota(int id) {
        List<Item> itens = new ArrayList<>();

        conex.conexao();
        String query = "SELECT * FROM  movprodutobase"
                + "WHERE nota_mov = ? "
                + "ORDER BY id_mov asc";

        /// corrigir sql
        try (PreparedStatement pst = conex.con.prepareStatement(query)) {
            pst.setInt(1, id);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Item item = new Item();
                    item.setId_prod_ent(rs.getInt("id_prod_ent"));
                    item.setNota_mov(rs.getInt("nota_mov"));
                    item.setModo_mov(rs.getInt("modo_mov"));
                    item.setStatus_mov(rs.getInt("stmovimento"));
                    item.setStsaldo(rs.getInt("stsaldo"));
                    item.setQtd_mov(rs.getDouble("qtd_mov"));
                    item.setQtd_prod(rs.getDouble("qtd_prod"));
                    item.setQtd_calc(rs.getDouble("qtd_calc"));
                    item.setValor_real(rs.getDouble("valor_real"));
                    item.setSistema_mov(rs.getString("sistema_mov"));
                    item.setData_mov(rs.getString("data_mov"));
                    item.setQtd_prod_ex(rs.getString("qtd_prod_ex"));
                    item.setQtd_calc(rs.getDouble("qtd_calc_ex"));
                    item.setValor_moeda(rs.getString("valor_moeda"));
                    item.setDestino_mov(rs.getString("destino_mov"));
                    item.setComplemento_mov(rs.getString("complemento_mov"));
                    item.setRegistro_mov(rs.getString("registro_mov"));
                    item.setVolume(rs.getString("volume"));
                    item.setUsuario_mov(rs.getString("usuario_mov"));
                    item.setTotal_mov(rs.getString("total_mov"));
                    item.setId_mov(rs.getInt("id_mov"));
                    itens.add(item);
                }

            }
        } catch (SQLException ex) {
            // Lidar com a exceção
            ex.printStackTrace(); // Considere usar um mecanismo de log apropriado

        }
        return itens;

    }
}
