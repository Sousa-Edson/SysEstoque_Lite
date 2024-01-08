/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package extras;

/**
 *
 * @author edson
 */ 
    
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbrirLink {
    public static void abrirLink(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException ex) {
            Logger.getLogger(AbrirLink.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        abrirLink("http://www.google.com/");
    }
}

