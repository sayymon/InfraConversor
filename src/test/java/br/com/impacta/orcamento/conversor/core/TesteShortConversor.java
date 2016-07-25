package br.com.impacta.orcamento.conversor.core;

import br.com.impacta.orcamento.conversor.core.Conversor;


public class TesteShortConversor implements Conversor<String, Short> {

	@Override
	public Short converter(String entrada) {
		return Short.valueOf(entrada);
	}


}
