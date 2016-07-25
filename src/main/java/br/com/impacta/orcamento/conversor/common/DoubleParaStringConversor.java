package br.com.impacta.orcamento.conversor.common;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class DoubleParaStringConversor implements Conversor<Double, String> {

	private final static Conversor<Double, String> INSTANCIA = 
				ConversorBuilder.para(new DoubleParaStringConversor())
				.comPreConversaoPadrao().build();
	
	private DoubleParaStringConversor() {}
	
	static Conversor<Double, String> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public String converter(Double entrada) {
		return entrada.toString();
	}

}
