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
public class Beans_Unidade {

    int id_referencia, id_unidade,status_unidade,fragmento_unidade;
    String sigla_unidade, desc_unidade, registro_unidade, usuario_unidade;

    public int getFragmento_unidade() {
        return fragmento_unidade;
    }

    public void setFragmento_unidade(int fragmento_unidade) {
        this.fragmento_unidade = fragmento_unidade;
    }

   

    public int getId_unidade() {
        return id_unidade;
    }

    public int getStatus_unidade() {
        return status_unidade;
    }

    public void setStatus_unidade(int status_unidade) {
        this.status_unidade = status_unidade;
    }

    public void setId_unidade(int id_unidade) {
        this.id_unidade = id_unidade;
    }

    public int getId_referencia() {
        return id_referencia;
    }

    public void setId_referencia(int id_referencia) {
        this.id_referencia = id_referencia;
    }

    public String getSigla_unidade() {
        return sigla_unidade;
    }

    public void setSigla_unidade(String sigla_unidade) {
        this.sigla_unidade = sigla_unidade;
    }

    public String getDesc_unidade() {
        return desc_unidade;
    }

    public void setDesc_unidade(String desc_unidade) {
        this.desc_unidade = desc_unidade;
    }

    public String getRegistro_unidade() {
        return registro_unidade;
    }

    public void setRegistro_unidade(String registro_unidade) {
        this.registro_unidade = registro_unidade;
    }

    public String getUsuario_unidade() {
        return usuario_unidade;
    }

    public void setUsuario_unidade(String usuario_unidade) {
        this.usuario_unidade = usuario_unidade;
    }

 

}
