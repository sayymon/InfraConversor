package br.com.impacta.orcamento.conversor.core;

/**
 * {@code Condicao} permite implementar condi��es para a execu��o do {@link Conversor}.
 * 
 * Quando associado com um {@link Conversor} o mesmo apenas � utilizado quando a condi��o � verdadeira.
 * 
 * @author Arthur Fernandes
 */
public interface Condicao {
	
	boolean seAplica();
	
}