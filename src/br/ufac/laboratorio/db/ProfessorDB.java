package br.ufac.laboratorio.db;

import java.sql.*;
import java.util.*;

import br.ufac.laboratorio.entity.*;
import br.ufac.laboratorio.exception.DataBaseGenericException;
import br.ufac.laboratorio.exception.DataBaseNotConnectedException;
import br.ufac.laboratorio.exception.EntityAlreadyExistException;
import br.ufac.laboratorio.exception.EntityLoginNotExistException;
import br.ufac.laboratorio.exception.EntityNotExistException;
import br.ufac.laboratorio.exception.EntityTableIsEmptyException;

public class ProfessorDB {
	private Conexao cnx;
	private ResultSet rs;
	private CentroDB cdb;
	private LoginDB ldb;

	public ProfessorDB(Conexao cnx) {
		this.cnx = cnx;
		cdb = new CentroDB(this.cnx);
		ldb = new LoginDB(this.cnx);
	}

	public boolean addProfessor(Professor p) throws 
	DataBaseGenericException, 
	DataBaseNotConnectedException, 
	EntityAlreadyExistException {

		String sqlIncluir = "INSERT INTO professores ("
				+ "matricula, nome, email, telefone, id_centro, id_login) "
				+ "VALUES ("+ p.getMatricula()	+","
				+ "'" + p.getNome()				+"',"
				+ "'" + p.getEmail()			+"',"
				+ "'" + p.getTelefone()			+"',"
				+ "" + p.getCentro().getId()	+","
				+ "" + p.getLogin().getId()	+");";

		try {
			getProfessor(p.getMatricula());
			throw new EntityAlreadyExistException("Professor (matricula="+ p.getMatricula()+ ")");
		} catch (Exception e) {
			// TODO: handle exception
			return cnx.atualiza(sqlIncluir) > 0;
		}
	}

	public Professor getProfessor(String matricula) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException, 
	EntityLoginNotExistException {

		String sqlBusca = "SELECT id, matricula, nome, email, telefone, id_centro, id_login"
				+" FROM professores "
				+" WHERE matricula= "+ matricula + ";";

		Professor professor = null;
		Centro centro = null;
		Login login = null;
		rs = cnx.consulte(sqlBusca);
		try {
			if(rs.next()) {

				centro = cdb.getCentroId(rs.getInt(6));
				login = ldb.getLoginId(rs.getInt(7));
				professor = new Professor(rs.getInt(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getString(4),
						rs.getString(5),
						centro, login);
			} else
				throw new EntityNotExistException("Professor (matricula=" + matricula + ")");
		} catch (SQLException e) {
			// TODO: handle exception
			throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
		}
		return professor;
	}

	public Professor getProfessorId(int id) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException, 
	EntityLoginNotExistException {

		String sqlBusca = "SELECT id, matricula, nome, email, telefone, id_centro, id_login"
				+" FROM professores "
				+" WHERE id = "+ id + ";";

		Professor professor = null;
		Centro centro = null;
		Login login = null;
		rs = cnx.consulte(sqlBusca);
		try {
			if(rs.next()) {

				centro = cdb.getCentroId(rs.getInt(6));
				login = ldb.getLoginId(rs.getInt(7));
				professor = new Professor(rs.getInt(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getString(4),
						rs.getString(5),
						centro, login);
			} else
				throw new EntityNotExistException("Professor (Id =" + id + ")");
		} catch (SQLException e) {
			// TODO: handle exception
			throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
		}
		return professor;
	}

	public Professor getProfessorIdSenha(int id) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException, 
	EntityLoginNotExistException {

		String sqlBusca = "SELECT id, matricula, nome, email, telefone, id_centro, id_login"
				+" FROM professores "
				+" WHERE id_login = "+ id + ";";

		Professor professor = null;
		Centro centro = null;
		Login login = null;
		rs = cnx.consulte(sqlBusca);
		try {
			if(rs.next()) {

				centro = cdb.getCentroId(rs.getInt(6));
				login = ldb.getLoginId(rs.getInt(7));
				professor = new Professor(rs.getInt(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getString(4),
						rs.getString(5),
						centro, login);
			} else
				throw new EntityNotExistException("Professor (Id =" + id + ")");
		} catch (SQLException e) {
			// TODO: handle exception
			throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
		}
		return professor;
	}


	public boolean updProfessor(Professor p) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException, 
	EntityLoginNotExistException {

		String sqlAtualiza = "UPDATE professores "
				+ " SET nome = '"+ p.getNome()				+"', "
				+ "email = '" 	 + p.getEmail()				+"', "
				+ "telefone = '" + p.getTelefone()			+"', "
				+ "id_login = '" + p.getLogin().getId()		+"' "	
				+ " WHERE id = " + p.getId()				+";";	
		getProfessorId(p.getId());
		return cnx.atualiza(sqlAtualiza) > 0;
	}

	public boolean delProfessor(Professor p) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException,
	EntityLoginNotExistException {

		String sqlDelete = "DELETE FROM professores " + " WHERE id = " + p.getId() + ";";
		getProfessorId(p.getId());
		ldb.delLogin(p.getLogin());
		return cnx.atualiza(sqlDelete) > 0;		
	}


	public List<Professor> getProfessores() throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityTableIsEmptyException,
	EntityNotExistException,
	EntityLoginNotExistException {

		List <Professor> listaProfessores = new ArrayList<Professor>();
		String sqlBusca = "SELECT id, matricula, nome, email, telefone, id_centro, id_login FROM professores;";
		Professor professor = null;
		Centro centro = null;
		Login login = null;

		rs = cnx.consulte(sqlBusca);
		try {
			if(rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					centro = cdb.getCentroId(rs.getInt(6));
					login = ldb.getLoginId(rs.getInt(7));
					professor = new Professor(rs.getInt(1), rs.getString(2),
							rs.getString(3), rs.getString(4),
							rs.getString(5), centro, login);
					listaProfessores.add(professor);
				}
			} else
				throw new EntityTableIsEmptyException("Professor");
		} catch (SQLException e) {
			// TODO: handle exception
			throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
		}
		return listaProfessores;
	}

	public List<Professor> getProfessoresPorNome(String nome) throws
	DataBaseGenericException,
	EntityTableIsEmptyException,
	EntityNotExistException,
	DataBaseNotConnectedException,
	EntityLoginNotExistException {

		List<Professor> listaProfessoresPorNome = new ArrayList<>();

		String sqlBuscaNome = "SELECT id, matricula, nome, email, telefone, id_centro, id_login"
				+ " FROM professores WHERE nome LIKE '%"+ nome +"%';";
		Professor professor = null;
		Centro centro = null;
		Login login = null;
		rs = cnx.consulte(sqlBuscaNome);
		try {
			if(rs.next()) {
				rs.beforeFirst();
				while(rs.next()) {
					centro = cdb.getCentro(rs.getString(6));
					login = ldb.getLoginId(rs.getInt(7));
					professor = new Professor(rs.getInt(1), rs.getString(2),
							rs.getString(3), rs.getString(4),
							rs.getString(5), centro, login);
					listaProfessoresPorNome.add(professor);
				}
			} else
				throw new EntityTableIsEmptyException("Professor");
		} catch (SQLException e) {
			// TODO: handle exception
			throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
		}
		return listaProfessoresPorNome;

	}


}
