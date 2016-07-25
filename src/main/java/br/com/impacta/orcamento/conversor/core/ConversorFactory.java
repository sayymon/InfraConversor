package br.com.impacta.orcamento.conversor.core;

/**
 * Sabe criar um conversor ({@link Conversor}) através de sua classe de saída.
 * <br><br>
 * Cada conversão deve possuir um factory que sabe criar todos os conversores necessários durante a conversão.
 * Estes conversores são obtido através da classe de saida passada ao factory 
 * 
 * @author Arthur Fernandes
 * @see Conversor
 * @see ConversorContexto
 *
 */
public interface ConversorFactory {

	/**
	 * Devolve uma instância de {@link Conversor} que saiba converter o objeto solicitado.
	 * @param classeSaida O {@link Class} do objeto convertido
	 * @return O {@link Conversor}
	 */
	<E, S> Conversor<E, S> construirConversor(Class<E> classeEntrada, Class<S> classeSaida);
	
	/**
	 * Devolve uma instância de {@link Conversor} que saiba converter o objeto solicitado.
	 * @param classeSaida O {@link Class} do objeto convertido
	 * @return O {@link Conversor}
	 */
	<E, S> Conversor<E, S> construirConversor(TipoParametrizado<E, ?> classeEntrada, TipoParametrizado<S, ?> classeSaida);
	
}
