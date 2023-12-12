/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import view.internal.ProdutoListaJIF;

/**
 *
 * @author hp
 */
public class ProdutoListaController {

    public void limparPesquisaProdutoLista(ProdutoListaJIF form) {
/*        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{};
        ModeloTabela modelo = new ModeloTabela(dados, colunas);
        jTable_Produto.setModel(modelo);*/
        
        form.getBtnEditar().setEnabled(false);
        form.getTxtBuscar().setText("");
        form.getTxtBuscar().requestFocus();
        
    }
}
