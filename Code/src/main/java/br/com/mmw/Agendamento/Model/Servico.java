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

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Servico implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	@Column(name="Servico_Descricao", nullable=false)
	private String descricao;
	
	@NotNull
	@Column(name="Servico_DataAbertura", nullable=false)
	@Temporal(value=TemporalType.DATE)
	private Date dataAbertura;
	
	@Column(name="Servico_DataFinalizado", nullable=true)
	@Temporal(value=TemporalType.DATE)
	private Date dataFinalizado;
	
	@Column(name="Servico_Finalizado", nullable=false)
	private boolean finalizado;
	
	@NotNull
	@Column(name="Servico_Valor_Total", scale=2, nullable=false)
	private BigDecimal valor;
	
	@NotEmpty
	@Column(name="Servico_Status", nullable=false)
	private String status;
	
	@Column(name="Servico_Observacao", nullable=true)
	private String obs;
	
	@ManyToOne
	@JoinColumn(name = "Cliente_ID")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "Empresa_ID")
	private EmpresaCliente empresaCliente;
	
	public Servico(){
		
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Date getDataFinalizado() {
		return dataFinalizado;
	}

	public void setDataFinalizado(Date dataFinalizado) {
		this.dataFinalizado = dataFinalizado;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public EmpresaCliente getEmpresaCliente() {
		return empresaCliente;
	}

	public void setEmpresaCliente(EmpresaCliente empresaCliente) {
		this.empresaCliente = empresaCliente;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public long getId() {
		return id;
	}
	
	

}
