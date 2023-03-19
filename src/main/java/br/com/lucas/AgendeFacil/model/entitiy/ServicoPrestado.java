package br.com.lucas.AgendeFacil.model.entitiy;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicoPrestado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 50)
	private String resumo;
	
	@Column(nullable = false, length = 200)
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	@Column(nullable = false, length = 200)
	private String enderecoServico;
	
	@Column(nullable = false, length = 15)
	private BigDecimal valor;
	
	@Column(nullable = false)
	private String concluido;
	
	@Column(nullable = false, length = 11)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataDoServico;

}
