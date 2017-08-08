package br.sodadrink.sp.model;

public class ItemComboBox {

	int id_item;
	String descricao;
	int posicao;

	@Override
	public String toString() {
		return descricao; // o que vai aparecer na comboBox
	}

	public ItemComboBox(int id_item, String descricao, int posicao) {
		this.id_item = id_item;
		this.descricao = descricao;
		this.posicao = posicao;
	}

	public int getId_item() {
		return id_item;
	}

	public void setId_item(int id_item) {
		this.id_item = id_item;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getPosicao() {
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}

}
