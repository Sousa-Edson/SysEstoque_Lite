/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import extras.TelaInternaController;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ListSelectionModel;
import model.Cliente;
import service.ClienteService;
import tableModel.ClienteTableModel;
import view.internal.ClienteListaInternal;

/**
 *
 * @author edson
 */
public class ClienteListaController {

    private final ClienteService clienteService;
    Cliente cliente;

    public ClienteListaController() {
        clienteService = new ClienteService();
        cliente = new Cliente();
    }

    public void prencherTabela(ClienteListaInternal form) {

        ClienteTableModel modelo = new ClienteTableModel(clienteService.listarClientesComFIltro(form.getTxtPesquisa().getText(), true));
        form.getTabela().setModel(modelo);
        form.getTabela().getColumnModel().getColumn(0).setPreferredWidth(60);
        form.getTabela().getColumnModel().getColumn(0).setResizable(true);
        form.getTabela().getTableHeader().setReorderingAllowed(false);
//        jTable_Lista.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        form.getTabela().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        form.getTxtPesquisa().requestFocus();
    }

    public void limparLista(ClienteListaInternal form) {
        ClienteTableModel modelo = new ClienteTableModel(new ArrayList<>());
        form.getTabela().setModel(modelo);

        form.getTxtPesquisa().setText("");
        form.getTxtPesquisa().requestFocus();

    }

    public void selecionaUmCliente(ClienteListaInternal form, java.awt.event.MouseEvent evt) {

        if (evt.getButton() == MouseEvent.BUTTON1) {
            cliente = clienteService.obterClientePorId((int) form.getTabela().getValueAt(form.getTabela().getSelectedRow(), 0));
            if (evt.getClickCount() == 2) {
                try {
                    TelaInternaController.ClienteCadastroInternal(cliente);

                } catch (Exception e) {
                    System.out.println("erro|selecionaUmProduto::: " + e.getMessage());
                } 
            }
        }
    }
}
