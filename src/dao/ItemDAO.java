/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import ConectaBanco.ConexaoBD;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        /*SELECT  ,  ,  ,  ,  ,  ,  ,  ,  ,
             ,  ,  ,  ,  ,  ,  , modo_mov,  ,  ,  ,  
FROM public.movprodutobase;*/
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

}
