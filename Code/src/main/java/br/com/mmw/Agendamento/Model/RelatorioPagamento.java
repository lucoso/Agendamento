package br.com.mmw.Agendamento.Model;

import java.io.Serializable;
import java.math.BigDecimal;

public class RelatorioPagamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal valorTotalRecebido;
	
	private int desconto;
	
	public RelatorioPagamento(){
		
	}

	public BigDecimal getValorTotalRecebido() {
		return valorTotalRecebido;
	}

	public void setValorTotalRecebido(BigDecimal valorTotalRecebido) {
		this.valorTotalRecebido = valorTotalRecebido;
	}

	public int getDesconto() {
		return desconto;
	}

	public void setDesconto(int desconto) {
		this.desconto = desconto;
	}
}
