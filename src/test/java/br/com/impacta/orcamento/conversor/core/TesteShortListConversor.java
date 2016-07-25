package br.com.impacta.orcamento.conversor.core;

import java.util.List;

public class TesteShortListConversor implements Conversor<List<Short>, String> {

	@Override
	public String converter(List<Short> entrada) {
		return entrada.get(0).toString();
	}


}
