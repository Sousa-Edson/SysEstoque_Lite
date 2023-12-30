/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

 

/**
 *
 * @author edson
 */
public class Item {

    int id_mov, id_prod_ent, nota_mov, modo_mov,status_mov,stsaldo,intmodotbl;
 Double qtd_mov, qtd_prod, qtd_calc, valor_real;
    String sistema_mov;
    String data_mov, qtd_prod_ex, qtd_calc_ex, valor_moeda, destino_mov,
            complemento_mov, registro_mov,  volume, usuario_mov, total_mov;
    
    model.Produto produto;

    public int getIntmodotbl() {
        return intmodotbl;
    }

    public void setIntmodotbl(int intmodotbl) {
        this.intmodotbl = intmodotbl;
    }
    
    
    public int getStsaldo() {
        return stsaldo;
    }

    public void setStsaldo(int stsaldo) {
        this.stsaldo = stsaldo;
    }
   

    public String getSistema_mov() {
        return sistema_mov;
    }

    public int getStatus_mov() {
        return status_mov;
    }

    public void setStatus_mov(int status_mov) {
        this.status_mov = status_mov;
    }

    public void setSistema_mov(String sistema_mov) {
        this.sistema_mov = sistema_mov;
    }

    public String getTotal_mov() {
        return total_mov;
    }

    public void setTotal_mov(String total_mov) {
        this.total_mov = total_mov;
    }

    public int getId_mov() {
        return id_mov;
    }

    public void setId_mov(int id_mov) {
        this.id_mov = id_mov;
    }

    public int getId_prod_ent() {
        return id_prod_ent;
    }

    public void setId_prod_ent(int id_prod_ent) {
        this.id_prod_ent = id_prod_ent;
    }

    public int getNota_mov() {
        return nota_mov;
    }

    public void setNota_mov(int nota_mov) {
        this.nota_mov = nota_mov;
    }

    public int getModo_mov() {
        return modo_mov;
    }

    public void setModo_mov(int modo_mov) {
        this.modo_mov = modo_mov;
    }

    public String getData_mov() {
        return data_mov;
    }

    public void setData_mov(String data_mov) {
        this.data_mov = data_mov;
    }

    public String getQtd_prod_ex() {
        return qtd_prod_ex;
    }

    public void setQtd_prod_ex(String qtd_prod_ex) {
        this.qtd_prod_ex = qtd_prod_ex;
    }

    public String getQtd_calc_ex() {
        return qtd_calc_ex;
    }

    public void setQtd_calc_ex(String qtd_calc_ex) {
        this.qtd_calc_ex = qtd_calc_ex;
    }

    public String getValor_moeda() {
        return valor_moeda;
    }

    public void setValor_moeda(String valor_moeda) {
        this.valor_moeda = valor_moeda;
    }

    public String getDestino_mov() {
        return destino_mov;
    }

    public void setDestino_mov(String destino_mov) {
        this.destino_mov = destino_mov;
    }

    public String getComplemento_mov() {
        return complemento_mov;
    }

    public void setComplemento_mov(String complemento_mov) {
        this.complemento_mov = complemento_mov;
    }

    public String getRegistro_mov() {
        return registro_mov;
    }

    public void setRegistro_mov(String registro_mov) {
        this.registro_mov = registro_mov;
    }

   
    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getUsuario_mov() {
        return usuario_mov;
    }

    public void setUsuario_mov(String usuario_mov) {
        this.usuario_mov = usuario_mov;
    }

    public Double getQtd_mov() {
        return qtd_mov;
    }

    public void setQtd_mov(Double qtd_mov) {
        this.qtd_mov = qtd_mov;
    }

    public Double getQtd_prod() {
        return qtd_prod;
    }

    public void setQtd_prod(Double qtd_prod) {
        this.qtd_prod = qtd_prod;
    }

    public Double getQtd_calc() {
        return qtd_calc;
    }

    public void setQtd_calc(Double qtd_calc) {
        this.qtd_calc = qtd_calc;
    }

    public Double getValor_real() {
        return valor_real;
    }

    public void setValor_real(Double valor_real) {
        this.valor_real = valor_real;
    }

    public model.Produto getProduto() {
        return produto;
    }

    public void setProduto(model.Produto produto) {
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "Movimento{" + "id_mov=" + id_mov + ", id_prod_ent=" + id_prod_ent + ", nota_mov=" + nota_mov + ", modo_mov=" + modo_mov + ", status_mov=" + status_mov + ", stsaldo=" + stsaldo + ", intmodotbl=" + intmodotbl + ", qtd_mov=" + qtd_mov + ", qtd_prod=" + qtd_prod + ", qtd_calc=" + qtd_calc + ", valor_real=" + valor_real + ", sistema_mov=" + sistema_mov + ", data_mov=" + data_mov + ", qtd_prod_ex=" + qtd_prod_ex + ", qtd_calc_ex=" + qtd_calc_ex + ", valor_moeda=" + valor_moeda + ", destino_mov=" + destino_mov + ", complemento_mov=" + complemento_mov + ", registro_mov=" + registro_mov + ", volume=" + volume + ", usuario_mov=" + usuario_mov + ", total_mov=" + total_mov + ", produto=" + produto + '}';
    }

    

    

}
