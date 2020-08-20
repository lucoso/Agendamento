package br.com.mmw.Agendamento.Model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class EmpresaCliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	@Column(name="Empresa_Nome", nullable=false)
	private String nome;
	
	@Column(name="Empresa_CPF", length=18, nullable=true)
	private String cpf;
	
	@Column(name="Empresa_CNPJ", length=20, nullable=true)
	private String cnpj;
	
	@Column(name="Empresa_Contato", nullable=true)
	private String contato;
	
	@NotEmpty
	@Column(name="Empresa_Tipo", length=8, nullable=false)
	private String tipo;
	
	@Column(name="Empresa_Telefone", nullable=true)
	private String tel;
	
	@Column(name="Empresa_Celular", nullable=true)
	private String cel;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Endereco_ID")
	private Endereco endereco;
	
	public EmpresaCliente(){
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCel() {
		return cel;
	}

	public void setCel(String cel) {
		this.cel = cel;
	}

	public long getId() {
		return id;
	}
	
	

}
