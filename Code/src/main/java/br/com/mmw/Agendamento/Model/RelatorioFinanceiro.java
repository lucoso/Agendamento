package br.com.mmw.Agendamento.Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class RelatorioFinanceiro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nomeCliente;
	
	private Date dataAberturaServico;
	
	private List<RelatorioPagamento> pagamentos;
	
	public RelatorioFinanceiro(){
		
	}
	
	public BigDecimal getTotalRecebidoCliente(){
		BigDecimal total = new BigDecimal(0);
		
		for(RelatorioPagamento r : pagamentos){
			total = total.add(r.getValorTotalRecebido());
		}
		
		return total;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Date getDataAberturaServico() {
		return dataAberturaServico;
	}

	public void setDataAberturaServico(Date dataAberturaServico) {
		this.dataAberturaServico = dataAberturaServico;
	}

	public List<RelatorioPagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<RelatorioPagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}

	
}
