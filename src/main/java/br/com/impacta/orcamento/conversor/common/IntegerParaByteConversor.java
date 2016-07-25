package br.com.impacta.orcamento.conversor.common;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class IntegerParaByteConversor implements Conversor<Integer, Byte> {

	private final static Conversor<Integer, Byte> INSTANCIA = 
			ConversorBuilder.para(new IntegerParaByteConversor())
			.comPreConversaoPadrao().build();
	
	private IntegerParaByteConversor() {}
	
	static Conversor<Integer, Byte> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public Byte converter(Integer entrada) {
		return entrada.byteValue();
	}

}
