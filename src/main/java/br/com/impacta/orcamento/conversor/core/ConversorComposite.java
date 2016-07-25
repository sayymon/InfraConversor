package br.com.impacta.orcamento.conversor.core;

import java.util.List;

/**
 * Aplica a conversão em sequencia utilizando os conversores informados
 * 
 * Sendo assim consegue converter A para D, tendo um conversor de A para B e C para D.
 *  
 * @author Arthur Fernandes
 *
 * @param <E> - Objeto que se faz necessário para a conversão
 * @param <S> - Objeto que resultará a conversão.
 */
final class ConversorComposite<E, S> implements Conversor<E, S>  {

	private List<Conversor<?, ?>> conversores;
	
	private ConversorComposite(final List<Conversor<?, ?>> conversores) {
		this.conversores = conversores;
	}
	
	static <E, S>ConversorComposite<E, S> para(final List<Conversor<?, ?>> conversores){
		return new ConversorComposite<E, S>(conversores);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public S converter(E entrada) {
		Object resultado = entrada;
		for (Conversor<?, ?> conversor : conversores) {
			resultado = ((Conversor)conversor).converter(resultado);
		}
		return (S) resultado;
	}

	
}
