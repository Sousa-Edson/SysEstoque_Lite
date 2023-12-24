/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import utils.DataHoraAtual;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.text.JTextComponent;
import model.Produto;
import service.ProdutoService;
import utils.FormatarDinheiro;
import utils.ManipulaValor;
import utils.UsuarioLogado;
import view.internal.ProdutoCadastroJIF;

/**
 *
 * @author edson
 */
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController() {
        produtoService = new ProdutoService();
    }

    public void limparCampos(ProdutoCadastroJIF form) {
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

    public void carregarCampos(ProdutoCadastroJIF form, Produto produto) {
        if (produto != null) {
            form.getTxtipo().setText(produto.getTipo_prod());
            form.getTxtDesc().setText(produto.getNome_prod());
            form.getTxtEdicao().setText(produto.getEdicao_prod());
            try {
                form.getTxtValor().setText(FormatarDinheiro.formatarDinheiro(produto.getValor()));
            } catch (Exception e) {
                form.getTxtValor().setText("");
            }

            form.getTxtObs().setText(produto.getObs_prod());
            int iNcm = 0;
            try {
                iNcm = Integer.parseInt(produto.getNcm_prod());
            } catch (Exception e) {
                System.out.println("e::" + e.getMessage());
            }
            form.getSpNcm().setValue(iNcm);
            int iCfop = 0;
            try {
                iCfop = Integer.parseInt(produto.getCfop_prod());
            } catch (Exception e) {
                System.out.println("e::" + e.getMessage());
            }
            form.getSpCfop().setValue(iCfop);
            form.getCbUnidade().setSelectedItem(produto.getUnidade().getSigla_unidade());
            form.getSpEstoqueMinimo().setValue(produto.getEstoque_prod());
            form.getBtnExcluir().setEnabled(false);
        } else {
            limparCampos(form);
        }
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

    public void manipulaValor(ProdutoCadastroJIF form) {
        form.getTxtValor().setText(ManipulaValor.exibeValorReal(form.getTxtValor().getText()));
    }

    public void salvar(ProdutoCadastroJIF form) {
        Produto produto = new Produto();
        produto.setCfop_prod(form.getSpCfop().getValue().toString());
        produto.setNcm_prod(form.getSpNcm().getValue().toString());
        double estoqueProduto = Double.parseDouble(form.getSpEstoqueMinimo().getValue().toString());
        produto.setEstoque_prod(estoqueProduto);
        produto.setTipo_prod(form.getTxtipo().getText());
        produto.setNome_prod(form.getTxtDesc().getText());
        produto.setEdicao_prod(form.getTxtEdicao().getText());
        double valor = Double.parseDouble(ManipulaValor.manipulaValor(form.getTxtValor().getText()));
        produto.setValor(valor);
        produto.setValor_ex(form.getTxtValor().getText());
        produto.setObs_prod(form.getTxtObs().getText());
        produto.setData_reg(DataHoraAtual.obterDataFormatada());
        produto.setHora_reg(DataHoraAtual.obterHoraFormatada());
        int unidade = Integer.parseInt(form.getCbUnidade_Int().getSelectedItem().toString());
        produto.setUn_prod(unidade);
        produto.setSaldo_prod(0.0);
        produto.setEstoque_prod(Double.valueOf(form.getSpEstoqueMinimo().getValue().toString()));
        produto.setStatus_prod(1);

        if (produtoService.salvarProduto(produto)) {
            limparCampos(form);
        }

    }

    public void chamaUnidade(ProdutoCadastroJIF aThis) {
        ControlaTelaInterna.ChamaCadastroUnidade();
    }

}
