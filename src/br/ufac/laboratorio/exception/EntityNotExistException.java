package br.ufac.laboratorio.exception;

public class EntityNotExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EntityNotExistException(String entidade){
		super("Entidade não existe: " + entidade);
	}
}
