package br.com.impacta.orcamento.conversor.core;

import br.com.impacta.orcamento.conversor.core.Conversor;


public class TesteArray2Conversor implements Conversor<String, Short[]> {

	@Override
	public Short[] converter(String entrada) {
		return new Short[]{Short.valueOf("9999")};
	}


}
