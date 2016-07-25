package br.com.impacta.orcamento.conversor.core;

/**
 * Associa um {@link Conversor} a uma {@link Condicao}, sendo assim é possível existir varios conversores para
 * converter o mesmo tipo de conversão com condições diferentes. 
 * @author Arthur Fernandes
 *
 */
class ConversorCondicao {

	private final Conversor<?, ?> conversor;
	private final Class<? extends Conversor<?, ?>> classeConversor;
	private final TipoParametrizado<?, ?> classeTipoParametrizadoEntrada;
	private final TipoParametrizado<?, ?> classeTipoParametrizadoSaida;
	private final Condicao regra;

	public final static Condicao CONDICAO_PADRAO = new Condicao() {
		@Override
		public boolean seAplica() {
			// Retorna false, porem ele é tratado como regra padrão que é executado quando não existem outros conversores
			return false;
		}
	};
	
	@SuppressWarnings("unchecked")
	private ConversorCondicao(Conversor<?, ?> conversor, TipoParametrizado<?, ?> classeTipoParametrizadoEntrada, 
			TipoParametrizado<?, ?> classeTipoParametrizadoSaida,
			Condicao regra) {
		this.conversor = conversor;
		this.classeConversor = (Class<? extends Conversor<?, ?>>) conversor.getClass();
		this.classeTipoParametrizadoEntrada = classeTipoParametrizadoEntrada;
		this.classeTipoParametrizadoSaida = classeTipoParametrizadoSaida;
		this.regra = regra;
	}

	static ConversorCondicao para(Conversor<?, ?> conversor, TipoParametrizado<?, ?> classeTipoParametrizadoEntrada, 
			TipoParametrizado<?, ?> classeTipoParametrizadoSaida,
			Condicao regra){
		return new ConversorCondicao(conversor, 
				classeTipoParametrizadoEntrada,
				classeTipoParametrizadoSaida, 
				regra);
	}
	
	public boolean tipoSeAplica(TipoParametrizado<?, ?> tipoEntrada, TipoParametrizado<?, ?> tipoSaida){
		return getClasseTipoParametrizadoEntrada().isMesmoTipoComHeranca(tipoEntrada) &&
				getClasseTipoParametrizadoSaida().isMesmoTipoAbsoluto(tipoSaida);
	}
	
	public Class<? extends Conversor<?, ?>> getClasseConversor() {
		return classeConversor;
	}

	public TipoParametrizado<?, ?> getClasseTipoParametrizadoEntrada() {
		return classeTipoParametrizadoEntrada;
	}
	
	public TipoParametrizado<?, ?> getClasseTipoParametrizadoSaida() {
		return classeTipoParametrizadoSaida;
	}
	
	public Condicao getRegra() {
		return regra;
	}
	
	public Conversor<?, ?> getConversor() {
		return conversor;
	}
	
	public boolean isRegraPadrao(){
		// Se estiver utilizando o REGRA_NULL_OBJECT deve ser tratado como regra padrão 
		// (Regra padrão é executada quando não existe outros conversores para ser executado)
		return CONDICAO_PADRAO.equals(regra);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((classeConversor == null) ? 0 : classeConversor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConversorCondicao other = (ConversorCondicao) obj;
		if (classeConversor == null) {
			if (other.classeConversor != null)
				return false;
		} else if (!classeConversor.equals(other.classeConversor))
			return false;
		return true;
	}
	
	

}