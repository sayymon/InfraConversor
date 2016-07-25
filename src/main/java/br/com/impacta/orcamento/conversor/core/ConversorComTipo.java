package br.com.impacta.orcamento.conversor.core;

/**
 * Associa um {@link Conversor} a uma {@link Condicao}, sendo assim é possível existir varios conversores para
 * converter o mesmo tipo de conversão com condições diferentes. 
 * @author Arthur Fernandes
 *
 */
public class ConversorComTipo {

	private final Conversor<?, ?> conversor;
	private final Class<?> classeConversor;
	private final TipoParametrizado<?, ?> classeTipoParametrizadoEntrada;
	private final TipoParametrizado<?, ?> classeTipoParametrizadoSaida;

	private ConversorComTipo(Conversor<?, ?> conversor, TipoParametrizado<?, ?> classeTipoParametrizadoEntrada, 
			TipoParametrizado<?, ?> classeTipoParametrizadoSaida) {
		this.conversor = conversor;
		this.classeConversor = conversor.getClass();
		this.classeTipoParametrizadoEntrada = classeTipoParametrizadoEntrada;
		this.classeTipoParametrizadoSaida = classeTipoParametrizadoSaida;
	}

	static ConversorComTipo para(Conversor<?, ?> conversor,
			TipoParametrizado<?, ?> entrada,
			TipoParametrizado<?, ?> saida){
		return new ConversorComTipo(conversor, 
				entrada,
				saida);
	}
	
	public boolean tipoSeAplica(TipoParametrizado<?, ?> tipoEntrada, TipoParametrizado<?, ?> tipoSaida){
		return getClasseTipoParametrizadoEntrada().isMesmoTipoComHeranca(tipoEntrada) &&
				getClasseTipoParametrizadoSaida().isMesmoTipoAbsoluto(tipoSaida);
	}
	
	public Class<?> getClasseConversor() {
		return classeConversor;
	}

	public TipoParametrizado<?, ?> getClasseTipoParametrizadoEntrada() {
		return classeTipoParametrizadoEntrada;
	}
	
	public TipoParametrizado<?, ?> getClasseTipoParametrizadoSaida() {
		return classeTipoParametrizadoSaida;
	}
	
	public Conversor<?, ?> getConversor() {
		return conversor;
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
		ConversorComTipo other = (ConversorComTipo) obj;
		if (classeConversor == null) {
			if (other.classeConversor != null)
				return false;
		} else if (!classeConversor.equals(other.classeConversor))
			return false;
		return true;
	}
	
	

}