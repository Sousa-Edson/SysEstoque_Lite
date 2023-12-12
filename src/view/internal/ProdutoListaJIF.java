/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.internal;

import ConectaBanco.ConexaoBD;
import Consulta.FrameExibirImagem;
import Interface.Principal;
import static Interface.Principal.jButton1;
import ModeloBeans.ModeloTabela;
import Sistema.ClassChamaCadastroProduto;
import UTIL.ControleCores;
import controller.ProdutoListaController;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 *
 * @author edson
 */
public class ProdutoListaJIF extends javax.swing.JInternalFrame {
    
    ProdutoListaController produtoListaController = new ProdutoListaController();
    
    ConexaoBD conex = new ConexaoBD();
    ConexaoBD conexSaldo = new ConexaoBD();
    ClassChamaCadastroProduto tela = new ClassChamaCadastroProduto();
    FrameExibirImagem imagem = new FrameExibirImagem();
    Principal menu;
    String UnProd;
    String SelecionaProdutoReferencia;
    String SelecionaProduto = null, SelecionaProdutoNome, SelecionaProdutoId;
    
    public ProdutoListaJIF() {
        initComponents();
        remover_Ico();
        
        pnPrincipal.setBackground(ControleCores.pegarCorPadrao());
        // setPosicao();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnPrincipal = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Produto = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jCheckBoxMenuItem_Exibe_Imagens = new javax.swing.JCheckBoxMenuItem();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Lista produto");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        pnPrincipal.setBackground(new java.awt.Color(0, 255, 204));

        jTable_Produto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable_Produto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_ProdutoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Produto);

        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-lupa-24.png"))); // NOI18N
        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-cancelar-24.png"))); // NOI18N
        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-novo-24.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-lápis-24.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.setEnabled(false);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnPrincipalLayout = new javax.swing.GroupLayout(pnPrincipal);
        pnPrincipal.setLayout(pnPrincipalLayout);
        pnPrincipalLayout.setHorizontalGroup(
            pnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 989, Short.MAX_VALUE)
                    .addGroup(pnPrincipalLayout.createSequentialGroup()
                        .addComponent(btnNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesquisar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLimpar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditar)))
                .addContainerGap())
        );

        pnPrincipalLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnEditar, btnLimpar, btnNovo, btnPesquisar});

        pnPrincipalLayout.setVerticalGroup(
            pnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisar)
                    .addComponent(btnLimpar)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnPrincipalLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnEditar, btnLimpar, btnNovo, btnPesquisar, txtBuscar});

        jMenu1.setText("Arquivo");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Exibir");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });

        jCheckBoxMenuItem_Exibe_Imagens.setText("Apenas com imagens");
        jCheckBoxMenuItem_Exibe_Imagens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem_Exibe_ImagensActionPerformed(evt);
            }
        });
        jMenu2.add(jCheckBoxMenuItem_Exibe_Imagens);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed

