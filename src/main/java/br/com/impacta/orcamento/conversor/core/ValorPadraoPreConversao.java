package br.com.impacta.orcamento.conversor.core;

/**
 * Classe de callback utilizada pelo utilitário {@link PreConversoes} para definição do valor padrão
 * 
 * @author Arthur Fernandes
 *
 * @param <S> Tipo do valor padrão
 * @see PreConversoes
 */
public interface ValorPadraoPreConversao<S> {

	/** 
	 * Valor padrão da PreConversao
	 * @return O valor padrão
	 */
	S valorPadrao();
	
}
