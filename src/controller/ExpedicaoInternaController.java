/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ExpedicaoDao;
import java.util.List;
import javax.swing.ListSelectionModel;
import model.ExpedicaoModel;
import tableModel.ExpedicaoTableModel;
import view.internal.ExpedicaoInterna;

/**
 *
 * @author edson
 */
public class ExpedicaoInternaController {

    ExpedicaoDao expedicaoDao;

    public ExpedicaoInternaController() {
        expedicaoDao = new ExpedicaoDao();
    }

    public void preencheTabela(ExpedicaoInterna form) {
        ExpedicaoTableModel modelo = new ExpedicaoTableModel();
        List<ExpedicaoModel> expedicoes = expedicaoDao.listarExpedicoes();
        modelo.setMovimentos(expedicoes);
        form.getTabela().setModel(modelo);
        form.getTabela().getColumnModel().getColumn(0).setPreferredWidth(60);
        form.getTabela().getColumnModel().getColumn(0).setResizable(true);

        form.getTabela().getTableHeader().setReorderingAllowed(false);
 
        form.getTabela().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

}
