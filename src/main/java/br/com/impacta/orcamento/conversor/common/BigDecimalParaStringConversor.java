package br.com.impacta.orcamento.conversor.common;

import java.math.BigDecimal;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class BigDecimalParaStringConversor implements Conversor<BigDecimal, String> {

	private final static Conversor<BigDecimal, String> INSTANCIA = 
			ConversorBuilder.para(new BigDecimalParaStringConversor())
			.comPreConversaoPadrao().build();
	
	private BigDecimalParaStringConversor() {}
	
	static Conversor<BigDecimal, String> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public String converter(BigDecimal entrada) {
		return entrada.toString();
	}

}
