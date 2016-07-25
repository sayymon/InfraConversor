package br.com.impacta.orcamento.conversor.common;

import java.math.BigDecimal;
import java.math.BigInteger;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

public class NumerosConversores {

	private NumerosConversores() {
		//Privado
	}
	
	public static Conversor<Double, BigDecimal> doubleParaBigDecimal(){
		return DoubleParaBigDecimalConversor.intancia();
	}
	
	public static Conversor<BigDecimal, Double> bigDecimalParaDouble(){
		return BigDecimalParaDoubleConversor.intancia();
	}
	
	public static Conversor<BigDecimal, Double> bigDecimalParaValorMonetarioDouble(){
		return ConversorBuilder.composite(BigDecimalParaBigDecimalMonetarioConversor.intancia(), BigDecimalParaDoubleConversor.intancia()).build();
	}
	
	public static Conversor<BigDecimal, BigDecimal> bigDecimalParaBigDecimalMonetario(){
		return BigDecimalParaBigDecimalMonetarioConversor.intancia();
	}
	
	public static Conversor<BigDecimal, String> bigDecimalParaString(){
		return BigDecimalParaStringConversor.intancia();
	}
	
	public static Conversor<Float, BigDecimal> floatParaBigDecimal(){
		return FloatParaBigDecimalConversor.intancia();
	}
	
	public static Conversor<BigDecimal, Float> bigDecimalParaFloat(){
		return BigDecimalParaFloatConversor.intancia();
	}
	
	public static Conversor<String, Integer> stringParaInteger(){
		return StringParaIntegerConversor.intancia();
	}
	
	public static Conversor<String, Short> stringParaShort(){
		return StringParaShortConversor.intancia();
	}
	
	public static Conversor<Integer, String> integerParaString(){
		return IntegerParaStringConversor.intancia();
	}
	
	public static Conversor<String, Long> stringParaLong(){
		return StringParaLongConversor.intancia();
	}
	
	public static Conversor<Long, String> longParaString(){
		return LongParaStringConversor.intancia();
	}
	
	public static Conversor<String, Double> stringParaDouble(){
		return StringParaDoubleConversor.intancia();
	}
	
	public static Conversor<Double, String> doubleParaString(){
		return DoubleParaStringConversor.intancia();
	}
	
	public static Conversor<Long, Integer> longParaInteger(){
		return LongParaIntegerConversor.intancia();
	}
	
	public static Conversor<Integer, Long> integerParaLong(){
		return IntegerParaLongConversor.intancia();
	}
	
	public static Conversor<Double, Integer> doubleParaInteger(){
		return DoubleParaIntegerConversor.intancia();
	}
	
	public static Conversor<Integer, Double> integerParaDouble(){
		return IntegerParaDoubleConversor.intancia();
	}
	
	public static Conversor<BigDecimal, Integer> bigDecimalParaInteger(){
		return BigDecimalParaIntegerConversor.intancia();
	}
	
	public static Conversor<BigDecimal, Long> bigDecimalParaLong(){
		return BigDecimalParaLongConversor.intancia();
	}
	
	public static Conversor<Integer, BigDecimal> integerParaBigDecimal(){
		return IntegerParaBigDecimalConversor.intancia();
	}
	
	public static Conversor<String, BigDecimal> stringParaBigDecimal(){
		return StringParaBigDecimalConversor.intancia();
	}
	
	public static Conversor<String, BigInteger> stringParaBigInteger(){
		return StringParaBigIntegerConversor.intancia();
	}
	
	public static Conversor<Short, Integer> shortParaInteger() {
		return ShortParaIntegerConversor.intancia();
	}
	
	public static Conversor<Short, String> shortParaString() {
		return ShortParaStringConversor.intancia();
	}
	
	public static Conversor<Integer, Short> integerParaShort() {
		return IntegerParaShortConversor.intancia();
	}

	public static Conversor<Integer, BigInteger> integerParaBigInteger() {
		return IntegerParaBigIntegerConversor.intancia();
	}
	
	public static Conversor<Integer, Byte> integerParaByte() {
		return IntegerParaByteConversor.intancia();
	}
	
	
	public static Conversor<Long, BigInteger> longParaBigInteger() {
		return LongParaBigIntegerConversor.intancia();
	}
	
	public static Conversor<Long, BigDecimal> longParaBigDecimal(){
		return LongParaBigDecimalConversor.intancia();
	}

	public static Conversor<Float, Integer> floatParaInteger() {
		return FloatParaIntegerConversor.intancia();
	}

	public static Conversor<Long, Double> longParaDouble() {
		return LongParaDoubleConversor.intancia();
	}
}
