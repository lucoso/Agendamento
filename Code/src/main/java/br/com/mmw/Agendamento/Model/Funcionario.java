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
import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Funcionario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	@Column(name="Funcionario_Nome", nullable=false)
	private String nome;
	
	@NotNull
	@Column(name="Funcionario_DataNascimento", nullable=false)
	@Temporal(value=TemporalType.DATE)
	private Date datanasc;
	
	@NotEmpty
	@CPF
	@Column(name="Funcionario_CPF", nullable=false)
	private String cpf;
	
	@Column(name="Funcionario_Telefone", nullable=true)
	private String tel;
	
	@Column(name="Funcionario_Celular", nullable=true)
	private String cel;
	
	@Column(name="Funcionario_DataAdmissao", nullable=true)
	@Temporal(value=TemporalType.DATE)
	private Date dataAdmissao;
	
	@Column(name="Funcionario_Salario", scale=2, nullable=true)
	private BigDecimal salario;
	
	@ManyToOne
	@JoinColumn(name="Empresa_ID")
	private EmpresaCliente empresaCliente;
	
	public Funcionario(){
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public EmpresaCliente getEmpresaCliente() {
		return empresaCliente;
	}

	public void setEmpresaCliente(EmpresaCliente empresaCliente) {
		this.empresaCliente = empresaCliente;
	}

	public Date getDatanasc() {
		return datanasc;
	}

	public void setDatanasc(Date datanasc) {
		this.datanasc = datanasc;
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
