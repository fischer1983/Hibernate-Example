package br.com.luciano.repository;

import java.util.List;

import br.com.luciano.model.Funcionario;

public class FuncionarioRepository extends AbstractRepository<Funcionario, Integer> {

	public FuncionarioRepository(Class<Funcionario> clazz) {
		super(clazz);
	}
	
	public List<Funcionario> findyByProjeto(final Integer idProjeto){
		StringBuilder hql = new StringBuilder()
				.append("SELECT f FROM Funcionario f ")
				.append(" JOIN f.projetos p ")
				.append(" WHERE p.id = :idProjeto");
		return this.createQuery(hql.toString()).setParameter("idProjeto", idProjeto).getResultList();		
	}
	
	public List<Funcionario> findyByDepartamento(final Integer idDepartamento){
		StringBuilder hql = new StringBuilder()
				.append("SELECT f FROM Funcionario f ")
				.append(" JOIN FETCH f.projetos p ")
				.append(" JOIN FETCH p.departamento d ")
				.append(" WHERE d.id = :idDepartamento");
		return this.createQuery(hql.toString()).setParameter("idDepartamento", idDepartamento).getResultList();		
	}	
	
	public List<Funcionario> findyByNome(final String nome){
		StringBuilder hql = new StringBuilder()
				.append("SELECT f FROM Funcionario f ")
				.append(" WHERE f.nome like :nome");
		return this.createQuery(hql.toString()).setParameter("nome", nome + "%").getResultList();		
	}
	
	
	public List<Funcionario> findyBySupervisor(final Integer idSupervisor){
		StringBuilder hql = new StringBuilder()
				.append("SELECT f FROM Funcionario f ")
				.append("JOIN f.supervisor s ")
				.append(" WHERE s.id = :idSupervisor");
		return this.createQuery(hql.toString()).setParameter("idSupervisor", idSupervisor).getResultList();		
	}	

}
