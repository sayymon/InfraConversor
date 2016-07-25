package br.com.impacta.orcamento.conversor.core;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import br.com.impacta.orcamento.conversor.core.Conversor;
import br.com.impacta.orcamento.conversor.core.ConversorContexto;
import br.com.impacta.orcamento.conversor.core.ConversorContextoProxy;
import br.com.impacta.orcamento.conversor.core.TipoParametrizado;


@RunWith(MockitoJUnitRunner.class)
public class ConversorContextoProxyTest {
	
	@SuppressWarnings("unchecked")
	private Conversor<String, String> conversor = Mockito.mock(Conversor.class);
	
	
	private Conversor<String, String> conversorProxy;
	
	@Before
	public void before() {
		conversorProxy = ConversorContextoProxy.obterProxy(TipoParametrizado.de(String.class), conversor);
	}
	
	@Test
	public void testarInclusaoDeParametros() {
		final String entradaConversor = "teste";
		
		when(conversor.converter(any(String.class))).then(new Answer<String>() {
			@Override
			public String answer(InvocationOnMock invocation) throws Throwable {
				assertTrue(ConversorContexto.existeContexto());
				assertTrue(ConversorContexto.ultimoParametro() != null);
				assertEquals(entradaConversor, ConversorContexto.ultimoParametro());
				assertEquals(entradaConversor, ConversorContexto.obterParametro(String.class));
				return "OK";
			}
			
		});
		
		conversorProxy.converter(entradaConversor);
	}
	
	@Test
	public void testarChamadaDoConverterFilho() {
		String ok = "OK";
		final String entradaConversor = "teste";
		
		when(conversor.converter(entradaConversor)).thenReturn(ok);
		String retorno = conversorProxy.converter(entradaConversor);
		Mockito.verify(conversor, VerificationModeFactory.times(1)).converter(entradaConversor);
		assertEquals(retorno, ok);
	}
	
}