package br.ufac.laboratorio.exception;

public class EntityLoginNotExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EntityLoginNotExistException(String login){
		super("Login não existe: " + login);
	}
}
