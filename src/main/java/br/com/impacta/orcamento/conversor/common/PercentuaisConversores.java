package br.com.impacta.orcamento.conversor.common;

import java.math.BigDecimal;

import br.com.impacta.orcamento.conversor.core.Conversor;

public class PercentuaisConversores {

	private PercentuaisConversores() {
		//Privado
	}
	
	public static Conversor<BigDecimal, BigDecimal> percentualParaCoeficiente(){
		return PercentualParaCoeficienteConversor.intancia();
	}
	
	public static Conversor<BigDecimal, BigDecimal> coeficienteParaPercentual(){
		return CoeficienteParaPercentualConversor.intancia();
	}
		
}
