/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
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
    int SelecionaProdutoId = 0;
    Produto produto;

    public ProdutoListaController() {
        produtoService = new ProdutoService();
        produto = new Produto();
    }

    public void chamaCadastro(ProdutoListaJIF form) {
        produto = null;
        TelaInternaController.chamaCadastroProduto(produto);
        form.getBtnEditar().setEnabled(false);
    }

    public void chamaEditar(ProdutoListaJIF form) {
        TelaInternaController.chamaCadastroProduto(produto);
        form.getBtnEditar().setEnabled(false);
    }

    public void limparPesquisaProdutoLista(ProdutoListaJIF form) {
        ProdutoTableModel modelo = new ProdutoTableModel();
        modelo.setProdutos(new ArrayList<>());
        form.getTabela().setModel(modelo);

        form.getBtnEditar().setEnabled(false);
        form.getTxtBuscar().setText("");
        form.getTxtBuscar().requestFocus();
    }

    public void preencheTabela(ProdutoListaJIF form) {
        ProdutoTableModel modelo = new ProdutoTableModel();
        List<Produto> produtos = produtoService.listarProdutosPorBusca(form.getTxtBuscar().getText().toUpperCase());
        modelo.setProdutos(produtos);
        form.getTabela().setModel(modelo);
        form.getTabela().getColumnModel().getColumn(0).setPreferredWidth(60);
        form.getTabela().getColumnModel().getColumn(0).setResizable(true);

        form.getTabela().getColumnModel().getColumn(2).setMinWidth(460);
        form.getTabela().getColumnModel().getColumn(2).setResizable(true);

        // Configura o renderizador de células para alinhar a terceira coluna à direita
        DefaultTableCellRenderer direitaRenderer = new DefaultTableCellRenderer();
        direitaRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        form.getTabela().getColumnModel().getColumn(3).setCellRenderer(direitaRenderer);

        form.getTabela().getTableHeader().setReorderingAllowed(false);
//        getjTable_Produto.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        form.getTabela().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        form.getTabela().requestFocus();
    }

    public void cliqueTabela(ProdutoListaJIF form, java.awt.event.MouseEvent evt) {
        String SelecionaProdutoNome = "";
        if (evt.getButton() == evt.BUTTON1) {
            SelecionaProdutoNome = "" + form.getTabela().getValueAt(form.getTabela().getSelectedRow(), 2);

            form.getTxtBuscar().setText(SelecionaProdutoNome);
            if (evt.getClickCount() == 2) {
                SelecionaProdutoId = (Integer) form.getTabela().getValueAt(form.getTabela().getSelectedRow(), 0);
                form.getBtnEditar().setEnabled(true);
                System.out.println("SelecionaProdutoId::" + SelecionaProdutoId);
                produto = produtoService.obterProdutoPorId(SelecionaProdutoId);
                if (produto == null) {
                    preencheTabela(form);
                    form.getBtnEditar().setEnabled(false);
                }
            }
        }

        if (evt.getButton() == evt.BUTTON3) {
            if (SelecionaProdutoId != 0) {
                SelecionaProdutoNome = "" + form.getTabela().getValueAt(form.getTabela().getSelectedRow(), 2);
                SelecionaProdutoId = (Integer) form.getTabela().getValueAt(form.getTabela().getSelectedRow(), 0);

                Object[] options = {"Sim", "Não"};
                if (JOptionPane.showOptionDialog(null, " Deseja realmente fazer varredura de saldo do produto '" + SelecionaProdutoNome + "' ? ",
                        "Varredura #" + SelecionaProdutoId + " ?", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, options, options[1]) == 0) {

                    produtoService.executarSaldo(SelecionaProdutoId);
                }
            }
        }
    }

}
