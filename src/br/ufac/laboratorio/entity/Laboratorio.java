package br.ufac.laboratorio.entity;

public class Laboratorio {
	private int id;
	private String nome;
	private Centro centro;
	private int computadores;

	public Laboratorio(String nome, Centro centro, int computadores) {
		this.nome = nome;
		this.centro = centro;
		this.computadores = computadores;
	}
	
	public Laboratorio(int id, String nome, Centro centro, int computadores) {
		this.id = id;
		this.nome = nome;
		this.centro = centro;
		this.computadores = computadores;
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

	public Centro getCentro() {
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}

	public int getComputadores() {
		return computadores;
	}

	public void setComputadores(int computadores) {
		this.computadores = computadores;
	}
	
}
