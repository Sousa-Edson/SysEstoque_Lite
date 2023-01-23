/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDao;

import ModeloBeans.Beans_Nota;
import ConectaBanco.ConexaoBD;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author edson
 */
// erro
public class Dao_Nota {

    ConexaoBD conex = new ConexaoBD();
    Beans_Nota beans = new Beans_Nota();

    public void Salvar(Beans_Nota beans) {
//        preencherUnidade(cadProd.getUnprod());
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" "
                    + "INSERT INTO nota(\n"
                    + "            id_referencianota, naturezaint, nota_operacao, nota_documento, nota_nota, \n"
                    + "            nota_data, nota_hora, nota_observacao, fornecedorint, nota_situacao, \n"
                    + "            nota_chave, nota_total, nota_registro, nota_usu, stnota,"
                    + " modalidade, transportadora, motorista, placa, uf, quantidade, \n"
                    + "       especie, numeracao, pesobruto, pesoliquido,motoristaint,empresaint,datavariavel)\n"
                    + "    VALUES (?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?);");
            pst.setInt(1, beans.getId_referencia());
            pst.setInt(2, beans.getNatureza());
            pst.setString(3, beans.getNota_operacao());
            pst.setString(4, beans.getNota_documento());
            pst.setString(5, beans.getNota_nota());
            pst.setString(6, beans.getNota_data());
            pst.setString(7, beans.getNota_hora());
            pst.setString(8, beans.getNota_observacao());
            pst.setInt(9, beans.getNota_fornecedor());
            pst.setString(10, beans.getNota_situacao());
            pst.setString(11, beans.getNota_chave());
            pst.setString(12, beans.getNota_total());
            pst.setString(13, beans.getNota_registro());
            pst.setString(14, beans.getNota_usuario());
            pst.setInt(15, beans.getNota_status());

            pst.setString(16, beans.getModalidade());
            pst.setString(17, beans.getTransportadora());
            pst.setString(18, beans.getMotorista());
            pst.setString(19, beans.getPlaca());
            pst.setString(20, beans.getUf());
            pst.setString(21, beans.getQuantidade());
            pst.setString(22, beans.getEspecie());
            pst.setString(23, beans.getNumeracao());
            pst.setString(24, beans.getPesobruto());
            pst.setString(25, beans.getPesoliquido());
            
             pst.setInt(26, beans.getMotoristaint());
              pst.setInt(27, beans.getEmpresaint());
               pst.setString(28, beans.getDatavariavel());

            pst.execute();
//            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso. ");
            System.out.println("ModeloDao.Dao_Nota.Salvar ----    Ok");
        } catch (SQLException ex) {
            System.out.println("ModeloDao.Dao_Nota.Salvar ----    Erro  " + ex);
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar . \nDaoNota\n" + ex);
        }
        conex.desconecta();
    }

    public void Alterar(Beans_Nota beans) {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" UPDATE nota"
                    + "   SET stnota=?"
                    + " WHERE id_nota=?;");

            pst.setInt(1, beans.getNota_status());

            pst.setInt(2, beans.getId_nota());

            pst.execute();
            JOptionPane.showMessageDialog(null, "Alterado");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Alterar\\nDaoNotan" + ex);
        }
        conex.desconecta();
    }

    public void Alterar2(Beans_Nota beans) {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" UPDATE nota SET stnota=?  WHERE id_referencianota=?;");

            pst.setInt(1, beans.getNota_status());

            pst.setInt(2, beans.getId_referencia());

            pst.execute();
            JOptionPane.showMessageDialog(null, "Alterado");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Alterar\n" + ex);
        }
        conex.desconecta();
    }

    public void Excluir(Beans_Nota beans) {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" UPDATE nota"
                    + "   SET stnota=?, nota_usu=?, nota_registro=?"
                    + " WHERE id_nota=?;");

            pst.setInt(1, beans.getNota_status());
            pst.setString(2, beans.getNota_usuario());
            pst.setString(3, beans.getNota_registro());

            pst.setInt(4, beans.getId_nota());

            pst.execute();
//            JOptionPane.showMessageDialog(null, "Excuido");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir . \\n" + ex);
        }
        conex.desconecta();
    }

}
