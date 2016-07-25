package br.com.impacta.orcamento.conversor.common;

import java.math.BigInteger;

import org.apache.commons.lang3.StringUtils;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class StringParaBigIntegerConversor implements Conversor<String, BigInteger> {

	private final static Conversor<String, BigInteger> INSTANCIA = 
			ConversorBuilder.para(new StringParaBigIntegerConversor())
			.comPreConversaoPadrao().build();
	
	private StringParaBigIntegerConversor() {}
	
	static Conversor<String, BigInteger> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public BigInteger converter(String entrada) {
		if (StringUtils.isBlank(entrada)){
			return null;
		}
		return BigInteger.valueOf(NumerosConversores.stringParaLong().converter(entrada));
	}

}
