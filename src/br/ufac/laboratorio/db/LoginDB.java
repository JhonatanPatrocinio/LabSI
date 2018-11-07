package br.ufac.laboratorio.db;

import java.sql.*;
import java.util.*;

import br.ufac.laboratorio.entity.*;
import br.ufac.laboratorio.exception.*;


public class LoginDB {
	private Conexao cnx;
	private ResultSet rs;

	public LoginDB(Conexao cnx) {
		this.cnx = cnx;
	}

	public boolean addLogin(Login l) throws
		DataBaseGenericException,
		EntityLoginAlreadyExistException,
		DataBaseNotConnectedException {

		String sqlIncluir = "INSERT INTO logins (login, senha, tipo) VALUES"
				+ " ('"+ l.getLogin() + "', '" + l.getSenha() + "', " + l.getTipo()+ ");";
		try {
			getLogin(l.getLogin());
			throw new EntityLoginAlreadyExistException(l.getLogin());
		} catch (EntityLoginNotExistException e) {
			// TODO: handle exception
			return cnx.atualiza(sqlIncluir) > 0;
		}

	}

	public Login getLogin(String login) throws
		DataBaseGenericException,
		EntityLoginNotExistException,
		DataBaseNotConnectedException {

		Login l = null;
		String sqlBusca = "SELECT id, login, senha, tipo FROM logins WHERE login = '"+ login + "';";

		rs = cnx.consulte(sqlBusca);
		try {
			if(rs.next())
				l = new Login(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
			else
				throw new EntityLoginNotExistException(login);
		} catch (SQLException e) {
			// TODO: handle exception
			throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
		}

		return l;		
	}
	
	public Login getLoginId(int id) throws
		DataBaseGenericException,
		EntityLoginNotExistException,
		DataBaseNotConnectedException {

		Login l = null;
		String sqlBusca = "SELECT id, login, senha, tipo FROM logins WHERE id = '"+ id + "';";

		rs = cnx.consulte(sqlBusca);
		try {
			if(rs.next())
				l = new Login(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
			else
				throw new EntityLoginNotExistException("id' "+ id + "';");
		} catch (SQLException e) {
			// TODO: handle exception
			throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
		}

		return l;		
	}

	public boolean updLogin(Login l) throws
		DataBaseGenericException,
		DataBaseNotConnectedException,
		EntityLoginNotExistException {

		String slqAtualiza = "UPDATE logins SET senha = '" + l.getSenha() + "' WHERE login = '" + l.getLogin() + "';";

		getLogin(l.getLogin());
		return cnx.atualiza(slqAtualiza) > 0;
	}

	public boolean delLogin(Login l) throws
		DataBaseGenericException,
		DataBaseNotConnectedException,
		EntityLoginNotExistException {

		String sqlDelete = "DELETE FROM logins WHERE login = '" + l.getLogin() + "';";

		getLogin(l.getLogin());
		return cnx.atualiza(sqlDelete) > 0;
	}

	public List<Login> getLogins() throws
		DataBaseGenericException,
		DataBaseNotConnectedException,
		EntityTableIsEmptyException {

		Login l = null;
		List<Login> logins = new ArrayList<>();
		String sqlBusca = "SELECT id, login, senha, tipo FROM logins;";

		rs = cnx.consulte(sqlBusca);

		try {
			if(rs.next()) {
				rs.beforeFirst();
				while(rs.next()) {
					l = new Login(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
					logins.add(l);
				}
			} else
				throw new EntityTableIsEmptyException("Login");
		} catch (SQLException e) {
			// TODO: handle exception
			throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
		}

		return logins;
	}

}
