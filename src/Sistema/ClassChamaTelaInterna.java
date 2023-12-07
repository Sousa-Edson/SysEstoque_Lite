/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import Interface.BandeiraInternaJIF;
import Interface.BandeiraJIF;
import Consulta.ConsultaInterna3;
import Consulta.FrameExibir;
import Interface.ExibirLabelJFrame;
import Interface.ExpedicaoJIF;
import Interface.FCECadastroJIF;
import Interface.FCEListaJIF;
import Interface.LoginJIF;
import Interface.MovimentoCadastroJIF;
import Interface.MovimentoListaJIF;
import Interface.NaturezaCadastroJIF;
import Interface.Principal;
import Interface.ProdCadastroJIF;
import Interface.ProdutoListaJIF;
import Interface.ProtocoloInternaJIF;
import Interface.SetorCadastroJIF;
import view.UnidadeCadastroJIF;
import Interface.UsuarioCadastroJIF;

/**
 *
 * @author edson
 */
//// 11 94669 6901 flp
public class ClassChamaTelaInterna {

    ClassManipulaMovimento ManipulaMovimento = new ClassManipulaMovimento();

    ConsultaInterna3 ConsultaInterna = new ConsultaInterna3();
    ProdutoListaJIF ProdutoListaJIF = new ProdutoListaJIF();
    ProdCadastroJIF ProdCadastroJIF = new ProdCadastroJIF();
    UnidadeCadastroJIF UnidadeCadastroJIF = new UnidadeCadastroJIF();
    FCEListaJIF FCELista = new FCEListaJIF();
    FCECadastroJIF FCECadastro = new FCECadastroJIF();
    ExibirLabelJFrame ExibirLabelJFrame = new ExibirLabelJFrame();
    FrameExibir FrameExibir = new FrameExibir();
    NaturezaCadastroJIF NaturezaCadastro = new NaturezaCadastroJIF();
    UsuarioCadastroJIF UsuarioCadastro = new UsuarioCadastroJIF();
    SetorCadastroJIF SetorCadastro = new SetorCadastroJIF();
    MovimentoListaJIF MovimentoLista = new MovimentoListaJIF();
    MovimentoCadastroJIF MovimentoCadastro = new MovimentoCadastroJIF();
    LoginJIF ChamaLogin = new LoginJIF();
    ExpedicaoJIF ChamaExpedicao = new ExpedicaoJIF();
    BandeiraInternaJIF ChamaBandeira = new BandeiraInternaJIF();
    ProtocoloInternaJIF ChamaProtocolo = new ProtocoloInternaJIF();
    Principal menu;

    public void ChamaCadastroProdutoInterno() {
        String id = menu.jLabelCodigoTela2.getText();
        ProdCadastroJIF.RecebeIdProduto(id);
        if (ProdCadastroJIF.isVisible()) {
//            ProdCadastroJIF.setVisible(false);
//            menu.PainelPrincipal.remove(ProdCadastroJIF);
            ProdCadastroJIF.setPosicao();
            ProdCadastroJIF.toFront();
            // ConsultaInterna.EventoData()
            ProdCadastroJIF.pack();
        } else { menu.PainelPrincipal.remove(ProdCadastroJIF);
            menu.PainelPrincipal.add(ProdCadastroJIF);
            // ProdCadastroJIF.setSize(514, 244); // [909, 421]
            ProdCadastroJIF.setPosicao();
            ProdCadastroJIF.setVisible(true);
            ProdCadastroJIF.toFront();
            // ConsultaInterna.EventoData()
            ProdCadastroJIF.pack();

        }
        ProdCadastroJIF.toFront();
    }

    public void ChamaCadastroUnidade() {
        UnidadeCadastroJIF.PreencheTabela();
        if (UnidadeCadastroJIF.isVisible()) {
        } else {menu.PainelPrincipal.remove(UnidadeCadastroJIF);
            menu.PainelPrincipal.add(UnidadeCadastroJIF);

            UnidadeCadastroJIF.setVisible(true);
        }
        UnidadeCadastroJIF.toFront();
        UnidadeCadastroJIF.setPosicao();
        UnidadeCadastroJIF.PreencheTabela();
    }

