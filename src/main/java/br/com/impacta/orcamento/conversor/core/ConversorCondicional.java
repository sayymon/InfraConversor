package br.com.impacta.orcamento.conversor.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Sabe requisitar o conversor a f�brica somente quando necess�rio.
 * 
 * @author Arthur Fernandes
 *
 * @param <E> - Objeto que se faz necess�rio para a convers�o
 * @param <S> - Objeto que resultar� a convers�o.
 */
class ConversorCondicional<E, S> implements Conversor<E, S> {

	private final TipoParametrizado<E, ?> tipoEntrada;
	private final TipoParametrizado<S, ?> tipoSaida;
	private final Collection<ConversorCondicao> conversores;
	
	private ConversorCondicional(final TipoParametrizado<E, ?> tipoEntrada, 
			final TipoParametrizado<S, ?> tipoSaida, 
			final Collection<ConversorCondicao> conversores) {
		this.tipoEntrada = tipoEntrada;
		this.tipoSaida = tipoSaida;
		this.conversores = conversores;
	}
	
	static <E, S> ConversorCondicional<E, S> para(final TipoParametrizado<E, ?> tipoEntrada, 
			final TipoParametrizado<S, ?> tipoSaida, 
			final Collection<ConversorCondicao> conversores) {
		return new ConversorCondicional<E, S>(tipoEntrada, tipoSaida, conversores);
	}
	
	@Override
	public S converter(E entrada) {
		return obterConversor().converter(entrada);
	}
	
	@SuppressWarnings("unchecked")
	private Conversor<E, S> obterConversor(){
		List<Conversor<E, S>> conversores = new ArrayList<Conversor<E,S>>();
		for (ConversorCondicao registro : this.conversores) {
			if (registro.getRegra().seAplica()){
				conversores.add((Conversor<E, S>) registro.getConversor());
			}
		}
		// Regra Padr�o, caso n�o exista nenhum conversor valido, 
		// pega o conversor com regra padr�o, caso exista.
		if (conversores.size() == 0){
			for (ConversorCondicao registro : this.conversores) {
				if (registro.isRegraPadrao()){
					conversores.add((Conversor<E, S>) registro.getConversor());
				}
			}
		}
		
		//Assert
		if (conversores.size() > 1){
			throw new RuntimeException("Existem (" + conversores.size() + ") conversores validos para a execu��o da convers�o de '" + 
					tipoEntrada.toString() + "' para '" + tipoSaida.toString() + "'");
		} else if (conversores.size() == 0){
			throw new RuntimeException("N�o existe nenhum conversor validos para a execu��o da convers�o de '" + 
					tipoEntrada.toString() + "' para '" + tipoSaida.toString() + "'");
		}
		return conversores.iterator().next();
	}
	

}
