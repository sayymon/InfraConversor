package br.com.impacta.orcamento.conversor.core;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

import com.google.common.base.Preconditions;

/** 
 * O Contexto de Convers�o armazena informa��es referente a convers�o corrente.
 * <br/><br/>
 * Quando uma convers�o utiliza o Contexto de Convers�o se torna poss�vel acessar
 * todas as entradas que foram recebidas na �rvore de convers�o at� o ponto onde a convers�o esta.
 * <br/><br/>
 * Por exemplo, Se estiver sendo convertido um DocumentoSeguro em PropostaVO, quando estiver sendo convertido
 * o VeiculoVO, assumindo que a �rvore de convers�o do VeiculoVO � a abaixo: 
 * <pre>
 * ->PropostaConversor (O conversor recebe DocumentoSeguro)
 *     ->ItemConversor (O conversor recebe ItemAutomovel)
 *          ->VeiculoConversor (O conversor recebe Veiculo)
 * </pre>         
 * No conversor VeiculoConversor seria poss�vel utilizando o Contexto de Convers�o acessar o ItemAutomovel e o DocumentoSeguro, 
 * que s�o as entradas dos conversores antecessores ao VeiculoConversor.
 * <br/><br/>
 * Para acessar um par�metros do Contexto de Convers�o deve ser usado o m�todo {@link ConversorContexto.obterParametro}.
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
	 * Retorna a instancia corrente do Contexto de Convers�o
	 * @return
	 */
	public static ConversorContexto getContexto() {
		ConversorContexto instanciaCorrente = CONTEXTO.get();
		if (instanciaCorrente == null) {
			throw new RuntimeException("Contexto n�o inicializado");
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
	 * @return A inst�ncia do contexto criado
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
	 * Inclui um parametro no Contexto de Convers�o
	 * @param parametroContexto O valor a ser incluido
	 */
	static void incluirParametro(ParametroContexto parametroContexto) {
		Preconditions.checkNotNull(parametroContexto);
		Preconditions.checkNotNull(parametroContexto.getTipoParametrizado());
		getContexto().getParams().add(parametroContexto);
	}
	
	/**
	 * Remove um parametro do Contexto de Convers�o
	 * @param key O {@link Class} do parametro
	 */
	static <T> void removerUltimoParametro() {
		getContexto().getParams().pollLast();
	}
	
	/**
	 * Retorna o parametro existente no Contexto de Convers�o pelo tipo da classe.
	 * 
	 * Se quiser acessar o ItemAutomovel dispon�vel no Contexto de Convers�o, basta passar o class
	 * do ItemAutovel para retornar o ItemAutomovel que esta dispon�vel no Contexto de Convers�o. 
	 * @param key O {@link Class} do parametro requisitado.
	 * @return O parametro requisitado.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T obterParametro(TipoParametrizado<T, ?> key) {
		return (T) getContexto().obterParametroContexto(key).getObject();
	}
	
	/**
	 * Retorna o parametro existente no Contexto de Convers�o pelo tipo da classe.
	 * 
	 * Se quiser acessar o ItemAutomovel dispon�vel no Contexto de Convers�o, basta passar o class
	 * do ItemAutovel para retornar o ItemAutomovel que esta dispon�vel no Contexto de Convers�o. 
	 * @param key O {@link Class} do parametro requisitado.
	 * @return O parametro requisitado.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T ultimoParametro() {
		ParametroContexto value = getContexto().getParams().descendingIterator().next();
		Preconditions.checkNotNull(value, "Parametro n�o dispon�vel no contexto");
		return (T) value.getObject();
	}
	
	/**
	 * Retorna o parametro existente no Contexto de Convers�o pelo tipo da classe.
	 * 
	 * Se quiser acessar o ItemAutomovel dispon�vel no Contexto de Convers�o, basta passar o class
	 * do ItemAutovel para retornar o ItemAutomovel que esta dispon�vel no Contexto de Convers�o. 
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
		Preconditions.checkNotNull(value, "Parametro n�o dispon�vel no contexto");
		return value;
	}
	
	
	/*
	 * M�todo get params
	 */
	private Deque<ParametroContexto> getParams() {
		return params;
	}
	
}