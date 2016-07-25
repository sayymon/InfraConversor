package br.com.impacta.orcamento.conversor.core;

import br.com.impacta.orcamento.conversor.core.Conversor;


public class TesteArrayOrcamentoConversor implements Conversor<String, Orcamento[]> {

	@Override
	public Orcamento[] converter(String entrada) {
		return new Orcamento[]{new Orcamento(1)};
	}


}
