 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloBeans;

import static java.lang.System.err;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class ModeloTabela extends AbstractTableModel {

    private ArrayList linhas = null;
    private String[] colunas = null;

    public ModeloTabela(ArrayList lin, String[] col) {
        setLinhas(lin);
        setColunas(col);
    }

    public ModeloTabela() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the linhas
     */
    public ArrayList getLinhas() {
        return linhas;
    }

    /**
     * @param linhas the linhas to set
     */
    public void setLinhas(ArrayList linhas) {
        this.linhas = linhas;
    }

    /**
     * @return the colunas
     */
    public String[] getColunas() {
        return colunas;
    }

    /**
     * @param colunas the colunas to set
     */
    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }

    public int getColumnCount() {
        return colunas.length;
    }

    public int getRowCount() {
        return linhas.size();
    }

    public String getColumnName(int numCol) {
        return colunas[numCol];
    }

    public Object getValueAt(int numLin, int numCol) {
        try {
            Object[] linha = (Object[]) getLinhas().get(numLin);
            return linha[numCol];

        }
        catch(Exception err) {
        JOptionPane.showMessageDialog(null,  "try catch" + err);
        }
        return null;
    }

    public void setNumRows(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
