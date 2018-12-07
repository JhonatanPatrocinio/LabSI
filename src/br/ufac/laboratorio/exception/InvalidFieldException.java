package br.ufac.laboratorio.exception;

import java.util.*;

public class InvalidFieldException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidFieldException(String entidade, List<String> listaDeCampos){
		super("Campos inválidos para '"
				+ entidade + "' " 
				+ listaDeCampos.toString());
	}
	
	public InvalidFieldException(String message){
		super(message);
	}
}
