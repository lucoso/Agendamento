package br.com.mmw.Agendamento.Controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.mmw.Agendamento.Manager.AgendamentoManager;
import br.com.mmw.Agendamento.Manager.ClienteManager;
import br.com.mmw.Agendamento.Manager.PagamentoManager;
import br.com.mmw.Agendamento.Manager.ParcelaManager;
import br.com.mmw.Agendamento.Manager.RelatorioManager;
import br.com.mmw.Agendamento.Manager.ServicoManager;
import br.com.mmw.Agendamento.Manager.UsuarioManager;
import br.com.mmw.Agendamento.Model.Agendamento;
import br.com.mmw.Agendamento.Model.Cliente;
import br.com.mmw.Agendamento.Model.EmpresaCliente;
import br.com.mmw.Agendamento.Model.Funcionario;
import br.com.mmw.Agendamento.Model.Pagamento;
import br.com.mmw.Agendamento.Model.Parcela;
import br.com.mmw.Agendamento.Model.Parcelado;
import br.com.mmw.Agendamento.Model.Relatorio;
import br.com.mmw.Agendamento.Model.Servico;
import br.com.mmw.Agendamento.Model.Usuario;
import br.com.mmw.Agendamento.Model.Vista;
import br.com.mmw.Agendamento.Util.FacesMessages;

