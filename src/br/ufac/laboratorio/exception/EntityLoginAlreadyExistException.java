package br.ufac.laboratorio.exception;

public class EntityLoginAlreadyExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EntityLoginAlreadyExistException(String login){
		super("Login já existe: " + login);
	}
}
