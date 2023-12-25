/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.NotaFiscalDAO;
import java.util.List;
import model.NotaFiscal;

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

}
