/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConectaBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author DNA
 */
public class ConexaoBD {

    public Statement stm;
    public ResultSet rs;
    public Connection con;

    private final String caminho = "jdbc:postgresql://localhost:5432/SysEstoqueVersao51";
    private final String driver = "org.postgresql.Driver";
    private final String usuario = "admin";
    private final String senha = "123456";

    public void conexao() {
        System.setProperty("jdbc.Drivers", driver);
        try {
            con = DriverManager.getConnection(caminho, usuario, senha);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao se conectar com BD\n" + ex.getMessage());
            System.out.println("Erro: " + ex);
        }
    }

    public void executaSql(String sql) {
        try {
            stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao executar SQL\n( Favor verificar):\nConexaoBD\n " + ex.getMessage() + "\n caminho " + caminho);
        }
    }

    public void executaSql2(String sql) {
        try {
            stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);

        } catch (SQLException ex) {
            System.out.println("Erro: executaSql2 ==  " + ex);
        }
    }

    public void desconecta() {
        System.setProperty("jdbc.Driver", driver);
        try {
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar conexão com BD:\n" + ex.getMessage());
        }
    }
}
