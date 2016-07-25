package br.com.impacta.orcamento.conversor.common;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class LongParaStringConversor implements Conversor<Long, String> {

	private final static Conversor<Long, String> INSTANCIA = 
			ConversorBuilder.para(new LongParaStringConversor())
			.comPreConversaoPadrao().build();
	
	private LongParaStringConversor() {}
	
	static Conversor<Long, String> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public String converter(Long entrada) {
		return entrada.toString();
	}

}
