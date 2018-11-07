package br.ufac.laboratorio.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufac.laboratorio.entity.*;
import br.ufac.laboratorio.exception.*;

public class CursoDB {
	private Conexao cnx;
	private ResultSet rs;

	public CursoDB(Conexao cnx) {
		this.cnx = cnx;
	}

	public boolean addCurso(Curso c) throws
	DataBaseGenericException,
	EntityAlreadyExistException,
	DataBaseNotConnectedException {

		String sqlIncluir = "INSERT INTO cursos (cod, nome) VALUES ("+ c.getCod()+ ", '" + c.getNome() + "');";
		try {
			getCurso(c.getCod());
			throw new EntityAlreadyExistException("Curso (codigo='" + c.getCod()+"')");
		} catch (EntityNotExistException e) {
			// TODO: handle exception
			return cnx.atualiza(sqlIncluir) > 0;
		}
	}

	public Curso getCurso(int cod) throws 
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException {

		Curso c = null;
		String sqlBusca = "SELECT id, cod, nome FROM cursos WHERE cod = " + cod + ";";
		rs = cnx.consulte(sqlBusca);
		try {
			if(rs.next())
				c = new Curso(rs.getInt(1), rs.getInt(2), rs.getString(3));
			else
				throw new EntityNotExistException("Cursos (codigo = '"+ cod +"')");
		}catch (SQLException e) {
			// TODO: handle exception
			throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
		}
		return c;
	}

	public boolean updCurso(Curso c) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException {

		
		String sqlAtualiza = "UPDATE cursos SET nome = '" + c.getNome() + " WHERE cod = '" + c.getCod() +"';";

		getCurso(c.getCod());
		return cnx.atualiza(sqlAtualiza) > 0;
	}

	public boolean delCurso(Curso c) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException {

		String sqlDelete = "DELETE FROM cursos WHERE cod = '" + c.getCod() + "';";

		getCurso(c.getCod());
		return cnx.atualiza(sqlDelete) > 0;

	}

	public List<Curso> getCursos() throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityTableIsEmptyException {

		Curso c = null;
		List<Curso> cursos = new ArrayList<>();
		String sqlBusca = "SELECT id, cod, nome FROM cursos;";

		rs = cnx.consulte(sqlBusca);

		try {
			if(rs.next()) {
				rs.beforeFirst();
				while(rs.next()) {
					c = new Curso(rs.getInt(1), rs.getInt(2), rs.getString(3));
					cursos.add(c);
				}
			} else
				throw new EntityTableIsEmptyException("Cursos");
		} catch (SQLException e) {
			// TODO: handle exception
			throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
		}

		return cursos;
	}

	public List<Curso> getCursosPorNome(String nome) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityTableIsEmptyException {

		Curso c = null;
		List<Curso> cursos = new ArrayList<>();

		String sqlBusca = "SELECT id, cod, nome FROM cursos WHERE nome LIKE '%" + nome + "%';";
		rs = cnx.consulte(sqlBusca);
		try {
			if(rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					c = new Curso(rs.getInt(1), rs.getInt(2), rs.getString(3));
					cursos.add(c);
				}
			} else 
				throw new EntityTableIsEmptyException("Cursos");
		} catch (SQLException e) {
			// TODO: handle exception
			throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
		}

		return cursos;
	}

}
