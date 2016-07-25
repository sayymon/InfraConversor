package br.com.impacta.orcamento.conversor.core;

import java.lang.reflect.Array;
import java.util.Collection;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Classe utilitária utilizada para criar a {@link PreConversao}
 * 
 * @author Arthur Fernandes
 * @see PreConversao
 */
public class PreConversoes {

	private static boolean aplicaConversaoDeCollection() {
		@SuppressWarnings("rawtypes")
		Collection collection = ConversorContexto.ultimoParametro();
		return collection != null && collection.size() > 0;
	}
	
	private static boolean aplicaConversaoDeObject() {
		return ConversorContexto.ultimoParametro() != null;
	}
	
	/**
	 * PreConversao para collections diferente de vazio com valor padrão informado
	 * @param valorPadraoCallBack Classe de callback utilizada para devolver o valor padrão
	 * @return A {@link PreConversao}
	 */
	public static <S> PreConversao<S> collectionsDiferenteDeVazioComValorPadrao(final ValorPadraoPreConversao<S> valorPadraoCallBack) {
		Preconditions.checkNotNull(valorPadraoCallBack);
		return new PreConversao<S>() {
			@Override
			public boolean aplicaConversao() {
				return aplicaConversaoDeCollection();
			}

			@Override
			public S valorPadrao() {
				return valorPadraoCallBack.valorPadrao();
			}
		};
	}
	
	/**
	 * PreConversao para objects diferente de null com valor padrão informado
	 * @param valorPadraoCallBack Classe de callback utilizada para devolver o valor padrão
	 * @return A {@link PreConversao}
	 */
	public static <S> PreConversao<S> diferenteDeNullComValorPadrao(final ValorPadraoPreConversao<S> valorPadraoCallBack){
		Preconditions.checkNotNull(valorPadraoCallBack);
		return new PreConversao<S>() {
			@Override
			public boolean aplicaConversao() {
				return aplicaConversaoDeObject();
			}

			@Override
			public S valorPadrao() {
				return valorPadraoCallBack.valorPadrao();
			}
		};
	}
	
	/**
	 * PreConversao para objects diferente de null com valor padrão informado imutável. Este método não deve
	 * ser utilizado para instâncias mutáveis. 
	 * @param valorPadraoCallBack Classe de callback utilizada para devolver o valor padrão
	 * @return A {@link PreConversao}
	 */
	public static <S> PreConversao<S> diferenteDeNullComValorPadraoImutavel(final S valorPadraoImutavel){
		return diferenteDeNullComValorPadrao(new ValorPadraoPreConversao<S>() {
			@Override
			public S valorPadrao() {
				return valorPadraoImutavel;
			}
		});
	}
	
	/**
	 * PreConversao para objects diferente de null com valor null
	 * @return A {@link PreConversao}
	 */
	public static <S> PreConversao<S> diferenteDeNullComValorPadraoNull(){
		return diferenteDeNullComValorPadrao(PreConversoes.<S>criarValorPadraoNull());
	}
	
	/**
	 * PreConversao para objects que sempre passa para a conversão
	 * @return A {@link PreConversao}
	 */
	public static <S> PreConversao<S> sempreConverte(){
		return new PreConversao<S>() {
			@Override
			public boolean aplicaConversao() {
				return true;
			}

			@Override
			public S valorPadrao() {
				// Como sempre irá converter nunca tera valor padrao
				return null;
			}
		};
	}
	
	/**
	 * Cria a {@link PreConversao} padrão baseado no tipo de entrada e saída do conversor.
	 * <br>
	 * - Quando o tipo da entrada é uma Collection a regra de entrada é se for diferente de null
	 * e diferente de vazio, quando o tipo da entrada é object a regra de entrada é se for 
	 * diferente de null.
	 * <br>
	 * - Quando a saída é Collection, List, Set ou Array o valor padrão de saída é uma instancia vazia. E quando
	 * for Object é null.
	 * 
	 * @param classeEntrada O tipo da entrada
	 * @param classeSaida O Tipo da saída
	 * @return A {@link PreConversao}
	 */
	public static <E, S> PreConversao<S> criarPreConversaoDefaultPara(final TipoParametrizado<?, ?> classeEntrada, 
			final TipoParametrizado<?, ?> classeSaida){
		final ValorPadraoPreConversao<S> valorPadraoCallback;
		
		if(classeSaida.getTipo().equals(TipoParametrizadoEnum.COLLECTION) ||
				classeSaida.getTipo().equals(TipoParametrizadoEnum.LIST)){
			valorPadraoCallback = criarValorPadraoEmptyList(); 
		}else if(classeSaida.getTipo().equals(TipoParametrizadoEnum.SET)){
			valorPadraoCallback = criarValorPadraoEmptySet();
		}else if(classeSaida.getTipo().equals(TipoParametrizadoEnum.ARRAY)){
			valorPadraoCallback = criarValorPadraoEmptyArray(classeSaida);
		}else{
			valorPadraoCallback = criarValorPadraoNull();
		}
		
		final PreConversao<S> preConversao;
		
		if(classeEntrada.getTipo().equals(TipoParametrizadoEnum.COLLECTION) ||
				classeEntrada.getTipo().equals(TipoParametrizadoEnum.LIST) || 
				classeEntrada.getTipo().equals(TipoParametrizadoEnum.SET)){
			preConversao = PreConversoes.collectionsDiferenteDeVazioComValorPadrao(valorPadraoCallback);
		}else{
			preConversao = PreConversoes.diferenteDeNullComValorPadrao(valorPadraoCallback);
		}
		
		return preConversao;
	}
	
	private static <S> ValorPadraoPreConversao<S> criarValorPadraoEmptyList(){
		return new ValorPadraoPreConversao<S>() {
			@SuppressWarnings("unchecked")
			@Override
			public S valorPadrao() {
				return (S) Lists.newArrayList();
			}
		};
	}
	
	private static <S> ValorPadraoPreConversao<S> criarValorPadraoEmptySet(){
		return new ValorPadraoPreConversao<S>() {
			@SuppressWarnings("unchecked")
			@Override
			public S valorPadrao() {
				return (S) Sets.newHashSet();
			}
		};
	}
	
	private static <S> ValorPadraoPreConversao<S> criarValorPadraoEmptyArray(final TipoParametrizado<?, ?> classeSaida){
		return new ValorPadraoPreConversao<S>() {
			@SuppressWarnings("unchecked")
			@Override
			public S valorPadrao() {
				return (S)Array.newInstance(classeSaida.getArrayType(), 0);
			}
		};
	}
	
	private static <S> ValorPadraoPreConversao<S> criarValorPadraoNull(){
		return new ValorPadraoPreConversao<S>() {
			@Override
			public S valorPadrao() {
				return null;
			}
		};
	}
}
