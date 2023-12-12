/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ListSelectionModel;
import model.Produto;
import service.ProdutoService;
import tableModel.ProdutoTableModel;
import view.internal.ProdutoListaJIF;

/**
 *
 * @author hp
 */
public class ProdutoListaController {

    private final ProdutoService produtoService;

    public ProdutoListaController() {
        produtoService = new ProdutoService();
    }

    public void limparPesquisaProdutoLista(ProdutoListaJIF form) {
        ProdutoTableModel modelo = new ProdutoTableModel();
        modelo.setProdutos(new ArrayList<>());
        form.getjTable_Produto().setModel(modelo);

        form.getBtnEditar().setEnabled(false);
        form.getTxtBuscar().setText("");
        form.getTxtBuscar().requestFocus();
    }

    public void preencheTabela(ProdutoListaJIF form) {
        ProdutoTableModel modelo = new ProdutoTableModel();
        List<Produto> produtos = produtoService.listarProdutos();
        modelo.setProdutos(produtos);
        form.getjTable_Produto().setModel(modelo);
        form.getjTable_Produto().getColumnModel().getColumn(0).setPreferredWidth(60);
        form.getjTable_Produto().getColumnModel().getColumn(0).setResizable(true);
        form.getjTable_Produto().getTableHeader().setReorderingAllowed(false);
//        getjTable_Produto.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        form.getjTable_Produto().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
}
