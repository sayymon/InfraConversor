package br.com.impacta.orcamento.conversor.common;

import org.apache.commons.lang3.StringUtils;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class StringParaDoubleConversor implements Conversor<String, Double> {

	private final static Conversor<String, Double> INSTANCIA = 
			ConversorBuilder.para(new StringParaDoubleConversor())
			.comPreConversaoPadrao().build();
	
	private StringParaDoubleConversor() {}
	
	static Conversor<String, Double> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public Double converter(String entrada) {
		if (StringUtils.isBlank(entrada)){
			return null;
		}
		return Double.valueOf(entrada);
	}

}
