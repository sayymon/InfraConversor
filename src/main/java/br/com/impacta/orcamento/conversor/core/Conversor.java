package br.com.impacta.orcamento.conversor.core;

/**
 * Um conversor sabe converter um objeto em outro objeto. 
 * 
 * @author Arthur Fernandes
 *
 * @param <E> - Objeto que se faz necessário para a conversão
 * @param <S> - Objeto que resultará a conversão.
 */
public interface Conversor<E, S> {

	/**
	 * Converte o objeto E em S.
	 * @param entrada O objeto a ser convertido
	 * @return O objeto convertido
	 */
	S converter(E entrada);
	
}
