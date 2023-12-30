/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author edson
 */
public class CodigoGeradoSistema {
    public static String obterCodigoGerado() {
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSSSS");
        return agora.format(formatter);
    }

    public static void main(String[] args) {
        String dataHoraFormatada = obterCodigoGerado();
        System.out.println(dataHoraFormatada);
    }
}
