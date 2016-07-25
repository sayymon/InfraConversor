package br.com.impacta.orcamento.conversor.core;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Demarca classes que são utilizadas como Wrapper para as entradas dos 
 * conversores.
 * <br> 
 * Quando a classe é demarcada como {@code ConversorWrapper} ela não 
 * é adicionada no contexto de conversão e sim seus atributos.
 *	<br><br>
 *
 * Seguindo o exemplo abaixo: 
 * 
 * <pre>
 *  @ConversorWrapper
 *  public class TesteWrapper {
 *     private TesteA testea;
 *     private List<TesteB> testeb;
 *     ...
 *  }
 * </pre>
 * 
 * No exemplo acima o {@code TesteA} e o {@code List<TesteB>} estará disponivel 
 * no contexto de conversão. Podendo ser acessado diretamento {@code ConversorContexto.obterParametro(TesteA.class)}
 * 
 * @author Arthur Buzzo Fernandes
 */
@Target(value={java.lang.annotation.ElementType.TYPE})
@Retention(value=java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface ConversorWrapper {

}
