package br.com.luciano.repository;

import br.com.luciano.model.Endereco;

public class EnderecoRepository extends AbstractRepository<Endereco, Integer> {

	public EnderecoRepository(Class<Endereco> clazz) {
		super(clazz);
	}

}
