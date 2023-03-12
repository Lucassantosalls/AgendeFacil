package br.com.lucas.AgendeFacil.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class Util {

	public BigDecimal stringToBigDecimal(String valor) {
		if(valor == null || valor.equals("")) {
			return null;
		}
		valor = valor.replace(".", "").replace(",", ".");
		return new BigDecimal(valor);
	}
	
	public Boolean stringToBoolean(String bolean) {
		if(bolean == null || bolean.equals("")) {
			return null;
		}
		if(bolean.equals("true")) {
			return true;
		}else {
			return false;
		}
	}
	public LocalDate validarData(String data) {
		try {
			LocalDate retorno = LocalDate.parse(data,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			return retorno;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data Inválida.");
		}
	}
}
