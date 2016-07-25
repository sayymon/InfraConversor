package br.com.impacta.orcamento.conversor.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

import br.com.impacta.orcamento.conversor.core.ConversorBuilder;
import br.com.impacta.orcamento.conversor.core.ConversorComTipo;
import br.com.impacta.orcamento.conversor.core.ConversorContexto;
import br.com.impacta.orcamento.conversor.core.ConversorFactory;
import br.com.impacta.orcamento.conversor.core.ConversorFactoryGerenciado;
import br.com.impacta.orcamento.conversor.core.ConversorFactoryGerenciadoContexto;
import br.com.impacta.orcamento.conversor.core.ConversorFactoryGerenciadoContextoBuilder;
import br.com.impacta.orcamento.conversor.core.PreConversoes;
import br.com.impacta.orcamento.conversor.core.TipoParametrizado;

public class ConversorContextoFactoryGeralTest {

	private ConversorFactory gerenciado;
	
	@Before
	public void before() {
		gerenciado = ConversorFactoryGerenciado.instancia(new ConversorFactoryGerenciadoContexto() {
			@Override
			public Collection<ConversorComTipo> inicializarContexto() {
				return ConversorFactoryGerenciadoContextoBuilder.instancia()
						.com(new TesteShortListParaStringConversor())
						.com(new TesteShortListConversor())
						.com(new TesteStringListConversor())
						.com(new TesteShortConversor())
						.com(new TesteArrayConversor(), PreConversoes.<Short[]>sempreConverte())
						.com(new TesteArray2Conversor(), new Condicao() {
							@Override
							public boolean seAplica() {
								return "786".equals(ConversorContexto.obterParametro(String.class));
							} 
						})
						.com(new TesteArrayStringConversor())
						.com(new TesteArrayString2Conversor())
						.com(new TesteArrayApoliceConversor())
						.com(new TesteArrayOrcamentoConversor())
						.com(new ApoliceParaOrcamentoConversor(), PreConversoes.diferenteDeNullComValorPadrao(new ValorPadraoPreConversao<Orcamento>() {
							@Override
							public Orcamento valorPadrao() {
								return new Orcamento(99);
							}
						}))
						.build();
			}
		});
	}
	
	@Test
	public void conversorStringParaArrayComNullPreConversaoPadrao() {
		ConversorFactory conversorFactory = ConversorFactoryGerenciado.instancia(new ConversorFactoryGerenciadoContexto() {
			@Override
			public Collection<ConversorComTipo> inicializarContexto() {
				return ConversorFactoryGerenciadoContextoBuilder.instancia()
						.com(new TesteShortConversor())
						.com(new TesteArray2Conversor())
						.build();
			}
		});
		
		Short[] retorno1 = conversorFactory.construirConversor(String.class, Short[].class).converter(null);
		assertNotNull(retorno1);
		assertEquals(retorno1.length, 0);
		
		Short[] retorno2 = conversorFactory
				.construirConversor(TipoParametrizado.collection(String.class), TipoParametrizado.array(Short.class))
				.converter(null);
		assertNotNull(retorno2);
		assertEquals(retorno2.length, 0);
		
		Short[] retorno3 = conversorFactory
				.construirConversor(TipoParametrizado.array(String.class), TipoParametrizado.array(Short.class))
				.converter(null);
		assertNotNull(retorno3);
		assertEquals(retorno3.length, 0);
		
	}
	
	@Test
	public void conversorSimplesUmParaUm() {
		Conversor<Apolice, Orcamento> apoliceParaOrcamento = gerenciado.construirConversor(Apolice.class, Orcamento.class);
		
		Orcamento orcamento = apoliceParaOrcamento.converter(new Apolice(1));
		assertEquals(orcamento.getCodigo(), Integer.valueOf(1));
		
		orcamento = apoliceParaOrcamento.converter(null);
		assertEquals(orcamento.getCodigo(), Integer.valueOf(99));
		
		Conversor<List<Apolice>, List<Orcamento>> conversorLista = gerenciado.construirConversor(TipoParametrizado.list(Apolice.class), TipoParametrizado.list(Orcamento.class));
		List<Orcamento> orcamentos = conversorLista.converter(Arrays.asList(new Apolice(12), new Apolice(34), null, new Apolice(45))); 
		assertEquals(orcamentos.get(0).getCodigo(), Integer.valueOf(12));
		assertEquals(orcamentos.get(1).getCodigo(), Integer.valueOf(34));
		assertEquals(orcamentos.get(2).getCodigo(), Integer.valueOf(99));
		// Testa size igual a 3, pois o conversor devolve null para apolice 45, entao nao deveria ser incluido na lista retornada pelo conversor de list da infra
		assertEquals(orcamentos.size(), 3);
	}
	
