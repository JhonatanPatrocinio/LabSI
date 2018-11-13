package br.ufac.laboratorio.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufac.laboratorio.entity.Data;
import br.ufac.laboratorio.exception.*;


public class DataDB {
	private Conexao cnx;
	private ResultSet rs;

	public DataDB(Conexao cnx) {
		this.cnx = cnx;
	}

	public boolean addData(Data d) throws
		DataBaseGenericException,
		EntityAlreadyExistException,
		DataBaseNotConnectedException {

		String sqlIncluir = "INSERT INTO datas (data_reserva, horario_inicio, horario_termino)"
				+ " VALUES ('" + d.getData()	+ "', '"
				+ d.getHorarioInicio()			+ "', '"
				+ d.getHorarioTermino()			+ "');";
		try {
			getDataId(d.getId());
			throw new EntityAlreadyExistException("Datas (id ='" + d.getId()+"')");
		} catch (EntityNotExistException e) {
			// TODO: handle exception
			return cnx.atualiza(sqlIncluir) > 0;
		}
	}

	public Data getDataId(int id) throws 
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException {

		Data d = null;
		String sqlBusca = "SELECT id, data_reserva, horario_inicio, horario_termino FROM datas WHERE id = '" + id + "';";
		rs = cnx.consulte(sqlBusca);
		try {
			if(rs.next())
				d = new Data(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			else
				throw new EntityNotExistException("Data (id = '"+ id +"')");
		}catch (SQLException e) {
			// TODO: handle exception
			throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
		}
		return d;
	}
	
	public Data getDataCompleta(String data, String horarioInicio, String horarioTermino) throws 
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException {

		Data d = null;
		String sqlBusca = "SELECT id, data_reserva, horario_inicio, horario_termino FROM datas "
				+ "WHERE data_reserva = '" + data + "' AND ;";
		rs = cnx.consulte(sqlBusca);
		try {
			if(rs.next())
				d = new Data(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			else
				throw new EntityNotExistException("Data (dia = '"+ data +"')");
		}catch (SQLException e) {
			// TODO: handle exception
			throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
		}
		return d;
	}
	
	public Data getData(String data) throws 
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException {

		Data d = null;
		String sqlBusca = "SELECT id, data_reserva, horario_inicio, horario_termino FROM datas WHERE data_reserva = '" + data + "';";
		rs = cnx.consulte(sqlBusca);
		try {
			if(rs.next())
				d = new Data(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			else
				throw new EntityNotExistException("Data (dia = '"+ data +"')");
		}catch (SQLException e) {
			// TODO: handle exception
			throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
		}
		return d;
	}
	
	public Data getDataHoraInicio(String horaInicio) throws 
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException {

		Data d = null;
		String sqlBusca = "SELECT id, data_reserva, horario_inicio, horario_termino FROM datas WHERE horario_inicio = '" + horaInicio + "';";
		rs = cnx.consulte(sqlBusca);
		try {
			if(rs.next())
				d = new Data(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			else
				throw new EntityNotExistException("Data (Horario Inicio = '"+ horaInicio +"')");
		}catch (SQLException e) {
			// TODO: handle exception
			throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
		}
		return d;
	}
	
	public Data getDataHoraTermino(String horaTermino) throws 
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException {

		Data d = null;
		String sqlBusca = "SELECT id, data_reserva, horario_inicio, horario_termino FROM datas WHERE horario_inicio = '" + horaTermino + "';";
		rs = cnx.consulte(sqlBusca);
		try {
			if(rs.next())
				d = new Data(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			else
				throw new EntityNotExistException("Data (Horario Termino = '"+ horaTermino +"')");
		}catch (SQLException e) {
			// TODO: handle exception
			throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
		}
		return d;
	}

	public boolean delData(Data d) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException {

		String sqlDelete = "DELETE FROM datas WHERE id = " + d.getId() + " ;";

		getDataId(d.getId());
		return cnx.atualiza(sqlDelete) > 0;

	}

	public List<Data> getDatas() throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityTableIsEmptyException {

		Data d = null;
		List<Data> datas = new ArrayList<>();
		String sqlBusca = "SELECT id, data_reserva, horario_inicio, horario_termino FROM datas;";

		rs = cnx.consulte(sqlBusca);

		try {
			if(rs.next()) {
				rs.beforeFirst();
				while(rs.next()) {
					d = new Data(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
					datas.add(d);
				}
			} else
				throw new EntityTableIsEmptyException("Datas");
		} catch (SQLException e) {
			// TODO: handle exception
			throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
		}

		return datas;
	}

	public List<Data> getDatasPorDia(String data) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityTableIsEmptyException {

		Data d = null;
		List<Data> datas = new ArrayList<>();

		String sqlBusca = "SELECT id, data_reserva, horario_inicio, horario_termino FROM datas WHERE data_reserva LIKE '%" + data + "%';";
		rs = cnx.consulte(sqlBusca);
		try {
			if(rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					d = new Data(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
					datas.add(d);
				}
			} else 
				throw new EntityTableIsEmptyException("Laboratorio");
		} catch (SQLException e) {
			// TODO: handle exception
			throw new DataBaseGenericException(e.getErrorCode(), e.getMessage());
		}

		return datas;
	}
}
