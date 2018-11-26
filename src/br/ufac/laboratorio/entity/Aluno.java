package br.ufac.laboratorio.entity;

public class Aluno {
	private int id;
	private String matricula;
	private String nome;
	private Curso curso;
	private Login login;
	
	public Aluno(String matricula, String nome, Curso curso, Login login) {
		this.curso = curso;
		this.login = login;
		this.matricula = matricula;
		this.nome = nome;
	}

	public Aluno(int id, String matricula, String nome, Curso curso, Login login) {
		this.curso = curso;
		this.id = id;
		this.login = login;
		this.matricula = matricula;
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
}
