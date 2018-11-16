package br.ufac.laboratorio.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufac.laboratorio.entity.*;

import br.ufac.laboratorio.exception.*;


public class ReservaDB {
	private Conexao cnx;
	private ResultSet rs;
	private ProfessorDB pdb;
	private LaboratorioDB ldb;
	private DataDB dDB;

	public ReservaDB(Conexao cnx) {
		this.cnx = cnx;
		this.pdb = new ProfessorDB(this.cnx);
		this.ldb = new LaboratorioDB(this.cnx);
		this.dDB = new DataDB(this.cnx);
	}

	public boolean addReserva(Reserva r) throws
		DataBaseGenericException,
		EntityAlreadyExistException,
		DataBaseNotConnectedException,
		EntityLoginNotExistException {

		String sqlIncluir = "INSERT INTO reservas (id_professor, id_laboratorio, id_data, status, obs) "
				+ "VALUES ("+ r.getIdProfessor().getId()	+ ", " 
				+ r.getIdLaboratorio().getId() 				+ ", "
				+ r.getIdData().getId()						+ ", '" 
				+ r.getStatus() 					+ "', '" 
				+ r.getObs() + "');";
		try {
			getReserva(r.getId());
			throw new EntityAlreadyExistException("Reserva (id='" + r.getId() +"')");
		} catch (EntityNotExistException e) {
			// TODO: handle exception
			return cnx.atualiza(sqlIncluir) > 0;
		}
	}

	public Reserva getReserva(int id) throws 
		DataBaseGenericException,
		DataBaseNotConnectedException,
		EntityNotExistException, 
		EntityLoginNotExistException {

		Reserva r = null;
		Professor professor = null;
		Data data = null;
		Laboratorio lab = null;
		String sqlBusca = "SELECT id, id_professor, id_laboratorio, id_data, status, obs FROM reservas WHERE id = " + id + ";";
		rs = cnx.consulte(sqlBusca);
		try {
			if(rs.next()) {
				professor = pdb.getProfessorId(rs.getInt(2));
				lab = ldb.getLaboratorio(rs.getInt(3));
		//		data = dDB.getData(rs.getInt(4));
				r = new Reserva(rs.getInt(1), professor,
						lab, data,
						rs.getInt(5), rs.getString(6));
			}else
				throw new EntityNotExistException("Reserva (id = "+ id +")");
		}catch (SQLException e) {
			// TODO: handle exception
			throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
		}
		return r;
	}


	public boolean delReserva(Reserva r) throws
		DataBaseGenericException,
		DataBaseNotConnectedException,
		EntityNotExistException,
		EntityLoginNotExistException {

		String sqlDelete = "DELETE FROM reservas WHERE id = '" + r.getId() + "';";
		getReserva(r.getId());
		return cnx.atualiza(sqlDelete) > 0;

	}

	public List<Reserva> getReservas() throws
		DataBaseGenericException,
		DataBaseNotConnectedException,
		EntityTableIsEmptyException,
		EntityNotExistException,
		EntityLoginNotExistException {

		Reserva r = null;
		Professor professor = null;
		Data data = null;
		Laboratorio lab = null;
		List<Reserva> reservas = new ArrayList<>();
		String sqlBusca = "SELECT id, id_professor, id_laboratorio, id_data, status, obs FROM reservas;";

		rs = cnx.consulte(sqlBusca);

		try {
			if(rs.next()) {
				rs.beforeFirst();
				while(rs.next()) {
					professor = pdb.getProfessorId(rs.getInt(2));
					lab = ldb.getLaboratorio(rs.getInt(3));
					data = dDB.getDataId(rs.getInt(4));
					r = new Reserva(rs.getInt(1), professor,
							lab, data,
							rs.getInt(5), rs.getString(6));
					reservas.add(r);
				}
			} else
				throw new EntityTableIsEmptyException("Reservas");
		} catch (SQLException e) {
			// TODO: handle exception
			throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
		}

		return reservas;
	}

	public List<Reserva> getReservasPorData(Data data) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityTableIsEmptyException,
	EntityNotExistException,
	EntityLoginNotExistException {

		Reserva r = null;
		Professor professor = null;
		Data datas = null;
		Laboratorio lab = null;
		List<Reserva> reservas = new ArrayList<>();

		String sqlBusca = "SELECT id, id_professor, id_laboratorio, id_data, status, obs FROM"
				+ " reservas WHERE id_data LIKE '%" + data.getData() + "%';";
		rs = cnx.consulte(sqlBusca);
		try {
			if(rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					professor = pdb.getProfessorId(rs.getInt(2));
					lab = ldb.getLaboratorio(rs.getInt(3));
					data = dDB.getDataId(rs.getInt(4));
					r = new Reserva(rs.getInt(1), professor,
							lab, datas,
							rs.getInt(5), rs.getString(6));
					reservas.add(r);
				}
			} else 
				throw new EntityTableIsEmptyException("Reservas");
		} catch (SQLException e) {
			// TODO: handle exception
			throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
		}

		return reservas;
	}
}
