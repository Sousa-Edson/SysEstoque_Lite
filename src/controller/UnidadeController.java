/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import UTIL.DataHoraAtual;
import UTIL.UsuarioLogado;
import model.Unidade;
import service.UnidadeService;
import view.UnidadeCadastroJIF;

/**
 *
 * @author edson
 */
public class UnidadeController {

    private UnidadeService unidadeService;

    private Unidade unidade = new Unidade();
//    UnidadeCadastroJIF uni;

    public UnidadeController() {
        unidadeService = new UnidadeService();
//        uni = new UnidadeCadastroJIF();
    }

    public void salvarUnidade(UnidadeCadastroJIF form) {

        unidadeService.salvarUnidade(
                new Unidade(unidadeService.idReferencia(),
                        form.getId_unidade(),
                        1,
                        form.getRbFragmentadoNao().isSelected() ? 0 : 1,
                        form.getTxtSigla().getText(),
                        form.getTxtDescricao().getText(),
                        DataHoraAtual.obterDataHoraFormatada(),
                        UsuarioLogado.getNome()));
    }

    public void d(UnidadeCadastroJIF uni) {
        System.out.println("controller.UnidadeController.d(): " + uni.getTxtDescricao().getText());
    }
}
