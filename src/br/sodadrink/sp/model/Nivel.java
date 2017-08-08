package br.sodadrink.sp.model;

public class Nivel {

	int id_nivel;
	String descricao;

	int categoria;
	int estoque;
	int fornecedor;
	int marca;

	int nivel;
	int produto;
	int usuario;
	int relatorio;

	public int getId_nivel() {
		return id_nivel;
	}

	public void setId_nivel(int id_nivel) {
		this.id_nivel = id_nivel;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	public int getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(int fornecedor) {
		this.fornecedor = fornecedor;
	}

	public int getMarca() {
		return marca;
	}

	public void setMarca(int marca) {
		this.marca = marca;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getProduto() {
		return produto;
	}

	public void setProduto(int produto) {
		this.produto = produto;
	}

	public int getUsuario() {
		return usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	public int getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(int relatorio) {
		this.relatorio = relatorio;
	}

}
