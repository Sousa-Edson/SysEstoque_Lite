/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.internal;

//import JDialog_Visao.Movimentacao.JDialog_Movimentacao_Produto;
//import JDialog_Visao.Movimentacao.Outros.JDialog_Expedicao_Saida;
//import JDialog_Visao.Movimentacao.JDialog_Movimentacao_Produto;
//import CaixasDeDialogos.JDialogExpedicaoSaida;
import Interface.*;
import ModeloBeans.ModeloTabela;
import ConectaBanco.ConexaoBD;
//import Interface.Tela.Movimento.ExpedicaoJIF;
import java.awt.Color;
import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
//import static Interface.Principal.MenuPrincipal.Painel_principal;
import Sistema.ManipulaProtocolo;
import java.math.BigDecimal;
import java.text.NumberFormat;
import view.MenuPrincipal;
import view.dialog.JDialogExpedicao;

/**
 *
 * @author David
 */
public final class ExpedicaoInterna extends javax.swing.JInternalFrame {

    ConexaoBD conex = new ConexaoBD();
    Principal menu;
    JDialogExpedicao TelaNotaExSai;
    ManipulaProtocolo prot = new ManipulaProtocolo(); 

    String CLASS = "";
    int selecao;
    String selecao_id;
    String MinhaIdNota;

