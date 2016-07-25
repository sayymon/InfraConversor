package br.com.impacta.orcamento.conversor.common;

import java.math.BigDecimal;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class FloatParaBigDecimalConversor implements Conversor<Float, BigDecimal> {

	private final static Conversor<Float, BigDecimal> INSTANCIA = 
				ConversorBuilder.para(new FloatParaBigDecimalConversor())
				.comPreConversaoPadrao().build();
	
	private FloatParaBigDecimalConversor() {}
	
	static Conversor<Float, BigDecimal> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public BigDecimal converter(Float entrada) {
		return BigDecimal.valueOf(entrada);
	}

}
