package br.com.mmw.Agendamento.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Endereco implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="Endereco_Rua", nullable=true)
	private String rua;
	
	@Column(name="Endereco_Numero", nullable=true)
	private int numero;
	
	@Column(name="Endereco_Complemento", nullable=true)
	private String complemento;
	
	@Column(name="Endereco_Bairro", nullable=true)
	private String bairro;
	
	@Column(name="Endereco_Cidade", nullable=true)
	private String cidade;
	
	@Column(name="Endereco_UF", nullable=true)
	private String uf;
	
	@Column(name="Endereco_CEP", nullable=true)
	private String cep;
	
	@Column(name="Endereco_Pais", nullable=true)
	private String pais;
	
	public Endereco(){
		
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public long getId() {
		return id;
	}
	
	

}
