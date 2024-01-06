/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author edson
 */
import utils.DataHoraAtual;
import utils.UsuarioLogado;
import dao.UsuarioDao;
import java.util.List;
import model.Usuario;

public class UsuarioService {

    private final UsuarioDao usuarioDao;

    public UsuarioService() {
        this.usuarioDao = new UsuarioDao();
    }

    public boolean salvarUsuario(Usuario usuario) {

        usuario.setStatus_usuario(1);
        usuario.setRegistro_usuario(DataHoraAtual.obterDataHoraFormatada());
        usuario.setUsuario_usuario(UsuarioLogado.getNome());

        if (usuario.getId_usuario() == 0) {
            usuario.setId_referencia(usuarioDao.carregaUltimo());

            usuarioDao.salvar(usuario);
            return true;
        } else {
            usuarioDao.atualizar(usuario);
            return true;
        }
    }

//    public void alterarUsuario(Usuario usuario) {
//        usuarioDao.alterarUsuario(usuario);
//    }
    public void excluirUsuario(int id) {
        Usuario usuario = new Usuario();
        usuario.setId_usuario(id);
        usuario.setStatus_usuario(3);
        usuario.setUsuario_usuario(UsuarioLogado.getNome());
        usuario.setRegistro_usuario(DataHoraAtual.obterDataHoraFormatada());
        usuarioDao.excluir(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioDao.listarUsuarios();
    }

    public Usuario obterUsuarioPorId(int id) {
        return usuarioDao.obterUsuarioPorId(id);
    }

    public int idReferencia() {
        return usuarioDao.carregaUltimo();
    }
}
