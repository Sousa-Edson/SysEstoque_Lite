/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author edson
 */
public class Produto {

    String data_reg, hora_reg, ncm_prod, tipo_prod, nome_prod, edicao_prod,
            cfop_prod, obs_prod, usu_prod, valor_ex;

    int sis_prod, id_prod, un_prod, status_prod;
    Double saldo_prod, estoque_prod, valor;

    Unidade unidade;

    public Produto() {
    }

    public Produto(String data_reg, String hora_reg, String ncm_prod, String tipo_prod, String nome_prod, String edicao_prod, String cfop_prod, String obs_prod, String usu_prod, String valor_ex, int sis_prod, int id_prod, int un_prod, int status_prod, Double saldo_prod, Double estoque_prod, Double valor) {
        this.data_reg = data_reg;
        this.hora_reg = hora_reg;
        this.ncm_prod = ncm_prod;
        this.tipo_prod = tipo_prod;
        this.nome_prod = nome_prod;
        this.edicao_prod = edicao_prod;
        this.cfop_prod = cfop_prod;
        this.obs_prod = obs_prod;
        this.usu_prod = usu_prod;
        this.valor_ex = valor_ex;
        this.sis_prod = sis_prod;
        this.id_prod = id_prod;
        this.un_prod = un_prod;
        this.status_prod = status_prod;
        this.saldo_prod = saldo_prod;
        this.estoque_prod = estoque_prod;
        this.valor = valor;
    }

    public Produto(String data_reg, String hora_reg, String ncm_prod, String tipo_prod, String nome_prod, String edicao_prod,
            String cfop_prod, String obs_prod, String usu_prod, String valor_ex, int sis_prod, int id_prod, Unidade unidade, int status_prod,
            Double saldo_prod, Double estoque_prod, Double valor) {
        this.data_reg = data_reg;
        this.hora_reg = hora_reg;
        this.ncm_prod = ncm_prod;
        this.tipo_prod = tipo_prod;
        this.nome_prod = nome_prod;
        this.edicao_prod = edicao_prod;
        this.cfop_prod = cfop_prod;
        this.obs_prod = obs_prod;
        this.usu_prod = usu_prod;
        this.valor_ex = valor_ex;
        this.sis_prod = sis_prod;
        this.id_prod = id_prod;
        this.unidade = unidade;
        this.status_prod = status_prod;
        this.saldo_prod = saldo_prod;
        this.estoque_prod = estoque_prod;
        this.valor = valor;
    }

    public String getValor_ex() {
        return valor_ex;
    }

    public void setValor_ex(String saldo_prod_ex) {
        this.valor_ex = saldo_prod_ex;
    }

    public int getUn_prod() {
        return un_prod;
    }

    public int getStatus_prod() {
        return status_prod;
    }

    public void setStatus_prod(int status_prod) {
        this.status_prod = status_prod;
    }

    public void setUn_prod(int un_prod) {
        this.un_prod = un_prod;
    }

    public Double getEstoque_prod() {
        return estoque_prod;
    }

    public void setEstoque_prod(Double estoque_prod) {
        this.estoque_prod = estoque_prod;
    }

    public Double getSaldo_prod() {
        return saldo_prod;
    }

    public void setSaldo_prod(Double saldo_prod) {
        this.saldo_prod = saldo_prod;
    }

    public String getHora_reg() {
        return hora_reg;
    }

    public void setHora_reg(String hora_reg) {
        this.hora_reg = hora_reg;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public int getId_prod() {
        return id_prod;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }

    public int getSis_prod() {
        return sis_prod;
    }

    public void setSis_prod(int sis_prod) {
        this.sis_prod = sis_prod;
    }

    public String getData_reg() {
        return data_reg;
    }

    public void setData_reg(String data_reg) {
        this.data_reg = data_reg;
    }

    public String getNcm_prod() {
        return ncm_prod;
    }

    public void setNcm_prod(String ncm_prod) {
        this.ncm_prod = ncm_prod;
    }

    public String getTipo_prod() {
        return tipo_prod;
    }

    public void setTipo_prod(String tipo_prod) {
        this.tipo_prod = tipo_prod;
    }

    public String getNome_prod() {
        return nome_prod;
    }

    public void setNome_prod(String nome_prod) {
        this.nome_prod = nome_prod;
    }

    public String getEdicao_prod() {
        return edicao_prod;
    }

    public void setEdicao_prod(String edicao_prod) {
        this.edicao_prod = edicao_prod;
    }

    public String getCfop_prod() {
        return cfop_prod;
    }

    public void setCfop_prod(String cfop_prod) {
        this.cfop_prod = cfop_prod;
    }

    public String getObs_prod() {
        return obs_prod;
    }

    public void setObs_prod(String obs_prod) {
        this.obs_prod = obs_prod;
    }

    public String getUsu_prod() {
        return usu_prod;
    }

    public void setUsu_prod(String usu_prod) {
        this.usu_prod = usu_prod;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    @Override
    public String toString() {
        return "Produto{" + "data_reg=" + data_reg + ", hora_reg=" + hora_reg + ", ncm_prod=" + ncm_prod + ", tipo_prod=" + tipo_prod + ", nome_prod=" + nome_prod + ", edicao_prod=" + edicao_prod + ", cfop_prod=" + cfop_prod + ", obs_prod=" + obs_prod + ", usu_prod=" + usu_prod + ", valor_ex=" + valor_ex + ", sis_prod=" + sis_prod + ", id_prod=" + id_prod + ", un_prod=" + un_prod + ", status_prod=" + status_prod + ", saldo_prod=" + saldo_prod + ", estoque_prod=" + estoque_prod + ", valor=" + valor + ", unidade=" + unidade + '}';
    }

}
