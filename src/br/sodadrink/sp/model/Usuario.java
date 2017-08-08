package br.sodadrink.sp.model;

public class Usuario {

	int id_usuario;
	int id_estado;
	int id_nivel;
	String nome;
	String cpf;
	String dtNascimento;
	String sexo;
	String login;
	String senha;
	String telefone;
	String cep;
	String cidade;
	String logradouro;
	String bairro;
	int numero;
	String email;
	
	String nomeNivel;

	boolean conseguiuLogar;

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public int getId_estado() {
		return id_estado;
	}

	public void setId_estado(int id_estado) {
		this.id_estado = id_estado;
	}

	public int getId_nivel() {
		return id_nivel;
	}

	public void setId_nivel(int id_nivel) {
		this.id_nivel = id_nivel;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(String dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = Integer.parseInt(numero);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isConseguiuLogar() {
		return conseguiuLogar;
	}

	public void setConseguiuLogar(boolean conseguiuLogar) {
		this.conseguiuLogar = conseguiuLogar;
	}

	public String getNomeNivel() {
		return nomeNivel;
	}

	public void setNomeNivel(String nomeNivel) {
		this.nomeNivel = nomeNivel;
	}
	
}
