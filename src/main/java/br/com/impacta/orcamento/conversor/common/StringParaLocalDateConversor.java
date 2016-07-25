package br.com.impacta.orcamento.conversor.common;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class StringParaLocalDateConversor implements Conversor<String, LocalDate> {
	
	private final static DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormat.forPattern("dd/MM/yyyy");

	private final static Conversor<String, LocalDate> INSTANCIA = 
			ConversorBuilder.para(new StringParaLocalDateConversor())
			.comPreConversaoPadrao().build();
	
	private StringParaLocalDateConversor() {}
	
	static Conversor<String, LocalDate> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public LocalDate converter(String entrada) {
		if(entrada.isEmpty()) {
			return null;
		}
		return LocalDate.parse(entrada, DATE_TIME_FORMAT);
	}

}
