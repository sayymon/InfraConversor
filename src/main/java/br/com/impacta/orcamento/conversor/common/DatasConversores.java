package br.com.impacta.orcamento.conversor.common;

import java.util.Calendar;
import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import br.com.impacta.orcamento.conversor.core.Conversor;

public class DatasConversores {

	private DatasConversores() {
		//Privado
	}
	
	public static Conversor<Calendar, LocalDate> calendarParaLocalDate(){
		return CalendarParaLocalDateConversor.intancia();
	}
	
	public static Conversor<LocalDate, Calendar> localDateParaCalendar(){
		return LocalDateParaCalendarConversor.intancia();
	}
	
	public static Conversor<Date, LocalDate> dateParaLocalDate(){
		return DateParaLocalDateConversor.intancia();
	}
	
	public static Conversor<Date, LocalDateTime> dateParaLocalDateTime(){
		return DateParaLocalDateTimeConversor.intancia();
	}	
	
	public static Conversor<LocalDate, Date> localDateParaDate(){
		return LocalDateParaDateConversor.intancia();
	}
	
	@Deprecated
	public static Conversor<String, LocalDate> stringDDMMYYYYParaLocalDate(){
		return StringParaLocalDateConversor.intancia();
	}
	
	public static Conversor<StringComFormato, LocalDate> stringComFormatoParaLocalDate() {
		return StringComFormatoParaLocalDateConversor.intancia();
	}
	
	public static Conversor<StringComFormato, Date> stringComFormatoParaDate() {
		return StringComFormatoParaDateConversor.intancia();
	}
	
	public static Conversor<LocalDateComFormato, String> localDateComFormatoParaString() {
		return LocalDateParaStringComFormatoConversor.intancia();
	}
	
	public static Conversor<DateComFormato, String> dateComFormatoParaString() {
		return DateParaStringComFormatoConversor.intancia();
	} 
	
	public static Conversor<XMLGregorianCalendar, Date> xmlGregorianCalendarParaDate(){
		return XmlGregorianCalendarParaDateConversor.intancia();
	}
}
