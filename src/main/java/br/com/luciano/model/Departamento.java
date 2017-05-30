package br.com.luciano.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(allocationSize = 1, name = "departamentoSeq", sequenceName = "departamento_seq", schema = "public")
public class Departamento extends AbstractEntity<Integer> {

	@Id
	@GeneratedValue(generator = "departamentoSeq", strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(length = 60)
	private String nome;

	private Integer numero;

	public Departamento(String nome, Integer numero) {
		super();
		this.nome = nome;
		this.numero = numero;
	}

	public Departamento() {
		super();
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

}
