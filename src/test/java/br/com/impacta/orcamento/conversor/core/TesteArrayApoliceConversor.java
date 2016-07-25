package br.com.impacta.orcamento.conversor.core;

import br.com.impacta.orcamento.conversor.core.Conversor;


public class TesteArrayApoliceConversor implements Conversor<String, Apolice[]> {

	@Override
	public Apolice[] converter(String entrada) {
		return new Apolice[]{new Apolice(1)};
	}


}
