package br.com.bancooriginal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 
 * @author Cosme Ribeiro da Silva
 *
 */
@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ClienteNaoEncontradoException.class)
	public ResponseEntity<?> clienteNaoEncontradoException(ClienteNaoEncontradoException ex, WebRequest request) {
		ErroDetalhes erroDetalhes = new ErroDetalhes(ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(erroDetalhes, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalException(Exception ex, WebRequest request) {
		ErroDetalhes erroDetalhes = new ErroDetalhes(ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(erroDetalhes, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
