package br.ufac.laboratorio.gui.aluno;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.ufac.laboratorio.entity.Aluno;

public class ListarAlunoTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	private List<Aluno> alunos;
	
	public ListarAlunoTableModel(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	@Override
	public int getRowCount() {
		return this.alunos.size();
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Aluno a;
		Object dado = null;
		
		a = alunos.get(rowIndex);
		
		switch (columnIndex) {
		case 0: dado = a.getId()				; break;
		case 1: dado = a.getMatricula()			; break;
		case 2: dado = a.getNome()				; break;
		case 3: dado = a.getCurso().getCod()	; break;
		case 4: dado = a.getLogin().getLogin()	; break;
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
		case 3: nome = "CURSO"; break;
		case 4: nome = "LOGIN"; break;
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

		default: obj = null; break;
		}		

		return obj.getClass();
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
