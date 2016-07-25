package br.com.impacta.orcamento.conversor.common;

import java.math.BigDecimal;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class BigDecimalParaFloatConversor implements Conversor<BigDecimal, Float> {

	private final static Conversor<BigDecimal, Float> INSTANCIA = 
			ConversorBuilder.para(new BigDecimalParaFloatConversor())
			.comPreConversaoPadrao().build();
	
	private BigDecimalParaFloatConversor() {}
	
	static Conversor<BigDecimal, Float> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public Float converter(BigDecimal entrada) {
		return entrada.floatValue();
	}

}
