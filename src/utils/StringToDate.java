/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author edson
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDate {

    public static void main(String[] args) {
        System.out.println("" + deStringParaData("12/12/2023"));
    }

    public static Date deStringParaData(String d) {
       SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date data = formato.parse(d);
            return data;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
