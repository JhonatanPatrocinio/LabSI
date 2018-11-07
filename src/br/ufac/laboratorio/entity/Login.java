package br.ufac.laboratorio.entity;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Login {
	private int id;
	private String login;
	private String senha;
	private int tipo;
	private MessageDigest crypto;
	
	public Login(int id, String login, String senha, int tipo) {
		this.id = id;
		this.login = login;
		this.senha = senha;
		this.tipo = tipo;
	}
	public Login(String login, String senha, int tipo) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		this.login = login;
		this.tipo = tipo;
		crypto = MessageDigest.getInstance("MD5");
		byte messageDigestSenha[] = crypto.digest(senha.getBytes("UTF-8"));
		StringBuilder hexStringSenha = new StringBuilder();
		for (byte b : messageDigestSenha) {
			hexStringSenha.append(String.format("%02X", 0xFF & b));
		}
		
		String hexSenha = hexStringSenha.toString();
		this.senha = hexSenha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getId() {
		return id;
	}
	
	public String TestaSenha(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest crypto = MessageDigest.getInstance("MD5");
		byte messageDigestSenha[] = crypto.digest(senha.getBytes("UTF-8"));
		StringBuilder hexStringSenha = new StringBuilder();
		for(byte b : messageDigestSenha) {
			hexStringSenha.append(String.format("%02X",	0xFF & b));
		}
		String hexSenha = hexStringSenha.toString();
		return hexSenha;
		
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	
}
