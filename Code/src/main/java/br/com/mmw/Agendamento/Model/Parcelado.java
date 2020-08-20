package br.com.mmw.Agendamento.Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Parcelado extends Pagamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="Prazo_Numero_Vezes", nullable=true)
	private int qntsVezes;
	
	public Parcelado(long id, String forma, String tipo, Date dataPagamento, BigDecimal valorTotal, int desconto,
			boolean pago, Servico servico, int qntsVezes) {
		super(id, forma, tipo, dataPagamento, valorTotal, desconto, pago, servico);
		this.qntsVezes = qntsVezes;
	}

	public Parcelado(){
		
	}

	public int getQntsVezes() {
		return qntsVezes;
	}

	public void setQntsVezes(int qntsVezes) {
		this.qntsVezes = qntsVezes;
	}

	

}
