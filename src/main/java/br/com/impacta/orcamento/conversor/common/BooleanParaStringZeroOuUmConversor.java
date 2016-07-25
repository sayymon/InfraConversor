package br.com.impacta.orcamento.conversor.common;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class BooleanParaStringZeroOuUmConversor implements Conversor<Boolean, String> {

	private final static Conversor<Boolean, String> INSTANCIA = 
			ConversorBuilder.para(new BooleanParaStringZeroOuUmConversor())
			.comPreConversaoPadrao().build();
	
	private BooleanParaStringZeroOuUmConversor() {}
	
	static Conversor<Boolean, String> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public String converter(Boolean condicional) {
		return String.valueOf(BooleanParaIntegerZeroOuUmConversor.intancia().converter(condicional));
	}
	
}

