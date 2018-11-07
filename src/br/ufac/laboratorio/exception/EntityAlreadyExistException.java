package br.ufac.laboratorio.exception;

public class EntityAlreadyExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EntityAlreadyExistException(String entidade){
		super("Entidade já existe: " + entidade);
	}
}
