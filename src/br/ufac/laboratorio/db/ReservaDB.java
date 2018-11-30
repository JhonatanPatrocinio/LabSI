package br.ufac.laboratorio.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufac.laboratorio.entity.*;

import br.ufac.laboratorio.exception.*;
import br.ufac.laboratorio.logic.LaboratorioLogic;
import br.ufac.laboratorio.logic.ProfessorLogic;


public class ReservaDB {
	private Conexao cnx;
	private ResultSet rs;
	private ProfessorLogic pdb;
	private LaboratorioLogic ldb;

	public ReservaDB(Conexao cnx) {
		this.cnx = cnx;
		this.pdb = new ProfessorLogic(cnx);
		this.ldb = new LaboratorioLogic(cnx);
	}

	public boolean addReserva(Reserva r) throws
	DataBaseGenericException,
	EntityAlreadyExistException,
	DataBaseNotConnectedException,
	EntityLoginNotExistException, 
	DataExistException {

		String sqlIncluir = "INSERT INTO reservas (id_professor, id_laboratorio, data_reserva, horario_inicio, horario_termino,"
				+ " status, obs) "
				+ "VALUES ("+ r.getIdProfessor().getId()	+ ", " 
				+ r.getIdLaboratorio().getId() 				+ ", '"
				+ r.getData()								+ "', '"
				+ r.getHorarioInicio()						+ "', '"
				+ r.getHorarioTermino()						+ "', "
				+ r.getStatus() 							+ ", '" 
				+ r.getObs() + "');";
		try {
			getReservaData(r.getData(), r.getHorarioInicio(), r.getHorarioTermino(), r.getIdLaboratorio().getId());
			throw new DataExistException("Já existe Reserva no Dia '" + r.getDataUsuario() +"' e Horario de '"+ r.getHorarioInicio() +"' até '"
			+r.getHorarioTermino()+"')");
		} catch (EntityNotExistException e) {
			return cnx.atualiza(sqlIncluir) > 0;
		}
	}

	public Reserva getReservaData(String datas, String horaInicio, String horaTermino, int idLab) throws 
	DataBaseGenericException, 
	DataBaseNotConnectedException, 
	EntityNotExistException, 
	EntityLoginNotExistException {


		Reserva r = null;
		Professor professor = null;
		Laboratorio lab = null;
		String sqlBusca = "SELECT id, id_professor, id_laboratorio, data_reserva, horario_inicio, horario_termino," 
				+" status, obs FROM reservas "
				+"WHERE"
				+"("
				+"(horario_inicio BETWEEN '"+ horaInicio +"' AND '"+ horaTermino +"') OR "
				+"(horario_termino BETWEEN '"+ horaInicio +"' AND '"+ horaTermino +"') OR "
				+"('"+ horaInicio +"' BETWEEN horario_inicio AND horario_termino ) OR "
				+"('"+ horaTermino +"' BETWEEN horario_inicio AND horario_termino )"
				+")"
				+" AND data_reserva = '"+ datas +"' AND id_laboratorio = "+ idLab +";";

		rs = cnx.consulte(sqlBusca);
		try {
			if(rs.next()) {
				professor = pdb.getProfessorId(rs.getInt(2));
				lab = ldb.getLaboratorioId(rs.getInt(3));
				r = new Reserva(rs.getInt(1), professor, lab, rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7), rs.getString(8));
			}else
				throw new EntityNotExistException("Reserva (data = "+ datas +")");
		}catch (SQLException e) {
			throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
		}
		return r;
	}

	public Reserva getReservaId(int id) throws 
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException, 
	EntityLoginNotExistException {

		Reserva r = null;
		Professor professor = null;
		Laboratorio lab = null;
		String sqlBusca = "SELECT id, id_professor, id_laboratorio, data_reserva, horario_inicio, horario_termino,"
				+ " status, obs FROM reservas WHERE id = " + id + ";";
		rs = cnx.consulte(sqlBusca);
		try {
			if(rs.next()) {
				professor = pdb.getProfessorId(rs.getInt(2));
				lab = ldb.getLaboratorioId(rs.getInt(3));
				r = new Reserva(rs.getInt(1), professor, lab, rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7), rs.getString(8));
			}else
				throw new EntityNotExistException("Reserva (id = "+ id +")");
		}catch (SQLException e) {
			throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
		}
		return r;
	}

	public List<Reserva> getReservas() throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityTableIsEmptyException,
	EntityNotExistException,
	EntityLoginNotExistException {

		Reserva r = null;
		Professor professor = null;
		Laboratorio lab = null;
		List<Reserva> reservas = new ArrayList<>();
		String sqlBusca = "SELECT id, id_professor, id_laboratorio, data_reserva, horario_inicio, horario_termino, status, obs FROM reservas;";

		rs = cnx.consulte(sqlBusca);

		try {
			if(rs.next()) {
				rs.beforeFirst();
				while(rs.next()) {
					professor = pdb.getProfessorId(rs.getInt(2));
					lab = ldb.getLaboratorioId(rs.getInt(3));
					r = new Reserva(rs.getInt(1), professor, lab, rs.getString(4), rs.getString(5),
							rs.getString(6), rs.getInt(7), rs.getString(8));
					reservas.add(r);
				}
			} else
				throw new EntityTableIsEmptyException("Reservas");
		} catch (SQLException e) {
			throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
		}

		return reservas;
	}

	public List<Reserva> getReservasPorStatus(int status) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityTableIsEmptyException,
	EntityNotExistException,
	EntityLoginNotExistException {

		Reserva r = null;
		Professor professor = null;
		Laboratorio lab = null;
		List<Reserva> reservas = new ArrayList<>();

		String sqlBusca = "SELECT id, id_professor, id_laboratorio, data_reserva, horario_inicio, horario_termino, status, obs FROM"
				+ " reservas WHERE status = "+ status + ";";
		rs = cnx.consulte(sqlBusca);
		try {
			if(rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					professor = pdb.getProfessorId(rs.getInt(2));
					lab = ldb.getLaboratorioId(rs.getInt(3));
					r = new Reserva(rs.getInt(1), professor, lab, rs.getString(4), rs.getString(5),
							rs.getString(6), rs.getInt(7), rs.getString(8));
					reservas.add(r);
				}
			} else 
				throw new EntityTableIsEmptyException("Reservas Para Analises");
		} catch (SQLException e) {
			throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
		}

		return reservas;
	}
	
	public List<Reserva> getReservasPorProfessor(int idProf) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityTableIsEmptyException,
	EntityNotExistException,
	EntityLoginNotExistException {

		Reserva r = null;
		Professor professor = null;
		Laboratorio lab = null;
		List<Reserva> reservas = new ArrayList<>();

		String sqlBusca = "SELECT id, id_professor, id_laboratorio, data_reserva, horario_inicio, horario_termino, status, obs FROM"
				+ " reservas WHERE id_professor = "+ idProf + ";";
		rs = cnx.consulte(sqlBusca);
		try {
			if(rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					professor = pdb.getProfessorId(rs.getInt(2));
					lab = ldb.getLaboratorioId(rs.getInt(3));
					r = new Reserva(rs.getInt(1), professor, lab, rs.getString(4), rs.getString(5),
							rs.getString(6), rs.getInt(7), rs.getString(8));
					reservas.add(r);
				}
			} else 
				throw new EntityTableIsEmptyException("Reservas Para Analises");
		} catch (SQLException e) {
			throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
		}

		return reservas;
	}
}
