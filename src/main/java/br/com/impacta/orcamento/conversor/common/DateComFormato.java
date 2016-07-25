package br.com.impacta.orcamento.conversor.common;

import java.util.Date;

/**
 * Entrada esperada para na conversão de datas
 * 
 * @see DatasConversores#dateComFormatoParaString()
 */
public class DateComFormato {

	private final Date date;
	private final String formato;
	
	private static final String FORMATO_DDMMYYYY = "dd/MM/yyyy";
	private static final String FORMATO_YYYYMMDD = "yyyyMMdd";
	
	/** 
	 * Método de construção padrão da {@code DateComFormato}
	 * @param textoData Define o atributo textoData
	 * @param formato Define o atributo formato
	 * @return O {@code DateComFormato}
	 */
	public static DateComFormato de(final Date date, final String formato) {
		return new DateComFormato(date, formato);
	} 
	
	/** 
	 * Método de construção da {@code DateComFormato} com formato "dd/MM/yyyy"
	 * @param textoData Define o atributo textoData
	 * @return O {@code DateComFormato}
	 */
	public static DateComFormato comFormatoDDMMYYYY(final Date date) {
		return new DateComFormato(date, FORMATO_DDMMYYYY);
	} 
	
	/** 
	 * Método de construção da {@code DateComFormato} com formato "yyyyMMdd"
	 * @param textoData Define o atributo textoData
	 * @return O {@code DateComFormato}
	 */
	public static DateComFormato comFormatoYYYYMMDD(final Date date) {
		return new DateComFormato(date, FORMATO_YYYYMMDD);
	} 
	
	/*
	 * Construtor privado
	 */
	private DateComFormato(final Date date, final String formato) {
		this.date = date;
		this.formato = formato;
	}
	
	/**
	 * @return O atributo date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @return O atributo formato
	 */
	public String getFormato() {
		return formato;
	}

}
