package br.ufac.laboratorio.logic;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import br.ufac.laboratorio.db.*;
import br.ufac.laboratorio.entity.*;
import br.ufac.laboratorio.exception.*;

public class ProfessorLogic {
	private ProfessorDB pdb;
	private CentroLogic cl;
	private LoginLogic loginLogic;
	
	public ProfessorLogic(Conexao cnx) {
		this.pdb = new ProfessorDB(cnx);
		this.cl = new CentroLogic(cnx);
		this.loginLogic = new LoginLogic(cnx);
	}
	
	public boolean addProfessor(String matricula, String nome, String email, String telefone, String centro1, String login, String senha, int tipo) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException, 
	EntityAlreadyExistException,
	InvalidFieldException, 
	EntityNotExistException,
	EntityLoginNotExistException,
	NoSuchAlgorithmException,
	UnsupportedEncodingException, 
	EntityLoginAlreadyExistException {

		Centro centro = null;
		Login login1 = null;
		List<String> camposInvalidos = new ArrayList<String>();
		boolean testField = false;

		if(matricula.length() < 11){
			testField = true;
			camposInvalidos.add("Matrícula");
		}

		if(nome.isEmpty() || nome.length() > 100){
			testField = true;
			camposInvalidos.add("Nome");
		}
		if(email.isEmpty() || email.length() > 100 || !(email.contains("@"))) {
			testField = true;
			camposInvalidos.add("Email");
		}
		
		try {
			centro = cl.getCentroSigla(centro1);
		} catch (EntityNotExistException e) {
			testField = true;
			camposInvalidos.add("Centro");
		}
		
		try {
			login1 = loginLogic.getLogin(login);
			testField = true;
			camposInvalidos.add("Login já existe");
			throw new EntityAlreadyExistException("Login");
		}catch (Exception e) {
			
		}

		if (testField){
			throw new InvalidFieldException("Professor", camposInvalidos);
		}
		try {
		loginLogic.addLogin(login, senha, tipo);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		login1 = loginLogic.getLogin(login);

		Professor professor = new Professor(matricula, nome, email ,telefone, centro, login1);

		return pdb.addProfessor(professor);

	}

	public Professor getProfessor(String matricula) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException,
	EntityLoginNotExistException {

		return pdb.getProfessor(matricula);

	}
	
	public Professor getProfessor(int id) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException,
	EntityLoginNotExistException {

		return pdb.getProfessorId(id);

	}
	
	public Professor getProfessorIdSenha(int idSenha) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException,
	EntityLoginNotExistException {

		return pdb.getProfessorIdSenha(idSenha);

	}

	public boolean updProfessor(int id, String matricula, String nome, String email, String telefone,
			String login, String senha, int tipo) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityNotExistException,
		InvalidFieldException,
		EntityLoginNotExistException,
		NoSuchAlgorithmException, 
		UnsupportedEncodingException {

		Centro centro = null;
		Login login1 = null;
		List<String> camposInvalidos = new ArrayList<String>();
		boolean testField = false;

		if(matricula.length() < 11){
			testField = true;
			camposInvalidos.add("Matrícula");
		}

		if(nome.isEmpty() || nome.length() > 100){
			testField = true;
			camposInvalidos.add("Nome");
		}
		if(email.isEmpty() || email.length() > 100 || !(email.contains("@"))) {
			testField = true;
			camposInvalidos.add("Email");
		}
		
		if (testField){
			throw new InvalidFieldException("Professor", camposInvalidos);
		}
		try {
			loginLogic.updLogin(login, senha, tipo);
		}catch (Exception e) {
			testField = true;
			camposInvalidos.add("Login");
			throw new InvalidFieldException("Professor", camposInvalidos);
		}


		login1 = loginLogic.getLogin(login);
		Professor professor = new Professor(id, matricula, nome, email ,telefone, centro, login1);

		return pdb.updProfessor(professor);

	}

	public boolean delProfessor(int idProfessor) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityNotExistException, 
		EntityLoginNotExistException {		
		
		Professor professor = pdb.getProfessorId(idProfessor);

		return pdb.delProfessor(professor);

	}

	public List<Professor> getProfessores() throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityTableIsEmptyException, 
		EntityNotExistException,
		EntityLoginNotExistException {
		
		return pdb.getProfessores();
	}

	public List<Professor> getProfessoresPorNome(String nome) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityTableIsEmptyException, 
		EntityNotExistException,
		EntityLoginNotExistException {
		return pdb.getProfessoresPorNome(nome);
	}
	
}
