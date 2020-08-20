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
public class Aviso implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	@Column(name="Aviso_Mensagem", nullable=false)
	private String msg;
	
	@NotNull
	@Column(name="Aviso_DataCriado", nullable=false)
	@Temporal(value=TemporalType.DATE)
	private Date dataCriado;
	
	@Column(name="Aviso_Data", nullable=true)
	@Temporal(value=TemporalType.DATE)
	private Date data;
	
	@NotNull
	@Column(name="Aviso_Importante", nullable=false)
	private boolean importante;
	
	@Column(name="Aviso_Finalizado", nullable=true)
	private boolean finalizado;
	
	@ManyToOne
	@JoinColumn(name = "Empresa_ID")
	private EmpresaCliente empresaCliente;
	
	public Aviso(){
		
	}

	public String getMsg() {
		return msg;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public boolean isImportante() {
		return importante;
	}

	public void setImportante(boolean importante) {
		this.importante = importante;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getDataCriado() {
		return dataCriado;
	}

	public void setDataCriado(Date dataCriado) {
		this.dataCriado = dataCriado;
	}

	public EmpresaCliente getEmpresaCliente() {
		return empresaCliente;
	}

	public void setEmpresaCliente(EmpresaCliente empresaCliente) {
		this.empresaCliente = empresaCliente;
	}

	public long getId() {
		return id;
	}
	
	

}
