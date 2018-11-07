package br.ufac.laboratorio.entity;

public class Curso {
	private int id;
	private int cod;
	private String nome;
	
	public Curso(int cod, String nome) {
		this.cod = cod;
		this.nome = nome;
	}
	
	public Curso(int id, int cod, String nome) {
		this.id = id;
		this.cod = cod;
		this.nome = nome;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}
	
	
}
