package br.ufac.laboratorio.test;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import br.ufac.laboratorio.db.CentroDB;
import br.ufac.laboratorio.db.Conexao;
import br.ufac.laboratorio.entity.Centro;
import br.ufac.laboratorio.entity.Login;
import br.ufac.laboratorio.exception.AccessDeniedForUserException;
import br.ufac.laboratorio.exception.DataBaseAlreadyConnectedException;
import br.ufac.laboratorio.exception.DataBaseGenericException;
import br.ufac.laboratorio.exception.DataBaseNotConnectedException;
import br.ufac.laboratorio.exception.EntityLoginAlreadyExistException;
import br.ufac.laboratorio.exception.InvalidFieldException;
import br.ufac.laboratorio.logic.LoginLogic;

public class LoginTeste {
	static final String DB_URL = "jdbc:mysql://localhost/laboratorio?useSSL=false";	
	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException, DataBaseNotConnectedException, EntityLoginAlreadyExistException, InvalidFieldException {
		
		LoginLogic loginLogic;
		Conexao cnx = new Conexao();
		
		try {
			cnx.conecte(DB_URL, "root", "1997");
		
//			loginLogic = new LoginLogic(cnx);
//			loginLogic.addLogin("admin", "senha123", 1);
			
			
			
		} catch (DataBaseAlreadyConnectedException | 
				AccessDeniedForUserException |
				DataBaseGenericException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		} finally {
			try {
				cnx.desconecte();
			} catch(DataBaseNotConnectedException | DataBaseGenericException e) {
				System.out.println(e.getMessage());
			}
		}
		
//		System.out.println(login.getLogin());
//		System.out.println(login.getSenha());
//		
//		String nova = login.TestaSenha("senha");
//		if(login.getSenha().equals(nova)) {
//			System.out.println("Iguais");
//		}else
//			System.out.println("Diferentes");
//	}
	}
}

