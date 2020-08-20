package br.com.mmw.Agendamento.Controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.mmw.Agendamento.Manager.AvisoManager;
import br.com.mmw.Agendamento.Manager.UsuarioManager;
import br.com.mmw.Agendamento.Model.Aviso;
import br.com.mmw.Agendamento.Model.Funcionario;
import br.com.mmw.Agendamento.Model.Usuario;

@ManagedBean
@SessionScoped
public class AtivaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String usuarionome;
	
	private String qntdAvisos;

	public String getUsuarionome() {
		return usuarionome;
	}

	public void setUsuarionome(String usuarionome) {
		this.usuarionome = usuarionome;
	}

	public String getQntdAvisos() {
		return qntdAvisos;
	}

	public void setQntdAvisos(String qntdAvisos) {
		this.qntdAvisos = qntdAvisos;
	}

	public void Buscar(){
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		String str = external.getRemoteUser();
		UsuarioManager um = new UsuarioManager();
		Usuario u = um.BuscarUmUsuarioPorLogin(str);
		Funcionario f = u.getFuncionario();
		BuscarQntdAvisos(f);
		String username = u.getFuncionario().getNome();
		String singlename[] = username.split(" ");
		usuarionome = singlename[0];	
	}
	
	public void BuscarQntdAvisos(Funcionario f){
		AvisoManager am = new AvisoManager();
		Date hoje = new Date();
		List<Aviso> avisos = am.BuscarAvisoDoDia(f.getEmpresaCliente().getId(), hoje);
		List<Aviso> avisosnaofinalizados = new ArrayList<Aviso>();
		for(Aviso a : avisos){
			if(a.isFinalizado() == false){
				avisosnaofinalizados.add(a);
			}
		}
		int qntd = avisosnaofinalizados.size();
		qntdAvisos = String.valueOf(qntd);
	}

}
