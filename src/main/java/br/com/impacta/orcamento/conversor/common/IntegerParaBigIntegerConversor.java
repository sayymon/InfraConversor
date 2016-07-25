package br.com.impacta.orcamento.conversor.common;

import java.math.BigInteger;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class IntegerParaBigIntegerConversor implements Conversor<Integer, BigInteger> {

	private final static Conversor<Integer, BigInteger> INSTANCIA = 
				ConversorBuilder.para(new IntegerParaBigIntegerConversor())
				.comPreConversaoPadrao().build();
	
	private IntegerParaBigIntegerConversor() {}
	
	static Conversor<Integer, BigInteger> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public BigInteger converter(Integer entrada) {
		return BigInteger.valueOf(entrada);
	}

}
