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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Produto;

public class ProdutoDao {

    private final ConexaoBD conex = new ConexaoBD();

    public boolean salvarProduto(Produto produto) {
        conex.conexao();
        try (PreparedStatement pst = conex.con.prepareStatement(
                "INSERT INTO produto (data_reg, hora_reg, ncm_prod, tipo_prod, nome_prod, "
                + "edicao_prod, cfop_prod, obs_prod, usu_prod, valor_ex, sis_prod, id_prod, "
                + "un_prod, status_prod, saldo_prod, estoque_prod, valor) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            pst.setString(1, produto.getData_reg());
            pst.setString(2, produto.getHora_reg());
            pst.setString(3, produto.getNcm_prod());
            pst.setString(4, produto.getTipo_prod());
            pst.setString(5, produto.getNome_prod());
            pst.setString(6, produto.getEdicao_prod());
            pst.setString(7, produto.getCfop_prod());
            pst.setString(8, produto.getObs_prod());
            pst.setString(9, produto.getUsu_prod());
            pst.setString(10, produto.getValor_ex());
            pst.setInt(11, produto.getSis_prod());
            pst.setInt(12, produto.getId_prod());
            pst.setInt(13, produto.getUn_prod());
            pst.setInt(14, produto.getStatus_prod());
            pst.setDouble(15, produto.getSaldo_prod());
            pst.setDouble(16, produto.getEstoque_prod());
            pst.setDouble(17, produto.getValor());

            pst.execute();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto.\nSalvar_Produto\n" + ex);

        } finally {
            conex.desconecta();
        }
        return false;
    }

    public boolean alterarProduto(Produto produto) {
        boolean ok = false;
        conex.conexao();
        try (PreparedStatement pst = conex.con.prepareStatement(
                "UPDATE produto SET data_reg=?, hora_reg=?, ncm_prod=?, tipo_prod=?, "
                + "nome_prod=?, edicao_prod=?, cfop_prod=?, obs_prod=?, usu_prod=?, "
                + "valor_ex=?, sis_prod=?, id_prod=?, un_prod=?, status_prod=?, saldo_prod=?, "
                + "estoque_prod=?, valor=? WHERE id_produto=?")) {

            pst.setString(1, produto.getData_reg());
            pst.setString(2, produto.getHora_reg());
            pst.setString(3, produto.getNcm_prod());
            pst.setString(4, produto.getTipo_prod());
            pst.setString(5, produto.getNome_prod());
            pst.setString(6, produto.getEdicao_prod());
            pst.setString(7, produto.getCfop_prod());
            pst.setString(8, produto.getObs_prod());
            pst.setString(9, produto.getUsu_prod());
            pst.setString(10, produto.getValor_ex());
            pst.setInt(11, produto.getSis_prod());
            pst.setInt(12, produto.getId_prod());
            pst.setInt(13, produto.getUn_prod());
            pst.setInt(14, produto.getStatus_prod());
            pst.setDouble(15, produto.getSaldo_prod());
            pst.setDouble(16, produto.getEstoque_prod());
            pst.setDouble(17, produto.getValor());
//            pst.setInt(18, produto.getId_produto());

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto alterado com sucesso.");
            ok = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar produto.\nAlterar_Produto\n" + ex);
        } finally {
            conex.desconecta();
        }
        return ok;
    }

    public void excluirProduto(Produto produto) {
        conex.conexao();
        try (PreparedStatement pst = conex.con.prepareStatement(
                "UPDATE produto SET status_prod=? WHERE id_produto=?")) {

//            pst.setInt(1, produto.getStatus_prod());
//            pst.setInt(2, produto.getId_produto());
//            pst.execute();
//            JOptionPane.showMessageDialog(null, "Produto " + produto.getId_produto() + " deletado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir/atualizar produto.\n" + ex);
        } finally {
            conex.desconecta();
        }
    }

    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
//        conex.conexao();
//        conex.executaSql2("SELECT id_produto, data_reg, hora_reg, ncm_prod, tipo_prod, nome_prod, "
//                + "edicao_prod, cfop_prod, obs_prod, usu_prod, valor_ex, sis_prod, id_prod, "
//                + "un_prod, status_prod, saldo_prod, estoque_prod, valor FROM produto "
//                + "WHERE status_prod = 1 ORDER BY data_reg DESC");
//
//        try {
//            while (conex.rs.next()) {
//                produtos.add(new Produto(
//                        conex.rs.getInt("id_produto"),
//                        conex.rs.getString("data_reg"),
//                        conex.rs.getString("hora_reg"),
//                        conex.rs.getString("ncm_prod"),
//                        conex.rs.getString("tipo_prod"),
//                        conex.rs.getString("nome_prod"),
//                        conex.rs.getString("edicao_prod"),
//                        conex.rs.getString("cfop_prod"),
//                        conex.rs.getString("obs_prod"),
//                        conex.rs.getString("usu_prod"),
//                        conex.rs.getString("valor_ex"),
//                        conex.rs.getInt("sis_prod"),
//                        conex.rs.getInt("id_prod"),
//                        conex.rs.getInt("un_prod"),
//                        conex.rs.getInt("status_prod"),
//                        conex.rs.getDouble("saldo_prod"),
//                        conex.rs.getDouble("estoque_prod"),
//                        conex.rs.getDouble("valor")));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            conex.desconecta();
//        }
        return produtos;
    }

    public Produto obterProdutoPorId(int idProduto) {
//        conex.conexao();
//        try {
//            PreparedStatement pstmt = conex.con.prepareStatement(
//                    "SELECT id_produto, data_reg, hora_reg, ncm_prod, tipo_prod, nome_prod, "
//                    + "edicao_prod, cfop_prod, obs_prod, usu_prod, valor_ex, sis_prod, id_prod, "
//                    + "un_prod, status_prod, saldo_prod, estoque_prod, valor FROM produto "
//                    + "WHERE id_produto = ? AND status_prod = 1");
//
//            pstmt.setInt(1, idProduto);
//            ResultSet rs = pstmt.executeQuery();
//
//            if (rs.next()) {
//                return new Produto(
//                        rs.getInt("id_produto"),
//                        rs.getString("data_reg"),
//                        rs.getString("hora_reg"),
//                        rs.getString("ncm_prod"),
//                        rs.getString("tipo_prod"),
//                        rs.getString("nome_prod"),
//                        rs.getString("edicao_prod"),
//                        rs.getString("cfop_prod"),
//                        rs.getString("obs_prod"),
//                        rs.getString("usu_prod"),
//                        rs.getString("valor_ex"),
//                        rs.getInt("sis_prod"),
//                        rs.getInt("id_prod"),
//                        rs.getInt("un_prod"),
//                        rs.getInt("status_prod"),
//                        rs.getDouble("saldo_prod"),
//                        rs.getDouble("estoque_prod"),
//                        rs.getDouble("valor"));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            conex.desconecta();
//        }
//
//        // Retorna null se o produto não for encontrado
//        return null;
//    }

    // ... outros métodos, se necessário
//}