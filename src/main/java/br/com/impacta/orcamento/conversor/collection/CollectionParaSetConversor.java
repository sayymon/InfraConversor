package br.com.impacta.orcamento.conversor.collection;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import br.com.impacta.orcamento.conversor.core.Conversor;

/**
 * Sabe converter um {@link Set} de objetos, em outro {@link Set} de objetos
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
public class CollectionParaSetConversor<E, S> implements Conversor<Collection<E>, Set<S>> {

	private final Conversor<E, S> conversor;

	public CollectionParaSetConversor(Conversor<E, S> conversor) {
		this.conversor = conversor;
	}
	
	@Override
	public Set<S> converter(Collection<E> entrada) {
		Set<S> list = new HashSet<S>();
		for (E e : entrada) {
			S s = this.conversor.converter(e);
			if (s != null){
				list.add(s);
			}
		}
		return list;
	}


}