    public ExpedicaoInterna() {
        initComponents();
        jButton1.setVisible(false);
        jButton2.setVisible(false);
        jButton_Fechar.setVisible(false);
        jLabel_Expedicao.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableListaProduto = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton_Fechar = new javax.swing.JButton();
        jLabel_Expedicao = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jCheckBoxMenuItem_cor = new javax.swing.JCheckBoxMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jCheckBoxMenuItem_Expedicao = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem_Relatorio = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem_Nota = new javax.swing.JCheckBoxMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jCheckBoxMenuItem_Protocolo_Preto = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem_Protocolo_Branco = new javax.swing.JCheckBoxMenuItem();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Expedição ");
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
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
                formInternalFrameOpened(evt);
            }
        });

        jTableListaProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableListaProduto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTableListaProdutoFocusGained(evt);
            }
        });
        jTableListaProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableListaProdutoMouseClicked(evt);
            }
        });
        jTableListaProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTableListaProdutoKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTableListaProduto);

        jButton2.setText("Exibir tudo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Filtrar/Atualizar");
        jButton1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton1FocusGained(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton_Fechar.setForeground(new java.awt.Color(255, 0, 0));
        jButton_Fechar.setText("Fechar");
        jButton_Fechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_FecharActionPerformed(evt);
            }
        });

        jLabel_Expedicao.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_Expedicao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Expedicao.setText("Ver Expedição");
        jLabel_Expedicao.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jMenu1.setText("Arquivo");

        jMenuItem1.setText("Filtrar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Exibe tudo");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Fechar");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jCheckBoxMenuItem_cor.setText("Cor");
        jMenu1.add(jCheckBoxMenuItem_cor);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Visualizar");

        jCheckBoxMenuItem_Expedicao.setSelected(true);
        jCheckBoxMenuItem_Expedicao.setText("Ver Expedição");
        jCheckBoxMenuItem_Expedicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem_ExpedicaoActionPerformed(evt);
            }
        });
        jMenu2.add(jCheckBoxMenuItem_Expedicao);

        jCheckBoxMenuItem_Relatorio.setText("Ver Relatório");
        jCheckBoxMenuItem_Relatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem_RelatorioActionPerformed(evt);
            }
        });
        jMenu2.add(jCheckBoxMenuItem_Relatorio);

        jCheckBoxMenuItem_Nota.setText("Ver Nota");
        jCheckBoxMenuItem_Nota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem_NotaActionPerformed(evt);
            }
        });
        jMenu2.add(jCheckBoxMenuItem_Nota);

        jMenu3.setText("Protocolo");

        jCheckBoxMenuItem_Protocolo_Preto.setText("Ver Protocolo Preto");
        jCheckBoxMenuItem_Protocolo_Preto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem_Protocolo_PretoActionPerformed(evt);
            }
        });
        jMenu3.add(jCheckBoxMenuItem_Protocolo_Preto);

        jCheckBoxMenuItem_Protocolo_Branco.setText("Ver Protocolo Branco");
        jCheckBoxMenuItem_Protocolo_Branco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem_Protocolo_BrancoActionPerformed(evt);
            }
        });
        jMenu3.add(jCheckBoxMenuItem_Protocolo_Branco);

        jMenu2.add(jMenu3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(452, 452, 452)
                        .addComponent(jLabel_Expedicao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Fechar)
                        .addGap(154, 154, 154)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2, jButton_Fechar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton_Fechar)
                    .addComponent(jLabel_Expedicao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTableListaProdutoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableListaProdutoFocusGained
//        atual = 1;
//        if (atual == 2) {
//            atualizarTabela();
//        } else {
//        }

    }//GEN-LAST:event_jTableListaProdutoFocusGained
    public void AbreNota() {
//        selecaod = (int) jTableListaProduto.getValueAt(jTableListaProduto.getSelectedRow(), 7);
        System.out.println("selecaod " + MinhaIdNota);
        MenuPrincipal.jLabelCodigoTela2.setText(String.valueOf(MinhaIdNota));
        MenuPrincipal.jLabelCodigoTela.setText("MovimentoCadastroEditar");
        MenuPrincipal.jButton1.doClick(); 
    }

    public void CorNaLinhaSelecionada() {
        CLASS = "" + jTableListaProduto.getValueAt(jTableListaProduto.getSelectedRow(), 0);
        jTableListaProduto
                .setDefaultRenderer(Object.class,
                        new DefaultTableCellRenderer() {
                    @Override
                    public Component getTableCellRendererComponent(JTable table, Object value,
                            boolean isSelected, boolean hasFocus, int row, int column) {
                        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value,
                                isSelected, hasFocus, row, column);
                        //******************************************************************************

                        Color c = Color.BLACK;
                        Object texto = table.getValueAt(row, 0);

                        if (texto != null && CLASS.equals(texto.toString())) {
                            c = Color.blue;
                            label.setBackground(c);
//                            jTableListaProduto.setSelectionForeground(Color.red);
                            jTableListaProduto.setSelectionForeground(BLACK);
                        } else {
                            label.setBackground(c);
                            label.setBackground(java.awt.Color.WHITE);
                            jTableListaProduto.setSelectionForeground(BLACK);
                        }

                        //******************************************************************************
                        return label;
                    }

                });
    }


    private void jTableListaProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableListaProdutoMouseClicked
        CorNaLinhaSelecionada();
        selecao = (int) jTableListaProduto.getValueAt(jTableListaProduto.getSelectedRow(), 0);
        selecao_id = String.valueOf(selecao);
        MinhaIdNota = "" + jTableListaProduto.getValueAt(jTableListaProduto.getSelectedRow(), 9);
        System.err.println("selecao = (int)     " + selecao + "         " + selecao_id);
        if (evt.getClickCount() == 2) {

            if (jLabel_Expedicao.getText().equals("Ver Nota")) {
                AbreNota();
            } else if (jLabel_Expedicao.getText().equals("Ver Expedição")) {
//            JOptionPane.showMessageDialog(rootPane, "Ver Expedição");
                abre_Expedicao();
            } else if (jLabel_Expedicao.getText().equals("Protocolo")) {
//            JOptionPane.showMessageDialog(rootPane, "Protocolo");
                abre_Protocolo();
            } else if (jLabel_Expedicao.getText().equals("Ver Relatorio")) {
                MinhaIdNota = String.valueOf(selecao_id);
                prot.RecebeMInhaOs(MinhaIdNota, "", "", "");
                prot.chamaRelatorio(); 
            } else {
                JOptionPane.showMessageDialog(rootPane, "Erro");
            }

        } 
    }//GEN-LAST:event_jTableListaProdutoMouseClicked
 
    public void abre_Protocolo() {
        if (selecao_id == "Id mov") {
            JOptionPane.showMessageDialog(rootPane, "SELECIONE UM ITEM NA LISTA.");
        } else {
            if (jCheckBoxMenuItem_Protocolo_Preto.isSelected()) {
                MinhaIdNota = String.valueOf(selecao_id);
                prot.RecebeMInhaOs(MinhaIdNota, "", "", "");
                prot.chamaProtocoloPreto();
            } else if (jCheckBoxMenuItem_Protocolo_Branco.isSelected()) {
                MinhaIdNota = String.valueOf(selecao_id);
                prot.RecebeMInhaOs(MinhaIdNota, "", "", "");
                prot.chamaProtocoloBranco();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Escolha um para abrir");
            }

//            if (jComboBox_tipo.getSelectedItem() == "PRETO") {
//                chama_Protocolo_Preto();
//            } else if (jComboBox_tipo.getSelectedItem() == "BRANCO") {
//                chama_Protocolo_Branco();
//            } else {
//                JOptionPane.showMessageDialog(rootPane, "Escolha um para abrir");
//            }
        }
        selecao_id = ("Id mov");
    }

    public void chama_Protocolo_Branco() {
//        MinhaIdNota = String.valueOf(selecao_id);
//        prot.RecebeMInhaOs(MinhaIdNota, menu.jLabel_IdEmpresa.getText(), menu.jLabel_Usuario.getText(), "");
//        prot.VerificaProtocolo();
//        prot.chamaRelatorio2();
    }

    public void chama_Protocolo_Preto() {
//        MinhaIdNota = String.valueOf(selecao_id);
////        jButton_Imprimir.setEnabled(!true);
//
//        prot.RecebeMInhaOs(MinhaIdNota, menu.jLabel_IdEmpresa.getText(), menu.jLabel_Usuario.getText(), "");
//        prot.VerificaProtocolo();
//        prot.chamaRelatorio();

    }


    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ExibeTudo();
    }//GEN-LAST:event_jButton2ActionPerformed
    public void ExibeTudo() {
        if (jCheckBoxMenuItem_cor.isSelected()) {
            System.err.println("- ExibeTudo- cor na linha sim selecionado");
          
        } else {
            System.err.println("- ExibeTudo- cor na linha não selecionado");
        }
//        CorNaLinha();
//        String texto = "SAIDA";
        preencherTabela("SELECT id_nota, nota_documento, nota_nota, nota_data,nota_situacao,ecft_nome,tipo_prod, nome_prod, edicao_prod,\n"
                + " sigla_unidade,nota_observacao,id_referencianota,qtd_mov, qtd_prod, qtd_prod_ex, \n" +
"                qtd_calc, qtd_calc_ex,nota_hora ,complemento_mov,destino_mov,desc_unidade\n"
                + "  FROM movprodutobase\n"
                + "  inner join nota on nota_mov = id_referencianota\n"
                + "  inner join produto on id_prod_ent = sis_prod\n"
                + "  inner join ecft as cl on sis_ecft=fornecedorint\n"
                + "   inner join unidade on id_referenciaunidade=idunid\n"
                + "   inner join natureza on id_referencianatureza=naturezaint\n"
                + "                  where stnota=1 and stmovimento=1 and stprod =1 and cl.stecft =1  and stunid =1 and stnat =1 and \n"
                + "                tipo_natureza='SAIDA'   order by datavariavel asc , id_referencianota asc");
 
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        atualizarTabela();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
//        atualizarTabela();
//        controla_ComboBox_tipo();
//        jLabel_Expedicao.setText("Ver Relatorio");
    }//GEN-LAST:event_formFocusGained

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
//        this.setMaximizable(true);
//        try {
//            this.setMaximum(true);
//        } catch (PropertyVetoException ex) {
//            Logger.getLogger(ExpedicaoJIF.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_formInternalFrameOpened

    private void jButton_FecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_FecharActionPerformed
        fecha_form();
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jButton_FecharActionPerformed

    public void fecha_form() {
        this.setVisible(false);

    }

    public void controla_ComboBox_tipo() {
        if (jLabel_Expedicao.getText().equals(("Protocolo"))) {
        } else {
        }
    }

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        fecha_form();
    }//GEN-LAST:event_formInternalFrameClosing

    private void jButton1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton1FocusGained
        atualizarTabela();
    }//GEN-LAST:event_jButton1FocusGained

    private void jTableListaProdutoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableListaProdutoKeyPressed
        if (evt.getKeyCode() == evt.VK_ESCAPE) {
            fecha_form();
            this.dispose();
        }
    }//GEN-LAST:event_jTableListaProdutoKeyPressed

    private void jCheckBoxMenuItem_ExpedicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem_ExpedicaoActionPerformed
        if (jCheckBoxMenuItem_Expedicao.isSelected()) {
            jCheckBoxMenuItem_Nota.setSelected(false);
            jCheckBoxMenuItem_Protocolo_Branco.setSelected(false);
            jCheckBoxMenuItem_Protocolo_Preto.setSelected(false);
            jCheckBoxMenuItem_Relatorio.setSelected(false);
            jCheckBoxMenuItem_Expedicao.setSelected(true);
            jLabel_Expedicao.setText("Ver Expedição");
        } else {
//            jRadioButton1.setSelected(true);
//            jRadioButton3.setSelected(false);
            jLabel_Expedicao.setText("Ver Relatorio");
        }
    }//GEN-LAST:event_jCheckBoxMenuItem_ExpedicaoActionPerformed

    private void jCheckBoxMenuItem_RelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem_RelatorioActionPerformed
        if (jCheckBoxMenuItem_Relatorio.isSelected()) {
            jCheckBoxMenuItem_Nota.setSelected(false);
            jCheckBoxMenuItem_Protocolo_Branco.setSelected(false);
            jCheckBoxMenuItem_Protocolo_Preto.setSelected(false);
            jCheckBoxMenuItem_Relatorio.setSelected(true);
            jCheckBoxMenuItem_Expedicao.setSelected(false);
            jLabel_Expedicao.setText("Ver Relatorio");
        } else {
//            jRadioButton2.setSelected(true);
            jLabel_Expedicao.setText("Ver Nota");
        }
    }//GEN-LAST:event_jCheckBoxMenuItem_RelatorioActionPerformed

    private void jCheckBoxMenuItem_NotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem_NotaActionPerformed
        if (jCheckBoxMenuItem_Nota.isSelected()) {
            jCheckBoxMenuItem_Nota.setSelected(true);
            jCheckBoxMenuItem_Protocolo_Branco.setSelected(false);
            jCheckBoxMenuItem_Protocolo_Preto.setSelected(false);
            jCheckBoxMenuItem_Relatorio.setSelected(false);
            jCheckBoxMenuItem_Expedicao.setSelected(false);
            jLabel_Expedicao.setText("Ver Nota");
        } else {
//            jRadioButton1.setSelected(true);
//            jRadioButton5.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBoxMenuItem_NotaActionPerformed

    private void jCheckBoxMenuItem_Protocolo_PretoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem_Protocolo_PretoActionPerformed
        if (jCheckBoxMenuItem_Protocolo_Preto.isSelected()) {
            jCheckBoxMenuItem_Nota.setSelected(false);
            jCheckBoxMenuItem_Protocolo_Branco.setSelected(false);
            jCheckBoxMenuItem_Protocolo_Preto.setSelected(true);
            jCheckBoxMenuItem_Relatorio.setSelected(false);
            jCheckBoxMenuItem_Expedicao.setSelected(false);
            jLabel_Expedicao.setText("Protocolo");
        } else {
//                jRadioButton5.setEnabled(false);
//                jRadioButton6.setEnabled(false);
            jCheckBoxMenuItem_Protocolo_Branco.setSelected(true);
            jLabel_Expedicao.setText("Ver Relatorio");
        }
    }//GEN-LAST:event_jCheckBoxMenuItem_Protocolo_PretoActionPerformed

    private void jCheckBoxMenuItem_Protocolo_BrancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem_Protocolo_BrancoActionPerformed
        if (jCheckBoxMenuItem_Protocolo_Branco.isSelected()) {
            jCheckBoxMenuItem_Nota.setSelected(false);
            jCheckBoxMenuItem_Protocolo_Branco.setSelected(true);
            jCheckBoxMenuItem_Protocolo_Preto.setSelected(false);
            jCheckBoxMenuItem_Relatorio.setSelected(false);
            jCheckBoxMenuItem_Expedicao.setSelected(false);
            jLabel_Expedicao.setText("Protocolo");
        } else {
//                jRadioButton5.setEnabled(false);
//                jRadioButton6.setEnabled(false);
            jCheckBoxMenuItem_Protocolo_Preto.setSelected(true);
            jLabel_Expedicao.setText("Ver Relatorio");
        }
    }//GEN-LAST:event_jCheckBoxMenuItem_Protocolo_BrancoActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        fecha_form();
        this.dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        ExibeTudo();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        atualizarTabela();
    }//GEN-LAST:event_jMenuItem1ActionPerformed
 
    public void abre_Expedicao() {
        TelaNotaExSai = new JDialogExpedicao(menu, rootPaneCheckingEnabled);
        if (TelaNotaExSai == null) {
            TelaNotaExSai.RIdSai(selecao_id);
            TelaNotaExSai.setVisible(true);
        } else {
            TelaNotaExSai.RIdSai(selecao_id);
            TelaNotaExSai.MinhaIdNotaSai(MinhaIdNota);
            TelaNotaExSai.setVisible(true);
            TelaNotaExSai.setResizable(false);
        }

    }

      public void atualizarTabela() {
        if (jCheckBoxMenuItem_cor.isSelected()) {
            System.err.println("- ExibeTudo- cor na linha sim selecionado");
             
        } else {
            System.err.println("- ExibeTudo- cor na linha não selecionado");
        }
        //  

        preencherTabela("SELECT id_nota, nota_documento, nota_nota, nota_data,nota_situacao,ecft_nome,tipo_prod, nome_prod, edicao_prod,\n"
                + "sigla_unidade,nota_observacao,id_referencianota,qtd_mov, qtd_prod, qtd_prod_ex, \n" +
"                qtd_calc, qtd_calc_ex,nota_hora ,complemento_mov,destino_mov,desc_unidade\n"
                + "  FROM movprodutobase\n"
                + "  inner join nota on nota_mov = id_referencianota\n"
                + "  inner join produto on id_prod_ent = sis_prod\n"
                + "  inner join ecft as cl on sis_ecft=fornecedorint\n"
                + "   inner join unidade on id_referenciaunidade=idunid\n"
                + "   inner join natureza on id_referencianatureza=naturezaint\n"
                + "                  where stnota=1 and stmovimento=1 and stprod =1 and cl.stecft =1  and stunid =1 and stnat =1 and \n"
                + "                tipo_natureza='SAIDA'and tipo_natureza='SAIDA'  and  nota_situacao!='4-ENVIADO'and nota_situacao!='5-DEVOLVIDO'  order by datavariavel asc , id_referencianota asc");

    }/// sis_ecft

    
    public void preencherTabela(String Sql) {
        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{"OS", "Data", "Situação", "Cliente", "Descrição", "Quantidade",
            "Unidade", "Documento", "Observação", "IdNota"};
        Double qtd;
        String qtd_u;
        conex.conexao();
        conex.executaSql(Sql);

        try {
            conex.rs.first();
            do {
                ////conex.rs.getString("qtd_mov")
                qtd = (conex.rs.getDouble("qtd_prod"));
//                qtd_u = String.valueOf(qtd).replace(".", ",").replace(",0", "");
                qtd_u = String.valueOf(qtd).trim();

                String texto = (String.valueOf(conex.rs.getDouble("qtd_prod")));
                double comDecimal = (Double.parseDouble(texto));
                double semDecimal = (Double.parseDouble(texto));
                DecimalFormat df = new DecimalFormat("##.###");
                System.out.println(df.format(comDecimal));
                System.out.println(df.format(semDecimal));
//                String saida = (df.format(semDecimal));
                String saida;
                String descricao;// 
                String edicao = conex.rs.getString("edicao_prod");
//                String guia = conex.rs.getString("guia");
                String datahora = conex.rs.getString("nota_data") + " " + conex.rs.getString("nota_hora");
                String complemento = conex.rs.getString("complemento_mov"); // complemento_mov,destino_mov
                String destino = conex.rs.getString("destino_mov");

//                 MeuTotal = (conex_MeuTotal.rs.getString("qtd_prod"));
//            MeuValor = (conex_MeuTotal.rs.getString("valor_real"));
//            System.err.println("PreencherMeuTotal()                  INICIO " + MeuValor);
                Double num4 = Double.parseDouble(String.valueOf(qtd));
                BigDecimal dfa = new BigDecimal(num4);
                NumberFormat nfa = NumberFormat.getInstance();
                nfa.setMinimumFractionDigits(3);
                String FormatoReala = nfa.format(dfa);

                FormatoReala = FormatoReala.replace(",000", "");
                saida = FormatoReala;

                if (edicao.equals(null) | edicao.equals("") | edicao.equals(" ")) {
                    edicao = "";
                } else {
                    edicao = " N° " + edicao;
                }
//                if (guia.equals(null) | guia.equals("") | guia.equals(" ")) {
//                    guia = "";
//                } else {
//                    guia = " [ Guia N° " + guia + " ]";
//                }
                if (complemento.equals(null) | complemento.equals("") | complemento.equals(" ")) {
                    complemento = "";
                } else {
                    complemento = " " + complemento;
                }
                if (destino.equals(null) | destino.equals("") | destino.equals(" ")) {
                    destino = "";
                } else {
                    destino = " " + destino;
                }
                descricao = " " + conex.rs.getString("tipo_prod") + " " + conex.rs.getString("nome_prod") + edicao + complemento + destino;
                descricao = descricao.toUpperCase();
                String DocumentoNumero = conex.rs.getString("nota_documento") + " " + conex.rs.getString("nota_nota");
                String TesteDefinido = conex.rs.getString("nota_documento");
                if (TesteDefinido.equals("Não definido")) {
                    DocumentoNumero = "Não definido";
                    System.out.println("Não definido         " + descricao);
                } else {
                    DocumentoNumero = DocumentoNumero;
                    System.out.println("Definido            " + descricao);
                }
                dados.add(new Object[]{conex.rs.getInt("id_referencianota"), conex.rs.getString("nota_data"),
                    conex.rs.getString("nota_situacao"), conex.rs.getString("ecft_nome"),
                    descricao, saida, conex.rs.getString("desc_unidade"),
                    DocumentoNumero, conex.rs.getString("nota_observacao"), conex.rs.getString("id_nota")
                });
                //alterardo por edson//

            } while (conex.rs.next());
        } catch (SQLException ex) {
            System.out.println("SQLException ex " + ex);

        }
        ModeloTabela modelo = new ModeloTabela(dados, colunas);
//        modelo.setNumRows(0);
//        TableCellRenderer tcr = new Colorir();
//        TableColumn column = jTableListaProduto.getColumnModel().getColumn(1);
//        column.setCellRenderer(tcr);

        jTableListaProduto.setModel(modelo);

        jTableListaProduto.getColumnModel().getColumn(0).setPreferredWidth(60);
        jTableListaProduto.getColumnModel().getColumn(0).setResizable(true);
//        jTableListaProduto.getColumnModel().getColumn(0).setCellRenderer(tcr);
        jTableListaProduto.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTableListaProduto.getColumnModel().getColumn(1).setResizable(true);
        jTableListaProduto.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTableListaProduto.getColumnModel().getColumn(2).setResizable(true);
        jTableListaProduto.getColumnModel().getColumn(3).setPreferredWidth(120);
        jTableListaProduto.getColumnModel().getColumn(3).setResizable(true);
        jTableListaProduto.getColumnModel().getColumn(4).setPreferredWidth(420);
        jTableListaProduto.getColumnModel().getColumn(4).setResizable(true);
        jTableListaProduto.getColumnModel().getColumn(5).setPreferredWidth(80);
        jTableListaProduto.getColumnModel().getColumn(5).setResizable(true);
        jTableListaProduto.getColumnModel().getColumn(6).setPreferredWidth(80);
        jTableListaProduto.getColumnModel().getColumn(6).setResizable(true);
        jTableListaProduto.getColumnModel().getColumn(7).setPreferredWidth(120);
        jTableListaProduto.getColumnModel().getColumn(7).setResizable(true);
        jTableListaProduto.getColumnModel().getColumn(8).setPreferredWidth(180);
        jTableListaProduto.getColumnModel().getColumn(8).setResizable(true);
        jTableListaProduto.getColumnModel().getColumn(9).setPreferredWidth(80);
        jTableListaProduto.getColumnModel().getColumn(9).setResizable(true);
//        jTableListaProduto.getColumnModel().getColumn(10).setPreferredWidth(290);
//        jTableListaProduto.getColumnModel().getColumn(10).setResizable(true);
        jTableListaProduto.getTableHeader().setReorderingAllowed(false);
        jTableListaProduto.setAutoResizeMode(jTableListaProduto.AUTO_RESIZE_OFF);
        jTableListaProduto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        conex.desconecta();

    }

     

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton_Fechar;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem_Expedicao;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem_Nota;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem_Protocolo_Branco;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem_Protocolo_Preto;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem_Relatorio;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem_cor;
    private javax.swing.JLabel jLabel_Expedicao;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTableListaProduto;
    // End of variables declaration//GEN-END:variables
}