package br.com.impacta.orcamento.conversor.core;

import java.util.Collection;

/**
 * Devolve a coleção de {@link ConversorCondicao} quando solicitado, para ser registrado 
 * no contexto da fábrica.
 * 
 * @author Arthur Fernandes
 * @see ConversorFactoryGerenciado
 */
public interface ConversorFactoryGerenciadoContexto {

	/**
	 * @return A lista de {@link ConversorCondicao}
	 */
	Collection<ConversorComTipo> inicializarContexto();
	
}
