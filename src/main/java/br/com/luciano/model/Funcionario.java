package br.com.luciano.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(allocationSize = 1, name = "funcionarioSeq", sequenceName = "funcionario_seq", schema = "public")
public class Funcionario extends AbstractEntity<Integer> {

	@Id
	@GeneratedValue(generator = "funcionarioSeq", strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(length = 60)
	private String nome;

	@Column(length = 11)
	private String cpf;

	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	@Enumerated(EnumType.STRING)
	private SexoEnum sexo;

	@ManyToOne
	@JoinColumn(name = "idEndereco", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_funcionario_endereco"))
	private Endereco endereco;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "supervisor")
	private List<Funcionario> supervisionados;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idSupervisor", nullable = true, foreignKey = @ForeignKey(name = "fk_funcionario_supervisor"))
	private Funcionario supervisor;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "funcionario_projeto", schema = "public", joinColumns = {
			@JoinColumn(name = "idFuncionario", nullable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_projeto_funcionario_idFuncionario")) }, inverseJoinColumns = {
					@JoinColumn(name = "idProjeto", nullable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_projeto_funcionario_idProjeto")) })
	private List<Projeto> projetos = new ArrayList<>();

	public Funcionario() {
		super();
	}

	public Funcionario(String nome, String cpf, Date dataNascimento, SexoEnum sexo, Endereco endereco, Projeto projeto,
			Funcionario supervisor) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.endereco = endereco;
		this.projetos.add(projeto);
		this.supervisor = supervisor;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public SexoEnum getSexo() {
		return sexo;
	}

	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}

	public Funcionario getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Funcionario supervisor) {
		this.supervisor = supervisor;
	}

}
