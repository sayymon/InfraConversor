package br.com.impacta.orcamento.conversor.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.common.collect.ImmutableList;


/**
 * {@code ConversorFactoryGerenciado} provê mecanismo para registrar conversores e 
 * recupera-los quando necessário.
 * 
 * Para {@link Conversor} é possível criar condições onde varios conversores do mesmo tipo pode ser registrado, 
 * e quando se necessita do conversor é devolvido o que possui uma {@link Condicao} valida.  
 * 
 * Para {@link Conversor} que não são do tipo de coleções é criado um conversor de coleção automáticamente, 
 * permitindo a conversão de uma coleção.
 * 
 * @author Arthur Fernandes
 *
 */
public class ConversorFactoryGerenciado implements ConversorFactory {

	private static final Logger LOGGER = Logger.getLogger(ConversorFactoryGerenciado.class);
	
	private Collection<ConversorComTipo> conversoresRegistrados = ImmutableList.of();
	private final ConversorFactoryGerenciadoContexto contexto;
	private boolean contextoInicializado;
	private ConversorLazyFactory conversorProxyFactory;
	
	private ConversorFactoryGerenciado(ConversorFactoryGerenciadoContexto contexto){
		this.contexto = contexto;
		this.conversorProxyFactory = ConversorLazyFactory.obterFactory(this);
	}
	
	public static ConversorFactory instancia(ConversorFactoryGerenciadoContexto contexto) {
		ConversorFactoryGerenciado gerenciadorConversorRegraFactory = new ConversorFactoryGerenciado(contexto);
		return gerenciadorConversorRegraFactory.conversorProxyFactory;
	}
	
	public static FluentConversorFactory fluentFactory(ConversorFactoryGerenciadoContexto contexto) {
		return FluentConversorFactory.para(instancia(contexto));
	}
	
	@Override
	public <E, S> Conversor<E, S> construirConversor(Class<E> classeEntrada,
			Class<S> classeSaida) {
		return construirConversor(TipoParametrizado.de(classeEntrada), TipoParametrizado.de(classeSaida));
	}
	
	@Override
	public <E, S> Conversor<E, S> construirConversor(
			TipoParametrizado<E, ?> tipoEntrada,
			TipoParametrizado<S, ?> tipoSaida) {
		
		if (!contextoInicializado){
			iniciaContexto();
		}
		List<Conversor<E, S>> conversores = obterConversores(tipoEntrada, tipoSaida);
		
		//Assert
		if (conversores.size() > 1){
			throw new RuntimeException("Existem (" + conversores.size() + ") conversores registrados validos para a execução da conversão de '" + 
					tipoEntrada.toString() + "' para '" + tipoSaida.toString() + "'");
		} else if (conversores.size() == 0){
			// Caso não existir o conversor de colection na fabrica, mas existir o conversor individual cria um conversor de coleção 
			// conforme solicitado
			if (isCollectionOuArray(tipoEntrada) && 
					isCollectionOuArray(tipoSaida) && 
					obterConversores(tipoEntrada.getTipoColecaoOuArray(), tipoSaida.getTipoColecaoOuArray()).size() == 1){
				final ConversorComTipo conversorColecao = criarConversorColecao(tipoEntrada, tipoSaida);
				registrarConversor(conversorColecao);
				conversores = obterConversores(tipoEntrada, tipoSaida);
			}else{
				throw new RuntimeException("Não existe nenhum conversor registrado validos para a execução da conversão de '" + 
						tipoEntrada.toString() + "' para '" + tipoSaida.toString() + "'");
			}
		}
		return conversores.iterator().next();
	}
	
