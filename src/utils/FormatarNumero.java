/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author edson
 */
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class FormatarNumero {

    public static String formatarNumero(double numero) {
        Locale locale = new Locale("pt", "BR");
        DecimalFormatSymbols simbolos = DecimalFormatSymbols.getInstance(locale);
        simbolos.setDecimalSeparator(',');
        simbolos.setGroupingSeparator('.');

        DecimalFormat formato = new DecimalFormat("#,##0.000", simbolos);

        return formato.format(numero);
    }

    public static void main(String[] args) {
        double numero = 34567890.08;
        String numeroFormatado = formatarNumero(numero);
        System.out.println(numeroFormatado);

    }

}
