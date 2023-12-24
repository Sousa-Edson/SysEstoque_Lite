/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author edson
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataHoraAtual {

    public static void main(String[] args) {
        String dataHoraFormatada = obterHoraFormatada();
        System.out.println("Data e Hora Atuais: " + dataHoraFormatada);
    }

    public static String obterDataHoraFormatada() {
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return agora.format(formatter);
    }

    public static String obterDataFormatada() {
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return agora.format(formatter);
    }

    public static String obterHoraFormatada() {
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return agora.format(formatter);
    }
}
