package br.com.impacta.orcamento.conversor.common;

import java.util.Date;

import org.joda.time.LocalDate;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class DateParaLocalDateConversor implements Conversor<Date, LocalDate> {

	private final static Conversor<Date, LocalDate> INSTANCIA = 
			ConversorBuilder.para(new DateParaLocalDateConversor())
			.comPreConversaoPadrao().build();
	private DateParaLocalDateConversor() {}
	
	static Conversor<Date, LocalDate> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public LocalDate converter(Date entrada) {
		return LocalDate.fromDateFields(entrada);
	}

}
