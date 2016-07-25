package br.com.impacta.orcamento.conversor.core;

/**
 * F�brica utilizada quando se faz necess�rio o uso do Contexto de Convers�o 
 * ({@link ConversorContexto}) durante a convers�o.
 *  
 * <br><br>
 * Sempre que se solicita a cria��o de um {@link Conversor} a esta f�brica ela cria um {@link ConversorContextoProxy} 
 * para fazer o controle do Contexto de Convers�o. 
 * 
 * @author Arthur Fernandes
 *
 */
public final class ConversorLazyFactory implements ConversorFactory {

	private final ConversorFactory factory;
	
	private ConversorLazyFactory(ConversorFactory factory) {
		this.factory = factory;
	}
	
	public static ConversorLazyFactory obterFactory(ConversorFactory factory){
		return new ConversorLazyFactory(factory);
	}

	@Override
	public <E, S> Conversor<E, S> construirConversor(Class<E> classeEntrada,
			Class<S> classeSaida) {
		return new LazyConversor<E, S>(factory, TipoParametrizado.de(classeEntrada), TipoParametrizado.de(classeSaida));
	}

	@Override
	public <E, S> Conversor<E, S> construirConversor(
			TipoParametrizado<E, ?> classeEntrada,
			TipoParametrizado<S, ?> classeSaida) {
		return new LazyConversor<E, S>(factory, classeEntrada, classeSaida);
	}

	
}
