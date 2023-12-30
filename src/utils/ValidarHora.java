/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author edson
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidarHora {

//    private static final String FORMATO_HORA_24H = "HH:mm";
    public static boolean validarHora(String hora) {
        Pattern padrao24H = Pattern.compile("^([01]?[0-9]|2[0-3]):[0-5][0-9]$");
        Pattern padrao24H2 = Pattern.compile("^([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$");

        Matcher matcher24H = padrao24H.matcher(hora);
        Matcher matcher24H2 = padrao24H2.matcher(hora);

        return matcher24H.matches() || matcher24H2.matches();
    }

    public static String validarHoraRetorno(String hora) {
        if (validarHora(hora)) {
            return hora;
        } else {
            return "";
        }
    }

    public static void main(String[] args) {
        String hora1 = "12:34";
        String hora2 = "08:45:00";
        String hora3 = "24:00:00"; // Inválida

        if (validarHora(hora1)) {
            System.out.println(hora1 + " é uma hora válida.");
        } else {
            System.out.println(hora1 + " não é uma hora válida.");
        }

        if (validarHora(hora2)) {
            System.out.println(hora2 + " é uma hora válida.");
        } else {
            System.out.println(hora2 + " não é uma hora válida.");
        }

        if (validarHora(hora3)) {
            System.out.println(hora3 + " é uma hora válida.");
        } else {
            System.out.println(hora3 + " não é uma hora válida.");
        }
    }
}
