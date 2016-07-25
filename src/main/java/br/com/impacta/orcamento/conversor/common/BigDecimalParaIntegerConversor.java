package br.com.impacta.orcamento.conversor.common;

import java.math.BigDecimal;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class BigDecimalParaIntegerConversor implements Conversor<BigDecimal, Integer> {

	private final static Conversor<BigDecimal, Integer> INSTANCIA = 
				ConversorBuilder.para(new BigDecimalParaIntegerConversor())
				.comPreConversaoPadrao().build();
	
	private BigDecimalParaIntegerConversor() {}
	
	static Conversor<BigDecimal, Integer> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public Integer converter(BigDecimal entrada) {
		return entrada.intValue();
	}

}
