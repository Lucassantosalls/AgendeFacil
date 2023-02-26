package br.com.lucas.AgendeFacil.rest;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.lucas.AgendeFacil.model.entitiy.Cliente;
import br.com.lucas.AgendeFacil.model.entitiy.ServicoPrestado;
import br.com.lucas.AgendeFacil.model.repository.ClienteRepository;
import br.com.lucas.AgendeFacil.model.repository.ServicoPrestadoRepository;
import br.com.lucas.AgendeFacil.rest.dto.ServicoPrestadoDTO;
import br.com.lucas.AgendeFacil.util.Util;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/servicos-prestados")
@RequiredArgsConstructor
public class ServicoPrestadoController {

	private final ClienteRepository clienteRepository;
	private final ServicoPrestadoRepository repository;
	private final Util util;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ServicoPrestado salvar(@RequestBody @Valid ServicoPrestadoDTO dto) {
		ServicoPrestado servicoprestado = new ServicoPrestado();
		
		Cliente cliente =
				clienteRepository
				.findById(dto.getId_cliente())
				.orElseThrow(() -> new ResponseStatusException(
						HttpStatus.BAD_REQUEST, "CLIENTE NÃO EXISTE."));
		
		servicoprestado.setDescricao(dto.getDescricao());
		servicoprestado.setResumo(dto.getResumo());
		servicoprestado.setEnderecoServico(dto.getEnderecoServico());
		servicoprestado.setDescricao(dto.getDescricao());
		servicoprestado.setDataDoServico(util.validarData(dto.getDataDoServico()));
		servicoprestado.setCliente(cliente);
		servicoprestado.setValor(util.stringToBigDecimal(dto.getValor()));
		servicoprestado.setConcluido(util.stringToBoolean(dto.getConcluido()));
		return repository.save(servicoprestado);
	}
	
	@GetMapping("{id}")
	public ServicoPrestado procurarPorId(@PathVariable Integer id) {
		return repository
				.findById(id)
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "NÃO FOI ENCONTRADO SERVICO COM ESSE ID."));
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarServico(@PathVariable Integer id) {
		repository
		.findById(id)
		.map(servicoprestado->{
			repository.delete(servicoprestado);
			return Void.TYPE;
		})
		.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "NÃO FOI ENCONTRADO SERVICO COM ESSE ID."));
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarServico(@PathVariable Integer id, @RequestBody @Valid ServicoPrestadoDTO dto) {
		repository
		.findById(id)
		.map(servicoprestado->{
			
			Cliente cliente =
					clienteRepository
					.findById(dto.getId_cliente())
					.orElseThrow(() -> new ResponseStatusException(
							HttpStatus.BAD_REQUEST, "CLIENTE NÃO EXISTE."));
			
			servicoprestado.setDescricao(dto.getDescricao());
			servicoprestado.setResumo(dto.getResumo());
			servicoprestado.setEnderecoServico(dto.getEnderecoServico());
			servicoprestado.setDescricao(dto.getDescricao());
			servicoprestado.setDataDoServico(util.validarData(dto.getDataDoServico()));
			servicoprestado.setCliente(cliente);
			servicoprestado.setValor(util.stringToBigDecimal(dto.getValor()));
			servicoprestado.setConcluido(util.stringToBoolean(dto.getConcluido()));
			servicoprestado.setId(id);
			return repository.save(servicoprestado);
		})
		.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "NÃO FOI ENCONTRADO SERVICO COM ESSE ID."));
	}
}
