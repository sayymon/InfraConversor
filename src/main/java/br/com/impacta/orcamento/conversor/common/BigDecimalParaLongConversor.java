package br.com.impacta.orcamento.conversor.common;

import java.math.BigDecimal;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class BigDecimalParaLongConversor implements Conversor<BigDecimal, Long> {

	private final static Conversor<BigDecimal, Long> INSTANCIA = 
				ConversorBuilder.para(new BigDecimalParaLongConversor())
				.comPreConversaoPadrao().build();
	
	private BigDecimalParaLongConversor() {}
	
	static Conversor<BigDecimal, Long> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public Long converter(BigDecimal entrada) {
		return entrada.longValue();
	}

}
