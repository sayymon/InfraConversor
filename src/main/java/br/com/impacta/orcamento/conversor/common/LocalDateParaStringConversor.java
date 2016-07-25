package br.com.impacta.orcamento.conversor.common;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class LocalDateParaStringConversor implements Conversor<LocalDate, String> {
	
	private final static DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormat.forPattern("dd/MM/yyyy");

	private final static Conversor<LocalDate, String> INSTANCIA = 
				ConversorBuilder.para(new LocalDateParaStringConversor())
				.comPreConversaoPadrao().build();
	
	private LocalDateParaStringConversor() {}
	
	static Conversor<LocalDate, String> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public String converter(LocalDate entrada) {
		return entrada.toString(DATE_TIME_FORMAT);
	}

}
