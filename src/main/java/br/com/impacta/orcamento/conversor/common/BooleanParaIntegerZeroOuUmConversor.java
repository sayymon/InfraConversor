package br.com.impacta.orcamento.conversor.common;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class BooleanParaIntegerZeroOuUmConversor implements Conversor<Boolean, Integer> {

	private static final Integer NAO = 0;
	private static final Integer SIM = 1;
	
	private final static Conversor<Boolean, Integer> INSTANCIA = 
			ConversorBuilder.para(new BooleanParaIntegerZeroOuUmConversor())
			.comPreConversaoPadrao().build();
	
	private BooleanParaIntegerZeroOuUmConversor() {}
	
	static Conversor<Boolean, Integer> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public Integer converter(Boolean condicional) {
		Integer retorno = null;
		if (condicional) {
			retorno = SIM;
		} else {
			retorno =  NAO;
		}
		return retorno;
	}


	
}

