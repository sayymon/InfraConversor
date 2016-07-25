package br.com.impacta.orcamento.conversor.common;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class ShortParaIntegerConversor implements Conversor<Short, Integer> {

	private final static Conversor<Short, Integer> INSTANCIA = 
				ConversorBuilder.para(new ShortParaIntegerConversor())
				.comPreConversaoPadrao().build();
	
	private ShortParaIntegerConversor() {}
	
	static Conversor<Short, Integer> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public Integer converter(Short entrada) {
		return entrada.intValue();
	}

}
