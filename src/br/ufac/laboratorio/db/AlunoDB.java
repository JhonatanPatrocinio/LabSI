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

public class AlunoDB {
	private Conexao cnx;
	private ResultSet rs;
	private CursoDB cdb;
	private LoginDB ldb;
	
	public AlunoDB(Conexao cnx) {
		this.cnx = cnx;
		cdb = new CursoDB(this.cnx);
		ldb = new LoginDB(this.cnx);
	}
	
	public boolean addAluno(Aluno a) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException, 
	EntityAlreadyExistException {
		
		String sqlIncluir = "INSERT INTO alunos ("
				+ "matricula, nome, id_curso, id_login) "
				+ "VALUES ('"+ a.getMatricula() +"',"
				+ "'" + a.getNome()			   +"',"
				+ "" + a.getCurso().getId()   +","
				+ "" + a.getLogin().getId()   +");";
		
		try {
			getAluno(a.getMatricula());
			throw new EntityAlreadyExistException("Aluno (matricula="+ a.getMatricula()+ ")");
		} catch(Exception e) {
			return cnx.atualiza(sqlIncluir) > 0;
		}
	}
	
	public Aluno getAluno(String matricula) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException, 
	EntityLoginNotExistException {
		
		String sqlbusca = "SELECT id, matricula, nome, id_curso, id_login"
				+" FROM alunos"
				+" WHERE matricula= "+ matricula + ";";
		
		Aluno aluno = null;
		Curso curso = null;
		Login login = null;
		rs = cnx.consulte(sqlbusca);
		try {
			if(rs.next()) {

				curso = cdb.getCursoId(rs.getInt(4));
				login = ldb.getLoginId(rs.getInt(5));
				aluno = new Aluno(rs.getInt(1), 
						rs.getString(2), 
						rs.getString(3),
						curso, login);
			} else
				throw new EntityNotExistException("Aluno (matricula=" + matricula + ")");
		} catch (SQLException e) {
			// TODO: handle exception
			throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
		}
		return aluno;
	}
	
	public Aluno getAlunoId(int id) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException, 
	EntityLoginNotExistException{
		
		String sqlBusca = "SELECT id, matricula, nome, id_curso, id_login"
				+" FROM alunos"
				+" WHERE id ="+ id + ";";
		
		Aluno aluno = null;
		Curso curso = null;
		Login login = null;
		rs = cnx.consulte(sqlBusca);
		try {
			if(rs.next()) {

				curso = cdb.getCursoId(rs.getInt(4));
				login = ldb.getLoginId(rs.getInt(5));
				aluno = new Aluno(rs.getInt(1), 
						rs.getString(2), 
						rs.getString(3), 
						curso, login);
			} else
				throw new EntityNotExistException("Aluno (Id =" + id + ")");
		} catch (SQLException e) {
			throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
		}
		return aluno;
	}
	
	public Aluno getAlunoIdSenha(int id) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException, 
	EntityLoginNotExistException{
		
		String sqlBusca = "SELECT id, matricula, nome, id_curso, id_login"
				+" FROM alunos"
				+" WHERE id_login ="+ id + ";";
		
		Aluno aluno = null;
		Curso curso = null;
		Login login = null;
		rs = cnx.consulte(sqlBusca);
		try {
			if(rs.next()) {

				curso = cdb.getCursoId(rs.getInt(4));
				login = ldb.getLoginId(rs.getInt(5));
				aluno = new Aluno(rs.getInt(1), 
						rs.getString(2), 
						rs.getString(3), 
						curso, login);
			} else
				throw new EntityNotExistException("Aluno (Id =" + id + ")");
		} catch (SQLException e) {
			throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
		}
		return aluno;
	}
	
	public boolean updAluno(Aluno a) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException, 
	EntityLoginNotExistException {

		String sqlAtualiza = "UPDATE alunos "
				+ " SET nome = '"+ a.getNome()				+"', "
				+ "id_login = '" + a.getLogin().getId()		+"' "	
				+ " WHERE id = " + a.getId()				+";";	
		getAlunoId(a.getId());
		return cnx.atualiza(sqlAtualiza) > 0;
	}
	
	public boolean delAluno(Aluno a) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException,
	EntityLoginNotExistException {

		String sqlDelete = "DELETE FROM alunos " + " WHERE id = " + a.getId() + ";";
		getAlunoId(a.getId());
		ldb.delLogin(a.getLogin());
		return cnx.atualiza(sqlDelete) > 0;		
	}
	
	public List<Aluno> getAlunos() throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityTableIsEmptyException,
	EntityNotExistException,
	EntityLoginNotExistException {
		
		List<Aluno> listaAlunos = new ArrayList<Aluno>();
		String sqlBusca = "SELECT id, matricula, nome, id_curso, id_login FROM alunos";
		Aluno aluno = null;
		Curso curso = null;
		Login login = null;
		
		rs = cnx.consulte(sqlBusca);
		try {
			if(rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					curso = cdb.getCursoId(rs.getInt(4));
					login = ldb.getLoginId(rs.getInt(5));
					aluno = new Aluno(rs.getInt(1), rs.getString(2),
							rs.getString(3), curso, login);
					listaAlunos.add(aluno);
				}
			} else
				throw new EntityTableIsEmptyException("Aluno");
		} catch (SQLException e) {
			// TODO: handle exception
			throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
		}
		return listaAlunos;
	}
	
	public List<Aluno> getAlunosPorNome(String nome) throws
	DataBaseGenericException,
	EntityTableIsEmptyException,
	EntityNotExistException,
	DataBaseNotConnectedException,
	EntityLoginNotExistException {
		
		List<Aluno> listaAlunosPorNome = new ArrayList<>();
		
		String sqlBuscaNome = "SELECT id, matricula, nome, id_curso, id_login"
				+ "FROM alunos WHERE nome LIKE '%"+ nome+ "%';";
		Aluno aluno = null;
		Curso curso = null;
		Login login = null;
		rs = cnx.consulte(sqlBuscaNome);
		try {
			if(rs.next()) {
				rs.beforeFirst();
				while(rs.next()) {
					curso = cdb.getCurso(rs.getInt(4));
					login = ldb.getLoginId(rs.getInt(5));
					aluno = new Aluno(rs.getInt(1), rs.getString(2),
					rs.getString(3), curso, login);
					listaAlunosPorNome.add(aluno);
				}
			} else
				throw new EntityTableIsEmptyException("Aluno");
		} catch (SQLException e) {
			// TODO: handle exception
			throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
		}
		return listaAlunosPorNome;
	}
}
