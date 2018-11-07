package br.ufac.laboratorio.db;

import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;

import br.ufac.laboratorio.entity.Centro;
import br.ufac.laboratorio.exception.DataBaseGenericException;
import br.ufac.laboratorio.exception.DataBaseNotConnectedException;
import br.ufac.laboratorio.exception.EntityAlreadyExistException;
import br.ufac.laboratorio.exception.EntityNotExistException;
import br.ufac.laboratorio.exception.EntityTableIsEmptyException;

public class CentroDB {
	private Conexao cnx;
	private ResultSet rs;
	
	public CentroDB(Conexao cnx) {
		this.cnx = cnx;
	}
	
	public boolean addCentro(Centro c) throws
		DataBaseGenericException,
		EntityAlreadyExistException,
		DataBaseNotConnectedException {
		
		String sqlIncluir = "INSERT INTO centros (sigla, nome) VALUES ('"+ c.getSigla() + "', '" + c.getNome() + "');" ;
		try {
			getCentro(c.getSigla());
			throw new EntityAlreadyExistException("Centro (sigla='" + c.getSigla() + "')");
		} catch (EntityNotExistException e) {
			// TODO: handle exception
			return cnx.atualiza(sqlIncluir) > 0;
		}
		
	}
	
	
	
	
	
	
	
	public Centro getCentro(String sigla) throws
		DataBaseGenericException,
		EntityNotExistException,
		DataBaseNotConnectedException {
		
		Centro c = null;
		String sqlBusca = "SELECT id, sigla, nome FROM centros WHERE sigla = '"+ sigla + "';";
		
		rs = cnx.consulte(sqlBusca);
		try {
			if(rs.next())
				c = new Centro(rs.getInt(1),rs.getString(2), rs.getString(3));
			else
				JOptionPane.showMessageDialog(null, new EntityNotExistException("Centro (sigla='" + sigla + "')") );
		} catch (SQLException e) {
			// TODO: handle exception
			throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
		}
		
		return c;		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public Centro getCentroId(int id) throws
	DataBaseGenericException,
	EntityNotExistException,
	DataBaseNotConnectedException {
	
	Centro c = null;
	String sqlBusca = "SELECT id, sigla, nome FROM centros WHERE id = '"+ id + "';";
	
	rs = cnx.consulte(sqlBusca);
	try {
		if(rs.next())
			c = new Centro(rs.getInt(1),rs.getString(2), rs.getString(3));
		else
			throw new EntityNotExistException("Centro (id='" + id + "')");
	} catch (SQLException e) {
		// TODO: handle exception
		throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
	}
	
	return c;		
}
	
	public boolean updCentro(Centro c) throws
		DataBaseGenericException,
		DataBaseNotConnectedException,
		EntityNotExistException {
		
		String slqAtualiza = "UPDATE centros SET nome = '" + c.getNome() + "' WHERE sigla = '" + c.getSigla() + "';";
		
		getCentro(c.getSigla());
		return cnx.atualiza(slqAtualiza) > 0;
	}
	
	public boolean delCentro(Centro c) throws
		DataBaseGenericException,
		DataBaseNotConnectedException,
		EntityNotExistException {
		
		String sqlDelete = "DELETE FROM centros WHERE sigla = '" + c.getSigla() + "';";
		
		getCentro(c.getSigla());
		return cnx.atualiza(sqlDelete) > 0;
	}
	
	public List<Centro> getCentros() throws
		DataBaseGenericException,
		DataBaseNotConnectedException,
		EntityTableIsEmptyException {
		
		Centro c = null;
		List<Centro> centros = new ArrayList<>();
		String sqlBusca = "SELECT id, sigla, nome FROM centros;";
		
		rs = cnx.consulte(sqlBusca);
		
		try {
			if(rs.next()) {
				rs.beforeFirst();
				while(rs.next()) {
					c = new Centro(rs.getInt(1),rs.getString(2), rs.getString(3));
					centros.add(c);
				}
			} else
				throw new EntityTableIsEmptyException("Centro");
		} catch (SQLException e) {
			// TODO: handle exception
			throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
		}
		
		return centros;
	}
	
	public List<Centro> getCentrosPorNome(String nome) throws
		DataBaseGenericException,
		DataBaseNotConnectedException,
		EntityTableIsEmptyException {
		
		Centro c = null;
		List<Centro> centros = new ArrayList<>();
		
		String sqlBusca = "SELECT id, sigla, nome FROM centros WHERE nome LIKE '%" + nome + "%';";
		rs = cnx.consulte(sqlBusca);
		try {
			if(rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					c = new Centro(rs.getInt(1),rs.getString(2), rs.getString(3));
					centros.add(c);
				}
			} else 
				throw new EntityTableIsEmptyException("Centro");
		} catch (SQLException e) {
			// TODO: handle exception
			throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
		}
		
		return centros;
	}
		
}
