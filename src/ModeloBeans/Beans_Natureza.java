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
public class Beans_Natureza {

    int id_natureza, id_referencia,status_natureza;
    String tipo_natureza, desc_natureza, registro_natureza, 
       usuario_natureza;

    public int getId_natureza() {
        return id_natureza;
    }

    public int getStatus_natureza() {
        return status_natureza;
    }

    public void setStatus_natureza(int status_natureza) {
        this.status_natureza = status_natureza;
    }

    public void setId_natureza(int id_natureza) {
        this.id_natureza = id_natureza;
    }

    public int getId_referencia() {
        return id_referencia;
    }

    public void setId_referencia(int id_referencia) {
        this.id_referencia = id_referencia;
    }

    public String getTipo_natureza() {
        return tipo_natureza;
    }

    public void setTipo_natureza(String tipo_natureza) {
        this.tipo_natureza = tipo_natureza;
    }

    public String getDesc_natureza() {
        return desc_natureza;
    }

    public void setDesc_natureza(String desc_natureza) {
        this.desc_natureza = desc_natureza;
    }

    public String getRegistro_natureza() {
        return registro_natureza;
    }

    public void setRegistro_natureza(String registro_natureza) {
        this.registro_natureza = registro_natureza;
    }

    public String getUsuario_natureza() {
        return usuario_natureza;
    }

    public void setUsuario_natureza(String usuario_natureza) {
        this.usuario_natureza = usuario_natureza;
    }

    

    
    
}