@ManagedBean
@ViewScoped
public class ServicoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Servico servico;
	
	private Servico servicoSelecionado;
	
	private Servico servicoParaFinalizar;
	
	private List<Servico> servicos;
	
	private String relatorio;
	
	private String relatoriofinal;
	
	private String novorelatorio;
	
	private Date datafinalizado;
	
	private String clientenome;
	
	private String statusbusca;
	
	private List<Relatorio> relatorios;
	
	private Relatorio relatorioSelecionado;
	
	private Parcelado parcelado;
	
	private Vista vista;
	
	private Pagamento pagamentoSelecionado;
	
	private List<Pagamento> pagamentos;
	
	private String tipoPagamento;
	
	private String formaPagamento;
	
	private int qntdVezesPagamento;
	
	private BigDecimal valorPagamento;
	
	private int descontoPagamento;
	
	private Parcela parcela;
	
	private Parcela parcelaSelecionado;
	
	private List<Parcela> parcelas;
	
	private long empresaid;
	
	private EmpresaCliente empresaCliente;
	
	private List<Agendamento> agendamentos;
	
	private ServicoManager sm = new ServicoManager();
	
	private RelatorioManager rm = new RelatorioManager();
	
	private ClienteManager cm = new ClienteManager();
	
	private PagamentoManager pm = new PagamentoManager();
	
	private ParcelaManager parm = new ParcelaManager();
	
	private AgendamentoManager am = new AgendamentoManager();
	
	private FacesMessages messages = new FacesMessages();
	
	
	public long getEmpresaid() {
		return empresaid;
	}

	public void setEmpresaid(long empresaid) {
		this.empresaid = empresaid;
	}

	public List<Agendamento> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(List<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}

	public EmpresaCliente getEmpresaCliente() {
		return empresaCliente;
	}

	public void setEmpresaCliente(EmpresaCliente empresaCliente) {
		this.empresaCliente = empresaCliente;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Servico getServicoSelecionado() {
		return servicoSelecionado;
	}

	public void setServicoSelecionado(Servico servicoSelecionado) {
		this.servicoSelecionado = servicoSelecionado;
	}

	public Servico getServicoParaFinalizar() {
		return servicoParaFinalizar;
	}

	public void setServicoParaFinalizar(Servico servicoParaFinalizar) {
		this.servicoParaFinalizar = servicoParaFinalizar;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public String getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(String relatorio) {
		this.relatorio = relatorio;
	}

	public String getRelatoriofinal() {
		return relatoriofinal;
	}

	public void setRelatoriofinal(String relatoriofinal) {
		this.relatoriofinal = relatoriofinal;
	}

	public String getNovorelatorio() {
		return novorelatorio;
	}

	public void setNovorelatorio(String novorelatorio) {
		this.novorelatorio = novorelatorio;
	}

	public Date getDatafinalizado() {
		return datafinalizado;
	}

	public void setDatafinalizado(Date datafinalizado) {
		this.datafinalizado = datafinalizado;
	}

	public String getClientenome() {
		return clientenome;
	}

	public void setClientenome(String clientenome) {
		this.clientenome = clientenome;
	}

	public String getStatusbusca() {
		return statusbusca;
	}

	public void setStatusbusca(String statusbusca) {
		this.statusbusca = statusbusca;
	}

	public List<Relatorio> getRelatorios() {
		return relatorios;
	}

	public void setRelatorios(List<Relatorio> relatorios) {
		this.relatorios = relatorios;
	}

	public Relatorio getRelatorioSelecionado() {
		return relatorioSelecionado;
	}

	public void setRelatorioSelecionado(Relatorio relatorioSelecionado) {
		this.relatorioSelecionado = relatorioSelecionado;
	}

	public Parcelado getParcelado() {
		return parcelado;
	}

	public void setParcelado(Parcelado parcelado) {
		this.parcelado = parcelado;
	}

	public Vista getVista() {
		return vista;
	}

	public void setVista(Vista vista) {
		this.vista = vista;
	}

	public Pagamento getPagamentoSelecionado() {
		return pagamentoSelecionado;
	}

	public void setPagamentoSelecionado(Pagamento pagamentoSelecionado) {
		this.pagamentoSelecionado = pagamentoSelecionado;
	}

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public int getQntdVezesPagamento() {
		return qntdVezesPagamento;
	}

	public void setQntdVezesPagamento(int qntdVezesPagamento) {
		this.qntdVezesPagamento = qntdVezesPagamento;
	}

	public BigDecimal getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

	public int getDescontoPagamento() {
		return descontoPagamento;
	}

	public void setDescontoPagamento(int descontoPagamento) {
		this.descontoPagamento = descontoPagamento;
	}

	public Parcela getParcela() {
		return parcela;
	}

	public void setParcela(Parcela parcela) {
		this.parcela = parcela;
	}

	public Parcela getParcelaSelecionado() {
		return parcelaSelecionado;
	}

	public void setParcelaSelecionado(Parcela parcelaSelecionado) {
		this.parcelaSelecionado = parcelaSelecionado;
	}

	public List<Parcela> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<Parcela> parcelas) {
		this.parcelas = parcelas;
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
	
	public void BuscarTodos(){
		servicos = new ArrayList<Servico>();
		servicos = sm.BuscarTodosServicosDoUltimoMes(empresaid);
	}
	
	public void ServicoEmEdicao(){
		servico = new Servico();
		relatorio = null;
	}
	
	public void CadastrarServico(){
		try{
			Relatorio novorelatorio = new Relatorio();
			novorelatorio.setResumo(relatorio);
			novorelatorio.setData(new Date());
			novorelatorio.setServico(servico);
			
			servico.setEmpresaCliente(empresaCliente);
			sm.CadastrarServico(servico);
			rm.CadastrarRelatorio(novorelatorio);
			BuscarTodos();
			
			messages.info("Serviço Cadastrado com Sucesso!");
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao Cadastrar o Novo Serviço");
		}
	}
	
	public void AtualizarServico(){
		try{
			sm.AtualizarServico(servicoSelecionado);
			BuscarTodos();
			servicoSelecionado = null;
			messages.info("Serviço Atualizado com Sucesso!");
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao Atualizar o Serviço");
		}
	}
	
	public void BuscarRelatorios(){
		relatorios = new ArrayList<Relatorio>();
		relatorios = rm.BuscarTodosRelatoriosDoServico(servicoSelecionado.getId());
	}
	
	public void LimparRelatorio(){
		novorelatorio = null;
	}
	
	public void CriarNovoRelatorio(){
		try{
			Relatorio novoRelatorio = new Relatorio();
			novoRelatorio.setData(new Date());
			novoRelatorio.setResumo(novorelatorio);
			novoRelatorio.setServico(servicoSelecionado);
			
			rm.CadastrarRelatorio(novoRelatorio);
			BuscarRelatorios();
			messages.info("Relatorio Criado com Sucesso!");
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao Criar o Novo Relatorio");
		}
	}
	
	public void AtualizarRelatorio(){
		try{
			rm.AtualizarRelatorio(relatorioSelecionado);
			BuscarRelatorios();
			relatorioSelecionado = null;
			messages.info("Relatorio Atualizado com Sucesso!");
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao Atualizar o Relatorio");
		}
	}
	
	public void RemoverRelatorio(){
		try{
			rm.RemoverRelatorio(relatorioSelecionado.getId());
			BuscarRelatorios();
			relatorioSelecionado = null;
			messages.info("Relatorio Excluido com Sucesso!");
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao Excluir o Relatorio");
		}
	}
	
	public void BuscarPagamentos(){
		pagamentos = new ArrayList<Pagamento>();
		pagamentos = pm.BuscarTodosPagamentosDoServico(servicoSelecionado.getId());
		int x = 0;
		int qntdParcelas = 0;
		if(!pagamentos.isEmpty()){
			for(Pagamento p : pagamentos){
				if(p.getTipo().equals("Parcelado")){
					List<Parcela> parcelas = parm.BuscarTodasParcelasDoPagamento(p.getId());
					if(!parcelas.isEmpty()){
						x = 0;
						qntdParcelas = parcelas.size();
						for(Parcela par : parcelas){
							if(par.isPago() == true){
								x++;
							}
						}
					}
					
					/**
					 * se essa condição for verdadeira...significa que todas as parcelas foram pagas
					 * entao to setando o pagamento como pago
					 */
					if(x == qntdParcelas){
						p.setPago(true);
						pm.AtualizarPagamento(p);
					}
				}
			}
		}
	}
	
	public void PagamentoEmEdicao(){
		valorPagamento = servicoSelecionado.getValor();
		tipoPagamento = null;
		formaPagamento = null;
		qntdVezesPagamento = 0;
	}
	
	public void DefinirTipoDePagamento(){
		if(tipoPagamento.equals("Parcelado")){
			parcelado = new Parcelado();
		}else{
			vista = new Vista();
		}
	}
	
	public void CadastrarPagamento(){
		try{
			if(tipoPagamento.equals("Parcelado")){
				parcelado.setTipo(tipoPagamento);
				parcelado.setForma(formaPagamento);
				parcelado.setQntsVezes(qntdVezesPagamento);
				parcelado.setPago(false);
				parcelado.setServico(servicoSelecionado);
				
				if(descontoPagamento > 0){
					parcelado.setDesconto(descontoPagamento);
					BigDecimal desc = new BigDecimal(descontoPagamento);
					BigDecimal divisao = desc.divide(new BigDecimal(100), 2, RoundingMode.HALF_EVEN);
					BigDecimal descontoemreal = divisao.multiply(valorPagamento);
					BigDecimal novovalor = valorPagamento.subtract(descontoemreal);
					parcelado.setValorTotal(novovalor);
				}else{
					parcelado.setValorTotal(valorPagamento);
				}
				
				Parcelado parceladoLocal = pm.CadastrarPagamentoParcelado(parcelado);
				
				BigDecimal valortotal = parceladoLocal.getValorTotal();
				int qntd = parceladoLocal.getQntsVezes();
				BigDecimal qntdv = new BigDecimal(qntd);
				BigDecimal[] result = valortotal.divideAndRemainder(qntdv);
				BigDecimal primeiraParcela = result[0].add(result[1]);
				BigDecimal demaisParcelas = result[0];
				
				for(int i=0; i<qntdVezesPagamento; i++){
					Parcela p = new Parcela();
					List<Parcela> parcelas = parm.BuscarTodasParcelasDoPagamento(parceladoLocal.getId());
					if(parcelas.isEmpty()){
						p.setPrimeira(true);
						p.setValor(primeiraParcela);
						p.setParcelado(parceladoLocal);
						
						Date hoje = new Date();
						Calendar cal = Calendar.getInstance();
						cal.setTime(hoje);
						
						cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 5);
						Date novadata = cal.getTime();
						
						p.setVencimento(novadata);
					}else{
						p.setPrimeira(false);
						p.setValor(demaisParcelas);
						p.setParcelado(parceladoLocal);
						
						Date hoje = new Date();
						Calendar cal = Calendar.getInstance();
						cal.setTime(hoje);
						
						cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + i);
						Date novadata = cal.getTime();
						
						p.setVencimento(novadata);
					}
					
					parm.CadastrarParcela(p);
				}
				
				BuscarPagamentos();
				messages.info("Pagamento Parcelado Cadastrado com Sucesso!");
				 
			}else{
				vista = new Vista();
				vista.setTipo(tipoPagamento);
				vista.setForma(formaPagamento);
				vista.setDataPagamento(new Date());
				vista.setPago(true);
				vista.setServico(servicoSelecionado);
				
				if(descontoPagamento > 0){
					vista.setDesconto(descontoPagamento);
					BigDecimal desc = new BigDecimal(descontoPagamento);
					BigDecimal divisao = desc.divide(new BigDecimal(100), 2, RoundingMode.HALF_EVEN);
					BigDecimal descontoemreal = divisao.multiply(valorPagamento);
					BigDecimal novovalor = valorPagamento.subtract(descontoemreal);
					vista.setValorTotal(novovalor);
				}else{
					vista.setValorTotal(valorPagamento);
				}
				
				
				pm.CadastrarPagamento(vista);
				BuscarPagamentos();
				messages.info("Pagamento à Vista Cadastrado com Sucesso!");
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao Cadastrar o Pagamento");
		}
	}
	
	public void RemoverPagamento(){
		try{
			pm.RemoverPagamento(pagamentoSelecionado.getId());
			pagamentoSelecionado = null;
			BuscarPagamentos();
			messages.info("Pagamento Excluido com Sucesso!");
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao Excluir o Pagamento");
		}
	}
	
	public void BuscarParcelasDoPagamento(){
		parcelas = new ArrayList<Parcela>();
		parcelas = parm.BuscarTodasParcelasDoPagamento(pagamentoSelecionado.getId());
	}
	
	public void NovaParcelaEmEdicao(){
		parcela = new Parcela();
	}
	
	public void CadastrarParcela(){
		try{
			Parcelado parcelado = (Parcelado) pagamentoSelecionado;
			parcela.setParcelado(parcelado);
			parcela.setPago(false);
			parcela.setPrimeira(false);
			parm.CadastrarParcela(parcela);
			BuscarParcelasDoPagamento();
			messages.info("Nova Parcela Criada com Sucesso!");
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao Criar uma Nova Parcela para o Pagamento");
		}
	}

	public void ParcelaEdicao(RowEditEvent event){
		try{
			Parcela p = ((Parcela) event.getObject());
			parm.AtualizarParcela(p);
			messages.info("Parcela Atualizada com Sucesso!");
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao Atualizar os dados da Parcela");
		}
		
	}
	
	public void ParcelaCancelaEdicao(RowEditEvent event){
		long id = ((Parcela) event.getObject()).getId();
		messages.info("Atualização da Parcela de ID número: " + id + " " + "Cancelada");
	}
	
	public void RemoverParcela(){
		try{
			parm.RemoverParcela(parcelaSelecionado.getId());
			BuscarParcelasDoPagamento();
			messages.info("Parcela Excluida com Sucesso!");
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao Remover a Parcela");
		}
	}
	
	public void PagarParcela(){
		try{
			parcelaSelecionado.setDataPagamento(new Date());
			parcelaSelecionado.setPago(true);
			parm.AtualizarParcela(parcelaSelecionado);
			BuscarParcelasDoPagamento();
			parcelaSelecionado = null;
			messages.info("Parcela Paga com Sucesso!");
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao Pagar a Parcela");
		}
	}

	public void FinalizarServico(){
		try{
			Relatorio novorelatorio = new Relatorio();
			novorelatorio.setResumo(relatoriofinal);
			novorelatorio.setData(new Date());
			novorelatorio.setServico(servicoParaFinalizar);
			
			servicoParaFinalizar.setDataFinalizado(datafinalizado);
			servicoParaFinalizar.setFinalizado(true);
			servicoParaFinalizar.setStatus("Finalizado");
			
			sm.AtualizarServico(servicoParaFinalizar);
			rm.CadastrarRelatorio(novorelatorio);
			
			messages.info("Serviço Finalizado com Sucesso!");
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao Finalizar o Serviço");
		}
	}
	
	public void LimparDadosFinalizados(){
		relatoriofinal = null;
		datafinalizado = null;
	}
	
	public void BuscarPorCliente(){
		try{
			servicos = new ArrayList<Servico>();
			servicos = sm.BuscarServicoPorCliente(clientenome, empresaid);
			if(servicos.isEmpty()){
				clientenome = null;
				messages.error("Nenhum Serviço Encontrado para esse Cliente");
			}else{
				RequestContext.getCurrentInstance().update("servico-frm:servicoCarousel");
				clientenome = null;
				messages.info("Serviços do Cliente Encontrados com Sucesso!");
			}
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao Buscar os Serviços do Cliente");
		}
	}
	
	public void BuscarPorStatus(){
		try{
			servicos = new ArrayList<Servico>();
			servicos = sm.BuscarServicoPorStatus(statusbusca, empresaid);
			if(servicos.isEmpty()){
				statusbusca = null;
				messages.error("Nenhum Serviço Encontrado com esse Status");
			}else{
				RequestContext.getCurrentInstance().update("servico-frm:servicoCarousel");
				statusbusca = null;
				messages.info("Serviços Encontrados com Sucesso!");
			}
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao Buscar os Serviços do Cliente");
		}
	}
	
	public void BuscarAgendamentos(){
		try{
			agendamentos = new ArrayList<Agendamento>();
			agendamentos = am.BuscarAgendamentoPorServico(servicoSelecionado.getId());
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao Buscar os Agendamentos do Serviço do Cliente");
		}
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
	
	public List<String> CompletarNomeClienteString(String query){
		
		List<Cliente> todosClientes = cm.BuscarTodosClientes(empresaid);
		List<String> filtros = new ArrayList<String>();
		
		for(int i = 0; i<todosClientes.size(); i++){
			Cliente c = todosClientes.get(i);
			if(c.getNome().toLowerCase().startsWith(query.toLowerCase())){
				filtros.add(c.getNome());
			}
		}
		
		return filtros;
	}	
}
