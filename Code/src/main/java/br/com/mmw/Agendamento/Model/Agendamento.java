package br.com.mmw.Agendamento.Model;

import java.io.Serializable;
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
public class Agendamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	@Column(name="Agendamento_Motivo", nullable=false)
	private String motivo;
	
	@Column(name="Agendamento_DataCriada", nullable=false)
	@Temporal(value=TemporalType.DATE)
	private Date dataCriada;
	
	@NotNull
	@Column(name="Agendamento_DataAgendada", nullable=false)
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date dataAgendada;
	
	@NotNull
	@Column(name="Agendamento_Cancelado", length=5, nullable=false)
	private boolean cancelado;
	
	@Column(name="Agendamento_Motivo_Cancelado", nullable=true)
	private String motivoCancelado;
	
	@NotNull
	@Column(name="Agendamento_Finalizado", length=5, nullable=false)
	private boolean finalizado;
	
	@Column(name="Agendamento_Relatorio", nullable=true)
	private String resumo;
	
	@ManyToOne
	@JoinColumn(name = "Funcionario_ID")
	private Funcionario funcionario;
	
	@ManyToOne
	@JoinColumn(name = "Servico_ID")
	private Servico servico;
	
	@ManyToOne
	@JoinColumn(name = "Empresa_ID")
	private EmpresaCliente empresaCliente;
	
	@ManyToOne
	@JoinColumn(name = "Cliente_ID")
	private Cliente cliente;
	
	public Agendamento(){
		
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public Date getDataCriada() {
		return dataCriada;
	}

	public void setDataCriada(Date dataCriada) {
		this.dataCriada = dataCriada;
	}

	public Date getDataAgendada() {
		return dataAgendada;
	}

	public void setDataAgendada(Date dataAgendada) {
		this.dataAgendada = dataAgendada;
	}

	public boolean isCancelado() {
		return cancelado;
	}

	public void setCancelado(boolean cancelado) {
		this.cancelado = cancelado;
	}

	public String getMotivoCancelado() {
		return motivoCancelado;
	}

	public void setMotivoCancelado(String motivoCancelado) {
		this.motivoCancelado = motivoCancelado;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public EmpresaCliente getEmpresaCliente() {
		return empresaCliente;
	}

	public void setEmpresaCliente(EmpresaCliente empresaCliente) {
		this.empresaCliente = empresaCliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public long getId() {
		return id;
	}
	
	

}
