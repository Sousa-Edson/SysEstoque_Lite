/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author edson
 */
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Objects;

public class ManipulaValor {
    
    public static void main(String[] args) {
        System.out.println( ManipulaValor("R$ 34,988"));
    }

   

    public  static String  ManipulaValor(String texto) {
       
        String MeuValor = texto.replace("R$", "").replace(" ", "").replace(".", "").replace(",", ".");

        if (Objects.equals(MeuValor, null) || MeuValor.trim().isEmpty()) {
            System.out.println("Interface.ProdCadastroJIF.ManipulaValor()");
            return null;
        }

        try {
            double valor = Double.parseDouble(MeuValor);
 
                BigDecimal df1 = new BigDecimal(valor);
                NumberFormat nf1 = NumberFormat.getInstance();
                nf1.setMinimumFractionDigits(4);
                nf1.setMaximumFractionDigits(4);
                String FormatoValorProd = "R$ " + nf1.format(df1); 
              return FormatoValorProd;
        } catch (NumberFormatException e) { 
            System.err.println("Erro ao converter para Double: " + e.getMessage());
             return null;
        }
       
    }
}
