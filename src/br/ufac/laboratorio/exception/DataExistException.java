package br.ufac.laboratorio.exception;

public class DataExistException extends Exception {

	private static final long serialVersionUID = 1L;

	public DataExistException(String message) {
		super(message);
	}
}
