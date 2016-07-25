package br.com.impacta.orcamento.conversor.common;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

public class CondicionaisConversores {

	private CondicionaisConversores() {
		//Privado
	}
	
	public static Conversor<Character, Boolean> characterSOuNParaBoolean(){
		return CharacterSOuNParaBooleanConversor.intancia();
	}
	
	public static Conversor<String, Boolean> stringSOuNParaBoolean(){
		return StringSOuNParaBooleanConversor.intancia();
	}
	
	public static Conversor<Boolean, String> booleanParaSOuNString(){
		return BooleanParaStringSOuNConversor.intancia();
	}
	
	public static Conversor<String, String> stringSouNParaStringZeroOuUm(){
		return ConversorBuilder.composite(stringSOuNParaBoolean(), booleanParaIntegerZeroOuUm(), NumerosConversores.integerParaString()).build();
	}
	
	public static Conversor<String, Integer> stringSouNParaIntegerZeroOuUm(){
		return ConversorBuilder.composite(stringSOuNParaBoolean(), booleanParaIntegerZeroOuUm()).build();
	}
	
	public static Conversor<Integer, String> IntegerZeroOuUmParaStringSouN(){
		return ConversorBuilder.composite(integerZeroOuUmParaBoolean(), booleanParaSOuNString()).build();
	}
	
	public static Conversor<Boolean, Integer> booleanParaIntegerZeroOuUm(){
		return BooleanParaIntegerZeroOuUmConversor.intancia();
	}
	
	public static Conversor<Boolean, String> booleanParaStringZeroOuUm(){
		return BooleanParaStringZeroOuUmConversor.intancia();
	}

	public static Conversor<Integer, Boolean> integerZeroOuUmParaBoolean(){
		return IntegerZeroOuUmParaBooleanConversor.intancia();
	}
}
