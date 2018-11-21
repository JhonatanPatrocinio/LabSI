package br.ufac.laboratorio.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.ufac.laboratorio.entity.*;
import br.ufac.laboratorio.exception.*;
import br.ufac.laboratorio.logic.CentroLogic;


public class LaboratorioDB {
	private Conexao cnx;
	private ResultSet rs;
	private CentroLogic cl;

	public LaboratorioDB(Conexao cnx) {
		this.cnx = cnx;
		this.cl = new CentroLogic(cnx);
	}

	public boolean addLaboratorio(Laboratorio l) throws
	DataBaseGenericException,
	EntityAlreadyExistException,
	DataBaseNotConnectedException {

		String sqlIncluir = "INSERT INTO laboratorios (nome, id_centro, computadores) VALUES ('" 
				+ l.getNome()			+ "', '"
				+ l.getCentro().getId() +", '"
				+ l.getComputadores()	+");";
		try {
			getLaboratorioNome(l.getNome());
			throw new EntityAlreadyExistException("Laboratorio (Nome ='" + l.getNome() +"')");
		} catch (EntityNotExistException e) {
			// TODO: handle exception
			return cnx.atualiza(sqlIncluir) > 0;
		}
	}

	public Laboratorio getLaboratorio(int id) throws 
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException {

		Laboratorio l = null;
		Centro centro = null;
		String sqlBusca = "SELECT id, nome, id_centro, computadores FROM laboratorios WHERE id = '" + id + "';";
		rs = cnx.consulte(sqlBusca);
		try {
			if(rs.next()) {
				centro = cl.getCentroId(rs.getInt(3));
				l = new Laboratorio(rs.getInt(1), rs.getString(2), centro, rs.getInt(4));
			}else
				throw new EntityNotExistException("Laboratorio (id = '"+ id +"')");
		}catch (SQLException e) {
			// TODO: handle exception
			throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
		}
		return l;
	}

	public Laboratorio getLaboratorioNome(String nome) throws 
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException {

		Laboratorio l = null;
		Centro centro = null;
		String sqlBusca = "SELECT id, nome, id_centro, computadores FROM laboratorios WHERE nome LIKE '%" + nome + "%';";
		rs = cnx.consulte(sqlBusca);
		try {
			if(rs.next()) {
				centro = cl.getCentroId(rs.getInt(3));
				l = new Laboratorio(rs.getInt(1), rs.getString(2), centro, rs.getInt(4));
			}else
				throw new EntityNotExistException("Laboratorio (nome = '"+ nome +"')");
		}catch (SQLException e) {
			// TODO: handle exception
			throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
		}
		return l;
	}

	public boolean updLaboratorio(Laboratorio l) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException {

		//Analisar essa STRING DE UPDATE PARA ALTERAR NOME COM CHAVE PRIMARIA
		String sqlAtualiza = "UPDATE laboratorios "
				+ "SET nome = '" 	+ l.getNome()			+ "', "
				+ "id_centro = '"	+ l.getCentro().getId()	+ "', "
				+ "computadores = '"+ l.getComputadores()	+ "' "
				+ " WHERE id = " 	+ l.getId() 			+";";

		getLaboratorioNome(l.getNome());
		return cnx.atualiza(sqlAtualiza) > 0;
	}

	public boolean delLaboratorio(Laboratorio l) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException {

		String sqlDelete = "DELETE FROM laboratorios WHERE id = '" + l.getId() + "';";

		getLaboratorio(l.getId());
		return cnx.atualiza(sqlDelete) > 0;

	}

	public List<Laboratorio> getLaboratorios() throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityTableIsEmptyException, 
	EntityNotExistException {

		Laboratorio l = null;
		Centro centro = null;
		List<Laboratorio> laboratorios = new ArrayList<>();
		String sqlBusca = "SELECT id, nome FROM laboratorios;";

		rs = cnx.consulte(sqlBusca);

		try {
			if(rs.next()) {
				rs.beforeFirst();
				while(rs.next()) {
					centro = cl.getCentroId(rs.getInt(3));
					l = new Laboratorio(rs.getInt(1), rs.getString(2), centro, rs.getInt(4));
					laboratorios.add(l);
				}
			} else
				throw new EntityTableIsEmptyException("Laboratorio");
		} catch (SQLException e) {
			// TODO: handle exception
			throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
		}

		return laboratorios;
	}

	public List<Laboratorio> getLaboratoriosPorNome(String nome) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityTableIsEmptyException,
	EntityNotExistException {

		Laboratorio l = null;
		Centro centro = null;
		List<Laboratorio> laboratorios = new ArrayList<>();

		String sqlBusca = "SELECT id, nome FROM laboratorios WHERE nome LIKE '%" + nome + "%';";
		rs = cnx.consulte(sqlBusca);
		try {
			if(rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					centro = cl.getCentroId(rs.getInt(3));
					l = new Laboratorio(rs.getInt(1), rs.getString(2), centro, rs.getInt(4));
					laboratorios.add(l);
				}
			} else 
				throw new EntityTableIsEmptyException("Laboratorio");
		} catch (SQLException e) {
			// TODO: handle exception
			throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
		}

		return laboratorios;
	}

}
