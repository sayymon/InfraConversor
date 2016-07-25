package br.com.impacta.orcamento.conversor.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import br.com.impacta.orcamento.conversor.collection.ArrayParaArrayConversor;
import br.com.impacta.orcamento.conversor.collection.ArrayParaCollectionConversor;
import br.com.impacta.orcamento.conversor.collection.CollectionParaArrayConversor;
import br.com.impacta.orcamento.conversor.collection.CollectionParaCollectionConversor;
import br.com.impacta.orcamento.conversor.collection.CollectionParaListConversor;
import br.com.impacta.orcamento.conversor.collection.CollectionParaSetConversor;

public class ConversorBuilder<E, S> {

	private Conversor<E, S> conversorPrincipal; 
	private final TipoParametrizado<?, ?> entrada; 
	private final TipoParametrizado<?, ?> saida; 
	private Collection<ConversorCondicao> conversoresComCondicao = Lists.newArrayList();
	private PreConversao<S> preConversao;
	private boolean criarPreConversaoPadrao = false;
	private boolean habilitarContextoConversao = false;
	
	private ConversorBuilder(final TipoParametrizado<E, ?> entrada, final TipoParametrizado<S, ?> saida, final Conversor<E, S> conversorPrincipal){
		this.entrada = entrada;
		this.saida = saida;
		this.conversorPrincipal = conversorPrincipal;
	}
	
	private ConversorBuilder(final TipoParametrizado<E, ?> entrada, final TipoParametrizado<S, ?> saida){
		this.entrada = entrada;
		this.saida = saida;
	}
	
	private ConversorBuilder(final Conversor<E, S> conversorPrincipal){
		this.conversorPrincipal = conversorPrincipal;
		this.entrada = ConversorRefletionUtil.obterClasseEntradaTipoParametrizadoConversor(conversorPrincipal.getClass());
		this.saida = ConversorRefletionUtil.obterClasseSaidaTipoParametrizadoConversor(conversorPrincipal.getClass());
	}
	
	public static <E, S>ConversorBuilder<E, S> para(final Conversor<E, S> conversor){
		return new ConversorBuilder<E, S>(conversor);
	}
	
	public static <E, E2, S>ConversorBuilder<E, S> composite(final Conversor<E, E2> conversorA, final Conversor<E2, S> conversorB){
		final TipoParametrizado<E, ?> entrada = ConversorRefletionUtil.obterClasseEntradaTipoParametrizadoConversor(conversorA.getClass());
		final TipoParametrizado<S, ?> saida = ConversorRefletionUtil.obterClasseSaidaTipoParametrizadoConversor(conversorB.getClass());
		List<Conversor<?, ?>> conversores = new ArrayList<Conversor<?,?>>();
		conversores.add(conversorA);
		conversores.add(conversorB);
		return new ConversorBuilder<E, S>(entrada, saida, ConversorComposite.<E, S>para(conversores));
	}
	
	public static <E, E2, E3, S>ConversorBuilder<E, S> composite(final Conversor<E, E2> conversorA, final Conversor<E2, E3> conversorB, final Conversor<E3, S> conversorC){
		final TipoParametrizado<E, ?> entrada = ConversorRefletionUtil.obterClasseEntradaTipoParametrizadoConversor(conversorA.getClass());
		final TipoParametrizado<S, ?> saida = ConversorRefletionUtil.obterClasseSaidaTipoParametrizadoConversor(conversorC.getClass());
		List<Conversor<?, ?>> conversores = new ArrayList<Conversor<?,?>>();
		conversores.add(conversorA);
		conversores.add(conversorB);
		conversores.add(conversorC);
		return new ConversorBuilder<E, S>(entrada, saida, ConversorComposite.<E, S>para(conversores));
	}
	
	public static <E, S>ConversorBuilder<E, S> para(final TipoParametrizado<E, ?> entrada, final TipoParametrizado<S, ?> saida){
		return new ConversorBuilder<E, S>(entrada, saida);
	}
	
	static <E, S>ConversorBuilder<E, S> para(final TipoParametrizado<E, ?> entrada, final TipoParametrizado<S, ?> saida, final Conversor<E, S> conversor){
		return new ConversorBuilder<E, S>(entrada, saida, conversor);
	}
	
