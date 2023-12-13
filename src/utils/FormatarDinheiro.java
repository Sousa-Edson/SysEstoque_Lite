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

public class FormatarDinheiro {

    public static String formatarDinheiro(double valor) {

        Locale locale = new Locale("pt", "BR");

        DecimalFormatSymbols simbolos = new DecimalFormatSymbols(locale);
        simbolos.setDecimalSeparator(',');
        simbolos.setGroupingSeparator('.');

        DecimalFormat formatoDecimal = new DecimalFormat("R$ #,##0.0000", simbolos);

        return formatoDecimal.format(valor);
    }

    public static void main(String[] args) {
        System.out.println(formatarDinheiro(0.003));
    }
}
