/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.text.JTextComponent;
import view.internal.ProdutoCadastroJIF;

/**
 *
 * @author edson
 */
public class ProdutoController {

    public  void limparCampos(ProdutoCadastroJIF form) {
        form.getTxtipo().setText("");
        form.getTxtDesc().setText("");
        form.getTxtEdicao().setText("");
        form.getTxtValor().setText("");
        form.getTxtObs().setText("");
        form.getSpNcm().setValue(0);
        form.getSpCfop().setValue(0);
        form.getSpEstoqueMinimo().setValue(0);
        form.getBtnExcluir().setEnabled(false);
        form.getTxtipo().requestFocus();
    }
  
    
     public void contaCaracteres(JTextComponent textComponent, JLabel label, int tamanho) {
        int contagem = textComponent.getText().length();
        if (contagem == tamanho) {
            textComponent.setBackground(Color.yellow);
            label.setText("Limite de caracteres " + contagem + "/" + tamanho);
        } else if (contagem > tamanho) {
            textComponent.setBackground(Color.red);
            System.out.println("maior " + contagem);
            label.setText("Ultrapassou limite de caracteres " + contagem + "/" + tamanho);
        } else {
            textComponent.setBackground(Color.white);
            System.out.println("caracteres " + contagem);
            label.setText("caracteres " + contagem + "/" + tamanho);
        }
    }
}
