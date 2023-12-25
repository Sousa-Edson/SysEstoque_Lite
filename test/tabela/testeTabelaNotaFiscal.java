/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tabela;

import dao.NotaFiscalDAO;

/**
 *
 * @author edson
 */
public class testeTabelaNotaFiscal {

      

    public static void main(String[] args) { 

        NotaFiscalDAO d = new NotaFiscalDAO( );
        d.listarNotasPorBusca("86");
    }

}
