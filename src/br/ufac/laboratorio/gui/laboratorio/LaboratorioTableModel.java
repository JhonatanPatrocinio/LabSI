package br.ufac.laboratorio.gui.laboratorio;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import br.ufac.laboratorio.entity.Laboratorio;

public class LaboratorioTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Laboratorio> laboratorios;
	
	public LaboratorioTableModel(List<Laboratorio> laboratorios) {
		this.laboratorios = laboratorios;
	}

	@Override
	public int getRowCount() {
		return laboratorios.size();
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Laboratorio l;
		Object dado = null;
		
		l = laboratorios.get(rowIndex);
		
		switch (columnIndex) {
		case 0: dado = l.getId(); break;
		case 1: dado = l.getNome(); break;
		case 2: dado = l.getCentro().getSigla(); break;
		case 3: dado = l.getComputadores(); break;
		default: throw new IndexOutOfBoundsException();
		}
		
		return dado;
	}
	
	@Override
	public String getColumnName(int columnIndex) {

		String nome="";
		
		switch (columnIndex) {
		case 0: nome = "ID"; break;
		case 1: nome = "NOME"; break;		
		case 2: nome = "CENTRO"; break;
		case 3: nome = "PC's"; break;
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
		case 3: obj = Integer.class; break;
		default: obj = null; break;
		}		

		return obj.getClass();
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

}
