package br.com.luciano.model;

public abstract class AbstractEntity<T> {
	
	public abstract void setId(T id);
	public abstract T getId();

}
