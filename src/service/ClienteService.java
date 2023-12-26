/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author edson
 */
import dao.ClienteDAO;
import java.util.List;
import model.Cliente;

public class ClienteService {

    private final ClienteDAO clienteDAO;

    public ClienteService() {
        clienteDAO = new ClienteDAO();
    }

    public List<Cliente> listarClientes(boolean ordenarPorId) {
        return clienteDAO.listarClientes(ordenarPorId);
    }

    // Outros métodos do serviço conforme necessário
}