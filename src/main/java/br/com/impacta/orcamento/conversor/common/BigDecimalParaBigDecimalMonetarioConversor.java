package br.com.impacta.orcamento.conversor.common;

import java.math.BigDecimal;
import java.math.RoundingMode;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class BigDecimalParaBigDecimalMonetarioConversor implements Conversor<BigDecimal, BigDecimal> {

	private final static Conversor<BigDecimal, BigDecimal> INSTANCIA = 
			ConversorBuilder.para(new BigDecimalParaBigDecimalMonetarioConversor())
			.comPreConversaoPadrao().build();
	
	private BigDecimalParaBigDecimalMonetarioConversor() {}
	
	static Conversor<BigDecimal, BigDecimal> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public BigDecimal converter(BigDecimal entrada) {
		return entrada.setScale(2, RoundingMode.HALF_UP);
	}

}
