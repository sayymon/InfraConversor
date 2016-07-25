package br.com.impacta.orcamento.conversor.core;

import java.util.Collection;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class ConversorFactoryGerenciadoContextoBuilder {
	
	private Map<TipoConversor, Collection<ConversorComCondicaoComPreConversao>> mapConversores = Maps.newHashMap();

	private ConversorFactoryGerenciadoContextoBuilder() {
	}
	
	public static ConversorFactoryGerenciadoContextoBuilder instancia(){
		return new ConversorFactoryGerenciadoContextoBuilder();
	}
	
	private <E, S> ConversorFactoryGerenciadoContextoBuilder com(Conversor<E, S> conversor,
			Condicao condicao, PreConversao<S> preconversao){
		TipoParametrizado<?, ?> entrada = ConversorRefletionUtil.obterClasseEntradaTipoParametrizadoConversor(conversor.getClass());
		TipoParametrizado<?, ?> saida = ConversorRefletionUtil.obterClasseSaidaTipoParametrizadoConversor(conversor.getClass());

		TipoConversor tipo = new TipoConversor(entrada, saida);
		Collection<ConversorComCondicaoComPreConversao> conversores = mapConversores.get(tipo);
		
		if (conversores == null){
			conversores = Lists.newArrayList();
		}
		conversores.add(new ConversorComCondicaoComPreConversao(conversor, condicao, preconversao));
		mapConversores.put(tipo, conversores);
		return this;
	}
	
	public <E, S> ConversorFactoryGerenciadoContextoBuilder com(Conversor<?, ?> conversor){
		return this.com(conversor, ConversorCondicao.CONDICAO_PADRAO);
	}
	
	public <E, S> ConversorFactoryGerenciadoContextoBuilder com(Conversor<E, S> conversor,
			Condicao condicao){
		return this.com(conversor, condicao, null);
	}
	
	public <E, S> ConversorFactoryGerenciadoContextoBuilder com(Conversor<E, S> conversor,
			PreConversao<S> preConversao){
		return this.com(conversor, ConversorCondicao.CONDICAO_PADRAO, preConversao);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Collection<ConversorComTipo> build(){
		Collection<ConversorComTipo> conversores = Lists.newArrayList();
		
		for (TipoConversor tipoConversor : mapConversores.keySet()) {
			Collection<ConversorComCondicaoComPreConversao> conversoresCondicao = mapConversores.get(tipoConversor);
			
			ConversorBuilder<?, ?> conversorBuilder;
			ConversorComCondicaoComPreConversao conversorPrincipal = null;
			
			if (conversoresCondicao.size() == 1){
				conversorPrincipal = conversoresCondicao.iterator().next();
				conversorBuilder = ConversorBuilder.para(conversorPrincipal.getConversor());
			}else{
				conversorBuilder = ConversorBuilder.para(tipoConversor.getEntrada(), tipoConversor.getSaida());
				for (ConversorComCondicaoComPreConversao conversorCondicao : conversoresCondicao) {
					if (conversorCondicao.isRegraPadrao()){
						conversorPrincipal = conversorCondicao;
						conversorBuilder.comCondicaoPadrao((Conversor)conversorPrincipal.getConversor());
					}else{
						conversorBuilder.comCondicao((Conversor)conversorCondicao.getConversor(), conversorCondicao.getCondicao());
					}
				}
				if (conversorPrincipal == null){
					conversorPrincipal = conversoresCondicao.iterator().next();
				}
			}
			
			if (conversorPrincipal.getPreConversao() == null){
				conversorBuilder.comPreConversaoPadrao();
			}else{
				conversorBuilder.comPreConversao((PreConversao)conversorPrincipal.getPreConversao());
			}
			conversorBuilder.habilitarContextoConversao();
			
			conversores.add(ConversorComTipo.para(conversorBuilder.build(), tipoConversor.getEntrada(), tipoConversor.getSaida()));
		}
		
		return conversores;
	}
	
	private class TipoConversor{
		private final TipoParametrizado<?, ?> entrada;
		private final TipoParametrizado<?, ?> saida;
		
		/* Contrutor padrao */
		private TipoConversor(TipoParametrizado<?, ?> entrada,
				TipoParametrizado<?, ?> saida) {
			super();
			this.entrada = entrada;
			this.saida = saida;
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

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result
					+ ((entrada == null) ? 0 : entrada.hashCode());
			result = prime * result + ((saida == null) ? 0 : saida.hashCode());
			return result;
		}
		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			TipoConversor other = (TipoConversor) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (entrada == null) {
				if (other.entrada != null)
					return false;
			} else if (!entrada.equals(other.entrada))
				return false;
			if (saida == null) {
				if (other.saida != null)
					return false;
			} else if (!saida.equals(other.saida))
				return false;
			return true;
		}
		private ConversorFactoryGerenciadoContextoBuilder getOuterType() {
			return ConversorFactoryGerenciadoContextoBuilder.this;
		}
		
	}
	
	private static class ConversorComCondicaoComPreConversao {

		private final Conversor<?, ?> conversor;
		private final Condicao condicao;
		private final PreConversao<?> preConversao;

		private ConversorComCondicaoComPreConversao(Conversor<?, ?> conversor, Condicao condicao, PreConversao<?> preConversao) {
			this.conversor = conversor;
			this.condicao = condicao;
			this.preConversao = preConversao;
		}

		public boolean isRegraPadrao() {
			return ConversorCondicao.CONDICAO_PADRAO.equals(condicao);
		}

		public PreConversao<?> getPreConversao() {
			return preConversao;
		}
		
		public Condicao getCondicao() {
			return condicao;
		}
		
		public Conversor<?, ?> getConversor() {
			return conversor;
		}
		
	}
}
