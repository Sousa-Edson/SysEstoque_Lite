/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import ModeloBeans.Imagem_Beans;
//import ConectaBanco.ConexaoBD;

/**
 *
 * @author marcelosiedler
 */
public class Imagem_DAO {

    public Boolean inserir(Imagem_Beans exemplo, Imagem_Beans produto) {
        System.out.println("produto " + produto.getProduto());
        Boolean retorno = false;
        String sql = "INSERT INTO imagemproduto (imagem,produtoid) values (?,?)";

        PreparedStatement pst = ConexaoImagem.getPreparedStatement(sql);
        try {
            pst.setBytes(1, exemplo.getImagem());
            pst.setInt(2, produto.getProduto());
            pst.executeUpdate();
            retorno = true;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return retorno;

    }

    public Boolean alterar(Imagem_Beans exemplo, Imagem_Beans produto) {
        System.out.println("produto " + produto.getProduto());
        Boolean retorno = false;
        String sql = "UPDATE imagemproduto   SET  imagem=? WHERE id=?";

        PreparedStatement pst = ConexaoImagem.getPreparedStatement(sql);
        try {
            pst.setBytes(1, exemplo.getImagem());
            pst.setInt(2, produto.getProduto());
            pst.executeUpdate();
            retorno = true;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return retorno;

    }

    public Boolean deletar(Imagem_Beans produto) {
        System.out.println("produto " + produto.getId());
        Boolean retorno = false;
        String sql = "DELETE FROM imagemproduto WHERE  id=?";

        PreparedStatement pst = ConexaoImagem.getPreparedStatement(sql);
        try {
            System.out.println("ModeloDao.Imagem_DAO.deletar()    -- " +produto.getId());
            pst.setInt(1, produto.getId());
            pst.executeUpdate();
            retorno = true;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return retorno;

    }

    public Imagem_Beans buscar(Integer id) {
        Imagem_Beans retorno = null;
        String sql = "SELECT id,imagem from imagemproduto where id=?";
        PreparedStatement pst = ConexaoImagem.getPreparedStatement(sql);

        try {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                retorno = new Imagem_Beans();
                retorno.setId(rs.getInt("id"));
                retorno.setImagem(rs.getBytes("imagem"));

            }

        } catch (Exception e) {
            e.printStackTrace();
            retorno = null;
        }

        return retorno;

    }

}
