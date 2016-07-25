package br.com.impacta.orcamento.conversor.core;

public class Orcamento {

	private Integer codigo = 1;
	
	public Orcamento(Integer codigo) {
		this.codigo = codigo;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
}