    public void FechaCadastroProdutoInternoEditar() {
        String id = menu.jLabelCodigoTela2.getText();
        if (ProdCadastroJIF.isVisible()) {
            ProdCadastroJIF.setVisible(false);
            menu.PainelPrincipal.remove(ProdCadastroJIF);

        } else {

        }
    }

    public void FechaFCECadastroEditar() {//ChamaFCECadastroEditar
        String id = menu.jLabelCodigoTela2.getText();
        if (FCECadastro.isVisible()) {
            FCECadastro.setVisible(false);
            menu.PainelPrincipal.remove(FCECadastro);

        } else {

        }
    }

    public void ChamaCadastroProdutoInternoEditar() {
        String id = menu.jLabelCodigoTela2.getText();
        if (ProdCadastroJIF.isVisible()) {
            ProdCadastroJIF.RecebeIdProduto(id);
            ProdCadastroJIF.toFront();
//            ProdCadastroJIF.setPosicao();
        } else { menu.PainelPrincipal.remove(ProdCadastroJIF);
            menu.PainelPrincipal.add(ProdCadastroJIF);
            ProdCadastroJIF.RecebeIdProduto(id);
            ProdCadastroJIF.setPosicao();
            ProdCadastroJIF.setVisible(true);
            ProdCadastroJIF.toFront();
            // ConsultaInterna.EventoData()
//            ProdCadastroJIF.pack();
        }
    }

    public void ChamaConsultaInterna() {
        if (ConsultaInterna.isVisible()) {
//            ConsultaInterna.setVisible(false);
//            menu.PainelPrincipal.remove(ConsultaInterna);
            ConsultaInterna.setSize(1068, 425); // [909, 421]
            ConsultaInterna.setPosicao();
            ConsultaInterna.toFront();
        } else {
            menu.PainelPrincipal.remove(ConsultaInterna);
            menu.PainelPrincipal.add(ConsultaInterna);
            ConsultaInterna.setSize(1068, 425); // [909, 421]
            ConsultaInterna.setPosicao();
            ConsultaInterna.setVisible(true);
            ConsultaInterna.toFront();
            // ConsultaInterna.EventoData()
            // ConsultaInterna.pack();

        }
    }

    public void ChamaProdutoLista() {
        if (ProdutoListaJIF.isVisible()) {
//            ProdutoListaJIF.setVisible(false);
//            menu.PainelPrincipal.remove(ProdutoListaJIF);
            ProdutoListaJIF.setSize(1068, 425); // [909, 421]
            ProdutoListaJIF.setPosicao();
            ProdutoListaJIF.toFront();
        } else {
            menu.PainelPrincipal.remove(ProdutoListaJIF);
            menu.PainelPrincipal.add(ProdutoListaJIF);
            ProdutoListaJIF.setSize(1068, 425); // [909, 421]
            ProdutoListaJIF.setPosicao();
            ProdutoListaJIF.setVisible(true);
            ProdutoListaJIF.toFront();
            // ConsultaInterna.EventoData()
            // ConsultaInterna.pack();

        }
    }

    public void AtualizaProdutoLista() {
        if (ProdutoListaJIF.isVisible()) {
            ProdutoListaJIF.BuscaLimpa();
        } else {

        }
    }

    public void ChamaFCELista() {
        if (FCELista.isVisible()) {
            FCELista.setSize(1068, 425); // [909, 421]
            FCELista.setPosicao();
            FCELista.toFront();
        } else { menu.PainelPrincipal.remove(FCELista);
            menu.PainelPrincipal.add(FCELista);
            FCELista.setSize(1068, 425); // [909, 421]
            FCELista.setPosicao();
            FCELista.setVisible(true);
            FCELista.toFront();

        }
    }

    public void ChamaFCEListaAtualizar() {
        if (FCELista.isVisible()) {

        } else {menu.PainelPrincipal.remove(FCELista);
            menu.PainelPrincipal.add(FCELista);
            FCELista.setVisible(true);
            FCELista.setSize(1068, 425); // [909, 421]
            FCELista.setPosicao();
            FCELista.toFront();
        }

        FCELista.EventoBuscar();
    }

