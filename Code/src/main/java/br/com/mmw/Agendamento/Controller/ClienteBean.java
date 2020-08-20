package br.com.mmw.Agendamento.Controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.mmw.Agendamento.Manager.ClienteManager;
import br.com.mmw.Agendamento.Manager.UsuarioManager;
import br.com.mmw.Agendamento.Model.Cliente;
import br.com.mmw.Agendamento.Model.ClienteFisico;
import br.com.mmw.Agendamento.Model.ClienteJuridico;
import br.com.mmw.Agendamento.Model.Funcionario;
import br.com.mmw.Agendamento.Model.Usuario;
import br.com.mmw.Agendamento.Util.FacesMessages;

@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Cliente clienteSelecionado;
	
	private ClienteFisico clienteFisicoSelecionado;
	
	private ClienteJuridico clienteJuridicoSelecionado;
	
	private LazyDataModel<ClienteFisico> clientesf;
	
	private LazyDataModel<ClienteJuridico> clientesj;
	
	private List<String> ufs;
	
	private List<String> paises;
	
	private long empresaid;
	
	private ClienteManager cm = new ClienteManager();
	
	private FacesMessages messages = new FacesMessages();
	
	
	public long getEmpresaid() {
		return empresaid;
	}

	public void setEmpresaid(long empresaid) {
		this.empresaid = empresaid;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
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

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public ClienteFisico getClienteFisicoSelecionado() {
		return clienteFisicoSelecionado;
	}

	public void setClienteFisicoSelecionado(ClienteFisico clienteFisicoSelecionado) {
		this.clienteFisicoSelecionado = clienteFisicoSelecionado;
	}

	public ClienteJuridico getClienteJuridicoSelecionado() {
		return clienteJuridicoSelecionado;
	}

	public void setClienteJuridicoSelecionado(ClienteJuridico clienteJuridicoSelecionado) {
		this.clienteJuridicoSelecionado = clienteJuridicoSelecionado;
	}

	public LazyDataModel<ClienteFisico> getClientesf() {
		return clientesf;
	}

	public void setClientesf(LazyDataModel<ClienteFisico> clientesf) {
		this.clientesf = clientesf;
	}

	public LazyDataModel<ClienteJuridico> getClientesj() {
		return clientesj;
	}

	public void setClientesj(LazyDataModel<ClienteJuridico> clientesj) {
		this.clientesj = clientesj;
	}

	/**
	 * Metodos
	 */
	
	public void BuscarUsuario(){
		String str = SecurityContextHolder.getContext().getAuthentication().getName();
		UsuarioManager um = new UsuarioManager();
		Usuario u = um.BuscarUmUsuarioPorLogin(str);
		Funcionario funcionario = u.getFuncionario();
		empresaid = funcionario.getEmpresaCliente().getId();
	}
	
	public void Inicializar(){
		BuscarClientesFisicos();
		BuscarClientesJuridicos();
		BuscarUsuario();
	}
	
	
	public void BuscarClientesFisicos(){
		
		clientesf = new LazyDataModel<ClienteFisico>(){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			List<ClienteFisico> companys;
			
			public List<ClienteFisico> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, Object> filters){
				
				companys = cm.BuscarTodosClientesFisicosLazy(empresaid, first, pageSize, filters);
				
				setRowCount(cm.ContarTodosClientesFisicos(empresaid));
				
				return companys;
				
			}

			@Override
			public Object getRowKey(ClienteFisico cliente) {
				return cliente.getId();
			}

			@Override
			public ClienteFisico getRowData(String clienteid) {
				
				Long id = Long.valueOf(clienteid);
				
				for(ClienteFisico c : companys ){
					if(id.equals(c.getId())){
						return c;
					}
				}
				
				return null;
			}	
		};
		
	}
	
	public void BuscarClientesJuridicos(){
		
		clientesj = new LazyDataModel<ClienteJuridico>(){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			List<ClienteJuridico> companys;
			
			public List<ClienteJuridico> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, Object> filters){
				
				companys = cm.BuscarTodosClientesJuridicosLazy(empresaid, first, pageSize, filters);
				
				setRowCount(cm.ContarTodosClientesJuridicos(empresaid));
				
				return companys;
				
			}

			@Override
			public Object getRowKey(ClienteJuridico cliente) {
				return cliente.getId();
			}

			@Override
			public ClienteJuridico getRowData(String clienteid) {
				
				Long id = Long.valueOf(clienteid);
				
				for(ClienteJuridico c : companys ){
					if(id.equals(c.getId())){
						return c;
					}
				}
				
				return null;
			}	
		};
		
	}
	
	public void AtualizarClienteFisico(){
		try{
			cm.AtualizarCliente(clienteFisicoSelecionado);
			BuscarClientesFisicos();
			clienteFisicoSelecionado = null;
			messages.info("Cliente Fisico Atualizado com Sucesso!");
		}catch(Exception ex){
			ex.printStackTrace();
			messages.error("Erro ao Atualizar os Dados do Cliente Fisico");
		}
	}
	
	public void AtualizarClienteJuridico(){
		try{
			cm.AtualizarCliente(clienteJuridicoSelecionado);
			BuscarClientesJuridicos();
			clienteJuridicoSelecionado = null;
			messages.info("Cliente Juridico Atualizado com Sucesso!");
		}catch(Exception ex){
			ex.printStackTrace();
			messages.error("Erro ao Atualizar os Dados do Cliente Juridico");
		}
	}
	
	public void Mostrar(){
		MostrarUFs();
		MostrarPaises();
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
