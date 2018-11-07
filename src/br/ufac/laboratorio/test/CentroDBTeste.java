package br.ufac.laboratorio.test;

import br.ufac.laboratorio.db.*;
import br.ufac.laboratorio.entity.Centro;
import br.ufac.laboratorio.exception.*;

public class CentroDBTeste {

	static final String DB_URL = "jdbc:mysql://localhost/laboratorio?useSSL=false";	
	public static void main(String[] args) throws DataBaseNotConnectedException, EntityNotExistException, EntityAlreadyExistException {
		// TODO Auto-generated method stub
		
		Conexao cnx = new Conexao();
		
		try {
			cnx.conecte(DB_URL, "root", "1995");
			
			CentroDB cdb = new CentroDB(cnx);
			Centro c1, c2, c3, c4;
			
			c1 = new Centro("CCET", "Centro Ciencias Exatas da Terra");
			c2 = new Centro("CFCH", "Centro de Filosofia e Ciências Humanas");
			c3 = new Centro("CCSD", "Centro de Ciências da Saúde e Desporto");
			
			//cdb.addCentro(c1);
			//cdb.addCentro(c2);
			//cdb.addCentro(c3);
			
			c4 = cdb.getCentro("CCET");
			
			System.out.println(c4.getId());
			System.out.println(c4.getSigla());
			System.out.println(c4.getNome());
			
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
		
	}

}
