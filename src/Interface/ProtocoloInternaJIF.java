/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import ConectaBanco.ConexaoBD;
import Interface.Principal;
import ModeloBeans.ModeloTabela;
import Sistema.ManipulaProtocolo;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author edson
 */
public class ProtocoloInternaJIF extends javax.swing.JInternalFrame {

    Principal menu;
    ConexaoBD conex = new ConexaoBD();
    ManipulaProtocolo prot = new ManipulaProtocolo();
    String selecaoc = "0", selecaod = "0";
    String ObsProtocolo = null;
    String DataProtocolo = null;
    String NotaProtocolo = null;
    String Chama_Sistema;
    String ReferenciaNota = null;

    String CLASS = "", CLASS2 = "";
    String tipo_natureza = null;
    String chama_Os = "Novo";
    String obs = "";
    int selecao;
    int editar = 1;
    int data_box = 1;
    int atual = 1;

    String Ver11;

    public ProtocoloInternaJIF() {
        initComponents();
        remover_Ico();

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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableListaProduto = new javax.swing.JTable();
        jTextFieldBusca = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jRadioButtonEntrada = new javax.swing.JRadioButton();
        jRadioButtonSaida = new javax.swing.JRadioButton();
        DataInicial = new com.toedter.calendar.JDateChooser();
        DataFinal = new com.toedter.calendar.JDateChooser();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jCheckBoxMenuItem_Preto = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem_Branco = new javax.swing.JCheckBoxMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jCheckBoxMenuItem_Observacao = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem_Data = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem_Nota = new javax.swing.JCheckBoxMenuItem();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
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

        jPanel1.setBackground(new java.awt.Color(153, 255, 204));

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
        jTableListaProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableListaProdutoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableListaProduto);

        jTextFieldBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBuscaActionPerformed(evt);
            }
        });
        jTextFieldBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldBuscaKeyReleased(evt);
            }
        });

        jButton1.setText("Pesquisar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Limpar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jRadioButtonEntrada.setBackground(new java.awt.Color(153, 255, 204));
        jRadioButtonEntrada.setText("ENTRADA");
        jRadioButtonEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonEntradaActionPerformed(evt);
            }
        });

        jRadioButtonSaida.setBackground(new java.awt.Color(153, 255, 204));
        jRadioButtonSaida.setText("SAIDA");

        DataInicial.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                DataInicialPropertyChange(evt);
            }
        });

        DataFinal.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                DataFinalPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextFieldBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButtonEntrada)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButtonSaida)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 30, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {DataFinal, DataInicial});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1)
                        .addComponent(jButton2)
                        .addComponent(jRadioButtonEntrada)
                        .addComponent(jRadioButtonSaida))
                    .addComponent(DataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenu2.setText("Exibir");

        jCheckBoxMenuItem_Preto.setSelected(true);
        jCheckBoxMenuItem_Preto.setText("Preto");
        jCheckBoxMenuItem_Preto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem_PretoActionPerformed(evt);
            }
        });
        jMenu2.add(jCheckBoxMenuItem_Preto);

        jCheckBoxMenuItem_Branco.setText("Branco");
        jCheckBoxMenuItem_Branco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem_BrancoActionPerformed(evt);
            }
        });
        jMenu2.add(jCheckBoxMenuItem_Branco);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("Visualizar");

        jCheckBoxMenuItem_Observacao.setText("Observação");
        jCheckBoxMenuItem_Observacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem_ObservacaoActionPerformed(evt);
            }
        });
        jMenu4.add(jCheckBoxMenuItem_Observacao);

        jCheckBoxMenuItem_Data.setText("Data");
        jCheckBoxMenuItem_Data.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem_DataActionPerformed(evt);
            }
        });
        jMenu4.add(jCheckBoxMenuItem_Data);

        jCheckBoxMenuItem_Nota.setText("Nota");
        jCheckBoxMenuItem_Nota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem_NotaActionPerformed(evt);
            }
        });
        jMenu4.add(jCheckBoxMenuItem_Nota);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBuscaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBuscaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            EventoBuscar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        EventoLimpar();
        if (jTextFieldBusca.getText().isEmpty()) {
            EventoData();
        } else {
        }
        EventoLimparTexto();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextFieldBuscaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscaKeyReleased
        if (evt.getKeyCode() == evt.VK_ENTER) {
          
            EventoBuscar();
        
        }
    }//GEN-LAST:event_jTextFieldBuscaKeyReleased

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        this.setVisible(false);
    }//GEN-LAST:event_formInternalFrameClosing

    private void DataInicialPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_DataInicialPropertyChange
        //JOptionPane.showMessageDialog(rootPane, "propriedade");

    }//GEN-LAST:event_DataInicialPropertyChange

    private void DataFinalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_DataFinalPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_DataFinalPropertyChange

    private void jTableListaProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableListaProdutoMouseClicked
        DataProtocolo = "" + jTableListaProduto.getValueAt(jTableListaProduto.getSelectedRow(), 7);
        NotaProtocolo = "" + jTableListaProduto.getValueAt(jTableListaProduto.getSelectedRow(), 8);
        ObsProtocolo = "" + jTableListaProduto.getValueAt(jTableListaProduto.getSelectedRow(), 9);
        String ver = "" + jTableListaProduto.getValueAt(jTableListaProduto.getSelectedRow(), 10);
        chama_Os = "" + jTableListaProduto.getValueAt(jTableListaProduto.getSelectedRow(), 0);
        selecaoc = ver;
        Principal.jLabelCodigoTela2.setText(String.valueOf(selecaoc));
        if (evt.getButton() == MouseEvent.BUTTON3) {
        } else {
            jTextFieldBusca.setText("" + jTableListaProduto.getValueAt(jTableListaProduto.getSelectedRow(), 3));
        }
        if (evt.getButton() == MouseEvent.BUTTON3) {

//            JOptionPane.showMessageDialog(rootPane, "botao direito " + selecaoc);
            int resposta = 0;
            Object[] options = {"Relatório", "Protocolo", "Movimento"};
            resposta = JOptionPane.showOptionDialog(null, "O que deseja vizualizar ?", "Informação", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (resposta == 0) {
                System.out.println("0");
//                    JOptionPane.showMessageDialog(rootPane, "Relatorio      " + selecao_id);
                chamaRelatorio();
            } else if (resposta == 1) {
                System.out.println("1");
                chamaProtocolo();
            } else if (resposta == 2) {

                Principal.jLabelCodigoTela2.setText(String.valueOf(selecaoc));
                Principal.jLabelCodigoTela.setText("MovimentoCadastroEditar");
                Principal.jButton1.doClick();
            } else {
                System.out.println("erro");
            }

        } else {
//            String TipoUsuario = Principal.jLabelTipoUsuario.getText();
//            if (TipoUsuario == "Manutenção") {
//                selecaoc = (int) jTableListaProduto.getValueAt(jTableListaProduto.getSelectedRow(), 8);
//                System.out.println("selecaoc " + selecaoc);
            if (evt.getClickCount() == 2) {
                abre_Protocolo();
//                    selecaod = (int) jTableListaProduto.getValueAt(jTableListaProduto.getSelectedRow(), 8);
//                    System.out.println("selecaod " + selecaod);
//                    Principal.jLabelCodigoTela2.setText(String.valueOf(selecaod));
//                    Principal.jLabelCodigoTela.setText("MovimentoCadastroEditar");
//                    Principal.jButton1.doClick();
//                }
//            } else {
//                ver = "" + jTableListaProduto.getValueAt(jTableListaProduto.getSelectedRow(), 10);
//                selecaoc = ver;
//                System.out.println("ver " + ver);
//                Ver11 = "" + jTableListaProduto.getValueAt(jTableListaProduto.getSelectedRow(), 11);
//            System.out.println("selecaoc normal " + selecaoc);
//            if (evt.getClickCount() == 2) {
//                selecaod = ver;
//                System.out.println("selecaod normal " + selecaod);
//                Principal.jLabelCodigoTela2.setText(String.valueOf(selecaod));
//                Principal.jLabelCodigoTela.setText("Exibir");
//                Principal.jButton1.doClick();
////                }
//            }
//                VerificaSeHaPalete();
//                System.out.println("Ver11 " + Ver11);
            } else {
            }

        }

    }//GEN-LAST:event_jTableListaProdutoMouseClicked
    public void abre_Protocolo() {
        if (selecaoc == "Id mov") {
            JOptionPane.showMessageDialog(rootPane, "SELECIONE UM ITEM NA LISTA.");
        } else {
            if (jCheckBoxMenuItem_Preto.isSelected()) {
                
                prot.RecebeMInhaOs(chama_Os, "", "", "");
                prot.chamaProtocoloPreto();
            } else if (jCheckBoxMenuItem_Branco.isSelected()) {
//                MinhaIdNota = String.valueOf(selecao_id);
                prot.RecebeMInhaOs(chama_Os, "", "", "");
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
       
    }

    
    
    public void chamaBandeira() {
        ReferenciaNota = selecaoc;
        if (jCheckBoxMenuItem_Observacao.isSelected()) {
            ObsProtocolo = ObsProtocolo;
        } else {
            ObsProtocolo = "";
        }
        if (jCheckBoxMenuItem_Data.isSelected()) {
            DataProtocolo = DataProtocolo;
        } else {
            DataProtocolo = "";
        }
        if (jCheckBoxMenuItem_Nota.isSelected()) {
            NotaProtocolo = NotaProtocolo;
        } else {
            NotaProtocolo = "";
        }

        prot.RecebeMeusDados(DataProtocolo, NotaProtocolo, ObsProtocolo, ReferenciaNota);
        prot.RecebeMeuNumeroSitema(Ver11);
        if (jCheckBoxMenuItem_Branco.isSelected()) {
            prot.chamaRelatorio_Bandeira_Pequeno();
                } else if (jCheckBoxMenuItem_Preto.isSelected()) {
            prot.chamaRelatorio_Bandeira_Medio();
        } else {
            prot.chamaRelatorio_Bandeira();
//            JOptionPane.showMessageDialog(null, "Selecione um tamanho !");
        }
    }

    public void VerificaSeHaPalete() {
        String sistema_mov;
        conex.conexao();
        conex.executaSql("SELECT id_nota, id_referencianota,sistema_mov\n"
                + "  FROM nota\n"
                + "  inner join movprodutobase on nota_mov = id_referencianota\n"
                + "  inner join palete on mov_palete ='" + Ver11 + "'");
        try {
            conex.rs.first();
            sistema_mov = conex.rs.getString("sistema_mov");
//            JOptionPane.showMessageDialog(rootPane, "Busque ok");
            chamaBandeira();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Busque novamente");
//            this.setTitle("Erro inesperado");
            System.out.println("Erro inesperado          ERRO " + ex);
        }
        conex.desconecta();
    }

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameClosed

    private void jRadioButtonEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonEntradaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonEntradaActionPerformed

    private void jCheckBoxMenuItem_PretoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem_PretoActionPerformed
        if (jCheckBoxMenuItem_Preto.isSelected()) {
            jCheckBoxMenuItem_Branco.setSelected(!true);
           
        }
    }//GEN-LAST:event_jCheckBoxMenuItem_PretoActionPerformed

    private void jCheckBoxMenuItem_BrancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem_BrancoActionPerformed
        if (jCheckBoxMenuItem_Branco.isSelected()) {
            jCheckBoxMenuItem_Preto.setSelected(!true);
        }
    }//GEN-LAST:event_jCheckBoxMenuItem_BrancoActionPerformed

    private void jCheckBoxMenuItem_ObservacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem_ObservacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxMenuItem_ObservacaoActionPerformed

    private void jCheckBoxMenuItem_DataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem_DataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxMenuItem_DataActionPerformed

    private void jCheckBoxMenuItem_NotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem_NotaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxMenuItem_NotaActionPerformed

    public void EventoData() {
        Date data = null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        if (DataInicial.getDate() == (null) | DataFinal.getDate() == (null)) {
            try {
                data = formato.parse(Principal.jLabel_Data.getText());
                DataInicial.setDate(data);
                DataFinal.setDate(data);
            } catch (ParseException ex) {
            }
        } else {
            DataInicial.setDate(null);
            DataFinal.setDate(null);
        }
    }

    public void EventoLimparTexto() {
        jTextFieldBusca.setText("");
    }

    public void EventoLimpar() {
//        jTextFieldBusca.setText("");
        jTextFieldBusca.requestFocus();
        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{};
        ModeloTabela modelo = new ModeloTabela(dados, colunas);
        jTableListaProduto.setModel(modelo);
    }

    public void EventoBuscar() {
        String MinhaBusca = jTextFieldBusca.getText(), situacao = "", operacao = "";
        MinhaBusca = MinhaBusca.replace("N° ", "").replace("N° ", "").replace("N°", "");
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        String INI2 = "", FIM2 = "";
        System.out.println(" MinhaBusca  - " + MinhaBusca);
        if (jRadioButtonEntrada.isSelected()) {
            if (jRadioButtonSaida.isSelected()) {
                operacao = "";
            } else {
                operacao = "ENTRADA";
            }
        } else if (jRadioButtonSaida.isSelected()) {
            operacao = "SAIDA";
        } else {
            System.out.println("erro - EventoBuscar");
        }
        Date INI = DataInicial.getDate();
        Date FIM = DataFinal.getDate();

        if (INI == null & FIM == null) {
            EventoLimpar();
            preencherTabela(""
                    + "select id_nota,sis_prod,tipo_prod,nome_prod,edicao_prod,nota_nota,nota_documento,nota_observacao,SUM(qtd_mov) AS qtd_mov,\n"
                    + "id_referencianota,ecft_nome,sistema_mov,\n"
                    + "nota_situacao,nota_data ,sigla_unidade,qtd_prod_ex,qtd_calc_ex,destino_mov,nota_operacao,complemento_mov,destino_mov\n"
                    + "From movprodutobase  \n"
                    + "Inner Join produto on id_prod_ent = sis_prod\n"
                    + "   inner join nota on id_referencianota = nota_mov \n"
                    + "   inner join ecft on sis_ecft=fornecedorint\n"
                    + "   inner join unidade on id_referenciaunidade = idunid\n"
                    + "where nota_data!= '' and  (coalesce((id_referencianota))||' '|| coalesce((sis_prod))||' '||coalesce((qtd_prod_ex))||' '||"
                    + " coalesce(UPPER(tipo_prod))||' '|| coalesce(UPPER(nome_prod))||' '|| coalesce(UPPER(edicao_prod))||' '|| coalesce(UPPER(complemento_mov))||' '|| coalesce(UPPER(destino_mov))\n"
                    + " ||' '|| coalesce(UPPER(nota_situacao))  ||' '|| coalesce(UPPER(ecft_nome))||' '||coalesce(UPPER(nota_nota))||' '||\n"
                    + "  coalesce(UPPER(nota_operacao))||' '||coalesce(UPPER(nota_observacao))||' '|| coalesce(UPPER(sigla_unidade))||' '|| coalesce(UPPER(obs_prod))) \n"
                    + "  ilike '%" + MinhaBusca + "%'  and nota_operacao ilike '%" + operacao + "%'\n"
                    + "  and stmovimento=1 and stnota=1 and stunid=1 and stecft =1 and stprod=1\n" // and nota_situacao ilike '%" + situacao + "%'
                    + "  and nota_data  !=''\n"
                    + "  and nota_data  !='' \n"
                    + "  GROUP BY  produto.id_prod,nota.nota_nota,nota.nota_documento,nota.nota_observacao,nota.id_nota,ecft.ecft_nome,unidade.sigla_unidade,\n"
                    + "   movprodutobase.qtd_prod_ex,movprodutobase.qtd_calc_ex ,movprodutobase.destino_mov,movprodutobase.nota_mov \n"
                    + "    ,movprodutobase.complemento_mov,movprodutobase.destino_mov,movprodutobase.sistema_mov  order by nota_mov desc"
                    + ""
            );
        } else if (INI == null) {
            EventoLimpar();
        } else if (FIM == null) {
            EventoLimpar();
        } else {
            INI2 = (formato.format(INI));
            FIM2 = (formato.format(FIM));
            System.out.println("INI2 " + INI2 + " FIM2 " + FIM2);
            preencherTabela(""
                    + "select id_nota,datavariavel,nota_operacao,\n"
                    + "id_nota,sis_prod,tipo_prod,nome_prod,edicao_prod,nota_nota,nota_documento,nota_observacao,qtd_mov,\n"
                    + "id_referencianota,ecft_nome,sistema_mov,\n"
                    + "nota_situacao,nota_data ,sigla_unidade,qtd_prod_ex,qtd_calc_ex,destino_mov,complemento_mov,destino_mov\n"
                    + "From movprodutobase \n"
                    + "Inner Join produto on id_prod_ent = sis_prod\n"
                    + "inner join nota on id_referencianota = nota_mov\n"
                    + "inner join ecft on sis_ecft=fornecedorint\n"
                    + "inner join unidade on id_referenciaunidade = idunid\n"
                    + "where nota_data!= '' and nota_operacao ilike  '%" + operacao + "%'\n"
                    + "  and stmovimento=1 and stnota=1 and stunid=1 and stecft =1 and stprod=1 \n"
                    + " and ( datavariavel  >'" + INI2 + "'  and datavariavel  <'" + FIM2 + "'    "
                    + " or datavariavel  ='" + INI2 + "' or datavariavel  ='" + FIM2 + "' )\n"
                    + "  order by datavariavel desc  "
                    + ""
            );
            System.out.println("EventoBuscar()       -      com data   -   operacao  =    " + operacao);
            jTextFieldBusca.requestFocus();
        }
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
        String[] colunas = new String[]{"OS", "Situação", "Cliente", "Descrição", "Quantidade", "Calculado", "Unidade", "Data", "Documento", "Observação", "Sistema", "Sistema"};
        conex.conexao();
        conex.executaSql(Sql);
        try {
            conex.rs.first();
            do {
                edicao = conex.rs.getString("edicao_prod");

                String complemento = conex.rs.getString("complemento_mov"); // complemento_mov,destino_mov
                String destino = conex.rs.getString("destino_mov");
                System.err.println("VER AQUI DESTINO" + destino);
                if (edicao.equals(null) | edicao.equals("") | edicao.equals(" ")) {
                    edicao = "";
                } else {
                    edicao = " N° " + edicao;
                }

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
                String VerificaOperacao = conex.rs.getString("nota_operacao");
                String Operacao = conex.rs.getString("nota_operacao") + " " + conex.rs.getString("nota_situacao");
                if (VerificaOperacao.equals("ENTRADA")) {
                    Operacao = "        ENTRADA";
                } else {
                    Operacao = Operacao.replace("-", " - ").replace("1", "").replace("2", "").replace("3", "").replace("4", "").replace("5", "");
                }
                String StrgDocumento = conex.rs.getString("nota_documento");
                if (StrgDocumento.equals("Não definido")) {
                    StrgDocumento = StrgDocumento;
                } else {
                    StrgDocumento = StrgDocumento + " " + conex.rs.getString("nota_nota");
                }
                System.out.println("VERIFICANDO A DATA " + conex.rs.getString("nota_data"));
                descricao = " " + conex.rs.getString("tipo_prod") + " " + conex.rs.getString("nome_prod") + edicao + complemento + destino;
                descricao = descricao.toUpperCase();
                dados.add(new Object[]{conex.rs.getInt("id_referencianota"), Operacao, conex.rs.getString("ecft_nome"),
                    descricao, conex.rs.getString("qtd_prod_ex"), conex.rs.getString("qtd_calc_ex"),
                    conex.rs.getString("sigla_unidade"), /// aqui ver unidade
                    conex.rs.getString("nota_data"),
                    StrgDocumento, conex.rs.getString("nota_observacao"),
                    conex.rs.getString("id_nota"), conex.rs.getString("sistema_mov")

                });
                //alterardo por edson//
                System.out.println("preencherTabela          CERTO ");

            } while (conex.rs.next());
            this.setTitle("##     Consulta Protocolo     ##");
        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(rootPane, "Busque novamente");
            this.setTitle("Consulta Protocolo  Busque novamente");
            System.out.println("preencherTabela          ERRO " + ex);
        }
        ModeloTabela modelo = new ModeloTabela(dados, colunas);
        jTableListaProduto.setModel(modelo);

        jTableListaProduto.getColumnModel().getColumn(0).setPreferredWidth(60);
        jTableListaProduto.getColumnModel().getColumn(0).setResizable(true);
        jTableListaProduto.getColumnModel().getColumn(1).setPreferredWidth(150);
        jTableListaProduto.getColumnModel().getColumn(1).setResizable(true);
        jTableListaProduto.getColumnModel().getColumn(2).setPreferredWidth(120);
        jTableListaProduto.getColumnModel().getColumn(2).setResizable(true);
        jTableListaProduto.getColumnModel().getColumn(3).setPreferredWidth(420);
        jTableListaProduto.getColumnModel().getColumn(3).setResizable(true);
//        jTableListaProduto.getColumnModel().getColumn(4).setPreferredWidth(100);
//        jTableListaProduto.getColumnModel().getColumn(4).setResizable(true);
        jTableListaProduto.getColumnModel().getColumn(4).setPreferredWidth(80);
        jTableListaProduto.getColumnModel().getColumn(4).setResizable(true);
        jTableListaProduto.getColumnModel().getColumn(5).setPreferredWidth(80);
        jTableListaProduto.getColumnModel().getColumn(5).setResizable(true);
        jTableListaProduto.getColumnModel().getColumn(6).setPreferredWidth(60);
        jTableListaProduto.getColumnModel().getColumn(6).setResizable(true);
        jTableListaProduto.getColumnModel().getColumn(7).setPreferredWidth(80);
        jTableListaProduto.getColumnModel().getColumn(7).setResizable(true);
        jTableListaProduto.getColumnModel().getColumn(8).setPreferredWidth(120);
        jTableListaProduto.getColumnModel().getColumn(8).setResizable(true);
        jTableListaProduto.getColumnModel().getColumn(9).setPreferredWidth(250);
        jTableListaProduto.getColumnModel().getColumn(9).setResizable(true);
        jTableListaProduto.getColumnModel().getColumn(10).setPreferredWidth(00);
        jTableListaProduto.getColumnModel().getColumn(10).setResizable(true);
        jTableListaProduto.getColumnModel().getColumn(11).setPreferredWidth(00);
        jTableListaProduto.getColumnModel().getColumn(11).setResizable(true);

        jTableListaProduto.getTableHeader().setReorderingAllowed(false);
        jTableListaProduto.setAutoResizeMode(jTableListaProduto.AUTO_RESIZE_OFF);
        jTableListaProduto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        conex.desconecta();

    }

    public void chamaRelatorio() {
     chama_Os = chama_Os;
         System.out.println("1");
                String MinhaIdNota;
        MinhaIdNota = chama_Os;
        prot.RecebeMInhaOs(MinhaIdNota, "", "", "");
              System.out.println("selecao_id "+chama_Os);
                prot.chamaRelatorio();
    }


    public void chamaProtocolo() {
        chama_Os = chama_Os;
         System.out.println("1");
                String MinhaIdNota;
        MinhaIdNota = chama_Os;
        prot.RecebeMInhaOs(MinhaIdNota, "", "", "");
              System.out.println("selecao_id "+chama_Os);
                prot.chamaProtocoloPreto();

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DataFinal;
    private com.toedter.calendar.JDateChooser DataInicial;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem_Branco;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem_Data;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem_Nota;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem_Observacao;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem_Preto;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButtonEntrada;
    private javax.swing.JRadioButton jRadioButtonSaida;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableListaProduto;
    private javax.swing.JTextField jTextFieldBusca;
    // End of variables declaration//GEN-END:variables
}
