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
public class AjusteProduto {

    public static void ajusteUnidadeProduto() {
        ConexaoBD conex = new ConexaoBD();
        conex.conexao();
        try {
            PreparedStatement pstmt = conex.con.prepareStatement(
                    " UPDATE produto SET idunid = ( SELECT id_unidade FROM unidade where unidade.id_referenciaunidade = idunid ORDER BY id_unidade DESC LIMIT 1 );");

            pstmt.execute();
            System.out.println("ajusteUnidadeProduto OK");

        } catch (SQLException ex) {
            System.out.println("ajusteUnidadeProduto Erro::: " + ex.getMessage());

        } finally {
            conex.desconecta();
        }
    }

    public static void ajusteUnidadeProdutoRolo() {
        ConexaoBD conex = new ConexaoBD();
        conex.conexao();
        try {
            PreparedStatement pstmt = conex.con.prepareStatement(
                    " UPDATE produto SET idunid = 1 where idunid =10");

            pstmt.execute();
            System.out.println("ajusteUnidadeProdutoRolo OK");

        } catch (SQLException ex) {
            System.out.println("ajusteUnidadeProdutoRolo Erro::: " + ex.getMessage());

        } finally {
            conex.desconecta();
        }
    }

    public static void main(String[] args) {
//        ajusteUnidadeProduto();
        
        ajusteUnidadeProdutoRolo();
    }
}
