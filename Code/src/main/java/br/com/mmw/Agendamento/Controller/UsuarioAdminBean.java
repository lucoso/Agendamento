package br.com.mmw.Agendamento.Controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import br.com.mmw.Agendamento.Manager.EmpresaClienteManager;
import br.com.mmw.Agendamento.Manager.FuncionarioManager;
import br.com.mmw.Agendamento.Manager.UsuarioManager;
import br.com.mmw.Agendamento.Model.EmpresaCliente;
import br.com.mmw.Agendamento.Model.Funcionario;
import br.com.mmw.Agendamento.Model.Usuario;
import br.com.mmw.Agendamento.Util.FacesMessages;

@ManagedBean
@ViewScoped
public class UsuarioAdminBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Usuario user;
	
	private Usuario usuarioSelecionado;
	
	private List<Usuario> usuarios;
	
	private EmpresaCliente empresaCliente;
	
	private UsuarioManager um = new UsuarioManager();
	
	private FacesMessages messages = new FacesMessages();
	
	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public EmpresaCliente getEmpresaCliente() {
		return empresaCliente;
	}

	public void setEmpresaCliente(EmpresaCliente empresaCliente) {
		this.empresaCliente = empresaCliente;
	}

	/**
	 * Metodos
	 */
	
	public void UsuarioEmEdicao(){
		user = new Usuario();
	}
	
	public void Salvar(){
		try{
			um.CadastrarUsuario(user);
			//RequestContext.getCurrentInstance().update(Arrays.asList("usuario-frm:table-usuarios", "usuario-frm:toolbar"));
			messages.info("Dados do Usuario Salvos com Sucesso!");
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao Salvar os Dados do Usuario");
		}
	}
	
	public void Atualizar(){
		try{
			um.AtualizarUsuario(usuarioSelecionado);
			usuarioSelecionado = null;
			RequestContext.getCurrentInstance().update(Arrays.asList("usuario-frm:table-usuarios", "usuario-frm:toolbar"));
			messages.info("Dados do Usuario Atualizados com Sucesso!");
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao Salvar os Dados do Usuario");
		}
	}
	
	public void Remover(){
		try{
			um.RemoverUsuario(usuarioSelecionado.getId());
			usuarioSelecionado = null;
			RequestContext.getCurrentInstance().update(Arrays.asList("usuario-frm:table-usuarios", "usuario-frm:toolbar"));
			messages.info("Usuario Removido com Sucesso!");
		}catch(Exception ex){
			ex.printStackTrace();
			messages.error("Erro ao Remover o Usuario");
		}
	}
	
	public void Buscar(){
		try{
			usuarios = new ArrayList<Usuario>();
			usuarios = um.BuscarTodosUsuarios(empresaCliente.getId());
			if(usuarios.isEmpty()){
				messages.error("Nenhum Usuario Encontrado para Este Cliente");
			}else{
				messages.info("Usuarios Encontrados com Sucesso!");
			}
			empresaCliente = null;
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao Buscar os Usuarios dos Funcionarios");
		}
	}
	
	public List<Funcionario> CompletarNomeFuncionario(String query){
		
		FuncionarioManager fm = new FuncionarioManager();
		List<Funcionario> todasFuncionarios = fm.BuscarTodosFuncionarios(empresaCliente.getId());
		List<Funcionario> filtros = new ArrayList<Funcionario>();
			
		for(int i = 0; i<todasFuncionarios.size(); i++){
			Funcionario f = todasFuncionarios.get(i);
				if(f.getNome().toLowerCase().startsWith(query.toLowerCase())){
					filtros.add(f);
				}
			}
			
			return filtros;		
		}
	
	public List<EmpresaCliente> CompletarNomeEmpresa(String query){
		
		EmpresaClienteManager ecm = new EmpresaClienteManager();
		List<EmpresaCliente> todasEmpresas = ecm.BuscarTodasEmpresas();
		List<EmpresaCliente> filtros = new ArrayList<EmpresaCliente>();
			
		for(int i = 0; i<todasEmpresas.size(); i++){
			EmpresaCliente e = todasEmpresas.get(i);
				if(e.getNome().toLowerCase().startsWith(query.toLowerCase())){
					filtros.add(e);
				}
			}
			
			return filtros;		
		}


}
