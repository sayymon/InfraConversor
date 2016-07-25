package br.com.impacta.orcamento.conversor.common;

import java.math.BigDecimal;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class IntegerParaBigDecimalConversor implements Conversor<Integer, BigDecimal> {

	private final static Conversor<Integer, BigDecimal> INSTANCIA = 
				ConversorBuilder.para(new IntegerParaBigDecimalConversor())
				.comPreConversaoPadrao().build();
	
	private IntegerParaBigDecimalConversor() {}
	
	static Conversor<Integer, BigDecimal> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public BigDecimal converter(Integer entrada) {
		return BigDecimal.valueOf(entrada);
	}

}
