package br.com.impacta.orcamento.conversor.common;

import java.util.Calendar;

import org.joda.time.LocalDate;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class CalendarParaLocalDateConversor implements Conversor<Calendar, LocalDate> {

	private final static Conversor<Calendar, LocalDate> INSTANCIA = 
			ConversorBuilder.para(new CalendarParaLocalDateConversor())
			.comPreConversaoPadrao().build();
	
	private CalendarParaLocalDateConversor() {}
	
	static Conversor<Calendar, LocalDate> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public LocalDate converter(Calendar entrada) {
		return LocalDate.fromCalendarFields(entrada);
	}

}
