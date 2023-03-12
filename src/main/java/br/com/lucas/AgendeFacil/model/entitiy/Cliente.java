package br.com.lucas.AgendeFacil.model.entitiy;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 150)
	@NotEmpty(message = "{nome.obrigatorio}")
	private String nome;
	
	@Column(nullable = false, length = 11)
	@NotNull(message = "{cpf.obrigatorio}")
	@CPF(message = "{cpf.invalido}")
	private String cpf;
	
	@Column(nullable = false, length = 11)
	@NotNull(message = "{celular.obrigatorio}")
	private String celular;
	
	@Column(nullable = false, length = 200)
	@NotNull(message = "{email.obrigatorio}")
	@Email(message = "{email.invalido}")
	private String email;
	
	@Column(nullable = false, length = 11)
	@NotNull(message = "{dtNascimento.obrigatorio}")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dtNascimento;
	
	@Column(nullable = false, length = 1)
	@NotNull(message = "{sexo.obrigatorio}")
	private String sexo;
	
	@Column(nullable = true, length = 200)
	@NotNull(message = "{endereco.obrigatorio}")
	private String endereco;
	
	@Column(name = "data_cadastro", length = 10)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCadastro;
	
	@PrePersist
	public void prePersist() {
		setDataCadastro(LocalDate.now());
	}
	
}
