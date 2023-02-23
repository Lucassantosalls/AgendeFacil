package br.com.lucas.AgendeFacil.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import br.com.lucas.AgendeFacil.rest.exception.ApiErrors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors validationErros(MethodArgumentNotValidException ex) {
		List<String> messagensEx = ex.getBindingResult()
		.getAllErrors()
		.stream()
		.map( objectError -> objectError.getDefaultMessage())
		.collect(Collectors.toList());
		
		return new ApiErrors(messagensEx);
	}	
	
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity responseStatusException(ResponseStatusException ex) {
		String mensagemDeErro = ex.getMessage();
		HttpStatus codStatus = ex.getStatus();
		
		ApiErrors apiErrors = new ApiErrors(mensagemDeErro);
		return new ResponseEntity(apiErrors,codStatus);
				
	}
}
