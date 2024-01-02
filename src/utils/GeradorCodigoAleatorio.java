/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author edson
 */
import java.security.SecureRandom;

public class GeradorCodigoAleatorio {
    private static final String CARACTERES_PERMITIDOS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789$#&@";

    public static String gerarCodigoAleatorio(int comprimento) {
        SecureRandom random = new SecureRandom();
        StringBuilder codigoAleatorio = new StringBuilder(comprimento);

        for (int i = 0; i < comprimento; i++) {
            int indice = random.nextInt(CARACTERES_PERMITIDOS.length());
            char caractere = CARACTERES_PERMITIDOS.charAt(indice);
            codigoAleatorio.append(caractere);
        }

        return codigoAleatorio.toString();
    }

    public static void main(String[] args) {
        // Exemplo de uso
        String codigoAleatorio = gerarCodigoAleatorio(4);
        System.out.println("Código Aleatório: " + codigoAleatorio);
    }
}

