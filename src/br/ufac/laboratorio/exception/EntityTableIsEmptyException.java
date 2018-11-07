package br.ufac.laboratorio.exception;

public class EntityTableIsEmptyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EntityTableIsEmptyException(String entidade){
		super("Tabela da entidade '" + entidade + "' está vazia!");
	}
}
