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
import model.Usuario;

public class UsuarioTableModel extends AbstractTableModel {
    private final String[] colunas = {"Codigo", "Tipo", "Descrição", "Registro", "Usuario", "Senha"};
    private final List<Usuario> listaUsuarios;

    public UsuarioTableModel(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    @Override
    public int getRowCount() {
        return listaUsuarios.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Usuario usuario = listaUsuarios.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return usuario.getId_referencia();
            case 1:
                return usuario.getSigla_usuario();
            case 2:
                return usuario.getDesc_usuario();
            case 3:
                return usuario.getRegistro_usuario();
            case 4:
                return usuario.getUsuario_usuario();
            case 5:
                return usuario.getSenha_usuario();
            default:
                return null;  
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }
}

