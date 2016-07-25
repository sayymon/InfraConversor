package br.com.impacta.orcamento.conversor.core;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.google.common.base.Preconditions;

/**
 * Classe representa a tipagem da conversão quando precisa-se trabalhar com 
 * tipagens parametrizadas nos conversores.
 * 
 * @author Arthur Fernandes
 *
 * @param <C> O tipo da parametrização do conversor.
 * @param <P> O tipo do tipo da parametrização do conversor.
 */
public class TipoParametrizado<C, P> {

	private final Class<?> classeOriginal;
	private final Class<?> parametrizacaoClasse;
	private final TipoParametrizadoEnum tipo;
	
	private TipoParametrizado(Class<?> classeOriginal, Class<?> parametrizacaoClasse, TipoParametrizadoEnum tipo) {
		this.classeOriginal = classeOriginal;
		this.parametrizacaoClasse = parametrizacaoClasse;
		this.tipo = tipo;
	}
	
	@SuppressWarnings({"unchecked" })
	public static <P> TipoParametrizado<P[], P> array(Class<P> parametrizacaoClasse){
		P[] pArray = (P[])Array.newInstance(parametrizacaoClasse, 0);
		return new TipoParametrizado<P[], P>((Class<P[]>)pArray.getClass(), null, TipoParametrizadoEnum.ARRAY);
	}
	
	public static <P> TipoParametrizado<List<P>, P> list(Class<P> parametrizacaoClasse){
		return new TipoParametrizado<List<P>, P>(List.class, parametrizacaoClasse, TipoParametrizadoEnum.LIST);
	}
	
	public static <P> TipoParametrizado<Collection<P>, P> collection(Class<P> parametrizacaoClasse){
		return new TipoParametrizado<Collection<P>, P>(Collection.class, parametrizacaoClasse, TipoParametrizadoEnum.COLLECTION);
	}
	
	public static <P> TipoParametrizado<Set<P>, P> set(Class<P> parametrizacaoClasse){
		return new TipoParametrizado<Set<P>, P>(Set.class, parametrizacaoClasse, TipoParametrizadoEnum.SET);
	}
	
	static <C> TipoParametrizado<C, ?> de(Class<? extends C> classeOriginal, Class<?> parametrizacaoClasse){
		return new TipoParametrizado<C, Object>(classeOriginal, parametrizacaoClasse, obterTipo(classeOriginal));
	}
	
	public static <C> TipoParametrizado<C, ?> de(Class<? extends C> classeOriginal){
		return de(classeOriginal, null);
	}
	
	public TipoParametrizadoEnum getTipo() {
		return tipo;
	}
	
	public Class<?> getClasseOriginal() {
		return classeOriginal;
	}
	
	public Class<?> getParametrizacaoClasse() {
		return parametrizacaoClasse;
	}
	
	public Class<?> getArrayType(){
		return getClasseOriginal().getComponentType();
	}
	
	public boolean possuiParametrizacao(){
		return parametrizacaoClasse != null;
	}
	
	@Override
	public String toString() {
		String toString = classeOriginal.getSimpleName();
		if(possuiParametrizacao()){
			toString += "<" + parametrizacaoClasse.getSimpleName() + ">";
		}
		return toString;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((classeOriginal == null) ? 0 : classeOriginal.hashCode());
		result = prime
				* result
				+ ((parametrizacaoClasse == null) ? 0 : parametrizacaoClasse
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("rawtypes")
		TipoParametrizado other = (TipoParametrizado) obj;
		if (classeOriginal == null) {
			if (other.classeOriginal != null)
				return false;
		} else if (!classeOriginal.equals(other.classeOriginal))
			return false;
		if (parametrizacaoClasse == null) {
			if (other.parametrizacaoClasse != null)
				return false;
		} else if (!parametrizacaoClasse.equals(other.parametrizacaoClasse))
			return false;
		return true;
	}
	
	static TipoParametrizadoEnum obterTipo(Class<?> classe){
		TipoParametrizadoEnum tipoParametrizadoEnum;
		
		if (isArray(classe)){
			tipoParametrizadoEnum = TipoParametrizadoEnum.ARRAY;
		}else if (isList(classe)){
			tipoParametrizadoEnum = TipoParametrizadoEnum.LIST;
		}else if (isSet(classe)){
			tipoParametrizadoEnum = TipoParametrizadoEnum.SET;
		}else if (isCollection(classe)){
			tipoParametrizadoEnum = TipoParametrizadoEnum.COLLECTION;
		}else{
			tipoParametrizadoEnum = TipoParametrizadoEnum.DEMAIS;
		}
		return tipoParametrizadoEnum;
	}
	
	private static boolean isCollection(Class<?> classe){
		return Collection.class.isAssignableFrom(classe);
	}
	
	private static boolean isList(Class<?> classe){
		return List.class.isAssignableFrom(classe);
	}
	
	private static boolean isSet(Class<?> classe){
		return Set.class.isAssignableFrom(classe);
	}
	
	private static boolean isArray(Class<?> classe){
		return classe.getComponentType() != null;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TipoParametrizado<P, ?> getTipoColecaoOuArray(){
		Preconditions.checkArgument(!TipoParametrizadoEnum.DEMAIS.equals(tipo));
		final TipoParametrizado<P, ?> tipoParametrizado;
		if (TipoParametrizadoEnum.ARRAY.equals(tipo)){
			tipoParametrizado = TipoParametrizado.de((Class)this.getArrayType());
		}else{
			tipoParametrizado = TipoParametrizado.de((Class)this.getParametrizacaoClasse());
		}
		return tipoParametrizado;
	}
	
	
	/**
	 * Verifica se o tipo parametrizado da entrada se aplica ao tipo requerido, na entrada verica-se por herança
	 * se o conversor registrado tiver como entrada Collection e for requerido List ele se aplica para a conversão
	 * @param tipo O tipo a ser vericiado
	 * @return Se for do mesmo tipo
	 */
	public boolean isMesmoTipoComHeranca(TipoParametrizado<?, ?> tipo){
		return ((this.getClasseOriginal().isAssignableFrom(tipo.getClasseOriginal()) && this.possuiParametrizacao() && 
					this.getParametrizacaoClasse().equals(tipo.getParametrizacaoClasse())) 
			|| (this.getClasseOriginal().isAssignableFrom(tipo.getClasseOriginal()) && !this.possuiParametrizacao()));
	}
	
	/**
	 * Verifica se o tipo parametrizado da saida se aplica ao tipo requerido, na saida verica-se por igualdade
	 * se o conversor registrado tiver como saida Collection e for requerido List ele NÃO se aplica para a conversão.
	 * @param tipo O tipo a ser vericiado
	 * @return Se for do mesmo tipo
	 */
	public boolean isMesmoTipoAbsoluto(TipoParametrizado<?, ?> tipo){
		return ((this.getClasseOriginal().equals(tipo.getClasseOriginal()) && this.possuiParametrizacao() && 
					this.getParametrizacaoClasse().equals(tipo.getParametrizacaoClasse())) 
			|| (this.getClasseOriginal().equals(tipo.getClasseOriginal()) && !this.possuiParametrizacao()));
	}
	
}
