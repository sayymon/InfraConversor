package br.com.impacta.orcamento.conversor.common;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class IntegerParaDoubleConversor implements Conversor<Integer, Double> {

	private final static Conversor<Integer, Double> INSTANCIA = 
				ConversorBuilder.para(new IntegerParaDoubleConversor())
				.comPreConversaoPadrao().build();
	
	private IntegerParaDoubleConversor() {}
	
	static Conversor<Integer, Double> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public Double converter(Integer entrada) {
		return entrada.doubleValue();
	}

}
