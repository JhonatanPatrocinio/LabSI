package br.ufac.laboratorio.exception;

public class DataBaseAlreadyConnectedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataBaseAlreadyConnectedException(String db){
		super("Banco de dados '"+ db + "' já está conectado!");
	}
}
