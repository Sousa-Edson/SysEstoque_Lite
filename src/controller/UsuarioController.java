/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import utils.DataHoraAtual;
import utils.UsuarioLogado;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import model.Usuario;
import model.Usuario;
import service.UsuarioService;
import tableModel.UsuarioTableModel;
import tableModel.UsuarioTableModel;
import utils.GeradorCodigoAleatorio;
import view.internal.UsuarioCadastroInternal;

/**
 *
 * @author edson
 */
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController() {
        usuarioService = new UsuarioService();
    }

    public void salvarUsuario(UsuarioCadastroInternal form) {
        if (validarEntradas(form)) {

            Usuario usuario = new Usuario();

            usuario.setId_usuario(form.getIdUsuario());

            usuario.setSigla_usuario((String) form.getCbTipo().getSelectedItem());
            usuario.setDesc_usuario(form.getTxtNome().getText());
            usuario.setSenha_usuario(form.getTxtSenha().getText());
            if (usuarioService.salvarUsuario(usuario)) {
                cancelarUsuario(form);
            }
        }
    }

    public boolean validarEntradas(UsuarioCadastroInternal form) {
        if (form.getTxtSenha().getText().equals(form.getTxtConfirma().getText()) == false) {
            exibirAviso("Campo excede o número maximo de caracteres!");
            form.getTxtSenha().setText("");
            form.getTxtConfirma().setText("");
            form.getTxtSenha().requestFocus();
            return false;
        }

        if (form.getTxtNome().getText().isEmpty() || form.getTxtSenha().getText().isEmpty() || form.getTxtConfirma().getText().isEmpty()) {
            exibirAviso("Campo(s) vazio(s)");
            form.getTxtNome().requestFocus();
            return false;
        }
        return true;
    }

    public void exibirAviso(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    public void limparCampos(UsuarioCadastroInternal form) {
        form.getTxtConfirma().setText("");
        form.getTxtNome().setText("");
        form.getTxtSenha().setText("");
        form.getTxtNome().requestFocus();
    }

    public void liberarBotes(UsuarioCadastroInternal form, boolean b) {
        form.getBtnNovo().setEnabled(b);
        form.getBtnExcluir().setEnabled(b);
        form.getBtnSalvar().setEnabled(b);
    }

    public void novaUsuario(UsuarioCadastroInternal form) {
        liberarBotes(form, false);
        liberarCampos(form, true);
        preencheTabela(form);
        limparCampos(form);
        form.getBtnSalvar().setEnabled(true);
        form.setIdUsuario(0);
    }

    public void liberarCampos(UsuarioCadastroInternal form, boolean b) {
        form.getTxtConfirma().setEnabled(b);
        form.getTxtNome().setEnabled(b);
        form.getTxtSenha().setEnabled(b);
        form.getCbTipo().setEnabled(b);
    }

    public void cancelarUsuario(UsuarioCadastroInternal form) {
        liberarCampos(form, false);
        liberarBotes(form, false);
        limparCampos(form);
        form.getBtnNovo().setEnabled(true);
        form.setIdUsuario(0);
        preencheTabela(form);
    }

    public void preencheTabela(UsuarioCadastroInternal form) {

        UsuarioTableModel modelo = new UsuarioTableModel(usuarioService.listarUsuarios());

        form.getTabela().setModel(modelo);
        form.getTabela().getColumnModel().getColumn(0).setPreferredWidth(60);
        form.getTabela().getColumnModel().getColumn(0).setResizable(true);
        form.getTabela().getTableHeader().setReorderingAllowed(false);
//        jTable_Lista.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        form.getTabela().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    public void selecionaUsuario(UsuarioCadastroInternal form) {
        int id_usuario = (int) form.getTabela().getValueAt(form.getTabela().getSelectedRow(), 0);
        Usuario usuario = usuarioService.obterUsuarioPorId(id_usuario);
        form.setIdUsuario(id_usuario);
        form.getTxtNome().setText(usuario.getDesc_usuario());
        form.getCbTipo().setSelectedItem(usuario.getSigla_usuario());

        liberarCampos(form, true);
        liberarBotes(form, true);
        form.getBtnNovo().setEnabled(false);
        form.getTxtNome().requestFocus();
    }

    public void deletarUsuario(UsuarioCadastroInternal form, int idUsuario) {
        Object[] options = {"Sim", "Não"};
        if (JOptionPane.showOptionDialog(null, "Deseja realmente deletar esta unidade?",
                "Deletar #" + idUsuario + " ?", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,
                null, options, options[1]) == 0) {
            if (UsuarioLogado.getId() != idUsuario) {

                String codigoDeConfirmacaoAleatorio = GeradorCodigoAleatorio.gerarCodigoAleatorio(4);

                String codigoDeConfirmacao = JOptionPane.showInputDialog(null,
                        "Insira o código de 4 caracteres: " + codigoDeConfirmacaoAleatorio + "  ",
                        "Confirmação", JOptionPane.WARNING_MESSAGE);

                if (codigoDeConfirmacao != null && codigoDeConfirmacao.length() == 4
                        && codigoDeConfirmacao.equals(codigoDeConfirmacaoAleatorio)) {
                    usuarioService.excluirUsuario(idUsuario);
                    preencheTabela(form);
                    cancelarUsuario(form);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Não é possivel deletar usuario logado", "Aviso", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