	@Test
	public void conversorListParaListCriadaAutomatica() {
		Conversor<List<Apolice>, List<Orcamento>> apoliceParaOrcamentoList = gerenciado.construirConversor(TipoParametrizado.list(Apolice.class), TipoParametrizado.list(Orcamento.class));
		
		List<Orcamento> orcamentoList = apoliceParaOrcamentoList.converter(Lists.newArrayList(new Apolice(2), new Apolice(3)));
		assertEquals(orcamentoList.get(0).getCodigo(), Integer.valueOf(2));
		assertEquals(orcamentoList.get(1).getCodigo(), Integer.valueOf(3));
		
	}
	
	@Test
	public void conversorListShortParaStringSimplesTestandoRegraPreConversaoParaEmptyLists() {
		Conversor<List<Short>, String> conversorShortListParaString = gerenciado.construirConversor(TipoParametrizado.list(Short.class), TipoParametrizado.de(String.class));
		
		String retorno0 = conversorShortListParaString.converter(Collections.<Short>emptyList());
		assertEquals(retorno0, null);
		
		retorno0 = conversorShortListParaString.converter(Arrays.asList(Short.valueOf("1123")));
		assertEquals(retorno0, "1123");
		
	}
	
	@Test
	public void conversorListShortParaStringSimplesTestandoRegraPreConversaoNullRetornandoCollection() {
		Conversor<String, List<Short>> conversorShortList = gerenciado.construirConversor(TipoParametrizado.de(String.class), TipoParametrizado.list(Short.class));
		
		List<Short> retorno = conversorShortList.converter("");
		assertEquals(retorno.get(0), Short.valueOf("4567"));
		
		retorno = conversorShortList.converter(null);
		assertEquals(retorno.size(), 0);
		
	}
	
	@Test
	public void conversorListStringParaStringSimplesTestandoRegraPreConversaoNullRetornandoCollection() {
		Conversor<String, List<String>> conversorStringList = gerenciado.construirConversor(TipoParametrizado.de(String.class), TipoParametrizado.list(String.class));
		
		List<String> retorno2 = conversorStringList.converter("11");
		assertEquals(retorno2.get(0), "9876");
		
		retorno2 = conversorStringList.converter(null);
		assertEquals(retorno2.size(), 0);
		
	}
	
	@Test
	public void conversorListStringParaStringSimplesTestandoRegraPreConversaoNullRetornandoCollectionMutavel() {
		Conversor<String, List<String>> conversorStringList = gerenciado.construirConversor(TipoParametrizado.de(String.class), TipoParametrizado.list(String.class));
		
		List<String> retorno2 = conversorStringList.converter(null);
		assertEquals(retorno2.size(), 0);
		retorno2.add("TESTE");
		
		retorno2 = conversorStringList.converter(null);
		assertEquals(retorno2.size(), 0);
	}
	
	@Test
	public void conversorStringParaShortSimplesTestandoRegraPreConversaoNullRetornandoNull() {
		Conversor<String, Short> conversorShort = gerenciado.construirConversor(String.class, Short.class);
		
		assertEquals(conversorShort.converter("1234"), Short.valueOf("1234"));
		assertEquals(conversorShort.converter(null), null);
		
	}
	
	@Test
	public void conversorStringParaArrayShortTestandoRegraPreConversaoNullConvertendo() {
		Conversor<String, Short[]> conversorShortArray = gerenciado.construirConversor(String.class, Short[].class);
		
		Short[] retorno3 = conversorShortArray.converter("852");
		assertEquals(retorno3[0], Short.valueOf("8520"));
		
		retorno3 = conversorShortArray.converter(null);
		assertEquals(retorno3[0], Short.valueOf("8520"));
		
		retorno3 = conversorShortArray.converter("786");
		assertEquals(retorno3[0], Short.valueOf("9999"));
		
	}
	
