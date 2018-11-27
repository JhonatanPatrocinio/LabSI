package br.ufac.laboratorio.gui.reserva;

import java.util.List;

import javax.swing.table.AbstractTableModel;


import br.ufac.laboratorio.entity.Reserva;

public class AnalisesTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Reserva> reservas;
	
	public AnalisesTableModel(List<Reserva> reservas) {
		this.reservas = reservas;
	}
	
	@Override
	public int getRowCount() {
		return this.reservas.size();
	}

	@Override
	public int getColumnCount() {
		return 8;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Reserva r;
		Object dado = null;
		
		r = reservas.get(rowIndex);
		
		switch (columnIndex) {
		case 0: dado = r.getId(); break;
		case 1: dado = r.getIdProfessor().getNome(); break;
		case 2: dado = r.getIdLaboratorio().getNome(); break;
		case 3: dado = r.getIdData().getDataUsuario(); break;
		case 4: dado = r.getIdData().getHorarioInicio(); break;
		case 5: dado = r.getIdData().getHorarioTermino(); break;
		case 6: dado = "Aguardando Análise"; break;
		case 7: dado = r.getObs(); break;
		default: throw new IndexOutOfBoundsException();
		}
		
		return dado;
	}
	
	@Override
	public String getColumnName(int columnIndex) {

		String nome="";
		
		switch (columnIndex) {
		case 0: nome = "ID"; break;
		case 1: nome = "PROFESSOR"; break;		
		case 2: nome = "LABORATORIO"; break;
		case 3: nome = "DATA"; break;
		case 4: nome = "HORARIO INICIO"; break;
		case 5: nome = "HORARIO FIM"; break;
		case 6: nome = "STATUS"; break;
		case 7: nome = "OBSERVAÇÕES"; break;
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
		case 6: obj = String.class; break;
		case 7: obj = String.class; break;

		default: obj = null; break;
		}		

		return obj.getClass();
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

}
