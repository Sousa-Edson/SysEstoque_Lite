/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author edson
 */
public class LimiteCaracteres {

    public static void main(String[] args) {
        // Sua variável
        String minhaString = "Esta é uma string com mais de 300 caracteres. Vamos cortá-la para atender ao limite.";

        // Limitar a string a 300 caracteres
        minhaString = limitarString(minhaString, 300);

        // Exibir a string resultante
        System.out.println(minhaString);
    }

    public static String limitarString(String texto, int limite) {
        if (texto.length() <= limite) {
            // A string já está dentro do limite, não é necessário cortar
            return texto;
        } else {
            // Cortar a string para atender ao limite
            return texto.substring(0, limite);
        }
    }
}
