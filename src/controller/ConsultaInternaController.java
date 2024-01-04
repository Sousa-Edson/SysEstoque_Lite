/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ConsultaInternaDao;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import tableModel.ConsultaTableModel;
import view.internal.ConsultaInterna;

/**
 *
 * @author edson
 */
public class ConsultaInternaController {

    ConsultaInternaDao consultaInternaDao = new ConsultaInternaDao();

    public void preencherTabela(ConsultaInterna form) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        String operacaoString = "";
        if (form.getRbEntrada().isSelected()) {
            operacaoString = "ENTRADA";
        }
        if (form.getRbSaida().isSelected()) {
            operacaoString = "SAIDA";
        }
        if (form.getRbEntrada().isSelected() && form.getRbSaida().isSelected()) {
            operacaoString = "";
        }

        String dataInicioString = "";
        String dataFinalString = "";

        try {
            dataInicioString = formato.format(form.getDataInicial().getDate());
            dataFinalString = formato.format(form.getDataFinal().getDate());
        } catch (Exception e) {
        }

        String buscaString = "" + form.getTxtBuscar().getText();

        List<Object[]> movimentos = consultaInternaDao.getMovimentos(operacaoString, dataInicioString, dataFinalString, buscaString);
        List<String> columnNames = Arrays.asList("ID Nota", "Data ", "Nota Operação", "Situação", " Nota Documento", "Cliente", "Id produto",
                "Produto", "Unidade", "Quantidade", "Complemento", "Observação da nota");
        ConsultaTableModel model = new ConsultaTableModel(movimentos, columnNames);

        form.getTabela().setModel(model);

        form.getTxtBuscar().requestFocus();

    }

    public void limparTabela(ConsultaInterna form) {
        form.getTxtBuscar().requestFocus();
        form.getTxtBuscar().setText("");
        ArrayList movimentos = new ArrayList();
        ArrayList columnNames = new ArrayList();
        ConsultaTableModel model = new ConsultaTableModel(movimentos, columnNames);
        form.getTabela().setModel(model);
    }

}
