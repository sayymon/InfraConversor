package br.com.impacta.orcamento.conversor.common;

import org.apache.commons.lang3.StringUtils;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class StringParaLongConversor implements Conversor<String, Long> {

	private final static Conversor<String, Long> INSTANCIA = 
			ConversorBuilder.para(new StringParaLongConversor())
			.comPreConversaoPadrao().build();
	
	private StringParaLongConversor() {}
	
	static Conversor<String, Long> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public Long converter(String entrada) {
		if (StringUtils.isBlank(entrada)){
			return null;
		}
		return Long.valueOf(entrada);
	}

}
