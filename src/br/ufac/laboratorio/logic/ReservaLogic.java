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
	private ProfessorLogic pl;
	private DataLogic dl;
	
	public ReservaLogic(Conexao cnx) {
		this.rdb = new ReservaDB(cnx);
		this.dl = new DataLogic(cnx);
		this.pl = new ProfessorLogic(cnx);
	}
	
	public boolean addReserva(Professor idProf, Laboratorio idLab, String datas, String horaInicio, String horaTermino, int status, String obs) throws 
	InvalidFieldException, 
	DataBaseGenericException, 
	EntityAlreadyExistException, 
	DataBaseNotConnectedException, 
	EntityLoginNotExistException {
		
		Data data = null;

		List<String> camposInvalidos = new ArrayList<String>();
		boolean testField = false;

		if(idLab == null){
			testField = true;
			camposInvalidos.add("Laboratorio");
		}
		if(datas.length() < 10) {
			testField = true;
			camposInvalidos.add("Formato data XX/XX/YYYY");
		}
		if(horaInicio.length() < 5) {
			testField = true;
			camposInvalidos.add("Horario Inicio");
		}
		if(horaTermino.length() < 5) {
			testField = true;
			camposInvalidos.add("Horario Inicio");
		}
		
		
		if (testField){
			throw new InvalidFieldException("Reserva", camposInvalidos);
		}	
		try {
			dl.addData(datas, horaInicio, horaTermino);
		}catch (DataBaseGenericException |
				DataBaseNotConnectedException |
				EntityAlreadyExistException |
				InvalidFieldException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), 
					"Falha no Cadastro da Reserva", JOptionPane.ERROR_MESSAGE);
//			testField = true;
//			camposInvalidos.add("Data já existe");
//			throw new EntityAlreadyExistException("Data");
		}
		
		try {
			data = dl.procuraData(datas, horaInicio, horaTermino);
		}catch (Exception e) {
			
		}

		if (testField){
			throw new InvalidFieldException("Reserva", camposInvalidos);
		}
		try {
//			loginLogic.addLogin(login, senha, tipo);
		} catch (Exception e) {
			// TODO: handle exception
		}


		Reserva r = new Reserva(idProf, idLab, data, status, obs);

		return rdb.addReserva(r);
	}
}
