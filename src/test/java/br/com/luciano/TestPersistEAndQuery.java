package br.com.luciano;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.com.luciano.model.Departamento;
import br.com.luciano.model.Endereco;
import br.com.luciano.model.Funcionario;
import br.com.luciano.model.Projeto;
import br.com.luciano.model.SexoEnum;
import br.com.luciano.repository.DepartamentoRepository;
import br.com.luciano.repository.EnderecoRepository;
import br.com.luciano.repository.FuncionarioRepository;
import br.com.luciano.repository.ProjetoRepository;
import junit.framework.Assert;

public class TestPersistEAndQuery {
	Logger log = Logger.getLogger(this.getClass().getName());

	private ProjetoRepository projRepository;
	private DepartamentoRepository depRepository;
	private EnderecoRepository enderecoRepository;
	private FuncionarioRepository funcionarioRepository;

	@Before
	public void init() {
		projRepository = new ProjetoRepository(Projeto.class);
		depRepository = new DepartamentoRepository(Departamento.class);
		enderecoRepository = new EnderecoRepository(Endereco.class);
		funcionarioRepository = new FuncionarioRepository(Funcionario.class);
	}

	@After
	public void close() {

	}

	@Test
	public void testPersist() {

		log.info("... Persistindo Departamentos ...");
		List<Departamento> departamentos = new ArrayList<>();

		Departamento d1 = new Departamento("RH", 1);
		departamentos.add(d1);
		Departamento d2 = new Departamento("TI", 2);
		departamentos.add(d2);
		Departamento d3 = new Departamento("Contabilidade", 3);
		departamentos.add(d3);
		Departamento d4 = new Departamento("DP", 4);
		departamentos.add(d4);
		Departamento d5 = new Departamento("Logistica", 5);
		departamentos.add(d5);

		depRepository.saveList(departamentos);

		log.info("... Persistindo Projetos ...");

		List<Projeto> list = new ArrayList<>();

		Projeto p1 = new Projeto("Projeto RH", depRepository.findById(1));
		list.add(p1);
		Projeto p2 = new Projeto("Projeto TI", depRepository.findById(2));
		list.add(p2);
		Projeto p3 = new Projeto("Projeto Contabilidade", depRepository.findById(3));
		list.add(p3);
		Projeto p4 = new Projeto("Projeto DP", depRepository.findById(4));
		list.add(p4);
		Projeto p5 = new Projeto("Projeto Logistica", depRepository.findById(5));
		list.add(p5);

		projRepository.saveList(list);

		log.info("... Persistindo Enderecos ...");

		List<Endereco> enderecos = new ArrayList<>();

		Endereco e1 = new Endereco("BR", "RS", "Porto Alegre", "Assis Brasil", "91060530");
		enderecos.add(e1);
		Endereco e2 = new Endereco("BR", "RS", "Porto Alegre", "Sertorio", "91060540");
		enderecos.add(e2);
		Endereco e3 = new Endereco("BR", "RS", "Porto Alegre", "Afonso Pena", "91060550");
		enderecos.add(e3);
		Endereco e4 = new Endereco("BR", "RS", "Porto Alegre", "Farrapos", "91060560");
		enderecos.add(e4);
		Endereco e5 = new Endereco("BR", "RS", "Porto Alegre", "Azenha", "91060570");
		enderecos.add(e5);

		enderecoRepository.saveList(enderecos);

		log.info("... Persistindo Funcionarios ...");

		List<Funcionario> funcionarios = new ArrayList<>();

		this.funcionarioRepository.getEm().getTransaction().begin();

		Funcionario pe1 = new Funcionario("Bill Gates", "91382887612", new Date(1995, 12, 19), SexoEnum.M,
				enderecoRepository.findById(1), projRepository.findById(1), null);
		funcionarios.add(pe1);
		Funcionario pe2 = new Funcionario("Mark Zuckemberg", "22191233163", new Date(1995, 11, 15), SexoEnum.M,
				enderecoRepository.findById(2), projRepository.findById(2), null);
		funcionarios.add(pe2);
		Funcionario pe3 = new Funcionario("Ada Lovelace", "65091998314", new Date(1995, 10, 6), SexoEnum.F,
				enderecoRepository.findById(3), projRepository.findById(3), pe1);
		funcionarios.add(pe3);
		Funcionario pe4 = new Funcionario("Steve Jobs", "06239764205", new Date(1995, 7, 11), SexoEnum.M,
				enderecoRepository.findById(4), projRepository.findById(4), null);
		funcionarios.add(pe4);
		Funcionario pe5 = new Funcionario("Steve Wozniak", "04174637741", new Date(1995, 1, 17), SexoEnum.M,
				enderecoRepository.findById(5), projRepository.findById(5), pe4);
		funcionarios.add(pe5);

		funcionarioRepository.saveList(funcionarios);

		this.funcionarioRepository.getEm().getTransaction().commit();

	}

	@Test
	public void testQuery() {
		log.info("... teste consultas...");

		List<Funcionario> funcionarios = new ArrayList<>();
		List<Projeto> projetos = new ArrayList<>();

		// a. Recuperar todos os funcionários de um projeto específico;
		funcionarios = funcionarioRepository.findyByProjeto(1);
		for (Funcionario f : funcionarios) {
			Assert.assertEquals("Bill Gates", f.getNome());
			System.out.println("**************************************");
			System.out.println(f.getNome() + " trabalhou no projeto 1 ");
			System.out.println("**************************************");
		}

		// b. Recuperar todos os funcionários de um departamento específico;
		funcionarios = funcionarioRepository.findyByDepartamento(2);
		for (Funcionario f : funcionarios) {
			Assert.assertEquals("Mark Zuckemberg", f.getNome());
			System.out.println("**************************************");
			System.out.println(f.getNome() + " trabalha no departamento 2 ");
			System.out.println("**************************************");
		}

		// c. Recuperar os funcionários de acordo com o nome
		funcionarios = funcionarioRepository.findyByNome("Ada");
		for (Funcionario f : funcionarios) {
			Assert.assertEquals("Ada Lovelace", f.getNome());
			System.out.println("**************************************");
			System.out.println(f.getNome() + " com o nome Ada ");
			System.out.println("**************************************");
		}

		// d. Recuperar todos os projetos em que um funcionário trabalhou;
		projetos = projRepository.findyByFuncionario(4);
		for (Projeto p : projetos) {
			System.out.println("**************************************");
			Assert.assertEquals("Projeto DP", p.getNome());
			System.out.println("Funcionario 4 trabalhou no projeto " + p.getNome());
			System.out.println("**************************************");
		}

		// e. Recuperar os funcionários que estão sob o acompanhamento de um
		// supervisor específico.
		funcionarios = funcionarioRepository.findyBySupervisor(1);
		for (Funcionario f : funcionarios) {
			System.out.println("**************************************");
			Assert.assertEquals("Ada Lovelace", f.getNome());
			System.out.println(f.getNome() + " é supervisionado pelo supervisor 1 ");
			System.out.println("**************************************");
		}

	}

}
