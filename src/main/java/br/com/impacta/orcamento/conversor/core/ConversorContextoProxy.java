package br.com.impacta.orcamento.conversor.core;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.Collection;

import com.google.common.collect.Lists;

/**
 * O Conversor Contexto Proxy é responsável por criar e fechar um Contexto de Conversão ({@link ConversorContexto}) e 
 * também por adicionar e remover os parâmetros do contexto durante a conversão.
 * <br>
 * @author Arthur Fernandes
 * @see ConversorContexto
 * @see ConversorContextoProxy
 * @param <E> - Objeto que se faz necessário para a conversão
 * @param <S> - Objeto que resultará a conversão.
 */
public final class ConversorContextoProxy<E, S> implements Conversor<E, S>  {

	private final TipoParametrizado<?, ?> classeEntrada;
	private final Conversor<E, S> conversor;

	/*
	 * Construtor privado
	 */
	private ConversorContextoProxy(TipoParametrizado<?, ?> classeEntrada,
			Conversor<E, S> conversor) {
		this.classeEntrada = classeEntrada;
		this.conversor = conversor;
	}

	/** 
	 * Cria uma instância do proxy 
	 * @param conversorFactory O Factory ({@link ConversorFactory}) do conversor a ser utilizado.
	 * @param classeSaida A classe de saída da conversão, utilizada para chamada do factory
	 * @return
	 */
	static <E, S> Conversor<E, S> obterProxy(TipoParametrizado<?, ?> classeEntrada,
			Conversor<E, S> conversor){
		return new ConversorContextoProxy<E, S>(classeEntrada, conversor);
	}

	@Override
	public S converter(E entrada) {
		S saida = null;

		boolean proprietarioContexto = false;

		if (!ConversorContexto.existeContexto()){
			ConversorContexto.abrirContexto();
			proprietarioContexto = true;
		}
		try {
			Collection<ParametroContexto> parametros = obterParametros(entrada);
			for (ParametroContexto parametroContexto : parametros) {
				ConversorContexto.incluirParametro(parametroContexto);
			}
			saida = conversor.converter(entrada);
			for (int i = 0; i < parametros.size(); i++) {
				ConversorContexto.removerUltimoParametro();
			}
		} finally {
			if (proprietarioContexto){
				ConversorContexto.fecharContexto();
			}
		}
		return saida;
	}
	
	private Collection<ParametroContexto> obterParametros(E entrada){
		Collection<ParametroContexto> parametros = Lists.newArrayList();
		
		if (classeEntrada.getClasseOriginal().isAnnotationPresent(ConversorWrapper.class)){
			Field[] fields =  entrada.getClass().getDeclaredFields();
			AccessibleObject.setAccessible(fields, true);
			for (Field field : fields) {
				Object valor;
				try {
					valor = field.get(entrada);
				} catch (IllegalArgumentException e1) {
					throw new RuntimeException("IllegalArgumentException " + field.getName(), e1);
				} catch (IllegalAccessException e1) {
					throw new RuntimeException("IllegalAccessException " + field.getName(), e1);
				}
				TipoParametrizado<?, ?> tipoParametrizado = ConversorRefletionUtil.resolverField(field);
				parametros.add(ParametroContexto.de(tipoParametrizado, valor));
			}
		}else{
			parametros.add(ParametroContexto.de(classeEntrada, entrada));
		}
		return parametros;
	}
	
}
