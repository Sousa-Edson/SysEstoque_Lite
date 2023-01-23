/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDao;

import ModeloBeans.Beans_Movimento;
import ConectaBanco.ConexaoBD;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author edson
 */
public class Dao_Movimento {

    ConexaoBD conex = new ConexaoBD();
    Beans_Movimento beans = new Beans_Movimento();

//    public void AlterarStatusSaldo1(Beans_Movimento beans) {
//        conex.conexao();/// aqui verficar depois
//        try {
//            java.sql.PreparedStatement pst = conex.con.prepareStatement(" UPDATE movprodutobase  SET stsaldo=1 WHERE sistema_mov=?;");
//
//            pst.setInt(1, beans.getStatus_mov());
//
//            pst.setString(2, beans.getSistema_mov());
//
//            pst.execute();
//            JOptionPane.showMessageDialog(null, "Alterado");
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Erro ao Alterar\n" + ex);
//        }
//        conex.desconecta();
//    }
    public void AlterarStatusSaldo_MovProdutoBase_Produto(Beans_Movimento beans) {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" UPDATE movprodutobase"
                    + "   SET stsaldo=2 WHERE id_prod_ent=?;");

            pst.setInt(1, beans.getId_prod_ent());

            pst.execute();
            JOptionPane.showMessageDialog(null, "Alterado\nAlterarStatusSaldo2");
            System.out.println("ModeloDao.Dao_Movimento.AlterarStatusSaldo2()");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Alterar\n" + ex);
        }
        conex.desconecta();
    }
    public void SalvarQuantidadeProduto_MovProdutoBase(Beans_Movimento beans) {
//        preencherUnidade(cadProd.getUnprod());
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" "
                    + "INSERT INTO movprodutobase(id_prod_ent,qtd_mov,qtd_prod, qtd_prod_ex, qtd_calc, qtd_calc_ex, stmovimento,stsaldo \n"
                    + "            )\n"
                    + "    VALUES ( ?, ?, ?, ?, ?, ?, ?,?);"
                    + "");
            pst.setInt(1, beans.getId_prod_ent());
            pst.setDouble(2, beans.getQtd_mov());
            pst.setDouble(3, beans.getQtd_prod());
            pst.setString(4, beans.getQtd_prod_ex());
            pst.setDouble(5, beans.getQtd_calc());
            pst.setString(6, beans.getQtd_calc_ex());
            pst.setInt(7, beans.getStatus_mov());
            pst.setInt(8, beans.getStsaldo());
            pst.execute();
//            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso.\nSalvarQuantidadeProduto ");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar \n SalvarQuantidadeProduto . \n\n" + ex);
        }
        conex.desconecta();
    }
//    public void Alterar(Beans_Movimento beans) {
//        conex.conexao();
//        try {
//            java.sql.PreparedStatement pst = conex.con.prepareStatement(" UPDATE movproduto"
//                    + "   SET stmovimento=?"
//                    + " WHERE sistema_mov=?;");
//
//            pst.setInt(1, beans.getStatus_mov());
//
//            pst.setInt(2, beans.getId_mov());
//
//            pst.execute();
//            JOptionPane.showMessageDialog(null, "Alterado");
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Erro ao Alterar\n" + ex);
//        }
//        conex.desconecta();
//    }
//    public void Alterar2(Beans_Movimento beans) {
//        conex.conexao();
//        try {
//            java.sql.PreparedStatement pst = conex.con.prepareStatement(" UPDATE movproduto SET stmovimento=?  WHERE sistema_mov=?;");
//
//            pst.setInt(1, beans.getStatus_mov());
//
//            pst.setString(2, beans.getSistema_mov());
//
//            pst.execute();
//            JOptionPane.showMessageDialog(null, "Alterado");
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Erro ao Alterar\n" + ex);
//        }
//        conex.desconecta();
//    }
   

