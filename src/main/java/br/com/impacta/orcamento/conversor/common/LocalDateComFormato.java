package br.com.impacta.orcamento.conversor.common;

import org.joda.time.LocalDate;

/**
 * Entrada esperada para na convers�o de datas
 * 
 * @see DatasConversores#localDateComFormatoParaString()
 */
public class LocalDateComFormato {

	private final LocalDate localDate;
	private final String formato;
	
	private static final String FORMATO_DDMMYYYY = "dd/MM/yyyy";
	private static final String FORMATO_YYYYMMDD = "yyyyMMdd";
	
	/** 
	 * M�todo de constru��o padr�o da {@code LocalDateComFormato}
	 * @param textoData Define o atributo textoData
	 * @param formato Define o atributo formato
	 * @return O {@code LocalDateComFormato}
	 */
	public static LocalDateComFormato de(final LocalDate localDate, final String formato) {
		return new LocalDateComFormato(localDate, formato);
	} 
	
	/** 
	 * M�todo de constru��o da {@code LocalDateComFormato} com formato "dd/MM/yyyy"
	 * @param textoData Define o atributo textoData
	 * @return O {@code LocalDateComFormato}
	 */
	public static LocalDateComFormato comFormatoDDMMYYYY(final LocalDate localDate) {
		return new LocalDateComFormato(localDate, FORMATO_DDMMYYYY);
	} 
	
	/** 
	 * M�todo de constru��o da {@code LocalDateComFormato} com formato "yyyyMMdd"
	 * @param textoData Define o atributo textoData
	 * @return O {@code LocalDateComFormato}
	 */
	public static LocalDateComFormato comFormatoYYYYMMDD(final LocalDate localDate) {
		return new LocalDateComFormato(localDate, FORMATO_YYYYMMDD);
	} 
	
	/*
	 * Construtor privado
	 */
	private LocalDateComFormato(final LocalDate localDate, final String formato) {
		this.localDate = localDate;
		this.formato = formato;
	}
	
	/**
	 * @return O atributo localDate
	 */
	public LocalDate getLocalDate() {
		return localDate;
	}

	/**
	 * @return O atributo formato
	 */
	public String getFormato() {
		return formato;
	}

}
