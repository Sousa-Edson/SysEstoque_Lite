/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UTIL;

/**
 *
 * @author edson
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataHoraAtual {

    public static void main(String[] args) {
        String dataHoraFormatada = obterDataHoraFormatada();
        System.out.println("Data e Hora Atuais: " + dataHoraFormatada);
    }

    public static String obterDataHoraFormatada() {
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataHoraFormatada = agora.format(formatter);
        return dataHoraFormatada;
    }
}
