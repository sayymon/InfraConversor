package br.com.impacta.orcamento.conversor.common;

import java.math.BigDecimal;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class BigDecimalParaDoubleConversor implements Conversor<BigDecimal, Double> {

	private final static Conversor<BigDecimal, Double> INSTANCIA = 
			ConversorBuilder.para(new BigDecimalParaDoubleConversor())
			.comPreConversaoPadrao().build();
	
	private BigDecimalParaDoubleConversor() {}
	
	static Conversor<BigDecimal, Double> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public Double converter(BigDecimal entrada) {
		return entrada.doubleValue();
	}

}
