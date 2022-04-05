package main.view;

import main.controller.ProdutoController;
import main.models.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel labelNome, labelDescricao;

	private JTextField textoNome, textoDescricao;

	private JButton botaoSalvar, botaoEditar, botaoLimpar, botarApagar;

	private JTable tabela;

	private DefaultTableModel modelo;

	private ProdutoController produtoController;

	// cosntructors

	public Frame() {
		super("Produtos");

		Container container = getContentPane();

		setLayout(null);

		this.produtoController = new ProdutoController();

		labelNome = new JLabel("Nome do Produto");
		labelDescricao = new JLabel("Descrição do Produto");

		labelNome.setBounds(10, 10, 240, 15);
		labelDescricao.setBounds(10, 50, 240, 15);

		labelNome.setForeground(Color.BLACK);
		labelDescricao.setForeground(Color.BLACK);

		container.add(labelNome);
		container.add(labelDescricao);

		textoNome = new JTextField();
		textoDescricao = new JTextField();

		textoNome.setBounds(10, 25, 265, 20);
		textoDescricao.setBounds(10, 65, 265, 20);

		container.add(textoNome);
		container.add(textoDescricao);

		botaoSalvar = new JButton("Salvar");
		botaoLimpar = new JButton("Limpar");

		botaoSalvar.setBounds(10, 145, 80, 20);
		botaoLimpar.setBounds(100, 145, 80, 20);

		container.add(botaoSalvar);
		container.add(botaoLimpar);

		tabela = new JTable();
		modelo = (DefaultTableModel) tabela.getModel();

		modelo.addColumn("Identificador do Produto");
		modelo.addColumn("Nome do Produto");
		modelo.addColumn("Descrição do Produto");

		preencherTabela();

		tabela.setBounds(10, 185, 760, 300);

		container.add(tabela);

		botarApagar = new JButton("Excluir");
		botaoEditar = new JButton("Alterar");

		botarApagar.setBounds(10, 500, 80, 20);
		botaoEditar.setBounds(100, 500, 80, 20);

		container.add(botarApagar);
		container.add(botaoEditar);

		setSize(800, 600);
		setVisible(true);
		setLocationRelativeTo(null);

		botaoSalvar.addActionListener(e -> {
			salvar();
			limparTabela();
			preencherTabela();
		});

		botaoLimpar.addActionListener(e -> limpar());

		botarApagar.addActionListener(e -> {
			deletar();
			limparTabela();
			preencherTabela();
		});

		botaoEditar.addActionListener(e -> {
			alterar();
			limparTabela();
			preencherTabela();
		});
	}

	// methods

	private void alterar() {
		Integer id = (Integer) modelo.getValueAt(tabela.getSelectedRow(), 0);
		String nome = (String) modelo.getValueAt(tabela.getSelectedRow(), 1);
		String descricao = (String) modelo.getValueAt(tabela.getSelectedRow(), 2);

		this.produtoController.alterar(nome, descricao, id);
	}

	private void deletar() {
		this.produtoController.deletar((Integer) modelo.getValueAt(tabela.getSelectedRow(), 0));

		modelo.removeRow(tabela.getSelectedRow());

		JOptionPane.showMessageDialog(this, "Item excluído com sucesso!");
	}

	private void limpar() {
		this.textoNome.setText("");
		this.textoDescricao.setText("");
	}

	private void limparTabela() {
		modelo.getDataVector().clear();
	}

	private void preencherTabela() {
		List<Produto> produtos = this.produtoController.listar();

		for (Produto produto : produtos) {
			modelo.addRow(new Object[] { produto.getId(), produto.getNome(), produto.getDescricao() });
		}
	}

	private void salvar() {
		if (!textoNome.getText().equals("") && !textoDescricao.getText().equals("")) {
			Produto produto = new Produto(textoNome.getText(), textoDescricao.getText());

			this.produtoController.salvar(produto);

			JOptionPane.showMessageDialog(this, "Salvo com sucesso!");

			this.limpar();
		} else {
			JOptionPane.showMessageDialog(this, "Nome e Descrição devem ser informados.");
		}
	}
}
