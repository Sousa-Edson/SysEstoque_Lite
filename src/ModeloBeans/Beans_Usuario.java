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
public class Beans_Usuario {

    int id_referencia, id_usuario,status_usuario;
  
    String sigla_usuario, desc_usuario, registro_usuario, usuario_usuario, senha_usuario;

      public int getStatus_usuario() {
        return status_usuario;
    }

    public void setStatus_usuario(int status_usuario) {
        this.status_usuario = status_usuario;
    }
    public String getSenha_usuario() {
        return senha_usuario;
    }

    public void setSenha_usuario(String senha_usuario) {
        this.senha_usuario = senha_usuario;
    }

    public int getId_referencia() {
        return id_referencia;
    }

    public void setId_referencia(int id_referencia) {
        this.id_referencia = id_referencia;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getSigla_usuario() {
        return sigla_usuario;
    }

    public void setSigla_usuario(String sigla_usuario) {
        this.sigla_usuario = sigla_usuario;
    }

    public String getDesc_usuario() {
        return desc_usuario;
    }

    public void setDesc_usuario(String desc_usuario) {
        this.desc_usuario = desc_usuario;
    }

    public String getRegistro_usuario() {
        return registro_usuario;
    }

    public void setRegistro_usuario(String registro_usuario) {
        this.registro_usuario = registro_usuario;
    }

    public String getUsuario_usuario() {
        return usuario_usuario;
    }

    public void setUsuario_usuario(String usuario_usuario) {
        this.usuario_usuario = usuario_usuario;
    }

    

}