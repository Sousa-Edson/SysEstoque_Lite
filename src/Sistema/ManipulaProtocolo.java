/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
////fcm
package Sistema;

import ConectaBanco.ConexaoBD;
import java.awt.Component;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author edson
 */
public class ManipulaProtocolo {

    ConexaoBD conex = new ConexaoBD();
    ConexaoBD conexBE = new ConexaoBD();
    ConexaoBD conex_MeuTotal = new ConexaoBD();
    String MinhaOs = null;
    String MinhaEmpresa = null;
    String MeuUsuario = null;
    String idreferenciaprotocolo = null;
    String MeuTransporte = null, tr_cnpj = null, tr_descricao = null, tr_telefone = null, tr_endereco = null, tr_no = null,
            tr_cep = null, tr_complemento = null, tr_bairro = null, tr_cidade = null, tr_inscricao = null;
    String ecft_descricao = null, ecft_endereco = null, ecft_no = null,
            ecft_complemento = null, ecft_bairro = null, ecft_cidade = null, ecft_telefone = null, MinhaObs = null,
            ecft_cnpj = null, ecft_inscricao = null;
    Double MeuValor = null;
    String FormatoReal, Chama_Sistema = null;
    String dataprotocolo = null, notaprotocolo = null, nota_observacao = null, ReferenciaNota = null;

