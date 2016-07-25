package br.com.impacta.orcamento.conversor.common;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class IntegerParaLongConversor implements Conversor<Integer, Long> {

	private final static Conversor<Integer, Long> INSTANCIA = 
				ConversorBuilder.para(new IntegerParaLongConversor())
				.comPreConversaoPadrao().build();
	
	private IntegerParaLongConversor() {}
	
	static Conversor<Integer, Long> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public Long converter(Integer entrada) {
		return Long.valueOf(entrada);
	}

}
