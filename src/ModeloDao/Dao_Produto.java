/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDao;

import ModeloBeans.Beans_Produto;
import ConectaBanco.ConexaoBD; 
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author edson
 */
public class Dao_Produto {
 
    ConexaoBD conex = new ConexaoBD();
    Beans_Produto cadProd = new Beans_Produto();

    public void Cria_Registro_Primario(Beans_Produto cadProd) {
//        preencherUnidade(cadProd.getUnprod());
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement("INSERT INTO produtoreferencia(rps)"
                    + "    VALUES (?);");

            pst.setInt(1, cadProd.getSis_prod());
            pst.execute();
//            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso. ");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto. \nCria_Registro_Primario\n" + ex);
        }
        conex.desconecta();
    }

    public void Alterar_Atualizar_Temporario(Beans_Produto cadProd) {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" UPDATE produto"
                    + "   SET stprod=?"
                    + " WHERE id_prod=?;");

            pst.setInt(1, cadProd.getStatus_prod());

            pst.setInt(2, cadProd.getId_prod());

            pst.execute();
//            JOptionPane.showMessageDialog(null, "Excuido");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir /  atualizar produto. \nSalvar_Temporario\n" + ex);
        }
    }

    public void Excluir_Atualizar_Temporario(Beans_Produto cadProd) {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" UPDATE produto"
                    + "   SET stprod=?, usu_prod=?, data_reg=?,hora_reg=?"
                    + " WHERE id_prod=?;");

            pst.setInt(1, cadProd.getStatus_prod());
            pst.setString(2, cadProd.getUsu_prod());
            pst.setString(3, cadProd.getData_reg());
            pst.setString(4, cadProd.getHora_reg());
            pst.setInt(5, cadProd.getId_prod());

            pst.execute();
//            JOptionPane.showMessageDialog(null, "Excuido");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir /  atualizar produto. \nSalvar_Temporario\n" + ex);
        }
        conex.desconecta();
    }

    public void Salvar_Temporario(Beans_Produto cadProd) {
//        preencherUnidade(cadProd.getUnprod());
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" INSERT INTO produto("
                    + "             data_reg, ncm_prod, idunid, tipo_prod, nome_prod, edicao_prod, "
                    + "            cfop_prod,estoque_prod, obs_prod, stprod, "
                    + "            usu_prod,sis_prod,valor_prod,hora_reg,valor_prod_ex ) "
                    + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            pst.setString(1, cadProd.getData_reg());
            pst.setString(2, cadProd.getNcm_prod());
            pst.setInt(3, cadProd.getUn_prod());
            pst.setString(4, cadProd.getTipo_prod());
            pst.setString(5, cadProd.getNome_prod());
            pst.setString(6, cadProd.getEdicao_prod());
            pst.setString(7, cadProd.getCfop_prod());
            pst.setDouble(8, cadProd.getEstoque_prod());
            pst.setString(9, cadProd.getObs_prod());
            pst.setInt(10, cadProd.getStatus_prod());
            pst.setString(11, cadProd.getUsu_prod());
            pst.setInt(12, cadProd.getSis_prod());
            pst.setDouble(13, cadProd.getValor());
            pst.setString(14, cadProd.getHora_reg());
             pst.setString(15, cadProd.getValor_ex());

            pst.execute();
//            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso. ");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto. \nSalvar_Temporario\n" + ex);
        }
        conex.desconecta();
    }

    public void AlteraSaldo(Beans_Produto cadProd) {
//        preencherUnidade(cadProd.getUnprod());
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" UPDATE produto"
                    + "   SET saldo_prod=?"
                    + " WHERE id_prod=?;");

            pst.setDouble(1, cadProd.getSaldo_prod());
            pst.setInt(2, cadProd.getId_prod());

            pst.execute();
//            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso. ");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao AlteraSaldo produto. \nAlteraSaldo\n" + ex);
        }
        conex.desconecta();
    }

}
