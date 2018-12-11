package br.ufac.laboratorio.gui.curso;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.ufac.laboratorio.entity.Centro;
import br.ufac.laboratorio.entity.Curso;

public class CursoTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Curso> cursos;
	
	public CursoTableModel(List<Curso> cursos) {
		this.cursos = cursos;
	}
	
	@Override
	public int getRowCount() {
		return cursos.size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Curso c;
		Object dado = null;
		
		c = cursos.get(rowIndex);
		
		switch (columnIndex) {
		case 0: dado = c.getId(); break;
		case 1: dado = c.getCod(); break;
		case 2: dado = c.getNome(); break;
		default: throw new IndexOutOfBoundsException();
		}
		
		return dado;
	}
	
	@Override
	public String getColumnName(int columnIndex) {

		String nome="";
		
		switch (columnIndex) {
		case 0: nome = "ID"; break;
		case 1: nome = "CODIGO"; break;		
		case 2: nome = "NOME"; break;
		}		
		return nome; 	
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {

		Object obj;
		
		switch (columnIndex) {
		case 0: obj = Integer.class; break;
		case 1: obj = Integer.class; break;
		case 2: obj = String.class; break;		
		default: obj = null; break;
		}		

		return obj.getClass();
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

}
