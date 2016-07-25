package br.com.impacta.orcamento.conversor.common;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class LongParaDoubleConversor implements Conversor<Long, Double> {

	private final static Conversor<Long, Double> INSTANCIA = 
				ConversorBuilder.para(new LongParaDoubleConversor())
				.comPreConversaoPadrao().build();
	
	private LongParaDoubleConversor() {}
	
	static Conversor<Long, Double> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public Double converter(Long entrada) {
		return entrada.doubleValue();
	}

}
