package br.com.impacta.orcamento.conversor.common;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class StringParaBigDecimalConversor implements Conversor<String, BigDecimal> {

	private final static Conversor<String, BigDecimal> INSTANCIA = 
			ConversorBuilder.para(new StringParaBigDecimalConversor())
			.comPreConversaoPadrao().build();
	
	private StringParaBigDecimalConversor() {}
	
	static Conversor<String, BigDecimal> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public BigDecimal converter(String entrada) {
		if (StringUtils.isBlank(entrada)){
			return null;
		}
		return new BigDecimal(entrada);
	}

}
