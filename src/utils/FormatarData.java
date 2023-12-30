/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author edson
 */
public class FormatarData {

    public static String formatarData(Date d) {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(d);
        } catch (Exception e) {
             return "";
        }
 
    }
}
