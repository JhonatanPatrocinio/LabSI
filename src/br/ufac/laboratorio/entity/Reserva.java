package br.ufac.laboratorio.entity;

public class Reserva {
	private int id;
	private Professor idProfessor;
	private Laboratorio idLaboratorio;
	private String data;
	private String horarioInicio;
	private String horarioTermino;
	private int status; // status = 0,1,2,3; Analise, Aceito, Recusado, Utilizado
	private String obs;
	
	public Reserva(int id, Professor idProf, Laboratorio idLab, String data, String horarioInicio, String horarioTermino, int status, String obs) {
		this.id = id;
		this.idProfessor = idProf;
		this.idLaboratorio = idLab;
		this.data = data;
		this.horarioInicio = horarioInicio;
		this.horarioTermino = horarioTermino;
		this.status = status;
		this.obs = obs;
	}
	
	public Reserva(Professor idProf, Laboratorio idLab, String data, String horarioInicio, String horarioTermino, int status, String obs) {
		this.idProfessor = idProf;
		this.idLaboratorio = idLab;
		this.data = data;
		this.horarioInicio = horarioInicio;
		this.horarioTermino = horarioTermino;
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

	public int getStatus() {
		return status;
	}
	
	public String getStatusPorCod(int cod) {
		if(cod == 0)
			return "Aguardando Analise";
		else if(cod == 1)
			return "Solicitação Aceita";
		else if(cod == 2)
			return "Solicitação Recusada";
		else
			return "Utilizado";
	}
	public String getObs() {
		return obs;
	}

	public String getData() {
		return data;
	}

	public String getHorarioInicio() {
		return horarioInicio;
	}
	
	public String getHorarioInicioUsuario() {
		String[] aux = horarioInicio.split(":");
		return aux[0]+":"+aux[1];
	}

	public String getHorarioTermino() {
		return horarioTermino;
	}
	

	public String getHorarioTerminoUsuario() {
		String[] aux = horarioTermino.split(":");
		return aux[0]+":"+aux[1];
	}
	
	public String getDataUsuario() {
		String[] stringData;
		stringData = data.split("-");
		
		return stringData[2]+"/"+stringData[1]+"/"+stringData[0];
	}

	public void setStatus(int status) {
		this.status = status;
	}
	


}
