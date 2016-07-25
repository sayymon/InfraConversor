package br.com.impacta.orcamento.conversor.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.impacta.orcamento.conversor.core.Conversor;

/**
 * Sabe converter uma lista ({@link List}) de objetos, em uma outra lista ({@link List}) de objetos
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
public class CollectionParaListConversor <E, S> implements Conversor<Collection<E>, List<S>> {

	private final Conversor<E, S> conversor;

	public CollectionParaListConversor(Conversor<E, S> conversor) {
		this.conversor = conversor;
	}

	@Override
	public List<S> converter(Collection<E> entrada) {
		List<S> list = new ArrayList<S>();
		for (E e : entrada) {
			S s = this.conversor.converter(e);
			if (s != null){
				list.add(s);
			}
		}
		return list;
	}


}
