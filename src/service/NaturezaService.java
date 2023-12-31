/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.NaturezaDAO;
import java.util.List;
import model.Natureza;

/**
 *
 * @author edson
 */
public class NaturezaService {

    private final NaturezaDAO naturezaDAO;

    public NaturezaService() {
        naturezaDAO = new NaturezaDAO();
    }

    public List<Natureza> listarNaturezas() {
        return naturezaDAO.listarNaturezas();
    }

    public List<Natureza> listarNaturezasAtivas() {
        return naturezaDAO.listarNaturezasAtivas();
    }

}
