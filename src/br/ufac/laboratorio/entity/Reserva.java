package br.ufac.laboratorio.entity;

public class Reserva {
	private int id;
	private Professor idProfessor;
	private Laboratorio idLaboratorio;
	private Data idData;
	private int status;
	private String obs;
	
	public Reserva(int id, Professor idProf, Laboratorio idLab, Data idData, int status, String obs) {
		this.id = id;
		this.idProfessor = idProf;
		this.idLaboratorio = idLab;
		this.idData = idData;
		this.status = status;
		this.obs = obs;
	}
	
	public Reserva(Professor idProf, Laboratorio idLab, Data idData, int status, String obs) {
		this.idProfessor = idProf;
		this.idLaboratorio = idLab;
		this.idData = idData;
		this.status = status;
		this.obs = obs;
	}

	public int getId() {
		return id;
	}

	public Professor getIdProfessor() {
		return idProfessor;
	}

	public void setIdProfessor(Professor idProfessor) {
		this.idProfessor = idProfessor;
	}

	public Laboratorio getIdLaboratorio() {
		return idLaboratorio;
	}

	public void setIdLaboratorio(Laboratorio idLaboratorio) {
		this.idLaboratorio = idLaboratorio;
	}

	public Data getIdData() {
		return idData;
	}

	public void setIdData(Data idData) {
		this.idData = idData;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}
	


}
