package br.com.impacta.orcamento.conversor.common;

import org.joda.time.LocalDate;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class DateParaStringComFormatoConversor implements Conversor<DateComFormato, String> {
	
	private final static Conversor<DateComFormato, String> INSTANCIA = 
			ConversorBuilder.para(new DateParaStringComFormatoConversor())
			.comPreConversaoPadrao().build();
	
	private DateParaStringComFormatoConversor() {}
	
	static Conversor<DateComFormato, String> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public String converter(DateComFormato entrada) {
		if(entrada.getDate() == null || entrada.getFormato() == null) {
			return null;
		}
		return LocalDate.fromDateFields(entrada.getDate()).toString(entrada.getFormato());
	}

}
