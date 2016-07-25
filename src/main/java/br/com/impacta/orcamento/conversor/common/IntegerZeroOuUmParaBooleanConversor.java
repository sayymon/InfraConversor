package br.com.impacta.orcamento.conversor.common;

import com.google.common.base.Preconditions;
import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;
import br.com.impacta.orcamento.conversor.core.PreConversoes;

class IntegerZeroOuUmParaBooleanConversor implements Conversor<Integer, Boolean> {

	private static final Integer NAO = 0;
	private static final Integer SIM = 1;
	
	private final static Conversor<Integer, Boolean> INSTANCIA = 
			ConversorBuilder.para(new IntegerZeroOuUmParaBooleanConversor())
				.comPreConversao(PreConversoes.diferenteDeNullComValorPadraoImutavel(Boolean.FALSE))
				.build();
	
	private IntegerZeroOuUmParaBooleanConversor() {}
	
	static Conversor<Integer, Boolean> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public Boolean converter(Integer entrada) {
		if (entrada == null) {
			return Boolean.FALSE;
		}
		Preconditions.checkArgument(entrada.equals(SIM) 
				|| entrada.equals(NAO));
		return entrada.equals(SIM);
	}

	
}
