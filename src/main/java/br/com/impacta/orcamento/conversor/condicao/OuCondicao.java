package br.com.impacta.orcamento.conversor.condicao;

import java.util.ArrayList;
import java.util.Collection;

import br.com.impacta.orcamento.conversor.core.Condicao;

public class OuCondicao implements Condicao {

	private Collection<Condicao> regras = new ArrayList<Condicao>();
	
	private OuCondicao() {
		// private
	}
	
	public static OuCondicao de(Condicao... regrasArray){
		OuCondicao regra = new OuCondicao();
		for (Condicao regraConversor : regrasArray) {
			regra.regras.add(regraConversor);
		}
		return regra;
	}
	@Override
	public boolean seAplica() {
		boolean usaConversor = false;
		for (Condicao regra : regras) {
			if (regra.seAplica()){
				usaConversor = true;
				break;
			}
		}
		return usaConversor;
	}

}
