/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Color;
import java.util.List;
import model.Natureza;
import service.NaturezaService;
import utils.ControleCores;
import view.internal.MovimentoCadastroJIF;

/**
 *
 * @author edson
 */
public class MovimentoCadastroController {

    Color corPadrao;

    public MovimentoCadastroController() {
        corPadrao = ControleCores.pegarCorPadrao();
    }

    public void mudarCorPaineis(MovimentoCadastroJIF form) {
        form.getPnPrincipal().setBackground(corPadrao);
        form.getPnDados().setBackground(corPadrao);
        form.getPnTransporte().setBackground(corPadrao);
        form.getPnTopo().setBackground(corPadrao);
        form.getPnInformacao().setBackground(corPadrao);
    }

    public void carregarNatureza(MovimentoCadastroJIF form) {
        NaturezaService naturezaService = new NaturezaService();
        List<Natureza> listaNaturezas = naturezaService.listarNaturezas();
        form.getCbNatureza().removeAllItems();
        for (Natureza natureza : listaNaturezas) {
            form.getCbNatureza().addItem(natureza);
        }
    }

}
