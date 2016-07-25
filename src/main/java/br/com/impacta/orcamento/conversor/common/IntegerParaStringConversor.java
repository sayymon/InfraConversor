package br.com.impacta.orcamento.conversor.common;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class IntegerParaStringConversor implements Conversor<Integer, String> {

	private final static Conversor<Integer, String> INSTANCIA = 
			ConversorBuilder.para(new IntegerParaStringConversor())
			.comPreConversaoPadrao().build();
	
	private IntegerParaStringConversor() {}
	
	static Conversor<Integer, String> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public String converter(Integer entrada) {
		return entrada.toString();
	}

}
