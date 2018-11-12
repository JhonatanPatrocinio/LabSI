package br.ufac.laboratorio.entity;

public class Aluno {
  private int id;
	private int matricula;
	private String nome;
	private Curso curso;
	private Login login;
	
	public Aluno(int matricula, String nome, Curso curso, Login login) {
		this.curso = curso;
		this.login = login;
		this.matricula = matricula;
		this.nome = nome;
	}

	public Aluno(int id, int matricula, String nome, Curso curso, Login login) {
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

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
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
