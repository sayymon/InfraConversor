package br.com.impacta.orcamento.conversor.core;

import java.util.Arrays;
import java.util.List;

import br.com.impacta.orcamento.conversor.core.Conversor;

public class TesteStringListConversor implements Conversor<String, List<String>> {

	@Override
	public List<String> converter(String entrada) {
		return Arrays.asList("9876");
	}


}
