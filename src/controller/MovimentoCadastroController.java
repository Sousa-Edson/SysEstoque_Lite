/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ProdutoDao;
import enums.SituacaoEnum;
import enums.TipoMovimentacao;
import view.dialog.JDialogBuscaProduto;
import view.dialog.JDialogComplementar;
import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Cliente;
import model.Item;
import model.Natureza;
import model.NotaFiscal;
import model.Produto;
import model.TransporteModel;
import service.ClienteService;
import service.ItemService;
import service.NaturezaService;
import service.NotaFiscalService;
import service.ProdutoService;
import tableModel.ItemTableModel;
import utils.ControleCores;
import utils.DataHoraAtual;
import utils.FormatarData;
import utils.FormatarDinheiro;
import utils.GeradorCodigoAleatorio;
import utils.LimiteCaracteres;
import utils.StringToDate;
import utils.ValidarHora;
import view.internal.NotaCadastroJIF;

/**
 *
 * @author edson
 */
public class MovimentoCadastroController {

    Color corPadrao;

    SimpleDateFormat formato;

    JDialogComplementar jDialogComplementar;
    JDialogBuscaProduto jDialogBuscaProduto;

    ProdutoService produtoService;
    NotaFiscalService notaFiscalService;

    NotaFiscal notaFiscal;
    Produto produto;

    public static List<Item> itens;

    public MovimentoCadastroController(NotaCadastroJIF form) {
        corPadrao = ControleCores.pegarCorPadrao();
        formato = new SimpleDateFormat("dd/MM/yyyy");

        jDialogBuscaProduto = new JDialogBuscaProduto(null, true);

        produtoService = new ProdutoService();
        notaFiscalService = new NotaFiscalService();

        notaFiscal = new NotaFiscal();

        itens = new ArrayList<>();

    }

    public void mudarCorPaineis(NotaCadastroJIF form) {
        form.getPnPrincipal().setBackground(corPadrao);
        form.getPnDados().setBackground(corPadrao);
        form.getPnTransporte().setBackground(corPadrao);
        form.getPnTopo().setBackground(corPadrao);
        form.getPnInformacao().setBackground(corPadrao);

//        form.getBtnExcluir().setVisible(false);
//        NotaCadastroJIF.getBtnExcluir().setVisible(false);
    }

    public void carregarNatureza(NotaCadastroJIF form) {
        NaturezaService naturezaService = new NaturezaService();
        List<Natureza> listaNaturezas = naturezaService.listarNaturezasAtivas();
        form.getCbNatureza().removeAllItems();
        for (Natureza natureza : listaNaturezas) {
            form.getCbNatureza().addItem(natureza);
        }
    }

    public void carregarCliente(NotaCadastroJIF form) {
        ClienteService clienteService = new ClienteService();
        List<Cliente> listaClientes = clienteService.listarClientes(false);
        form.getCbCliente().removeAllItems();
        for (Cliente cliente : listaClientes) {
            form.getCbCliente().addItem(cliente);
        }
    }

    public void dataAtual(NotaCadastroJIF form) {
        if (form.getDataNota().getDate() == (null)) {
            try {
                form.getDataNota().setDate(formato.parse(DataHoraAtual.obterDataFormatada()));
            } catch (ParseException ex) {
                System.err.println("erro::" + ex.getMessage());
            }
        } else {
            form.getDataNota().setDate(null);
        }
    }

    public void horaAtual(NotaCadastroJIF form) {
        if (form.getTxtHora().getText().trim().isEmpty()) {
            form.getTxtHora().setText(DataHoraAtual.obterHoraFormatada());
        } else {
            form.getTxtHora().setText(null);
        }
    }

    public void chamaFormularioComplementar(NotaCadastroJIF form) {
        if (form.getIdProdutoComun() != 0) {
            jDialogComplementar = new JDialogComplementar(null, true);
            produto = new Produto();
            produto = produtoService.obterProdutoPorId(form.getIdProdutoComun());
            jDialogComplementar.recebeProduto(produto);
            jDialogComplementar.setVisible(true);
        } else {
            System.err.println("id produto vazio!");
        }
        prencherTabela(form);
        limparBuscarUmProdutoPorNome(form);
    }

