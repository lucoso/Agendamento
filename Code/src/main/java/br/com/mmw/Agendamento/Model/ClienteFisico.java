package br.com.mmw.Agendamento.Model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.br.CPF;

@Entity
public class ClienteFisico extends Cliente implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@CPF
	@Column(name="Cliente_CPF", length=15, nullable=true)
	private String cpf;
	
	@Column(name="Cliente_DataNasc", nullable=true)
	@Temporal(value=TemporalType.DATE)
	private Date datanasc;
	
	public ClienteFisico(){
		
	}

	public ClienteFisico(long id, String nome, String tel, String cel, String email, Endereco endereco,
			EmpresaCliente empresaCliente, String cpf, Date datanasc) {
		super(id, nome, tel, cel, email, endereco, empresaCliente);
		this.cpf = cpf;
		this.datanasc = datanasc;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDatanasc() {
		return datanasc;
	}

	public void setDatanasc(Date datanasc) {
		this.datanasc = datanasc;
	}
	
	

}
