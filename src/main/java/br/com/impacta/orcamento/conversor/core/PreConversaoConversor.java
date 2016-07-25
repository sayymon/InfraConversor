package br.com.impacta.orcamento.conversor.core;


/**
 * Define o {@link ValorPadraoConversao} e a {@link PreConversao} para o conversor caso ele implemente as interfaces. Caso contrario, 
 * aplica o {@link ValorPadraoConversao} e a {@link PreConversao} dependendo do tipo do {@link Conversor}
 * @author Arthur Fernandes
 *
 * @param <E>
 * @param <S>
 */
public class PreConversaoConversor<E, S> implements Conversor<E, S> {

	private final Conversor<E, S> conversor;
	private final PreConversao<S> preConversao;
	
	private PreConversaoConversor(Conversor<E, S> conversor,
			PreConversao<S> preConversao) {
		super();
		this.conversor = conversor;
		this.preConversao = preConversao;
	}

	static <E, S> Conversor<E, S> para(Conversor<E, S> conversor, PreConversao<S> preConversao){
		return new PreConversaoConversor<E, S>(conversor, preConversao);
	}
	
	@Override
	public S converter(E entrada) {
		S valor = null;
		if (preConversao.aplicaConversao()){
			valor = conversor.converter(entrada);
		}else{
			valor = preConversao.valorPadrao();
		}
		return valor;
	}

}
