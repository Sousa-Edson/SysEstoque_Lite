/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author edson
 */
import ConectaBanco.ConexaoBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Cliente;
import model.Natureza;
import model.NotaFiscal;

public class NotaFiscalDAO {
    
    private final ConexaoBD conex;
    
    public NotaFiscalDAO() {
        conex = new ConexaoBD();
    }
    
    public List<NotaFiscal> listarNotasPorBusca(String minhaBusca) {
        List<NotaFiscal> notas = new ArrayList<>();
        conex.conexao();
        String query = "SELECT * FROM nota "
                + "inner join movprodutobase on nota_mov = id_referenciaNota "
                + "inner join ecft on fornecedorint = sis_ecft "
                + "inner join natureza on naturezaint = id_referencianatureza "
                + "WHERE (COALESCE(CAST(id_nota AS TEXT), '') || ' ' || "
                + "COALESCE(CAST(ecft_nome AS TEXT), '') || ' ' || "
                + "COALESCE(UPPER(nota_operacao), '') || ' ' || "
                + "COALESCE(UPPER(nota_nota), '') || ' ' || "
                + "COALESCE(UPPER(nota_observacao), '') || ' ' || "
                + "COALESCE(CAST(nota_registro AS TEXT), '')) ILIKE ?  "
                + "ORDER BY   id_nota DESC";
        
        try (PreparedStatement pst = conex.con.prepareStatement(query)) {
            pst.setString(1, "%" + minhaBusca + "%");
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {

                // Aqui você deve criar uma classe NotaFiscal e preenchê-la com os dados do resultado
                NotaFiscal nota = new NotaFiscal();
                nota.setId_nota(rs.getInt("id_nota"));
                
                nota.setNota_documento(rs.getString("nota_documento"));
                nota.setNota_nota(rs.getString("nota_nota"));
                nota.setNota_data(rs.getString("nota_data"));
                
                nota.setNota_observacao(rs.getString("nota_observacao"));
                nota.setNota_registro(rs.getString("nota_registro"));
                nota.setId_referencia(rs.getInt("id_referenciaNota"));
                
                Natureza natureza = new Natureza();
                natureza.setId_natureza(rs.getInt("id_natureza"));
                natureza.setId_referencia(rs.getInt("id_referencianatureza"));
                natureza.setDesc_natureza(rs.getString("desc_natureza"));
                natureza.setTipo_natureza(rs.getString("tipo_natureza"));
                nota.setNatureza(natureza);
                
                Cliente cliente = new Cliente();
                cliente.setCliente_nome(rs.getString("ecft_nome"));
                nota.setCliente(cliente);
                notas.add(nota);
            }
        } catch (SQLException ex) {
            System.err.println("Erro " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao listar notas.\n" + ex.getMessage());
        } finally {
            conex.desconecta();
        }
        
        return notas;
    }
}
