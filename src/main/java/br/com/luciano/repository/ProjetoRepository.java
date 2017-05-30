package br.com.luciano.repository;

import java.util.List;

import br.com.luciano.model.Projeto;

public class ProjetoRepository extends AbstractRepository<Projeto, Integer> {

	public ProjetoRepository(Class<Projeto> clazz) {
		super(clazz);
	}

	public List<Projeto> findyByFuncionario(final Integer idFuncionario) {
		StringBuilder hql = new StringBuilder().append("SELECT p FROM Projeto p ")
				.append("JOIN FETCH p.funcionarios f ").append(" WHERE f.id = :idFuncionario");
		return this.createQuery(hql.toString()).setParameter("idFuncionario", idFuncionario).getResultList();
	}
}
