package br.ufac.laboratorio.logic;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import br.ufac.laboratorio.db.*;
import br.ufac.laboratorio.entity.*;
import br.ufac.laboratorio.exception.*;

public class LoginLogic {
	private LoginDB ldb;
	
	public LoginLogic(Conexao cnx) {
		this.ldb = new LoginDB(cnx);
	}
	
	public boolean addLogin(String login, String senha, int tipo) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityLoginAlreadyExistException,
	InvalidFieldException,
	NoSuchAlgorithmException,
	UnsupportedEncodingException {

		List<String> camposInvalidos = new ArrayList<>();
		boolean testField = false;

		if(login.isEmpty() || login.length() < 5) {
			testField = true;
			camposInvalidos.add("Login vazio ou menor que 5 caracteres");
		}
		if(senha.isEmpty() || senha.length() < 8) {
			testField = true;
			camposInvalidos.add("Senha deve possuir 8 ou mais caracteres");
		}
		if(tipo == 0) {
			testField = true;
			camposInvalidos.add("Tipo de Usuario");
		}

		if(testField) {
			throw new InvalidFieldException("Login", camposInvalidos);
		}

		Login l = new Login(login, senha, tipo);		
		return ldb.addLogin(l);
	}

	public Login getLogin(String login) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException,
	EntityLoginNotExistException {

		return ldb.getLogin(login);


	}

	public Login getLoginId(int id) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException, 
	EntityLoginNotExistException {

		return ldb.getLoginId(id);

	}

	public boolean updLogin(String login, String senha, int tipo) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	InvalidFieldException,
	EntityNotExistException, 
	EntityLoginNotExistException,
	NoSuchAlgorithmException,
	UnsupportedEncodingException {
		
		Login antigo = null;
		Login novo = null;
		List<String> camposInvalidos = new ArrayList<>();
		boolean testField = false;
		antigo = ldb.getLogin(login);
		novo = new Login(login, senha, tipo);
		if(novo.getSenha().equals(antigo.getSenha())) {
			testField = true;
			camposInvalidos.add("Senha igual a anterior");
		}
		if(senha.isEmpty() || senha.length() < 8) {
			testField = true;
			camposInvalidos.add("Senha deve possuir 8 ou mais caracteres");
		}

		if(testField) {
			throw new InvalidFieldException("Login", camposInvalidos);
			
		} else
			return ldb.updLogin(novo);
	}


	public boolean delLogin(String login, String senha, int tipo) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException, 
	EntityNotExistException, 
	NoSuchAlgorithmException, 
	UnsupportedEncodingException,
	EntityLoginNotExistException {
		
		Login l = new Login(login, senha, tipo);
		return ldb.delLogin(l);
	}

	public List<Login> getLogins() throws
	DataBaseGenericException,
	DataBaseNotConnectedException, 
	EntityTableIsEmptyException {
		
		return ldb.getLogins();
	}

}
