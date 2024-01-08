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
import utils.DataHoraAtual;
import utils.UsuarioLogado;

public class ClienteService {

    private final ClienteDAO clienteDAO;

    public ClienteService() {
        clienteDAO = new ClienteDAO();
    }

    public List<Cliente> listarClientes(boolean ordenarPorId) {
        return clienteDAO.listarClientes(ordenarPorId);
    }

    public List<Cliente> listarClientesComFIltro(String busca, boolean ordenarPorId) {
        return clienteDAO.listarClientesComFIltro(busca, ordenarPorId);
    }

    public Cliente obterClientePorId(int i) {
        return clienteDAO.obterClientePorId(i);
    }

    public void salvar(Cliente cliente) {

        cliente.setCliente_registro(DataHoraAtual.obterDataHoraFormatada());
        cliente.setCliente_usuario(UsuarioLogado.getNome());
        cliente.setCliente_tipo("CLIENTE");
        cliente.setStcliente(1);

        if (cliente.getCliente_id() == 0) {
            cliente.setSis_cliente(clienteDAO.carregaUltimo());
            clienteDAO.salvar(cliente);
        } else {
            clienteDAO.atualizar(cliente);
        }
    }

    public boolean excluirCliente(Cliente cliente) {
        cliente.setCliente_registro(DataHoraAtual.obterDataHoraFormatada());
        cliente.setCliente_usuario(UsuarioLogado.getNome()); 
        cliente.setStcliente(3);
        clienteDAO.marcarComoDeletado(cliente);
        return true;
    }
}
