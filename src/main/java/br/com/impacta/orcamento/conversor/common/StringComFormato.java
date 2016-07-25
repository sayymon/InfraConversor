package br.com.impacta.orcamento.conversor.common;


/**
 * Entrada esperada para na conversão de datas
 * 
 * @see DatasConversores#stringComFormatoParaLocalDate()
 */
public class StringComFormato {

	private final String textoData;
	
	private final String formato;
	
	private static final String FORMATO_DDMMYYYY = "dd/MM/yyyy";
	private static final String FORMATO_YYYYMMDD = "yyyyMMdd";
	
	/*
	 * Construtor privado
	 */
	private StringComFormato(String textoData, String formato) {
		this.textoData = textoData;
		this.formato = formato;
	}
	
	/** 
	 * Método de construção padrão da {@code StringComFormato}
	 * @param textoData Define o atributo textoData
	 * @param formato Define o atributo formato
	 * @return O {@code StringComFormato}
	 */
	public static StringComFormato de(String textoData, String formato) {
		return new StringComFormato(textoData, formato);
	} 
	
	/** 
	 * Método de construção da {@code LocalDateComFormato} com formato "dd/MM/yyyy"
	 * @param textoData Define o atributo textoData
	 * @return O {@code LocalDateComFormato}
	 */
	public static StringComFormato comFormatoDDMMYYYY(final String textoData) {
		return new StringComFormato(textoData, FORMATO_DDMMYYYY);
	} 
	
	/** 
	 * Método de construção da {@code LocalDateComFormato} com formato "yyyyMMdd"
	 * @param textoData Define o atributo textoData
	 * @return O {@code LocalDateComFormato}
	 */
	public static StringComFormato comFormatoYYYYMMDD(final String textoData) {
		return new StringComFormato(textoData, FORMATO_YYYYMMDD);
	} 

	/**
	 * @return O atributo textoData
	 */
	public String getTextoData() {
		return textoData;
	}

	/**
	 * @return O atributo formato
	 */
	public String getFormato() {
		return formato;
	}

}
