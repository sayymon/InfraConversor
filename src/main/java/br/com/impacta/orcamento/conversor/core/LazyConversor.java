package br.com.impacta.orcamento.conversor.core;


/**
 * Sabe requisitar o conversor a f�brica somente quando necess�rio.
 * 
 * @author Arthur Fernandes
 *
 * @param <E> - Objeto que se faz necess�rio para a convers�o
 * @param <S> - Objeto que resultar� a convers�o.
 */
public class LazyConversor<E, S> implements Conversor<E, S> {

	private final TipoParametrizado<?, ?> classeSaida;
	private final TipoParametrizado<?, ?> classeEntrada;
	private final ConversorFactory factory;
	
	public LazyConversor(ConversorFactory factory, TipoParametrizado<?, ?> classeEntrada, TipoParametrizado<?, ?> classeSaida) {
		this.classeEntrada = classeEntrada;
		this.classeSaida = classeSaida;
		this.factory = factory;
	}

	@Override
	public S converter(E entrada) {
		return obterConversor().converter(entrada);
	}
	
	@SuppressWarnings("unchecked")
	private Conversor<E, S> obterConversor(){
		return (Conversor<E, S>) this.factory.construirConversor(classeEntrada, classeSaida);
	}
	
	public TipoParametrizado<?, ?> getClasseEntrada() {
		return classeEntrada;
	}
	
	public TipoParametrizado<?, ?> getClasseSaida() {
		return classeSaida;
	}

}
