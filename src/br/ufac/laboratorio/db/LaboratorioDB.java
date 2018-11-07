package br.ufac.laboratorio.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.ufac.laboratorio.entity.*;
import br.ufac.laboratorio.exception.*;


public class LaboratorioDB {
	private Conexao cnx;
	private ResultSet rs;

	public LaboratorioDB(Conexao cnx) {
		this.cnx = cnx;
	}

	public boolean addLaboratorio(Laboratorio l) throws
	DataBaseGenericException,
	EntityAlreadyExistException,
	DataBaseNotConnectedException {

		String sqlIncluir = "INSERT INTO laboratorios (nome) VALUES ('" + l.getNome() + "');";
		try {
			getLaboratorio(l.getId());
			throw new EntityAlreadyExistException("Laboratorio (id ='" + l.getId() +"')");
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
		String sqlBusca = "SELECT id, nome FROM laboratorios WHERE id = '" + id + "';";
		rs = cnx.consulte(sqlBusca);
		try {
			if(rs.next())
				l = new Laboratorio(rs.getInt(1), rs.getString(2));
			else
				throw new EntityNotExistException("Laboratorio (id = '"+ id +"')");
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
		String sqlAtualiza = "UPDATE laboratorios SET nome = '" + l.getNome() + " WHERE id = '" + l.getId() +"';";

		getLaboratorio(l.getId());
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
	EntityTableIsEmptyException {

		Laboratorio l = null;
		List<Laboratorio> laboratorios = new ArrayList<>();
		String sqlBusca = "SELECT id, nome FROM laboratorios;";

		rs = cnx.consulte(sqlBusca);

		try {
			if(rs.next()) {
				rs.beforeFirst();
				while(rs.next()) {
					l = new Laboratorio(rs.getInt(1), rs.getString(2));
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
	EntityTableIsEmptyException {

		Laboratorio l = null;
		List<Laboratorio> laboratorios = new ArrayList<>();

		String sqlBusca = "SELECT id, nome FROM laboratorios WHERE nome LIKE '%" + nome + "%';";
		rs = cnx.consulte(sqlBusca);
		try {
			if(rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					l = new Laboratorio(rs.getInt(1), rs.getString(2));
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
