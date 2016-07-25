package br.com.impacta.orcamento.conversor.common;

import org.apache.commons.lang3.StringUtils;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class StringParaShortConversor implements Conversor<String, Short> {

	private final static Conversor<String, Short> INSTANCIA = 
			ConversorBuilder.para(new StringParaShortConversor())
			.comPreConversaoPadrao().build();
	
	private StringParaShortConversor() {}
	
	static Conversor<String, Short> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public Short converter(String entrada) {
		if (StringUtils.isBlank(entrada)){
			return null;
		}
		return Short.valueOf(entrada);
	}

}
