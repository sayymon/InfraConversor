package br.com.impacta.orcamento.conversor.core;

public class ApoliceParaOrcamentoConversor implements Conversor<Apolice, Orcamento> {

	@Override
	public Orcamento converter(Apolice entrada) {
		if (entrada.getCodigo().equals(45)){
			return null;
		}
		return new Orcamento(entrada.getCodigo());
	}

}
