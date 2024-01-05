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
    Natureza natureza;

    public NaturezaController() {
        naturezaService = new NaturezaService();
        natureza = new Natureza();
    }

    public void preencheTabela(NaturezaCadastroInternal form) {
        NaturezaTableModel modelo = new NaturezaTableModel();
        List<Natureza> produtos = naturezaService.listarNaturezasAtivas();
        modelo.setNaturezas(produtos);
        form.getTabela().setModel(modelo);
        form.getTabela().getColumnModel().getColumn(0).setPreferredWidth(60);

        form.getTabela().getTableHeader().setReorderingAllowed(false);
//        getjTable_Produto.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        form.getTabela().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        form.getTabela().requestFocus();
    }

    public void eventoSalvar(NaturezaCadastroInternal form) {
        
        natureza.setTipo_natureza((String) form.getCbTipoNatureza().getSelectedItem());
        natureza.setDesc_natureza(form.getTxtDescricao().getText().toUpperCase());

        naturezaService.salvar(natureza);
        bloqueiaCampos(form, false);
        limparCampos(form);
        form.getBtnNovo().setEnabled(true);
    }

    public void bloqueiaCampos(NaturezaCadastroInternal form, boolean ativo) {
        form.getBtnNovo().setEnabled(ativo);
        form.getBtnSalvar().setEnabled(ativo);
        form.getBtnExcluir().setEnabled(ativo);
        form.getCbTipoNatureza().setEnabled(ativo);
        form.getTxtDescricao().setEnabled(ativo);
    }

    public void limparCampos(NaturezaCadastroInternal form) {
        preencheTabela(form);
        form.getTxtDescricao().setText(null);
        form.getCbTipoNatureza().setSelectedItem("ENTRADA");
    }

    public void selecionarUmItem(NaturezaCadastroInternal form, java.awt.event.MouseEvent evt) {
        if (evt.getButton() == evt.BUTTON1) {

            int SelecionaId = (Integer) form.getTabela().getValueAt(form.getTabela().getSelectedRow(), 0);
            bloqueiaCampos(form, true);
            form.getBtnNovo().setEnabled(false);
            natureza = naturezaService.obterUmaNatureza(SelecionaId);
            form.getTxtDescricao().setText(natureza.getDesc_natureza());
            form.getCbTipoNatureza().setSelectedItem(natureza.getTipo_natureza());
        }
    }

}
