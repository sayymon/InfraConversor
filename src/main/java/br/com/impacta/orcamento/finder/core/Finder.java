package br.com.impacta.orcamento.finder.core;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;

/**
 * {@code Finder} prov� alguns recursos padr�es para a implementa��o de um {@code Finder}
 * de objetos. 
 * 
 * Um {@code Finder} de objetos segue alguns patterns de Fluent Interface para buscar informa��es em objetos. 
 * 
 * @author Arthur Fernandes
 *
 * @param <T> O tipo a ser tratado
 */
public class Finder<T> {

	private final T object;
	private final T nullObject;
	
	/**
	 * Construtor padr�o do {@code Finder}
	 * @param valor O valor tratado pelo finder
	 * @param nullObject Uma instancia de Null Object para ser utilizada quando o valor � Null.
	 */
	protected Finder(final T object, final T nullObject) {
		Preconditions.checkNotNull(nullObject);
		this.object = object;
		this.nullObject = nullObject;
	}
	
	/**
	 * O valor tratado pelo finder, este m�todo n�o � Null Safe, 
	 * caso o valor seja {@code null} ser� devolvido {@code null}.
	 * @return O valor
	 */
	public T get() {
		return object;
	}
	
	/**
	 * O valor tratado pelo finder, este m�todo � Null Safe, 
	 * caso o valor seja {@code null} ser� devolvido um {@code Null Object}.
	 * @return O valor
	 */
	public T getNullSafe() {
		return MoreObjects.firstNonNull(this.object, this.nullObject);
	}
	
}
