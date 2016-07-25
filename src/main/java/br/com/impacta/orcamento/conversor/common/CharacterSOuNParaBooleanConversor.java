package br.com.impacta.orcamento.conversor.common;

import com.google.common.base.Preconditions;
import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;
import br.com.impacta.orcamento.conversor.core.PreConversoes;

class CharacterSOuNParaBooleanConversor implements Conversor<Character, Boolean> {

	private static final String NAO = "N";
	private static final String SIM = "S";
	
	private final static Conversor<Character, Boolean> INSTANCIA = 
			ConversorBuilder.para(new CharacterSOuNParaBooleanConversor())
			.comPreConversao(PreConversoes.diferenteDeNullComValorPadraoImutavel(Boolean.FALSE))
			.build();
	
	
	private CharacterSOuNParaBooleanConversor() {}
	
	static Conversor<Character, Boolean> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public Boolean converter(Character character) {
		final String entrada = String.valueOf(character);
		if (entrada.isEmpty()) {
			return Boolean.FALSE;
		}
		Preconditions.checkArgument(entrada.equalsIgnoreCase(SIM) 
				|| entrada.equalsIgnoreCase(NAO));
		return entrada.equalsIgnoreCase(SIM);
	}
	
}
