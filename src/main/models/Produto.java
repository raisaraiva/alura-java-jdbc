package main.models;

public class Produto {

	private Integer id;

	private String descricao;

	private String nome;

	// constructors

	public Produto() {
	}

	public Produto(String nome, String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}

	public Produto(Integer id, String nome, String descricao) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
	}

	// getters and setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	// methods

	@Override
	public String toString() {
		return String.format("O produto é: %d, %s, %s", this.id, this.nome, this.descricao);
	}
}
