package br.com.impacta.orcamento.conversor.common;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class DoubleParaIntegerConversor implements Conversor<Double, Integer> {

	private final static Conversor<Double, Integer> INSTANCIA = 
			ConversorBuilder.para(new DoubleParaIntegerConversor())
			.comPreConversaoPadrao().build();
	
	
	private DoubleParaIntegerConversor() {}
	
	static Conversor<Double, Integer> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public Integer converter(Double entrada) {
		return entrada.intValue();
	}

}
