package br.com.impacta.orcamento.conversor.core;

import java.lang.reflect.Field;
import java.util.Collection;

import com.google.common.reflect.TypeToken;

/** 
 * Utilitário utilizado internamente pela infra de conversão para resolver problemas utilizando reflexão.
 * @author Arthur Fernandes
 *
 */
public class ConversorRefletionUtil {

	private static final Integer ORDEM_PARAMETRO_ENTRADA_CONVERSOR = 0;
	private static final Integer ORDEM_PARAMETRO_SAIDA_CONVERSOR = 1;
	
	private ConversorRefletionUtil() {
		// private
	}
	
	@SuppressWarnings("unchecked")
	static <E> TipoParametrizado<E, ?> obterClasseEntradaTipoParametrizadoConversor(Class<?> classeConversor) {
		return (TipoParametrizado<E, ?>)obterClasseTipoParametrizadoConversor(classeConversor, ORDEM_PARAMETRO_ENTRADA_CONVERSOR);
	}
	
	@SuppressWarnings("unchecked")
	static <S> TipoParametrizado<S, ?> obterClasseSaidaTipoParametrizadoConversor(Class<?> classeConversor) {
		return (TipoParametrizado<S, ?>)obterClasseTipoParametrizadoConversor(classeConversor, ORDEM_PARAMETRO_SAIDA_CONVERSOR);
	}
	
	private static TipoParametrizado<?, ?> obterClasseTipoParametrizadoConversor(Class<?> classeConversor, int parametro){
		TypeToken<?> typeConversor = TypeToken.of(classeConversor);
		TypeToken<?> typeSaidaConversor = typeConversor.resolveType(Conversor.class.getTypeParameters()[parametro]); 
		
		Class<?> classSaidaConversor = typeSaidaConversor.getRawType();
		Class<?> parametrizacaoClasse = null;
		if (Collection.class.isAssignableFrom(classSaidaConversor)){
			parametrizacaoClasse = typeSaidaConversor.resolveType(Collection.class.getTypeParameters()[0]).getRawType();
		}
		return TipoParametrizado.de(classSaidaConversor, parametrizacaoClasse); 
	}
	
	static TipoParametrizado<?, ?> resolverField(Field field){
		TypeToken<?> type = TypeToken.of(field.getGenericType());
		Class<?> parametrizacaoClass = null;
		if (Collection.class.isAssignableFrom(type.getRawType())){
			parametrizacaoClass = type.resolveType(Collection.class.getTypeParameters()[0]).getRawType();
		}
		TipoParametrizado<?, ?> tipoParametrizado = TipoParametrizado.de(field.getType(), parametrizacaoClass);
		return tipoParametrizado;
	}
	
	
}
