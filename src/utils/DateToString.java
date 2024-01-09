/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Asp
 */
public class DateToString {
     public static String deDateParaString(Date d) {
      
        // Formato desejado
        SimpleDateFormat desiredFormat = new SimpleDateFormat("yyyy/MM/dd");

        // Formate a data como uma string conforme desejado
        String formattedDate = desiredFormat.format(d);

        System.out.println("Data formatada: " + formattedDate);
        return formattedDate;
}
}
