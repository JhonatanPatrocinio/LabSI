package br.ufac.laboratorio.logic;

import java.util.ArrayList;
import java.util.List;

import br.ufac.laboratorio.db.*;
import br.ufac.laboratorio.entity.*;
import br.ufac.laboratorio.exception.*;

public class LaboratorioLogic {
	private LaboratorioDB ldb;
	
	public LaboratorioLogic(Conexao cnx) {
		this.ldb = new LaboratorioDB(cnx);
	}
	
	public boolean addLaboratorio(String nome) throws 
	InvalidFieldException, 
	DataBaseGenericException, 
	EntityAlreadyExistException, 
	DataBaseNotConnectedException {
		
		List<String> camposInvalidos = new ArrayList<>();
		boolean testField = false;

		if(nome.isEmpty() || nome.length() > 100) {
			testField = true;
			camposInvalidos.add("Nome");
		}

		if(testField) {
			throw new InvalidFieldException("Laboratorio", camposInvalidos);
		}
		
		Laboratorio l = new Laboratorio(nome);
		return ldb.addLaboratorio(l);		
	}
	
	public Laboratorio getLaboratorioId(int id) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException {

	return ldb.getLaboratorio(id);
	}

	public  Laboratorio getLaboratorioNome(String nome) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException {

		return ldb.getLaboratorioNome(nome);
	}
	
	public boolean updLaboratorio(int id, String nome) throws 
	InvalidFieldException, 
	DataBaseGenericException, 
	DataBaseNotConnectedException, 
	EntityNotExistException {
		
		List<String> camposInvalidos = new ArrayList<>();
		boolean testField = false;
		
		if(nome.isEmpty() || nome.length() > 100) {
			testField = true;
			camposInvalidos.add("Nome");
		}

		if(testField) {
			throw new InvalidFieldException("Laboratorio", camposInvalidos);
		}
		
		Laboratorio l = new Laboratorio(id, nome);
		return ldb.updLaboratorio(l);		
	}
	
	
}
