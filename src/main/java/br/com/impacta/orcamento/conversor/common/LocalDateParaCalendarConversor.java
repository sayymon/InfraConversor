package br.com.impacta.orcamento.conversor.common;

import java.util.Calendar;
import java.util.Locale;

import org.joda.time.LocalDate;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class LocalDateParaCalendarConversor implements Conversor<LocalDate, Calendar> {

	private final static Conversor<LocalDate, Calendar> INSTANCIA = 
			ConversorBuilder.para(new LocalDateParaCalendarConversor())
			.comPreConversaoPadrao().build();
	
	private LocalDateParaCalendarConversor() {}
	
	static Conversor<LocalDate, Calendar> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public Calendar converter(LocalDate entrada) {
		return entrada.toDateTimeAtStartOfDay().toCalendar(Locale.getDefault());
	}

}
