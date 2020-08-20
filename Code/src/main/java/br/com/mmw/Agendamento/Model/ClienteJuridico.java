package br.com.mmw.Agendamento.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.br.CNPJ;

@Entity
public class ClienteJuridico extends Cliente implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@CNPJ
	@Column(name="Cliente_CNPJ", length=18, nullable=true)
	private String cnpj;

	public ClienteJuridico(){
		
	}
	
	public ClienteJuridico(long id, String nome, String tel, String cel, String email, Endereco endereco,
			EmpresaCliente empresaCliente, String cnpj) {
		super(id, nome, tel, cel, email, endereco, empresaCliente);
		this.cnpj = cnpj;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	

}
