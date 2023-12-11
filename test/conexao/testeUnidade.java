/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import dao.UnidadeDao;

/**
 *
 * @author edson
 */
public class testeUnidade {

    public static void main(String[] args) {
        UnidadeDao unidadeDao = new UnidadeDao();
        System.out.println("Unidade: " + unidadeDao.obterUnidadePorId(1).getDesc_unidade());
    }
}
