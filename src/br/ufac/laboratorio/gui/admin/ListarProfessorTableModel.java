package br.ufac.laboratorio.gui.admin;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.ufac.laboratorio.entity.Professor;

public class ListarProfessorTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Professor> professores;
	
	public ListarProfessorTableModel(List<Professor> professores) {
		this.professores = professores;
	}
	@Override
	public int getRowCount() {
		return this.professores.size();
	}

	@Override
	public int getColumnCount() {
		return 7;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Professor p;
		Object dado = null;
		
		p = professores.get(rowIndex);
		
		switch (columnIndex) {
		case 0: dado = p.getId()				; break;
		case 1: dado = p.getMatricula()			; break;
		case 2: dado = p.getNome()				; break;
		case 3: dado = p.getEmail()				; break;
		case 4: dado = p.getTelefone()			; break;
		case 5: dado = p.getCentro().getSigla()	; break;
		case 6: dado = p.getLogin().getLogin()	; break;
		default: throw new IndexOutOfBoundsException();
		}
		
		return dado;
	}
	

	@Override
	public String getColumnName(int columnIndex) {

		String nome="";
		
		switch (columnIndex) {
		case 0: nome = "ID"; break;
		case 1: nome = "MATRICULA"; break;		
		case 2: nome = "NOME"; break;
		case 3: nome = "EMAIL"; break;
		case 4: nome = "TELEFONE"; break;
		case 5: nome = "CENTRO"; break;
		case 6: nome = "LOGIN"; break;
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

		default: obj = null; break;
		}		

		return obj.getClass();
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }


}
