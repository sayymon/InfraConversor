package br.com.impacta.orcamento.conversor.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

public class StringParaIntegerConversor implements Conversor<String, Integer> {

	private final static Conversor<String, Integer> INSTANCIA = ConversorBuilder.para(new StringParaIntegerConversor())
	.comPreConversaoPadrao().build();
	
	private StringParaIntegerConversor() {}
	
	public static Conversor<String, Integer> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public Integer converter(String entrada) {
		
		Integer numeroInteiro = null;
		
		if (StringUtils.isNotBlank(entrada) && 
			NumberUtils.isNumber(entrada) &&
			Long.valueOf(entrada) <= Integer.MAX_VALUE){
			numeroInteiro = Integer.valueOf(entrada);
		}
		return numeroInteiro;
	}

}
