package br.com.impacta.orcamento.conversor.core;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

import com.google.common.base.Preconditions;

/** 
 * O Contexto de Conversão armazena informações referente a conversão corrente.
 * <br/><br/>
 * Quando uma conversão utiliza o Contexto de Conversão se torna possível acessar
 * todas as entradas que foram recebidas na árvore de conversão até o ponto onde a conversão esta.
 * <br/><br/>
 * Por exemplo, Se estiver sendo convertido um DocumentoSeguro em PropostaVO, quando estiver sendo convertido
 * o VeiculoVO, assumindo que a árvore de conversão do VeiculoVO é a abaixo: 
 * <pre>
 * ->PropostaConversor (O conversor recebe DocumentoSeguro)
 *     ->ItemConversor (O conversor recebe ItemAutomovel)
 *          ->VeiculoConversor (O conversor recebe Veiculo)
 * </pre>         
 * No conversor VeiculoConversor seria possível utilizando o Contexto de Conversão acessar o ItemAutomovel e o DocumentoSeguro, 
 * que são as entradas dos conversores antecessores ao VeiculoConversor.
 * <br/><br/>
 * Para acessar um parâmetros do Contexto de Conversão deve ser usado o método {@link ConversorContexto.obterParametro}.
 * <br/><br/>
 * @author Arthur Fernandes
 * @see ConversorLazyFactory
 * @see ConversorContextoProxy
 */
public class ConversorContexto {

	private static final ThreadLocal<ConversorContexto> CONTEXTO = new ThreadLocal<ConversorContexto>();
	
	private final Deque<ParametroContexto> params = new ArrayDeque<ParametroContexto>();
	
	private ConversorContexto() {
		// Construtor privado
	}
	
	/**
	 * Retorna a instancia corrente do Contexto de Conversão
	 * @return
	 */
	public static ConversorContexto getContexto() {
		ConversorContexto instanciaCorrente = CONTEXTO.get();
		if (instanciaCorrente == null) {
			throw new RuntimeException("Contexto não inicializado");
		}
		return instanciaCorrente;
	}

	/**
	 * Verica se existe um contexto aberto
	 * @return Se existir um contexto aberto
	 */
	static boolean existeContexto() {
		return CONTEXTO.get() != null;
	}

	/**
	 * Abre o contexto
	 * @return A instância do contexto criado
	 */
	static ConversorContexto abrirContexto() {
		ConversorContexto instancia = new ConversorContexto();
		if (CONTEXTO.get() != null) {
			throw new RuntimeException("Contexto duplicado");
		}
		CONTEXTO.set(instancia);
		return instancia;
	}

	/**
	 * Fecha o contexto aberto
	 */
	static void fecharContexto() {
		CONTEXTO.remove();
	}
	
	/**
	 * Inclui um parametro no Contexto de Conversão
	 * @param parametroContexto O valor a ser incluido
	 */
	static void incluirParametro(ParametroContexto parametroContexto) {
		Preconditions.checkNotNull(parametroContexto);
		Preconditions.checkNotNull(parametroContexto.getTipoParametrizado());
		getContexto().getParams().add(parametroContexto);
	}
	
	/**
	 * Remove um parametro do Contexto de Conversão
	 * @param key O {@link Class} do parametro
	 */
	static <T> void removerUltimoParametro() {
		getContexto().getParams().pollLast();
	}
	
	/**
	 * Retorna o parametro existente no Contexto de Conversão pelo tipo da classe.
	 * 
	 * Se quiser acessar o ItemAutomovel disponível no Contexto de Conversão, basta passar o class
	 * do ItemAutovel para retornar o ItemAutomovel que esta disponível no Contexto de Conversão. 
	 * @param key O {@link Class} do parametro requisitado.
	 * @return O parametro requisitado.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T obterParametro(TipoParametrizado<T, ?> key) {
		return (T) getContexto().obterParametroContexto(key).getObject();
	}
	
	/**
	 * Retorna o parametro existente no Contexto de Conversão pelo tipo da classe.
	 * 
	 * Se quiser acessar o ItemAutomovel disponível no Contexto de Conversão, basta passar o class
	 * do ItemAutovel para retornar o ItemAutomovel que esta disponível no Contexto de Conversão. 
	 * @param key O {@link Class} do parametro requisitado.
	 * @return O parametro requisitado.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T ultimoParametro() {
		ParametroContexto value = getContexto().getParams().descendingIterator().next();
		Preconditions.checkNotNull(value, "Parametro não disponível no contexto");
		return (T) value.getObject();
	}
	
	/**
	 * Retorna o parametro existente no Contexto de Conversão pelo tipo da classe.
	 * 
	 * Se quiser acessar o ItemAutomovel disponível no Contexto de Conversão, basta passar o class
	 * do ItemAutovel para retornar o ItemAutomovel que esta disponível no Contexto de Conversão. 
	 * @param key O {@link Class} do parametro requisitado.
	 * @return O parametro requisitado.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T obterParametro(Class<T> key) {
		return (T) getContexto().obterParametroContexto(TipoParametrizado.de(key)).getObject();
	}
	

	/*
	 * Busca o {@Code ParametroContexto} de acordo com o tipo parametro informado
	 */
	private ParametroContexto obterParametroContexto(TipoParametrizado<?, ?> parametro){
		ParametroContexto value = null;
		Iterator<ParametroContexto> iterator = getParams().descendingIterator();
		
		while (iterator.hasNext()) {
			ParametroContexto parametroContexto = iterator.next();
			if (parametroContexto.getTipoParametrizado().isMesmoTipoAbsoluto(parametro)){
				value = parametroContexto;
				break;
			}
		}
		Preconditions.checkNotNull(value, "Parametro não disponível no contexto");
		return value;
	}
	
	
	/*
	 * Método get params
	 */
	private Deque<ParametroContexto> getParams() {
		return params;
	}
	
}