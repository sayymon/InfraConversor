package br.com.impacta.orcamento.conversor.common;

import java.math.BigDecimal;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class CoeficienteParaPercentualConversor implements Conversor<BigDecimal, BigDecimal> {

	private final static Conversor<BigDecimal, BigDecimal> INSTANCIA = 
			ConversorBuilder.para(new CoeficienteParaPercentualConversor())
			.comPreConversaoPadrao().build();
	
	private CoeficienteParaPercentualConversor() {}
	
	static Conversor<BigDecimal, BigDecimal> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public BigDecimal converter(BigDecimal entrada) {
		return BigDecimal.ONE.subtract(entrada).multiply(BigDecimal.valueOf(100)).abs(); 
	}
}
