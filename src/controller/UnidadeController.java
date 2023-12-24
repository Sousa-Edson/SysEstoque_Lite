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
import model.Unidade;
import service.UnidadeService;
import tableModel.UnidadeTableModel;
import view.internal.UnidadeCadastroJIF;

/**
 *
 * @author edson
 */
public class UnidadeController {
    
    private final UnidadeService unidadeService;
    
    public UnidadeController() {
        unidadeService = new UnidadeService();
    }
    
    public void salvarUnidade(UnidadeCadastroJIF form) {
        if (unidadeService.salvarUnidade(
                new Unidade(unidadeService.idReferencia(),
                        form.getId_unidade(),
                        1,
                        form.getRbFragmentadoNao().isSelected() ? 0 : 1,
                        form.getTxtSigla().getText().toUpperCase(),
                        form.getTxtDescricao().getText().toUpperCase(),
                        DataHoraAtual.obterDataHoraFormatada(),
                        UsuarioLogado.getNome())) == true) {
            cancelarUnidade(form);
        }
    }
    
    public boolean validarEntradas(UnidadeCadastroJIF form) {
        if (form.getTxtSigla() != null && form.getTxtSigla().getText().length() >= 5) {
            exibirAviso("Campo excede o número maximo de caracteres!");
            form.setTxtSigla("");
            form.getTxtSigla().requestFocus();
            return false;
        }
        if (form.getTxtDescricao().getText().length() >= 11) {
            exibirAviso("Campo excede o número maximo de caracteres!");
            form.setTxtDescricao("");
            form.getTxtDescricao().requestFocus();
            return false;
        }
        if (form.getTxtSigla().getText().isEmpty() | form.getTxtDescricao().getText().isEmpty()) {
            exibirAviso("Campo(s) vazio(s)");
            form.getTxtSigla().requestFocus();
            return false;
        }
        return true;
    }
    
    public void exibirAviso(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Aviso", JOptionPane.WARNING_MESSAGE);
    }
    
    public void limparCampos(UnidadeCadastroJIF form) {
        form.getTxtDescricao().setText("");
        form.getTxtSigla().setText("");
        form.getTxtSigla().requestFocus();
    }
    
    public void liberarBotes(UnidadeCadastroJIF form, boolean b) {
        form.getBtnNovo().setEnabled(b);
        form.getBtnExcluir().setEnabled(b);
        form.getBtnSalvar().setEnabled(b);
    }
    
    public void novaUnidade(UnidadeCadastroJIF form) {
        liberarBotes(form, false);
        liberarCampos(form, true);
        preencheTabela(form);
        limparCampos(form);
        form.getBtnSalvar().setEnabled(true);
        form.setId_unidade(0);
    }
    
    public void liberarCampos(UnidadeCadastroJIF form, boolean b) {
        form.getRbFragmentadoNao().setEnabled(b);
        form.getRbFragmentadoSim().setEnabled(b);
        form.getTxtSigla().setEnabled(b);
        form.getTxtDescricao().setEnabled(b);
    }
    
    public void cancelarUnidade(UnidadeCadastroJIF form) {
        liberarCampos(form, false);
        liberarBotes(form, false);
        limparCampos(form);
        form.getBtnNovo().setEnabled(true);
        form.setId_unidade(0);
        preencheTabela(form);
    }
    
    public void preencheTabela(UnidadeCadastroJIF form) {
        
        UnidadeTableModel modelo = new UnidadeTableModel();
        List<Unidade> unidades = unidadeService.listarUnidades();
        modelo.setUnidades(unidades);
        form.getjTable_Lista().setModel(modelo);
        form.getjTable_Lista().getColumnModel().getColumn(0).setPreferredWidth(60);
        form.getjTable_Lista().getColumnModel().getColumn(0).setResizable(true);
        form.getjTable_Lista().getTableHeader().setReorderingAllowed(false);
//        jTable_Lista.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        form.getjTable_Lista().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
    }
    
    public void selecionaUnidade(UnidadeCadastroJIF form) {
        int id_unidade = (int) form.getjTable_Lista().getValueAt(form.getjTable_Lista().getSelectedRow(), 0);
        Unidade unidade = unidadeService.obterUnidadePorId(id_unidade);
        form.setId_unidade(id_unidade);
        form.getTxtSigla().setText(unidade.getSigla_unidade());
        form.getTxtDescricao().setText(unidade.getDesc_unidade());
        
        liberarCampos(form, true);
        liberarBotes(form, true);
        form.getBtnNovo().setEnabled(false);
        form.getTxtSigla().requestFocus();
    }
    
    public void deletarUnidade(UnidadeCadastroJIF form, int idUnidade) {
        Object[] options = {"Sim", "Não"};
        if (JOptionPane.showOptionDialog(null, "Deseja realmente deletar esta unidade?",
                "Deletar #" + idUnidade + " ?", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,
                null, options, options[1]) == 0) {
            unidadeService.excluirUnidade(idUnidade);
            preencheTabela(form);
            cancelarUnidade(form);
        }
    }
}
