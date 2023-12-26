/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexao;

/**
 *
 * @author edson
 */
import dao.NaturezaDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import model.Natureza;
import static org.eclipse.persistence.jpa.jpql.Assert.fail;
 

public class NaturezaDAOTest {
 
    
     
    public static void main(String[] args) {
          NaturezaDAO naturezaDAO = new NaturezaDAO();

        // Chama o método para obter a lista de naturezas
        List<Natureza> naturezas = naturezaDAO.listarNaturezas();
        for (Natureza natureza : naturezas) {
            System.out.println(""+natureza.getDesc_natureza());
        }
       
    }
}

