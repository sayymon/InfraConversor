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
 * {@code AbstractCollectionFinder} provê algumas funcionalidades para trabalhar com {@link Finder} de coleções.
 * 
 * @author Arthur Fernandes
 *
 * @param <T> O tipo tratado pelo {@link Finder} de coleção
 * @param <U> O tipo de {@link Finder} utilizado para tratar o objeto unitário do {@link Finder} de coleção 
 */
public abstract class AbstractCollectionFinder<T, U extends Finder<T>> extends
		Finder<Collection<T>> {

	/**
	 * A coleção a ser utilizada no {@link Finder}
	 * @param valor A coleção
	 */
	protected AbstractCollectionFinder(Collection<T> valor) {
		super(valor, Collections.<T> emptyList());
	}
	
	/**
	 * O array a ser utilizado no {@link Finder}
	 * @param valor A coleção
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
	 * O primeiro item da coleção, caso não existir registro é criado um {@link Finder} com o valor null. 
	 * Este método é null safe. 
	 * @return O finder unitário
	 */
	public U primeiro() {
		return construirFinderUnitario(FluentIterable.from(getNullSafe())
				.first().orNull());
	}
	
	/** 
	 * O primeiro item após aplicar a filtragem utilizando o {@code Predicate} informado, caso não exista um registro após o {@code Predicate} for aplicado é devolvido um {@link Finder} 
	 * com o valor null. 
	 * @param predicate O {@code Predicate} para fazer a filtragem 
	 * @return O {@link Finder} do primeiro item da coleção após a filtragem
	 */
	protected U primeiro(Predicate<T> predicate) {
		return construirFinderUnitario(FluentIterable.from(getNullSafe())
				.filter(predicate).first().orNull());
	}

	/**
	 * O ultimo item da coleção, caso não existir registro nenhum da coleção é criado um {@link Finder} 
	 * com o valor null. 
	 * Este método é null safe. 
	 * @return O {@link Finder} unitário
	 */
	public U ultimo() {
		return construirFinderUnitario(FluentIterable.from(getNullSafe())
				.last().orNull());
	}

	/**
	 * Se existe algum registro na coleção
	 * @return Se existe registro
	 */
	public boolean existe() {
		return getNullSafe().size() != 0;
	}

	/** 
	 * O item correspondente a posição informada, caso não exista um registro correspondente na coleção é criado um {@link Finder} 
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
	 * Devolve uma {@code ImmutableList} da coleção
	 * @return uma {@code ImmutableList}
	 */
	public ImmutableList<T> toList(){
		return FluentIterable.from(getNullSafe()).toList();
	}
	
	/**
	 * Devolve uma {@code ImmutableSet} da coleção
	 * @return uma {@code ImmutableSet}
	 */
	public ImmutableSet<T> toSet(){
		return FluentIterable.from(getNullSafe()).toSet();
	}

	/**
	 * Sabe construir um {@link Finder} unitário utilizando o valor informado. 
	 * O valor informado não é null safe.
	 * @param valor O valor a ser utilizado na construção.
	 * @return O {@link Finder} unitário da coleção
	 */
	protected abstract U construirFinderUnitario(T valor);
	
	/**
	 * Aplica o {@code Predicate} na coleção e devolve uma nova coleção com o filtro aplicado
	 * @param predicate O {@code Predicate} para fazer a filtragem
	 * @return Uma coleção filtrada
	 */
	protected Collection<T> filtrar(final Predicate<T> predicate){
		return FluentIterable.from(getNullSafe()).filter(predicate).toList();
	}

}
