package br.com.mmw.Agendamento.Controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.mmw.Agendamento.Manager.AgendamentoManager;
import br.com.mmw.Agendamento.Manager.AvisoManager;
import br.com.mmw.Agendamento.Manager.ClienteManager;
import br.com.mmw.Agendamento.Manager.FuncionarioManager;
import br.com.mmw.Agendamento.Manager.UsuarioManager;
import br.com.mmw.Agendamento.Model.Agendamento;
import br.com.mmw.Agendamento.Model.Aviso;
import br.com.mmw.Agendamento.Model.Cliente;
import br.com.mmw.Agendamento.Model.ClienteFisico;
import br.com.mmw.Agendamento.Model.ClienteJuridico;
import br.com.mmw.Agendamento.Model.EmpresaCliente;
import br.com.mmw.Agendamento.Model.Endereco;
import br.com.mmw.Agendamento.Model.Funcionario;
import br.com.mmw.Agendamento.Model.Usuario;
import br.com.mmw.Agendamento.Util.FacesMessages;

@ManagedBean
@ViewScoped
public class HomeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ClienteFisico clientefisico;
	
	private ClienteJuridico clientejuridico;
	
	private ClienteFisico clienteFisicoAg;
	
	private ClienteJuridico clienteJuridicoAg;
	
	private Cliente clienteparafiltro;
	
	private Funcionario funcionarioparafiltro;
	
	private String status = "Agendado";
	
	private String statusAgendamento = "Agendado";
	
	private LazyScheduleModel eventModel;
	
	private List<Agendamento> agendamentos;
	
	private Endereco endereco;
	
	private Agendamento agenda;
	
	private String tipocliente;
	
	private String motivocancelado;
	
	private Date datareagendar;
	
	private List<String> ufs;
	
	private List<String> paises;
	
	private boolean filtros;
	
	private Aviso aviso;
	
	private Aviso avisoParaFinalizar;
	
	private List<Aviso> avisos;
	
	private AvisoManager avm = new AvisoManager();
	
	private ClienteManager cm = new ClienteManager();
	
	private AgendamentoManager am = new AgendamentoManager();
	
	private FacesMessages messages = new FacesMessages();
	
	private long empresaid;
	
	private EmpresaCliente empresaCliente;
	
	private Funcionario funcionario;
	
	public ClienteFisico getClientefisico() {
		return clientefisico;
	}

	public void setClientefisico(ClienteFisico clientefisico) {
		this.clientefisico = clientefisico;
	}

	public Aviso getAvisoParaFinalizar() {
		return avisoParaFinalizar;
	}

	public void setAvisoParaFinalizar(Aviso avisoParaFinalizar) {
		this.avisoParaFinalizar = avisoParaFinalizar;
	}

	public List<Aviso> getAvisos() {
		return avisos;
	}

	public void setAvisos(List<Aviso> avisos) {
		this.avisos = avisos;
	}

	public Funcionario getFuncionarioparafiltro() {
		return funcionarioparafiltro;
	}

	public void setFuncionarioparafiltro(Funcionario funcionarioparafiltro) {
		this.funcionarioparafiltro = funcionarioparafiltro;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public EmpresaCliente getEmpresaCliente() {
		return empresaCliente;
	}

	public void setEmpresaCliente(EmpresaCliente empresaCliente) {
		this.empresaCliente = empresaCliente;
	}

	public long getEmpresaid() {
		return empresaid;
	}

	public void setEmpresaid(long empresaid) {
		this.empresaid = empresaid;
	}

	public Aviso getAviso() {
		return aviso;
	}

	public void setAviso(Aviso aviso) {
		this.aviso = aviso;
	}

	public ClienteJuridico getClientejuridico() {
		return clientejuridico;
	}

	public void setClientejuridico(ClienteJuridico clientejuridico) {
		this.clientejuridico = clientejuridico;
	}

	public ClienteFisico getClienteFisicoAg() {
		return clienteFisicoAg;
	}

	public void setClienteFisicoAg(ClienteFisico clienteFisicoAg) {
		this.clienteFisicoAg = clienteFisicoAg;
	}

	public ClienteJuridico getClienteJuridicoAg() {
		return clienteJuridicoAg;
	}

	public void setClienteJuridicoAg(ClienteJuridico clienteJuridicoAg) {
		this.clienteJuridicoAg = clienteJuridicoAg;
	}

	public Cliente getClienteparafiltro() {
		return clienteparafiltro;
	}

	public void setClienteparafiltro(Cliente clienteparafiltro) {
		this.clienteparafiltro = clienteparafiltro;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LazyScheduleModel getEventModel() {
		return eventModel;
	}

	public void setEventModel(LazyScheduleModel eventModel) {
		this.eventModel = eventModel;
	}

	public String getStatusAgendamento() {
		return statusAgendamento;
	}

	public void setStatusAgendamento(String statusAgendamento) {
		this.statusAgendamento = statusAgendamento;
	}

	public List<Agendamento> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(List<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Agendamento getAgenda() {
		return agenda;
	}

	public void setAgenda(Agendamento agenda) {
		this.agenda = agenda;
	}

	public String getTipocliente() {
		return tipocliente;
	}

	public void setTipocliente(String tipocliente) {
		this.tipocliente = tipocliente;
	}

	public String getMotivocancelado() {
		return motivocancelado;
	}

	public void setMotivocancelado(String motivocancelado) {
		this.motivocancelado = motivocancelado;
	}

	public Date getDatareagendar() {
		return datareagendar;
	}

	public void setDatareagendar(Date datareagendar) {
		this.datareagendar = datareagendar;
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

	public boolean isFiltros() {
		return filtros;
	}

	public void setFiltros(boolean filtros) {
		this.filtros = filtros;
	}

	/**
	 * Metodos
	 */
	
	public void BuscarUsuario(){
		String str = SecurityContextHolder.getContext().getAuthentication().getName();
		UsuarioManager um = new UsuarioManager();
		Usuario u = um.BuscarUmUsuarioPorLogin(str);
		funcionario = u.getFuncionario();
		empresaid = funcionario.getEmpresaCliente().getId();
		empresaCliente = funcionario.getEmpresaCliente();
	}
	
	public void FiltrarEmEdicao(){
		filtros = true;
		Inicializar();
		RequestContext.getCurrentInstance().update("home-frm:schedule");
		RequestContext.getCurrentInstance().update("home-frm:panel-filtros");
	}
	
	public void Limpar(){
		clienteparafiltro = null;
		funcionarioparafiltro = null;
		status = null;
	}
	
	public void Inicializar(){
		
		agenda = new Agendamento();
		
		try{
			eventModel = new LazyScheduleModel(){

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void loadEvents(Date start, Date end) {
			
					if(filtros == true){
						long clienteid = 0;
						long funcionarioid = 0;
						if(clienteparafiltro != null){
							clienteid = clienteparafiltro.getId();
						}
						if(funcionarioparafiltro != null){
							funcionarioid = funcionarioparafiltro.getId();
						}
						agendamentos = am.BuscarAgendamentosComFiltrosLazy(start, end, empresaid, clienteid, funcionarioid, status);
					}
					
					if(filtros == false){
						agendamentos = am.BuscarTodosAgendamentosLazy(start, end, empresaid);
					}
					
					
					for(Agendamento ag : agendamentos){
						
						DefaultScheduleEvent evt = new DefaultScheduleEvent();
						
						evt.setData(ag.getId());
						evt.setStartDate(ag.getDataAgendada());
						evt.setEndDate(ag.getDataAgendada());
						evt.setTitle(ag.getCliente().getNome());
						evt.setDescription(ag.getMotivo());
						evt.setEditable(true);
						evt.setAllDay(false);
						
						boolean f = ag.isFinalizado();
						boolean c = ag.isCancelado();
						if((f == true) && (c == false)){
							evt.setStyleClass("emp2");
						}
						
						if((f == false) && (c == true)){
							evt.setStyleClass("emp1");
						}
							
						eventModel.addEvent(evt);
						}
				}	
			};
			
		}catch(Exception ex){
			ex.printStackTrace();
			messages.error("Erro ao buscar todos os agendamentos");
			
		}
		
		}
	
	public void EventoSelecionado(SelectEvent selecionado){
		
		ScheduleEvent ev = (ScheduleEvent) selecionado.getObject();
		
		Iterator<Agendamento> itr = agendamentos.iterator();
		for(int i=0;i<agendamentos.size();i++){
			while(itr.hasNext()){
				Agendamento ag = itr.next();
				if(ag.getId() == (Long) ev.getData()){
					agenda = ag;
					DefinirStatus();
				}
			}
		}
	}
	
	public void NovoAgendamentoClicado(SelectEvent selecionado){
		
		ScheduleEvent ev = new DefaultScheduleEvent("", (Date)selecionado.getObject(), (Date)selecionado.getObject());
		agenda = new Agendamento();
		agenda.setDataAgendada(new Date(ev.getStartDate().getTime()));
		tipocliente = null;
		clienteFisicoAg = null;
		clienteJuridicoAg = null;
		
	}
	
	public void ClienteFisicoEmEdicao(){
		clientefisico = new ClienteFisico();
		endereco = new Endereco();
		MostrarUFs();
		MostrarPaises();
	}
	
	public void CadastrarClienteFisico(){
		try{
			clientefisico.setEndereco(endereco);
			clientefisico.setEmpresaCliente(empresaCliente);
			cm.CadastrarCliente(clientefisico);
			messages.info("Cliente Fisico Cadastrado com Sucesso!");
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao Cadastrar o Cliente");
		}
	}
	
	public void ClienteJuridicoEmEdicao(){
		clientejuridico = new ClienteJuridico();
		endereco = new Endereco();
		MostrarUFs();
		MostrarPaises();
	}
	
	public void CadastrarClienteJuridico(){
		try{
			clientejuridico.setEndereco(endereco);
			clientejuridico.setEmpresaCliente(empresaCliente);
			cm.CadastrarCliente(clientejuridico);
			messages.info("Cliente Juridico Cadastrado com Sucesso!");
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao Cadastrar o Cliente");
		}
	}
	
	public void AgendamentoEmEdicao(){
		agenda = new Agendamento();
		tipocliente = null;
		clienteFisicoAg = null;
		clienteJuridicoAg = null;
	}
	
	public void Agendar(){
		try{
			if(tipocliente.equals("Fisico")){
				agenda.setCliente(clienteFisicoAg);
			}else{
				agenda.setCliente(clienteJuridicoAg);
			}
			agenda.setFuncionario(funcionario);
			agenda.setEmpresaCliente(empresaCliente);
			am.CadastrarAgendamento(agenda);
			Inicializar();
			messages.info("Agendamento Cadastrado com Sucesso!");
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao Agendar");
		}
	}
	
	public void CancelarAgendamento(){
		try{
			agenda.setMotivoCancelado(motivocancelado);
			agenda.setCancelado(true);
			am.AtualizarAgendamento(agenda);
			Inicializar();
			messages.info("Agendamento Cancelado com Sucesso!");
			RequestContext.getCurrentInstance().update("home-frm:schedule");
			RequestContext.getCurrentInstance().execute("PF('MostraragendaDialog').hide();");
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao Cancelar o Agendamento");
		}
	}
	
	public void Reagendar(){
		agenda.setDataAgendada(datareagendar);
		am.AtualizarAgendamento(agenda);
		Inicializar();
		RequestContext.getCurrentInstance().update("home-frm:schedule");
		RequestContext.getCurrentInstance().execute("PF('reagendar-Dialog').hide();");
		RequestContext.getCurrentInstance().execute("PF('MostraragendaDialog').hide();");
		messages.info("Reagendamento Feito com Sucesso!");
	}
	
	public String ConcluirAgendamento(){
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("agendaparaconcluir", agenda);
		return "ConcluirAgendamento.xhtml?faces-redirect=true";
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
	
	public void AvisoEmEdicao(){
		aviso = new Aviso();
	}
	
	public void CriarAviso(){
		try{
			aviso.setEmpresaCliente(empresaCliente);
			avm.CadastrarAviso(aviso);
			messages.info("Aviso Criado com Sucesso!");
		}catch(Exception ex){
			ex.printStackTrace();
			messages.error("Erro ao Criar aviso");
		}
	}
	
	public void BuscarAvisosImportantes(){
		List<Aviso> avisos = avm.BuscarTodosAvisosImportantes(empresaid);
		if(!avisos.isEmpty()){
			String msg = null;
			for(Aviso a : avisos){
				msg = a.getMsg();
			}
			messages.warning(msg);
			RequestContext.getCurrentInstance().execute("PF('avisoimportante-Dialog').show();");
		}
	}
	
	public void BuscarAvisos(){
		//List<Aviso> avisos = avm.BuscarAvisoDoDia(empresaid, new Date());
		avisos = new ArrayList<Aviso>();
		avisos = avm.BuscarAvisoDoDia(empresaid, new Date());
		boolean temavisonaofinal = false;
		for(Aviso a : avisos){
			if(a.isFinalizado() == false){
				temavisonaofinal = true;
			}
		}
		if(temavisonaofinal == true){
			messages.warning("Atenção! Você Possui Avisos Não Lidos Para Hoje!");
			RequestContext.getCurrentInstance().execute("PF('avisoimportante-Dialog').show();");
		}
	}
	
	public void BuscarAvisosDoDia(){
		avisos = new ArrayList<Aviso>();
		avisos = avm.BuscarAvisoDoDia(empresaid, new Date()); 
	}
	
	public void Finalizar(){
		try{
			avisoParaFinalizar.setFinalizado(true);
			avm.AtualizarAviso(avisoParaFinalizar);
			BuscarAvisosDoDia();
			avisoParaFinalizar = null;
			//messages.info("Aviso Finalizado Com Sucesso!");
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao Finalizar o Aviso");
		}
	}
	
	public void DefinirStatus(){
		boolean f = agenda.isFinalizado();
		boolean c = agenda.isCancelado();
		if((f == true) && (c == false)){
			statusAgendamento = "Concluido";
			RequestContext.getCurrentInstance().update("home-frm:painelbotoes-dialog");
		}
		
		if((f == false) && (c == true)){
			statusAgendamento = "Cancelado";
			RequestContext.getCurrentInstance().update("home-frm:painelbotoes-dialog");
		}
		
		if((f == false) && (c == false)){
			statusAgendamento = "Agendado";
			RequestContext.getCurrentInstance().update("home-frm:painelbotoes-dialog");
		}
	}

	public List<ClienteFisico> CompletarNomeClienteFisico(String query){
		
		List<ClienteFisico> todosClientesFisicos = cm.BuscarTodosClientesFisicos(empresaid);
		List<ClienteFisico> filtros = new ArrayList<ClienteFisico>();
		
		for(int i = 0; i<todosClientesFisicos.size(); i++){
			ClienteFisico c = todosClientesFisicos.get(i);
			if(c.getNome().toLowerCase().startsWith(query.toLowerCase())){
				filtros.add(c);
			}
		}
		
		return filtros;
	}
	
	public List<ClienteJuridico> CompletarNomeClienteJuridico(String query){
		
		List<ClienteJuridico> todosClientesJuridicos = cm.BuscarTodosClientesJuridicos(empresaid);
		List<ClienteJuridico> filtros = new ArrayList<ClienteJuridico>();
		
		for(int i = 0; i<todosClientesJuridicos.size(); i++){
			ClienteJuridico c = todosClientesJuridicos.get(i);
			if(c.getNome().toLowerCase().startsWith(query.toLowerCase())){
				filtros.add(c);
			}
		}
		
		return filtros;
	}
	
	public List<Cliente> CompletarNomeCliente(String query){
		
		List<Cliente> todosClientes = cm.BuscarTodosClientes(empresaid);
		List<Cliente> filtros = new ArrayList<Cliente>();
		
		for(int i = 0; i<todosClientes.size(); i++){
			Cliente c = todosClientes.get(i);
			if(c.getNome().toLowerCase().startsWith(query.toLowerCase())){
				filtros.add(c);
			}
		}
		
		return filtros;
	}
	
	public List<Funcionario> CompletarNomeFuncionario(String query){
		
		FuncionarioManager fm = new FuncionarioManager();
		List<Funcionario> todosFuncionarios = fm.BuscarTodosFuncionarios(empresaid);
		List<Funcionario> filtros = new ArrayList<Funcionario>();
		
		for(int i = 0; i<todosFuncionarios.size(); i++){
			Funcionario f = todosFuncionarios.get(i);
			if(f.getNome().toLowerCase().startsWith(query.toLowerCase())){
				filtros.add(f);
			}
		}
		
		return filtros;
	}

}
