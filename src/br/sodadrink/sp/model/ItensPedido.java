package br.sodadrink.sp.model;

public class ItensPedido {

	int id_item_pedido;
	int id_produto;
	int id_pedido;
	int quantidade;

	int quantidadeTotalVenda;

	public int getId_item_pedido() {
		return id_item_pedido;
	}

	public void setId_item_pedido(int id_item_pedido) {
		this.id_item_pedido = id_item_pedido;
	}

	public int getId_produto() {
		return id_produto;
	}

	public void setId_produto(int id_produto) {
		this.id_produto = id_produto;
	}

	public int getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(int id_pedido) {
		this.id_pedido = id_pedido;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getQuantidadeTotalVenda() {
		return quantidadeTotalVenda;
	}

	public void setQuantidadeTotalVenda(int quantidadeTotalVenda) {
		this.quantidadeTotalVenda = quantidadeTotalVenda;
	}

}
