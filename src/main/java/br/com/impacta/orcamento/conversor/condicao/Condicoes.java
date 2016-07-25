package br.com.impacta.orcamento.conversor.condicao;

import br.com.impacta.orcamento.conversor.core.Condicao;

public class Condicoes {

	private Condicoes(){
		// Private
	}
	
	public static Condicao ou(Condicao... condicoes){
		return OuCondicao.de(condicoes);
	}
	
	public static Condicao e(Condicao... condicoes){
		return ECondicao.de(condicoes);
	}
	
	public static Condicao negacao(Condicao condicao){
		return NegacaoCondicao.de(condicao);
	}
}
