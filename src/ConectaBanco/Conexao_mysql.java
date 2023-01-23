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
 * @author Jorge Luis Boeira Bavaresco
 * jlbavaresco@gmail.com
 */
public class Conexao_mysql {
    public Statement stm;
    public ResultSet rs;
    public Connection conecta;
    
    /**
     * O atributo banco representa a url jdbc de conexão com o banco de dados
     * Para se trabalhar com outros bancos criados no postgresql 
     * muda-se somente o nome do banco
     */
    private static final String banco = 
            "jdbc:mysql://localhost:3306/celke";
    /**
     * O atributo driver representa a classe do Driver JDBC que será usada na 
     * conexão. Quando se utiliza outros bancos usa-se a classe apropriada a 
     * cada banco
     */
    private static final String driver = 
            "org.mysql.Driver";
    /**
     * Os atributos usuario e senha representam usuario e senha do 
     * SGBD a ser usado na conexão
     */
    private static final String usuario = "root";
    private static final String senha = "";  
   
    /**
     * O atributo con representa um objeto que 
     * contém a conexão com o banco de dados em si
     */
    public static Connection con = null;
    
    /**
     * Metodo que retorna uma conexão com o banco de dados
     * @return objeto java.sql.Connection
     */
    public static Connection getConexao(){
        // primeiro testo se o objeto con não foi inicializado
        if (con == null){
            try {
                // defino a classe do driver a ser usado
                Class.forName(driver);
                // criação da conexão com o BD
                con = 
                DriverManager.getConnection(
                        banco, usuario, senha);
            } catch (ClassNotFoundException ex) {
                System.out.println("Não encontrou o driver");
            } catch (SQLException ex) {
                System.out.println("Erro ao conectar: "+
                        ex.getMessage());
            }
        }
        return con;
    }
    /**
     * Método que recebe um comando SQL para ser executado
     * @param sql
     * @return um objeto java.sql.PreparedStatement
     */
    public static PreparedStatement getPreparedStatement(String sql){
        // testo se a conexão já foi criada
        if (con == null){
            // cria a conexão
            con = getConexao();
        }
        try {
            // retorna um objeto java.sql.PreparedStatement
            return con.prepareStatement(sql);
        } catch (SQLException e){
            System.out.println("Erro de sql: "+
                    e.getMessage());
        }
        return null;
    }
    
    
     public void conexao() {
        System.setProperty("jdbc.Drivers", driver);
        try {

            conecta = DriverManager.getConnection(banco, usuario, senha);
            JOptionPane.showMessageDialog(null, "Conectado!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao se conectar com BD\n" + ex.getMessage());
        }
    }

    public void executaSql(String sql) {
        try {
            stm = conecta.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao executar SQL\n( Favor verificar):\nConexaoBD\n " + ex.getMessage());
        }
    }
     public void desconecta() {
        System.setProperty("jdbc.Driver", driver);
        try {

            conecta.close();

        } catch (SQLException ex) {
               JOptionPane.showMessageDialog(null, "Erro ao fechar conexão com BD:\n" + ex.getMessage());
        }
       
    }

}

