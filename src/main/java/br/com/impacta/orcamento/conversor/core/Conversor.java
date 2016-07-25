package br.com.impacta.orcamento.conversor.core;

/**
 * Um conversor sabe converter um objeto em outro objeto. 
 * 
 * @author Arthur Fernandes
 *
 * @param <E> - Objeto que se faz necess�rio para a convers�o
 * @param <S> - Objeto que resultar� a convers�o.
 */
public interface Conversor<E, S> {

	/**
	 * Converte o objeto E em S.
	 * @param entrada O objeto a ser convertido
	 * @return O objeto convertido
	 */
	S converter(E entrada);
	
}
