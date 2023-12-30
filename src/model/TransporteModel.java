/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author edson
 */
public class TransporteModel {

    private String modalidade, transportadora, motorista, placa, uf, quantidade, especie, numeracao, pesobruto, pesoliquido;
    private int motoristaint;

    public TransporteModel() {
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

    public int getMotoristaint() {
        return motoristaint;
    }

    public void setMotoristaint(int motoristaint) {
        this.motoristaint = motoristaint;
    }

    @Override
    public String toString() {
        return "TransporteModel{" + "modalidade=" + modalidade + ", transportadora=" + transportadora + ", motorista=" + motorista + ", placa=" + placa + ", uf=" + uf + ", quantidade=" + quantidade + ", especie=" + especie + ", numeracao=" + numeracao + ", pesobruto=" + pesobruto + ", pesoliquido=" + pesoliquido + ", motoristaint=" + motoristaint + '}';
    }

}
