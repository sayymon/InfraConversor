package br.com.impacta.orcamento.conversor.condicao;

import java.util.ArrayList;
import java.util.Collection;

import br.com.impacta.orcamento.conversor.core.Condicao;

public class ECondicao implements Condicao {

	private Collection<Condicao> regras = new ArrayList<Condicao>();
	
	private ECondicao() {
		// private
	}
	
	public static ECondicao de(Condicao... regrasArray){
		ECondicao regra = new ECondicao();
		for (Condicao regraConversor : regrasArray) {
			regra.regras.add(regraConversor);
		}
		return regra;
	}
	@Override
	public boolean seAplica() {
		boolean usaConversor = true;
		for (Condicao regra : regras) {
			if (!regra.seAplica()){
				usaConversor = false;
				break;
			}
		}
		return usaConversor;
	}

}
