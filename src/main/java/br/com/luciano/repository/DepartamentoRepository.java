package br.com.luciano.repository;

import br.com.luciano.model.Departamento;

public class DepartamentoRepository extends AbstractRepository<Departamento, Integer> {

	public DepartamentoRepository(Class<Departamento> clazz) {
		super(clazz);
	}

}
