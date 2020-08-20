package br.com.mmw.Agendamento.Controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.mmw.Agendamento.Manager.AvisoManager;
import br.com.mmw.Agendamento.Manager.EmpresaClienteManager;
import br.com.mmw.Agendamento.Model.Aviso;
import br.com.mmw.Agendamento.Model.EmpresaCliente;
import br.com.mmw.Agendamento.Util.FacesMessages;

@ManagedBean
@ViewScoped
public class AvisoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Aviso aviso;
	
	private Aviso avisoSelecionado;
	
	private List<Aviso> avisos;
	
	private EmpresaCliente clienteparabuscar;
	
	private AvisoManager am = new AvisoManager();
	
	private FacesMessages messages = new FacesMessages();
	
	
	public Aviso getAviso() {
		return aviso;
	}

	public void setAviso(Aviso aviso) {
		this.aviso = aviso;
	}

	public Aviso getAvisoSelecionado() {
		return avisoSelecionado;
	}

	public void setAvisoSelecionado(Aviso avisoSelecionado) {
		this.avisoSelecionado = avisoSelecionado;
	}

	public List<Aviso> getAvisos() {
		return avisos;
	}

	public void setAvisos(List<Aviso> avisos) {
		this.avisos = avisos;
	}

	public EmpresaCliente getClienteparabuscar() {
		return clienteparabuscar;
	}

	public void setClienteparabuscar(EmpresaCliente clienteparabuscar) {
		this.clienteparabuscar = clienteparabuscar;
	}

	/**
	 * Metodos
	 */
	
	public void Buscar(){
		try{
			avisos = new ArrayList<Aviso>();
			avisos = am.BuscarTodosAvisosImportantes(clienteparabuscar.getId());
			if(avisos.isEmpty()){
				messages.error("Não Foram Encontrados Avisos para Este Cliente");
			}else{
				messages.info("Avisos Encontrados com Sucesso!");
			}
			
			clienteparabuscar = null;
			
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao Buscar os Avisos do Cliente");
		}
	}
	
	public void AvisoEmEdicao(){
		aviso = new Aviso();
	}
	
	public void Salvar(){
		try{
			Aviso a = aviso;
			am.CadastrarAvisoImportante(aviso);
			avisos = new ArrayList<Aviso>();
			avisos = am.BuscarTodosAvisosImportantes(a.getEmpresaCliente().getId());
			messages.info("Aviso Cadastrado com Sucesso!");

		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao Buscar os Avisos do Cliente");
		}
	}
	
	public void Atualizar(){
		try{
			Aviso a = avisoSelecionado;
			am.AtualizarAviso(avisoSelecionado);
			avisos = new ArrayList<Aviso>();
			avisos = am.BuscarTodosAvisosImportantes(a.getEmpresaCliente().getId());
			messages.info("Aviso Atualizado com Sucesso!");

		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao Buscar os Avisos do Cliente");
		}
	}
	
	public void Remover(){
		try{
			long id = avisoSelecionado.getEmpresaCliente().getId();
			am.RemoverAviso(avisoSelecionado.getId());
			avisos = new ArrayList<Aviso>();
			avisos = am.BuscarTodosAvisosImportantes(id);
			messages.info("Aviso Excluido com Sucesso!");
			
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao Buscar os Avisos do Cliente");
		}
	}
	

	public List<EmpresaCliente> CompletarNomeEmpresaCliente(String query){
		
		EmpresaClienteManager ecm = new EmpresaClienteManager();
		List<EmpresaCliente> todosClientes = ecm.BuscarTodasEmpresas();
		List<EmpresaCliente> filtros = new ArrayList<EmpresaCliente>();
		
		for(int i = 0; i<todosClientes.size(); i++){
			EmpresaCliente c = todosClientes.get(i);
			if(c.getNome().toLowerCase().startsWith(query.toLowerCase())){
				filtros.add(c);
			}
		}
		
		return filtros;
	}


}
