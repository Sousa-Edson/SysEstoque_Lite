/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import view.internal.ProdutoListaJIF;
import view.MenuPrincipal;
import view.internal.UnidadeCadastroJIF;

/**
 *
 * @author edson
 */
public class ControlaTelaInterna {

    private static UnidadeCadastroJIF unidadeCadastroJIF;
    private static ProdutoListaJIF produtoListaJIF;

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
}
