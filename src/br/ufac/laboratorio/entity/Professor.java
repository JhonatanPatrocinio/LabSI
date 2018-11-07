package br.ufac.laboratorio.entity;

public class Professor {
	private int id;
	private String matricula;
	private String nome;
	private String email;
	private String telefone;
	private Centro centro;
	private Login login;
	
	public Professor(int id, String matricula, String nome, String email, String telefone, Centro centro, Login login) {
		this.id = id;
		this.matricula = matricula;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.centro = centro;
		this.login = login;
	}
	
	public Professor(String matricula, String nome, String email, String telefone, Centro centro, Login login) {
		this.matricula = matricula;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.centro = centro;
		this.login = login;
	}


	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public Centro getCentro() {
		return centro;
	}
	public void setCentro(Centro centro) {
		this.centro = centro;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
