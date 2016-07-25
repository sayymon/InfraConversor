package br.com.impacta.orcamento.conversor.collection;

import java.util.ArrayList;
import java.util.Collection;

import br.com.impacta.orcamento.conversor.core.Conversor;

/**
 * Sabe converter um {@link Collection} de objetos, em outro {@link Collection} de objetos
 * <br><br>
 * 
 * Quando se possui o um conversor de ItemAutomovel para ItemVO por exemplo e temos uma lista de ItemAutomovel
 * para ser convertida em uma lista de ItemVO, utilizamos esse conversor.
 * 
 * @author Arthur Fernandes
 *
 * @param <E> - Objeto que se faz necessário para a conversão
 * @param <S> - Objeto que resultará a conversão.
 */
public class CollectionParaCollectionConversor<E, S> implements Conversor<Collection<E>, Collection<S>> {

	private final Conversor<E, S> conversor;
	
	public CollectionParaCollectionConversor(Conversor<E, S> conversor) {
		this.conversor = conversor;
	}
	
	@Override
	public Collection<S> converter(Collection<E> entrada) {
		Collection<S> list = new ArrayList<S>();
		for (E e : entrada) {
			S s = this.conversor.converter(e);
			if (s != null){
				list.add(s);
			}
		}
		return list;
	}
	

}
