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
public class ConexaoBD_2 {

    public Statement stm;
    public ResultSet rs;
    public Connection con;
     
    ///// 192.168.0.1 "jdbc:mysql://localhost:3306/celke"; "org.mysql.Driver"
    private final String caminho = "jdbc:mysql://localhost:3306/celke";
//     private final String caminho = "jdbc:postgresql://192.168.0.3:5432/SysEstoqueX";
//    private final String caminho = "jdbc:postgresql://000.000.0.0:5432/SysEstoque";  /// aqui funciona em rede esmo mudando o ipconfig
    private final String driver = "org.mysql.Driver";
    private final String usuario = "root";
    private final String senha = ""; //positivo@75329867"; ESTA É A SENHA ;;;;;;    1

    public void conexao() {
        System.setProperty("jdbc.Drivers", driver);
        try {

            con = DriverManager.getConnection(caminho, usuario, senha);
//            JOptionPane.showMessageDialog(null, "Conectado!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao se conectar com BD\n" + ex.getMessage());
        }
    }

    public void executaSql(String sql) {
        try {
            stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao executar SQL\n( Favor verificar):\nConexaoBD\n " + ex.getMessage());
        }
    }

    public void executaSql2(String sql) {
        try {
            stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);

        } catch (SQLException ex) {
            ///   JOptionPane.showMessageDialog(null, "Erro ao executar SQL\n( Favor verificar):\n Verifique a conexao BD\n" + ex.getMessage());
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