    public void ChamaFCECadastro() {
        if (FCECadastro.isVisible()) {
        } else {  menu.PainelPrincipal.remove(FCECadastro);
            menu.PainelPrincipal.add(FCECadastro);
            FCECadastro.setVisible(true);
        }
//        FCELista.setSize(1068, 425); // [909, 421]
        FCECadastro.recebeId(menu.jLabelCodigoTela2.getText());
        FCECadastro.iniciaFormulario();
        System.out.println("Sistema.ClassChamaTelaInterna.ChamaFCECadastro()" + menu.jLabelCodigoTela2.getText());
        FCECadastro.setPosicao();
        FCECadastro.toFront();
    }

    public void ChamaFCECadastroEditar() {
        if (FCECadastro.isVisible()) {
        } else { menu.PainelPrincipal.remove(FCECadastro);
            menu.PainelPrincipal.add(FCECadastro);
            FCECadastro.setVisible(true);
        }
//        FCELista.setSize(1068, 425); // [909, 421]
        FCECadastro.recebeId(menu.jLabelCodigoTela2.getText());
        FCECadastro.iniciaFormulario();
        System.out.println("Sistema.ClassChamaTelaInterna.ChamaFCECadastroEditar()" + menu.jLabelCodigoTela2.getText());
        FCECadastro.setPosicao();
        FCECadastro.toFront();
    }

    public void ChamaExibir() {
        ExibirLabelJFrame.RecebeDados(menu.jLabelCodigoTela2.getText());
        if (ExibirLabelJFrame.isVisible()) {
            ExibirLabelJFrame.toFront();
        } else {
            ExibirLabelJFrame.toFront();
            ExibirLabelJFrame.setVisible(true);

        }
    }

    public void ChamaFrameExibir() {
        FrameExibir.RecebeDados(menu.jLabelCodigoTela2.getText());
        if (FrameExibir.isVisible()) {
            FrameExibir.toFront();
        } else {
            FrameExibir.toFront();
            FrameExibir.setVisible(true);

        }
    }

    public void ChamaNaturezaCadastro() {
        if (NaturezaCadastro.isVisible()) {
        } else {  menu.PainelPrincipal.remove(NaturezaCadastro);
            menu.PainelPrincipal.add(NaturezaCadastro);
            NaturezaCadastro.setVisible(true);
        }
//        NaturezaCadastro.recebeId(menu.jLabelCodigoTela2.getText());
//        NaturezaCadastro.iniciaFormulario();
        NaturezaCadastro.PreencheTabela();
        NaturezaCadastro.setPosicao();
        NaturezaCadastro.toFront();
    }

    public void ChamaUsuarioCadastro() {
        if (UsuarioCadastro.isVisible()) {
        } else { menu.PainelPrincipal.remove(UsuarioCadastro);
            menu.PainelPrincipal.add(UsuarioCadastro);
            UsuarioCadastro.setVisible(true);
        }
        UsuarioCadastro.PreencheTabela();
        UsuarioCadastro.setPosicao();
        UsuarioCadastro.toFront();
    }

    public void ChamaSetorCadastro() {
        if (SetorCadastro.isVisible()) {
        } else { menu.PainelPrincipal.remove(SetorCadastro);
            menu.PainelPrincipal.add(SetorCadastro);
            SetorCadastro.setVisible(true);
        }
        SetorCadastro.PreencheTabela();
        SetorCadastro.setPosicao();
        SetorCadastro.toFront();
    }

    public void ChamaMovimentoLista() {
        if (MovimentoLista.isVisible()) {
        } else {
            menu.PainelPrincipal.remove(MovimentoLista);
            menu.PainelPrincipal.add(MovimentoLista);
            MovimentoLista.setVisible(true);
        }
        MovimentoLista.BotaoCancelar();//preencherTabela
        MovimentoLista.setPosicao();
        MovimentoLista.toFront();
    }

