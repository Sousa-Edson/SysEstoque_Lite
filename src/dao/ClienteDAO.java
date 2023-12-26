/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author edson
 */
import ConectaBanco.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;

public class ClienteDAO {

    private final ConexaoBD conex;

    public ClienteDAO() {
        conex = new ConexaoBD();
    }

    public List<Cliente> listarClientes(boolean ordenarPorId) {
        conex.conexao();
        List<Cliente> clientes = new ArrayList<>();

        String ordenacao = ordenarPorId ? "ORDER BY sis_ecft DESC" : "ORDER BY ecft_nome ASC";

        String sql = "SELECT ecft_id ,sis_ecft, ecft_tipo, ecft_nome, ecft_cnpj, ecft_inscricao, "
                + "ecft_descricao, ecft_telefone, ecft_endereco, ecft_no, ecft_cep, "
                + "ecft_complemento, ecft_bairro, ecft_cidade, ecft_observacao, "
                + "ecft_usuario, stecft, ecft_registro FROM ecft " + ordenacao;

        try (PreparedStatement stmt = conex.preparaSql(sql); ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {
                Cliente cliente = new Cliente();
                cliente.setCliente_id(resultado.getInt("ecft_id"));
                cliente.setSis_cliente(resultado.getInt("sis_ecft"));
                cliente.setCliente_tipo(resultado.getString("ecft_tipo"));
                cliente.setCliente_nome(resultado.getString("ecft_nome"));
                cliente.setCliente_cnpj(resultado.getString("ecft_cnpj"));
                cliente.setCliente_inscricao(resultado.getString("ecft_inscricao"));
                cliente.setCliente_descricao(resultado.getString("ecft_descricao"));
                cliente.setCliente_telefone(resultado.getString("ecft_telefone"));
                cliente.setCliente_endereco(resultado.getString("ecft_endereco"));
                cliente.setCliente_no(resultado.getString("ecft_no"));
                cliente.setCliente_cep(resultado.getString("ecft_cep"));
                cliente.setCliente_complemento(resultado.getString("ecft_complemento"));
                cliente.setCliente_bairro(resultado.getString("ecft_bairro"));
                cliente.setCliente_cidade(resultado.getString("ecft_cidade"));
                cliente.setCliente_observacao(resultado.getString("ecft_observacao"));
                cliente.setCliente_usuario(resultado.getString("ecft_usuario"));
                cliente.setStcliente(resultado.getInt("stecft"));
                cliente.setCliente_registro(resultado.getString("ecft_registro"));

                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("erro" + e.getMessage());
        } finally {
            conex.desconecta();
        }

        return clientes;
    }
}
