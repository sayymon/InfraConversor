package br.com.impacta.orcamento.conversor.common;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class IntegerParaShortConversor implements Conversor<Integer, Short> {

	private final static Conversor<Integer, Short> INSTANCIA = 
			ConversorBuilder.para(new IntegerParaShortConversor())
			.comPreConversaoPadrao().build();
	
	private IntegerParaShortConversor() {}
	
	static Conversor<Integer, Short> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public Short converter(Integer entrada) {
		return entrada.shortValue();
	}

}
