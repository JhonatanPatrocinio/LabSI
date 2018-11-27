package br.ufac.laboratorio.test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import br.ufac.laboratorio.db.*;
import br.ufac.laboratorio.entity.*;
import br.ufac.laboratorio.exception.*;

public class AlunoDBTeste {
	
	static final String DB_URL = "jdbc:mysql://localhost/laboratorio?useSSL=false";	
	public static void main(String[] args) throws DataBaseAlreadyConnectedException,
		DataBaseGenericException,
		AccessDeniedForUserException, EntityNotExistException, 
		DataBaseNotConnectedException, EntityLoginAlreadyExistException,
		EntityLoginNotExistException, EntityAlreadyExistException, NoSuchAlgorithmException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		
		Conexao cnx = new Conexao();
		try {
			cnx.conecte(DB_URL, "root", "1997");
			
			AlunoDB adb = new AlunoDB(cnx);
			LoginDB ldb = new LoginDB(cnx);
			CursoDB cdb = new CursoDB(cnx);
			
			Aluno aluno, aluno1;
			Login login;
			Curso curso;
			
			curso = new Curso(70, "VETV");
			//curso = cdb.getCursoId(1);
			login = new Login("andress", "1997", 3);
			//login = ldb.getLoginId(0);
			aluno = new Aluno("20160300030", "André Luisss", curso, login);
//			if (ldb.addLogin(login)) {
//				login = ldb.getLogin(login.getLogin());
//			} else
//				System.out.println("ERRO");
			
			cdb.addCurso(curso);
			curso = cdb.getCursoId(1);
			ldb.addLogin(login);
			login = ldb.getLogin(login.getLogin());
			adb.addAluno(aluno);
			aluno1 = adb.getAluno("20160300030");
			
			System.out.println(aluno1.getId());
			System.out.println(aluno1.getNome());
			System.out.println(aluno1.getCurso().getNome());
			System.out.println(aluno1.getLogin().getLogin());
			System.out.println(aluno1.getLogin().getSenha());
		}finally {
			try {
				cnx.desconecte();
			}catch(DataBaseNotConnectedException | DataBaseGenericException e) {
				System.out.println(e.getMessage());				
			}
		}
	}

}
