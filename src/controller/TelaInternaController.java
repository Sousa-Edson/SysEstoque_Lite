/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import extras.FramePositionUtil;
import extras.InternalFrameUtil;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import model.Produto;
import view.internal.ProdutoListaJIF;
import view.MenuPrincipal;
import view.internal.LoginJIF;
import view.internal.MovimentoCadastroJIF;
import view.internal.MovimentoListaJIF;
import view.internal.ProdutoCadastroJIF;
import view.internal.UnidadeCadastroJIF;

/**
 *
 * @author edson
 */
public class TelaInternaController {

    private static LoginJIF loginJIF;
    private static UnidadeCadastroJIF unidadeCadastroJIF;
    private static ProdutoListaJIF produtoListaJIF;
    private static ProdutoCadastroJIF produtoCadastroJIF;
    private static MovimentoListaJIF movimentoListaJIF;
    private static MovimentoCadastroJIF movimentoCadastroJIF;

    public static void chamaLogin(MenuPrincipal form) {
        if (loginJIF == null || !loginJIF.isVisible()) {
            loginJIF = new LoginJIF();
            MenuPrincipal.desktopPrincipal.add(loginJIF);
            InternalFrameUtil.removerIcone(loginJIF);
            loginJIF.setVisible(true);
        }
        loginJIF.toFront();
        FramePositionUtil.setCenteredPosition(loginJIF);
    }

    public static void chamaCadastroUnidade() {
        if (unidadeCadastroJIF == null || !unidadeCadastroJIF.isVisible()) {
            unidadeCadastroJIF = new UnidadeCadastroJIF();
            MenuPrincipal.desktopPrincipal.add(unidadeCadastroJIF);
            InternalFrameUtil.removerIcone(unidadeCadastroJIF);
            unidadeCadastroJIF.setVisible(true);
        }
        unidadeCadastroJIF.toFront();
        FramePositionUtil.setCenteredPosition(unidadeCadastroJIF);
    }

    public static void chamaListaProduto() {
        if (produtoListaJIF == null || !produtoListaJIF.isVisible()) {
            produtoListaJIF = new ProdutoListaJIF();
            MenuPrincipal.desktopPrincipal.add(produtoListaJIF);
            InternalFrameUtil.removerIcone(produtoListaJIF);
            produtoListaJIF.setVisible(true);
        }
        produtoListaJIF.toFront();
        FramePositionUtil.setCenteredPosition(produtoListaJIF);
        produtoListaJIF.getTxtBuscar().requestFocus();
    }

    public static void chamaCadastroProduto(Produto produto) {
        if (produtoCadastroJIF == null || !produtoCadastroJIF.isVisible()) {
            produtoCadastroJIF = new ProdutoCadastroJIF(produto);
            MenuPrincipal.desktopPrincipal.add(produtoCadastroJIF);
            InternalFrameUtil.removerIcone(produtoCadastroJIF);
            produtoCadastroJIF.setVisible(true);
        }
        produtoCadastroJIF.toFront();
        FramePositionUtil.setCenteredPosition(produtoCadastroJIF);
    }

    public static void chamaListaNota() {
        if (movimentoListaJIF == null || !movimentoListaJIF.isVisible()) {
            movimentoListaJIF = new MovimentoListaJIF();
            MenuPrincipal.desktopPrincipal.add(movimentoListaJIF);
            InternalFrameUtil.removerIcone(movimentoListaJIF);
            movimentoListaJIF.setVisible(true);
        }
        movimentoListaJIF.toFront();
        FramePositionUtil.setCenteredPosition(movimentoListaJIF);
    }

    static void chamaNota() {
        if (movimentoCadastroJIF == null || !movimentoCadastroJIF.isVisible()) {
            movimentoCadastroJIF = new MovimentoCadastroJIF();
            MenuPrincipal.desktopPrincipal.add(movimentoCadastroJIF);
            InternalFrameUtil.removerIcone(movimentoCadastroJIF);
            movimentoCadastroJIF.setVisible(true);
        }
        movimentoCadastroJIF.toFront();
        FramePositionUtil.setCenteredPosition(movimentoCadastroJIF);
    }
}
