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

/**
 *
 * @author edson
 */
public class ConsultaInternaDao {
//    private final ConexaoBD conex;

    public ConsultaInternaDao() {
//        conex = new ConexaoBD();
    }

    public List<Object[]> getMovimentos(String operacao, String INI2, String FIM2, String busca) {
        String variaveis = "  ";

        if (operacao.length() > 1) {
            variaveis = variaveis + " AND nota_operacao = '" + operacao + "'";
        }

        if (INI2.length() > 1) {
            variaveis = variaveis + " AND datavariavel >= '" + INI2 + "'";
        }
        if (FIM2.length() > 1) {
            variaveis = variaveis + " AND datavariavel <= '" + FIM2 + "'";
        }
        if (busca.length() > 1) {
            variaveis = variaveis + " AND (\n"
                    + "    COALESCE(CONCAT(id_prod, ' ', tipo_prod, ' ', nome_prod, ' ', edicao_prod), '') ||\n"
                    + "    ' ' ||\n"
                    + "    COALESCE(ecft_nome, '') ||\n"
                    + "    ' ' ||\n"
                    + "    COALESCE(sigla_unidade, '') ||\n"
                    + "    ' ' ||\n"
                    + "    COALESCE(nota_observacao, '')\n"
                    + ") ILIKE '%" + busca + "%' ";
        }

        System.out.println(variaveis);
        ConexaoBD conex = new ConexaoBD();
        conex.conexao();
        List<Object[]> movimentos = new ArrayList<>();

        String query = "  SELECT id_nota, nota_data, nota_operacao,nota_situacao,concat(nota_documento,' ',nota_nota),ecft_nome,id_prod,\n"
                + "concat(  tipo_prod,' ', nome_prod,' ', edicao_prod), sigla_unidade, qtd_prod_ex,complemento_mov,\n"
                + "  nota_observacao\n"
                + "FROM movprodutobase \n"
                + "INNER JOIN produto ON id_prod_ent = id_prod \n"
                + "INNER JOIN nota ON id_nota = nota_mov \n"
                + "INNER JOIN ecft ON ecft_id = fornecedorint \n"
                + "INNER JOIN unidade ON id_unidade = idunid "
                + "WHERE stmovimento = 1 AND stnota = 1 AND stunid = 1 AND stecft = 1 AND stprod = 1  " + variaveis
                //                + " AND nota_operacao ILIKE '%" + operacao + "%' " 
                //                + "AND (datavariavel >= '" + INI2 + "' AND datavariavel <= '" + FIM2 + "' "
                + " ORDER BY datavariavel DESC";

        try (PreparedStatement preparedStatement = conex.con.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int columnCount = resultSet.getMetaData().getColumnCount();
                Object[] row = new Object[columnCount];

                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = resultSet.getObject(i);
                }

                movimentos.add(row);
            }

        } catch (SQLException e) {
            System.out.println("erro::" + e.getMessage());
            e.printStackTrace();
        }

        return movimentos;
    }

//    public static void main(String[] args) {
//        System.out.println("dd"+getMovimentos("","","").toString());
//    }
}
