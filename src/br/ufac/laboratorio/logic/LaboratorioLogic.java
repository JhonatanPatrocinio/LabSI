package br.ufac.laboratorio.logic;

import java.util.ArrayList;
import java.util.List;

import br.ufac.laboratorio.db.*;
import br.ufac.laboratorio.entity.*;
import br.ufac.laboratorio.exception.*;

public class LaboratorioLogic {
	private LaboratorioDB ldb;
	private CentroLogic cl;
	
	public LaboratorioLogic(Conexao cnx) {
		this.ldb = new LaboratorioDB(cnx);
		this.cl = new CentroLogic(cnx);
	}
	
	public boolean addLaboratorio(String nome, int id_centro, int computadores) throws 
	InvalidFieldException, 
	DataBaseGenericException, 
	EntityAlreadyExistException, 
	DataBaseNotConnectedException {
		Centro centro = null;
		List<String> camposInvalidos = new ArrayList<>();
		boolean testField = false;

		if(nome.isEmpty() || nome.length() > 100) {
			testField = true;
			camposInvalidos.add("Nome");
		}
		if(computadores == 0) {
			testField = true;
			camposInvalidos.add("Quantidade de Computadores = 0");
		}

			try {
				centro = cl.getCentroId(id_centro);
			} catch (EntityNotExistException e) {
				testField = true;
				camposInvalidos.add("Centro");
			}
			if(testField) {
				throw new InvalidFieldException("Laboratorio", camposInvalidos);
			}
		
		Laboratorio l = new Laboratorio(nome, centro, computadores);
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
	
	public boolean updLaboratorio(int id, String nome, int id_centro, int computadores) throws 
	InvalidFieldException, 
	DataBaseGenericException, 
	DataBaseNotConnectedException, 
	EntityNotExistException {
		Centro centro = null;
		List<String> camposInvalidos = new ArrayList<>();
		boolean testField = false;
		
		if(nome.isEmpty() || nome.length() > 100) {
			testField = true;
			camposInvalidos.add("Nome");
		}
		if(computadores == 0) {
			testField = true;
			camposInvalidos.add("Quantidade de Computadores = 0");
		}

			try {
				centro = cl.getCentroId(id_centro);
			} catch (EntityNotExistException e) {
				testField = true;
				camposInvalidos.add("Centro");
			}

		if(testField) {
			throw new InvalidFieldException("Laboratorio", camposInvalidos);
		}
		
		Laboratorio l = new Laboratorio(id, nome, centro, computadores);
		return ldb.updLaboratorio(l);		
	}
	
	public List<Laboratorio> getLaboratorios() throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityTableIsEmptyException, 
	EntityNotExistException {
		return ldb.getLaboratorios();
		
	}
	
}
