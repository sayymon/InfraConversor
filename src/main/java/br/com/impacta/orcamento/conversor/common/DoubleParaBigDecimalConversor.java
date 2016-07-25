package br.com.impacta.orcamento.conversor.common;

import java.math.BigDecimal;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class DoubleParaBigDecimalConversor implements Conversor<Double, BigDecimal> {

	private final static Conversor<Double, BigDecimal> INSTANCIA = 
				ConversorBuilder.para(new DoubleParaBigDecimalConversor())
				.comPreConversaoPadrao().build();
	
	private DoubleParaBigDecimalConversor() {}
	
	static Conversor<Double, BigDecimal> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public BigDecimal converter(Double entrada) {
		return BigDecimal.valueOf(entrada);
	}

}
