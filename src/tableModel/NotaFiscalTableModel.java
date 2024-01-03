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
import model.NotaFiscal;

public class NotaFiscalTableModel extends AbstractTableModel {

    private List<NotaFiscal> notas;
    private final String[] colunas = {"Id", "Tipo", "Natureza", "Documento", "Cliente/Fornecedor ", "Data", "Observação", "Registro"};

    public NotaFiscalTableModel() {
        this.notas = new ArrayList<>();
        // Adicione dados de exemplo se necessário
    }

    public void setNotas(List<NotaFiscal> unidades) {
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
        NotaFiscal nota = notas.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return nota.getId_nota();
            case 1:
                return nota.getNota_operacao();
            case 2:
                return nota.getNatureza().getDesc_natureza();
            case 3:
                return nota.getNota_documento() + " " + nota.getNota_nota();
            case 4:
                return nota.getCliente().getCliente_nome();
            case 5:
                return nota.getNota_data();
            case 6:
                return nota.getNota_observacao();
            case 7:
                return nota.getNota_registro();

            default:
                return null;
        }
    }
}
