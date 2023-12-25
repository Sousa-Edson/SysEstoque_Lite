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
import model.Nota;

public class NotaFiscalTableModel extends AbstractTableModel {

    private List<Nota> notas;
    private final String[] colunas = {"Id", "Natureza", "Documento", "  ", "Data", "Observação", "Registro", "Codigo"};

    public NotaFiscalTableModel() {
        this.notas = new ArrayList<>();
        // Adicione dados de exemplo se necessário
    }

    public void setUnidades(List<Nota> unidades) {
        this.notas = unidades;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return notas.size();
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
        Nota nota = notas.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return nota.getId_nota();
            case 1:
                return nota.getNatureza();
            case 2:
                return nota.getNota_documento();
            case 3:
                return nota.getNota_nota();
            case 4:
                return nota.getNota_data();
            case 5:
                return nota.getNota_observacao();
            case 6:
                return nota.getNota_registro();
            case 7:
                return nota.getId_referencia();

            default:
                return null;
        }
    }
}
