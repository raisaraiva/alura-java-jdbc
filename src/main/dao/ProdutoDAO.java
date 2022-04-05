package main.dao;

import main.models.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private Connection connection;

    // constructors

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public void alterar(String nome, String descricao, Integer id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE PRODUTO P SET P.NOME = ?, P.DESCRICAO = ? WHERE ID = ?")) {
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, descricao);
            preparedStatement.setInt(3, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletar(Integer id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM PRODUTO WHERE ID = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Produto> listar() {
        List<Produto> produtoList = new ArrayList<>();

        try {
            String sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTO";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.execute();

                try (ResultSet preparedStatementResultSet = preparedStatement.getResultSet()) {
                    while (preparedStatementResultSet.next()) {
                        Produto produto = new Produto(preparedStatementResultSet.getInt(1), preparedStatementResultSet.getString(2), preparedStatementResultSet.getString(3));

                        produtoList.add(produto);
                    }
                }
            }
            return produtoList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void salvar(Produto produto) {
        try {
            String sql = "INSERT INTO PRODUTO (NOME, DESCRICAO) VALUES (?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                preparedStatement.setString(1, produto.getNome());
                preparedStatement.setString(2, produto.getDescricao());

                preparedStatement.execute();

                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    while (resultSet.next()) {
                        produto.setId(resultSet.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
