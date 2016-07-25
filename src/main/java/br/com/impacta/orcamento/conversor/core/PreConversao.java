package br.com.impacta.orcamento.conversor.core;

/**
 * {@link} RegraConversao}, quando implementada esta interface no {@link Conversor} � poss�vel definir 
 * quando a convers�o deve ser realizada.
 * 
 * Quando a convers�o n�o se aplica o valor padr�o do conversor � retornado. O Valor padr�o � definido por {@link ValorPadraoConversao}.
 *  
 * @author Arthur Fernandes
 * @see ValorPadraoConversao
 *
 */
public interface PreConversao<S> {

	boolean aplicaConversao();
	
	S valorPadrao();
	
}
