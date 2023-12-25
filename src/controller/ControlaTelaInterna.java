/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
 
import model.Produto;
import view.internal.ProdutoListaJIF;
import view.MenuPrincipal;
import view.internal.MovimentoListaJIF;
import view.internal.ProdutoCadastroJIF;
import view.internal.UnidadeCadastroJIF;

/**
 *
 * @author edson
 */
public class ControlaTelaInterna {

    private static UnidadeCadastroJIF unidadeCadastroJIF;
    private static ProdutoListaJIF produtoListaJIF;
    private static ProdutoCadastroJIF produtoCadastroJIF;
    private static MovimentoListaJIF movimentoListaJIF;

    public static void ChamaCadastroUnidade() {
        if (unidadeCadastroJIF == null || !unidadeCadastroJIF.isVisible()) {
            unidadeCadastroJIF = new UnidadeCadastroJIF();
            MenuPrincipal.desktopPrincipal.add(unidadeCadastroJIF);
            unidadeCadastroJIF.setVisible(true);
        }
        unidadeCadastroJIF.toFront();
        unidadeCadastroJIF.setPosicao();
    }

    public static void ChamaListaProduto() {
        if (produtoListaJIF == null || !produtoListaJIF.isVisible()) {
            produtoListaJIF = new ProdutoListaJIF();
            MenuPrincipal.desktopPrincipal.add(produtoListaJIF);
            produtoListaJIF.setVisible(true);
        }
        produtoListaJIF.toFront();
        produtoListaJIF.setPosicao();
        produtoListaJIF.getTxtBuscar().requestFocus();
    }

    public static void ChamaCadastroProduto(Produto produto) {
        if (produtoCadastroJIF == null || !produtoCadastroJIF.isVisible()) {
            produtoCadastroJIF = new ProdutoCadastroJIF(produto);
            MenuPrincipal.desktopPrincipal.add(produtoCadastroJIF);
            produtoCadastroJIF.setVisible(true);
        }
        produtoCadastroJIF.toFront();
        produtoCadastroJIF.setPosicao();

    }

    public static void ChamaListaNota() {
        if (movimentoListaJIF == null || !movimentoListaJIF.isVisible()) {
            movimentoListaJIF = new MovimentoListaJIF();
            MenuPrincipal.desktopPrincipal.add(movimentoListaJIF);
            movimentoListaJIF.setVisible(true);
        }
        movimentoListaJIF.toFront();
        movimentoListaJIF.setPosicao();
//        movimentoListaJIF.().requestFocus();
    }
}
