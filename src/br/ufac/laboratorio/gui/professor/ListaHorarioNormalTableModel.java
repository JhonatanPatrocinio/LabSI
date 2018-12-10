package br.ufac.laboratorio.gui.professor;

import java.util.List;
import javax.swing.table.AbstractTableModel;

import br.ufac.laboratorio.entity.Reserva;

public class ListaHorarioNormalTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	private List<Reserva> reservas;
	
	public ListaHorarioNormalTableModel(List<Reserva> reservas) {
		this.reservas = reservas;
	}
	@Override
	public int getRowCount() {
	return this.reservas.size();
	}

	@Override
	public int getColumnCount() {
		return 6;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Reserva r;
		Object dado = null;
		
		r = reservas.get(rowIndex);
		
		switch (columnIndex) {
		case 0: dado = r.getIdProfessor().getNome(); break;
		case 1: dado = r.getIdLaboratorio().getNome(); break;
		case 2: dado = r.getDataUsuario(); break;
		case 3: dado = r.getHorarioInicioUsuario(); break;
		case 4: dado = r.getHorarioTerminoUsuario(); break;
		case 5: dado = r.getObs(); break;
		default: throw new IndexOutOfBoundsException();
		}
		return dado;
	}
	
	public String getColumnName(int columnIndex) {

		String nome="";
		
		switch (columnIndex) {
		case 0: nome = "PROFESSOR"; break;
		case 1: nome = "LABORATORIO"; break;		
		case 2: nome = "DATA"; break;
		case 3: nome = "COMEÇA"; break;
		case 4: nome = "TERMINA"; break;
		case 5: nome = "OBSERVAÇÕES"; break;
		}		
		return nome; 	
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {

		Object obj;
		
		switch (columnIndex) {
		case 0: obj = Integer.class; break;
		case 1: obj = String.class; break;
		case 2: obj = String.class; break;
		case 3: obj = String.class; break;
		case 4: obj = String.class; break;
		case 5: obj = String.class; break;	

		default: obj = null; break;
		}		

		return obj.getClass();
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

}
