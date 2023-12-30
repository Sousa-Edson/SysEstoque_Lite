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
import model.Item;
import model.Natureza;
import model.NotaFiscal;
import service.ItemService;

public class NotaFiscalDAO {

    private final ConexaoBD conex;

    public NotaFiscalDAO() {
        conex = new ConexaoBD();
    }

    public List<NotaFiscal> listarNotasPorBusca(String minhaBusca) {
        List<NotaFiscal> notas = new ArrayList<>();
        conex.conexao();
        String query = "SELECT * FROM nota "
                + "inner join ecft on fornecedorint = sis_ecft "
                + "inner join natureza on naturezaint = id_referencianatureza "
                + "WHERE (COALESCE(CAST(id_nota AS TEXT), '') || ' ' || "
                + "COALESCE(CAST(ecft_nome AS TEXT), '') || ' ' || "
                + "COALESCE(UPPER(nota_operacao), '') || ' ' || "
                + "COALESCE(UPPER(nota_nota), '') || ' ' || "
                + "COALESCE(UPPER(nota_observacao), '') || ' ' || "
                + "COALESCE(CAST(nota_registro AS TEXT), '')) ILIKE ?  AND stnota = 1 "
                + "ORDER BY   id_nota DESC";

        try (PreparedStatement pst = conex.con.prepareStatement(query)) {
            pst.setString(1, "%" + minhaBusca + "%");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

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

    public int carregaUltimaIdReferenciaNota() {
        conex.conexao();
        conex.executaSql2("SELECT  *  FROM nota  where id_referencianota is not null and id_referencianota !=0 ORDER BY  id_referencianota DESC  LIMIT 1;");
        try {
            conex.rs.first();
            System.out.println("id_referencianota:: " + conex.rs.getInt("id_referencianota"));
            return conex.rs.getInt("id_referencianota");
        } catch (SQLException ex) {
            System.err.println("erro::" + ex.getMessage());
            return 0;
        } finally {
            conex.desconecta();
        }
    }

    public void adicionarNotaFiscal(NotaFiscal notaFiscal) {
        conex.conexao();
        String sql = "INSERT INTO nota ( id_referencianota, stnota, empresaint, nota_documento, nota_data, "
                + "nota_nota, nota_hora, nota_observacao, nota_chave, nota_total, nota_registro, nota_usu, nota_operacao, "
                + "nota_situacao, datavariavel, naturezaint, fornecedorint, motoristaint,"
                + "motorista, placa, uf, quantidade, especie, numeracao, pesobruto, pesoliquido,"
                + "modalidade, transportadora) "
                + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,"
                + "?, ?, ?, ?, ?, ?, ?, ?,"
                + "?, ?)";

        try (PreparedStatement preparedStatement = conex.con.prepareStatement(sql)) {

            int id = carregaUltimaIdReferenciaNota() + 1;
            System.out.println("id:: " + id);

            notaFiscal.setId_referencia(id);
            System.out.println("notaFiscal.getId_referencia():: " + notaFiscal.getId_referencia());

            notaDados(preparedStatement, notaFiscal);

            preparedStatement.executeUpdate();

            // Salvar os itens associados à nota fiscal
            ItemService itemService = new ItemService();
            List<Item> itens = notaFiscal.getItens();
            for (Item item : itens) {
                itemService.salvarItem(item, notaFiscal.getId_referencia());
            }
            System.out.println("deu certo :: " + notaFiscal.getId_nota());
        } catch (SQLException e) {
            System.out.println("erro " + e.getMessage());
            e.printStackTrace(); // Trate a exceção apropriadamente no seu código real
        }
    }

    public PreparedStatement notaDados(PreparedStatement preparedStatement, NotaFiscal notaFiscal) throws SQLException {
        preparedStatement.setInt(1, notaFiscal.getId_referencia());
        preparedStatement.setInt(2, notaFiscal.getNota_status());
        preparedStatement.setInt(3, notaFiscal.getEmpresaint());
        preparedStatement.setString(4, notaFiscal.getNota_documento());
        preparedStatement.setString(5, notaFiscal.getNota_data());
        preparedStatement.setString(6, notaFiscal.getNota_nota());
        preparedStatement.setString(7, notaFiscal.getNota_hora());
        preparedStatement.setString(8, notaFiscal.getNota_observacao());
        preparedStatement.setString(9, notaFiscal.getNota_chave());
        preparedStatement.setString(10, notaFiscal.getNota_total());
        preparedStatement.setString(11, notaFiscal.getNota_registro());
        preparedStatement.setString(12, notaFiscal.getNota_usuario());
        preparedStatement.setString(13, notaFiscal.getNota_operacao());
        preparedStatement.setString(14, notaFiscal.getNota_situacao());
        preparedStatement.setString(15, notaFiscal.getDatavariavel());
        
        preparedStatement.setInt(16, notaFiscal.getNatureza().getId_referencia());
        preparedStatement.setInt(17, notaFiscal.getCliente().getSis_cliente());
        
        preparedStatement.setInt(18, notaFiscal.getTransporteModel().getMotoristaint());

        preparedStatement.setString(19, notaFiscal.getTransporteModel().getMotorista());
        preparedStatement.setString(20, notaFiscal.getTransporteModel().getPlaca());
        preparedStatement.setString(21, notaFiscal.getTransporteModel().getUf());
        preparedStatement.setString(22, notaFiscal.getTransporteModel().getQuantidade());
        preparedStatement.setString(23, notaFiscal.getTransporteModel().getEspecie());
        preparedStatement.setString(24, notaFiscal.getTransporteModel().getNumeracao());
        preparedStatement.setString(25, notaFiscal.getTransporteModel().getPesobruto());
        preparedStatement.setString(26, notaFiscal.getTransporteModel().getPesoliquido());

        preparedStatement.setString(27, notaFiscal.getTransporteModel().getModalidade());
        preparedStatement.setString(28, notaFiscal.getTransporteModel().getTransportadora());
        return preparedStatement;
    }

}
