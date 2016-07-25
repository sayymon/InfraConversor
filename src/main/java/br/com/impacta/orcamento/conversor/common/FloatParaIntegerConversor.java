package br.com.impacta.orcamento.conversor.common;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class FloatParaIntegerConversor implements Conversor<Float, Integer> {

	private final static Conversor<Float, Integer> INSTANCIA = 
				ConversorBuilder.para(new FloatParaIntegerConversor())
				.comPreConversaoPadrao().build();
	
	private FloatParaIntegerConversor() {}
	
	static Conversor<Float, Integer> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public Integer converter(Float entrada) {
		return entrada.intValue();
	}
}
