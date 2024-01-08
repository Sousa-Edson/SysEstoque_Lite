/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javax.swing.JOptionPane;
import model.Cliente;
import service.ClienteService;
import utils.DataHoraAtual;
import utils.UsuarioLogado;
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
        form.getTxtNome().setText("");
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

        form.getLblId().setText("0");
    }

    public void bloqueiaCampos(ClienteCadastroInternal form, boolean bloquear) {
        form.getTxtNome().setEnabled(bloquear);
        form.getTxtBairro().setEnabled(bloquear);
        form.getTxtCep().setEnabled(bloquear);
        form.getTxtCidade().setEnabled(bloquear);
        form.getTxtCnpj().setEnabled(bloquear);
        form.getTxtComplemento().setEnabled(bloquear);
        form.getTxtDescricao().setEnabled(bloquear);
        form.getTxtEndereco().setEnabled(bloquear);
        form.getTxtEstadual().setEnabled(bloquear);
        form.getTxtNumero().setEnabled(bloquear);
        form.getTxtObservacao().setEnabled(bloquear);
        form.getTxtTelefone().setEnabled(bloquear);

    }

    public void bloqueiBotoes(ClienteCadastroInternal form, boolean bloqueado) {
        form.getBtnNovo().setEnabled(bloqueado);
        form.getBtnExcluir().setEnabled(bloqueado);
        form.getBtnSalvar().setEnabled(bloqueado);
    }

    public void carregarCampos(ClienteCadastroInternal form, Cliente cliente) {
        bloqueiBotoes(form, false);
        bloqueiaCampos(form, false);

        if (cliente != null) {

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

            form.getLblData().setText(cliente.getCliente_registro());
            form.getLblId().setText("" + cliente.getCliente_id());

            form.getBtnNovo().setEnabled(true);
            form.getBtnExcluir().setEnabled(true);
            form.getBtnNovo().setText("Editar");
        } else {

            limparCampos(form);
            form.getLblData().setText(DataHoraAtual.obterDataHoraFormatada());
            form.getBtnNovo().setEnabled(true);
        }
        form.getTxtNome().requestFocus();
    }

    public void salvar(ClienteCadastroInternal form) {

        cliente.setCliente_nome(form.getTxtNome().getText());
        cliente.setCliente_bairro(form.getTxtBairro().getText());
        cliente.setCliente_cep(form.getTxtCep().getText());
        cliente.setCliente_cidade(form.getTxtCidade().getText());
        cliente.setCliente_cnpj(form.getTxtCnpj().getText());
        cliente.setCliente_complemento(form.getTxtComplemento().getText());
        cliente.setCliente_descricao(form.getTxtDescricao().getText());
        cliente.setCliente_endereco(form.getTxtEndereco().getText());
        cliente.setCliente_inscricao(form.getTxtEstadual().getText());
        cliente.setCliente_no(form.getTxtNumero().getText());
        cliente.setCliente_observacao(form.getTxtObservacao().getText());
        cliente.setCliente_telefone(form.getTxtTelefone().getText());

        clienteService.salvar(cliente);
        form.dispose();
    }

    public void cliqueEmCancelar(ClienteCadastroInternal form) {
        limparCampos(form);
        bloqueiBotoes(form, false);
        bloqueiaCampos(form, false);
        form.getBtnNovo().setEnabled(true);
        form.getBtnNovo().setText("Novo");
    }

    public void cliqueBotaoNovo(ClienteCadastroInternal form) {
        bloqueiBotoes(form, false);
        form.getBtnSalvar().setEnabled(true);
        bloqueiaCampos(form, true);
        form.getBtnNovo().setText("Novo");
    }

    public void cliqueBotaoSalvar(ClienteCadastroInternal form) {
        if (form.getTxtNome().getText().trim().length() != 0) {
            salvar(form);
            cliqueEmCancelar(form);
        } else {
            JOptionPane.showMessageDialog(null, "Não é possivel salvar, ha campos vazios!");
        }

    }

    public void cliqueBotaoExcluir(ClienteCadastroInternal form) {
        Object[] options = {"Sim", "Não"};
        if (JOptionPane.showOptionDialog(null, "Deseja realmente deletar este cliente?",
                "Deletar #" + cliente.getCliente_id() + " ?", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,
                null, options, options[1]) == 0) {
            if (clienteService.excluirCliente(cliente)) {
                limparCampos(form);
                form.dispose();
            }

        }
    }

}