//        if (jCheckBoxMenuItem_Exibe_Imagens.isSelected()) {
//            EventoBuscaExibirImagens();
//
//        } else {
//            EventoBuscar();
//        }
        produtoListaController.preencheTabela(this);
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        produtoListaController.limparPesquisaProdutoLista(this);
    }//GEN-LAST:event_btnLimparActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        if (evt.getKeyCode() == evt.VK_ENTER) {
            // EventoBuscar();
            btnPesquisar.doClick();
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        this.setVisible(false);
    }//GEN-LAST:event_formInternalFrameClosing

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
//        BuscaUltimaId();
        btnEditar.setEnabled(false);
        Principal.jLabelCodigoTela2.setText("novo");
        Principal.jLabelCodigoTela.setText("CadastroProduto");
        Principal.jButton1.doClick();

    }//GEN-LAST:event_btnNovoActionPerformed

    private void jTable_ProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_ProdutoMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {
            SelecionaProdutoNome = "" + jTable_Produto.getValueAt(jTable_Produto.getSelectedRow(), 1);
            SelecionaProdutoId = "" + jTable_Produto.getValueAt(jTable_Produto.getSelectedRow(), 0);
            int resposta = 0;
            resposta = JOptionPane.showConfirmDialog(rootPane, "Deseja realmente fazer varredura de saldo do produto '" + SelecionaProdutoNome + "' ? ");
            if (resposta == JOptionPane.YES_OPTION) {
                ExecutaSaldo();
            }
//           
        } else {
            btnEditar.setEnabled(true);
            String TipoUsuario = Principal.jLabelTipoUsuario.getText();
            int Clique = 1;
            String NomeProduto;
            SelecionaProdutoReferencia = "" + jTable_Produto.getValueAt(jTable_Produto.getSelectedRow(), 0);
            if (TipoUsuario == "Manutenção") {
                if (jCheckBoxMenuItem_Exibe_Imagens.isSelected()) {
                    NomeProduto = ("" + jTable_Produto.getValueAt(jTable_Produto.getSelectedRow(), 1));
                    txtBuscar.setText(NomeProduto);
                } else {
                    NomeProduto = ("" + jTable_Produto.getValueAt(jTable_Produto.getSelectedRow(), 2));
                    txtBuscar.setText(NomeProduto);
                    SelecionaProduto = "" + jTable_Produto.getValueAt(jTable_Produto.getSelectedRow(), 8);
                    System.out.println("seleciona produto " + SelecionaProduto);
                }
                if (evt.getClickCount() == 2) {
//                    Principal.jLabelCodigoTela2.setText(SelecionaProduto);
//                    Principal.jLabelCodigoTela.setText("CadastroProdutoEditar");
//                    Principal.jButton1.doClick();
//                    jTextFieldBusca.setText("");
                }
            } else {
                NomeProduto = ("" + jTable_Produto.getValueAt(jTable_Produto.getSelectedRow(), 1));
                txtBuscar.setText(NomeProduto);
                SelecionaProduto = "" + jTable_Produto.getValueAt(jTable_Produto.getSelectedRow(), 7);
                System.out.println("seleciona produto " + SelecionaProduto);
                if (evt.getClickCount() == 2) {
//                    Principal.jLabelCodigoTela2.setText(SelecionaProduto);
//                    Principal.jLabelCodigoTela.setText("CadastroProdutoEditar");
//                    Principal.jButton1.doClick();
//                    jTextFieldBusca.setText("");

                    imagem.jLabel_Nome_Produto.setText(NomeProduto);
                    imagem.jLabel_Id_Produto.setText(SelecionaProdutoReferencia);
                    imagem.preencherTabela();
                    imagem.setVisible(true);
                }
            }
        }

    }//GEN-LAST:event_jTable_ProdutoMouseClicked

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        System.out.println("SelecionaProduto " + SelecionaProduto);
//        if (SelecionaProduto.equals(null)) {
//        } else 
        if (SelecionaProduto == null) {///SelecionaProduto.isEmpty() | 
            System.out.println("a");
        } else {
            System.out.println("b");
            Principal.jLabelCodigoTela2.setText(SelecionaProduto);
            Principal.jLabelCodigoTela.setText("CadastroProdutoEditar");
            Principal.jButton1.doClick();
        }
        btnEditar.setEnabled(false);
    }//GEN-LAST:event_btnEditarActionPerformed

    private void jCheckBoxMenuItem_Exibe_ImagensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem_Exibe_ImagensActionPerformed
        if (jCheckBoxMenuItem_Exibe_Imagens.isSelected()) {
            EventoBuscaExibirImagens();
            // jTextFieldBusca
        } else {
            btnPesquisar.doClick();
        }

    }//GEN-LAST:event_jCheckBoxMenuItem_Exibe_ImagensActionPerformed

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu2MouseClicked

