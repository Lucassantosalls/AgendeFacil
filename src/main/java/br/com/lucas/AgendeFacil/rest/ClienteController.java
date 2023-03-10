package br.com.lucas.AgendeFacil.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import br.com.lucas.AgendeFacil.model.repository.ClienteRepository;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin("*")
public class ClienteController {
	
	@Autowired
	private ClienteRepository repository;
	
	public ClienteController(ClienteRepository repository) {
		this.repository = repository;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente salvar(@RequestBody @Valid Cliente cliente) {
		return repository.save(cliente);
	}
	
	@GetMapping("{id}")
	public Cliente procurarPorId(@PathVariable Integer id) {
		return repository
				.findById(id)
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "NÃO FOI ENCONTRADO CLIENTE COM ESSE ID."));
	}
	
	@GetMapping
	public List<Cliente> buscarTodos(){
		return repository.findAll();
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarCliente(@PathVariable Integer id) {
		repository
		.findById(id)
		.map(cliente->{
			repository.delete(cliente);
			return Void.TYPE;
		})
		.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "NÃO FOI ENCONTRADO CLIENTE COM ESSE ID."));
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarCliente(@PathVariable Integer id, @RequestBody @Valid Cliente clienteAtualizado) {
		repository
		.findById(id)
		.map(cliente->{
			clienteAtualizado.setId(cliente.getId());
			return repository.save(clienteAtualizado);
		})
		.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "NÃO FOI ENCONTRADO CLIENTE COM ESSE ID."));
	}
}
