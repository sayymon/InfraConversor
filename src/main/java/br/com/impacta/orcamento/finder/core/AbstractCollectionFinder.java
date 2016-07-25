package br.com.impacta.orcamento.finder.core;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

/**
 * {@code AbstractCollectionFinder} prov� algumas funcionalidades para trabalhar com {@link Finder} de cole��es.
 * 
 * @author Arthur Fernandes
 *
 * @param <T> O tipo tratado pelo {@link Finder} de cole��o
 * @param <U> O tipo de {@link Finder} utilizado para tratar o objeto unit�rio do {@link Finder} de cole��o 
 */
public abstract class AbstractCollectionFinder<T, U extends Finder<T>> extends
		Finder<Collection<T>> {

	/**
	 * A cole��o a ser utilizada no {@link Finder}
	 * @param valor A cole��o
	 */
	protected AbstractCollectionFinder(Collection<T> valor) {
		super(valor, Collections.<T> emptyList());
	}
	
	/**
	 * O array a ser utilizado no {@link Finder}
	 * @param valor A cole��o
	 */
	protected AbstractCollectionFinder(T[] valor) {
		this(fromNullSafeArray(valor));
	}
	
	/*
	 * Cria uma collection se o array for diferente de null
	 */
	private static <T> Collection<T> fromNullSafeArray(T[] valor){
		Collection<T> collection = null;
		if (valor != null){
			collection = Arrays.asList(valor);
		}
		return collection;
	}

	/**
	 * O primeiro item da cole��o, caso n�o existir registro � criado um {@link Finder} com o valor null. 
	 * Este m�todo � null safe. 
	 * @return O finder unit�rio
	 */
	public U primeiro() {
		return construirFinderUnitario(FluentIterable.from(getNullSafe())
				.first().orNull());
	}
	
	/** 
	 * O primeiro item ap�s aplicar a filtragem utilizando o {@code Predicate} informado, caso n�o exista um registro ap�s o {@code Predicate} for aplicado � devolvido um {@link Finder} 
	 * com o valor null. 
	 * @param predicate O {@code Predicate} para fazer a filtragem 
	 * @return O {@link Finder} do primeiro item da cole��o ap�s a filtragem
	 */
	protected U primeiro(Predicate<T> predicate) {
		return construirFinderUnitario(FluentIterable.from(getNullSafe())
				.filter(predicate).first().orNull());
	}

	/**
	 * O ultimo item da cole��o, caso n�o existir registro nenhum da cole��o � criado um {@link Finder} 
	 * com o valor null. 
	 * Este m�todo � null safe. 
	 * @return O {@link Finder} unit�rio
	 */
	public U ultimo() {
		return construirFinderUnitario(FluentIterable.from(getNullSafe())
				.last().orNull());
	}

	/**
	 * Se existe algum registro na cole��o
	 * @return Se existe registro
	 */
	public boolean existe() {
		return getNullSafe().size() != 0;
	}

	/** 
	 * O item correspondente a posi��o informada, caso n�o exista um registro correspondente na cole��o � criado um {@link Finder} 
	 * com o valor null. 
	 * @param posicao
	 * @return
	 */
	public U get(int posicao) {
		Preconditions.checkArgument(posicao < 0);
		try {
			return construirFinderUnitario(FluentIterable.from(getNullSafe())
					.get(posicao));
		} catch (IndexOutOfBoundsException e) {
			return construirFinderUnitario(null);
		}
	}
	
	
	/**
	 * Devolve uma {@code ImmutableList} da cole��o
	 * @return uma {@code ImmutableList}
	 */
	public ImmutableList<T> toList(){
		return FluentIterable.from(getNullSafe()).toList();
	}
	
	/**
	 * Devolve uma {@code ImmutableSet} da cole��o
	 * @return uma {@code ImmutableSet}
	 */
	public ImmutableSet<T> toSet(){
		return FluentIterable.from(getNullSafe()).toSet();
	}

	/**
	 * Sabe construir um {@link Finder} unit�rio utilizando o valor informado. 
	 * O valor informado n�o � null safe.
	 * @param valor O valor a ser utilizado na constru��o.
	 * @return O {@link Finder} unit�rio da cole��o
	 */
	protected abstract U construirFinderUnitario(T valor);
	
	/**
	 * Aplica o {@code Predicate} na cole��o e devolve uma nova cole��o com o filtro aplicado
	 * @param predicate O {@code Predicate} para fazer a filtragem
	 * @return Uma cole��o filtrada
	 */
	protected Collection<T> filtrar(final Predicate<T> predicate){
		return FluentIterable.from(getNullSafe()).filter(predicate).toList();
	}

}
