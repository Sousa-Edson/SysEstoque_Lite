/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extras;

import model.NotaFiscal;
import model.Produto;
import view.internal.ProdutoListaJIF;
import view.MenuPrincipal;
import view.internal.ClienteListaInternal;
import view.internal.ConsultaInterna;
import view.internal.ExpedicaoInterna;
import view.internal.LoginJIF;
import view.internal.NaturezaCadastroInternal;
import view.internal.NotaCadastroJIF;
import view.internal.NotaListaJIF;
import view.internal.ProdutoCadastroJIF;
import view.internal.UnidadeCadastroJIF;
import view.internal.UsuarioCadastroInternal;

/**
 *
 * @author edson
 */
public class TelaInternaController {

    private static LoginJIF loginJIF;
    private static UnidadeCadastroJIF unidadeCadastroJIF;
    private static ProdutoListaJIF produtoListaJIF;
    private static ProdutoCadastroJIF produtoCadastroJIF;
    private static NotaListaJIF movimentoListaJIF;
    private static NotaCadastroJIF movimentoCadastroJIF;
    private static ConsultaInterna consultaInterna;
    private static ExpedicaoInterna expedicaoInterna;
    private static NaturezaCadastroInternal naturezaCadastroInternal;
    private static UsuarioCadastroInternal usuarioCadastroInternal;
    private static ClienteListaInternal clienteListaInternal;

    public static void chamaClienteListaInternal(MenuPrincipal form) {
        if (clienteListaInternal == null || !clienteListaInternal.isVisible()) {
            clienteListaInternal = new ClienteListaInternal();
            MenuPrincipal.desktopPrincipal.add(clienteListaInternal);
            InternalFrameUtil.removerIcone(clienteListaInternal);
            clienteListaInternal.setVisible(true);
        }
        clienteListaInternal.toFront();
        FramePositionUtil.setCenteredPosition(clienteListaInternal);
    }

    public static void chamaUsuarioCadastroInternal(MenuPrincipal form) {
        if (usuarioCadastroInternal == null || !usuarioCadastroInternal.isVisible()) {
            usuarioCadastroInternal = new UsuarioCadastroInternal();
            MenuPrincipal.desktopPrincipal.add(usuarioCadastroInternal);
            InternalFrameUtil.removerIcone(usuarioCadastroInternal);
            usuarioCadastroInternal.setVisible(true);
        }
        usuarioCadastroInternal.toFront();
        FramePositionUtil.setCenteredPosition(usuarioCadastroInternal);
    }

    public static void chamaNaturezaCadastroInternal(MenuPrincipal form) {
        if (naturezaCadastroInternal == null || !naturezaCadastroInternal.isVisible()) {
            naturezaCadastroInternal = new NaturezaCadastroInternal();
            MenuPrincipal.desktopPrincipal.add(naturezaCadastroInternal);
            InternalFrameUtil.removerIcone(naturezaCadastroInternal);
            naturezaCadastroInternal.setVisible(true);
        }
        naturezaCadastroInternal.toFront();
        FramePositionUtil.setCenteredPosition(naturezaCadastroInternal);
    }

    public static void chamaExpedicaoInterna(MenuPrincipal form) {
        if (expedicaoInterna == null || !expedicaoInterna.isVisible()) {
            expedicaoInterna = new ExpedicaoInterna();
            MenuPrincipal.desktopPrincipal.add(expedicaoInterna);
            InternalFrameUtil.removerIcone(expedicaoInterna);
            expedicaoInterna.setVisible(true);
        }
        expedicaoInterna.toFront();
        FramePositionUtil.setCenteredPosition(expedicaoInterna);
    }

    public static void chamaConsultaInterna(MenuPrincipal form) {
        if (consultaInterna == null || !consultaInterna.isVisible()) {
            consultaInterna = new ConsultaInterna();
            MenuPrincipal.desktopPrincipal.add(consultaInterna);
            InternalFrameUtil.removerIcone(consultaInterna);
            consultaInterna.setVisible(true);
        }
        consultaInterna.toFront();
        FramePositionUtil.setCenteredPosition(consultaInterna);
    }

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
            movimentoListaJIF = new NotaListaJIF();
            MenuPrincipal.desktopPrincipal.add(movimentoListaJIF);
            InternalFrameUtil.removerIcone(movimentoListaJIF);
            movimentoListaJIF.setVisible(true);
        }
        movimentoListaJIF.toFront();
        movimentoListaJIF.getTxtBuscar().requestFocus();
        FramePositionUtil.setCenteredPosition(movimentoListaJIF);
    }

    public static void chamaNota(NotaFiscal notaFiscal) {
        if (movimentoCadastroJIF == null || !movimentoCadastroJIF.isVisible()) {
            movimentoCadastroJIF = new NotaCadastroJIF(notaFiscal);
            MenuPrincipal.desktopPrincipal.add(movimentoCadastroJIF);
            InternalFrameUtil.removerIcone(movimentoCadastroJIF);
            movimentoCadastroJIF.setVisible(true);
        }
        movimentoCadastroJIF.toFront();
        FramePositionUtil.setCenteredPosition(movimentoCadastroJIF);
    }
}
