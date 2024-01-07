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
import model.Cliente;

public class ClienteTableModel extends AbstractTableModel {
    private final String[] colunas = {"Id", "Tipo", "Nome", "Descrição", "Telefone", "Observação", "Registro"};
    private final List<Cliente> listaClientes;

    public ClienteTableModel(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    @Override
    public int getRowCount() {
        return listaClientes.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente cliente = listaClientes.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return cliente.getCliente_id();
            case 1:
                return cliente.getCliente_tipo();
            case 2:
                return cliente.getCliente_nome();
            case 3:
                return cliente.getCliente_descricao();
            case 4:
                return cliente.getCliente_telefone();
            case 5:
                return cliente.getCliente_observacao();
            case 6:
                return cliente.getCliente_registro();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }
}
