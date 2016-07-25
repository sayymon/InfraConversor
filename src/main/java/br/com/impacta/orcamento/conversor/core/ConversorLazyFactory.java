package br.com.impacta.orcamento.conversor.core;

/**
 * Fábrica utilizada quando se faz necessário o uso do Contexto de Conversão 
 * ({@link ConversorContexto}) durante a conversão.
 *  
 * <br><br>
 * Sempre que se solicita a criação de um {@link Conversor} a esta fábrica ela cria um {@link ConversorContextoProxy} 
 * para fazer o controle do Contexto de Conversão. 
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
