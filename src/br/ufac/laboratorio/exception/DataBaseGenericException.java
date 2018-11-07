package br.ufac.laboratorio.exception;

public class DataBaseGenericException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataBaseGenericException(int codigo, String mensagem){
		super("Exceção genérica do servidor: \n\t[" 
				+ codigo+ "] -> "
				+ mensagem+"!");
	}
}
