package br.com.impacta.orcamento.conversor.common;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class BooleanParaStringSOuNConversor implements Conversor<Boolean, String> {

	private static final String NAO = "N";
	private static final String SIM = "S";
	
	private final static Conversor<Boolean, String> INSTANCIA = 
				ConversorBuilder.para(new BooleanParaStringSOuNConversor())
				.comPreConversaoPadrao().build();
	
	private BooleanParaStringSOuNConversor() {}
	
	static Conversor<Boolean, String> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public String converter(Boolean condicional) {
		String retorno = null;
		if (condicional) {
			retorno = SIM;
		} else {
			retorno =  NAO;
		}
		return retorno;
	}


	
}

