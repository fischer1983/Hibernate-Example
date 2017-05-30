package br.com.luciano.model;

public enum SexoEnum {

	M("MASCULINO"), F("FEMINIMO");

	private final String descricao;

	private SexoEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
