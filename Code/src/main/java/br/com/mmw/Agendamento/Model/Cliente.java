package br.com.mmw.Agendamento.Model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Cliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	@Column(name="Cliente_Nome", nullable=false)
	private String nome;
	
	@Column(name="Cliente_Telefone", nullable=true)
	private String tel;
	
	@Column(name="Cliente_Celular", nullable=true)
	private String cel;
	
	@Email
	@Column(name="Cliente_Email", nullable=true)
	private String email;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Endereco_ID")
	private Endereco endereco;
	
	@ManyToOne
	@JoinColumn(name = "Empresa_ID")
	private EmpresaCliente empresaCliente;

	public Cliente(long id, String nome, String tel, String cel, String email, Endereco endereco,
			EmpresaCliente empresaCliente) {
		super();
		this.id = id;
		this.nome = nome;
		this.tel = tel;
		this.cel = cel;
		this.email = email;
		this.endereco = endereco;
		this.empresaCliente = empresaCliente;
	}
	
	public Cliente(){
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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
