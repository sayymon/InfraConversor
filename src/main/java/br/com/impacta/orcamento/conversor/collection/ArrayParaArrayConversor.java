package br.com.impacta.orcamento.conversor.collection;

import java.lang.reflect.Array;
import java.util.Collection;

import br.com.impacta.orcamento.conversor.core.Conversor;

/**
 * Sabe converter uma coleção ({@link Array}) de objetos, em um Array de objetos
 * <br><br>
 * 
 * Quando se possui o um conversor de ItemAutomovel para ItemVO por exemplo e temos uma lista de ItemAutomovel
 * para ser convertida em um Array de ItemVO, utilizamos esse conversor.
 * 
 * @author Arthur Fernandes
 *
 * @param <E> - Objeto que se faz necessário para a conversão
 * @param <S> - Objeto que resultará a conversão.
 */
public class ArrayParaArrayConversor <E, S> implements Conversor<E[], S[]> {

	private final Class<?> classeSaida;
	private final Conversor<E[], Collection<S>> conversorCollection;
	
	public ArrayParaArrayConversor(final Conversor<E, S> conversor, Class<?> classeSaida) {
		this.classeSaida = classeSaida;
		// Utiliza o conversor collection, para montar uma collection temporaria antes de criar o array,
		// para resolver o problema de quando o conversor devolve null e nao podemos add na collection
		conversorCollection = new ArrayParaCollectionConversor<E, S>(conversor);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public S[] converter(E[] entrada) {
		Collection<S> collectionSaida = conversorCollection.converter(entrada);

		S[] arraySaida = (S[])Array.newInstance(classeSaida, collectionSaida.size());
		int i = 0;
		for (S s : collectionSaida) {
			arraySaida[i] = s;
			i++;
		}
		return arraySaida;
	}

}
