/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import view.internal.ProdutoCadastroJIF;

/**
 *
 * @author edson
 */
public class ProdutoController {

    public  void limparCampos(ProdutoCadastroJIF form) {
        form.getTxtipo().setText("");
        form.getTxtDesc().setText("");
        form.getTxtEdicao().setText("");
        form.getTxtValor().setText("");
        form.getTxtObs().setText("");
        form.getSpNcm().setValue(0);
        form.getSpCfop().setValue(0);
        form.getSpEstoqueMinimo().setValue(0);
        form.getBtnExcluir().setEnabled(false);
        form.getTxtipo().requestFocus();
    }

}
