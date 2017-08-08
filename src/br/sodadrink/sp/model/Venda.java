package br.sodadrink.sp.model;

public class Venda {

	int id_pedido;
	int id_forma_pagamento;
	int id_cliente;
	int id_status;
	String dtPedido;
	String dtEnvio;
	String dtEntrega;
	float valorTotal;

	String nomeFormaPagamento;
	String nomeCliente;
	String nomeStatus;

	public int getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(int id_pedido) {
		this.id_pedido = id_pedido;
	}

	public int getId_forma_pagamento() {
		return id_forma_pagamento;
	}

	public void setId_forma_pagamento(int id_forma_pagamento) {
		this.id_forma_pagamento = id_forma_pagamento;
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public int getId_status() {
		return id_status;
	}

	public void setId_status(int id_status) {
		this.id_status = id_status;
	}

	public String getDtPedido() {
		return dtPedido;
	}

	public void setDtPedido(String dtPedido) {
		this.dtPedido = dtPedido;
	}

	public String getDtEnvio() {
		return dtEnvio;
	}

	public void setDtEnvio(String dtEnvio) {
		this.dtEnvio = dtEnvio;
	}

	public String getDtEntrega() {
		return dtEntrega;
	}

	public void setDtEntrega(String dtEntrega) {
		this.dtEntrega = dtEntrega;
	}

	public float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(String valorTotal) {
		this.valorTotal = Float.parseFloat(valorTotal);
	}

	public String getNomeFormaPagamento() {
		return nomeFormaPagamento;
	}

	public void setNomeFormaPagamento(String nomeFormaPagamento) {
		this.nomeFormaPagamento = nomeFormaPagamento;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeStatus() {
		return nomeStatus;
	}

	public void setNomeStatus(String nomeStatus) {
		this.nomeStatus = nomeStatus;
	}

}
