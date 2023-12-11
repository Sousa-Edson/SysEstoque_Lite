/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author edson
 */
import dao.UnidadeDao;
import java.util.List;
import model.Unidade;

public class UnidadeService {

    private final UnidadeDao unidadeDao;

    public UnidadeService() {
        this.unidadeDao = new UnidadeDao();
    }

    public void salvarUnidade(Unidade unidade) {
        if (unidade.getId_unidade() == 0) {
            unidadeDao.salvarUnidade(unidade);
        } else {
            unidadeDao.alterarUnidade(unidade);
        }
    }

    public void alterarUnidade(Unidade unidade) {
        unidadeDao.alterarUnidade(unidade);
    }

    public void excluirUnidade(Unidade unidade) {
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
