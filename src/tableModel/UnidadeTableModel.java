/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableModel;

/**
 *
 * @author edson
 */
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import model.Unidade;

public class UnidadeTableModel extends AbstractTableModel {

    private List<Unidade> unidades;
    private final String[] colunas = {"Id", "Sigla", "Descrição", "Fragmentado", "Registro", "Usuario"};

    public UnidadeTableModel() {
        this.unidades = new ArrayList<>();
        // Adicione dados de exemplo se necessário
    }

    public void setUnidades(List<Unidade> unidades) {
        this.unidades = unidades;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return unidades.size();
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
        Unidade unidade = unidades.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return unidade.getId_unidade();
            case 1:
                return unidade.getSigla_unidade();
            case 2:
                return unidade.getDesc_unidade();
            case 3:
                return unidade.getFragmento_unidade();
            case 4:
                return unidade.getRegistro_unidade();
            case 5:
                return unidade.getUsuario_unidade();
           
            default:
                return null;
        }
    }
}
