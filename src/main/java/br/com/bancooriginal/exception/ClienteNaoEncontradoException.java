package br.com.bancooriginal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author Cosme Ribeiro da Silva
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ClienteNaoEncontradoException extends Exception {

	/**
	 * Número de série da classe
	 */
	private static final long serialVersionUID = 1L;

	public ClienteNaoEncontradoException(String message) {
		super(message);
	}
}
