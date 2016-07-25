package br.com.impacta.orcamento.conversor.common;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class LongParaIntegerConversor implements Conversor<Long, Integer> {

	private final static Conversor<Long, Integer> INSTANCIA = 
			ConversorBuilder.para(new LongParaIntegerConversor())
			.comPreConversaoPadrao().build();
	
	private LongParaIntegerConversor() {}
	
	static Conversor<Long, Integer> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public Integer converter(Long entrada) {
		return entrada.intValue();
	}

}