//    public void AlterarMinhaOS(Beans_Movimento beans) {
//        conex.conexao();
//        try {
//            java.sql.PreparedStatement pst = conex.con.prepareStatement(" UPDATE movprodutobase SET nota_mov=?  WHERE sistema_mov=?;");
//
//            pst.setInt(1, beans.getNota_mov());
//
//            pst.setString(2, beans.getSistema_mov());
//
//            pst.execute();
//            JOptionPane.showMessageDialog(null, "Alterado \nAlterarMinhaOS");
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Erro ao Alterar\nAlterarMinhaOS\n" + ex);
//        }
//        conex.desconecta();
//    }
//    public void AlterarCalculado(Beans_Movimento beans) {
//        conex.conexao();
//        try {
//            java.sql.PreparedStatement pst = conex.con.prepareStatement(" UPDATE movproduto SET "
//                    + "qtd_calc=? , qtd_calc_ex=?  WHERE sistema_mov=?;");
//            pst.setDouble(1, beans.getQtd_calc());
//            pst.setString(2, beans.getQtd_calc_ex());
//            pst.setString(3, beans.getSistema_mov());
//            pst.execute();
////            JOptionPane.showMessageDialog(null, "Alterado    AlterarCalculado" );
//            System.out.println("ModeloDao.Dao_Movimento.AlterarCalculado()\n.........................................Alterado");
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Erro ao Alterar\nAlterarCalculado\n" + ex);
//        }
//        conex.desconecta();
//    }
//    public void Excluir2(Beans_Movimento beans) {
//        conex.conexao();
//        try {
//            java.sql.PreparedStatement pst = conex.con.prepareStatement(" UPDATE movproduto"
//                    + "   SET  stmovimento=?,usuario_mov =?,registro_mov =?"
//                    + " WHERE id_mov=?;");
//
//            pst.setInt(1, beans.getStatus_mov());
//            pst.setString(2, beans.getUsuario_mov());
//            pst.setString(3, beans.getRegistro_mov());
//
//            pst.setInt(4, beans.getId_mov());
//
//            pst.execute();
////            JOptionPane.showMessageDialog(null, "Excuido");
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Erro ao Excluir . \\n" + ex);
//        }
//        conex.desconecta();
//    }
    public void ExcluirMovProdutoBase(Beans_Movimento beans) {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" UPDATE movprodutobase"
                    + "   SET  stmovimento=?,usuario_mov =?,registro_mov =?"
                    + " WHERE nota_mov=?;");

            pst.setInt(1, beans.getStatus_mov());
            pst.setString(2, beans.getUsuario_mov());
            pst.setString(3, beans.getRegistro_mov());

            pst.setInt(4, beans.getNota_mov());

            pst.execute();
//            JOptionPane.showMessageDialog(null, "Excuido");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir . \\n" + ex);
        }
        conex.desconecta();
    }

    public void SalvarMovProduto(Beans_Movimento beans) {
//        preencherUnidade(cadProd.getUnprod());
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" "
                    + "INSERT INTO movproduto(\n"
                    + "             id_prod_ent, data_mov, nota_mov, sistema_mov, qtd_mov, \n"
                    + "            qtd_prod, qtd_prod_ex, qtd_calc, qtd_calc_ex, valor_real, valor_moeda, \n"
                    + "            destino_mov, complemento_mov, registro_mov, stmovimento, volume, \n"
                    + "            usuario_mov, modo_mov,total_mov,intmodotbl)\n"
                    + "    VALUES ( ?, ?, ?, ?, ?, \n"
                    + "            ?, ?, ?, ?, ?, ?, \n"
                    + "            ?, ?, ?, ?, ?, \n"
                    + "            ?, ?,?,?);"
                    + "");

            pst.setInt(1, beans.getId_prod_ent());
            pst.setString(2, beans.getData_mov());
            pst.setInt(3, beans.getNota_mov());
            pst.setString(4, beans.getSistema_mov());
            pst.setDouble(5, beans.getQtd_mov());
            pst.setDouble(6, beans.getQtd_prod());
            pst.setString(7, beans.getQtd_prod_ex());
            pst.setDouble(8, beans.getQtd_calc());
            pst.setString(9, beans.getQtd_calc_ex());
            pst.setDouble(10, beans.getValor_real());
            pst.setString(11, beans.getValor_moeda());
            pst.setString(12, beans.getDestino_mov());
            pst.setString(13, beans.getComplemento_mov());
            pst.setString(14, beans.getRegistro_mov());
            pst.setInt(15, beans.getStatus_mov());
            pst.setString(16, beans.getVolume());
            pst.setString(17, beans.getUsuario_mov());
            pst.setInt(18, beans.getModo_mov());
            pst.setString(19, beans.getTotal_mov());
            pst.setInt(20, beans.getIntmodotbl());
            pst.execute();