    public void RecebeMeusDados(String texto, String texto2, String texto3, String texto4) {
        dataprotocolo = texto;
        notaprotocolo = texto2;
        notaprotocolo = notaprotocolo.replace("Nota", "").replace("Não definido", "Não definido");
        nota_observacao = texto3;
        ReferenciaNota = texto4;
        System.err.println("recebido      " + dataprotocolo + "/" + notaprotocolo + "/" + nota_observacao + "/" + ReferenciaNota);

        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement("TRUNCATE TABLE protocolo RESTART IDENTITY;");
            pst.execute();
            System.out.println("   OK");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " ERRO" + ex);
            System.out.println("  ERRO" + ex);
        }
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement("INSERT INTO protocolo(\n"
                    + "             protocolo_referencianota, obsprotocolo, dataprotocolo,  notaprotocolo)\n"
                    + "    VALUES ('" + ReferenciaNota + "', '" + nota_observacao + "', '" + dataprotocolo + "', '" + notaprotocolo + "');");
            pst.execute();
            System.out.println("   OK");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " ERRO" + ex);
            System.out.println("  ERRO" + ex);
        }
        conex.desconecta();
    }

    public void RecebeMInhaOs(String texto, String texto2, String texto3, String texto4) {
        MinhaOs = texto;
        MinhaEmpresa = texto2;
        MeuUsuario = texto3;
        MinhaObs = texto4;
        System.out.println(" --- RecebeMInhaOs " + texto);
    }

    public void RecebeMeuNumeroSitema(String texto) {
        Chama_Sistema = texto;
    }

    public void CorrigeVerifica_Principal() {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement("UPDATE palete\n"
                    + "   SET \n"
                    + "       verifica_principal=cast (nota_palete as integer)");
            pst.execute();
            System.out.println("UPDATE palete 2 CorrigeVerifica_Principal   OK");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "UPDATE palete 2 CorrigeVerifica_Principal  ERRO" + ex);
            System.out.println("UPDATE palete     2   CorrigeVerifica_Principal     ERRO" + ex);
        }
        conex.desconecta();
    }

    public void CorrigeVerifica_Principal_2() {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement("UPDATE palete\n"
                    + "   SET \n"
                    + "       verifica_principal=cast (nota_palete as integer)"
                    + "where nota_palete='" + MinhaOs + "'");
            pst.execute();
            System.out.println("UPDATE palete 2 CorrigeVerifica_Principal_2   OK          " + MinhaOs);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "UPDATE palete 2 CorrigeVerifica_Principal_2  ERRO" + ex);
            System.out.println("UPDATE palete     2   CorrigeVerifica_Principal_2     ERRO" + ex);
        }
        conex.desconecta();
    }

    public void CorrigeVerifica_Principal_3() {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement("UPDATE palete\n"
                    + "   SET \n"
                    + "       verifica_principal=cast (nota_palete as integer)"
                    + "where  verifica_principal is null ");
            pst.execute();
            System.out.println("UPDATE palete 2 CorrigeVerifica_Principal_3   OK          " + MinhaOs);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "UPDATE palete 2 CorrigeVerifica_Principal_3  ERRO" + ex);
            System.out.println("UPDATE palete     2   CorrigeVerifica_Principal_3     ERRO" + ex);
        }
        conex.desconecta();
    }

    public void ManipulaValorTotalNota() {
        //        int MinhaOs = MinhaOs;
        System.out.println("Sistema.ManipulaProtocolo.ManipulaValorTotalNota()   ===   " + MinhaOs);
        System.err.println("ManipulaValorTotalNota()                  INICIO ");
        conex_MeuTotal.conexao();
        conex_MeuTotal.executaSql("SELECT id_referencianota,   sum(qtd_prod*valor_real)as valor_total  FROM movprodutobase\n"
                + "inner join nota on id_referencianota=nota_mov where stmovimento=1 and stnota =1 "
                + "and modo_mov=1 and id_referencianota= '" + MinhaOs + "'\n"
                + "group by  nota.id_referencianota  order by id_referencianota desc");
        try {
            conex_MeuTotal.rs.first();
//            MeuTotal = (conex_MeuTotal.rs.getString("qtd_prod"));
            MeuValor = (conex_MeuTotal.rs.getDouble("valor_total"));
            System.err.println("PreencherMeuTotal()                  INICIO " + MeuValor);
            Double num4 = (MeuValor);
            BigDecimal df = new BigDecimal(num4);
            NumberFormat nf = NumberFormat.getCurrencyInstance();
            nf.setMinimumFractionDigits(4);
            FormatoReal = nf.format(df);
            FormatoReal = FormatoReal.replace(",0000", "").replace(",000", ",00");
//            JOptionPane.showMessageDialog(null, "FormatoReal /n" + FormatoReal);
            System.err.println("ManipulaValorTotalNota()                  CERTO                               " + FormatoReal);
            UpdateNotaTotal();
        } catch (SQLException ex) {
            System.err.println("ManipulaValorTotalNota()                  ERRO " + ex);
            JOptionPane.showMessageDialog(null, "Errrrrrrrrrrrrrrroooooooooo\n " + ex + "\n" + MeuValor + "\n" + MinhaOs);
//            MeuTotal = "0";
        }
        conex_MeuTotal.desconecta();
    }

    public void UpdateNotaTotal() {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement("UPDATE nota   SET  nota_total='" + FormatoReal + "' WHERE id_referencianota='" + MinhaOs + "'     ");
            pst.execute();
            System.out.println("UPDATE  nota nota_total   OK");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "UPDATE nota nota_total    ERRO" + ex);
            System.out.println("UPDATE nota nota_total             ERRO" + ex);
        }
        conex.desconecta();
    }

    public void BuscaEmpresa() {
        System.out.println("MinhaEmpresa --====== " + MinhaEmpresa);
        // MinhaEmpresa="17";
        System.err.println("BuscaEmpresa         BuscaEmpresa  ");
        conexBE.conexao();
        conexBE.executaSql2("SELECT ecft_id, sis_ecft, ecft_tipo, ecft_nome, ecft_cnpj, ecft_inscricao, \n"
                + "       ecft_descricao, ecft_telefone, ecft_endereco, ecft_no, ecft_cep, \n"
                + "       ecft_complemento, ecft_bairro, ecft_cidade, ecft_observacao, \n"
                + "       ecft_usuario, ecft_registro, stecft"
                + "  FROM ecft where ecft_tipo='EMPRESA' and ecft_id='" + MinhaEmpresa + "'");
        try {
            conexBE.rs.first();
            ecft_descricao = conexBE.rs.getString("ecft_descricao");
            ecft_endereco = conexBE.rs.getString("ecft_endereco");
            ecft_no = conexBE.rs.getString("ecft_no");
            ecft_complemento = conexBE.rs.getString("ecft_complemento");
            ecft_bairro = conexBE.rs.getString("ecft_bairro");
            ecft_cidade = conexBE.rs.getString("ecft_cidade");
            ecft_telefone = conexBE.rs.getString("ecft_telefone");
            ecft_cnpj = conexBE.rs.getString("ecft_cnpj");
            ecft_inscricao = conexBE.rs.getString("ecft_inscricao");
            System.err.println("BuscaEmpresa         OK  ");
        } catch (SQLException ex) {
            System.err.println("BuscaEmpresa          ERRO\n " + ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        conexBE.desconecta();
    }

    public void BuscaTransporte() {
        conex.conexao();
        conex.executaSql2("SELECT ecft_id, sis_ecft, ecft_tipo, ecft_nome, ecft_cnpj, ecft_inscricao, \n"
                + "       ecft_descricao, ecft_telefone, ecft_endereco, ecft_no, ecft_cep, \n"
                + "       ecft_complemento, ecft_bairro, ecft_cidade, ecft_observacao, \n"
                + "       ecft_usuario, ecft_registro, stecft"
                + "  FROM ecft where ecft_tipo='TRANSPORTE' and ecft_nome='" + MeuTransporte + "'");
        try {

            conex.rs.first();
            tr_descricao = conex.rs.getString("ecft_descricao");
            tr_endereco = conex.rs.getString("ecft_endereco");
            tr_no = conex.rs.getString("ecft_no");
            tr_complemento = conex.rs.getString("ecft_complemento");
            tr_bairro = conex.rs.getString("ecft_bairro");
            tr_cep = conex.rs.getString("ecft_cep");
            tr_cidade = conex.rs.getString("ecft_cidade");
            tr_telefone = conex.rs.getString("ecft_telefone");
            tr_cnpj = conex.rs.getString("ecft_cnpj");
            tr_inscricao = conex.rs.getString("ecft_inscricao");
            System.err.println("BuscaTransporte         OK  ");
        } catch (SQLException ex) {
            System.err.println("BuscaTransporte         ERRO\n " + ex);
            MeuTransporte = "Não definido";
            tr_cnpj = "";
            tr_descricao = "";
            tr_telefone = "";
            tr_endereco = "";
            tr_no = "";
            tr_cep = "";
            tr_complemento = "";
            tr_bairro = "";
            tr_cidade = "";
            tr_inscricao = "";
        }
        conex.desconecta();
    }

    public void VerificaProtocolo() {
        BuscaEmpresa();
        BuscaTransporte();
        ManipulaValorTotalNota();
//        conex.conexao();
//        conex.executaSql2("SELECT idreferenciaprotocolo,idprotocolo  FROM protocolotopo where idreferenciaprotocolo='" + MinhaOs + "' order by idprotocolo asc");
//
//        try {
//            conex.rs.first();
//            idreferenciaprotocolo = " " + conex.rs.getString("idreferenciaprotocolo");
//            UpdateProtocolo_2();
//            System.err.println("certo           ");
//        } catch (SQLException ex) {
//            System.err.println("erro           " + ex);
//            InsertProtocolo_2();
////            Logger.getLogger(UsuarioJIF.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        conex.desconecta();
    }

    public void UpdateProtocolo() {
        conex.conexao();
        try {

            java.sql.PreparedStatement pst = conex.con.prepareStatement(""
                    + "UPDATE protocolotopo\n"
                    + "   SET  empresaprotocolo= '" + ecft_descricao + "', enderecoprotocolo='" + ecft_endereco + "', noprotocolo='" + ecft_no + "', \n"
                    + "       complementoprotocolo='" + ecft_complemento + "', bairroprotocolo='" + ecft_bairro + "', cidadeprotocolo='" + ecft_cidade + "', \n"
                    + "       telefoneprotocolo='" + ecft_telefone + "', clienteprotocolo=ecft_nome,  \n"
                    + "       notausuprotocolo='" + MeuUsuario + "', obsprotocolo=nota_observacao, quantidadeprotocolo=quantidade, \n"
                    + "       especieprotocolo=especie,cnpjprotocolo= '" + ecft_cnpj + "',inscricaoprotocolo= '" + ecft_inscricao + "',"
                    + "tr_cnpj='" + tr_cnpj + "', tr_descricao='" + tr_descricao + "', tr_telefone='" + tr_telefone + "',"
                    + " tr_endereco='" + tr_endereco + "', tr_no='" + tr_no + "', tr_cep='" + tr_cep + "',"
                    + " tr_complemento='" + tr_complemento + "', tr_bairro='" + tr_bairro + "', tr_cidade='" + tr_cidade + "'"
                    + ", tr_inscricao='" + tr_inscricao + "'\n"
                    + "       From  nota \n"
                    + "inner join ecft on ecft_nome=nota_fornecedor "
                    + "inner join transporte on osnota = id_referencia "
                    + "where   nota_status like'%ATIVO%' and online=1 and id_referencia='" + MinhaOs + "' and idreferenciaprotocolo='" + MinhaOs + "'" //id_referencia
                    + "");//// nota_observacao   '" + MinhaObs + "'
            pst.execute();
            System.out.println("   UpdateProtocolo      OK");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "   UpdateProtocolo   ERRO" + ex);
        }
        conex.desconecta();
    }

    public void UpdateProtocolo_2() {
        System.out.println("-------------------------------------------- UpdateProtocolo_2()");
        conex.conexao();
        try {

            java.sql.PreparedStatement pst = conex.con.prepareStatement(""
                    + "UPDATE protocolotopo\n"
                    + "   SET  empresaprotocolo= '" + ecft_descricao + "', enderecoprotocolo='" + ecft_endereco + "', noprotocolo='" + ecft_no + "', \n"
                    + "       complementoprotocolo='" + ecft_complemento + "', bairroprotocolo='" + ecft_bairro + "', cidadeprotocolo='" + ecft_cidade + "', \n"
                    + "       telefoneprotocolo='" + ecft_telefone + "', clienteprotocolo=ecft_nome,  \n"
                    + "       notausuprotocolo='" + MeuUsuario + "', obsprotocolo=nota_observacao, quantidadeprotocolo=quantidade, \n"
                    + "       especieprotocolo=especie,cnpjprotocolo= '" + ecft_cnpj + "',inscricaoprotocolo= '" + ecft_inscricao + "',"
                    + "tr_cnpj='" + tr_cnpj + "', tr_descricao='" + tr_descricao + "', tr_telefone='" + tr_telefone + "',"
                    + " tr_endereco='" + tr_endereco + "', tr_no='" + tr_no + "', tr_cep='" + tr_cep + "',"
                    + " tr_complemento='" + tr_complemento + "', tr_bairro='" + tr_bairro + "', tr_cidade='" + tr_cidade + "'"
                    + ", tr_inscricao='" + tr_inscricao + "', "
                    + "dataprotocolo='" + dataprotocolo + "', notaprotocolo='" + notaprotocolo + "'\n"
                    + "       From  nota \n"
                    + "inner join ecft on ecft_nome=nota_fornecedor "
                    + "inner join transporte on osnota = id_referencia "
                    + "where   nota_status like'%ATIVO%' and online=1 and id_referencia='" + MinhaOs + "' and idreferenciaprotocolo='" + MinhaOs + "'" //id_referencia
                    + "");//// nota_observacao   '" + MinhaObs + "'
            pst.execute();
            System.out.println("   UpdateProtocolo      OK");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "   UpdateProtocolo   ERRO" + ex);
        }
        conex.desconecta();
    }

    public void InsertProtocolo() {
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement("INSERT INTO protocolotopo(\n"
                    + "             empresaprotocolo, enderecoprotocolo, noprotocolo, \n"
                    + "            complementoprotocolo, bairroprotocolo, cidadeprotocolo, telefoneprotocolo, \n"
                    + "            clienteprotocolo, idreferenciaprotocolo, notausuprotocolo, obsprotocolo, \n"
                    + "            quantidadeprotocolo, especieprotocolo, inscricaoprotocolo, cnpjprotocolo,"
                    + "vol_motorista, vol_placa, vol_uf, vol_quantidade, vol_especie,vol_numeracao, vol_peso_bruto, vol_peso_liquido, "
                    + "tr_cnpj, tr_descricao, \n"
                    + "       tr_telefone, tr_endereco, tr_no, tr_cep, tr_complemento, tr_bairro, \n"
                    + "       tr_cidade, tr_inscricao"
                    + ")\n"
                    + "     (select '" + ecft_descricao + "','" + ecft_endereco + "','" + ecft_no + "',\n"
                    + "     '" + ecft_complemento + "','" + ecft_bairro + "','" + ecft_cidade + "','" + ecft_telefone + "',\n"
                    + "     ecft_nome,id_referencia,'" + MeuUsuario + "',nota_observacao,\n" //// nota_observacao   '" + MinhaObs + "'
                    + "     quantidade,especie,'" + ecft_inscricao + "','" + ecft_cnpj + "',"
                    + "motorista, placa, uf, quantidade, especie, numeracao, pesobruto, pesoliquido,"
                    + "'" + tr_cnpj + "', '" + tr_descricao + "', '" + tr_telefone + "',"
                    + " '" + tr_endereco + "', '" + tr_no + "', '" + tr_cep + "',"
                    + " '" + tr_complemento + "', '" + tr_bairro + "', '" + tr_cidade + "'"
                    + ", '" + tr_inscricao + "'\n"
                    + "\n"
                    + "     \n"
                    + "From  nota \n"
                    + "inner join ecft on ecft_nome=nota_fornecedor "
                    + "inner join transporte on osnota = id_referencia\n"
                    + "where   nota_status like'%ATIVO%' and online=1 and id_referencia='" + MinhaOs + "' \n"
                    + "GROUP BY nota.nota_nota,nota.nota_documento,nota.nota_observacao,nota.id_nota,\n"
                    + " ecft.ecft_endereco,ecft.ecft_no,ecft.ecft_complemento,"
                    + "transporte.modalidade,transporte.transportadora,transporte.motorista,transporte.placa,\n"
                    + "transporte.uf,transporte.quantidade,transporte.especie,transporte.numeracao,transporte.pesobruto,transporte.pesoliquido,"
                    + "\n"
                    + " ecft.ecft_bairro,ecft.ecft_cidade,ecft.ecft_telefone,ecft.ecft_nome,transporte.motorista,transporte.quantidade,transporte.especie\n"
                    + "  order by id_referencia asc\n"
                    + " );"
            );
            pst.execute();
            System.out.println(" InsertProtocolo     OK");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "     InsertProtocolo      ERRO" + ex);
        }
        conex.desconecta();
    }

    public void InsertProtocolo_2() {
        System.out.println("-------aqui mudar pra verificar------------------------ Sistema.ManipulaProtocolo.InsertProtocolo_2()");
        conex.conexao();
        try {
            java.sql.PreparedStatement pst = conex.con.prepareStatement("INSERT INTO protocolotopo(\n"
                    + "             empresaprotocolo, enderecoprotocolo, noprotocolo, \n"
                    + "            complementoprotocolo, bairroprotocolo, cidadeprotocolo, telefoneprotocolo, \n"
                    + "            clienteprotocolo, idreferenciaprotocolo, notausuprotocolo, obsprotocolo, \n"
                    + "            quantidadeprotocolo, especieprotocolo, inscricaoprotocolo, cnpjprotocolo,"
                    + "vol_motorista, vol_placa, vol_uf, vol_quantidade, vol_especie,vol_numeracao, vol_peso_bruto, vol_peso_liquido, "
                    + "tr_cnpj, tr_descricao, \n"
                    + "       tr_telefone, tr_endereco, tr_no, tr_cep, tr_complemento, tr_bairro, \n"
                    + "       tr_cidade, tr_inscricao, dataprotocolo,notaprotocolo"
                    + ")\n"
                    + "     (select '" + ecft_descricao + "','" + ecft_endereco + "','" + ecft_no + "',\n"
                    + "     '" + ecft_complemento + "','" + ecft_bairro + "','" + ecft_cidade + "','" + ecft_telefone + "',\n"
                    + "     ecft_nome,id_referencianota,'" + MeuUsuario + "',nota_observacao,\n" //// nota_observacao   '" + MinhaObs + "'
                    + "     quantidade,especie,'" + ecft_inscricao + "','" + ecft_cnpj + "',"
                    + "motorista, placa, uf, quantidade, especie, numeracao, pesobruto, pesoliquido,"
                    + "'" + tr_cnpj + "', '" + tr_descricao + "', '" + tr_telefone + "',"
                    + " '" + tr_endereco + "', '" + tr_no + "', '" + tr_cep + "',"
                    + " '" + tr_complemento + "', '" + tr_bairro + "', '" + tr_cidade + "'"
                    + ", '" + tr_inscricao + "', '" + dataprotocolo + "', '" + notaprotocolo + "'\n"
                    + "\n"
                    + "     \n"
                    + "From  nota \n"
                    + "inner join ecft on sis_ecft=fornecedorint "
                    + "inner join transporte on osnota = id_referencianota\n"
                    + "where   stnota=1 and online=1 and id_referencianota='" + MinhaOs + "' \n"
                    + "GROUP BY nota.nota_nota,nota.nota_documento,nota.nota_observacao,nota.id_nota,\n"
                    + " ecft.ecft_endereco,ecft.ecft_no,ecft.ecft_complemento,"
                    + "transporte.modalidade,transporte.transportadora,transporte.motorista,transporte.placa,\n"
                    + "transporte.uf,transporte.quantidade,transporte.especie,transporte.numeracao,transporte.pesobruto,transporte.pesoliquido,"
                    + "\n"
                    + " ecft.ecft_bairro,ecft.ecft_cidade,ecft.ecft_telefone,ecft.ecft_nome,transporte.motorista,transporte.quantidade,transporte.especie\n"
                    + "  order by id_referencia asc\n"
                    + " );"
            );
            pst.execute();
            System.out.println(" InsertProtocolo  2   OK");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "     InsertProtocolo   2   ERRO" + ex);
        }
        conex.desconecta();
    }

