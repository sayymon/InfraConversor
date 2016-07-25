package br.com.impacta.orcamento.conversor.condicao;

import br.com.impacta.orcamento.conversor.core.Condicao;

public class NegacaoCondicao implements Condicao {

	private final Condicao regra;
	
	private NegacaoCondicao(Condicao regra) {
		this.regra = regra;
	}
	
	public static NegacaoCondicao de(Condicao regra){
		return new NegacaoCondicao(regra);
	}
	
	@Override
	public boolean seAplica() {
		return !regra.seAplica();
	}

}
