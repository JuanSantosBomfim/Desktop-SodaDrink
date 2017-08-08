package br.sodadrink.sp.model;

public class Estoque {

	int id_estoque;
	int id_produto;
	int id_prateleira;

	String nomeProduto;
	String nomePrateleira;
	String nomeEstante;
	String nomeCorredor;

	public int getId_estoque() {
		return id_estoque;
	}

	public void setId_estoque(int id_estoque) {
		this.id_estoque = id_estoque;
	}

	public int getId_produto() {
		return id_produto;
	}

	public void setId_produto(int id_produto) {
		this.id_produto = id_produto;
	}

	public int getId_prateleira() {
		return id_prateleira;
	}

	public void setId_prateleira(int id_prateleira) {
		this.id_prateleira = id_prateleira;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getNomePrateleira() {
		return nomePrateleira;
	}

	public void setNomePrateleira(String nomePrateleira) {
		this.nomePrateleira = nomePrateleira;
	}

	public String getNomeEstante() {
		return nomeEstante;
	}

	public void setNomeEstante(String nomeEstante) {
		this.nomeEstante = nomeEstante;
	}

	public String getNomeCorredor() {
		return nomeCorredor;
	}

	public void setNomeCorredor(String nomeCorredor) {
		this.nomeCorredor = nomeCorredor;
	}

}
