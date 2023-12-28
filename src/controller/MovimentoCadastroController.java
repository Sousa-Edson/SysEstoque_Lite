/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import view.dialog.JDialogBuscaProduto;
import ModeloBeans.Beans_Movimento;
import view.dialog.JDialogComplementar;
import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ListSelectionModel;
import model.Cliente;
import model.Movimento;
import model.Natureza;
import model.Produto;
import service.ClienteService;
import service.NaturezaService;
import service.ProdutoService;
import tableModel.BuscaProdutoTableModel;
import tableModel.MovimentoTableModel;
import utils.ControleCores;
import utils.DataHoraAtual;
import view.internal.MovimentoCadastroJIF;

/**
 *
 * @author edson
 */
public class MovimentoCadastroController {

    Color corPadrao;

    SimpleDateFormat formato;

    JDialogComplementar jDialogComplementar;
    JDialogBuscaProduto jDialogBuscaProduto;
 
    ProdutoService produtoService;

    public static List<Movimento> movimentos;

    Produto produto;

    public MovimentoCadastroController(MovimentoCadastroJIF form) {
        corPadrao = ControleCores.pegarCorPadrao();
        formato = new SimpleDateFormat("dd/MM/yyyy");

        jDialogBuscaProduto = new JDialogBuscaProduto(null, true);

        produtoService = new ProdutoService();

        movimentos = new ArrayList<>();

    }

    public void mudarCorPaineis(MovimentoCadastroJIF form) {
        form.getPnPrincipal().setBackground(corPadrao);
        form.getPnDados().setBackground(corPadrao);
        form.getPnTransporte().setBackground(corPadrao);
        form.getPnTopo().setBackground(corPadrao);
        form.getPnInformacao().setBackground(corPadrao);

//        form.getBtnExcluir().setVisible(false);
//        MovimentoCadastroJIF.getBtnExcluir().setVisible(false);
    }

    public void carregarNatureza(MovimentoCadastroJIF form) {
        NaturezaService naturezaService = new NaturezaService();
        List<Natureza> listaNaturezas = naturezaService.listarNaturezas();
        form.getCbNatureza().removeAllItems();
        for (Natureza natureza : listaNaturezas) {
            form.getCbNatureza().addItem(natureza);
        }
    }

    public void carregarCliente(MovimentoCadastroJIF form) {
        ClienteService clienteService = new ClienteService();
        List<Cliente> listaClientes = clienteService.listarClientes(false);
        form.getCbCliente().removeAllItems();
        for (Cliente cliente : listaClientes) {
            form.getCbCliente().addItem(cliente);
        }
    }

    public void dataAtual(MovimentoCadastroJIF form) {
        if (form.getDataNota().getDate() == (null)) {
            try {
                form.getDataNota().setDate(formato.parse(DataHoraAtual.obterDataFormatada()));
            } catch (ParseException ex) {
                System.err.println("erro::" + ex.getMessage());
            }
        } else {
            form.getDataNota().setDate(null);
        }
    }

    public void horaAtual(MovimentoCadastroJIF form) {
        if (form.getTxtHora().getText().trim().isEmpty()) {
            form.getTxtHora().setText(DataHoraAtual.obterHoraFormatada());
        } else {
            form.getTxtHora().setText(null);
        }
    }

    public void chamaFormularioComplementar(MovimentoCadastroJIF form) {
        if (form.getIdProdutoComun() != 0) {
            jDialogComplementar = new JDialogComplementar(null, true);
            produto = new Produto();
            produto = produtoService.obterProdutoPorId(form.getIdProdutoComun());
            jDialogComplementar.recebeProduto(produto);
            jDialogComplementar.setVisible(true);
        } else {
            System.err.println("id produto vazio!");
        }
        limparBuscarUmProdutoPorNome(form);
        prencherTabela(form);
    }

    public void chamaFormularioBuscaProduto(MovimentoCadastroJIF form) {
        jDialogBuscaProduto.getTxtBusca().setText(MovimentoCadastroJIF.getTxtBuscarUmProdutoPorNomeStatic().getText().toUpperCase());
        jDialogBuscaProduto.getBtnBuscar().doClick();
        jDialogBuscaProduto.setVisible(true);
    }

    public void limparBuscarUmProdutoPorNome(MovimentoCadastroJIF form) {
        form.getTxtBuscarUmProdutoPorNomeComun().setText("");
        form.getTxtBuscarUmProdutoPorNomeComun().requestFocus();
        form.setIdProdutoComun(0);
    }

    public void validarNomeProduto(MovimentoCadastroJIF form, java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == evt.VK_F12) {
//            Complementar.PreencherSetor();

            //            CD.RecebeDadosMovimentoAlterado(MinhaNatureza, jLabel_Complemento.getText(), jLabel_Setor.getText());
//            Complementar.VerificaModo(MinhaNatureza);
//            Complementar.RecebeDados(jLabel_Complemento.getText(), jLabel_Setor.getText(), jLabel_Calculado.getText());
//            jDialogComplementar.setVisible(true);
        }
        if (evt.getKeyCode() == evt.VK_F2) {
            chamaFormularioBuscaProduto(form);

        }
        if (evt.getKeyCode() == evt.VK_ENTER) {
//            EventoBuscaProduto();
            if (form.getIdProdutoComun() != 0) {
                form.getBtnInserirProduto().requestFocus();
            } else {
                chamaFormularioBuscaProduto(form);
            }
        }
    }

    public void prencherTabela(MovimentoCadastroJIF form) {
        System.out.println("movimentos" + movimentos);
        MovimentoTableModel modelo = new MovimentoTableModel(); 
        modelo.setMovimentos(movimentos);
        form.getTabela().setModel(modelo);
        form.getTabela().getColumnModel().getColumn(0).setPreferredWidth(60);
        form.getTabela().getColumnModel().getColumn(0).setResizable(true);
        form.getTabela().getTableHeader().setReorderingAllowed(false);
//        form.getTabela().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    public static void recebeProduto(Produto produto) {
        MovimentoCadastroJIF.getTxtBuscarUmProdutoPorNomeStatic().setText(produto.getTipo_prod() + " " + produto.getNome_prod() + " " + produto.getEdicao_prod() + "");
        MovimentoCadastroJIF.setIdProduto(produto.getId_prod());
    }
}
