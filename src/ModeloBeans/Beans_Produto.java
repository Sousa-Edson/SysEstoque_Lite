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
public class Beans_Produto {

    String data_reg, hora_reg, ncm_prod, tipo_prod, nome_prod, edicao_prod,
            cfop_prod, obs_prod, usu_prod, valor_ex;

    int sis_prod, id_prod, un_prod, status_prod;
    Double saldo_prod, estoque_prod, valor;

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

}
