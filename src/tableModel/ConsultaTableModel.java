/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableModel;

/**
 *
 * @author edson
 */
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ConsultaTableModel extends AbstractTableModel {

    private final List<Object[]> data;
    private final List<String> columnNames;

    public ConsultaTableModel(List<Object[]> data, List<String> columnNames) {
        this.data = data;
        this.columnNames = columnNames;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return columnNames.get(column);
    }
    //{"#", "DATA","OPERAÇÃO","Situação", "Cliente","Documento","ID PRODUTO",  "Descrição", "Quantidade",  "Unidade","Complemento",  "Observação"};

}
