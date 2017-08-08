package br.sodadrink.sp.model;

public class Produto {

	int id_produto;
	int id_categoria;
	int id_fornecedor;
	int id_marca;
	String nome;
	float valorCompra;
	String imagem;
	float peso;
	int codBarra;
	int quantidadeEstoque;
	int porcDesconto;
	int aprovado;

	float valorVenda;
	int quantidadeEngradado;
	int qtdParaOSite;

	String nomeCategoria;
	String nomeFornecedor;
	String nomeMarca;

	public int getId_produto() {
		return id_produto;
	}

	public void setId_produto(int id_produto) {
		this.id_produto = id_produto;
	}

	public int getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}

	public int getId_fornecedor() {
		return id_fornecedor;
	}

	public void setId_fornecedor(int id_fornecedor) {
		this.id_fornecedor = id_fornecedor;
	}

	public int getId_marca() {
		return id_marca;
	}

	public void setId_marca(int id_marca) {
		this.id_marca = id_marca;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(String valorCompra) {
		this.valorCompra = Float.parseFloat(valorCompra);
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = Float.parseFloat(peso);
	}

	public int getCodBarra() {
		return codBarra;
	}

	public void setCodBarra(String codBarra) {
		this.codBarra = Integer.parseInt(codBarra);
	}

	public int getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(String quantidadeEstoque) {
		this.quantidadeEstoque = Integer.parseInt(quantidadeEstoque);
	}

	public int getPorcDesconto() {
		return porcDesconto;
	}

	public void setPorcDesconto(int porcDesconto) {
		this.porcDesconto = porcDesconto;
	}

	public int getAprovado() {
		return aprovado;
	}

	public void setAprovado(int aprovado) {
		this.aprovado = aprovado;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}

	public String getNomeMarca() {
		return nomeMarca;
	}

	public void setNomeMarca(String nomeMarca) {
		this.nomeMarca = nomeMarca;
	}

	public float getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(float valorVenda) {
		this.valorVenda = valorVenda;
	}

	public int getQuantidadeEngradado() {
		return quantidadeEngradado;
	}

	public void setQuantidadeEngradado(int quantidadeEngradado) {
		this.quantidadeEngradado = quantidadeEngradado;
	}

	public int getQtdParaOSite() {
		return qtdParaOSite;
	}

	public void setQtdParaOSite(int qtdParaOSite) {
		this.qtdParaOSite = qtdParaOSite;
	}

}