    public void ManipulaMovimentoEditar() {
        ManipulaMovimento.RecebeNota_Mov(Principal.jLabelCodigoTela2.getText());
        ManipulaMovimento.InsereMovimentoTemp();
    }

    public void ManipulaMovimentoLimpar() {
        ManipulaMovimento.LimpaMovimentoTemp();
    }

    public void FechaMovimentoCadastro() {
        if (MovimentoCadastro.isVisible()) {
            MovimentoCadastro.setVisible(false);
            menu.PainelPrincipal.remove(MovimentoCadastro);
//            MovimentoCadastro.setVisible(false);
        } else {
        }
        if (MovimentoLista.isVisible()) {
            MovimentoLista.BotaoCancelar();
        } else {
        }
    }

    public void ChamaMovimentoCadastroEditar() {
        ManipulaMovimentoLimpar();
        if (MovimentoCadastro.isVisible()) {
        } else { menu.PainelPrincipal.remove(MovimentoCadastro);
            menu.PainelPrincipal.add(MovimentoCadastro);
            MovimentoCadastro.setVisible(true);
        }
        ManipulaMovimentoEditar(); // aqui insere na tabela
        MovimentoCadastro.setSize(1100, 500);//pack(); [1061, 436]
        MovimentoCadastro.RecebeIdNota(Principal.jLabelCodigoTela2.getText());
        MovimentoCadastro.PreencherNatureza();
        MovimentoCadastro.PreencherFornecedor();
        MovimentoCadastro.PreencherTransporte();
        MovimentoCadastro.CarregaFormulario();
//        MovimentoCadastro.SelecionarNatureza();
        MovimentoCadastro.setPosicao();
        MovimentoCadastro.toFront();
        MovimentoCadastro.PreencherTabela();
    }

    public void ChamaMovimentoCadastroNovo() {
        ManipulaMovimentoLimpar();
        if (MovimentoCadastro.isVisible()) {
        } else {menu.PainelPrincipal.remove(MovimentoCadastro);
            menu.PainelPrincipal.add(MovimentoCadastro);
            MovimentoCadastro.setVisible(true);
        }
        ManipulaMovimentoLimpar();
        MovimentoCadastro.setSize(1100, 500);//pack(); [1061, 436]
        MovimentoCadastro.RecebeIdNota(Principal.jLabelCodigoTela2.getText());
        MovimentoCadastro.PreencherFornecedor();
        MovimentoCadastro.PreencherNatureza();
        MovimentoCadastro.PreencherTransporte();
        MovimentoCadastro.CarregaFormulario();
//        MovimentoCadastro.SelecionarNatureza();
        MovimentoCadastro.setPosicao();
        MovimentoCadastro.toFront();
    }

    public void ChamaLogin() {
//        ExibirLabelJFrame.RecebeDados(menu.jLabelCodigoTela2.getText());
        if (ChamaLogin.isVisible()) {
            ChamaLogin.toFront();
        } else {
            ChamaLogin.toFront();
            ChamaLogin.setVisible(true);
        }
        System.out.println("Sistema.ClassChamaTelaInterna.ChamaLogin()");
    }

    public void ChamaExpedicao() {
//        ExibirLabelJFrame.RecebeDados(menu.jLabelCodigoTela2.getText());
        if (ChamaExpedicao.isVisible()) {
            ChamaExpedicao.toFront();
            ChamaExpedicao.atualizarTabela();
            ChamaExpedicao.setPosicao();
        } else {
            menu.PainelPrincipal.remove(ChamaExpedicao);
            menu.PainelPrincipal.add(ChamaExpedicao);
            ChamaExpedicao.setPosicao();
            ChamaExpedicao.toFront();
            ChamaExpedicao.setVisible(true);
            ChamaExpedicao.atualizarTabela();
        }
        ChamaExpedicao.setResizable(true);
        System.out.println("Sistema.ClassChamaTelaInterna.ChamaExpedicao()");
    }

