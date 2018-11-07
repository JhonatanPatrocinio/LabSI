package br.ufac.laboratorio.exception;

public class AccessDeniedForUserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccessDeniedForUserException(String usuario){
		super("Acesso negado para o usuário '"+ usuario + "'!");
	}
}