	/*
	 * Cria o conversor de coleção baseado no tipo solicitado
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private ConversorComTipo criarConversorColecao(final TipoParametrizado<?, ?> tipoEntrada, 
			final TipoParametrizado<?, ?> tipoSaida){
		final Conversor<?, ?> conversorIndividual = obterConversores(tipoEntrada.getTipoColecaoOuArray(), tipoSaida.getTipoColecaoOuArray()).iterator().next();
		final ConversorBuilder<?, ?> conversorColecaoBuilder = ConversorBuilder.para(
				tipoEntrada.getTipoColecaoOuArray(), 
				tipoSaida.getTipoColecaoOuArray(), 
				(Conversor) conversorIndividual);
		
		final Conversor<?, ?> conversorColecao;
		
		if(tipoEntrada.getTipo() == TipoParametrizadoEnum.ARRAY && 
				tipoSaida.getTipo() == TipoParametrizadoEnum.ARRAY){
			conversorColecao = conversorColecaoBuilder.buildArrayParaArray();
		}else if(tipoEntrada.getTipo() == TipoParametrizadoEnum.ARRAY && 
					tipoSaida.getTipo() == TipoParametrizadoEnum.COLLECTION){
			conversorColecao = conversorColecaoBuilder.buildArrayParaCollection();
		}else if (tipoSaida.getTipo() == TipoParametrizadoEnum.ARRAY){ // Quando entrada for collection e array
			conversorColecao = conversorColecaoBuilder.buildCollectionParaArray();
		}else if(tipoSaida.getTipo() == TipoParametrizadoEnum.LIST){
			conversorColecao = conversorColecaoBuilder.buildList();
		}else if(tipoSaida.getTipo() == TipoParametrizadoEnum.SET){
			conversorColecao = conversorColecaoBuilder.buildSet();
		}else{
			conversorColecao = conversorColecaoBuilder.buildCollection();
		}
		TipoParametrizado<?, ?> tipoEntradaConversorColecao = tipoEntrada;
		if (!tipoEntrada.getTipo().equals(TipoParametrizadoEnum.ARRAY)){
			// Quando for conversão de List ou Set deve registrar como Collection
			tipoEntradaConversorColecao = TipoParametrizado.collection(tipoEntrada.getParametrizacaoClasse());
		}
		return ConversorComTipo.para(conversorColecao, tipoEntradaConversorColecao, tipoSaida);
	}
	
	/*
	 * Registra o conversão de coleção na factory
	 */
	private synchronized void registrarConversor(ConversorComTipo conversorComTipo){
		// apenas registra se não existir
		if (this.obterConversores(conversorComTipo.getClasseTipoParametrizadoEntrada(), 
				conversorComTipo.getClasseTipoParametrizadoSaida()).isEmpty()){
			conversoresRegistrados = ImmutableList.<ConversorComTipo>builder()
					.addAll(conversoresRegistrados)
					.add(conversorComTipo)
					.build();
			if (LOGGER.isDebugEnabled()){
				LOGGER.trace("Criando conversor de '" + 
						conversorComTipo.getClasseTipoParametrizadoEntrada().toString() + 
						"' para '" + conversorComTipo.getClasseTipoParametrizadoSaida().toString() + "'");
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private <E, S> List<Conversor<E, S>> obterConversores(TipoParametrizado<E, ?> tipoEntrada,
			TipoParametrizado<S, ?> tipoSaida){
		List<Conversor<E, S>> conversores = new ArrayList<Conversor<E,S>>();
		for (ConversorComTipo conversor : conversoresRegistrados) {
			if (conversor.tipoSeAplica(tipoEntrada, tipoSaida)){
				conversores.add((Conversor<E, S>) conversor.getConversor());
			}
		}
		return conversores;
	}
	
	private boolean isCollectionOuArray(TipoParametrizado<?, ?> tipoParametrizado){
		return tipoParametrizado.getTipo() == TipoParametrizadoEnum.ARRAY ||
				tipoParametrizado.getTipo() == TipoParametrizadoEnum.COLLECTION ||
				tipoParametrizado.getTipo() == TipoParametrizadoEnum.LIST ||
				tipoParametrizado.getTipo() == TipoParametrizadoEnum.SET;
	}
	
	private synchronized void iniciaContexto(){
		// Verifica novamente pois aqui esta sincronizado e assim garantirá que só irá inicializar uma vez
		if (!contextoInicializado){
			conversoresRegistrados = ImmutableList.copyOf(contexto.inicializarContexto());
			contextoInicializado = true;
			loggarConversoresRegistrados();
		}
	}
	
	private void loggarConversoresRegistrados(){
		if (LOGGER.isDebugEnabled()){
			long threadId = Thread.currentThread().getId();
			LOGGER.trace("Thread # " + threadId + " is doing this task");
			for (ConversorComTipo conversor : this.conversoresRegistrados) {
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append("Conversor (")
					.append(conversor.getClasseConversor().getSimpleName())
					.append(") registrado de '")
					.append(conversor.getClasseTipoParametrizadoEntrada().toString())
					.append("' para '")
					.append(conversor.getClasseTipoParametrizadoSaida().toString())
					.append("'");					
				LOGGER.trace(stringBuilder.toString());
			}
		}
	}

}