//    public void chamaRelatorio() {
//        conex.conexao();
//        String numero = MinhaOs;
//        try {
//            conex.executaSql("select nota_mov,nota_operacao,nota_data,sis_prod,tipo_prod,nome_prod,guia,edicao_prod,SUM(qtd_mov) AS qtd_mov,\n"
//                    + "idreferenciaprotocolo, empresaprotocolo, enderecoprotocolo, noprotocolo, \n"
//                    + "       complementoprotocolo, bairroprotocolo, cidadeprotocolo, telefoneprotocolo, \n"
//                    + "       clienteprotocolo, idreferenciaprotocolo, notausuprotocolo, obsprotocolo, \n"
//                    + "       quantidadeprotocolo, especieprotocolo,\n"
//                    + "     un_prod,qtd_prod_ex,qtd_calc_ex,destino_mov,complemento_mov\n"
//                    + "From movprodutobase  Inner Join produto on id_prod_ent = sis_prod   inner join protocolotopo on idreferenciaprotocolo = nota_mov \n"
//                    + "inner join nota on id_referencia=nota_mov \n"
//                    + "where  status_mov like'%ATIVO%' and  status_prod like'%ATIVO%' and idreferenciaprotocolo='" + numero + "'\n"
//                    + "GROUP BY  produto.id_prod,nota.nota_data,nota.nota_operacao,\n"
//                    + "protocolotopo.idreferenciaprotocolo,protocolotopo.empresaprotocolo,protocolotopo.enderecoprotocolo,\n"
//                    + "protocolotopo.noprotocolo,protocolotopo.complementoprotocolo,protocolotopo.bairroprotocolo,protocolotopo.cidadeprotocolo,\n"
//                    + "protocolotopo.telefoneprotocolo,protocolotopo.clienteprotocolo,protocolotopo.notausuprotocolo,protocolotopo.obsprotocolo,\n"
//                    + "protocolotopo.quantidadeprotocolo,protocolotopo.especieprotocolo,protocolotopo.idprotocolo,\n"
//                    + " movprodutobase.qtd_prod_ex,movprodutobase.qtd_calc_ex ,movprodutobase.destino_mov,movprodutobase.nota_mov ,\n"
//                    + " movprodutobase.complemento_mov,movprodutobase.destino_mov order by idprotocolo desc");
//            JRResultSetDataSource relatResult = new JRResultSetDataSource(conex.rs); // C:/BKPBANCO/SysEstoque/MyReports/Visualizar_c-transporte_A4.jasper
//            JasperPrint jpPrint = JasperFillManager.fillReport("C:/BKPBANCO/SysEstoque_Versao_5.1/MyReports/Protocolo_A4_2_Modificado.jasper", new HashMap(), relatResult);
//            JasperViewer jv = new JasperViewer(jpPrint, false); /// C:\Users\edson\Documents\NetBeansProjects\SysEstoque_Versao_5.1
//            jv.toFront();
//            jv.setVisible(true);
//            jv.toFront();
//            System.out.println("Protocolo Tipo Preto");
//        } catch (Exception ex) {
//            Component parentComponent = null;
//            JOptionPane.showMessageDialog(parentComponent, MinhaOs);
//            JOptionPane.showMessageDialog(parentComponent, "\n" + ex);
//            System.out.println("Sistema.ManipulaProtocolo.chamaRelatorio()\n" + ex);
//        }
//        conex.desconecta();
//    }
    public void chamaRelatorio2() {
        conex.conexao();
        String numero = MinhaOs;
        try {
            conex.executaSql("select nota_mov,nota_operacao,nota_data,sis_prod,tipo_prod,nome_prod,guia,edicao_prod,SUM(qtd_mov) AS qtd_mov,\n"
                    + "idreferenciaprotocolo, empresaprotocolo, enderecoprotocolo, noprotocolo, \n"
                    + "       complementoprotocolo, bairroprotocolo, cidadeprotocolo, telefoneprotocolo, \n"
                    + "       clienteprotocolo, idreferenciaprotocolo, notausuprotocolo, obsprotocolo, \n"
                    + "       quantidadeprotocolo, especieprotocolo,\n"
                    + "     un_prod,qtd_prod_ex,qtd_calc_ex,destino_mov,complemento_mov\n"
                    + "From movprodutobase  Inner Join produto on id_prod_ent = sis_prod   inner join protocolotopo on idreferenciaprotocolo = nota_mov \n"
                    + "inner join nota on id_referencia=nota_mov \n"
                    + "where  status_mov like'%ATIVO%' and  status_prod like'%ATIVO%' and idreferenciaprotocolo='" + numero + "'\n"
                    + "GROUP BY  produto.id_prod,nota.nota_data,nota.nota_operacao,\n"
                    + "protocolotopo.idreferenciaprotocolo,protocolotopo.empresaprotocolo,protocolotopo.enderecoprotocolo,\n"
                    + "protocolotopo.noprotocolo,protocolotopo.complementoprotocolo,protocolotopo.bairroprotocolo,protocolotopo.cidadeprotocolo,\n"
                    + "protocolotopo.telefoneprotocolo,protocolotopo.clienteprotocolo,protocolotopo.notausuprotocolo,protocolotopo.obsprotocolo,\n"
                    + "protocolotopo.quantidadeprotocolo,protocolotopo.especieprotocolo,protocolotopo.idprotocolo,\n"
                    + " movprodutobase.qtd_prod_ex,movprodutobase.qtd_calc_ex ,movprodutobase.destino_mov,movprodutobase.nota_mov ,\n"
                    + " movprodutobase.complemento_mov,movprodutobase.destino_mov order by idprotocolo desc");
            JRResultSetDataSource relatResult = new JRResultSetDataSource(conex.rs); // C:/BKPBANCO/SysEstoque/MyReports/Visualizar_c-transporte_A4.jasper
            JasperPrint jpPrint = JasperFillManager.fillReport("C:/BKPBANCO/SysEstoque_Versao_5.1/MyReports/Protocolo_A4_2_Modificado_Branco.jasper", new HashMap(), relatResult);
            JasperViewer jv = new JasperViewer(jpPrint, false); /// C:\Users\edson\Documents\NetBeansProjects\SysEstoque_Versao_5.1
            jv.toFront();
            jv.setVisible(true);
            jv.toFront();
            System.out.println("Protocolo Tipo Branco");
        } catch (Exception ex) {
            Component parentComponent = null;
            JOptionPane.showMessageDialog(parentComponent, MinhaOs);
            JOptionPane.showMessageDialog(parentComponent, "\n" + ex);
            System.out.println("Sistema.ManipulaProtocolo.chamaRelatorio()\n" + ex);
        }
        conex.desconecta();
    }

    public void chamaRelatorio3() {
        System.out.println("VisualizarNota_com_transporte_A4       INICIADO");
        conex.conexao();
        String numero = MinhaOs;
        try {
            conex.executaSql("select nota_mov,nota_situacao,obsprotocolo,nota_operacao,natureza,nota_data,sis_prod,"
                    + "tipo_prod,nome_prod,guia,edicao_prod,SUM(qtd_mov) AS qtd_mov,\n"
                    + "idreferenciaprotocolo, empresaprotocolo, enderecoprotocolo, noprotocolo, \n"
                    + "       complementoprotocolo, bairroprotocolo, cidadeprotocolo, telefoneprotocolo, \n"
                    + "       clienteprotocolo, idreferenciaprotocolo, notausuprotocolo,  \n"
                    + "       quantidadeprotocolo, especieprotocolo,valor_moeda,total_mov,\n"
                    + "     un_prod,qtd_prod_ex,qtd_calc_ex,destino_mov,complemento_mov, modalidade, transportadora, motorista, placa, \n"
                    + "       uf, quantidade, especie, numeracao, pesobruto, pesoliquido, ecft_cnpj, ecft_inscricao, \n"
                    + "       ecft_descricao, ecft_telefone, ecft_endereco, ecft_no, ecft_cep, \n"
                    + "       ecft_complemento, ecft_bairro, ecft_cidade,nota_registro,inscricaoprotocolo, cnpjprotocolo,\n"
                    + "       nota_data ,  nota_hora , nota_total,uf,\n"
                    + "        tr_cnpj, tr_descricao,tr_telefone, tr_endereco, tr_no, tr_cep, tr_complemento, tr_bairro,tr_cidade, tr_inscricao,nota_chave,nota_nota,nota_documento\n"
                    + "From movprodutobase  \n"
                    + "Inner Join produto on id_prod_ent = sis_prod  \n"
                    + " inner join protocolotopo on idreferenciaprotocolo = nota_mov \n"
                    + "inner join nota on id_referencia=nota_mov \n"
                    + "inner join transporte on osnota=nota_mov \n"
                    + "inner join ecft on ecft_nome = clienteprotocolo\n"
                    + "where  status_mov like'%ATIVO%' and nota_status like'%ATIVO%' and status_prod like'%ATIVO%' and online=1\n"
                    + "and idreferenciaprotocolo='" + numero + "'\n"
                    + "GROUP BY ecft.ecft_cnpj,ecft.ecft_inscricao,ecft.ecft_descricao,ecft.ecft_telefone,ecft.ecft_endereco,\n"
                    + "ecft.ecft_no,ecft.ecft_cep,ecft.ecft_complemento,ecft.ecft_bairro,ecft.ecft_cidade,nota.nota_registro,\n"
                    + " produto.id_prod,nota.nota_data,nota.nota_operacao,nota.natureza,nota.nota_total,movprodutobase.valor_moeda,movprodutobase.total_mov,\n"
                    + "transporte.modalidade,transporte.transportadora,transporte.motorista,transporte.placa,\n"
                    + "transporte.uf,transporte.quantidade,transporte.especie,transporte.numeracao,transporte.pesobruto,transporte.pesoliquido,\n"
                    + "protocolotopo.idreferenciaprotocolo,protocolotopo.empresaprotocolo,protocolotopo.enderecoprotocolo,\n"
                    + "protocolotopo.noprotocolo,protocolotopo.complementoprotocolo,protocolotopo.bairroprotocolo,protocolotopo.cidadeprotocolo,\n"
                    + "protocolotopo.telefoneprotocolo,protocolotopo.clienteprotocolo,protocolotopo.notausuprotocolo,protocolotopo.obsprotocolo,\n"
                    + "protocolotopo.quantidadeprotocolo,protocolotopo.especieprotocolo,protocolotopo.idprotocolo,\n"
                    + " movprodutobase.qtd_prod_ex,movprodutobase.qtd_calc_ex ,movprodutobase.destino_mov,movprodutobase.nota_mov ,nota.nota_situacao,\n"
                    + " movprodutobase.complemento_mov,movprodutobase.destino_mov ,nota.nota_hora,nota.nota_chave,nota.nota_nota,nota.nota_documento\n"
                    + "  order by idprotocolo desc");
            JRResultSetDataSource relatResult = new JRResultSetDataSource(conex.rs); // C:/BKPBANCO/SysEstoque/MyReports/Visualizar_c-transporte_A4.jasper
            JasperPrint jpPrint = JasperFillManager.fillReport("C:/BKPBANCO/SysEstoque_Versao_5.1/MyReports/VisualizarNota_com_transporte_A4.jasper", new HashMap(), relatResult);
            JasperViewer jv = new JasperViewer(jpPrint, false); /// C:\Users\edson\Documents\NetBeansProjects\SysEstoque_Versao_5.1
            jv.toFront();
            jv.setVisible(true);
            jv.toFront();
            System.out.println("VisualizarNota_com_transporte_A4       OK");
        } catch (Exception ex) {
            Component parentComponent = null;
            JOptionPane.showMessageDialog(parentComponent, MinhaOs);
            JOptionPane.showMessageDialog(parentComponent, "\n" + ex);
            System.out.println("Sistema.ManipulaProtocolo.chamaRelatorio()\n" + ex);
        }
        conex.desconecta();
        System.out.println("VisualizarNota_com_transporte_A4       FINALIZADO");
    }

    public void chamaRelatorioEmBranco() {
        conex.conexao();

        try {
            conex.executaSql("SELECT id_nota, nota_documento, nota_nota, nota_data, nota_hora, nota_observacao, \n"
                    + "       nota_registro, nota_situacao, nota_chave, nota_total, nota_operacao, \n"
                    + "       nota_usu, id_referencianota, stnota, naturezaint, fornecedorint, \n"
                    + "       modalidade, transportadora, motorista, placa, uf, quantidade, \n"
                    + "       especie, numeracao, pesobruto, pesoliquido, motoristaint, empresaint, \n"
                    + "       datavariavel\n"
                    + "  FROM nota;");
            JRResultSetDataSource relatResult = new JRResultSetDataSource(conex.rs); // C:/BKPBANCO/SysEstoque/MyReports/Visualizar_c-transporte_A4.jasper
            JasperPrint jpPrint = JasperFillManager.fillReport("C:/BKPBANCO/SysEstoque_Lite/MyReports/EmBranco_A4.jasper", new HashMap(), relatResult);
            JasperViewer jv = new JasperViewer(jpPrint, false); /// C:\Users\edson\Documents\NetBeansProjects\SysEstoque_Versao_5.1
//            jv.toFront();
            jv.setVisible(false);
//            jv.toFront();
            System.out.println("Sistema.ManipulaProtocolo.chamaRelatorio()/n" + " Carregou o relatorio branco");
        } catch (Exception ex) {
            Component parentComponent = null;
            JOptionPane.showMessageDialog(parentComponent, MinhaOs);
            JOptionPane.showMessageDialog(parentComponent, ex);
            System.out.println("Sistema.ManipulaProtocolo.chamaRelatorio()" + ex);

        }
        conex.desconecta();

    }

    public void chamaRelatorio_Bandeira() {
        conex.conexao();
        String numero = Chama_Sistema;
        try {
            conex.executaSql("SELECT idlinha, pctpad, lastro, altura, pctavu, totpct, unavu, totparc,   mov_palete, qtd_palete, nota_palete, verifica_principal,\n"
                    + "clienteprotocolo,empresaprotocolo,tipo_prod,nome_prod,guia,edicao_prod,complemento_mov,destino_mov,obsprotocolo,\n"
                    + " nota_documento, nota_nota, nota_data, nota_hora,un_prod,dataprotocolo, notaprotocolo\n"
                    + "\n"
                    + "  FROM palete\n"
                    + "inner join protocolotopo on  idreferenciaprotocolo=verifica_principal\n"
                    + "inner join movprodutobase on  sistema_mov=mov_palete\n"
                    + "Inner Join produto on id_prod_ent = sis_prod  \n"
                    + "inner join nota on id_referencia=nota_mov \n"
                    + "   where mov_palete='" + numero + "' and status_mov like'%ATIVO%'  and status_palete like'%ATIVO%'  and nota_status like'%ATIVO%' \n"
                    + "order by id_palete asc");
            JRResultSetDataSource relatResult = new JRResultSetDataSource(conex.rs); // C:/BKPBANCO/SysEstoque/MyReports/Visualizar_c-transporte_A4.jasper
            JasperPrint jpPrint = JasperFillManager.fillReport("C:/BKPBANCO/SysEstoque_Versao_5.1/MyReports/MinhaBandeira_A4.jasper", new HashMap(), relatResult);
            JasperViewer jv = new JasperViewer(jpPrint, false); /// C:\Users\edson\Documents\NetBeansProjects\SysEstoque_Versao_5.1
            jv.toFront();
            jv.setVisible(true);
            jv.toFront();
            System.out.println("Protocolo Tipo Preto");
        } catch (Exception ex) {
            Component parentComponent = null;
            JOptionPane.showMessageDialog(parentComponent, MinhaOs);
            JOptionPane.showMessageDialog(parentComponent, "\n" + ex);
            System.out.println("Sistema.ManipulaProtocolo.chamaRelatorio()\n" + ex);
        }
        conex.desconecta();
    }

    public void chamaRelatorio_Bandeira_Pequeno() {
        conex.conexao();
        String numero = Chama_Sistema;
        try {
            conex.executaSql("SELECT  protocolo_referencianota, obsprotocolo, dataprotocolo,  notaprotocolo,nota_observacao,ecft_nome,idlinha, pctpad, lastro, altura, pctavu, totpct, unavu, totparc,   mov_palete, qtd_palete, nota_palete, verifica_principal,\n"
                    + "tipo_prod,nome_prod,edicao_prod,complemento_mov,destino_mov,\n"
                    + " nota_documento, nota_nota, nota_data, nota_hora,sigla_unidade\n"
                    + " FROM palete\n"
                    + "  inner join movprodutobase on  sistema_mov=mov_palete\n"
                    + " Inner Join produto on id_prod_ent = sis_prod \n"
                    + " inner join nota on id_referencianota=nota_mov \n"
                    + "  inner join unidade on idunid  = id_referenciaunidade\n"
                    + "  inner join protocolo on protocolo_referencianota=id_nota\n"
                    + "  inner join ecft on sis_ecft= empresaint\n"
                    + " where mov_palete='" + numero + "' and stmovimento=1  and stpalete=1  and stnota=1\n"
                    + " order by id_palete asc");
            JRResultSetDataSource relatResult = new JRResultSetDataSource(conex.rs); // C:/BKPBANCO/SysEstoque/MyReports/Visualizar_c-transporte_A4.jasper
            JasperPrint jpPrint = JasperFillManager.fillReport("C:/BKPBANCO/SysEstoque_Lite/MyReports/MinhaBandeira_A4_Pequeno.jasper", new HashMap(), relatResult);
            JasperViewer jv = new JasperViewer(jpPrint, false); /// C:\Users\edson\Documents\NetBeansProjects\SysEstoque_Versao_5.1
            jv.toFront();
            jv.setVisible(true);
            jv.toFront();
            System.out.println("Protocolo Tipo Preto");
        } catch (Exception ex) {
            Component parentComponent = null;
            JOptionPane.showMessageDialog(parentComponent, MinhaOs);
            JOptionPane.showMessageDialog(parentComponent, "\n" + ex);
            System.out.println("Sistema.ManipulaProtocolo.chamaRelatorio()\n" + ex);
        }
        conex.desconecta();
    }

    public void chamaRelatorio_Bandeira_Grande() {
        conex.conexao();
        String numero = Chama_Sistema;
        try {
            conex.executaSql("SELECT  protocolo_referencianota, obsprotocolo, dataprotocolo,  notaprotocolo,nota_observacao,ecft_nome,idlinha, pctpad, lastro, altura, pctavu, totpct, unavu, totparc,   mov_palete, qtd_palete, nota_palete, verifica_principal,\n"
                    + "tipo_prod,nome_prod,edicao_prod,complemento_mov,destino_mov,\n"
                    + " nota_documento, nota_nota, nota_data, nota_hora,sigla_unidade\n"
                    + " FROM palete\n"
                    + "  inner join movprodutobase on  sistema_mov=mov_palete\n"
                    + " Inner Join produto on id_prod_ent = sis_prod \n"
                    + " inner join nota on id_referencianota=nota_mov \n"
                    + "  inner join unidade on idunid  = id_referenciaunidade\n"
                    + "  inner join protocolo on protocolo_referencianota=id_nota\n"
                    + "  inner join ecft on sis_ecft= empresaint\n"
                    + " where mov_palete='" + numero + "' and stmovimento=1  and stpalete=1  and stnota=1\n"
                    + " order by id_palete asc");
            JRResultSetDataSource relatResult = new JRResultSetDataSource(conex.rs); // C:/BKPBANCO/SysEstoque/MyReports/Visualizar_c-transporte_A4.jasper
            JasperPrint jpPrint = JasperFillManager.fillReport("C:/BKPBANCO/SysEstoque_Lite/MyReports/MinhaBandeira_A4_Grande.jasper", new HashMap(), relatResult);
            JasperViewer jv = new JasperViewer(jpPrint, false); /// C:\Users\edson\Documents\NetBeansProjects\SysEstoque_Versao_5.1
            jv.toFront();
            jv.setVisible(true);
            jv.toFront();
            System.out.println("Protocolo Tipo Preto");
        } catch (Exception ex) {
            Component parentComponent = null;
            JOptionPane.showMessageDialog(parentComponent, MinhaOs);
            JOptionPane.showMessageDialog(parentComponent, "\n" + ex);
            System.out.println("Sistema.ManipulaProtocolo.chamaRelatorio()\n" + ex);
        }
        conex.desconecta();
    }

    public void chamaRelatorio_Bandeira_Medio() {
        conex.conexao();
        String numero = Chama_Sistema;
        try {
            conex.executaSql("SELECT  protocolo_referencianota, obsprotocolo, dataprotocolo,  notaprotocolo,nota_observacao,ecft_nome,idlinha, pctpad, lastro, altura, pctavu, totpct, unavu, totparc,   mov_palete, qtd_palete, nota_palete, verifica_principal,\n"
                    + "tipo_prod,nome_prod,edicao_prod,complemento_mov,destino_mov,\n"
                    + " nota_documento, nota_nota, nota_data, nota_hora,sigla_unidade\n"
                    + " FROM palete\n"
                    + "  inner join movprodutobase on  sistema_mov=mov_palete\n"
                    + " Inner Join produto on id_prod_ent = sis_prod \n"
                    + " inner join nota on id_referencianota=nota_mov \n"
                    + "  inner join unidade on idunid  = id_referenciaunidade\n"
                    + "  inner join protocolo on protocolo_referencianota=id_nota\n"
                    + "  inner join ecft on sis_ecft= empresaint\n"
                    + " where mov_palete='" + numero + "' and stmovimento=1  and stpalete=1  and stnota=1\n"
                    + " order by id_palete asc");
            JRResultSetDataSource relatResult = new JRResultSetDataSource(conex.rs); // C:/BKPBANCO/SysEstoque/MyReports/Visualizar_c-transporte_A4.jasper
            JasperPrint jpPrint = JasperFillManager.fillReport("C:/BKPBANCO/SysEstoque_Lite/MyReports/MinhaBandeira_A4_Medio.jasper", new HashMap(), relatResult);
            JasperViewer jv = new JasperViewer(jpPrint, false); /// C:\Users\edson\Documents\NetBeansProjects\SysEstoque_Versao_5.1
            jv.toFront();
            jv.setVisible(true);
            jv.toFront();
            System.out.println("chamaRelatorio_Bandeira_Medio");
        } catch (Exception ex) {
            Component parentComponent = null;
            JOptionPane.showMessageDialog(parentComponent, MinhaOs);
            JOptionPane.showMessageDialog(parentComponent, "\n" + ex);
            System.out.println("chamaRelatorio_Bandeira_Medio\n" + ex);
        }
        conex.desconecta();
    }

    public void chamaRelatorio() {
        MinhaOs = MinhaOs;
        System.out.println("Aqui ta chamando o relatorio com Os   " + MinhaOs);
        conex.conexao();
        String numero = MinhaOs;
        try {
            conex.executaSql("SELECT id_nota, nota_documento, nota_nota, nota_data, nota_hora, nota_observacao, \n"
                    + "       nota_registro, nota_situacao, nota_chave, nota_total, nota_operacao, \n"
                    + "       nota_usu, id_referencianota, stnota, naturezaint, fornecedorint, \n"
                    + "       modalidade, transportadora, motorista, placa, uf, quantidade, \n"
                    + "       especie, numeracao, pesobruto, pesoliquido,\n"
                    + "\n"
                    + "       id_mov, id_prod_ent, data_mov, nota_mov, qtd_mov, qtd_prod, qtd_prod_ex, \n"
                    + "       qtd_calc, qtd_calc_ex, valor_real, valor_moeda, destino_mov, \n"
                    + "       complemento_mov, registro_mov, volume, usuario_mov, modo_mov, \n"
                    + "       total_mov, sistema_mov, stmovimento, stsaldo,\n"
                    + "\n"
                    + "       id_prod, sis_prod, ncm_prod, tipo_prod, nome_prod, edicao_prod, \n"
                    + "       cfop_prod, saldo_prod, valor_prod, estoque_prod, obs_prod, usu_prod, \n"
                    + "       data_reg, hora_reg, valor_prod_ex, stprod, idunid,\n"
                    + "\n"
                    + "        cl.ecft_id, cl.sis_ecft, cl.ecft_tipo, cl.ecft_nome, cl.ecft_cnpj, cl.ecft_inscricao, \n"
                    + "       cl.ecft_descricao, cl.ecft_telefone, cl.ecft_endereco, cl.ecft_no, cl.ecft_cep, \n"
                    + "       cl.ecft_complemento, cl.ecft_bairro, cl.ecft_cidade, cl.ecft_observacao, \n"
                    + "       cl.ecft_usuario, cl.ecft_registro, cl.stecft,\n"
                    + "\n"
                    + "        em.ecft_id, em.sis_ecft, em.ecft_tipo, em.ecft_nome,em.ecft_cnpj as cnpj_empresa, em.ecft_inscricao as incricao_empresa, \n"
                    + "       em.ecft_descricao as descricao_empresa, em.ecft_telefone as telefone_empresa, em.ecft_endereco as endereco_empresa, em.ecft_no as no_empresa, em.ecft_cep as cep_empresa, \n"
                    + "       em.ecft_complemento as complemento_empresa , em.ecft_bairro as bairro_empresa, em.ecft_cidade as cidade_empresa, em.ecft_observacao, \n"
                    + "       em.ecft_usuario, em.ecft_registro, em.stecft,\n"
                    + "\n"
                    + "        tr.ecft_id, tr.sis_ecft, tr.ecft_tipo, tr.ecft_nome, tr.ecft_cnpj as cnpj_transporte, tr.ecft_inscricao as inscricao_transporte, \n"
                    + "       tr.ecft_descricao as descricao_transporte, tr.ecft_telefone as telefone_transporte, tr.ecft_endereco as endereco_transporte, tr.ecft_no as no_transporte, tr.ecft_cep as cep_transporte, \n"
                    + "       tr.ecft_complemento as complemento_transporte, tr.ecft_bairro as bairro_transporte, tr.ecft_cidade as cidade_transporte, tr.ecft_observacao, \n"
                    + "       tr.ecft_usuario, tr.ecft_registro, tr.stecft,\n"
                    + "\n"
                    + "       id_unidade, id_referenciaunidade, sigla_unidade, desc_unidade, registro_unidade, usuario_unidade, stunid,\n"
                    + "\n"
                    + "       id_natureza, desc_natureza, tipo_natureza, usuario_natureza, registro_natureza, id_referencianatureza, stnat\n"
                    + "       \n"
                    + "       \n"
                    + "  FROM nota     \n"
                    + "\n"
                    + "  inner join movprodutobase on nota_mov = id_referencianota\n"
                    + "  inner join produto on id_prod_ent = sis_prod\n"
                    + "  inner join ecft as cl on sis_ecft=fornecedorint \n"
                    + "                     inner join ecft  as em on em.sis_ecft=empresaint and em.ecft_tipo='EMPRESA'\n"
                    + "                      inner join ecft as tr on tr.sis_ecft= motoristaint and tr.ecft_tipo='TRANSPORTE'\n"
                    + "  inner join unidade on id_referenciaunidade=idunid\n"
                    + "  inner join natureza on id_referencianatureza=naturezaint\n"
                    + "\n"
                    + "  where stnota=1 and stmovimento=1 and stprod =1 and cl.stecft =1 and stunid =1 and stnat =1 and id_referencianota='" + numero + "'   ");
            JRResultSetDataSource relatResult = new JRResultSetDataSource(conex.rs);
            JasperPrint jpPrint = JasperFillManager.fillReport("C:/BKPBANCO/SysEstoque_lite/MyReports/VisualizarNota_com_transporte_A4.jasper", new HashMap(), relatResult);
            JasperViewer jv = new JasperViewer(jpPrint, false); /// C:\Users\edson\Documents\NetBeansProjects\SysEstoque_Versao_5.1\MyReports
            jv.toFront();
            jv.setVisible(true);
            jv.toFront();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            System.out.println("Consulta.JIF_Relatorio.chamaRelatorio()   " + ex);
        }
        conex.desconecta();
        System.out.println("Consulta.JIF_Relatorio.chamaRelatorio()   " + "Concluida  00000000000");

    }

    public void chamaProtocoloPreto() {
        MinhaOs = MinhaOs;
        System.out.println("Aqui ta chamando o chamaProtocoloPreto com Os   " + MinhaOs);
        conex.conexao();
        String numero = MinhaOs;
        try {
            conex.executaSql("SELECT id_nota, nota_documento, nota_nota, nota_data, nota_hora, nota_observacao, \n"
                    + "       nota_registro, nota_situacao, nota_chave, nota_total, nota_operacao, \n"
                    + "       nota_usu, id_referencianota, stnota, naturezaint, fornecedorint, \n"
                    + "       modalidade, transportadora, motorista, placa, uf, quantidade, \n"
                    + "       especie, numeracao, pesobruto, pesoliquido,\n"
                    + "\n"
                    + "       id_mov, id_prod_ent, data_mov, nota_mov, qtd_mov, qtd_prod, qtd_prod_ex, \n"
                    + "       qtd_calc, qtd_calc_ex, valor_real, valor_moeda, destino_mov, \n"
                    + "       complemento_mov, registro_mov, volume, usuario_mov, modo_mov, \n"
                    + "       total_mov, sistema_mov, stmovimento, stsaldo,\n"
                    + "\n"
                    + "       id_prod, sis_prod, ncm_prod, tipo_prod, nome_prod, edicao_prod, \n"
                    + "       cfop_prod, saldo_prod, valor_prod, estoque_prod, obs_prod, usu_prod, \n"
                    + "       data_reg, hora_reg, valor_prod_ex, stprod, idunid,\n"
                    + "\n"
                    + "        cl.ecft_id, cl.sis_ecft, cl.ecft_tipo, cl.ecft_nome, cl.ecft_cnpj, cl.ecft_inscricao, \n"
                    + "       cl.ecft_descricao, cl.ecft_telefone, cl.ecft_endereco, cl.ecft_no, cl.ecft_cep, \n"
                    + "       cl.ecft_complemento, cl.ecft_bairro, cl.ecft_cidade, cl.ecft_observacao, \n"
                    + "       cl.ecft_usuario, cl.ecft_registro, cl.stecft,\n"
                    + "\n"
                    + "        em.ecft_id, em.sis_ecft, em.ecft_tipo, em.ecft_nome,em.ecft_cnpj as cnpj_empresa, em.ecft_inscricao as incricao_empresa, \n"
                    + "       em.ecft_descricao as descricao_empresa, em.ecft_telefone as telefone_empresa, em.ecft_endereco as endereco_empresa, em.ecft_no as no_empresa, em.ecft_cep as cep_empresa, \n"
                    + "       em.ecft_complemento as complemento_empresa , em.ecft_bairro as bairro_empresa, em.ecft_cidade as cidade_empresa, em.ecft_observacao, \n"
                    + "       em.ecft_usuario, em.ecft_registro, em.stecft,\n"
                    + "\n"
                    + "        tr.ecft_id, tr.sis_ecft, tr.ecft_tipo, tr.ecft_nome, tr.ecft_cnpj as cnpj_transporte, tr.ecft_inscricao as inscricao_transporte, \n"
                    + "       tr.ecft_descricao as descricao_transporte, tr.ecft_telefone as telefone_transporte, tr.ecft_endereco as endereco_transporte, tr.ecft_no as no_transporte, tr.ecft_cep as cep_transporte, \n"
                    + "       tr.ecft_complemento as complemento_transporte, tr.ecft_bairro as bairro_transporte, tr.ecft_cidade as cidade_transporte, tr.ecft_observacao, \n"
                    + "       tr.ecft_usuario, tr.ecft_registro, tr.stecft,\n"
                    + "\n"
                    + "       id_unidade, id_referenciaunidade, sigla_unidade, desc_unidade, registro_unidade, usuario_unidade, stunid,\n"
                    + "\n"
                    + "       id_natureza, desc_natureza, tipo_natureza, usuario_natureza, registro_natureza, id_referencianatureza, stnat\n"
                    + "       \n"
                    + "       \n"
                    + "  FROM nota     \n"
                    + "\n"
                    + "  inner join movprodutobase on nota_mov = id_referencianota\n"
                    + "  inner join produto on id_prod_ent = sis_prod\n"
                    + "  inner join ecft as cl on sis_ecft=fornecedorint\n"
                    + "   inner join ecft  as em on em.sis_ecft=empresaint and em.ecft_tipo='EMPRESA'\n"
                    + "    inner join ecft as tr on tr.sis_ecft= motoristaint and tr.ecft_tipo='TRANSPORTE'\n"
                    + "  inner join unidade on id_referenciaunidade=idunid\n"
                    + "  inner join natureza on id_referencianatureza=naturezaint\n"
                    + "\n"
                    + "  where stnota=1 and stmovimento=1 and stprod =1 and cl.stecft =1 and em.stecft =1 and tr.stecft =1 and stunid =1  and stnat =1 and id_referencianota='" + numero + "'");
            JRResultSetDataSource relatResult = new JRResultSetDataSource(conex.rs);
            JasperPrint jpPrint = JasperFillManager.fillReport("C:/BKPBANCO/SysEstoque_lite/MyReports/Protocolo_A4_2_Modificado.jasper", new HashMap(), relatResult);
            JasperViewer jv = new JasperViewer(jpPrint, false); /// C:\Users\edson\Documents\NetBeansProjects\SysEstoque_Versao_5.1\MyReports
            jv.toFront();
            jv.setVisible(true);
            jv.toFront();
            System.out.println("Consulta.JIF_Relatorio.chamaProtocoloPreto()   " + "Deu certo ");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            System.out.println("Consulta.JIF_Relatorio.chamaProtocoloPreto()   " + ex);
        }
        conex.desconecta();
        System.out.println("Consulta.JIF_Relatorio.chamaProtocoloPreto()   " + "Concluida  00000000000");

    }

    public void chamaProtocoloBranco() {
        MinhaOs = MinhaOs;
        System.out.println("Aqui ta chamando o chamaProtocoloBranco com Os   " + MinhaOs);
        conex.conexao();
        String numero = MinhaOs;
        try {
            conex.executaSql("SELECT id_nota, nota_documento, nota_nota, nota_data, nota_hora, nota_observacao, \n"
                    + "       nota_registro, nota_situacao, nota_chave, nota_total, nota_operacao, \n"
                    + "       nota_usu, id_referencianota, stnota, naturezaint, fornecedorint, \n"
                    + "       modalidade, transportadora, motorista, placa, uf, quantidade, \n"
                    + "       especie, numeracao, pesobruto, pesoliquido,\n"
                    + "\n"
                    + "       id_mov, id_prod_ent, data_mov, nota_mov, qtd_mov, qtd_prod, qtd_prod_ex, \n"
                    + "       qtd_calc, qtd_calc_ex, valor_real, valor_moeda, destino_mov, \n"
                    + "       complemento_mov, registro_mov, volume, usuario_mov, modo_mov, \n"
                    + "       total_mov, sistema_mov, stmovimento, stsaldo,\n"
                    + "\n"
                    + "       id_prod, sis_prod, ncm_prod, tipo_prod, nome_prod, edicao_prod, \n"
                    + "       cfop_prod, saldo_prod, valor_prod, estoque_prod, obs_prod, usu_prod, \n"
                    + "       data_reg, hora_reg, valor_prod_ex, stprod, idunid,\n"
                    + "\n"
                    + "        cl.ecft_id, cl.sis_ecft, cl.ecft_tipo, cl.ecft_nome, cl.ecft_cnpj, cl.ecft_inscricao, \n"
                    + "       cl.ecft_descricao, cl.ecft_telefone, cl.ecft_endereco, cl.ecft_no, cl.ecft_cep, \n"
                    + "       cl.ecft_complemento, cl.ecft_bairro, cl.ecft_cidade, cl.ecft_observacao, \n"
                    + "       cl.ecft_usuario, cl.ecft_registro, cl.stecft,\n"
                    + "\n"
                    + "        em.ecft_id, em.sis_ecft, em.ecft_tipo, em.ecft_nome,em.ecft_cnpj as cnpj_empresa, em.ecft_inscricao as incricao_empresa, \n"
                    + "       em.ecft_descricao as descricao_empresa, em.ecft_telefone as telefone_empresa, em.ecft_endereco as endereco_empresa, em.ecft_no as no_empresa, em.ecft_cep as cep_empresa, \n"
                    + "       em.ecft_complemento as complemento_empresa , em.ecft_bairro as bairro_empresa, em.ecft_cidade as cidade_empresa, em.ecft_observacao, \n"
                    + "       em.ecft_usuario, em.ecft_registro, em.stecft,\n"
                    + "\n"
                    + "        tr.ecft_id, tr.sis_ecft, tr.ecft_tipo, tr.ecft_nome, tr.ecft_cnpj as cnpj_transporte, tr.ecft_inscricao as inscricao_transporte, \n"
                    + "       tr.ecft_descricao as descricao_transporte, tr.ecft_telefone as telefone_transporte, tr.ecft_endereco as endereco_transporte, tr.ecft_no as no_transporte, tr.ecft_cep as cep_transporte, \n"
                    + "       tr.ecft_complemento as complemento_transporte, tr.ecft_bairro as bairro_transporte, tr.ecft_cidade as cidade_transporte, tr.ecft_observacao, \n"
                    + "       tr.ecft_usuario, tr.ecft_registro, tr.stecft,\n"
                    + "\n"
                    + "       id_unidade, id_referenciaunidade, sigla_unidade, desc_unidade, registro_unidade, usuario_unidade, stunid,\n"
                    + "\n"
                    + "       id_natureza, desc_natureza, tipo_natureza, usuario_natureza, registro_natureza, id_referencianatureza, stnat\n"
                    + "       \n"
                    + "       \n"
                    + "  FROM nota     \n"
                    + "\n"
                    + "  inner join movprodutobase on nota_mov = id_referencianota\n"
                    + "  inner join produto on id_prod_ent = sis_prod\n"
                    + "  inner join ecft as cl on sis_ecft=fornecedorint\n"
                    + "   inner join ecft  as em on em.sis_ecft=empresaint and em.ecft_tipo='EMPRESA'\n"
                    + "    inner join ecft as tr on tr.sis_ecft= motoristaint and tr.ecft_tipo='TRANSPORTE'\n"
                    + "  inner join unidade on id_referenciaunidade=idunid\n"
                    + "  inner join natureza on id_referencianatureza=naturezaint\n"
                    + "\n"
                    + "  where stnota=1 and stmovimento=1 and stprod =1 and cl.stecft =1 and em.stecft =1 and tr.stecft =1 and stunid =1  and stnat =1 and id_referencianota='" + numero + "'");
            JRResultSetDataSource relatResult = new JRResultSetDataSource(conex.rs);
            JasperPrint jpPrint = JasperFillManager.fillReport("C:/BKPBANCO/SysEstoque_lite/MyReports/Protocolo_A4_2_Modificado_Branco2.jasper", new HashMap(), relatResult);
            JasperViewer jv = new JasperViewer(jpPrint, false); /// C:\Users\edson\Documents\NetBeansProjects\SysEstoque_Versao_5.1\MyReports
            jv.toFront();
            jv.setVisible(true);
            jv.toFront();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            System.out.println("Consulta.JIF_Relatorio.chamaProtocoloBranco()   " + ex);
        }
        conex.desconecta();
        System.out.println("Consulta.JIF_Relatorio.chamaProtocoloBranco()   " + "Concluida  00000000000");

    }

}
