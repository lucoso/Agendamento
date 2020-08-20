package br.com.mmw.Agendamento.Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class Parcela implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Column(name="Parcela_Valor_Dividido", scale=2, nullable=false)
	private BigDecimal valor;
	
	@Column(name="Parcela_Data_Pagamento", nullable=true)
	@Temporal(value=TemporalType.DATE)
	private Date dataPagamento;
	
	@NotNull
	@Column(name="Parcela_Pago", length=5, nullable=false)
	private boolean pago;
	
	@NotNull
	@Column(name="Primeira_Parcela", length=5, nullable=false)
	private boolean primeira;
	
	@NotNull
	@Column(name="Parcela_Data_Vencimento", nullable=false)
	@Temporal(value=TemporalType.DATE)
	private Date vencimento;
	
	@ManyToOne
	@JoinColumn(name="Parcelado_ID")
	private Parcelado parcelado;
	
	public Parcela(){
		
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}

	public boolean isPrimeira() {
		return primeira;
	}

	public void setPrimeira(boolean primeira) {
		this.primeira = primeira;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public Parcelado getParcelado() {
		return parcelado;
	}

	public void setParcelado(Parcelado parcelado) {
		this.parcelado = parcelado;
	}

	public long getId() {
		return id;
	}
	
	

}
