package br.ufac.laboratorio.logic;

import java.util.*;
import br.ufac.laboratorio.db.*;
import br.ufac.laboratorio.entity.*;
import br.ufac.laboratorio.exception.*;

public class CentroLogic {
	private CentroDB cdb;

	public CentroLogic(Conexao cnx) {
		this.cdb = new CentroDB(cnx);
	}

	public boolean addCentro(String sigla, String nome) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityAlreadyExistException,
	InvalidFieldException {

		List<String> camposInvalidos = new ArrayList<>();
		boolean testField = false;

		if(sigla.isEmpty() || sigla.length() > 6) {
			testField = true;
			camposInvalidos.add("Sigla");
		}
		if(nome.isEmpty() || nome.length() > 100) {
			testField = true;
			camposInvalidos.add("Nome");
		}

		if(testField) {
			throw new InvalidFieldException("Centro", camposInvalidos);
		}

		Centro c = new Centro(sigla, nome);		
		return cdb.addCentro(c);
	}

	public Centro getCentroSigla(String sigla) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException {

		return cdb.getCentro(sigla);


	}

	public Centro getCentroId(int id) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException {

		return cdb.getCentroId(id);

	}

	public boolean updCentro(String sigla, String nome) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	InvalidFieldException,
	EntityNotExistException {

		List<String> camposInvalidos = new ArrayList<>();
		boolean testField = false;

		if(sigla.isEmpty() || sigla.length() > 5) {
			testField = true;
			camposInvalidos.add("Sigla");
		}
		if(nome.isEmpty() || nome.length() > 100) {
			testField = true;
			camposInvalidos.add("Nome");
		}

		if(testField) {
			throw new InvalidFieldException("Centro", camposInvalidos);
		}

		Centro c = new Centro(sigla, nome);		
		return cdb.updCentro(c);
	}


	public boolean delCentro(String sigla, String nome) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException, 
	EntityNotExistException {
		Centro c = new Centro(sigla, nome);
		return cdb.delCentro(c);
	}

	public List<Centro> getCentros() throws
	DataBaseGenericException,
	DataBaseNotConnectedException, 
	EntityTableIsEmptyException {
		return cdb.getCentros();
	}

	public List<Centro> getCentrosPorNome(String nome) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityTableIsEmptyException {
		return cdb.getCentrosPorNome(nome);
	}
}
