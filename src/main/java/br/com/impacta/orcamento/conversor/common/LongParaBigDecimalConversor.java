package br.com.impacta.orcamento.conversor.common;

import java.math.BigDecimal;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class LongParaBigDecimalConversor implements Conversor<Long, BigDecimal> {

	private final static Conversor<Long, BigDecimal> INSTANCIA = 
			ConversorBuilder.para(new LongParaBigDecimalConversor())
			.comPreConversaoPadrao().build();
	
	private LongParaBigDecimalConversor() {}
	
	static Conversor<Long, BigDecimal> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public BigDecimal converter(Long entrada) {
		return BigDecimal.valueOf(entrada);
	}

}
