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
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.mmw.Agendamento.Manager.AgendamentoManager;
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
public class ConcluirAgendamentoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String escolher = "Não";
	
	private String criar;
	
	private String receberpagamento;
	
	private String tipoPagamento;
	
	private String formaPagamento;
	
	private int qntdVezesPagamento;
	
	private BigDecimal valorPagamento;
	
	private int descontoPagamento;
	
	private Parcelado parcelado;
	
	private Vista vista;
	
	private String resumo;
	
	private List<Servico> servicos;
	
	private Servico servicoSelecionado;
	
	private Servico servico;
	
	private Servico servicoCadastrado;
	
	private boolean nenhum;
	
	private boolean selecionado = false;
	
	private Agendamento agenda;
	
	private Cliente c;
	
	private long empresaid;
	
	private EmpresaCliente empresaCliente;
	
	private AgendamentoManager am = new AgendamentoManager();
	
	private ServicoManager sm = new ServicoManager();
	
	private PagamentoManager pm = new PagamentoManager();
	
	private ParcelaManager parm = new ParcelaManager();
	
	private FacesMessages messages = new FacesMessages();
	
	
	public String getReceberpagamento() {
		return receberpagamento;
	}

	public void setReceberpagamento(String receberpagamento) {
		this.receberpagamento = receberpagamento;
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

	public String getEscolher() {
		return escolher;
	}

	public void setEscolher(String escolher) {
		this.escolher = escolher;
	}

	public String getCriar() {
		return criar;
	}

	public void setCriar(String criar) {
		this.criar = criar;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public Servico getServicoSelecionado() {
		return servicoSelecionado;
	}

	public void setServicoSelecionado(Servico servicoSelecionado) {
		this.servicoSelecionado = servicoSelecionado;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Servico getServicoCadastrado() {
		return servicoCadastrado;
	}

	public void setServicoCadastrado(Servico servicoCadastrado) {
		this.servicoCadastrado = servicoCadastrado;
	}

	public boolean isNenhum() {
		return nenhum;
	}

	public void setNenhum(boolean nenhum) {
		this.nenhum = nenhum;
	}

	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

	public Agendamento getAgenda() {
		return agenda;
	}

	public void setAgenda(Agendamento agenda) {
		this.agenda = agenda;
	}

	public Cliente getC() {
		return c;
	}

	public void setC(Cliente c) {
		this.c = c;
	}

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
	
	public void PegarAgendamento(){
		BuscarUsuario();
		agenda = (Agendamento) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("agendaparaconcluir");
		c = agenda.getCliente();
	}
	
	public void DefinirEscolha(){
		if(escolher.equals("Sim")){
			servicos = new ArrayList<Servico>();
			servicos = sm.BuscarServicoPorCliente(c.getNome(), empresaid);
			if(servicos.isEmpty()){
				messages.warning("Ainda Não Existem Serviços para o Cliente: " + c.getNome());
			}
		}
	}
	
	public void EscolherServico(){
		selecionado = true;
		RequestContext.getCurrentInstance().update("concluir-frm:nenhumacima");
		RequestContext.getCurrentInstance().update("concluir-frm:criarservico");
		messages.info("Serviço de ID número: " + servicoSelecionado.getId() + " " + "Selecionado com Sucesso!");
	}

	public void DefinirCriacao(){
		if(criar.equals("Sim")){
			servico = new Servico();
			RequestContext.getCurrentInstance().update("concluir-frm:servico-dialog");
			RequestContext.getCurrentInstance().execute("PF('servico-Dialog').show();");
		}
	}
	
	public void CadastrarServico(){
		try{
			servico.setCliente(c);
			servico.setEmpresaCliente(empresaCliente);
			servicoCadastrado = sm.CadastrarServicoParaConcluirAgendamento(servico);
			messages.info("Serviço Cadastrado com Sucesso!");
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao Cadastrar o Serviço");
		}
	}
	
	public void ReceberPagamentoEmEdicao(){
		if(receberpagamento.equals("Sim")){
			if(selecionado == true){
				List<Pagamento> pagamentos = pm.BuscarTodosPagamentosDoServico(servicoSelecionado.getId());
				if(pagamentos.isEmpty()){
					valorPagamento = servicoSelecionado.getValor();
					RequestContext.getCurrentInstance().update("concluir-frm:pagamento-dialog");
					RequestContext.getCurrentInstance().execute("PF('pagamento-Dialog').show();");
				}else{
					RequestContext.getCurrentInstance().update("concluir-frm:pagamentoedicao-dialog");
					RequestContext.getCurrentInstance().execute("PF('pagamentoedicao-Dialog').show();");
				}
				
			}else{
				valorPagamento = servicoCadastrado.getValor();
				RequestContext.getCurrentInstance().update("concluir-frm:pagamento-dialog");
				RequestContext.getCurrentInstance().execute("PF('pagamento-Dialog').show();");
			}
		}
	}
	
	public void FecharAviso(){
		RequestContext.getCurrentInstance().execute("PF('pagamentoedicao-Dialog').hide();");
	}
	
	public void CancelarCriacaoNovoPagamento(){
		receberpagamento = "Não";
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
				
				if((escolher.equals("Sim")) && (selecionado == true)){
					parcelado.setServico(servicoSelecionado);
				}
				
				if((escolher.equals("Sim")) && (selecionado == false)){
					parcelado.setServico(servicoCadastrado);
				}
				
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
				
				messages.info("Pagamento Parcelado Cadastrado com Sucesso!");
				 
			}else{
				vista = new Vista();
				vista.setTipo(tipoPagamento);
				vista.setForma(formaPagamento);
				vista.setDataPagamento(new Date());
				vista.setPago(true);
				
				if((escolher.equals("Sim")) && (selecionado == true)){
					vista.setServico(servicoSelecionado);
				}
				
				if((escolher.equals("Sim")) && (selecionado == false)){
					vista.setServico(servicoCadastrado);
				}
				
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
				messages.info("Pagamento à Vista Cadastrado com Sucesso!");
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao Cadastrar o Pagamento");
		}
	}
	
	public String ConcluirAgendamento(){
		try{
			if((escolher.equals("Sim")) && (selecionado == true)){
				agenda.setServico(servicoSelecionado);
				RelatorioManager rm = new RelatorioManager();
				Relatorio r = new Relatorio();
				r.setResumo(resumo);
				r.setServico(servicoSelecionado);
				rm.CadastrarRelatorio(r);
			}
			
			if((escolher.equals("Sim")) && (selecionado == false)){
				agenda.setServico(servicoCadastrado);
				RelatorioManager rm = new RelatorioManager();
				Relatorio r = new Relatorio();
				r.setResumo(resumo);
				r.setServico(servicoCadastrado);
				rm.CadastrarRelatorio(r);
			}
			
			if(escolher.equals("Não")){
				agenda.setResumo(resumo);
			}
			
			agenda.setFinalizado(true);
			am.AtualizarAgendamento(agenda);
			messages.info("Agendamento Concluido com Sucesso!");
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			
		}catch(Exception ex){
			ex.printStackTrace();
			messages.fatal("Erro ao Concluir o Agendamento");
		}
		
		return "Home.xhtml?faces-redirect=true";
	}
}
