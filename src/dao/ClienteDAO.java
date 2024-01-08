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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Cliente;

public class ClienteDAO {

    private final ConexaoBD conex;

    public ClienteDAO() {
        conex = new ConexaoBD();
    }

    public List<Cliente> listarClientesComFIltro(String busca, boolean ordenarPorId) {
        conex.conexao();
        List<Cliente> clientes = new ArrayList<>();

        String ordenacao = ordenarPorId ? "ORDER BY ecft_id DESC" : "ORDER BY ecft_nome ASC";

        String sql = "SELECT ecft_id ,sis_ecft, ecft_tipo, ecft_nome, ecft_cnpj, ecft_inscricao, "
                + "ecft_descricao, ecft_telefone, ecft_endereco, ecft_no, ecft_cep, "
                + "ecft_complemento, ecft_bairro, ecft_cidade, ecft_observacao, "
                + "ecft_usuario, stecft, ecft_registro FROM ecft "
                + "WHERE stecft=1 AND  (COALESCE(CAST(ecft_nome AS TEXT), '') || ' ' || COALESCE(CAST(ecft_descricao AS TEXT), '')) ILIKE ? " + ordenacao;

        try (PreparedStatement stmt = conex.preparaSql(sql);) {
            stmt.setString(1, "%" + busca + "%");
            ResultSet resultado = stmt.executeQuery();
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

    public List<Cliente> listarClientes(boolean ordenarPorId) {
        conex.conexao();
        List<Cliente> clientes = new ArrayList<>();

        String ordenacao = ordenarPorId ? "ORDER BY sis_ecft DESC" : "ORDER BY ecft_nome ASC";

        String sql = "SELECT ecft_id ,sis_ecft, ecft_tipo, ecft_nome, ecft_cnpj, ecft_inscricao, "
                + "ecft_descricao, ecft_telefone, ecft_endereco, ecft_no, ecft_cep, "
                + "ecft_complemento, ecft_bairro, ecft_cidade, ecft_observacao, "
                + "ecft_usuario, stecft, ecft_registro FROM ecft  WHERE stecft=1 " + ordenacao;

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

    public Cliente obterClientePorId(int i) {
        conex.conexao();
        String query
                = "SELECT ecft_id ,sis_ecft, ecft_tipo, ecft_nome, ecft_cnpj, ecft_inscricao, "
                + "ecft_descricao, ecft_telefone, ecft_endereco, ecft_no, ecft_cep, "
                + "ecft_complemento, ecft_bairro, ecft_cidade, ecft_observacao, "
                + "ecft_usuario, stecft, ecft_registro FROM ecft where ecft_id= ? ";

        try (PreparedStatement pst = conex.con.prepareStatement(query)) {
            pst.setInt(1, i);

            try (ResultSet resultado = pst.executeQuery()) {
                if (resultado.next()) {
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

                    return cliente;
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

    public int carregaUltimo() {
        int id_referencia = 1;
        conex.conexao();
        conex.executaSql2("SELECT  sis_ecft  FROM ecft where sis_ecft is not null and sis_ecft !=0 order by sis_ecft asc");
        try {
            conex.rs.last();
            id_referencia = conex.rs.getInt("sis_ecft");
            id_referencia = id_referencia + 1;
        } catch (SQLException ex) {
        } finally {
            conex.desconecta();
        }
        return id_referencia;
    }

    public void salvar(Cliente cliente) {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement("INSERT INTO ecft ("
                    + "ecft_registro, sis_ecft, ecft_tipo, ecft_nome, ecft_cnpj, ecft_inscricao, "
                    + "ecft_descricao, ecft_telefone, ecft_endereco, ecft_no, ecft_cep, "
                    + "ecft_complemento, ecft_bairro, ecft_cidade, ecft_observacao, ecft_usuario, "
                    + "stecft) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            pst.setString(1, cliente.getCliente_registro());
            pst.setInt(2, cliente.getSis_cliente());
            pst.setString(3, cliente.getCliente_tipo());
            pst.setString(4, cliente.getCliente_nome());
            pst.setString(5, cliente.getCliente_cnpj());
            pst.setString(6, cliente.getCliente_inscricao());
            pst.setString(7, cliente.getCliente_descricao());
            pst.setString(8, cliente.getCliente_telefone());
            pst.setString(9, cliente.getCliente_endereco());
            pst.setString(10, cliente.getCliente_no());
            pst.setString(11, cliente.getCliente_cep());
            pst.setString(12, cliente.getCliente_complemento());
            pst.setString(13, cliente.getCliente_bairro());
            pst.setString(14, cliente.getCliente_cidade());
            pst.setString(15, cliente.getCliente_observacao());
            pst.setString(16, cliente.getCliente_usuario());
            pst.setInt(17, cliente.getStcliente());

            pst.execute();
            // JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente. \n\n" + ex);
        } finally {
            conex.desconecta();
        }
    }

    public void atualizar(Cliente cliente) {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement("UPDATE ecft SET "
                    + "sis_ecft = ?, "
                    + "ecft_tipo = ?, "
                    + "ecft_nome = ?, "
                    + "ecft_cnpj = ?, "
                    + "ecft_inscricao = ?, "
                    + "ecft_descricao = ?, "
                    + "ecft_telefone = ?, "
                    + "ecft_endereco = ?, "
                    + "ecft_no = ?, "
                    + "ecft_cep = ?, "
                    + "ecft_complemento = ?, "
                    + "ecft_bairro = ?, "
                    + "ecft_cidade = ?, "
                    + "ecft_observacao = ?, "
                    + "ecft_usuario = ?, "
                    + "stecft = ?, "
                    + "ecft_registro = ? "
                    + "WHERE ecft_id = ?");

            pst.setInt(1, cliente.getSis_cliente());
            pst.setString(2, cliente.getCliente_tipo());
            pst.setString(3, cliente.getCliente_nome());
            pst.setString(4, cliente.getCliente_cnpj());
            pst.setString(5, cliente.getCliente_inscricao());
            pst.setString(6, cliente.getCliente_descricao());
            pst.setString(7, cliente.getCliente_telefone());
            pst.setString(8, cliente.getCliente_endereco());
            pst.setString(9, cliente.getCliente_no());
            pst.setString(10, cliente.getCliente_cep());
            pst.setString(11, cliente.getCliente_complemento());
            pst.setString(12, cliente.getCliente_bairro());
            pst.setString(13, cliente.getCliente_cidade());
            pst.setString(14, cliente.getCliente_observacao());
            pst.setString(15, cliente.getCliente_usuario());
            pst.setInt(16, cliente.getStcliente());
            pst.setString(17, cliente.getCliente_registro());

            // Define o ID do cliente para a cláusula WHERE
            pst.setInt(18, cliente.getCliente_id());

            pst.executeUpdate();
            // JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar cliente. \n\n" + ex);
        } finally {
            conex.desconecta();
        }
    }

    public boolean marcarComoDeletado(Cliente cliente) {
        boolean deletado = false;
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement("UPDATE ecft SET ecft_usuario = ?, stecft = ?,  ecft_registro = ?  WHERE ecft_id = ?");

            pst.setString(1, cliente.getCliente_usuario());
            pst.setInt(2, cliente.getStcliente());
            pst.setString(3, cliente.getCliente_registro());
            pst.setInt(4, cliente.getCliente_id());
            pst.executeUpdate();
            deletado = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar cliente. \n\n" + ex);
        } finally {
            conex.desconecta();
        }
        return deletado;
    }

}
