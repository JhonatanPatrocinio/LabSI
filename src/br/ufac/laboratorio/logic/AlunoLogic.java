package br.ufac.laboratorio.logic;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import br.ufac.laboratorio.db.*;
import br.ufac.laboratorio.entity.*;
import br.ufac.laboratorio.exception.*;

public class AlunoLogic {
	private AlunoDB adb;
	private CursoLogic cl;
	private LoginLogic loginLogic;
	
	public AlunoLogic(Conexao cnx) {
		this.adb = new AlunoDB(cnx);
		this.cl = new CursoLogic(cnx);
		this.loginLogic = new LoginLogic(cnx);
	}
	
	public boolean addAluno(String matricula, String nome, int curso1, String login, String senha, int tipo) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException, 
	EntityAlreadyExistException,
	InvalidFieldException, 
	EntityNotExistException,
	EntityLoginNotExistException,
	NoSuchAlgorithmException,
	UnsupportedEncodingException, 
	EntityLoginAlreadyExistException{
		
		Curso curso = null;
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
		
		try {
			curso = cl.getCursoCod(curso1);
		} catch (EntityNotExistException e) {
			testField = true;
			camposInvalidos.add("Curso");
		}
		
		try {
			login1 = loginLogic.getLogin(login);
			testField = true;
			camposInvalidos.add("Login já existe");
			throw new EntityAlreadyExistException("Login");
		}catch (Exception e) {
			
		}

		if (testField){
			throw new InvalidFieldException("Aluno", camposInvalidos);
		}
		try {
		loginLogic.addLogin(login, senha, tipo);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		login1 = loginLogic.getLogin(login);

		Aluno aluno = new Aluno(matricula, nome, curso, login1);

		return adb.addAluno(aluno);
	}
	
	public Aluno getAluno(String matricula) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException,
	EntityLoginNotExistException {

		return adb.getAluno(matricula);

	}
	
	public Aluno getAluno(int id) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException,
	EntityLoginNotExistException {

		return adb.getAlunoId(id);

	}
	
	public Aluno getAlunoIdSenha(int idSenha) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException,
	EntityLoginNotExistException {

		return adb.getAlunoIdSenha(idSenha);

	}
	
	public boolean updAluno(int id, String matricula, String nome, String login, String senha, int tipo) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityNotExistException,
		InvalidFieldException,
		EntityLoginNotExistException,
		NoSuchAlgorithmException, 
		UnsupportedEncodingException {

		Curso curso = null;
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
		
		if (testField){
			throw new InvalidFieldException("Aluno", camposInvalidos);
		}
		try {
			loginLogic.updLogin(login, senha, tipo);
		}catch (Exception e) {
			testField = true;
			camposInvalidos.add("Login");
			throw new InvalidFieldException("Aluno", camposInvalidos);
		}


		login1 = loginLogic.getLogin(login);
		Aluno aluno = new Aluno(id, matricula, nome, curso, login1);

		return adb.updAluno(aluno);

	}
	
	public boolean delAluno(int idAluno) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException, 
	EntityNotExistException, 
	EntityLoginNotExistException {		
	
	Aluno aluno = adb.getAlunoId(idAluno);

	return adb.delAluno(aluno);

	}

	public List<Aluno> getAlunos() throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityTableIsEmptyException, 
		EntityNotExistException,
		EntityLoginNotExistException {

		return adb.getAlunos();
	}

	public List<Aluno> getAlunosPorNome(String nome) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityTableIsEmptyException, 
		EntityNotExistException,
		EntityLoginNotExistException {
		
		return adb.getAlunosPorNome(nome);
	}
}
