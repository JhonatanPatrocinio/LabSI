package br.ufac.laboratorio.logic;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.ufac.laboratorio.db.Conexao;
import br.ufac.laboratorio.db.ReservaDB;
import br.ufac.laboratorio.entity.*;
import br.ufac.laboratorio.exception.*;


public class ReservaLogic {
	private ReservaDB rdb;

	public ReservaLogic(Conexao cnx) {
		this.rdb = new ReservaDB(cnx);
	}

	public boolean addReserva(Professor idProf, Laboratorio idLab, String datas, String horaInicio, String horaTermino, int status, String obs) throws 
	InvalidFieldException, 
	DataBaseGenericException, 
	EntityAlreadyExistException, 
	DataBaseNotConnectedException, 
	EntityLoginNotExistException, 
	DataExistException {

		List<String> camposInvalidos = new ArrayList<String>();
		boolean testField = false;

		if(idLab == null){
			testField = true;
			camposInvalidos.add("Laboratorio Vazio");
		}
		if(datas.length() < 10) {
			testField = true;
			camposInvalidos.add("Formato data XX/XX/YYYY");
		}
		if(horaInicio.length() < 5) {
			testField = true;
			camposInvalidos.add("Horario Inicio Invalido");
		}
		if(horaTermino.length() < 5) {
			testField = true;
			camposInvalidos.add("Horario Termino Invalido");
		}

		String novadata = formataDatasql(datas);
		if (testField){
			throw new InvalidFieldException("Reserva", camposInvalidos);
		}

		//Talvez não Precise disso
		/*	try {
			rdb.getReservaData(novadata, horaInicio, horaTermino, idLab.getId());
			throw new EntityAlreadyExistException("Reserva: Dia e Horario");
		}catch (Exception e) {

		}*/

		Reserva reserva = new Reserva(idProf, idLab, novadata, horaInicio, horaTermino, status, obs);
		return rdb.addReserva(reserva);

	}

	public Reserva getReservaId(int id) throws 
	DataBaseGenericException, 
	DataBaseNotConnectedException, 
	EntityNotExistException, 
	EntityLoginNotExistException {
		return rdb.getReservaId(id);
	}

	public List<Reserva> getReservasPorStatus(int status) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityTableIsEmptyException,
	EntityNotExistException,
	EntityLoginNotExistException {
		return rdb.getReservasPorStatus(status);
	}
	public List<Reserva> getReservasPorProfessor(int idProf) throws 
	DataBaseGenericException, 
	DataBaseNotConnectedException, 
	EntityTableIsEmptyException, 
	EntityNotExistException, 
	EntityLoginNotExistException{
		return rdb.getReservasPorProfessor(idProf);
	}

	private String formataDatasql(String data) {

		String[] arraydatanova;
		arraydatanova = data.split("/");
		System.out.println(arraydatanova[2]+"-"+arraydatanova[1]+"-"+arraydatanova[0]);
		return arraydatanova[2]+"-"+arraydatanova[1]+"-"+arraydatanova[0];

	}
}
