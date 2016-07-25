package br.com.impacta.orcamento.conversor.collection;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

import br.com.impacta.orcamento.conversor.core.Conversor;

/**
 * Sabe converter uma cole��o ({@link Array}) de objetos, em um Collection de objetos
 * <br><br>
 * 
 * Quando se possui o um conversor de ItemAutomovel para ItemVO por exemplo e temos uma lista de ItemAutomovel
 * para ser convertida em um Array de ItemVO, utilizamos esse conversor.
 * 
 * @author Arthur Fernandes
 *
 * @param <E> - Objeto que se faz necess�rio para a convers�o
 * @param <S> - Objeto que resultar� a convers�o.
 */
public class ArrayParaCollectionConversor <E, S> implements Conversor<E[], Collection<S>> {

	private final Conversor<E, S> conversor;
	
	public ArrayParaCollectionConversor(final Conversor<E, S> conversor) {
		this.conversor = conversor;
	}
	
	@Override
	public Collection<S> converter(E[] entrada) {
		Collection<S> collection = new ArrayList<S>();
		for (E e : entrada) {
			S s = this.conversor.converter(e);
			if (s != null){
				collection.add(s);
			}
		}
		return collection;
	}

}
