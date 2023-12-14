/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author edson
 */
import java.math.BigDecimal;
import java.text.NumberFormat;

public class ManipulaValor {

    public static void main(String[] args) {
        System.out.println(manipulaValor("R$ 34,96"));
    }

    public static String manipulaValor(String texto) {
        String meuValor = texto.replace("R$", "").replace(" ", "").replace(".", "").replace(",", ".");

        if (meuValor == null || meuValor.trim().isEmpty()) {
            System.out.println("manipulaValor: Valor vazio ou nulo");
            return null;
        }

        try {
            double valor = Double.parseDouble(meuValor);
            BigDecimal decimalValor = BigDecimal.valueOf(valor);
            NumberFormat numberFormat = NumberFormat.getInstance();
            numberFormat.setMinimumFractionDigits(4);
            numberFormat.setMaximumFractionDigits(4);
            String formatoValorProd = "R$ " + numberFormat.format(decimalValor);

            return formatoValorProd;
        } catch (NumberFormatException e) {
            System.err.println("Erro ao converter para Double: " + e.getMessage());
            return null;
        }
    }

}
