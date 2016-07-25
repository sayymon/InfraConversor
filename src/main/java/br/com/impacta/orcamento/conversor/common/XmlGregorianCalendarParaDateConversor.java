package br.com.impacta.orcamento.conversor.common;

import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;
import br.com.impacta.orcamento.conversor.exceptions.ConversaoDataException;

class XmlGregorianCalendarParaDateConversor implements Conversor<XMLGregorianCalendar, Date> {
	

	private final static Conversor<XMLGregorianCalendar, Date> INSTANCIA = 
			ConversorBuilder.para(new XmlGregorianCalendarParaDateConversor())
			.comPreConversaoPadrao().build();
	
	private XmlGregorianCalendarParaDateConversor() {}
	
	static Conversor<XMLGregorianCalendar, Date> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public Date converter(XMLGregorianCalendar entrada) {
		try{
			return entrada.toGregorianCalendar().getTime();
		}catch(final Exception e){
			throw new ConversaoDataException("Não foi possivel converter a data: "+entrada.toXMLFormat());
		}
	}

}