	@Test
	public void conversorComposite() {
		
		Conversor<Short, Integer> conversorA = new Conversor<Short, Integer>() {
			@Override
			public Integer converter(Short entrada) {
				return entrada.intValue();
			}
		};
		
		Conversor<Integer, Long> conversorB = new Conversor<Integer, Long>() {
			@Override
			public Long converter(Integer entrada) {
				return entrada.longValue();
			}
		};
		
		Conversor<Long, BigInteger> conversorC = new Conversor<Long, BigInteger>() {
			@Override
			public BigInteger converter(Long entrada) {
				return BigInteger.valueOf(entrada);
			}
		};
		
		Conversor<Short, BigInteger> conversorComposite = ConversorBuilder.composite(conversorA, conversorB, conversorC).build();
		
		BigInteger retorno = conversorComposite.converter(Short.valueOf("123"));
		assertEquals(retorno, new BigInteger("123"));
		
	}
	
	@Test
	public void testarGerais() {
		
		Conversor<String, String[]> conversorStringArray = gerenciado.construirConversor(String.class, String[].class);
		Conversor<Long[], String[]> conversorStringLongArray = gerenciado.construirConversor(Long[].class, String[].class);
		Conversor<String, Apolice[]> conversorStringApoliceArray = gerenciado.construirConversor(String.class, Apolice[].class);
		Conversor<String, Orcamento[]> conversorStringOrcamentoArray = gerenciado.construirConversor(String.class, Orcamento[].class);
		Conversor<List<String>, List<Short>> conversorShortCollection = gerenciado.construirConversor(TipoParametrizado.list(String.class), TipoParametrizado.list(Short.class));
		Conversor<Collection<String>, Set<Short>> conversorShortSet = gerenciado.construirConversor(TipoParametrizado.collection(String.class), TipoParametrizado.set(Short.class));
		Conversor<Collection<String>, List<Short>> conversorShortList2 = gerenciado.construirConversor(TipoParametrizado.collection(String.class), TipoParametrizado.list(Short.class));
		Conversor<Collection<String>, Short[]> conversorShortArray2 = gerenciado.construirConversor(TipoParametrizado.collection(String.class), TipoParametrizado.array(Short.class));
		
		String[] retorno4 = conversorStringArray.converter("");
		assertEquals(retorno4[0], "7410");
		
		String[] retorno5 = conversorStringLongArray.converter(new Long[]{(long)0});
		assertEquals(retorno5[0], "9630");
		
		Apolice[] retorno6 = conversorStringApoliceArray.converter("");
		assertEquals(retorno6[0].getCodigo(), Integer.valueOf(1));
		
		Orcamento[] retorno7 = conversorStringOrcamentoArray.converter("");
		assertEquals(retorno7[0].getCodigo(), Integer.valueOf(1));
		
		Collection<Short> retorno8 = conversorShortCollection.converter(Arrays.asList("1", "2"));
		Iterator<Short> iterator = retorno8.iterator();
		assertEquals(iterator.next(), Short.valueOf("1"));
		assertEquals(iterator.next(), Short.valueOf("2"));
		
		Set<Short> retorno9 = conversorShortSet.converter(Arrays.asList("999", null, "998"));
		iterator = retorno9.iterator();
		assertEquals(iterator.next(), Short.valueOf("999"));
		assertEquals(iterator.next(), Short.valueOf("998"));
		
		List<Short> retorno10 = conversorShortList2.converter(Arrays.asList("1", "2"));
		iterator = retorno10.iterator();
		assertEquals(iterator.next(), Short.valueOf("1"));
		assertEquals(iterator.next(), Short.valueOf("2"));
		
		Short[] retorno11 = conversorShortArray2.converter(Arrays.asList("1", "2"));
		assertEquals(retorno11[0], Short.valueOf("1"));
		assertEquals(retorno11[1], Short.valueOf("2"));
		
		Collection<Short> retorno12 = conversorShortCollection.converter(null);
		assertEquals(retorno12.size(), 0);
		
	}
	
}
