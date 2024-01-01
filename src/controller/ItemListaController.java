/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import extras.TelaInternaController;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ListSelectionModel;
import model.NotaFiscal;
import service.NotaFiscalService;
import tableModel.NotaFiscalTableModel;
import view.internal.NotaListaJIF;

/**
 *
 * @author edson
 */
public class ItemListaController {

    private final NotaFiscalService notaService;
    NotaFiscal notaFiscal;

    public ItemListaController() {
        notaService = new NotaFiscalService();
        notaFiscal = new NotaFiscal();
    }

    public void limparPesquisaProdutoLista(NotaListaJIF form) {
        NotaFiscalTableModel modelo = new NotaFiscalTableModel();
        modelo.setNotas(new ArrayList<>());
        form.getTabela().setModel(modelo);

        form.getBtnEditar().setEnabled(false);
        form.getTxtBuscar().setText("");
        form.getTxtBuscar().requestFocus();
    }

    public void preencheTabela(NotaListaJIF form) {
        NotaFiscalTableModel modelo = new NotaFiscalTableModel();
        List<NotaFiscal> produtos = notaService.listarNotasPorBusca(form.getTxtBuscar().getText().toUpperCase());
        modelo.setNotas(produtos);
        form.getTabela().setModel(modelo);
        form.getTabela().getColumnModel().getColumn(0).setPreferredWidth(60);
        form.getTabela().getColumnModel().getColumn(0).setResizable(true);

        form.getTabela().getTableHeader().setReorderingAllowed(false);
//        getjTable_Produto.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        form.getTabela().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        form.getTxtBuscar().requestFocus();
    }

    public void chamaCadastro(NotaListaJIF aThis) {
        notaFiscal = null;
        TelaInternaController.chamaNota(notaFiscal);
    }

    public void cliqueTabela(NotaListaJIF form, java.awt.event.MouseEvent evt) {
        if (evt.getButton() == evt.BUTTON1) {
            if (evt.getClickCount() == 2) {
                int SelecionaNotaId = (Integer) form.getTabela().getValueAt(form.getTabela().getSelectedRow(), 0);
//                form.getBtnEditar().setEnabled(true);
                System.out.println("SelecionaNotaId::" + SelecionaNotaId);
                notaFiscal = notaService.obterNotaPorId(SelecionaNotaId);
                TelaInternaController.chamaNota(notaFiscal);
                if (notaFiscal == null) {
                    preencheTabela(form);
                    form.getBtnEditar().setEnabled(false);
                }
            }
        }
    }

}
