package br.com.mmw.Agendamento.Controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.mmw.Agendamento.Manager.EmpresaClienteManager;
import br.com.mmw.Agendamento.Manager.FuncionarioManager;
import br.com.mmw.Agendamento.Model.EmpresaCliente;
import br.com.mmw.Agendamento.Model.Endereco;
import br.com.mmw.Agendamento.Model.Funcionario;
import br.com.mmw.Agendamento.Util.FacesMessages;

@ManagedBean
@ViewScoped
public class AdminBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EmpresaCliente cliente;
	
	private EmpresaCliente clienteSelecionado;
	
	private Funcionario funcionario;
	
	private LazyDataModel<EmpresaCliente> clientes;
	
	private String tipocliente;
	
	private List<String> ufs;
	
	private List<String> paises;
	
	private Endereco endereco;
	
	private EmpresaClienteManager ecm = new EmpresaClienteManager();
	
	private FacesMessages messages = new FacesMessages();
	
	public EmpresaCliente getCliente() {
		return cliente;
	}

	public void setCliente(EmpresaCliente cliente) {
		this.cliente = cliente;
	}

	public List<String> getUfs() {
		return ufs;
	}

	public void setUfs(List<String> ufs) {
		this.ufs = ufs;
	}

	public List<String> getPaises() {
		return paises;
	}

	public void setPaises(List<String> paises) {
		this.paises = paises;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public EmpresaCliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(EmpresaCliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public LazyDataModel<EmpresaCliente> getClientes() {
		return clientes;
	}

	public void setClientes(LazyDataModel<EmpresaCliente> clientes) {
		this.clientes = clientes;
	}

	public String getTipocliente() {
		return tipocliente;
	}

	public void setTipocliente(String tipocliente) {
		this.tipocliente = tipocliente;
	}

	/**
	 * Metodos
	 */
	
	public AdminBean(){
		
		clientes = new LazyDataModel<EmpresaCliente>(){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			List<EmpresaCliente> companys;
			
			public List<EmpresaCliente> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, Object> filters){
				
				companys = ecm.BuscarTodasEmpresasLazy(first, pageSize, filters);
				
				setRowCount(ecm.ContarTodasEmpresas());
				
				return companys;
				
			}

			@Override
			public Object getRowKey(EmpresaCliente empresa) {
				return empresa.getId();
			}

			@Override
			public EmpresaCliente getRowData(String empresaid) {
				
				Long id = Long.valueOf(empresaid);
				
				for(EmpresaCliente e : companys ){
					if(id.equals(e.getId())){
						return e;
					}
				}
				
				return null;
			}	
		};
		
	}

	public void ClienteEmEdicao(){
		cliente = new EmpresaCliente();
	}
	
	public void DefinirTipo(){
		String tipo = clienteSelecionado.getTipo();
		tipocliente = tipo;
	}
	
	public void Salvar(){
		try{
			List<EmpresaCliente> emps = null;
			
			if(clienteSelecionado == null){
				if(tipocliente.equals("Fisico")){
					emps = ecm.BuscarEmpresaPorCPFOuCNPJ(tipocliente, cliente.getCpf());
				}else{
					emps = ecm.BuscarEmpresaPorCPFOuCNPJ(tipocliente, cliente.getCnpj());
				}
				
				if(emps.isEmpty()){
					cliente.setTipo(tipocliente);
					ecm.CadastrarEmpresa(cliente);
					messages.info("Cliente Cadastrado com Sucesso!");
					tipocliente = null;
					RequestContext.getCurrentInstance().update("admin-frm:table-clientes");
				}else{
					messages.error("Atenção! Este Cliente ja esta Cadastrado no Sistema");
				}
			}else{
				
				ecm.AtualizarEmpresa(cliente);
				messages.info("Cliente Atualizado com Sucesso!");
				clienteSelecionado = null;
				tipocliente = null;
				RequestContext.getCurrentInstance().update(Arrays.asList("admin-frm:table-clientes", "admin-frm:toolbar"));
			}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao Salvar os Dados do Cliente");
		}
	}
	
	public void FuncionarioEmEdicao(){
		funcionario = new Funcionario();
	}
	
	public void CadastrarFuncionario(){
		try{
			FuncionarioManager fm = new FuncionarioManager();
			funcionario.setEmpresaCliente(clienteSelecionado);
			fm.CadastrarFuncionario(funcionario);
			clienteSelecionado = null;
			messages.info("Funcionario Cadastrado com Sucesso!");
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao cadastrar o funcionario");
		}
	}
	
	public void EnderecoEmEdicao(){
		try{
			Endereco end = clienteSelecionado.getEndereco();
			if(end == null){
				endereco = new Endereco();
			}else{
				endereco = end;
			}
			
			MostrarUFs();
			MostrarPaises();
			
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("erro ao buscar endereço do cliente");
		}
	}
	
	public void CadastrarEndereco(){
		try{
			clienteSelecionado.setEndereco(endereco);
			ecm.AtualizarEmpresa(clienteSelecionado);
			clienteSelecionado = null;
			messages.info("Endereço do Cliente Cadastrado com Sucesso!");
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("erro ao cadastrar o endereço do cliente");
		}
	}
	
	public void MostrarUFs(){
		ufs = new ArrayList<String>();
		ufs.add("AC");
		ufs.add("AL");
		ufs.add("AP");
		ufs.add("AM");
		ufs.add("BA");
		ufs.add("CE");
		ufs.add("DF");
		ufs.add("ES");
		ufs.add("GO");
		ufs.add("MA");
		ufs.add("MT");
		ufs.add("MS");
		ufs.add("MG");
		ufs.add("PR");
		ufs.add("PB");
		ufs.add("PA");
		ufs.add("PE");
		ufs.add("PI");
		ufs.add("RJ");
		ufs.add("RN");
		ufs.add("RS");
		ufs.add("RO");
		ufs.add("RR");
		ufs.add("SC");
		ufs.add("SE");
		ufs.add("SP");
		ufs.add("TO");
	}
	
	public void MostrarPaises(){
		paises = new ArrayList<String>();
		paises.add("Alemanha");
		paises.add("Argentina");
		paises.add("Bolívia");
		paises.add("Brasil");
		paises.add("Canadá");
		paises.add("Chile");
		paises.add("China");
		paises.add("Colômbia");
		paises.add("Costa Rica");
		paises.add("Equador");
		paises.add("Espanha");
		paises.add("Estados Unidos");
		paises.add("França");
		paises.add("Inglaterra");
		paises.add("Itália");
		paises.add("Japão");
		paises.add("México");
		paises.add("Panamá");
		paises.add("Paraguai");
		paises.add("Peru");
		paises.add("Portugal");
		paises.add("Turquia");
		paises.add("Uruguai");
		paises.add("Venezuela");
	}

}
