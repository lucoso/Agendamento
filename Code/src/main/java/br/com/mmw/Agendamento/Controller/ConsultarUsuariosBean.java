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
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.mmw.Agendamento.Manager.FuncionarioManager;
import br.com.mmw.Agendamento.Manager.UsuarioManager;
import br.com.mmw.Agendamento.Model.Funcionario;
import br.com.mmw.Agendamento.Model.Usuario;
import br.com.mmw.Agendamento.Util.FacesMessages;

@ManagedBean(name = "consultaUsBean")
@ViewScoped
public class ConsultarUsuariosBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Usuario user;
	
	private Usuario usuarioSelecionado;
	
	private LazyDataModel<Usuario> usuarios;
	
	private UsuarioManager um = new UsuarioManager();
	
	private FacesMessages messages = new FacesMessages();

	private long empresaid;
	
	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public long getEmpresaid() {
		return empresaid;
	}

	public void setEmpresaid(long empresaid) {
		this.empresaid = empresaid;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public LazyDataModel<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(LazyDataModel<Usuario> usuarios) {
		this.usuarios = usuarios;
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
	
	public void BuscarTodos(){
		BuscarUsuario();
		BuscarFuncionarios();
	}
	
	public void BuscarFuncionarios(){
		
		usuarios = new LazyDataModel<Usuario>(){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			List<Usuario> lista;
			
			public List<Usuario> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, Object> filters){
				
				lista = um.BuscarTodosUsuariosLazy(empresaid, first, pageSize, filters);
				
				setRowCount(um.ContarTodosUsuarios(empresaid));
				
				return lista;
				
			}

			@Override
			public Object getRowKey(Usuario usuario) {
				return usuario.getId();
			}

			@Override
			public Usuario getRowData(String usuarioid) {
				
				Long id = Long.valueOf(usuarioid);
				
				for(Usuario u : lista ){
					if(id.equals(u.getId())){
						return u;
					}
				}
				
				return null;
			}	
		};
		
	}
	
	public void UsuarioEmEdicao(){
		user = new Usuario();
	}
	
	public void Salvar(){
		try{
			um.AtualizarUsuario(user);
			usuarioSelecionado = null;
			BuscarTodos();
			RequestContext.getCurrentInstance().update(Arrays.asList("usuario-frm:table-usuarios", "usuario-frm:toolbar"));
			messages.info("Dados do Usuario Salvos com Sucesso!");
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao Salvar os Dados do Usuario");
		}
	}
	
	public void Remover(){
		try{
			um.RemoverUsuario(usuarioSelecionado.getId());
			usuarioSelecionado = null;
			BuscarTodos();
			RequestContext.getCurrentInstance().update(Arrays.asList("usuario-frm:table-usuarios", "usuario-frm:toolbar"));
			messages.info("Usuario Removido com Sucesso!");
		}catch(Exception ex){
			ex.printStackTrace();
			messages.error("Erro ao Remover o Usuario");
		}
	}
	
	public List<Funcionario> CompletarNomeFuncionario(String query){
		
		FuncionarioManager fm = new FuncionarioManager();
		List<Funcionario> todasFuncionarios = fm.BuscarTodosFuncionarios(empresaid);
		List<Funcionario> filtros = new ArrayList<Funcionario>();
			
		for(int i = 0; i<todasFuncionarios.size(); i++){
			Funcionario f = todasFuncionarios.get(i);
				if(f.getNome().toLowerCase().startsWith(query.toLowerCase())){
					filtros.add(f);
				}
			}
			
			return filtros;		
		}

}