    public void chamaFormularioBuscaProduto(NotaCadastroJIF form) {
        jDialogBuscaProduto.getTxtBusca().setText(NotaCadastroJIF.getTxtBuscarUmProdutoPorNomeStatic().getText().toUpperCase());
        jDialogBuscaProduto.getBtnBuscar().doClick();
        jDialogBuscaProduto.setVisible(true);
    }

    public void limparBuscarUmProdutoPorNome(NotaCadastroJIF form) {
        form.getTxtBuscarUmProdutoPorNomeComun().setText("");
        form.setIdProdutoComun(0);
        form.getTxtBuscarUmProdutoPorNomeComun().requestFocus();
    }

    public void limparTabela(NotaCadastroJIF form) {
        ItemTableModel modelo = new ItemTableModel();
        modelo.setMovimentos(new ArrayList<>());
        form.getTabela().setModel(modelo);
    }

    public void validarNomeProduto(NotaCadastroJIF form, java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == evt.VK_F12) {
//            Complementar.PreencherSetor();

            //            CD.RecebeDadosMovimentoAlterado(MinhaNatureza, jLabel_Complemento.getText(), jLabel_Setor.getText());
//            Complementar.VerificaModo(MinhaNatureza);
//            Complementar.RecebeDados(jLabel_Complemento.getText(), jLabel_Setor.getText(), jLabel_Calculado.getText());
//            jDialogComplementar.setVisible(true);
        }
        if (evt.getKeyCode() == evt.VK_F2) {
            chamaFormularioBuscaProduto(form);

        }
        if (evt.getKeyCode() == evt.VK_ENTER) {
//            EventoBuscaProduto();
            if (form.getIdProdutoComun() != 0) {
                form.getBtnInserirProduto().requestFocus();
            } else {
                try {
                    form.setIdProdutoComun(Integer.parseInt(form.getTxtBuscarUmProdutoPorNomeComun().getText()));
                    produto = produtoService.obterProdutoPorId(form.getIdProdutoComun());
                    form.getTxtBuscarUmProdutoPorNomeComun().setText(produto.getTipo_prod() + " " + produto.getNome_prod() + " " + produto.getEdicao_prod());
                    form.getBtnInserirProduto().requestFocus();

                } catch (Exception e) {
                    System.out.println("Não é numero:: " + e.getMessage());
                    chamaFormularioBuscaProduto(form);
                }

            }
        }
    }

    public void prencherTabela(NotaCadastroJIF form) {

        try {
            ItemTableModel modelo = new ItemTableModel();
            modelo.setMovimentos(itens);
            form.getTabela().setModel(modelo);
            form.getTabela().getColumnModel().getColumn(0).setPreferredWidth(60);
            form.getTabela().getColumnModel().getColumn(0).setResizable(true);
            form.getTabela().getTableHeader().setReorderingAllowed(false);
//        form.getTabela().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        } catch (Exception e) {
            System.err.println("\n\nerro prencherTabela:: " + e.getMessage());
        }
//        System.out.println("\n\n##### aqui--- prencherTabela:: " + itens.size());
    }

    public static void recebeProduto(Produto produto) {
        NotaCadastroJIF.getTxtBuscarUmProdutoPorNomeStatic().setText(produto.getTipo_prod() + " " + produto.getNome_prod() + " " + produto.getEdicao_prod() + "");
        NotaCadastroJIF.setIdProduto(produto.getId_prod());
    }

    public void salvarNota(NotaCadastroJIF form) {

        if (notaFiscal.getId_nota() != 0) {

            Object[] options = {"Confirmar", "Cancelar"};
            if (JOptionPane.showOptionDialog(null, "Deseja realmente gerar uma cópia de  #" + notaFiscal.getId_nota(),
                    "Aviso", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                    null, options, options[1]) == 0) {
                // Gerar um código aleatório
                String codigoDeConfirmacaoAleatorio = GeradorCodigoAleatorio.gerarCodigoAleatorio(4);
                // Exibir um JOptionPane para obter o código
                String codigoDeConfirmacao = JOptionPane.showInputDialog(null,
                        "Insira o código de 4 caracteres: " + codigoDeConfirmacaoAleatorio + "  ",
                        "Confirmação", JOptionPane.WARNING_MESSAGE);
                // Comparar os códigos
                if (codigoDeConfirmacao != null && codigoDeConfirmacao.length() == 4
                        && codigoDeConfirmacao.equals(codigoDeConfirmacaoAleatorio)) {
                    notaFiscal.setId_nota(0);
                    form.getBtnExcluir().setEnabled(false);
                    form.getBtnSalvar().setText("Salvar");
                    JOptionPane.showMessageDialog(null, "Cópia gerada com sucesso.");
                    iniciandoFormulario(form, true);
                } else {
                    JOptionPane.showMessageDialog(null, "Código incorreto ou invalido.");
                }
            }
        } else {

            if (form.getTxtNota().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Campo nota não pode estar vazio!");
            } else if (form.getDataNota().getDate() == null) {
                JOptionPane.showMessageDialog(null, "Campo data não pode estar vazio!");
            } else if (itens.size() <= 0) {
                JOptionPane.showMessageDialog(null, "Não exitem itens a serem salvos!");
            } else {

                notaFiscal.setNota_observacao(LimiteCaracteres.limitarString(form.getTxtAreaObservacao().getText().toUpperCase(), 300));
                notaFiscal.setNota_nota(LimiteCaracteres.limitarString(form.getTxtNota().getText().toUpperCase(), 30));
                notaFiscal.setNota_chave(LimiteCaracteres.limitarString(form.getTxtChave().getText().toUpperCase(), 60));

                notaFiscal.setCliente((Cliente) form.getCbCliente().getSelectedItem());
                notaFiscal.setNatureza((Natureza) form.getCbNatureza().getSelectedItem());

                notaFiscal.setNota_hora(ValidarHora.validarHoraRetorno(form.getTxtHora().getText()));
                notaFiscal.setNota_data(FormatarData.formatarData(form.getDataNota().getDate()));
                notaFiscal.setDatavariavel(FormatarData.formatarData(form.getDataNota().getDate()));

                notaFiscal.setNota_situacao(SituacaoEnum.CALCULADO.getNome());// criar um enum para isso       
                notaFiscal.setNota_operacao("" + (TipoMovimentacao) form.getCbTipoMovimentacao().getSelectedItem());// criar um enum para isso
                System.out.println("OP:: " + notaFiscal.getNota_operacao());

                TransporteModel transporteModel = new TransporteModel();
                transporteModel.setMotorista(LimiteCaracteres.limitarString(form.getTxtMotorista().getText().toUpperCase(), 160));
                transporteModel.setPlaca(LimiteCaracteres.limitarString(form.getTxtPlaca().getText().toUpperCase(), 10));
                transporteModel.setUf(LimiteCaracteres.limitarString(form.getTxtUf().getText().toUpperCase(), 10));
                transporteModel.setQuantidade(LimiteCaracteres.limitarString(form.getTxtVolQuantidade().getText().toUpperCase(), 100));
                transporteModel.setPesobruto(LimiteCaracteres.limitarString(form.getTxtPesoBruto().getText().toUpperCase(), 100));
                transporteModel.setPesoliquido(LimiteCaracteres.limitarString(form.getTxtPesoLiquido().getText().toUpperCase(), 100));
                transporteModel.setNumeracao(LimiteCaracteres.limitarString(form.getTxtVolNumeracao().getText().toUpperCase(), 100));
                transporteModel.setEspecie(LimiteCaracteres.limitarString(form.getTxtVolEspecie().getText().toUpperCase(), 100));

                notaFiscal.setTransporteModel(transporteModel);

                double totalDaNota = 0.0;
                for (Item iten : itens) {
                    double totalDoItem = iten.getQtd_prod() * iten.getValor_real();
                    totalDaNota = totalDaNota + totalDoItem;

                }
                notaFiscal.setNota_total(FormatarDinheiro.formatarDinheiro(totalDaNota));

                notaFiscal.setItens(itens);
                System.out.println("###\n\n" + notaFiscal);

                System.out.println("itens.size():: " + itens.size());
                notaFiscalService.adicionarNotaFiscal(notaFiscal);
                form.dispose();
            }
        }
    }

    public void carregarTipoMovimentacao(NotaCadastroJIF form) {
        form.getCbTipoMovimentacao().addItem(TipoMovimentacao.ENTRADA);
        form.getCbTipoMovimentacao().addItem(TipoMovimentacao.SAIDA);

    }

    public void limparCampos(NotaCadastroJIF form) {
        form.getTxtNota().setText("");
        form.getTxtChave().setText("");
        form.getTxtHora().setText("");
        form.getTxtNota().setText("");
        form.getDataNota().setDate(null);
        form.getTxtAreaObservacao().setText("");

        form.getTxtMotorista().setText("");
        form.getTxtPlaca().setText("");
        form.getTxtUf().setText("");

        form.getTxtVolEspecie().setText("");
        form.getTxtVolNumeracao().setText("");
        form.getTxtVolQuantidade().setText("");
        form.getTxtPesoBruto().setText("");
        form.getTxtPesoLiquido().setText("");

        form.getBtnExcluir().setEnabled(false);
        form.getBtnSalvar().setText("Salvar");
        notaFiscal = null;

        iniciandoFormulario(form, true);

    }

    public void carregarNotaFiscal(NotaCadastroJIF form, NotaFiscal notaFiscal) {
        if (notaFiscal != null) {
            this.notaFiscal = notaFiscal;
            itens = notaFiscal.getItens();
            System.out.println("notaFiscal.getId_nota():: " + notaFiscal.getId_nota());
            form.getTxtNota().setText(notaFiscal.getNota_nota());
            form.getTxtChave().setText(notaFiscal.getNota_chave());
            form.getTxtHora().setText(notaFiscal.getNota_hora());
            form.getDataNota().setDate(StringToDate.deStringParaData(notaFiscal.getNota_data()));
            form.getTxtAreaObservacao().setText(notaFiscal.getNota_observacao());

            form.getTxtMotorista().setText(notaFiscal.getTransporteModel().getMotorista());
            form.getTxtPlaca().setText(notaFiscal.getTransporteModel().getPlaca());
            form.getTxtUf().setText(notaFiscal.getTransporteModel().getUf());

            form.getTxtVolEspecie().setText(notaFiscal.getTransporteModel().getEspecie());
            form.getTxtVolNumeracao().setText(notaFiscal.getTransporteModel().getNumeracao());
            form.getTxtVolQuantidade().setText(notaFiscal.getTransporteModel().getQuantidade());
            form.getTxtPesoBruto().setText(notaFiscal.getTransporteModel().getPesobruto());
            form.getTxtPesoLiquido().setText(notaFiscal.getTransporteModel().getPesoliquido());

            ClienteService clienteService = new ClienteService();
            List<Cliente> listaClientes = clienteService.listarClientes(false);
            for (int i = 0; i < listaClientes.size(); i++) {
                Cliente clienteNoComboBox = listaClientes.get(i);
                if (clienteNoComboBox.equals(notaFiscal.getCliente())) {
                    form.getCbCliente().setSelectedItem(clienteNoComboBox);
                    break;
                }
            }

            NaturezaService naturezaService = new NaturezaService();
            List<Natureza> listaNaturezas = naturezaService.listarNaturezas();
            for (int i = 0; i < listaNaturezas.size(); i++) {
                Natureza naturezaNoComboBox = listaNaturezas.get(i);
                if (naturezaNoComboBox.equals(notaFiscal.getNatureza())) {
                    form.getCbNatureza().setSelectedItem(naturezaNoComboBox);
                    break;
                }
            }

            form.getCbTipoMovimentacao().setSelectedItem(TipoMovimentacao.valueOf(notaFiscal.getNota_operacao()));
            ItemService itemService = new ItemService();
            itens = itemService.obterItennPorIdNota(notaFiscal.getId_nota());
            prencherTabela(form);
            form.getBtnExcluir().setEnabled(true);
            form.getBtnSalvar().setText("Gerar cópia");

            iniciandoFormulario(form, false);
        } else {
            iniciandoFormulario(form, true);
        }

    }

    public void excluirNota(NotaCadastroJIF form) {
        Object[] options = {"Confirmar", "Cancelar"};
        if (JOptionPane.showOptionDialog(null, "Deseja realmente deletar #" + notaFiscal.getId_nota(),
                "Aviso", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[1]) == 0) {

            String codigoDeConfirmacaoAleatorio = GeradorCodigoAleatorio.gerarCodigoAleatorio(4);

            String codigoDeConfirmacao = JOptionPane.showInputDialog(null,
                    "Insira o código de 4 caracteres: " + codigoDeConfirmacaoAleatorio + "  ",
                    "Confirmação", JOptionPane.WARNING_MESSAGE);

            if (codigoDeConfirmacao != null && codigoDeConfirmacao.length() == 4
                    && codigoDeConfirmacao.equals(codigoDeConfirmacaoAleatorio)) {

                form.getBtnExcluir().setEnabled(false);
                form.getBtnSalvar().setText("Salvar");
                if (notaFiscalService.excluirNota(notaFiscal.getId_nota())) {
                    notaFiscal.setId_nota(0);
                    form.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Código incorreto ou invalido.");
            }

        }

    }

    int selecionaIndex = 0;

    public void removerUmItem(NotaCadastroJIF form) {
        itens.remove(selecionaIndex);
        selecionaIndex = 0;
        prencherTabela(form);
        form.getBtnRemoverUmProduto().setEnabled(!true);
    }

    public void selecionarUmItem(NotaCadastroJIF form, java.awt.event.MouseEvent evt) {
        if (evt.getButton() == evt.BUTTON1) {
            selecionaIndex = (Integer) form.getTabela().getSelectedRow();
            form.getBtnRemoverUmProduto().setEnabled(true);
        }
    }

    private void iniciandoFormulario(NotaCadastroJIF form, boolean ativo) {

        form.getLblProduto().setVisible(ativo);
        form.getTxtBuscarUmProdutoPorNomeComun().setVisible(ativo);
        form.getBtnInserirProduto().setVisible(ativo);
        form.getBtnLimparCampoBuscaProduto().setVisible(ativo);
        form.getBtnRemoverUmProduto().setVisible(ativo);

        form.getBtnHoraAtual().setVisible(ativo);
        form.getBtnDataAtual().setVisible(ativo);

        form.getTabela().setEnabled(ativo);

        form.getCbCliente().setEnabled(ativo);
        form.getCbNatureza().setEnabled(ativo);
        form.getCbTipoMovimentacao().setEnabled(ativo);

        form.getTxtHora().setEnabled(ativo);
        form.getDataNota().setEnabled(ativo);
        form.getTxtNota().setEnabled(ativo);
        form.getTxtChave().setEnabled(ativo);

        form.getTxtAreaObservacao().setEnabled(ativo);

        form.getTxtMotorista().setEnabled(ativo);
        form.getTxtPlaca().setEnabled(ativo);
        form.getTxtUf().setEnabled(ativo);

        form.getTxtVolEspecie().setEnabled(ativo);
        form.getTxtVolNumeracao().setEnabled(ativo);
        form.getTxtVolQuantidade().setEnabled(ativo);
        form.getTxtPesoBruto().setEnabled(ativo);
        form.getTxtPesoLiquido().setEnabled(ativo);

    }
}
