package br.ufac.laboratorio.logic;

import java.util.*;
import br.ufac.laboratorio.db.*;
import br.ufac.laboratorio.entity.*;
import br.ufac.laboratorio.exception.*;

public class CursoLogic {
	private CursoDB cdb;
	
	public CursoLogic(Conexao cnx) {
		this.cdb = new CursoDB(cnx);
	}
	
	public boolean addCurso(int cod, String nome) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityAlreadyExistException,
	InvalidFieldException{
		List<String> camposInvalidos = new ArrayList<>();
		boolean testField = false;

		if(cod == 0 || cod > 999999999) {
			testField = true;
			camposInvalidos.add("Curso");
		}
		if(nome.isEmpty() || nome.length() > 100) {
			testField = true;
			camposInvalidos.add("Nome");
		}

		if(testField) {
			throw new InvalidFieldException("Curso", camposInvalidos);
		}

		Curso c = new Curso(cod, nome);		
		return cdb.addCurso(c);
	}
	
	public Curso getCursoCod(int cod) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException {

		return cdb.getCurso(cod);


	}
	
	public Curso getCursoId(int id) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException {

		return cdb.getCursoId(id);

	}
	
	public boolean updCurso(int cod, String nome) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	InvalidFieldException,
	EntityNotExistException {

		List<String> camposInvalidos = new ArrayList<>();
		boolean testField = false;

		if(cod == 0 || cod > 999999999) {
			testField = true;
			camposInvalidos.add("Curso");
		}
		if(nome.isEmpty() || nome.length() > 100) {
			testField = true;
			camposInvalidos.add("Nome");
		}

		if(testField) {
			throw new InvalidFieldException("Curso", camposInvalidos);
		}

		Curso c = new Curso(cod, nome);		
		return cdb.updCurso(c);
	}
	
	public boolean delCurso(int cod, String nome) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException, 
	EntityNotExistException {
		Curso c = new Curso(cod, nome);
		return cdb.delCurso(c);
	}
	
	public List<Curso> getCursos() throws
	DataBaseGenericException,
	DataBaseNotConnectedException, 
	EntityTableIsEmptyException {
		return cdb.getCursos();
	}
	
	public List<Curso> getCursosPorNome(String nome) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityTableIsEmptyException {
		return cdb.getCursosPorNome(nome);
	}
}
