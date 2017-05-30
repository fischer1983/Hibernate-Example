package br.com.luciano.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(allocationSize = 1, name = "enderecoSeq", sequenceName = "endereco_seq", schema = "public")
public class Endereco extends AbstractEntity<Integer> {

	@Id
	@GeneratedValue(generator = "enderecoSeq", strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(length = 2)
	private String pais;

	@Column(length = 2)
	private String uf;

	@Column(length = 45)
	private String cidade;

	@Column(length = 150)
	private String rua;

	@Column(length = 9)
	private String cep;

	public Endereco() {
		super();
	}

	public Endereco(Integer id, String pais, String uf, String cidade, String rua, String cep) {
		super();
		this.id = id;
		this.pais = pais;
		this.uf = uf;
		this.cidade = cidade;
		this.rua = rua;
		this.cep = cep;
	}

	public Endereco(String pais, String uf, String cidade, String rua, String cep) {
		super();
		this.pais = pais;
		this.uf = uf;
		this.cidade = cidade;
		this.rua = rua;
		this.cep = cep;
	}

	public void setId(Integer id) {
		this.id = id;

	}

	public Integer getId() {
		return this.id;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

}
