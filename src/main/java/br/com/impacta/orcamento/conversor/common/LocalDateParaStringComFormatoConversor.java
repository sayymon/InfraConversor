package br.com.impacta.orcamento.conversor.common;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class LocalDateParaStringComFormatoConversor implements Conversor<LocalDateComFormato, String> {
	
	private final static Conversor<LocalDateComFormato, String> INSTANCIA = 
			ConversorBuilder.para(new LocalDateParaStringComFormatoConversor())
			.comPreConversaoPadrao().build();
	
	private LocalDateParaStringComFormatoConversor() {}
	
	static Conversor<LocalDateComFormato, String> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public String converter(LocalDateComFormato entrada) {
		if(entrada.getLocalDate() == null || entrada.getFormato() == null) {
			return null;
		}
		return entrada.getLocalDate().toString(entrada.getFormato());
	}

}
