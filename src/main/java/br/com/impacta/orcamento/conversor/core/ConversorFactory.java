package br.com.impacta.orcamento.conversor.core;

/**
 * Sabe criar um conversor ({@link Conversor}) atrav�s de sua classe de sa�da.
 * <br><br>
 * Cada convers�o deve possuir um factory que sabe criar todos os conversores necess�rios durante a convers�o.
 * Estes conversores s�o obtido atrav�s da classe de saida passada ao factory 
 * 
 * @author Arthur Fernandes
 * @see Conversor
 * @see ConversorContexto
 *
 */
public interface ConversorFactory {

	/**
	 * Devolve uma inst�ncia de {@link Conversor} que saiba converter o objeto solicitado.
	 * @param classeSaida O {@link Class} do objeto convertido
	 * @return O {@link Conversor}
	 */
	<E, S> Conversor<E, S> construirConversor(Class<E> classeEntrada, Class<S> classeSaida);
	
	/**
	 * Devolve uma inst�ncia de {@link Conversor} que saiba converter o objeto solicitado.
	 * @param classeSaida O {@link Class} do objeto convertido
	 * @return O {@link Conversor}
	 */
	<E, S> Conversor<E, S> construirConversor(TipoParametrizado<E, ?> classeEntrada, TipoParametrizado<S, ?> classeSaida);
	
}
