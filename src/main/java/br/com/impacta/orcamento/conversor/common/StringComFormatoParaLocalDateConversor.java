package br.com.impacta.orcamento.conversor.common;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

import com.google.common.base.Preconditions;
import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class StringComFormatoParaLocalDateConversor  implements Conversor<StringComFormato, LocalDate> {
	
	private final static Conversor<StringComFormato, LocalDate> INSTANCIA = 
			ConversorBuilder.para(new StringComFormatoParaLocalDateConversor())
			.comPreConversaoPadrao().build();
	
	private StringComFormatoParaLocalDateConversor() {}
	
	static Conversor<StringComFormato, LocalDate> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public LocalDate converter(StringComFormato entrada) {
		Preconditions.checkNotNull(entrada.getFormato());
		if(StringUtils.isBlank(entrada.getTextoData())) {
			return null;
		}
		return LocalDate.parse(entrada.getTextoData(), DateTimeFormat.forPattern(entrada.getFormato()));
	}

}
