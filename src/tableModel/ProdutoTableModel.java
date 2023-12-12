/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Produto;

/**
 *
 * @author edson
 */
public class ProdutoTableModel extends AbstractTableModel {

    private List<Produto> produtos;
    private final String[] colunas = {"Id", "Status", "Produto", "Saldo", "Unid", "Valor Unit.", "Observação", "Registro", "Id Linha"};

    public ProdutoTableModel() {
        this.produtos = new ArrayList<>();
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
                return produto.getStatus_prod();
            case 2:
                return produto.getNome_prod();
            case 3:
                return produto.getSaldo_prod();
            case 4:
                return produto.getUn_prod();
            case 5:
                return produto.getValor();
            case 6:
                return produto.getObs_prod();
            case 7:
                return produto.getData_reg();
            case 8:
                return produto.getSis_prod(); // ajuste conforme necessário
            default:
                return null;
        }
    }
}
