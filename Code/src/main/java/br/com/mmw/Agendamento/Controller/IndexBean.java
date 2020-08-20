package br.com.mmw.Agendamento.Controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import br.com.mmw.Agendamento.Manager.FuncionarioManager;
import br.com.mmw.Agendamento.Manager.UsuarioManager;
import br.com.mmw.Agendamento.Model.Funcionario;
import br.com.mmw.Agendamento.Model.Papel;
import br.com.mmw.Agendamento.Model.Usuario;
import br.com.mmw.Agendamento.Util.FacesMessages;

@ManagedBean
@ViewScoped
public class IndexBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer progress;
	
	private String username;
	
	private String senha;
	
	private String cpf;
	
	private boolean b = false;
	
	private FacesMessages messages = new FacesMessages();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isB() {
		return b;
	}

	public void setB(boolean b) {
		this.b = b;
	}

	public String getSenha() {
		return senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getProgress() {
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}
	
	public void BuscaInicial(){
		try{
			UsuarioManager um = new UsuarioManager();
			int i = um.BuscarUsuarioParaInstalacao();
			if(i == 0){
				RequestContext.getCurrentInstance().execute("PF('progresso-Dialog').show();");
			}
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Erro ao fazer busca inicial");
		}
	}
	
	public void AoCompletar(){
		RequestContext.getCurrentInstance().execute("PF('progresso-Dialog').hide();");
		messages.info("Instalação Efetuada com Sucesso!");
	}
	
	public void ExecutarProgresso(){
		for(int i=0; i<100; i++){
			progress = i;
			try{
				Thread.sleep(200);
			}catch(InterruptedException e){
				
			}
		}
		CadastrarUsuarioPadrao();
		progress = 100;
	}
	
	public void CadastrarUsuarioPadrao(){
		try{
			Usuario u = new Usuario();
			UsuarioManager um = new UsuarioManager();
			
			u.setAtivo(true);
			u.setLogin("administrador geral");
			u.setPapel(Papel.AdministradorGeral);
			u.setSenha("B1928374655");
			
			um.CadastrarUsuario(u);
			
			
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Erro ao Criar Usuario Padrao");
		}
	}
	
	public void Limpar(){
		username = null;
	}
	
	public void BuscarFuncionarioPorCPF(){
		try{
			FuncionarioManager fm = new FuncionarioManager();
			List<Funcionario> funcs = fm.BuscarFuncionarioPorCPF(cpf);
			if(funcs.isEmpty()){
				messages.error("Atenção este CPF não esta Cadastrado no Sistema! Não é Possível Recuperar a Senha!");
				RequestContext.getCurrentInstance().update("dialogs:msg-senha");
			}else{
				b = true;
				RequestContext.getCurrentInstance().update("dialogs:msg-senha");
				messages.info("CPF Encontrado!");
			}
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao Buscar o CPF");
		}
	}
	
	public void Recuperar(){
		try{
			UsuarioManager um = new UsuarioManager();
			List<Usuario> usuarios = um.BuscarUsuarioPorLoginParaRecuperarSenha(username);
			if(usuarios.isEmpty()){
				Limpar();
				messages.error("Atenção! Este e-mail NÃO esta cadastrado no sistema. Digite o e-mail novamente....");
				RequestContext.getCurrentInstance().update("dialogs:msg-senha");
				RequestContext.getCurrentInstance().update("dialogs:painel-recuperar");
			}else{
				for(Usuario u : usuarios){
					senha = u.getSenha();
				}
				messages.info("Senha Recuperada com Sucesso");
				RequestContext.getCurrentInstance().update("dialogs:msg-senha");
			}
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao recuperar a senha");
		}
	}
}
