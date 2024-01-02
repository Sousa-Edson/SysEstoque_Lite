/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package refactor;

import ConectaBanco.ConexaoBD;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author edson
 */
public class AjusteItem {

    public static void ajusteNotaItem() {
        ConexaoBD conex = new ConexaoBD();
        conex.conexao();
        try {
            PreparedStatement pstmt = conex.con.prepareStatement(
                    "UPDATE movprodutobase as m SET nota_mov  = ( SELECT nota.id_nota FROM nota where nota.id_referencianota  = m.nota_mov ORDER BY id_nota DESC LIMIT 1 );");

            pstmt.execute();
            System.out.println("ajusteNotaItem OK");

        } catch (SQLException ex) {
            System.out.println("ajusteNotaItem Erro::: " + ex.getMessage());

        } finally {
            conex.desconecta();
        }
    }

    public static void ajusteProdutoItem() {
        ConexaoBD conex = new ConexaoBD();
        conex.conexao();
        try {
            PreparedStatement pstmt = conex.con.prepareStatement(
                    " UPDATE movprodutobase as m SET id_prod_ent  = ( SELECT produto.id_prod FROM produto where produto.sis_prod  = m.id_prod_ent ORDER BY produto.id_prod DESC LIMIT 1 );");

            pstmt.execute();
            System.out.println("ajusteProdutoItem OK");

        } catch (SQLException ex) {
            System.out.println("ajusteProdutoItem Erro::: " + ex.getMessage());

        } finally {
            conex.desconecta();
        }
    }

    public static void main(String[] args) {
        ajusteNotaItem();
//        ajusteProdutoItem();
    }
}
