/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author edson
 */
import UTIL.DataHoraAtual;
import UTIL.UsuarioLogado;
import dao.UnidadeDao;
import java.util.List;
import model.Unidade;

public class UnidadeService {

    private final UnidadeDao unidadeDao;

    public UnidadeService() {
        this.unidadeDao = new UnidadeDao();
    }

    public boolean salvarUnidade(Unidade unidade) {
        if (unidade.getId_unidade() == 0) {
            return unidadeDao.salvarUnidade(unidade);
        } else {
            return unidadeDao.alterarUnidade(unidade);
        }
    }

    public void alterarUnidade(Unidade unidade) {
        unidadeDao.alterarUnidade(unidade);
    }

    public void excluirUnidade(int id) {
        Unidade unidade = new Unidade();
        unidade.setId_unidade(id);
        unidade.setStatus_unidade(3);
        unidade.setUsuario_unidade(UsuarioLogado.getNome());
        unidade.setRegistro_unidade(DataHoraAtual.obterDataHoraFormatada());
        unidadeDao.excluirUnidade(unidade);
    }

    public List<Unidade> listarUnidades() {
        return unidadeDao.listarUnidades();
    }

    public Unidade obterUnidadePorId(int id) {
        return unidadeDao.obterUnidadePorId(id);
    }

    // Adicione métodos adicionais conforme necessário
    // Exemplo de um método que realiza uma operação específica
    public void realizarOperacaoEspecial(Unidade unidade) {
        // Lógica específica da operação especial
        // Pode envolver chamadas a métodos do DAO ou outras operações
    }

    //apagar
    public int idReferencia() {
        return unidadeDao.CarregaUltimo();
    }
}
