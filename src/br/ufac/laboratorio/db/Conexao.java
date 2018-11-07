package br.ufac.laboratorio.db;

import java.sql.*;

import br.ufac.laboratorio.exception.AccessDeniedForUserException;
import br.ufac.laboratorio.exception.DataBaseAlreadyConnectedException;
import br.ufac.laboratorio.exception.DataBaseGenericException;
import br.ufac.laboratorio.exception.DataBaseNotConnectedException;

public class Conexao {
	private Connection con = null;
	private Statement smt = null;
	private boolean conectado = false;

	public boolean conecte (String urlBanco, String userName, String userPasswd) throws 
		DataBaseAlreadyConnectedException,
		AccessDeniedForUserException,
		DataBaseGenericException {
		
		if(conectado)
			throw new DataBaseAlreadyConnectedException("laboratorio");
		else {
			try {
				con = DriverManager.getConnection(urlBanco, userName, userPasswd);
				conectado = true;
			} catch (SQLException e) {
				// TODO: handle exception
				conectado = false;
				if(e.getErrorCode() == 1045)
					throw new AccessDeniedForUserException(userName);
				else
					throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
			}
		}
		return conectado;
	}

	public void desconecte() throws DataBaseNotConnectedException, DataBaseGenericException {

		if(!conectado)
			throw new DataBaseNotConnectedException("laboratorio");
		else {
			try {
				con.close();
				conectado = false;
			} catch (SQLException e) {
				throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
			}
		}
	}

	public int atualiza(String sqlUpdate) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException {
		
		if(!conectado)
			throw new DataBaseNotConnectedException("laboratorio");
		else {
			try {
				smt = con.createStatement();
				return smt.executeUpdate(sqlUpdate);
			} catch(SQLException e) {
				throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
			}
		}
	}
	
	public ResultSet consulte (String sqlQuery) throws 
		DataBaseGenericException, 
		DataBaseNotConnectedException
{

	if(!conectado)
		throw new DataBaseNotConnectedException("laboratorio");
	else {
		try {
			smt = con.createStatement();
			return smt.executeQuery(sqlQuery);
		} catch (SQLException sqle) {
			throw new DataBaseGenericException(sqle.getErrorCode(), 
					sqle.getMessage());
		}
	}
}

}
