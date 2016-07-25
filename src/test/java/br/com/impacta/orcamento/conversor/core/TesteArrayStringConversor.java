package br.com.impacta.orcamento.conversor.core;

import br.com.impacta.orcamento.conversor.core.Conversor;


public class TesteArrayStringConversor implements Conversor<String, String[]> {

	@Override
	public String[] converter(String entrada) {
		return new String[]{"7410"};
	}


}
