package br.sodadrink.sp.model;

public class EstantePrateleira {

	int id_estante_prateleira;
	int id_estante;
	int id_prateleira;

	String nomeEstante;
	String nomePrateleira;

	public int getId_estante_prateleira() {
		return id_estante_prateleira;
	}

	public void setId_estante_prateleira(int id_estante_prateleira) {
		this.id_estante_prateleira = id_estante_prateleira;
	}

	public int getId_estante() {
		return id_estante;
	}

	public void setId_estante(int id_estante) {
		this.id_estante = id_estante;
	}

	public int getId_prateleira() {
		return id_prateleira;
	}

	public void setId_prateleira(int id_prateleira) {
		this.id_prateleira = id_prateleira;
	}

	public String getNomeEstante() {
		return nomeEstante;
	}

	public void setNomeEstante(String nomeEstante) {
		this.nomeEstante = nomeEstante;
	}

	public String getNomePrateleira() {
		return nomePrateleira;
	}

	public void setNomePrateleira(String nomePrateleira) {
		this.nomePrateleira = nomePrateleira;
	}

}
