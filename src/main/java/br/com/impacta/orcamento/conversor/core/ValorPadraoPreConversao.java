package br.com.impacta.orcamento.conversor.core;

/**
 * Classe de callback utilizada pelo utilit�rio {@link PreConversoes} para defini��o do valor padr�o
 * 
 * @author Arthur Fernandes
 *
 * @param <S> Tipo do valor padr�o
 * @see PreConversoes
 */
public interface ValorPadraoPreConversao<S> {

	/** 
	 * Valor padr�o da PreConversao
	 * @return O valor padr�o
	 */
	S valorPadrao();
	
}
