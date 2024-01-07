/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ListSelectionModel;
import model.Produto;
import service.ProdutoService;
import tableModel.BuscaProdutoTableModel;
import view.dialog.JDialogBuscaProduto;

/**
 *
 * @author edson
 */
public class BuscaProdutoController {
    
    private final ProdutoService produtoService;
    
    Produto produto;
    
    public BuscaProdutoController() {
        produtoService = new ProdutoService();
        produto = new Produto();
    }
    
    public void prencherTabela(JDialogBuscaProduto form) {
        
        BuscaProdutoTableModel modelo = new BuscaProdutoTableModel();
        List<Produto> produtos = produtoService.listarProdutosPorBusca(form.getTxtBusca().getText().toUpperCase());
        modelo.setProdutos(produtos);
        form.getTabela().setModel(modelo);
        form.getTabela().getColumnModel().getColumn(0).setPreferredWidth(60);
        form.getTabela().getColumnModel().getColumn(0).setResizable(true);
        form.getTabela().getTableHeader().setReorderingAllowed(false);
//        jTable_Lista.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        form.getTabela().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        form.getTxtBusca().requestFocus();
    }
    
    public void limparPesquisaProdutoLista(JDialogBuscaProduto form) {
        BuscaProdutoTableModel modelo = new BuscaProdutoTableModel();
        modelo.setProdutos(new ArrayList<>());
        form.getTabela().setModel(modelo);
        
        form.getTxtBusca().setText("");
        form.getTxtBusca().requestFocus();
        
        form.getTxtMeuTexto().setText("");
        form.getLblInformacao().setText("");
    }
    
    public void selecionaUmProduto(JDialogBuscaProduto form, java.awt.event.MouseEvent evt) {
        
        if (evt.getButton() == MouseEvent.BUTTON1) {
            produto = produtoService.obterProdutoPorId((int) form.getTabela().getValueAt(form.getTabela().getSelectedRow(), 0));
            form.getTxtMeuTexto().setText(produto.getObs_prod());
            form.getLblInformacao().setText("<html> "
                    + "&nbsp;&nbsp;<label>Cadastrado em:</label>" + produto.getData_reg() + " "
                    + "&nbsp;&nbsp;<label>Cadastrado por:</label>" + produto.getUsu_prod() + " "
                    + "</html>");
            if (evt.getClickCount() == 2) {
                try {
                    MovimentoCadastroController.recebeProduto(produto);
//                    System.out.println("produto:: " + produto);
                } catch (Exception e) {
                    System.out.println("erro|selecionaUmProduto::: " + e.getMessage());
                }
                limparPesquisaProdutoLista(form);
                form.dispose();
            }
        }
    }
}
