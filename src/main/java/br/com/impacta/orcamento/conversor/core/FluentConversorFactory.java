package br.com.impacta.orcamento.conversor.core;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Fluent Conversor Factory provê uma interface fluente para a construção de conversores.
 * @author Arthur Fernandes
 *
 */
public final class FluentConversorFactory {

	private final ConversorFactory factory;
	
	private FluentConversorFactory(final ConversorFactory factory) {
		this.factory = factory;
	}
	
	public static FluentConversorFactory para(final ConversorFactory factory) {
		return new FluentConversorFactory(factory);
	}
	
	/**
	 * Devolve uma instância de {@link Conversor} que saiba converter o objeto solicitado.
	 * @param classeSaida O {@link Class} do objeto convertido
	 * @return O {@link Conversor}
	 */
	public <E, S> Conversor<E, S> construirConversor(Class<E> classeEntrada, Class<S> classeSaida){
		return factory.construirConversor(classeEntrada, classeSaida);
	}

	/**
	 * Constrói um conversor 
	 * @return FluentConversorFactoryEntrada
	 */
	public  FluentConversorFactoryEntrada construirConversor() {
		return new FluentConversorFactoryEntrada();
	}
	
	public class FluentConversorFactoryEntrada{
		public <E> FluentConversorFactorySaida<List<E>> deList(Class<E> classeEntrada){
			return new FluentConversorFactorySaida<List<E>>(TipoParametrizado.list(classeEntrada));
		}
		
		public <E> FluentConversorFactorySaida<Set<E>> deSet(Class<E> classeEntrada){
			return new FluentConversorFactorySaida<Set<E>>(TipoParametrizado.set(classeEntrada));
		}
		
		public <E> FluentConversorFactorySaida<Collection<E>> deCollection(Class<E> classeEntrada){
			return new FluentConversorFactorySaida<Collection<E>>(TipoParametrizado.collection(classeEntrada));
		}
		
		public <E> FluentConversorFactorySaida<E> de(Class<E> classeEntrada){
			return new FluentConversorFactorySaida<E>(TipoParametrizado.de(classeEntrada));
		}
		
		public <E> FluentConversorFactorySaida<E[]> deArray(Class<E> classeEntrada){
			return new FluentConversorFactorySaida<E[]>(TipoParametrizado.array(classeEntrada));
		}
	}
	
	public class FluentConversorFactorySaida<E>{
		private TipoParametrizado<E, ?> entrada;
		
		public FluentConversorFactorySaida(TipoParametrizado<E, ?> entrada) {
			this.entrada = entrada;
		}
		
		public <S> Conversor<E, List<S>> paraList(Class<S> classeSaida){
			return factory.construirConversor(entrada, TipoParametrizado.list(classeSaida));
		}
		
		public <S> Conversor<E, Set<S>> paraSet(Class<S> classeSaida){
			return factory.construirConversor(entrada, TipoParametrizado.set(classeSaida));
		}
		
		public <S> Conversor<E, Collection<S>> paraCollection(Class<S> classeSaida){
			return factory.construirConversor(entrada, TipoParametrizado.collection(classeSaida));
		}
		
		public <S> Conversor<E, S> para(Class<S> classeSaida){
			return factory.construirConversor(entrada, TipoParametrizado.de(classeSaida));
		}
		
		public <S> Conversor<E, S[]> paraArray(Class<S> classeSaida){
			return factory.construirConversor(entrada, TipoParametrizado.array(classeSaida));
		}
	}
	
}
