package br.ufac.laboratorio.entity;


public class Data {
	private int id;
	private String data;
	private String horarioInicio;
	private String horarioTermino;
	
	public Data(int id, String data, String horarioInicio, String horarioTermino) {
		this.id = id;
		this.data = data;
		this.horarioInicio = horarioInicio;
		this.horarioTermino = horarioTermino;
	}
	
	public Data(String data, String horarioInicio, String horarioTermino) {
		this.horarioInicio = horarioInicio;
		this.horarioTermino = horarioTermino;
		this.data = data;
	}
	
	
	public int getId() {
		return id;
	}
	
	
	public String getHorarioInicio() {
		return horarioInicio;
	}
	public void setHorarioInicio(String horarioInicio) {
		this.horarioInicio = horarioInicio;
	}
	public String getHorarioTermino() {
		return horarioTermino;
	}
	public void setHorarioTermino(String horarioTermino) {
		this.horarioTermino = horarioTermino;
	}
	public String getDataUsuario() {
		String[] stringData;
		stringData = data.split("/");
		
		return stringData[2]+"/"+stringData[1]+"/"+stringData[0];
	}
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		String[] stringData;
		stringData = data.split("/");
		
		this.data = stringData[2]+"/"+stringData[1]+"/"+stringData[0];
	}
}
