package br.com.impacta.orcamento.conversor.core;

/**
 * {@link} RegraConversao}, quando implementada esta interface no {@link Conversor} é possível definir 
 * quando a conversão deve ser realizada.
 * 
 * Quando a conversão não se aplica o valor padrão do conversor é retornado. O Valor padrão é definido por {@link ValorPadraoConversao}.
 *  
 * @author Arthur Fernandes
 * @see ValorPadraoConversao
 *
 */
public interface PreConversao<S> {

	boolean aplicaConversao();
	
	S valorPadrao();
	
}
