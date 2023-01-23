/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import ConectaBanco.ConexaoBD;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author edson
 */
public class ClassManipulaMovimento {

    ConexaoBD conex = new ConexaoBD();
    String NotaMov, NotaReferencia;

    public void RecebeNota_Mov(String nm) {
        NotaMov = nm;

    }

    public void LimpaMovimentoTemp() {
        conex.conexao();
         try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement("TRUNCATE TABLE movproduto RESTART IDENTITY;");
            pst.execute();
            System.out.println("InsereMovimentoTemp - limpa tabela ok");
        } catch (SQLException ex) {
            System.out.println("InsereMovimentoTemp - limpa tabela erro" + ex);
        }
          conex.desconecta();
    }

    public void InsereMovimentoTemp() {
        conex.conexao();
        try {
            conex.executaSql2("SELECT id_nota, id_referencianota  FROM nota where id_nota = " + NotaMov + " ");
            conex.rs.first();
            NotaReferencia = conex.rs.getString("id_referencianota");
            System.out.println("InsereMovimentoTemp - seleciona tabela ok");
        } catch (SQLException ex) {
            System.out.println("InsereMovimentoTemp - seleciona tabela erro" + ex);
            NotaReferencia = "0";
        }
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement("TRUNCATE TABLE movproduto RESTART IDENTITY;");
            pst.execute();
            System.out.println("InsereMovimentoTemp - limpa tabela ok");
        } catch (SQLException ex) {
            System.out.println("InsereMovimentoTemp - limpa tabela erro" + ex);
        }
        if (NotaReferencia == "0") {
            System.out.println("InsereMovimentoTemp - insere tabela == 0 ");
        } else {
            try {
                java.sql.PreparedStatement pst = conex.con.prepareStatement(""
                        + "INSERT INTO movproduto(\n"
                        + "            id_prod_ent, data_mov, nota_mov, qtd_mov, qtd_prod, qtd_prod_ex, \n"
                        + "            qtd_calc, qtd_calc_ex, valor_real, valor_moeda, destino_mov, \n"
                        + "            complemento_mov, registro_mov, stmovimento, volume, usuario_mov, \n"
                        + "            modo_mov, total_mov, sistema_mov,idmovtemp,intmodotbl)\n"
                        + "     ( select id_prod_ent, data_mov, nota_mov, qtd_mov, qtd_prod, qtd_prod_ex, \n"
                        + "            qtd_calc, qtd_calc_ex, valor_real, valor_moeda, destino_mov, \n"
                        + "            complemento_mov, registro_mov, stmovimento, volume, usuario_mov, \n"
                        + "            modo_mov, total_mov, sistema_mov,id_mov ,'0'From movprodutobase\n"
                        + "            where nota_mov=" + NotaReferencia + " and stmovimento=1  )"
                        + "");
                pst.execute();
                System.out.println("InsereMovimentoTemp - insere tabela ok");
            } catch (SQLException ex) {
                System.out.println("InsereMovimentoTemp - insere tabela erro" + ex);
            }
        }
        conex.desconecta();
    }
}
