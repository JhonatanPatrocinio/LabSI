package br.ufac.laboratorio.logic;

import java.util.ArrayList;
import java.util.List;

import br.ufac.laboratorio.db.*;
import br.ufac.laboratorio.entity.*;
import br.ufac.laboratorio.exception.*;

public class DataLogic {
	private DataDB ddb;
	
	public DataLogic(Conexao cnx) {
		this.ddb = new DataDB(cnx);
	}

	public boolean addData(String data, String horaInicio, String horaTermino) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityAlreadyExistException,
	InvalidFieldException {

		List<String> camposInvalidos = new ArrayList<>();
		boolean testField = false;
		
		String novadata = this.formataDatasql(data);
		
		if(horaInicio.length() < 5) {
			testField = true;
			camposInvalidos.add("Hora Inicio");
		}
		if(horaTermino.length() < 5) {
			testField = true;
			camposInvalidos.add("Hora Termino");
		}
		if(novadata.length() < 10) {
			testField = true;
			camposInvalidos.add("Data");
		}
		

		if(testField) {
			throw new InvalidFieldException("Data", camposInvalidos);
		}
		
		Data date = new Data(novadata, horaInicio, horaTermino);
		return ddb.addData(date);
		
	}
	
	public Data getDataId(int id) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException {
		
		return ddb.getDataId(id);
	}

	public Data getDataDia(String data) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException {

		return ddb.getData(data);
	}
	
	public Data getDataHoraInicio(String horaInicio) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException {

		return ddb.getDataHoraInicio(horaInicio);
	}
	
	public Data getDataHoraTermino(String horaTermino) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException {

		return ddb.getDataHoraTermino(horaTermino);	
	}

	public List<Data> getDatas() throws
	DataBaseGenericException,
	DataBaseNotConnectedException, 
	EntityTableIsEmptyException {
		return ddb.getDatas();
	}

	
	private String formataDatasql(String data) {
		
		String[] arraydatanova;
		arraydatanova = data.split("/");
		System.out.println(arraydatanova[2]+"-"+arraydatanova[1]+"-"+arraydatanova[0]);
		return arraydatanova[2]+"-"+arraydatanova[1]+"-"+arraydatanova[0];
		
	}
}
