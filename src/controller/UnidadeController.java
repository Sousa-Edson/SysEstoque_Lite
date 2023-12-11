/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import UTIL.DataHoraAtual;
import UTIL.UsuarioLogado;
import model.Unidade;
import service.UnidadeService;

/**
 *
 * @author edson
 */ 

public class UnidadeController {
    private UnidadeService unidadeService;
    
    private Unidade unidade= new Unidade();

    public UnidadeController() {
        unidadeService = new UnidadeService();
    }

    public void salvarUnidade(String siglaUnidade,String descUnidade,int stsUnidade , int fragUnidade , int id ) {
        
        
        // Lógica de controle específica, se necessário
        // Pode envolver validações ou outras operações antes de chamar o serviço
        // ...

        // Chama o serviço para realizar a operação
        unidadeService.salvarUnidade(new Unidade(unidadeService.idReferencia(),  
                stsUnidade, 
                fragUnidade, 
                siglaUnidade, 
                descUnidade, 
                DataHoraAtual.obterDataHoraFormatada(), 
                UsuarioLogado.getNome()));

        // Lógica adicional após a chamada do serviço, se necessário
        // ...
    }
}

 