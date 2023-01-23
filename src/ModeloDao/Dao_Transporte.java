/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDao;

import ModeloBeans.Beans_Movimento;
import ModeloBeans.Beans_Nota;
import ModeloBeans.Beans_Transporte;
import ConectaBanco.ConexaoBD;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author edson
 */
public class Dao_Transporte {

    ConexaoBD conex = new ConexaoBD();
    Beans_Transporte beans = new Beans_Transporte();

    public void Salvar(Beans_Transporte beans) {
//        preencherUnidade(cadProd.getUnprod());
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(""
                    + "INSERT INTO transporte(\n"
                    + "             modalidade, transportadora, motorista, placa, \n"
                    + "            uf, quantidade, especie, numeracao, pesobruto, pesoliquido, online, \n"
                    + "            osnota)\n"
                    + "    VALUES ( ?, ?, ?, ?, \n"
                    + "            ?, ?, ?, ?, ?, ?, ?, \n"
                    + "            ?);"
                    + "");

            pst.setInt(1, beans.getOsnota());
            pst.setString(1, beans.getModalidade());
            pst.setString(2, beans.getTransportadora());
            pst.setString(3, beans.getMotorista());
            pst.setString(4, beans.getPlaca());
            pst.setString(5, beans.getUf());
            pst.setString(6, beans.getQuantidade());
            pst.setString(7, beans.getEspecie());
            pst.setString(8, beans.getNumeracao());
            pst.setString(9, beans.getPesobruto());
            pst.setString(10, beans.getPesoliquido());
            pst.setInt(11, beans.getOnline());
            pst.setInt(12, beans.getOsnota());

            pst.execute();
//            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso. ");
  System.out.println("Salvo transporte");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar . \n\n" + ex);
        }
        conex.desconecta();
    }

    public void Alterar(Beans_Transporte beans) {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" UPDATE transporte SET  online=? WHERE osnota=? ");

            pst.setInt(1, beans.getOnline());

            pst.setInt(2, beans.getOsnota());

            pst.execute();
//            JOptionPane.showMessageDialog(null, "Alterado");
            System.out.println("Alterado transporte");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Alterar\n" + ex);
        }
        conex.desconecta();
    }

    public void Excluir(Beans_Transporte beans) {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" UPDATE transporte SET  online=? WHERE osnota=? ");

            pst.setInt(1, beans.getOnline());

            pst.setInt(2, beans.getOsnota());

            pst.execute();
//            JOptionPane.showMessageDialog(null, "Excuido");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir . \\n" + ex);
        }
        conex.desconecta();
    }

}
