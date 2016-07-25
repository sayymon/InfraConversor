package br.com.impacta.orcamento.conversor.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Sabe requisitar o conversor a fábrica somente quando necessário.
 * 
 * @author Arthur Fernandes
 *
 * @param <E> - Objeto que se faz necessário para a conversão
 * @param <S> - Objeto que resultará a conversão.
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
		// Regra Padrão, caso não exista nenhum conversor valido, 
		// pega o conversor com regra padrão, caso exista.
		if (conversores.size() == 0){
			for (ConversorCondicao registro : this.conversores) {
				if (registro.isRegraPadrao()){
					conversores.add((Conversor<E, S>) registro.getConversor());
				}
			}
		}
		
		//Assert
		if (conversores.size() > 1){
			throw new RuntimeException("Existem (" + conversores.size() + ") conversores validos para a execução da conversão de '" + 
					tipoEntrada.toString() + "' para '" + tipoSaida.toString() + "'");
		} else if (conversores.size() == 0){
			throw new RuntimeException("Não existe nenhum conversor validos para a execução da conversão de '" + 
					tipoEntrada.toString() + "' para '" + tipoSaida.toString() + "'");
		}
		return conversores.iterator().next();
	}
	

}
