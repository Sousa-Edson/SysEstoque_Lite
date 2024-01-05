/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.ExpedicaoModel;
import utils.FormatarNumero;

/**
 *
 * @author edson
 */
public class ExpedicaoTableModel extends AbstractTableModel {

    private List<ExpedicaoModel> lista;
    private final String[] colunas = {"#", "Data", "Situação", "Cliente", "Documento", "Descrição", "Quantidade", "Unidade", "Complemento", "Observação"};

    public ExpedicaoTableModel() {
        this.lista = new ArrayList<>();
    }

    public void setMovimentos(List<ExpedicaoModel> listaExpedicao) {
        this.lista = listaExpedicao;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ExpedicaoModel expedicaoModel = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return expedicaoModel.getNotaFiscal().getId_nota();
            case 1:
                return expedicaoModel.getNotaFiscal().getNota_data();
            case 2:
                return expedicaoModel.getNotaFiscal().getNota_situacao();
            case 3:
                return expedicaoModel.getNotaFiscal().getCliente().getCliente_nome();
            case 4:
                return expedicaoModel.getNotaFiscal().getNota_documento() + " " + expedicaoModel.getNotaFiscal().getNota_nota();
            case 5:
                return expedicaoModel.getProduto().getTipo_prod() + " " + expedicaoModel.getProduto().getNome_prod() + " " + expedicaoModel.getProduto().getEdicao_prod() + "";
            case 6:
                return FormatarNumero.formatarNumero(expedicaoModel.getQtd_prod());
            case 7:
                return expedicaoModel.getProduto().getUnidade().getSigla_unidade();
            case 8:
                return expedicaoModel.getComplemento_mov();
            case 9:
                return expedicaoModel.getNotaFiscal().getNota_observacao();
            default:
                return null;
        }
    }
}
