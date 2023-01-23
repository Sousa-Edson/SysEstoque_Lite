/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloBeans;

/**
 *
 * @author edson
 */
public class Beans_Transporte {

    int id_transporte, online,osnota;

  
    String modalidade, transportadora, motorista, placa, uf, quantidade, especie, numeracao, pesobruto, pesoliquido;

    public int getId_transporte() {
        return id_transporte;
    }
  public int getOsnota() {
        return osnota;
    }

    public void setOsnota(int osnota) {
        this.osnota = osnota;
    }
    
    public void setId_transporte(int id_transporte) {
        this.id_transporte = id_transporte;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public String getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(String transportadora) {
        this.transportadora = transportadora;
    }

    public String getMotorista() {
        return motorista;
    }

    public void setMotorista(String motorista) {
        this.motorista = motorista;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getNumeracao() {
        return numeracao;
    }

    public void setNumeracao(String numeracao) {
        this.numeracao = numeracao;
    }

    public String getPesobruto() {
        return pesobruto;
    }

    public void setPesobruto(String pesobruto) {
        this.pesobruto = pesobruto;
    }

    public String getPesoliquido() {
        return pesoliquido;
    }

    public void setPesoliquido(String pesoliquido) {
        this.pesoliquido = pesoliquido;
    }

}
