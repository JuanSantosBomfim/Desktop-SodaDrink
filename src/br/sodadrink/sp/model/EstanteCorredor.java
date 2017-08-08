package br.sodadrink.sp.model;

public class EstanteCorredor {

	int id_estante_corredor;
	int id_corredor;
	int id_estante;

	String nomeEstante;
	String nomeCorredor;

	public int getId_estante_corredor() {
		return id_estante_corredor;
	}

	public void setId_estante_corredor(int id_estante_corredor) {
		this.id_estante_corredor = id_estante_corredor;
	}

	public int getId_corredor() {
		return id_corredor;
	}

	public void setId_corredor(int id_corredor) {
		this.id_corredor = id_corredor;
	}

	public int getId_estante() {
		return id_estante;
	}

	public void setId_estante(int id_estante) {
		this.id_estante = id_estante;
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
