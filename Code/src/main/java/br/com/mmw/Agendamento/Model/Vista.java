package br.com.mmw.Agendamento.Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Vista extends Pagamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Vista() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Vista(long id, String forma, String tipo, Date dataPagamento, BigDecimal valorTotal, int desconto,
			boolean pago, Servico servico) {
		super(id, forma, tipo, dataPagamento, valorTotal, desconto, pago, servico);
		// TODO Auto-generated constructor stub
	}

	

	
	
	

}
