package br.com.impacta.orcamento.conversor.common;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class ShortParaStringConversor implements Conversor<Short, String> {

	private final static Conversor<Short, String> INSTANCIA = 
			ConversorBuilder.para(new ShortParaStringConversor())
			.comPreConversaoPadrao().build();
	
	private ShortParaStringConversor() {}
	
	static Conversor<Short, String> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public String converter(Short entrada) {
		return entrada.toString();
	}

}
