/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UTIL;

/**
 *
 * @author edson
 */
import java.awt.Color;

public class ControleCores { //[204,204,255]

    private static Color corPadrao = new Color(204, 204, 255);

    private static Color corDestaque = Color.YELLOW;
//    }

    public static Color pegarCorPadrao() {
        return corPadrao;
    }

    public static Color pegarCorDestaque() {
        return corDestaque;
    }

}
