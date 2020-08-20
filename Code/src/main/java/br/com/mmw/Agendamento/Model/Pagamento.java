package br.com.mmw.Agendamento.Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Pagamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	@Column(name="Pagamento_Forma", length=15, nullable=false)
	private String forma;
	
	@NotEmpty
	@Column(name="Pagamento_Tipo", length=15, nullable=false)
	private String tipo;
	
	@Column(name="Pagamento_Data", nullable=true)
	@Temporal(value=TemporalType.DATE)
	private Date dataPagamento;
	
	@NotNull
	@Column(name="Pagamento_Valor_Total", scale=2, nullable=false)
	private BigDecimal valorTotal;
	
	@Column(name="Pagamento_Desconto", nullable=true)
	private int desconto;
	
	@NotNull
	@Column(name="Pagamento_Pago", length=5, nullable=false)
	private boolean pago;
	
	@ManyToOne
	@JoinColumn(name="Servico_ID")
	private Servico servico;

	public Pagamento(long id, String forma, String tipo, Date dataPagamento, BigDecimal valorTotal, int desconto,
			boolean pago, Servico servico) {
		super();
		this.id = id;
		this.forma = forma;
		this.tipo = tipo;
		this.dataPagamento = dataPagamento;
		this.valorTotal = valorTotal;
		this.desconto = desconto;
		this.pago = pago;
		this.servico = servico;
	}

	public Pagamento(){
		
	}

	public String getForma() {
		return forma;
	}

	public void setForma(String forma) {
		this.forma = forma;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public boolean isPago() {
		return pago;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getDesconto() {
		return desconto;
	}

	public void setDesconto(int desconto) {
		this.desconto = desconto;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public long getId() {
		return id;
	}
	
	

}
