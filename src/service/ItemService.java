/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author edson
 */
import dao.ItemDAO;
import java.util.List;
import model.Item;
import utils.CodigoGeradoSistema;

public class ItemService {

    private ItemDAO itemDAO;

    public ItemService() {
        this.itemDAO = new ItemDAO();
    }

    public void salvarItem(Item item,int getId_referencia) {
        
        item.setNota_mov(getId_referencia);
        item.setSistema_mov(CodigoGeradoSistema.obterCodigoGerado());
        itemDAO.salvarItem(item);
    }

//    public List<Item> obterItens() {
//        // Adicione lógica de negócios, se necessário, antes de chamar o DAO
//        return itemDAO.obterItens();
//    }
    // Outros métodos do serviço conforme necessário
}
