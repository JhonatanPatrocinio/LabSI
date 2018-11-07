package br.ufac.laboratorio.entity;

public class Laboratorio {
	private int id;
	private String nome;

	public Laboratorio(String nome) {
		this.nome = nome;
	}
	
	public Laboratorio(int id, String nome) {
		this.id = id;
		this.nome = nome;
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
