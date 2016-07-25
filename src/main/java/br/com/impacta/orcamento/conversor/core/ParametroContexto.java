package br.com.impacta.orcamento.conversor.core;

/**
 * Wrapper para encapsular os parametros do contexto de conversão
 * @author Arthur Fernandes
 *
 */
public class ParametroContexto {

	private final TipoParametrizado<?, ?> tipoParametrizado;
	private final Object object;
	
	/*
	 * Contrutor padrão
	 */
	private ParametroContexto(final TipoParametrizado<?, ?> tipoParametrizado,
			final Object object) {
		super();
		this.tipoParametrizado = tipoParametrizado;
		this.object = object;
	}
	
	/**
	 * Cria o {@code ParametroContexto}
	 * @param tipoParametrizado O {@code TipoParametrizado}
	 * @param object O {@code Object}
	 * @return O {@code ParametroContexto}
	 */
	protected static ParametroContexto de(final TipoParametrizado<?, ?> tipoParametrizado,
			final Object object){
		return new ParametroContexto(tipoParametrizado, object);
	}


	/**
	 * @return O atributo tipoParametrizado
	 */
	public TipoParametrizado<?, ?> getTipoParametrizado() {
		return tipoParametrizado;
	}


	/**
	 * @return O atributo object
	 */
	public Object getObject() {
		return object;
	}
	
}
