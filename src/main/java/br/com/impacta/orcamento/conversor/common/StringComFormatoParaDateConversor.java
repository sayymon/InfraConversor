package br.com.impacta.orcamento.conversor.common;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

import com.google.common.base.Preconditions;
import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;
import br.com.impacta.orcamento.conversor.exceptions.ConversaoDataException;

class StringComFormatoParaDateConversor  implements Conversor<StringComFormato, Date> {
	
	private final static Conversor<StringComFormato, Date> INSTANCIA = 
			ConversorBuilder.para(new StringComFormatoParaDateConversor())
			.comPreConversaoPadrao().build();
	
	private StringComFormatoParaDateConversor() {}
	
	static Conversor<StringComFormato, Date> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public Date converter(StringComFormato entrada) {
		Preconditions.checkNotNull(entrada.getFormato());
		if(StringUtils.isBlank(entrada.getTextoData())) {
			return null;
		}
		try{
			return LocalDate.parse(entrada.getTextoData(), DateTimeFormat.forPattern(entrada.getFormato())).toDate();
		}catch(Exception e){
			throw new ConversaoDataException("Não foi possivel converter a data: "+entrada.getTextoData() +" com formato " +entrada.getFormato());
		}
	}

}
