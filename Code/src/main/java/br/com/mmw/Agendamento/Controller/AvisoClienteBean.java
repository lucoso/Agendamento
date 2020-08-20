package br.com.mmw.Agendamento.Controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.mmw.Agendamento.Manager.AvisoManager;
import br.com.mmw.Agendamento.Manager.UsuarioManager;
import br.com.mmw.Agendamento.Model.Aviso;
import br.com.mmw.Agendamento.Model.Funcionario;
import br.com.mmw.Agendamento.Model.Usuario;
import br.com.mmw.Agendamento.Util.FacesMessages;

@ManagedBean
@ViewScoped
public class AvisoClienteBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Aviso avisoSelecionado;
	
	private Aviso avisoParaFinalizar;
	
	private LazyDataModel<Aviso> avisos;
	
	private Date datainicial;
	
	private Date datafinal;
	
	private boolean buscouporperiodo = false;
	
	private String legenda;
	
	private long empresaid;
	
	private AvisoManager am = new AvisoManager();
	
	private FacesMessages messages = new FacesMessages();
	
	
	public Aviso getAvisoSelecionado() {
		return avisoSelecionado;
	}

	public void setAvisoSelecionado(Aviso avisoSelecionado) {
		this.avisoSelecionado = avisoSelecionado;
	}

	public String getLegenda() {
		return legenda;
	}

	public void setLegenda(String legenda) {
		this.legenda = legenda;
	}

	public boolean isBuscouporperiodo() {
		return buscouporperiodo;
	}

	public void setBuscouporperiodo(boolean buscouporperiodo) {
		this.buscouporperiodo = buscouporperiodo;
	}

	public Date getDatainicial() {
		return datainicial;
	}

	public void setDatainicial(Date datainicial) {
		this.datainicial = datainicial;
	}

	public Date getDatafinal() {
		return datafinal;
	}

	public void setDatafinal(Date datafinal) {
		this.datafinal = datafinal;
	}

	public long getEmpresaid() {
		return empresaid;
	}

	public void setEmpresaid(long empresaid) {
		this.empresaid = empresaid;
	}

	public Aviso getAvisoParaFinalizar() {
		return avisoParaFinalizar;
	}

	public void setAvisoParaFinalizar(Aviso avisoParaFinalizar) {
		this.avisoParaFinalizar = avisoParaFinalizar;
	}

	public LazyDataModel<Aviso> getAvisos() {
		return avisos;
	}

	public void setAvisos(LazyDataModel<Aviso> avisos) {
		this.avisos = avisos;
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
	
	public void Limpar(){
		datainicial = null;
		datafinal = null;
	}
	
	public void BuscarAvisos(){
		
		avisos = new LazyDataModel<Aviso>(){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			List<Aviso> warnings;
			
			public List<Aviso> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, Object> filters){
				
				if(buscouporperiodo == true){
					warnings = am.BuscarAvisosPorPeriodoLazy(empresaid, datainicial, datafinal, first, pageSize);
					
					setRowCount(am.QuantidadeBuscarAvisosPorPeriodo(empresaid, datainicial, datafinal));
				}else{
					Date hoje = new Date();
					Calendar cal = Calendar.getInstance();
					cal.setTime(hoje);
					
					cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - 7);
					Date passado = cal.getTime();
					
					legenda = "Avisos da Semana";
					
					warnings = am.BuscarAvisosPorPeriodoLazy(empresaid, passado, hoje, first, pageSize);
					
					setRowCount(am.QuantidadeBuscarAvisosPorPeriodo(empresaid, passado, hoje));
				}
				
				return warnings;
				
			}

			@Override
			public Object getRowKey(Aviso aviso) {
				return aviso.getId();
			}

			@Override
			public Aviso getRowData(String avisoid) {
				
				Long id = Long.valueOf(avisoid);
				
				for(Aviso a : warnings ){
					if(id.equals(a.getId())){
						return a;
					}
				}
				
				return null;
			}	
		};
		
	}
	
	public void BuscarAvisosPorPeriodo(){
		buscouporperiodo = true;
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String inicio = formato.format(datainicial);
		String fim = formato.format(datafinal);
		legenda = "Avisos no Período: De " + inicio + " Até " + fim; 
	}
	
	public void Atualizar(){
		try{
			am.AtualizarAviso(avisoSelecionado);
			BuscarAvisos();
			avisoSelecionado = null;
			messages.info("Aviso Atualizado Com Sucesso!");
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao atualizar o aviso");
		}
	}
	
	public void Remover(){
		try{
			am.RemoverAviso(avisoSelecionado.getId());
			BuscarAvisos();
			avisoSelecionado = null;
			messages.info("Aviso Excluido Com Sucesso!");
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao excluir o aviso");
		}
	}
	
	public void Finalizar(){
		try{
			avisoParaFinalizar.setFinalizado(true);
			am.AtualizarAviso(avisoParaFinalizar);
			BuscarAvisos();
			avisoParaFinalizar = null;
			messages.info("Aviso Finalizado Com Sucesso!");
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao Finalizar o Aviso");
		}
	}

}
