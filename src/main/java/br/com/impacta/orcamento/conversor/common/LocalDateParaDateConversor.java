package br.com.impacta.orcamento.conversor.common;

import java.util.Date;

import org.joda.time.LocalDate;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;
import br.com.impacta.orcamento.conversor.exceptions.ConversaoDataException;

class LocalDateParaDateConversor implements Conversor<LocalDate, Date> {
	
	private static final String  PATTERN = "dd/MM/yyyy";

	private final static Conversor<LocalDate, Date> INSTANCIA = 
			ConversorBuilder.para(new LocalDateParaDateConversor())
			.comPreConversaoPadrao().build();
	
	private LocalDateParaDateConversor() {}
	
	static Conversor<LocalDate, Date> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public Date converter(LocalDate entrada) {
		try{
			return entrada.toDateTimeAtStartOfDay().toDate();
		}catch(final Exception e){
			throw new ConversaoDataException("Não foi possivel converter a data: "+entrada.toString(PATTERN));
		}
	}

}
