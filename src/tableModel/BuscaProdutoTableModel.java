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
import model.Produto;

public class BuscaProdutoTableModel extends AbstractTableModel {

    private List<Produto> produtos;
    private final String[] colunas = {"Id", "Produto", "Saldo", "Unid", "Valor", "Obs", "Registro"};

    public BuscaProdutoTableModel() {
        this.produtos = new ArrayList<>();
        // Adicione dados de exemplo se necessário
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return produtos.size();
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
        Produto produto = produtos.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return produto.getId_prod();
            case 1:
                return produto.getTipo_prod()+" "+produto.getNome_prod()+" "+produto.getEdicao_prod();
            case 2:
                return produto.getSaldo_prod();
            case 3:
                return produto.getUnidade().getSigla_unidade();
            case 4:
                return produto.getValor_ex();
            case 5:
                return produto.getObs_prod();
            case 6:
                return produto.getData_reg(); 
            default:
                return null;
        }
    }
}