    public void ChamaBandeira() {
//        ExibirLabelJFrame.RecebeDados(menu.jLabelCodigoTela2.getText());
        if (ChamaBandeira.isVisible()) {
            ChamaBandeira.toFront();
            ChamaBandeira.setPosicao();
            ChamaBandeira.EventoBuscar();
        } else { menu.PainelPrincipal.remove(ChamaBandeira);
            menu.PainelPrincipal.add(ChamaBandeira);
            ChamaBandeira.toFront();
            ChamaBandeira.setPosicao();
            ChamaBandeira.setVisible(true);
            ChamaBandeira.EventoBuscar();
        }
        System.out.println("Sistema.ClassChamaTelaInterna.ChamaBandeira()");
    }

    public void ChamaProtocolo() {
//        ExibirLabelJFrame.RecebeDados(menu.jLabelCodigoTela2.getText());
        if (ChamaProtocolo.isVisible()) {
            ChamaProtocolo.toFront();
            ChamaProtocolo.setPosicao();
            ChamaProtocolo.EventoBuscar();
        } else {
            menu.PainelPrincipal.remove(ChamaProtocolo);
            menu.PainelPrincipal.add(ChamaProtocolo);
            ChamaProtocolo.toFront();
            ChamaProtocolo.setPosicao();
            ChamaProtocolo.setVisible(true);
            ChamaProtocolo.EventoBuscar();
        }
        System.out.println("Sistema.ClassChamaTelaInterna.ChamaProtocolo()");
    }

    public void ChamaAtualizaTudo() {
//        if (ChamaProtocolo.isVisible()) {
//            ChamaProtocolo.EventoBuscar();
//        } else {
//        }
//        if (ChamaBandeira.isVisible()) {
//            ChamaBandeira.EventoBuscar();
//        } else {
//        }
//        if (ChamaExpedicao.isVisible()) {
//            ChamaExpedicao.atualizarTabela();
//        } else {
//        }
//        if (MovimentoLista.isVisible()) {
//            MovimentoLista.preencherTabela();
//        } else {
//        }
//        if (UsuarioCadastro.isVisible()) {
//            UsuarioCadastro.PreencheTabela();
//        } else {
//        }
//
//        if (NaturezaCadastro.isVisible()) {
//            NaturezaCadastro.PreencheTabela();
//        } else {
//        }
//
//        if (FCELista.isVisible()) {
//            FCELista.preencherTabela();
//        } else {
//        }
//
//        if (ProdutoListaJIF.isVisible()) {
//            ProdutoListaJIF.EventoBuscar();
//        } else {
//
//        }
//
//        if (ConsultaInterna.isVisible()) {
//            ConsultaInterna.EventoBuscar();
//        } else {
//        }
//
//        if (UnidadeCadastroJIF.isVisible()) {
//            UnidadeCadastroJIF.PreencheTabela();
//        } else {
//        }
        System.out.println("Sistema.ClassChamaTelaInterna.ChamaAtualizaTudo()");
    }

    public void ChamaLimpaTudo() {
        ChamaProtocolo.setVisible(false);
        ChamaBandeira.setVisible(false);
        ChamaExpedicao.setVisible(false);
        MovimentoLista.setVisible(false);
        UsuarioCadastro.setVisible(false);;
        NaturezaCadastro.setVisible(false);
        FCELista.setVisible(false);
        ProdutoListaJIF.setVisible(false);
        ConsultaInterna.setVisible(false);
        UnidadeCadastroJIF.setVisible(false);
        ConsultaInterna.setVisible(false);
        ProdutoListaJIF.setVisible(false);
        ProdCadastroJIF.setVisible(false);
        UnidadeCadastroJIF.setVisible(false);
        FCELista.setVisible(false);
        FCECadastro.setVisible(false);
        NaturezaCadastro.setVisible(false);
        UsuarioCadastro.setVisible(false);
        SetorCadastro.setVisible(false);
        MovimentoLista.setVisible(false);
        MovimentoCadastro.setVisible(false);
        ChamaExpedicao.setVisible(false);
        ChamaBandeira.setVisible(false);
        ChamaProtocolo.setVisible(false);
//        menu.PainelPrincipal.removeAll();
        System.out.println("Sistema.ClassChamaTelaInterna.ChamaAtualizaTudo()");
    }
}
