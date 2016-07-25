package br.com.impacta.orcamento.conversor.common;

import java.util.Date;

import org.joda.time.LocalDateTime;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class DateParaLocalDateTimeConversor implements Conversor<Date, LocalDateTime> {

	private final static Conversor<Date, LocalDateTime> INSTANCIA = 
			ConversorBuilder.para(new DateParaLocalDateTimeConversor())
			.comPreConversaoPadrao().build();
	private DateParaLocalDateTimeConversor() {}
	
	static Conversor<Date, LocalDateTime> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public LocalDateTime converter(Date entrada) {
		return LocalDateTime.fromDateFields(entrada);
	}

}