	public ConversorBuilder<E, S> comCondicao(final Conversor<E, S> conversor, final Condicao condicao){
		this.conversoresComCondicao.add(ConversorCondicao.para(conversor, entrada, saida, condicao));
		return this;
	}
	
	public ConversorBuilder<E, S> comCondicaoPadrao(final Conversor<E, S> conversor){
		this.conversoresComCondicao.add(ConversorCondicao.para(conversor, entrada, saida, ConversorCondicao.CONDICAO_PADRAO));
		return this;
	}
	
	public ConversorBuilder<E, S> habilitarContextoConversao(){
		this.habilitarContextoConversao = true;
		return this;
	}
	
	public ConversorBuilder<E, S> comPreConversao(final PreConversao<S> preConversao){
		this.habilitarContextoConversao = true;
		this.preConversao = preConversao;
		return this;
	}
	
	public ConversorBuilder<E, S> comPreConversaoPadrao(){
		this.habilitarContextoConversao = true;
		this.criarPreConversaoPadrao = true;
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public Conversor<E, S> build(){
		Conversor<E, S> conversor = null;
		
		// Se foi adicionado conversor com condicao cria o conversor condicional
		if (conversoresComCondicao.size() > 0){
			conversor = (Conversor<E, S>) ConversorCondicional.para(entrada, saida, conversoresComCondicao);
		}else{
			// Se não for usar conversor condicional, necessariamente precisa possuir 
			// um conversor
			Preconditions.checkNotNull(this.conversorPrincipal);
			conversor = this.conversorPrincipal;
		}
		// Se foi pedido para criar PreConversao padrão cria-a
		if (criarPreConversaoPadrao){
			preConversao = PreConversoes.criarPreConversaoDefaultPara(entrada, saida);
		}
		// Se foi criada a PreConversao Padrao ou foi passada um PreConversao, 
		// adiciona o Conversor com PreConversao
		if (preConversao != null){ 
			conversor = PreConversaoConversor.para(conversor, preConversao);
		}
		// Adiciona proxy caso tenha sido solicitado
		if (habilitarContextoConversao){
			conversor = ConversorContextoProxy.obterProxy(entrada, conversor);
		}
		return conversor;
	}
	
	public Conversor<Collection<E>, List<S>> buildList(){
		return ConversorBuilder.para(new CollectionParaListConversor<E, S>(build()))
				.comPreConversaoPadrao()
				.habilitarContextoConversao()
				.build();
	}
	
	public Conversor<Collection<E>, Set<S>> buildSet(){
		return ConversorBuilder.para(new CollectionParaSetConversor<E, S>(build()))
				.comPreConversaoPadrao()
				.habilitarContextoConversao()
				.build();
	}
	
	public Conversor<Collection<E>, Collection<S>> buildCollection(){
		return ConversorBuilder.para(new CollectionParaCollectionConversor<E, S>(build()))
				.comPreConversaoPadrao()
				.habilitarContextoConversao()
				.build();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Conversor<Collection<E>, S[]> buildCollectionParaArray(){
		return ConversorBuilder.para(
					TipoParametrizado.collection(entrada.getClasseOriginal()), 
					TipoParametrizado.array(saida.getClasseOriginal()), 
					(Conversor)new CollectionParaArrayConversor<E, S>(build(), saida.getClasseOriginal()))
				.comPreConversaoPadrao()
				.habilitarContextoConversao()
				.build();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Conversor<E[], S[]> buildArrayParaArray(){
		return ConversorBuilder.para(
					TipoParametrizado.array(entrada.getClasseOriginal()), 
					TipoParametrizado.array(saida.getClasseOriginal()), 
					(Conversor)new ArrayParaArrayConversor<E, S>(build(), saida.getClasseOriginal()))
				.comPreConversaoPadrao()
				.habilitarContextoConversao()
				.build();
	}
	
	public Conversor<E[], Collection<S>> buildArrayParaCollection(){
		return ConversorBuilder.para(new ArrayParaCollectionConversor<E, S>(build()))
				.comPreConversaoPadrao()
				.habilitarContextoConversao()
				.build();
	}

	/**
	 * @return Retorna o valor do atributo entrada
	 */
	public TipoParametrizado<?, ?> getEntrada() {
		return entrada;
	}

	/**
	 * @return Retorna o valor do atributo saida
	 */
	public TipoParametrizado<?, ?> getSaida() {
		return saida;
	}

}
