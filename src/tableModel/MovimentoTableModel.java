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
import model.Movimento;
import utils.FormatarDinheiro;
import utils.FormatarNumero;

public class MovimentoTableModel extends AbstractTableModel {

    private List<Movimento> movimentos;
    private final String[] colunas = {"#", "Id", "Produto", "Unid", "Quantidade", "Complemento", "Valor", "Total"};

    public MovimentoTableModel() {
        this.movimentos = new ArrayList<>();
        // Adicione dados de exemplo se necessário
    }

    public void setMovimentos(List<Movimento> movimentos) {
        this.movimentos = movimentos;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return movimentos.size();
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
        Movimento movimento = movimentos.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return rowIndex+1;
            case 1:
                return movimento.getProduto().getId_prod();
            case 2:
                return movimento.getProduto().getTipo_prod() + " " + movimento.getProduto().getNome_prod() + " " + movimento.getProduto().getEdicao_prod() + "";
            case 3:
                return movimento.getProduto().getUnidade().getSigla_unidade();
            case 4:
                return FormatarNumero.formatarNumero(movimento.getQtd_mov());
            case 5:
                return movimento.getComplemento_mov();
            case 6:
                return FormatarDinheiro.formatarDinheiro(movimento.getValor_real());
            case 7:
                return FormatarDinheiro.formatarDinheiro(movimento.getQtd_mov() * movimento.getValor_real());
            default:
                return null;
        }
    }
}
