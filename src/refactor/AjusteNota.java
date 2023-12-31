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
public class AjusteNota {

    public static void ajusteNotaCliente() {
        ConexaoBD conex = new ConexaoBD();
        conex.conexao();
        try {
            PreparedStatement pstmt = conex.con.prepareStatement(
                    "UPDATE nota as n SET fornecedorint  = ( SELECT e.ecft_id FROM ecft as e where e.sis_ecft  = n.fornecedorint ORDER BY e.ecft_id  DESC LIMIT 1 );");

            pstmt.execute();
            System.out.println("ajusteNotaCliente OK");

        } catch (SQLException ex) {
            System.out.println("ajusteNotaCliente Erro::: " + ex.getMessage());

        } finally {
            conex.desconecta();
        }
    }

    public static void ajusteNotaNatureza() {
        ConexaoBD conex = new ConexaoBD();
        conex.conexao();
        try {
            PreparedStatement pstmt = conex.con.prepareStatement(
                    " UPDATE nota as n SET naturezaint  = ( SELECT e.id_natureza FROM natureza  as e where e.id_referencianatureza  = n.naturezaint ORDER BY e.id_natureza  DESC LIMIT 1 );");

            pstmt.execute();
            System.out.println("ajusteNotaNatureza OK");

        } catch (SQLException ex) {
            System.out.println("ajusteNotaNatureza Erro::: " + ex.getMessage());

        } finally {
            conex.desconecta();
        }
    }

    public static void main(String[] args) {
        ajusteNotaCliente();
    }
}
