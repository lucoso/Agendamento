package br.com.mmw.Agendamento.Controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.mmw.Agendamento.Manager.FuncionarioManager;
import br.com.mmw.Agendamento.Manager.UsuarioManager;
import br.com.mmw.Agendamento.Model.EmpresaCliente;
import br.com.mmw.Agendamento.Model.Funcionario;
import br.com.mmw.Agendamento.Model.Usuario;
import br.com.mmw.Agendamento.Util.FacesMessages;

@ManagedBean
@ViewScoped
public class FuncionarioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Funcionario funcionario;
	
	private Funcionario funcionarioSelecionado;
	
	private LazyDataModel<Funcionario> funcionarios;
	
	private long empresaid;
	
	private EmpresaCliente empresaCliente;
	
	private FuncionarioManager fm = new FuncionarioManager();
	
	private FacesMessages messages = new FacesMessages();
	
	public long getEmpresaid() {
		return empresaid;
	}

	public void setEmpresaid(long empresaid) {
		this.empresaid = empresaid;
	}

	public EmpresaCliente getEmpresaCliente() {
		return empresaCliente;
	}

	public void setEmpresaCliente(EmpresaCliente empresaCliente) {
		this.empresaCliente = empresaCliente;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Funcionario getFuncionarioSelecionado() {
		return funcionarioSelecionado;
	}

	public void setFuncionarioSelecionado(Funcionario funcionarioSelecionado) {
		this.funcionarioSelecionado = funcionarioSelecionado;
	}

	public LazyDataModel<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(LazyDataModel<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
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
		empresaCliente = funcionario.getEmpresaCliente();
	}
	
	public FuncionarioBean(){
		
		funcionarios = new LazyDataModel<Funcionario>(){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			List<Funcionario> funcs;
			
			public List<Funcionario> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, Object> filters){
				
				funcs = fm.BuscarTodosFuncionariosLazy(empresaid, first, pageSize, filters);
				
				setRowCount(fm.ContarTodosFuncionarios(empresaid));
				
				return funcs;
				
			}

			@Override
			public Object getRowKey(Funcionario funcionario) {
				return funcionario.getId();
			}

			@Override
			public Funcionario getRowData(String funcionarioid) {
				
				Long id = Long.valueOf(funcionarioid);
				
				for(Funcionario f : funcs ){
					if(id.equals(f.getId())){
						return f;
					}
				}
				
				return null;
			}	
		};
		
	}
	
	public void FuncionarioEmEdicao(){
		funcionario = new Funcionario();
	}
	
	public void Salvar(){
		try{
			funcionario.setEmpresaCliente(empresaCliente);
			fm.CadastrarFuncionario(funcionario);
			messages.info("Funcionario Cadastrado com Sucesso!");
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao Salvar os Dados do Funcionario");
		}
	}
	
	public void Atualizar(){
		try{
			fm.AtualizarFuncionario(funcionarioSelecionado);
			funcionarioSelecionado = null;
			messages.info("Funcionario Atualizado com Sucesso!");
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao Salvar os Dados do Funcionario");
		}
	}

}
