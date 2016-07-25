package br.com.impacta.orcamento.conversor.common;

import java.math.BigDecimal;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorBuilder;

class PercentualParaCoeficienteConversor implements Conversor<BigDecimal, BigDecimal> {

	private final static Conversor<BigDecimal, BigDecimal> INSTANCIA = 
				ConversorBuilder.para(new PercentualParaCoeficienteConversor())
				.comPreConversaoPadrao().build();
	
	private static final BigDecimal PORCENTAGEM_100 = BigDecimal.valueOf(100);
	
	private PercentualParaCoeficienteConversor() {}
	
	static Conversor<BigDecimal, BigDecimal> intancia() {
		return INSTANCIA;
	}
	
	@Override
	public BigDecimal converter(BigDecimal entrada) {
		return BigDecimal.ONE.subtract(entrada.divide(PORCENTAGEM_100));
	}
	
}
