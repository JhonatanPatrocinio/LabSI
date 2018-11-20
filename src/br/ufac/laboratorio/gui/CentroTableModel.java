package br.ufac.laboratorio.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.ufac.laboratorio.entity.Centro;

public class CentroTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Centro> centros;
	
	public CentroTableModel(List<Centro> centros) {
		this.centros = centros;
	}
	
	@Override
	public int getRowCount() {
		return centros.size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Centro c;
		Object dado = null;
		
		c = centros.get(rowIndex);
		
		switch (columnIndex) {
		case 0: dado = c.getId(); break;
		case 1: dado = c.getSigla(); break;
		case 2: dado = c.getNome(); break;
		default: throw new IndexOutOfBoundsException(columnIndex);
		}
		
		return dado;
	}
	
	@Override
	public String getColumnName(int columnIndex) {

		String nome="";
		
		switch (columnIndex) {
		case 0: nome = "ID"; break;
		case 1: nome = "SIGLA"; break;		
		case 2: nome = "NOME"; break;
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
		default: obj = null; break;
		}		

		return obj.getClass();
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

}
