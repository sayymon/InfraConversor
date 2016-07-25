package br.com.impacta.orcamento.conversor.common;

import com.google.common.base.Preconditions;
import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;
import br.com.impacta.orcamento.conversor.core.PreConversoes;

class StringSOuNParaBooleanConversor implements Conversor<String, Boolean> {

	private static final String NAO = "N";
	private static final String SIM = "S";
	
	private final static Conversor<String, Boolean> INSTANCIA = 
			ConversorBuilder.para(new StringSOuNParaBooleanConversor())
			.comPreConversao(PreConversoes.diferenteDeNullComValorPadraoImutavel(Boolean.FALSE))
			.build();
	
	
	private StringSOuNParaBooleanConversor() {}
	
	static Conversor<String, Boolean> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public Boolean converter(String entrada) {
		if (entrada.isEmpty()) {
			return Boolean.FALSE;
		}
		Preconditions.checkArgument(entrada.equalsIgnoreCase(SIM) 
				|| entrada.equalsIgnoreCase(NAO));
		return entrada.equalsIgnoreCase(SIM);
	}
	
}
