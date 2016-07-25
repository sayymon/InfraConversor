package br.com.impacta.orcamento.conversor.common;

import java.math.BigInteger;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class LongParaBigIntegerConversor implements Conversor<Long, BigInteger> {

	private final static Conversor<Long, BigInteger> INSTANCIA = 
			ConversorBuilder.para(new LongParaBigIntegerConversor())
			.comPreConversaoPadrao().build();
	
	private LongParaBigIntegerConversor() {}
	
	static Conversor<Long, BigInteger> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public BigInteger converter(Long entrada) {
		return BigInteger.valueOf(entrada);
	}

}
