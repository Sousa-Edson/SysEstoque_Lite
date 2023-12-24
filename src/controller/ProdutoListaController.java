/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
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
        produto =null;
        ControlaTelaInterna.ChamaCadastroProduto(produto);
        form.getBtnEditar().setEnabled(false); 
    }

    public void chamaEditar(ProdutoListaJIF form) {
        ControlaTelaInterna.ChamaCadastroProduto(produto);
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
        String SelecionaProdutoNome = "" + form.getTabela().getValueAt(form.getTabela().getSelectedRow(), 2);

        form.getTxtBuscar().setText(SelecionaProdutoNome);
        if (evt.getClickCount() == 2) {
            SelecionaProdutoId = (Integer) form.getTabela().getValueAt(form.getTabela().getSelectedRow(), 0);
            form.getBtnEditar().setEnabled(true);
            System.out.println("SelecionaProdutoId::" + SelecionaProdutoId);
            produto = produtoService.obterProdutoPorId(SelecionaProdutoId);
        }

//         if (evt.getButton() == MouseEvent.BUTTON3) {
//            SelecionaProdutoNome = "" + jTable_Produto.getValueAt(jTable_Produto.getSelectedRow(), 1);
//            SelecionaProdutoId = "" + jTable_Produto.getValueAt(jTable_Produto.getSelectedRow(), 0);
//            int resposta = 0;
//            resposta = JOptionPane.showConfirmDialog(rootPane, "Deseja realmente fazer varredura de saldo do produto '" + SelecionaProdutoNome + "' ? ");
//            if (resposta == JOptionPane.YES_OPTION) {
//                ExecutaSaldo();
//            }
////           
//        } else {
//            btnEditar.setEnabled(true);
//            String TipoUsuario = Principal.jLabelTipoUsuario.getText();
//            int Clique = 1;
//            String NomeProduto;
//            SelecionaProdutoReferencia = "" + jTable_Produto.getValueAt(jTable_Produto.getSelectedRow(), 0);
//            if (TipoUsuario == "Manutenção") {
//                if (jCheckBoxMenuItem_Exibe_Imagens.isSelected()) {
//                    NomeProduto = ("" + jTable_Produto.getValueAt(jTable_Produto.getSelectedRow(), 1));
//                    txtBuscar.setText(NomeProduto);
//                } else {
//                    NomeProduto = ("" + jTable_Produto.getValueAt(jTable_Produto.getSelectedRow(), 2));
//                    txtBuscar.setText(NomeProduto);
//                    SelecionaProduto = "" + jTable_Produto.getValueAt(jTable_Produto.getSelectedRow(), 8);
//                    System.out.println("seleciona produto " + SelecionaProduto);
//                }
//                if (evt.getClickCount() == 2) {
////                    Principal.jLabelCodigoTela2.setText(SelecionaProduto);
////                    Principal.jLabelCodigoTela.setText("CadastroProdutoEditar");
////                    Principal.jButton1.doClick();
////                    jTextFieldBusca.setText("");
//                }
//            } else {
//                NomeProduto = ("" + jTable_Produto.getValueAt(jTable_Produto.getSelectedRow(), 1));
//                txtBuscar.setText(NomeProduto);
//                SelecionaProduto = "" + jTable_Produto.getValueAt(jTable_Produto.getSelectedRow(), 7);
//                System.out.println("seleciona produto " + SelecionaProduto);
//                if (evt.getClickCount() == 2) {
//                    Principal.jLabelCodigoTela2.setText(SelecionaProduto);
//                    Principal.jLabelCodigoTela.setText("CadastroProdutoEditar");
//                    Principal.jButton1.doClick();
//                    jTextFieldBusca.setText("");
//
//                    imagem.jLabel_Nome_Produto.setText(NomeProduto);
//                    imagem.jLabel_Id_Produto.setText(SelecionaProdutoReferencia);
//                    imagem.preencherTabela();
////                    imagem.setVisible(true);
//                }
//            }
//        }
    }

}
