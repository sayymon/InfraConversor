package br.com.impacta.orcamento.conversor.core;

/**
 * {@code Condicao} permite implementar condições para a execução do {@link Conversor}.
 * 
 * Quando associado com um {@link Conversor} o mesmo apenas é utilizado quando a condição é verdadeira.
 * 
 * @author Arthur Fernandes
 */
public interface Condicao {
	
	boolean seAplica();
	
}