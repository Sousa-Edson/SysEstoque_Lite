/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Cliente;
import service.ClienteService;
import view.internal.ClienteCadastroInternal;

/**
 *
 * @author edson
 */
public class ClienteCadastroController {

    private final ClienteService clienteService;
    Cliente cliente;

    public ClienteCadastroController() {
        clienteService = new ClienteService();
        cliente = new Cliente();
    }

    public void limparCampos(ClienteCadastroInternal form) {
        form.getTxtBairro().setText("");
        form.getTxtCep().setText("");
        form.getTxtCidade().setText("");
        form.getTxtCnpj().setText("");
        form.getTxtComplemento().setText("");
        form.getTxtDescricao().setText("");
        form.getTxtEndereco().setText("");
        form.getTxtEstadual().setText("");
        form.getTxtNumero().setText("");
        form.getTxtObservacao().setText("");
        form.getTxtTelefone().setText("");

    }

    public void carregarCampos(ClienteCadastroInternal form, Cliente cliente) {

        if (cliente != null) {
            System.out.println("aqui");
            this.cliente = cliente;

            form.getTxtNome().setText(cliente.getCliente_nome());
            form.getTxtBairro().setText(cliente.getCliente_bairro());
            form.getTxtCep().setText(cliente.getCliente_cep());
            form.getTxtCidade().setText(cliente.getCliente_cidade());
            form.getTxtCnpj().setText(cliente.getCliente_cnpj());
            form.getTxtComplemento().setText(cliente.getCliente_complemento());
            form.getTxtDescricao().setText(cliente.getCliente_descricao());
            form.getTxtEndereco().setText(cliente.getCliente_endereco());
            form.getTxtEstadual().setText(cliente.getCliente_inscricao());
            form.getTxtNumero().setText(cliente.getCliente_no());
            form.getTxtObservacao().setText(cliente.getCliente_observacao());
            form.getTxtTelefone().setText(cliente.getCliente_telefone());

        } else {
            limparCampos(form);
            System.out.println("adada");
//            form.getBtnExcluir().setEnabled(false);
        }
//        form.getTxtipo().requestFocus();
    }
}
