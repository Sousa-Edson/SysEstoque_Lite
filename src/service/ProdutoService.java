/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author edson
 */
import dao.ProdutoDao;
import java.util.List;
import model.Produto;
import utils.UsuarioLogado;

public class ProdutoService {
    
    private final ProdutoDao produtoDao;
    
    public ProdutoService() {
        this.produtoDao = new ProdutoDao();
    }
    
    public boolean salvarProduto(Produto produto) {
        System.out.println("salvarProduto:: " + produto.toString());
        produto.setUsu_prod(UsuarioLogado.getNome());
        produto.setSis_prod(ultimoId());
        return produtoDao.salvarProduto(produto);
    }
    
    public boolean alterarProduto(Produto produto) {
        return produtoDao.alterarProduto(produto);
    }
    
    public void excluirProduto(Produto produto) {
        produtoDao.excluirProduto(produto);
    }
    
    public List<Produto> listarProdutos() {
        return produtoDao.listarProdutos();
    }
    
    public List<Produto> listarProdutosPorBusca(String busca) {
        return produtoDao.listarProdutosPorBusca(busca);
    }
    
    public Produto obterProdutoPorId(int idProduto) {
        return produtoDao.obterProdutoPorId(idProduto);
    }
    
    public int ultimoId() {
        return produtoDao.BuscaUltimaId();
    }
}
