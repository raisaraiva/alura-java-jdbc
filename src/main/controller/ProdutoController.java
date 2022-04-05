package main.controller;

import main.dao.ProdutoDAO;
import main.factory.ConnectionFactory;
import main.models.Produto;

import java.sql.Connection;
import java.util.List;

public class ProdutoController {

    private ProdutoDAO produtoDAO;

    // cosntructors

    public ProdutoController() {
        Connection connection = new ConnectionFactory().recuperarConexao();

        this.produtoDAO = new ProdutoDAO(connection);
    }

    // methods

    public void alterar(String nome, String descricao, Integer id) {
        this.produtoDAO.alterar(nome, descricao, id);
    }

    public void deletar(Integer id) {
        this.produtoDAO.deletar(id);
    }

    public List<Produto> listar() {
        return this.produtoDAO.listar();
    }

    public void salvar(Produto produto) {
        this.produtoDAO.salvar(produto);
    }
}
