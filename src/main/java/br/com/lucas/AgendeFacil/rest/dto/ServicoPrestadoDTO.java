package br.com.lucas.AgendeFacil.rest.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServicoPrestadoDTO {

	@NotBlank(message = "{resumo.obrigatorio}")
	@Size(max = 50, message = "{resumo.tamanho}")
	private String resumo;
	
	@NotBlank(message = "{descricao.obrigatorio}")
	@Size(max = 200, message = "{descricao.tamanho}")
	private String descricao;
	
	private Integer id_cliente;
	
	@NotBlank(message = "{endereco.obrigatorio}")
	@Size(max = 200, message = "{endereco.tamanho}")
	private String enderecoServico;
	
	@NotBlank(message = "{valor.obrigatorio}")
	@Size(max = 15, message = "{valor.tamanho}")
	private String valor;
	
	@NotBlank(message = "{concluido.obrigatorio}")
	private String concluido;
	
	@NotBlank(message = "{dataServ.obrigatorio}")
	private String dataDoServico;
}
