package br.com.impacta.orcamento.conversor.core;

import br.com.impacta.orcamento.conversor.core.Conversor;


public class TesteArrayString2Conversor implements Conversor<Long[], String[]> {

	@Override
	public String[] converter(Long[] entrada) {
		return new String[]{"9630"};
	}


}