//    public void EventoLimpar() {
//        SelecionaProduto = null;
//        txtBuscar.setText("");
//        txtBuscar.requestFocus();
//        ArrayList dados = new ArrayList();
//        String[] colunas = new String[]{};
//        ModeloTabela modelo = new ModeloTabela(dados, colunas);
//        jTable_Produto.setModel(modelo);
//
//    }
    public void EventoBuscaExibirImagens() {
        String MinhaBusca = txtBuscar.getText();
//        if (jRadioButtonOrdem.isSelected()) {
//            preencherTabela("select * from produto\n"
//                    + "inner join unidade on  idunid=id_referenciaunidade \n"
//                    + "inner join imagemproduto on produtoid = sis_prod\n"
//                    + "where (coalesce((sis_prod))||' '||coalesce(UPPER(tipo_prod)) ||' '|| "
//                    + "coalesce(UPPER(nome_prod)) ||' '||coalesce(UPPER(edicao_prod)) ||' '||"
//                    + " coalesce(UPPER(obs_prod)) ) "
//                    + "ilike '%" + MinhaBusca + "%' and stunid =1 "
//                    + "  and stprod =1 \n"
//                    + "order by sis_prod  desc  ,id_prod  desc");
//        } else {
        preencherTabela("select * from produto\n"
                + "inner join unidade on  idunid=id_referenciaunidade \n"
                + "inner join imagemproduto on produtoid = sis_prod\n"
                + "where (coalesce((sis_prod))||' '||coalesce(UPPER(tipo_prod)) ||' '|| "
                + "coalesce(UPPER(nome_prod)) ||' '||coalesce(UPPER(edicao_prod)) ||' '||"
                + " coalesce(UPPER(obs_prod)) ) "
                + "ilike '%" + MinhaBusca + "%' and stunid =1 "
                + "  and stprod =1 \n"
                + "order by sis_prod  asc  ,id_prod  asc");
//        }
    }
    
    public void EventoBuscar() {
        btnEditar.setEnabled(false);
        String MinhaBusca = txtBuscar.getText(), situacao = "", MostraTabela = "1";//ATIVO
        String TipoUsuario = Principal.jLabelTipoUsuario.getText();
//        if (TipoUsuario == "Manutenção") {
//            if (jRadioButtonOrdem.isSelected()) {
//                preencherTabela2("select * from produto "
//                        + "inner join unidade on  idunid=id_referenciaunidade "
//                        + "where (coalesce((id_prod)) ||' '||coalesce((sis_prod))||' '||coalesce(UPPER(tipo_prod)) ||' '|| "
//                        + "coalesce(UPPER(nome_prod))||' '||coalesce(UPPER(edicao_prod)) ||' '||"
//                        + " coalesce(UPPER(obs_prod))||' '|| coalesce(UPPER(usu_prod)) ||' '|| coalesce((data_reg)) ) "
//                        + "ilike '%" + MinhaBusca + "%' and stunid =1  "
//                        + "order by sis_prod  desc  ,id_prod  desc");//and stprod =" + MostraTabela + "
//            } else {
        preencherTabela2("select * from produto "
                + "inner join unidade on  idunid=id_referenciaunidade "
                + "where (coalesce((id_prod)) ||' '||coalesce((sis_prod))||' '||coalesce(UPPER(tipo_prod)) ||' '|| "
                + "coalesce(UPPER(nome_prod)) ||' '||coalesce(UPPER(edicao_prod)) ||' '||"
                + " coalesce(UPPER(obs_prod))||' '|| coalesce(UPPER(usu_prod))  ||' '|| coalesce((data_reg)) ) "
                + "ilike '%" + MinhaBusca + "%' and stunid =1  "
                + "order by sis_prod  asc  ,id_prod  asc");// and stprod =" + MostraTabela + "
//            }
//        } else {
//            if (jRadioButtonOrdem.isSelected()) {
//                preencherTabela("select * from produto "
//                        + "inner join unidade on  idunid=id_referenciaunidade "
//                        + "where (coalesce((id_prod)) ||' '||coalesce((sis_prod))||' '||coalesce(UPPER(tipo_prod)) ||' '|| "
//                        + "coalesce(UPPER(nome_prod))||' '||coalesce(UPPER(edicao_prod)) ||' '||"
//                        + " coalesce(UPPER(obs_prod))||' '|| coalesce(UPPER(usu_prod)) ||' '|| coalesce((data_reg)) ) "
//                        + "ilike '%" + MinhaBusca + "%' and stprod =" + MostraTabela + " and stunid =1 "
//                        + "order by sis_prod  desc  ,id_prod  desc");
//            } else {
//                preencherTabela("select * from produto "
//                        + "inner join unidade on  idunid=id_referenciaunidade "
//                        + "where (coalesce((id_prod)) ||' '||coalesce((sis_prod))||' '||coalesce(UPPER(tipo_prod)) ||' '|| "
//                        + "coalesce(UPPER(nome_prod)) ||' '||coalesce(UPPER(edicao_prod)) ||' '||"
//                        + " coalesce(UPPER(obs_prod))||' '|| coalesce(UPPER(usu_prod))  ||' '|| coalesce((data_reg)) ) "
//                        + "ilike '%" + MinhaBusca + "%' and stunid =1  and stprod =" + MostraTabela + " "
//                        + "order by sis_prod  asc  ,id_prod  asc");
//            }
//        }

        txtBuscar.requestFocus();
    }
    
    public void BuscaLimpa() {
        
        preencherTabela3("select * from produto "
                + "inner join unidade on  idunid=id_referenciaunidade "
                + "where  stunid =1 and stprod =1 "
                + "order by sis_prod  desc  ,id_prod  desc");
        System.out.println("Interface.ProdutoListaJIF.BuscaLimpa()");
    }
    
    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }
    
    public void remover_Ico() {
        this.setFrameIcon(null);

        // hack to remove system menu in Windows
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        Container north = (Container) ui.getNorthPane();
        north.remove(0);
        north.validate();
        north.repaint();
    }
    
    public void preencherTabela(String Sql) {
        String descricao;
        String edicao;
        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{"Id", "Produto", "Saldo", "Unid", "Valor Unit.", "Observação", "Registro", "Id Linha"};
        conex.conexao();
        conex.executaSql(Sql);
        try {
            conex.rs.first();
            do {
                edicao = conex.rs.getString("edicao_prod");
                
                String datahora = conex.rs.getString("usu_prod") + " " + conex.rs.getString("data_reg") + " " + conex.rs.getString("hora_reg");
                if (edicao.equals(null) | edicao.equals("") | edicao.equals(" ")) {
                    edicao = "";
                } else {
                    edicao = " N° " + edicao;
                }
                
                descricao = " " + conex.rs.getString("tipo_prod") + " " + conex.rs.getString("nome_prod") + edicao;
                descricao = descricao.toUpperCase();
                Double num4 = (conex.rs.getDouble("valor_prod"));
                BigDecimal df = new BigDecimal(num4);
                NumberFormat nf = NumberFormat.getInstance();// getCurrencyInstance
                nf.setMinimumFractionDigits(3);
                nf.setMaximumFractionDigits(3);
                String FormatoValorProd = nf.format(df);
                FormatoValorProd = "R$ " + FormatoValorProd;
//                String VReal = ("" + FormatoReal);
                // Double VReal = (conex.rs.getDouble("valor_prod")); //valor_prod_ex
                //nf.setMinimumFractionDigits(4);
//                String SaldoDouble = String.valueOf(conex.rs.getDouble("saldo_prod"));
                Double num5 = (conex.rs.getDouble("saldo_prod"));
                BigDecimal df5 = new BigDecimal(num5);
                NumberFormat nf5 = NumberFormat.getInstance();
                nf5.setMinimumFractionDigits(3);
                nf5.setMaximumFractionDigits(3);
                String FormatoReal5 = nf5.format(df5);
                FormatoReal5 = FormatoReal5.replace(",000", "");
//                SaldoDouble = SaldoDouble.replace(".", ",");
                int stprod = conex.rs.getInt("stprod");
                String status_prod;
                if (stprod == 1) {
                    status_prod = "ATIVO";
                } else if (stprod == 2) {
                    status_prod = "ALTERADO";
                } else if (stprod == 3) {
                    status_prod = "EXCLUIDO";
                } else {
                    status_prod = "INDEFINIDO";
                }
                UnProd = conex.rs.getString("sigla_unidade");
                String valor_prod_ex = (conex.rs.getString("valor_prod_ex"));
                dados.add(new Object[]{conex.rs.getString("sis_prod"), descricao,
                    FormatoReal5, UnProd, valor_prod_ex,//FormatoValorProd,
                    //                    conex.rs.getString("ncm_prod"), conex.rs.getString("cfop_prod"),
                    conex.rs.getString("obs_prod"),
                    datahora, conex.rs.getInt("id_prod")});
                
            } while (conex.rs.next());
        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(menu, "preenchertabela\n" + ex);
            JOptionPane.showMessageDialog(menu, "Produto não encontrado ...\nBusque novamente !");
            BuscaLimpa();
        }
        ModeloTabela modelo = new ModeloTabela(dados, colunas);
        jTable_Produto.setBackground(null);
        jTable_Produto.setModel(modelo);
        jTable_Produto.getColumnModel().getColumn(0).setPreferredWidth(40);
        jTable_Produto.getColumnModel().getColumn(0).setResizable(true);
        jTable_Produto.getColumnModel().getColumn(1).setPreferredWidth(450);
        jTable_Produto.getColumnModel().getColumn(1).setResizable(true);
        jTable_Produto.getColumnModel().getColumn(2).setPreferredWidth(60);
        jTable_Produto.getColumnModel().getColumn(2).setResizable(true);
        jTable_Produto.getColumnModel().getColumn(3).setPreferredWidth(50);
        jTable_Produto.getColumnModel().getColumn(3).setResizable(true);
        jTable_Produto.getColumnModel().getColumn(4).setPreferredWidth(80);
        jTable_Produto.getColumnModel().getColumn(4).setResizable(true);
        jTable_Produto.getColumnModel().getColumn(5).setPreferredWidth(500);
        jTable_Produto.getColumnModel().getColumn(5).setResizable(true);
        jTable_Produto.getColumnModel().getColumn(6).setPreferredWidth(230);
        jTable_Produto.getColumnModel().getColumn(6).setResizable(true);
        jTable_Produto.getColumnModel().getColumn(7).setPreferredWidth(00);
        jTable_Produto.getColumnModel().getColumn(7).setResizable(true);
        
        jTable_Produto.getTableHeader().setReorderingAllowed(false);
        jTable_Produto.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable_Produto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        conex.desconecta();
        
    }
    
    public void preencherTabela2(String Sql) {
        String descricao;
        String edicao;
        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{"Id", "Status", "Produto", "Saldo", "Unid", "Valor Unit.", "Observação", "Registro", "Id Linha"};
        conex.conexao();
        conex.executaSql(Sql);
        try {
            conex.rs.first();
            do {
                edicao = conex.rs.getString("edicao_prod");
                
                String datahora = conex.rs.getString("usu_prod") + " " + conex.rs.getString("data_reg") + " " + conex.rs.getString("hora_reg");
                if (edicao.equals(null) | edicao.equals("") | edicao.equals(" ")) {
                    edicao = "";
                } else {
                    edicao = " N° " + edicao;
                }
                descricao = " " + conex.rs.getString("tipo_prod") + " " + conex.rs.getString("nome_prod") + edicao;
                descricao = descricao.toUpperCase();
                Double num4 = (conex.rs.getDouble("valor_prod"));
                BigDecimal df = new BigDecimal(num4);
                NumberFormat nf = NumberFormat.getInstance();// getCurrencyInstance
                nf.setMinimumFractionDigits(3);
                nf.setMaximumFractionDigits(3);
                String FormatoValorProd = nf.format(df);
                FormatoValorProd = "R$ " + FormatoValorProd;
                Double num5 = (conex.rs.getDouble("saldo_prod"));
                BigDecimal df5 = new BigDecimal(num5);
                NumberFormat nf5 = NumberFormat.getInstance();
                nf5.setMinimumFractionDigits(3);
                nf5.setMaximumFractionDigits(3);
                String FormatoReal5 = nf5.format(df5);
                FormatoReal5 = FormatoReal5.replace(",000", "");
                int stprod = conex.rs.getInt("stprod");
                String status_prod;
                if (stprod == 1) {
                    status_prod = "ATIVO";
                } else if (stprod == 2) {
                    status_prod = "ALTERADO";
                } else if (stprod == 3) {
                    status_prod = "EXCLUIDO";
                } else {
                    status_prod = "INDEFINIDO";
                }
                UnProd = conex.rs.getString("sigla_unidade");
                String valor_prod_ex = (conex.rs.getString("valor_prod_ex"));
                dados.add(new Object[]{conex.rs.getString("sis_prod"), status_prod, descricao,
                    FormatoReal5, UnProd, valor_prod_ex,//FormatoValorProd,
                    //                    conex.rs.getString("ncm_prod"), conex.rs.getString("cfop_prod"),
                    conex.rs.getString("obs_prod"),
                    datahora, conex.rs.getInt("id_prod")});
                
            } while (conex.rs.next());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(menu, "preenchertabela\n" + ex);
        }
        ModeloTabela modelo = new ModeloTabela(dados, colunas);
        jTable_Produto.setBackground(null);
        jTable_Produto.setModel(modelo);
        jTable_Produto.getColumnModel().getColumn(0).setPreferredWidth(40);
        jTable_Produto.getColumnModel().getColumn(0).setResizable(true);
        jTable_Produto.getColumnModel().getColumn(1).setPreferredWidth(60);
        jTable_Produto.getColumnModel().getColumn(1).setResizable(true);
        jTable_Produto.getColumnModel().getColumn(2).setPreferredWidth(450);
        jTable_Produto.getColumnModel().getColumn(2).setResizable(true);
        jTable_Produto.getColumnModel().getColumn(3).setPreferredWidth(60);
        jTable_Produto.getColumnModel().getColumn(3).setResizable(true);
        jTable_Produto.getColumnModel().getColumn(4).setPreferredWidth(50);
        jTable_Produto.getColumnModel().getColumn(4).setResizable(true);
        jTable_Produto.getColumnModel().getColumn(5).setPreferredWidth(80);
        jTable_Produto.getColumnModel().getColumn(5).setResizable(true);
        jTable_Produto.getColumnModel().getColumn(6).setPreferredWidth(500);
        jTable_Produto.getColumnModel().getColumn(6).setResizable(true);
        jTable_Produto.getColumnModel().getColumn(7).setPreferredWidth(230);
        jTable_Produto.getColumnModel().getColumn(7).setResizable(true);
        jTable_Produto.getColumnModel().getColumn(8).setPreferredWidth(00);
        jTable_Produto.getColumnModel().getColumn(8).setResizable(true);
        
        jTable_Produto.getTableHeader().setReorderingAllowed(false);
        jTable_Produto.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable_Produto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        conex.desconecta();
        
    }
    
    public void preencherTabela3(String Sql) {
        String descricao;
        String edicao;
        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{"Id", "Produto", "Saldo", "Unid", "Valor Unit.", "Observação", "Registro", "Id Linha"};
        conex.conexao();
        conex.executaSql(Sql);
        try {
            conex.rs.first();
            do {
                edicao = conex.rs.getString("edicao_prod");
                
                String datahora = conex.rs.getString("usu_prod") + " " + conex.rs.getString("data_reg") + " " + conex.rs.getString("hora_reg");
                if (edicao.equals(null) | edicao.equals("") | edicao.equals(" ")) {
                    edicao = "";
                } else {
                    edicao = " N° " + edicao;
                }
                
                descricao = " " + conex.rs.getString("tipo_prod") + " " + conex.rs.getString("nome_prod") + edicao;
                descricao = descricao.toUpperCase();
                Double num4 = (conex.rs.getDouble("valor_prod"));
                BigDecimal df = new BigDecimal(num4);
                NumberFormat nf = NumberFormat.getInstance();// getCurrencyInstance
                nf.setMinimumFractionDigits(3);
                nf.setMaximumFractionDigits(3);
                String FormatoValorProd = nf.format(df);
                FormatoValorProd = "R$ " + FormatoValorProd;
//                String VReal = ("" + FormatoReal);
                // Double VReal = (conex.rs.getDouble("valor_prod")); //valor_prod_ex
                //nf.setMinimumFractionDigits(4);
//                String SaldoDouble = String.valueOf(conex.rs.getDouble("saldo_prod"));
                Double num5 = (conex.rs.getDouble("saldo_prod"));
                BigDecimal df5 = new BigDecimal(num5);
                NumberFormat nf5 = NumberFormat.getInstance();
                nf5.setMinimumFractionDigits(3);
                nf5.setMaximumFractionDigits(3);
                String FormatoReal5 = nf5.format(df5);
                FormatoReal5 = FormatoReal5.replace(",000", "");
//                SaldoDouble = SaldoDouble.replace(".", ",");
                int stprod = conex.rs.getInt("stprod");
                String status_prod;
                if (stprod == 1) {
                    status_prod = "ATIVO";
                } else if (stprod == 2) {
                    status_prod = "ALTERADO";
                } else if (stprod == 3) {
                    status_prod = "EXCLUIDO";
                } else {
                    status_prod = "INDEFINIDO";
                }
                UnProd = conex.rs.getString("sigla_unidade");
                String valor_prod_ex = (conex.rs.getString("valor_prod_ex"));
                dados.add(new Object[]{conex.rs.getString("sis_prod"), descricao,
                    FormatoReal5, UnProd, valor_prod_ex,//FormatoValorProd,
                    //                    conex.rs.getString("ncm_prod"), conex.rs.getString("cfop_prod"),
                    conex.rs.getString("obs_prod"),
                    datahora, conex.rs.getInt("id_prod")});
                
            } while (conex.rs.next());
        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(menu, "preenchertabela\n" + ex);
            JOptionPane.showMessageDialog(menu, "Produto não encontrado ...\nBusque novamente !");
            BuscaLimpa();
        }
        ModeloTabela modelo = new ModeloTabela(dados, colunas);
        jTable_Produto.setBackground(null);
        jTable_Produto.setModel(modelo);
        jTable_Produto.getColumnModel().getColumn(0).setPreferredWidth(40);
        jTable_Produto.getColumnModel().getColumn(0).setResizable(true);
        jTable_Produto.getColumnModel().getColumn(1).setPreferredWidth(450);
        jTable_Produto.getColumnModel().getColumn(1).setResizable(true);
        jTable_Produto.getColumnModel().getColumn(2).setPreferredWidth(60);
        jTable_Produto.getColumnModel().getColumn(2).setResizable(true);
        jTable_Produto.getColumnModel().getColumn(3).setPreferredWidth(50);
        jTable_Produto.getColumnModel().getColumn(3).setResizable(true);
        jTable_Produto.getColumnModel().getColumn(4).setPreferredWidth(80);
        jTable_Produto.getColumnModel().getColumn(4).setResizable(true);
        jTable_Produto.getColumnModel().getColumn(5).setPreferredWidth(500);
        jTable_Produto.getColumnModel().getColumn(5).setResizable(true);
        jTable_Produto.getColumnModel().getColumn(6).setPreferredWidth(230);
        jTable_Produto.getColumnModel().getColumn(6).setResizable(true);
        jTable_Produto.getColumnModel().getColumn(7).setPreferredWidth(00);
        jTable_Produto.getColumnModel().getColumn(7).setResizable(true);
        
        jTable_Produto.getTableHeader().setReorderingAllowed(false);
        jTable_Produto.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable_Produto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        conex.desconecta();
        
    }
    
    public void ExecutaSaldo() {
        String MeuExecutaSaldo = SelecionaProdutoId;
        System.out.println("aquiExecutaSaldo()        Verificando            " + MeuExecutaSaldo);
        conexSaldo.conexao();
        try {
            java.sql.PreparedStatement pst = conexSaldo.con.prepareStatement("update produto as prod set \n"
                    + "saldo_prod=( select  sum(qtd_mov) as qtd_prod  from movprodutobase as base\n"
                    + " where id_prod_ent=sis_prod and stmovimento=1 and stsaldo=1 \n"
                    + " GROUP BY   prod.sis_prod order by sis_prod asc )\n"
                    + " from movprodutobase as base\n"
                    + "where prod.sis_prod='" + MeuExecutaSaldo + "'  and base.stsaldo=1");
            pst.execute();
            System.out.println("aquiExecutaSaldo() ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ExecutaSaldo -- \n" + ex);
        }
        conexSaldo.desconecta();
        BuscaLimpa();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnNovo;
    public static javax.swing.JButton btnPesquisar;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem_Exibe_Imagens;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Produto;
    private javax.swing.JPanel pnPrincipal;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables

    public JButton getBtnEditar() {
        return btnEditar;
    }
    
    public JButton getBtnLimpar() {
        return btnLimpar;
    }
    
    public JButton getBtnNovo() {
        return btnNovo;
    }
    
    public static JButton getBtnPesquisar() {
        return btnPesquisar;
    }
    
    public JTextField getTxtBuscar() {
        return txtBuscar;
    }
    
    public JTable getjTable_Produto() {
        return jTable_Produto;
    }
    
}
