/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import model.Cliente;
import model.Natureza;
import service.ClienteService;
import service.NaturezaService;
import utils.ControleCores;
import utils.DataHoraAtual;
import view.internal.MovimentoCadastroJIF;

/**
 *
 * @author edson
 */
public class MovimentoCadastroController {
    
    Color corPadrao;
    
    SimpleDateFormat formato;
    
    public MovimentoCadastroController() {
        corPadrao = ControleCores.pegarCorPadrao();
        formato = new SimpleDateFormat("dd/MM/yyyy");
    }
    
    public void mudarCorPaineis(MovimentoCadastroJIF form) {
        form.getPnPrincipal().setBackground(corPadrao);
        form.getPnDados().setBackground(corPadrao);
        form.getPnTransporte().setBackground(corPadrao);
        form.getPnTopo().setBackground(corPadrao);
        form.getPnInformacao().setBackground(corPadrao);

//        form.getBtnExcluir().setVisible(false);
//        MovimentoCadastroJIF.getBtnExcluir().setVisible(false);
    }
    
    public void carregarNatureza(MovimentoCadastroJIF form) {
        NaturezaService naturezaService = new NaturezaService();
        List<Natureza> listaNaturezas = naturezaService.listarNaturezas();
        form.getCbNatureza().removeAllItems();
        for (Natureza natureza : listaNaturezas) {
            form.getCbNatureza().addItem(natureza);
        }
    }
    
    public void carregarCliente(MovimentoCadastroJIF form) {
        ClienteService clienteService = new ClienteService();
        List<Cliente> listaClientes = clienteService.listarClientes(false);
        form.getCbCliente().removeAllItems();
        for (Cliente cliente : listaClientes) {
            form.getCbCliente().addItem(cliente);
        }
    }
    
    public void dataAtual(MovimentoCadastroJIF form) {
        if (form.getDataNota().getDate() == (null)) {
            try {
                form.getDataNota().setDate(formato.parse(DataHoraAtual.obterDataFormatada()));
            } catch (ParseException ex) {
                System.err.println("erro::" + ex.getMessage());
            }
        } else {
            form.getDataNota().setDate(null);
        }        
    }
    
    public void horaAtual(MovimentoCadastroJIF form) {
        if (form.getTxtHora().getText().trim().isEmpty()) {
            form.getTxtHora().setText(DataHoraAtual.obterHoraFormatada());
        } else {
            form.getTxtHora().setText(null);
        }        
    }
    
}
