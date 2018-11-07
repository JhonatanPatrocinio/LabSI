package br.ufac.laboratorio.test;

import br.ufac.laboratorio.db.*;
import br.ufac.laboratorio.entity.*;
import br.ufac.laboratorio.exception.*;


public class ProfessorDBTeste {

	static final String DB_URL = "jdbc:mysql://localhost/laboratorio?useSSL=false";	
	public static void main(String[] args) throws DataBaseAlreadyConnectedException,
		DataBaseGenericException,
		AccessDeniedForUserException, EntityNotExistException, 
		DataBaseNotConnectedException, EntityLoginAlreadyExistException,
		EntityLoginNotExistException, EntityAlreadyExistException {
		// TODO Auto-generated method stub
		
		Conexao cnx = new Conexao();
		try {
			cnx.conecte(DB_URL, "root", "1995");
			
			ProfessorDB pdb = new ProfessorDB(cnx);
			CentroDB cdb  = new CentroDB(cnx);
			LoginDB ldb = new LoginDB(cnx);
			
			Professor professor, professor1;
			Login login;
			Centro centro;
			
			
//			centro = cdb.getCentroId(1);
//			login = new Login("admin", "tesi1");
//			if (ldb.addLogin(login)) {
//				login = ldb.getLogin(login.getLogin());
//			} else
//				System.out.println("ERRO");
//			
			//professor = new Professor("20160304021", "Jhonatan", "jhonatan.patrocinio@gmail.com", "68999369820", centro, login);
			//pdb.addProfessor(professor);
				
			professor1 = pdb.getProfessor("20160304021");
			
			System.out.println(professor1.getId());
			System.out.println(professor1.getNome());
			System.out.println(professor1.getEmail());
			System.out.println(professor1.getTelefone());
			System.out.println(professor1.getCentro().getNome());
			System.out.println(professor1.getLogin().getLogin());
			System.out.println(professor1.getLogin().getSenha());
			
			
		} finally {
			try {
				cnx.desconecte();
			} catch(DataBaseNotConnectedException | DataBaseGenericException e) {
				System.out.println(e.getMessage());
			}
		}
		
	}

}
