/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import javax.swing.ListSelectionModel;
import model.Natureza;
import service.NaturezaService;
import tableModel.NaturezaTableModel;
import view.internal.ConsultaInterna;
import view.internal.NaturezaCadastroInternal;

/**
 *
 * @author edson
 */
public class NaturezaController {

    private final NaturezaService naturezaService;

    public NaturezaController() {
        naturezaService = new NaturezaService();
    }

    public void preencheTabela(NaturezaCadastroInternal form) {
        NaturezaTableModel modelo = new NaturezaTableModel();
        List<Natureza> produtos = naturezaService.listarNaturezas();
        modelo.setNaturezas(produtos);
        form.getTabela().setModel(modelo);
        form.getTabela().getColumnModel().getColumn(0).setPreferredWidth(60);

        form.getTabela().getTableHeader().setReorderingAllowed(false);
//        getjTable_Produto.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        form.getTabela().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        form.getTabela().requestFocus();
    }

    public void eventoSalvar(NaturezaCadastroInternal form) {
        Natureza natureza = new Natureza();
        natureza.setTipo_natureza((String) form.getCbTipoNatureza().getSelectedItem());
        natureza.setDesc_natureza(form.getTxtDescricao().getText().toUpperCase());

        naturezaService.salvar(natureza);
        bloqueiaCampos(form, false);
        limparCampos(form);
    }

    public void bloqueiaCampos(NaturezaCadastroInternal form, boolean ativo) {
        form.getBtnNovo().setEnabled(true);
        form.getBtnSalvar().setEnabled(false);
        form.getBtnExcluir().setEnabled(false);
        form.getCbTipoNatureza().setEnabled(false);
        form.getTxtDescricao().setEnabled(false);
    }

    public void limparCampos(NaturezaCadastroInternal form) {
        preencheTabela(form);
        form.getTxtDescricao().setText(null);
        form.getCbTipoNatureza().setSelectedItem("ENTRADA");
    }
}
