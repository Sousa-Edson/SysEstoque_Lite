/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author edson
 */
public class NotaFiscal {

    private int id_nota, id_referencia, nota_status, empresaint;
    private String nota_documento, nota_data, nota_nota, nota_hora, nota_observacao,
            nota_chave, nota_total, nota_registro, nota_usuario;

    private String nota_operacao, nota_situacao;

    private String datavariavel;

    private Natureza natureza;
    private Cliente cliente;

    private TransporteModel transporteModel;

    private List<Item> itens;

    public NotaFiscal() {
    }

    public int getId_nota() {
        return id_nota;
    }

    public void setId_nota(int id_nota) {
        this.id_nota = id_nota;
    }

    public int getId_referencia() {
        return id_referencia;
    }

    public void setId_referencia(int id_referencia) {
        this.id_referencia = id_referencia;
    }

    public int getNota_status() {
        return nota_status;
    }

    public void setNota_status(int nota_status) {
        this.nota_status = nota_status;
    }

    public int getEmpresaint() {
        return empresaint;
    }

    public void setEmpresaint(int empresaint) {
        this.empresaint = empresaint;
    }

    public String getNota_documento() {
        return nota_documento;
    }

    public void setNota_documento(String nota_documento) {
        this.nota_documento = nota_documento;
    }

    public String getNota_data() {
        return nota_data;
    }

    public void setNota_data(String nota_data) {
        this.nota_data = nota_data;
    }

    public String getNota_nota() {
        return nota_nota;
    }

    public void setNota_nota(String nota_nota) {
        this.nota_nota = nota_nota;
    }

    public String getNota_hora() {
        return nota_hora;
    }

    public void setNota_hora(String nota_hora) {
        this.nota_hora = nota_hora;
    }

    public String getNota_observacao() {
        return nota_observacao;
    }

    public void setNota_observacao(String nota_observacao) {
        this.nota_observacao = nota_observacao;
    }

    public String getNota_chave() {
        return nota_chave;
    }

    public void setNota_chave(String nota_chave) {
        this.nota_chave = nota_chave;
    }

    public String getNota_total() {
        return nota_total;
    }

    public void setNota_total(String nota_total) {
        this.nota_total = nota_total;
    }

    public String getNota_registro() {
        return nota_registro;
    }

    public void setNota_registro(String nota_registro) {
        this.nota_registro = nota_registro;
    }

    public String getNota_usuario() {
        return nota_usuario;
    }

    public void setNota_usuario(String nota_usuario) {
        this.nota_usuario = nota_usuario;
    }

    public String getNota_operacao() {
        return nota_operacao;
    }

    public void setNota_operacao(String nota_operacao) {
        this.nota_operacao = nota_operacao;
    }

    public String getNota_situacao() {
        return nota_situacao;
    }

    public void setNota_situacao(String nota_situacao) {
        this.nota_situacao = nota_situacao;
    }

    public String getDatavariavel() {
        return datavariavel;
    }

    public void setDatavariavel(String datavariavel) {
        this.datavariavel = datavariavel;
    }

    public Natureza getNatureza() {
        return natureza;
    }

    public void setNatureza(Natureza natureza) {
        this.natureza = natureza;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public TransporteModel getTransporteModel() {
        return transporteModel;
    }

    public void setTransporteModel(TransporteModel transporteModel) {
        this.transporteModel = transporteModel;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    @Override
    public String toString() {
        return "NotaFiscal{" + "id_nota=" + id_nota + ", id_referencia=" + id_referencia + ", nota_status=" + nota_status + ", empresaint=" + empresaint + ", nota_documento=" + nota_documento + ", nota_data=" + nota_data + ", nota_nota=" + nota_nota + ", nota_hora=" + nota_hora + ", nota_observacao=" + nota_observacao + ", nota_chave=" + nota_chave + ", nota_total=" + nota_total + ", nota_registro=" + nota_registro + ", nota_usuario=" + nota_usuario + ", nota_operacao=" + nota_operacao + ", nota_situacao=" + nota_situacao + ", datavariavel=" + datavariavel + ", natureza=" + natureza + ", cliente=" + cliente + ", transporteModel=" + transporteModel + ", itens=" + itens + '}';
    }
    

}
