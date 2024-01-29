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
import model.Cliente;
import model.ExpedicaoModel;
import model.NotaFiscal;
import model.Produto;
import model.Unidade;

/**
 *
 * @author edson
 */
public class ExpedicaoDao {

    ConexaoBD conex;

    public ExpedicaoDao() {
        conex = new ConexaoBD();
    }

    public List<ExpedicaoModel> listarExpedicoes() {

        conex.conexao();
        List<ExpedicaoModel> listaExpedicao = new ArrayList<>();

        String sql = "SELECT id_nota, nota_documento, nota_nota, nota_data,nota_situacao,ecft_nome,tipo_prod, nome_prod, edicao_prod,\n"
                + "sigla_unidade,nota_observacao,id_referencianota,qtd_mov, qtd_prod, qtd_prod_ex, \n"
                + "              qtd_calc, qtd_calc_ex,nota_hora ,complemento_mov,destino_mov,desc_unidade,datavariavel\n"
                + "FROM movprodutobase\n"
                + "inner join nota on nota_mov = id_nota\n"
                + " inner join produto on id_prod_ent = id_prod\n"
                + " inner join ecft as cl on ecft_id=fornecedorint\n"
                + " inner join unidade on id_unidade=idunid\n"
                + " inner join natureza on id_natureza=naturezaint\n"
                + "          where stnota=1 and stmovimento=1   and \n"
                + "    nota_operacao='SAIDA'  and  nota_situacao!='4-ENVIADO' and nota_situacao!='5-DEVOLVIDO' \n"
                + "order by datavariavel asc, id_referencianota asc";//datavariavel asc ,

        try (PreparedStatement stmt = conex.preparaSql(sql)) {
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                ExpedicaoModel expedicaoModel = new ExpedicaoModel();
                
                NotaFiscal notaFiscal = new NotaFiscal();
                notaFiscal.setId_nota(resultado.getInt("id_nota"));
                notaFiscal.setNota_data(resultado.getString("nota_data"));
                notaFiscal.setNota_situacao(resultado.getString("nota_situacao"));
                notaFiscal.setNota_documento(resultado.getString("nota_documento").toUpperCase());
                notaFiscal.setNota_nota(resultado.getString("nota_nota"));
                notaFiscal.setNota_observacao(resultado.getString("nota_observacao"));

                Cliente cliente = new Cliente();
                cliente.setCliente_nome(resultado.getString("ecft_nome"));

                notaFiscal.setCliente(cliente);

                Produto produto = new Produto();
                produto.setTipo_prod(resultado.getString("tipo_prod"));
                produto.setNome_prod(resultado.getString("nome_prod"));
                produto.setEdicao_prod(resultado.getString("edicao_prod"));

                Unidade unidade = new Unidade();
                unidade.setSigla_unidade(resultado.getString("sigla_unidade"));
                produto.setUnidade(unidade);

                expedicaoModel.setProduto(produto);

                expedicaoModel.setQtd_prod(resultado.getDouble("qtd_prod"));
                expedicaoModel.setComplemento_mov(resultado.getString("complemento_mov"));

                expedicaoModel.setNotaFiscal(notaFiscal);

                listaExpedicao.add(expedicaoModel);
            }
        } catch (SQLException e) {
            //  System.out.println("erro::"+e.getMessage());
            e.printStackTrace();
        } finally {
            conex.desconecta();
        }

        return listaExpedicao;
    }

}