//            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso. ");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar . \n\n" + ex);
        }
        conex.desconecta();
    }
     public void SalvarMovProdutoBase(Beans_Movimento beans) {
//        preencherUnidade(cadProd.getUnprod());
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" "
                    + "INSERT INTO movprodutobase(\n"
                    + "             id_prod_ent, data_mov, nota_mov, sistema_mov, qtd_mov, \n"
                    + "            qtd_prod, qtd_prod_ex, qtd_calc, qtd_calc_ex, valor_real, valor_moeda, \n"
                    + "            destino_mov, complemento_mov, registro_mov, stmovimento, volume, \n"
                    + "            usuario_mov, modo_mov,total_mov)\n"
                    + "    VALUES ( ?, ?, ?, ?, ?, \n"
                    + "            ?, ?, ?, ?, ?, ?, \n"
                    + "            ?, ?, ?, ?, ?, \n"
                    + "            ?, ?,?);"
                    + "");

            pst.setInt(1, beans.getId_prod_ent());
            pst.setString(2, beans.getData_mov());
            pst.setInt(3, beans.getNota_mov());
            pst.setString(4, beans.getSistema_mov());
            pst.setDouble(5, beans.getQtd_mov());
            pst.setDouble(6, beans.getQtd_prod());
            pst.setString(7, beans.getQtd_prod_ex());
            pst.setDouble(8, beans.getQtd_calc());
            pst.setString(9, beans.getQtd_calc_ex());
            pst.setDouble(10, beans.getValor_real());
            pst.setString(11, beans.getValor_moeda());
            pst.setString(12, beans.getDestino_mov());
            pst.setString(13, beans.getComplemento_mov());
            pst.setString(14, beans.getRegistro_mov());
            pst.setInt(15, beans.getStatus_mov());
            pst.setString(16, beans.getVolume());
            pst.setString(17, beans.getUsuario_mov());
            pst.setInt(18, beans.getModo_mov());
            pst.setString(19, beans.getTotal_mov());
//            pst.setInt(20, beans.getIntmodotbl());
            pst.execute();
//            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso. ");;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar . \n\n" + ex);
        }
        conex.desconecta();
    }

    public void ExcluirMovProduto(Beans_Movimento beans) {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" UPDATE movproduto"
                    + "   SET  stmovimento=?,usuario_mov =?,registro_mov =?"
                    + " WHERE id_mov=?;");

            pst.setInt(1, beans.getStatus_mov());
            pst.setString(2, beans.getUsuario_mov());
            pst.setString(3, beans.getRegistro_mov());

            pst.setInt(4, beans.getId_mov());

            pst.execute();
//            JOptionPane.showMessageDialog(null, "Excuido");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir . \\n" + ex);
        }
        conex.desconecta();
    }

    
     public void AlterarMovProduto(Beans_Movimento beans) {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" UPDATE movproduto SET stmovimento=? ,intmodotbl=? WHERE id_mov=?;");

            pst.setInt(1, beans.getStatus_mov());
            pst.setInt(2, beans.getIntmodotbl());
            pst.setInt(3, beans.getId_mov());

            pst.execute();
            JOptionPane.showMessageDialog(null, "Alterado");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Alterar\n" + ex);
        }
        conex.desconecta();
    }
       public void AlterarMovProdutoBase(Beans_Movimento beans) {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement(" UPDATE movprodutobase SET stmovimento=? WHERE id_mov=?;");

            pst.setInt(1, beans.getStatus_mov());
            pst.setInt(2, beans.getId_mov());

            pst.execute();
            JOptionPane.showMessageDialog(null, "Alterado");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Alterar\n" + ex);
        }
        conex.desconecta();
    }
}
