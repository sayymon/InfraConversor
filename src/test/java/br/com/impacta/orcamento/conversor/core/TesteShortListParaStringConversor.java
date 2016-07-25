package br.com.impacta.orcamento.conversor.core;

import java.util.Arrays;
import java.util.List;

import br.com.impacta.orcamento.conversor.core.Conversor;

public class TesteShortListParaStringConversor implements Conversor<String, List<Short>> {

	@Override
	public List<Short> converter(String entrada) {
		return Arrays.asList(Short.valueOf("4567"));
	}


}
