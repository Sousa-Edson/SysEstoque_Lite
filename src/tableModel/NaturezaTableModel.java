/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableModel;

/**
 *
 * @author edson
 */
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Natureza;

public class NaturezaTableModel extends AbstractTableModel {

    private final String[] colunas = {"Id", "Tipo", "Descrição", "Registro", "Usuario"};
    private List<Natureza> listaNaturezas;

    public NaturezaTableModel() {
        this.listaNaturezas = new ArrayList<>();
    }

    public void setNaturezas(List<Natureza> lista) {
        this.listaNaturezas = lista;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return listaNaturezas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Natureza natureza = listaNaturezas.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return natureza.getId_natureza();
            case 1:
                return natureza.getTipo_natureza();
            case 2:
                return natureza.getDesc_natureza();
            case 3:
                return natureza.getRegistro_natureza();
            case 4:
                return natureza.getUsuario_natureza();

            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }
}
