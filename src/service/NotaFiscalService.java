/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.NotaFiscalDAO;
import java.util.List;
import model.NotaFiscal;
import utils.DataHoraAtual;
import utils.UsuarioLogado;

/**
 *
 * @author edson
 */
public class NotaFiscalService {

    NotaFiscalDAO notaFiscalDAO;

    public NotaFiscalService() {
        notaFiscalDAO = new NotaFiscalDAO();
    }

    public List<NotaFiscal> listarNotasPorBusca(String busca) {
        return notaFiscalDAO.listarNotasPorBusca(busca);
    }

    public NotaFiscal obterNotaPorId(int busca) {
        return notaFiscalDAO.obterNotaPorId(busca);
    }

    public void adicionarNotaFiscal(NotaFiscal notaFiscal) {
        notaFiscal.setNota_status(1);// 1 para ativo            
        notaFiscal.setNota_documento("nota");
        notaFiscal.setNota_registro(DataHoraAtual.obterDataHoraFormatada());
        notaFiscal.setNota_usuario(UsuarioLogado.getNome());
        notaFiscal.getTransporteModel().setModalidade("0-NÃO DEFINIDO");
        notaFiscal.getTransporteModel().setModalidade("NÃO DEFINIDO");
        notaFiscal.getTransporteModel().setTransportadora("NÃO DEFINIDO");

        notaFiscalDAO.adicionarNotaFiscal(notaFiscal);
    }

}
