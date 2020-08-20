package br.com.mmw.Agendamento.Controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.mmw.Agendamento.Manager.FuncionarioManager;
import br.com.mmw.Agendamento.Manager.UsuarioManager;
import br.com.mmw.Agendamento.Model.Funcionario;
import br.com.mmw.Agendamento.Model.Usuario;
import br.com.mmw.Agendamento.Util.FacesMessages;

@ManagedBean
@ViewScoped
public class ProfileBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Funcionario funcionario;
	
	private Funcionario funcionarioParaAtualizar;
	
	private Usuario usuario;
	
	private Usuario usuarioParaAtualizar;
	
	private String novasenha;
	
	private String senhaatual;
	
	private String datanascimento;
	
	private String mensagem;
	
	private boolean correto = false;
	
	private boolean modificou = false;
	
	private boolean mudoulogin = false;
	
	private FuncionarioManager fm = new FuncionarioManager();
	
	private UsuarioManager um = new UsuarioManager();
	
	private FacesMessages messages = new FacesMessages();
	
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public boolean isMudoulogin() {
		return mudoulogin;
	}

	public void setMudoulogin(boolean mudoulogin) {
		this.mudoulogin = mudoulogin;
	}

	public String getDatanascimento() {
		return datanascimento;
	}

	public void setDatanascimento(String datanascimento) {
		this.datanascimento = datanascimento;
	}

	public Funcionario getFuncionarioParaAtualizar() {
		return funcionarioParaAtualizar;
	}

	public void setFuncionarioParaAtualizar(Funcionario funcionarioParaAtualizar) {
		this.funcionarioParaAtualizar = funcionarioParaAtualizar;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioParaAtualizar() {
		return usuarioParaAtualizar;
	}

	public void setUsuarioParaAtualizar(Usuario usuarioParaAtualizar) {
		this.usuarioParaAtualizar = usuarioParaAtualizar;
	}

	public String getNovasenha() {
		return novasenha;
	}

	public void setNovasenha(String novasenha) {
		this.novasenha = novasenha;
	}

	public String getSenhaatual() {
		return senhaatual;
	}

	public void setSenhaatual(String senhaatual) {
		this.senhaatual = senhaatual;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public boolean isCorreto() {
		return correto;
	}

	public void setCorreto(boolean correto) {
		this.correto = correto;
	}

	public boolean isModificou() {
		return modificou;
	}

	public void setModificou(boolean modificou) {
		this.modificou = modificou;
	}

	/**
	 * Metodos
	 */
	
	public void Inicializar(){
		String str = SecurityContextHolder.getContext().getAuthentication().getName();
		UsuarioManager um = new UsuarioManager();
		usuario  = um.BuscarUmUsuarioPorLogin(str);
		funcionario = usuario.getFuncionario();
		Date data = funcionario.getDatanasc();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		datanascimento = formato.format(data);
	}
	
	public void BuscarDadosPessoais(){
		funcionarioParaAtualizar = funcionario;
	}
	
	public void BuscarDadosUsuario(){
		usuarioParaAtualizar = usuario;
		mensagem = null;
		senhaatual = null;
	}
	
	public void AtualizarFuncionario(){
		try{
			fm.AtualizarFuncionario(funcionarioParaAtualizar);
			funcionarioParaAtualizar = null;
			Inicializar();
			messages.info("Dados Pessoais Atualizados com Sucesso!");
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao Atualizar os Dados Pessoais");
		}
	}
	
	public void VerificarSenhaAtual(){
		String senhaantiga = usuarioParaAtualizar.getSenha();
		if(senhaantiga.equals(senhaatual)){
			correto = false;
			modificou = true;
			mensagem = "";
			RequestContext.getCurrentInstance().update("profile-frm:msg-senha");
		}else{
			correto = true;
			senhaatual = null;
			mensagem = "Atenção! A Senha Atual Esta Errada! Digite Novamente....";
			RequestContext.getCurrentInstance().update(Arrays.asList("profile-frm:msg-senha", "profile-frm:senhaatual"));
		}
	}
	
	public void MudouLogin(){
		mudoulogin = true;
	}
	
	public void AtualizarUsuario(){
		try{
			if(modificou == true){
				usuarioParaAtualizar.setSenha(novasenha);
			}
			um.AtualizarUsuario(usuarioParaAtualizar);
			usuarioParaAtualizar = null;
			if(mudoulogin == true){
				messages.info("Dados do Usuario Atualizados com Sucesso!");
				messages.info("Como o Nome de Usuario foi Modificado é Necessário Sair e Entrar no Sistema Novamente!");
			}else{
				messages.info("Dados do Usuario Atualizados com Sucesso!");
			}
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao Atualizar os Dados Pessoais");
		}
	}

}
