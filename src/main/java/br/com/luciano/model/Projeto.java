package br.com.luciano.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(allocationSize = 1, name = "projetoSeq", sequenceName = "projeto_seq", schema = "public")
public class Projeto extends AbstractEntity<Integer> {

	@Id
	@GeneratedValue(generator = "projetoSeq", strategy = GenerationType.SEQUENCE)	
	private Integer id;

	@Column(length = 60)
	private String nome;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idDepartamento", referencedColumnName = "id"
		, foreignKey = @ForeignKey(name = "fk_projeto_departamento"))
	private Departamento departamento;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "projetos")
	public List<Funcionario> funcionarios; 
	
	public Projeto() {
		super();
	}
		
	public Projeto(String nome, Departamento departamento) {
		super();
		this.nome = nome;
		this.departamento = departamento;
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

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<Funcionario> getFunciorarios() {
		return funcionarios;
	}

	public void setFunciorarios(List<Funcionario> funciorarios) {
		this.funcionarios = funciorarios;
	}

}
