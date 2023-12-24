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
import model.Unidade;
import view.internal.ProdutoCadastroJIF;

public class ProdutoDao {

    private final ConexaoBD conex = new ConexaoBD();

    public boolean salvarProduto(Produto produto) {
        conex.conexao();
        try (PreparedStatement pst = conex.con.prepareStatement(
                "INSERT INTO produto (data_reg, hora_reg, ncm_prod, tipo_prod, nome_prod, "
                + "edicao_prod, cfop_prod, obs_prod, usu_prod, valor_prod_ex, sis_prod, "
                + "idunid, stprod, saldo_prod, estoque_prod, valor_prod) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

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
            pst.setInt(12, produto.getUn_prod());
            pst.setInt(13, produto.getStatus_prod());
            pst.setDouble(14, produto.getSaldo_prod());
            pst.setDouble(15, produto.getEstoque_prod());
            pst.setDouble(16, produto.getValor());

            pst.execute();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto.\nSalvar_Produto\n" + ex.getMessage());
            System.out.println("ex::" + ex.getMessage());
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
        conex.conexao();
        conex.executaSql2("SELECT id_prod, data_reg, hora_reg, ncm_prod, tipo_prod,"
                + " nome_prod,  edicao_prod, cfop_prod, obs_prod, usu_prod, valor_prod,"
                + " sis_prod, id_prod,  idunid, stprod, saldo_prod, estoque_prod, "
                + "valor_prod_ex FROM produto WHERE stprod = 1 ORDER BY data_reg DESC ");

        try {
            while (conex.rs.next()) {
                Produto produto = new Produto();
                produto.setId_prod(conex.rs.getInt("id_prod"));
                produto.setData_reg(conex.rs.getString("data_reg"));
                produto.setHora_reg(conex.rs.getString("hora_reg"));
                produto.setNcm_prod(conex.rs.getString("ncm_prod"));
                produto.setTipo_prod(conex.rs.getString("tipo_prod"));
                produto.setNome_prod(conex.rs.getString("nome_prod"));
                produto.setEdicao_prod(conex.rs.getString("edicao_prod"));
                produto.setCfop_prod(conex.rs.getString("cfop_prod"));
                produto.setObs_prod(conex.rs.getString("obs_prod"));
                produto.setUsu_prod(conex.rs.getString("usu_prod"));
                produto.setValor_ex(conex.rs.getString("valor_prod_ex"));
                produto.setSis_prod(conex.rs.getInt("sis_prod"));
                produto.setId_prod(conex.rs.getInt("id_prod"));
                produto.setUn_prod(conex.rs.getInt("idunid"));
                produto.setStatus_prod(conex.rs.getInt("stprod"));
                produto.setSaldo_prod(conex.rs.getDouble("saldo_prod"));
                produto.setEstoque_prod(conex.rs.getDouble("estoque_prod"));
                produto.setValor(conex.rs.getDouble("valor_prod"));

                produtos.add(produto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.desconecta();
        }
        return produtos;
    }

    public Produto obterProdutoPorId(int idProduto) {
        conex.conexao();
        try {
            PreparedStatement pstmt = conex.con.prepareStatement(
                    "SELECT id_produto, data_reg, hora_reg, ncm_prod, tipo_prod, nome_prod, "
                    + "edicao_prod, cfop_prod, obs_prod, usu_prod, valor_ex, sis_prod, id_prod, "
                    + "un_prod, status_prod, saldo_prod, estoque_prod, valor FROM produto "
                    + "WHERE id_produto = ? AND status_prod = 1");

            pstmt.setInt(1, idProduto);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Produto produto = new Produto();
                produto.setId_prod(conex.rs.getInt("id_produto"));
                produto.setData_reg(conex.rs.getString("data_reg"));
                produto.setHora_reg(conex.rs.getString("hora_reg"));
                produto.setNcm_prod(conex.rs.getString("ncm_prod"));
                produto.setTipo_prod(conex.rs.getString("tipo_prod"));
                produto.setNome_prod(conex.rs.getString("nome_prod"));
                produto.setEdicao_prod(conex.rs.getString("edicao_prod"));
                produto.setCfop_prod(conex.rs.getString("cfop_prod"));
                produto.setObs_prod(conex.rs.getString("obs_prod"));
                produto.setUsu_prod(conex.rs.getString("usu_prod"));
                produto.setValor_ex(conex.rs.getString("valor_ex"));
                produto.setSis_prod(conex.rs.getInt("sis_prod"));
                produto.setId_prod(conex.rs.getInt("id_prod"));
                produto.setUn_prod(conex.rs.getInt("un_prod"));
                produto.setStatus_prod(conex.rs.getInt("status_prod"));
                produto.setSaldo_prod(conex.rs.getDouble("saldo_prod"));
                produto.setEstoque_prod(conex.rs.getDouble("estoque_prod"));
                produto.setValor(conex.rs.getDouble("valor"));
                return produto;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conex.desconecta();
        }

        return null;
    }

    // ... outros métodos, se necessário
    // novo
    public List<Produto> listarProdutosPorBusca(String minhaBusca) {
        List<Produto> produtos = new ArrayList<>();
        conex.conexao();
        String query = "SELECT * FROM produto "
                + "INNER JOIN unidade ON idunid = id_referenciaunidade "
                + "WHERE (COALESCE(CAST(id_prod AS TEXT), '') || ' ' || "
                + "COALESCE(CAST(sis_prod AS TEXT), '') || ' ' || "
                + "COALESCE(UPPER(tipo_prod), '') || ' ' || "
                + "COALESCE(UPPER(nome_prod), '') || ' ' || "
                + "COALESCE(UPPER(edicao_prod), '') || ' ' || "
                + "COALESCE(UPPER(obs_prod), '') || ' ' || "
                + "COALESCE(UPPER(usu_prod), '') || ' ' || "
                + "COALESCE(CAST(data_reg AS TEXT), '')) ILIKE ? AND stprod = 1 "
                + "ORDER BY sis_prod DESC, id_prod DESC";

        try (PreparedStatement pst = conex.con.prepareStatement(query)) {
            pst.setString(1, "%" + minhaBusca + "%");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId_prod(rs.getInt("id_prod"));
                produto.setData_reg(rs.getString("data_reg"));
                produto.setHora_reg(rs.getString("hora_reg"));
                produto.setNcm_prod(rs.getString("ncm_prod"));
                produto.setTipo_prod(rs.getString("tipo_prod"));
                produto.setNome_prod(rs.getString("nome_prod"));
                produto.setEdicao_prod(rs.getString("edicao_prod"));
                produto.setCfop_prod(rs.getString("cfop_prod"));
                produto.setObs_prod(rs.getString("obs_prod"));
                produto.setUsu_prod(rs.getString("usu_prod"));
                produto.setValor_ex(rs.getString("valor_prod_ex"));
                produto.setSis_prod(rs.getInt("sis_prod"));
                produto.setId_prod(rs.getInt("id_prod"));
                produto.setUn_prod(rs.getInt("idunid"));
                produto.setStatus_prod(rs.getInt("stprod"));
                produto.setSaldo_prod(rs.getDouble("saldo_prod"));
                produto.setEstoque_prod(rs.getDouble("estoque_prod"));
                produto.setValor(rs.getDouble("valor_prod"));

                Unidade unidade = new Unidade();
                unidade.setId_unidade(rs.getInt("id_unidade"));
                unidade.setId_referencia(rs.getInt("id_referenciaunidade"));
                unidade.setSigla_unidade(rs.getString("sigla_unidade"));
                unidade.setDesc_unidade(rs.getString("desc_unidade"));
                produto.setUnidade(unidade);

                produtos.add(produto);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos.\n" + ex);
        } finally {
            conex.desconecta();
        }

        return produtos;
    }

    public int BuscaUltimaId() {
        int numeroUltimaId = 0;
        conex.conexao();
        conex.executaSql2("select * from produto order by sis_prod  desc ");
        try {
            conex.rs.first();
            numeroUltimaId = (conex.rs.getInt("sis_prod") + 1);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoCadastroJIF.class.getName()).log(Level.SEVERE, null, ex);
        }
        conex.desconecta();
        return numeroUltimaId;
    }
}
