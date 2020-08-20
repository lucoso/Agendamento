package br.com.mmw.Agendamento.Controller;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.security.core.context.SecurityContextHolder;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.html.WebColors;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.draw.LineSeparator;

import br.com.mmw.Agendamento.Manager.AgendamentoManager;
import br.com.mmw.Agendamento.Manager.ClienteManager;
import br.com.mmw.Agendamento.Manager.FuncionarioManager;
import br.com.mmw.Agendamento.Manager.PagamentoManager;
import br.com.mmw.Agendamento.Manager.ServicoManager;
import br.com.mmw.Agendamento.Manager.UsuarioManager;
import br.com.mmw.Agendamento.Model.Agendamento;
import br.com.mmw.Agendamento.Model.Cliente;
import br.com.mmw.Agendamento.Model.Funcionario;
import br.com.mmw.Agendamento.Model.Pagamento;
import br.com.mmw.Agendamento.Model.RelatorioFinanceiro;
import br.com.mmw.Agendamento.Model.RelatorioPagamento;
import br.com.mmw.Agendamento.Model.Servico;
import br.com.mmw.Agendamento.Model.Usuario;
import br.com.mmw.Agendamento.Util.FacesMessages;

@ManagedBean
@ViewScoped
public class RelatorioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PieChartModel graficoAgendamentos;
	
	private BarChartModel graficoServicos;
	
	private List<Servico> servicos;
	
	private Date datainicial;
	
	private Date datafinal;
	
	private Date datainicialGrafico;
	
	private Date datafinalGrafico;
	
	private String escolhaTipoRelatorio = "Periodo";
	
	private BigDecimal total;
	
	private Cliente cliente;
	
	private String today;
	
	private String hora;
	
	private LazyDataModel<Agendamento> agendamentos;
	
	private List<Agendamento> agendamentosParaRelatorios;
	
	private Date datainicialAg;
	
	private Date datafinalAg;
	
	private String periodoagendamentos;
	
	private List<RelatorioFinanceiro> financeiros;
	
	private String totalrecebido;
	
	private int totalagendamentos;
	
	private Agendamento agendamentoSelecionado;
	
	private ServicoManager sm = new ServicoManager();
	
	private AgendamentoManager am = new AgendamentoManager();
	
	private PagamentoManager pm = new PagamentoManager();
	
	private FacesMessages messages = new FacesMessages();
	
	private long empresaid;
	
	
	public int getTotalagendamentos() {
		return totalagendamentos;
	}

	public void setTotalagendamentos(int totalagendamentos) {
		this.totalagendamentos = totalagendamentos;
	}

	public String getTotalrecebido() {
		return totalrecebido;
	}

	public void setTotalrecebido(String totalrecebido) {
		this.totalrecebido = totalrecebido;
	}

	public List<Agendamento> getAgendamentosParaRelatorios() {
		return agendamentosParaRelatorios;
	}

	public void setAgendamentosParaRelatorios(List<Agendamento> agendamentosParaRelatorios) {
		this.agendamentosParaRelatorios = agendamentosParaRelatorios;
	}

	public Agendamento getAgendamentoSelecionado() {
		return agendamentoSelecionado;
	}

	public void setAgendamentoSelecionado(Agendamento agendamentoSelecionado) {
		this.agendamentoSelecionado = agendamentoSelecionado;
	}

	public List<RelatorioFinanceiro> getFinanceiros() {
		return financeiros;
	}

	public void setFinanceiros(List<RelatorioFinanceiro> financeiros) {
		this.financeiros = financeiros;
	}

	public LazyDataModel<Agendamento> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(LazyDataModel<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}

	public Date getDatainicialAg() {
		return datainicialAg;
	}

	public void setDatainicialAg(Date datainicialAg) {
		this.datainicialAg = datainicialAg;
	}

	public Date getDatafinalAg() {
		return datafinalAg;
	}

	public void setDatafinalAg(Date datafinalAg) {
		this.datafinalAg = datafinalAg;
	}

	public String getPeriodoagendamentos() {
		return periodoagendamentos;
	}

	public void setPeriodoagendamentos(String periodoagendamentos) {
		this.periodoagendamentos = periodoagendamentos;
	}

	public long getEmpresaid() {
		return empresaid;
	}

	public void setEmpresaid(long empresaid) {
		this.empresaid = empresaid;
	}

	public PieChartModel getGraficoAgendamentos() {
		return graficoAgendamentos;
	}

	public void setGraficoAgendamentos(PieChartModel graficoAgendamentos) {
		this.graficoAgendamentos = graficoAgendamentos;
	}

	public BarChartModel getGraficoServicos() {
		return graficoServicos;
	}

	public void setGraficoServicos(BarChartModel graficoServicos) {
		this.graficoServicos = graficoServicos;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
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

	public Date getDatainicialGrafico() {
		return datainicialGrafico;
	}

	public void setDatainicialGrafico(Date datainicialGrafico) {
		this.datainicialGrafico = datainicialGrafico;
	}

	public Date getDatafinalGrafico() {
		return datafinalGrafico;
	}

	public void setDatafinalGrafico(Date datafinalGrafico) {
		this.datafinalGrafico = datafinalGrafico;
	}

	public String getEscolhaTipoRelatorio() {
		return escolhaTipoRelatorio;
	}

	public void setEscolhaTipoRelatorio(String escolhaTipoRelatorio) {
		this.escolhaTipoRelatorio = escolhaTipoRelatorio;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getToday() {
		return today;
	}

	public void setToday(String today) {
		this.today = today;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
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
	
	public void DefinirHoje(){
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat hour = new SimpleDateFormat("HH:mm:ss");
		Date h = new Date();
		today = f.format(h);
		hora = hour.format(h);
	}
	
	public RelatorioBean(){
		BuscarUsuario();
		MostrarGraficoAgendamento();
		MostrarGraficoServico();
		GerarRelatorioFinanceiro();
		DefinirHoje();
	}
	
	public void MontarGraficoAgendamento(Date inicio, Date fim){
		
		List<Agendamento> agendamentos = am.BuscarAgendamentoPorPeriodo(empresaid, inicio, fim);
		List<Agendamento> concluidos = new ArrayList<Agendamento>();
		List<Agendamento> cancelados = new ArrayList<Agendamento>();
		
		for(Agendamento a : agendamentos){
			if((a.isCancelado() == true) && (a.isFinalizado() == false)){
				cancelados.add(a);
			}
			
			if((a.isCancelado() == false) && (a.isFinalizado() == true)){
				concluidos.add(a);
			}
		}
		
		int qntdCancelados = cancelados.size();
		int qntdConcluidos = concluidos.size();
		
		int periodo = fim.getMonth() - inicio.getMonth();
		String periodos = null;
		
		if(periodo == 0){
			periodo = 1;
			periodos = periodo + " mês";
		}else{
			periodos = periodo + " meses";
		}
		
		graficoAgendamentos = new PieChartModel();
		
		graficoAgendamentos.set("Agendamentos Concluidos", qntdConcluidos);
		graficoAgendamentos.set("Agendamentos Cancelados", qntdCancelados);
		
		graficoAgendamentos.setTitle("Agendamentos Concluidos e Cancelados no Período de " + periodos);
		graficoAgendamentos.setLegendPosition("w");
	}
	
	public BarChartModel MontarGraficoServico(Date inicio, Date fim){
		
		List<Servico> servicos = sm.BuscarServicosPorPeriodo(empresaid, inicio, fim); 
		List<Servico> finalizados = new ArrayList<Servico>();
		List<Servico> naofinalizados = new ArrayList<Servico>();
		
		for(Servico s : servicos){
			if(s.isFinalizado() == true){
				finalizados.add(s);
			}else{
				naofinalizados.add(s);
			}
		}
		
		int qntdFinalizados = finalizados.size();
		int qntdNaoFinalizados = naofinalizados.size();
		
		int periodo = fim.getMonth() - inicio.getMonth();
		String periodos = null;
		
		if(periodo == 0){
			periodo = 1;
			periodos = periodo + " mês";
		}else{
			periodos = periodo + " meses";
		}
		
		BarChartModel models = new BarChartModel();
		
		ChartSeries end = new ChartSeries();
		end.setLabel("Serviços Finalizados");
		end.set("Serviços Finalizados e Não Finalizados no Período de " + periodos, qntdFinalizados);
		
		ChartSeries notend = new ChartSeries();
		notend.setLabel("Serviços Não Finalizados");
		notend.set("Serviços Finalizados e Não Finalizados no Período de " + periodos, qntdNaoFinalizados);
		
		models.addSeries(end);
		models.addSeries(notend);
		
		return models;
	}
	
	public void MostrarGraficoServico(){
		
		if((datainicialGrafico == null) && (datafinalGrafico == null)){
			Date hoje = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(hoje);
			
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 3);
			Date passado = cal.getTime();
			
			graficoServicos = MontarGraficoServico(passado, hoje);

		}else{
			graficoServicos = MontarGraficoServico(datainicialGrafico, datafinalGrafico);
		}
		
		graficoServicos.setTitle("Grafico de Serviços Finalizados e Não Finalizados");
		graficoServicos.setAnimate(true);
		graficoServicos.setBarWidth(150);
		graficoServicos.setLegendPosition("nw");
		Axis yAxis = graficoServicos.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(100);
	}
	
	public void MostrarGraficoAgendamento(){
		if((datainicialGrafico == null) && (datafinalGrafico == null)){
			Date hoje = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(hoje);
			
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 3);
			Date passado = cal.getTime();
			MontarGraficoAgendamento(passado, hoje);
		}else{
			MontarGraficoAgendamento(datainicialGrafico, datafinalGrafico);
		}
	}
	
	public void GerarNovoGrafico(){
		MostrarGraficoAgendamento();
		MostrarGraficoServico();
	}
	
	public void Gerar(){
		financeiros = new ArrayList<RelatorioFinanceiro>();
		total = new BigDecimal(0);
		List<Servico> servs = new ArrayList<Servico>();
		
		if(escolhaTipoRelatorio.equals("Periodo")){
			servs = sm.BuscarServicosPorPeriodo(empresaid, datainicial, datafinal);
		}else{
			servs = sm.BuscarServicosPorPeriodoECliente(empresaid, datainicial, datafinal, cliente.getId());
		}
		
		/**
		 * definir relatorio e total recebido
		 */
		for(Servico s : servs){	
			RelatorioFinanceiro rf = new RelatorioFinanceiro();
			
			List<RelatorioPagamento> relatoriopagamentos = new ArrayList<RelatorioPagamento>();
			List<Pagamento> pagamentos = pm.BuscarTodosPagamentosDoServico(s.getId());
			if(!pagamentos.isEmpty()){
				for(Pagamento p : pagamentos){
					if(p.isPago() == true){
						rf.setDataAberturaServico(s.getDataAbertura());
						rf.setNomeCliente(s.getCliente().getNome());
						RelatorioPagamento rp = new RelatorioPagamento();
						BigDecimal valor = p.getValorTotal();
						rp.setValorTotalRecebido(valor);
						rp.setDesconto(p.getDesconto());
						total = total.add(valor);
						
						relatoriopagamentos.add(rp);
					}
				}
			}
			rf.setPagamentos(relatoriopagamentos);
			financeiros.add(rf);
		}
		
		totalrecebido = total.toString();
	}
	
	public void GerarRelatorioFinanceiro(){
		financeiros = new ArrayList<RelatorioFinanceiro>();
		total = new BigDecimal(0);
		List<Servico> servs = new ArrayList<Servico>();
		
		Date hoje = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(hoje);
		
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 3);
		Date passado = cal.getTime();
		
		servs = sm.BuscarServicosPorPeriodo(empresaid, passado, hoje);
		
		/**
		 * definir total recebido
		 */
		for(Servico s : servs){	
			RelatorioFinanceiro rf = new RelatorioFinanceiro();
			
			List<RelatorioPagamento> relatoriopagamentos = new ArrayList<RelatorioPagamento>();
			List<Pagamento> pagamentos = pm.BuscarTodosPagamentosDoServico(s.getId());
			if(!pagamentos.isEmpty()){
				for(Pagamento p : pagamentos){
					if(p.isPago() == true){
						rf.setDataAberturaServico(s.getDataAbertura());
						rf.setNomeCliente(s.getCliente().getNome());
						RelatorioPagamento rp = new RelatorioPagamento();
						BigDecimal valor = p.getValorTotal();
						rp.setValorTotalRecebido(valor);
						rp.setDesconto(p.getDesconto());
						total = total.add(valor);
						
						relatoriopagamentos.add(rp);
					}
				}
			}
			rf.setPagamentos(relatoriopagamentos);
			financeiros.add(rf);
		}
		
		totalrecebido = total.toString();
		datainicial = passado;
		datafinal = hoje;
	}
	
	public List<Funcionario> BuscarFuncionarios(){
		FuncionarioManager fm = new FuncionarioManager();
		List<Funcionario> funcs = fm.BuscarTodosFuncionarios(empresaid);
		return funcs;
	}
	
	public void preProcessPDF(Object document) throws DocumentException, IOException, BadElementException {
		Document pdf = (Document) document;
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		String logo = ec.getRealPath("") + File.separator + "resources" + File.separator + "mmw" + File.separator + "images" + File.separator + "logo-oficial.png"; 
		 
		Image img = Image.getInstance(logo);
		img.setAlignment(Element.ALIGN_LEFT);
		img.scaleAbsolute(150, 100);
		
		Font f = new Font();
		f.setSize(22);
		f.setFamily("Calibri");
		f.setStyle("bold");
		
		Font f2 = new Font();
		f2.setSize(14);
		f2.setFamily("Calibri");
		f2.setStyle("bold");
		
		Font f3 = new Font();
		f3.setSize(12);
		f3.setFamily("Calibri");
		
		Font f4 = new Font();
		f4.setSize(11);
		f4.setFamily("Calibri");
		f4.setColor(Color.green.darker());
		
		Font fonttable = new Font();
		fonttable.setSize(15);
		fonttable.setFamily("Calibri");
		fonttable.setColor(Color.WHITE);
		
		Font fonttable2 = new Font();
		fonttable2.setSize(14);
		fonttable2.setFamily("Calibri");
		fonttable2.setColor(Color.WHITE);
		
		Color minhacor = WebColors.getRGBColor("#008000");
		
		/**
		 * data inicial e final
		 */
		
		SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
		String init = formatar.format(datainicial);
		String finish = formatar.format(datafinal);
		
		
		/**
		 * Header
		 */
		
		Chunk chunk = new Chunk(img, 0, -45);
		HeaderFooter header = new HeaderFooter(new Phrase(chunk), false);
		header.setAlignment(Element.ALIGN_LEFT);
		header.setBorder(Rectangle.NO_BORDER);
		pdf.setHeader(header);
		
		/**
		 * Rodape
		 */
		Paragraph fraserodape = new Paragraph();
		fraserodape.add(new Paragraph("MMW SISTEMAS", f4));
		fraserodape.add(new Paragraph("www.mmwsistemas.com.br", f4));
		fraserodape.add(new Paragraph("Telefone: (22) 99715-8310", f4));
		HeaderFooter footer = new HeaderFooter(fraserodape, false);
		footer.setAlignment(Element.ALIGN_CENTER);
		pdf.setFooter(footer);
		
		LineSeparator linha = new LineSeparator();
		
		Paragraph p = new Paragraph("MMW Agendamentos - Relatorio Finaceiro ", f);
		p.setAlignment(Element.ALIGN_CENTER);
		p.setSpacingAfter(20);
		//p.setSpacingBefore(50);
		
		Paragraph p2 = new Paragraph("Relatorio Financeiro dos Serviços", f2);
		p2.setAlignment(Element.ALIGN_CENTER);
		p2.setSpacingAfter(10);
		
		Paragraph p3 = new Paragraph("\u2022 " + "Relatorio Gerado dia: " + today + " às " + hora, f3);
		p3.setSpacingAfter(10);
		
		Paragraph p4 = new Paragraph("\u2022 " + "Periodo Base: De " + init + " Até " + finish, f3);
		p4.setSpacingAfter(20);
		
		Paragraph p5 = new Paragraph("Gastos", f2);
		p5.setSpacingAfter(20);
		p5.setAlignment(Element.ALIGN_CENTER);
		
		/**
		 * Tabela dos Funcionarios
		 */
		
		List<Funcionario> funcionarios = BuscarFuncionarios();
		BigDecimal totalfuncs = new BigDecimal(0);
		if(!funcionarios.isEmpty()){
			for(Funcionario funcionario : funcionarios){
				totalfuncs = totalfuncs.add(funcionario.getSalario());
			}
		}
		
		String total = "Total Gasto Com os Funcionarios: R$" + totalfuncs;
		
		PdfPTable tabelaFunc = new PdfPTable(3);
		PdfPCell head = new PdfPCell(new Phrase("Tabela de Funcionários", fonttable));
		head.setBackgroundColor(minhacor);
		head.setHorizontalAlignment(Element.ALIGN_CENTER);
		head.setVerticalAlignment(Element.ALIGN_MIDDLE);
		head.setColspan(3);
		tabelaFunc.addCell(head);
		head = new PdfPCell(new Phrase(total, f2));
		head.setHorizontalAlignment(Element.ALIGN_CENTER);
		head.setVerticalAlignment(Element.ALIGN_MIDDLE);
		head.setColspan(3);
		tabelaFunc.addCell(head);
		tabelaFunc.setHeaderRows(2);
		tabelaFunc.setFooterRows(1);
		tabelaFunc.setSkipFirstHeader(false);
		tabelaFunc.setSkipLastFooter(false);
		head = new PdfPCell(new Phrase("Nome", fonttable2));
		head.setBackgroundColor(minhacor);
		head.setHorizontalAlignment(Element.ALIGN_CENTER);
		head.setVerticalAlignment(Element.ALIGN_MIDDLE);
		tabelaFunc.addCell(head);
		head = new PdfPCell(new Phrase("Data Admissão", fonttable2));
		head.setBackgroundColor(minhacor);
		head.setHorizontalAlignment(Element.ALIGN_CENTER);
		head.setVerticalAlignment(Element.ALIGN_MIDDLE);
		tabelaFunc.addCell(head);
		head = new PdfPCell(new Phrase("Salario", fonttable2));
		head.setBackgroundColor(minhacor);
		head.setHorizontalAlignment(Element.ALIGN_CENTER);
		head.setVerticalAlignment(Element.ALIGN_MIDDLE);
		tabelaFunc.addCell(head);
		
		if(!funcionarios.isEmpty()){
			for(Funcionario funcionario : funcionarios){
				head = new PdfPCell(new Phrase(funcionario.getNome(), f3));
				head.setVerticalAlignment(Element.ALIGN_LEFT);
				tabelaFunc.addCell(head);
			
				String dataadmissao = formatar.format(funcionario.getDataAdmissao());
				head = new PdfPCell(new Phrase(dataadmissao, f3));
				head.setVerticalAlignment(Element.ALIGN_MIDDLE);
				head.setHorizontalAlignment(Element.ALIGN_CENTER);
				tabelaFunc.addCell(head);
			
				String salario = null;
				if(funcionario.getSalario() == null){
					salario = "";
				}else{
					salario = "R$" + funcionario.getSalario().toString();
				}
				head = new PdfPCell(new Phrase(salario, f3));
				head.setVerticalAlignment(Element.ALIGN_MIDDLE);
				head.setHorizontalAlignment(Element.ALIGN_CENTER);
				tabelaFunc.addCell(head);
				
				totalfuncs = totalfuncs.add(funcionario.getSalario());
			}
		}
		
		Paragraph p6 = new Paragraph("Ganhos", f2);
		p6.setSpacingAfter(10);
		p6.setAlignment(Element.ALIGN_CENTER);
		
		Paragraph p7 = new Paragraph("\u2022 " + "Total Arrecadado no Período: R$" + totalrecebido, f2);
		p7.setSpacingAfter(20);
		
		pdf.setMargins(30, 30, 50, 30);
		pdf.open();
		pdf.add(p);
		pdf.add(linha);
		pdf.add(p2);
		pdf.add(p3);
		pdf.add(p4);
		pdf.add(p5);
		pdf.add(tabelaFunc);
		pdf.newPage();
		pdf.add(p6);
		pdf.add(p7);
		
	}
	
	public void BuscarAgendamentos(){
		if((datainicialAg == null) && (datafinalAg == null)){
			Date hoje = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(hoje);
			
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 3);
			Date passado = cal.getTime();
			
			BuscarAgendamentosLazy(passado, hoje);
			totalagendamentos = am.ContarTodosAgendamentosLazyParaRelatorio(passado, hoje, empresaid);
			agendamentosParaRelatorios = am.BuscarTodosAgendamentosParaRelatorio(passado, hoje, empresaid);
			periodoagendamentos = "Agendamentos Feitos nos Últimos 3 Meses";

		}else{
			boolean b = datainicialAg.after(datafinalAg);
			if(b == true){
				messages.error("Atenção! A Data Inicial Deve Ser Antes da Data Final");
			}else{
				BuscarAgendamentosLazy(datainicialAg, datafinalAg);
				totalagendamentos = am.ContarTodosAgendamentosLazyParaRelatorio(datainicialAg, datafinalAg, empresaid);
				agendamentosParaRelatorios = am.BuscarTodosAgendamentosParaRelatorio(datainicialAg, datafinalAg, empresaid);
				int periodo = datafinalAg.getMonth() - datainicialAg.getMonth();
				if((periodo == 0) || (periodo == 1)){
					periodoagendamentos = "Agendamentos Feitos no Último Mes";
				}else{
					periodoagendamentos = "Agendamentos Feitos nos Últimos " + periodo +  " Meses";
				}
			}
		}
	}
	
	public void BuscarAgendamentosLazy(Date init, Date finish){
		
		agendamentos = new LazyDataModel<Agendamento>(){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			List<Agendamento> agendas;
			
			public List<Agendamento> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, Object> filters){
				
				agendas = am.BuscarTodosAgendamentosLazyParaRelatorio(init, finish, empresaid, first, pageSize);
				
				setRowCount(am.ContarTodosAgendamentosLazyParaRelatorio(init, finish, empresaid));
				
				return agendas;
				
			}

			@Override
			public Object getRowKey(Agendamento agenda) {
				return agenda.getId();
			}

			@Override
			public Agendamento getRowData(String agendaid) {
				
				Long id = Long.valueOf(agendaid);
				
				for(Agendamento a : agendas ){
					if(id.equals(a.getId())){
						return a;
					}
				}
				
				return null;
			}	
		};
	}
	
	public void preProcessPDFAg(Object document) throws DocumentException, IOException, BadElementException {
		Document pdf = (Document) document;
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		String logo = ec.getRealPath("") + File.separator + "resources" + File.separator + "mmw" + File.separator + "images" + File.separator + "logo-oficial.png"; 
		 
		Image img = Image.getInstance(logo);
		img.setAlignment(Element.ALIGN_LEFT);
		img.scaleAbsolute(150, 100);
		
		Font f = new Font();
		f.setSize(22);
		f.setFamily("Calibri");
		f.setStyle("bold");
		
		Font f2 = new Font();
		f2.setSize(14);
		f2.setFamily("Calibri");
		f2.setStyle("bold");
		
		Font f3 = new Font();
		f3.setSize(12);
		f3.setFamily("Calibri");
		
		Font f4 = new Font();
		f4.setSize(11);
		f4.setFamily("Calibri");
		f4.setColor(Color.green.darker());
		
		Font fonttable = new Font();
		fonttable.setSize(14);
		fonttable.setFamily("Calibri");
		fonttable.setColor(Color.WHITE);
		
		Font fonttable2 = new Font();
		fonttable2.setSize(12);
		fonttable2.setFamily("Calibri");
		fonttable2.setColor(Color.BLUE);
		
		/**
		 * data inicial e final
		 */
		
		String init = null;
		String finish = null;
		SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
		if((datainicialAg == null) && (datafinalAg == null)){
			Date hoje = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(hoje);
			
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 3);
			Date passado = cal.getTime();
		
		//SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
		init = formatar.format(passado);
		finish = formatar.format(hoje);
		
		}else{
			//SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
			init = formatar.format(datainicialAg);
			finish = formatar.format(datafinalAg);
		}
		
		
		/**
		 * Header
		 */
		
		Chunk chunk = new Chunk(img, 0, -45);
		HeaderFooter header = new HeaderFooter(new Phrase(chunk), false);
		header.setAlignment(Element.ALIGN_LEFT);
		header.setBorder(Rectangle.NO_BORDER);
		pdf.setHeader(header);
		
		/**
		 * Rodape
		 */
		Paragraph fraserodape = new Paragraph();
		fraserodape.add(new Paragraph("MMW SISTEMAS", f4));
		fraserodape.add(new Paragraph("www.mmwsistemas.com.br", f4));
		fraserodape.add(new Paragraph("Telefone: (22) 99715-8310", f4));
		HeaderFooter footer = new HeaderFooter(fraserodape, false);
		footer.setAlignment(Element.ALIGN_CENTER);
		pdf.setFooter(footer);
		
		LineSeparator linha = new LineSeparator();
		
		Paragraph p = new Paragraph("MMW Agendamentos - Relatorio dos Agendamentos ", f);
		p.setAlignment(Element.ALIGN_CENTER);
		p.setSpacingAfter(20);
		
		Paragraph p3 = new Paragraph("\u2022 " + "Relatorio Gerado dia: " + today + " às " + hora, f3);
		p3.setSpacingAfter(10);
		
		Paragraph p4 = new Paragraph("\u2022 " + "Periodo Base: De " + init + " Até " + finish, f3);
		p4.setSpacingAfter(20);
		
		Paragraph p5 = new Paragraph("Relatórios de Cada Agendamento", f2);
		p5.setSpacingAfter(20);
		p5.setAlignment(Element.ALIGN_CENTER);
		
		Paragraph p6 = new Paragraph("Agendamentos Feitos no Período e seus Devidos Status", f2);
		p6.setSpacingAfter(20);
		p6.setAlignment(Element.ALIGN_CENTER);
		
		/**
		 * Tabela dos Relatorios
		 */
		
		Color minhacor = WebColors.getRGBColor("#008000");
		
		float[] tamanhoColunas = {2, 2, 2, 8};
		PdfPTable tabelaRelatorios = new PdfPTable(tamanhoColunas);
		tabelaRelatorios.setWidthPercentage(100);
		PdfPCell cliente = new PdfPCell(new Paragraph("Cliente", fonttable));
		PdfPCell motivo = new PdfPCell(new Paragraph("Motivo", fonttable));
		PdfPCell dataagendada = new PdfPCell(new Paragraph("Data Agendada", fonttable));
		PdfPCell relatorio = new PdfPCell(new Paragraph("Relatorio do Agendamento", fonttable));
		cliente.setBackgroundColor(minhacor);
		motivo.setBackgroundColor(minhacor);
		dataagendada.setBackgroundColor(minhacor);
		relatorio.setBackgroundColor(minhacor);
		cliente.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cliente.setHorizontalAlignment(Element.ALIGN_CENTER);
		motivo.setVerticalAlignment(Element.ALIGN_MIDDLE);
		motivo.setHorizontalAlignment(Element.ALIGN_CENTER);
		dataagendada.setVerticalAlignment(Element.ALIGN_MIDDLE);
		dataagendada.setHorizontalAlignment(Element.ALIGN_CENTER);
		relatorio.setVerticalAlignment(Element.ALIGN_MIDDLE);
		relatorio.setHorizontalAlignment(Element.ALIGN_CENTER);
		
		tabelaRelatorios.addCell(cliente);
		tabelaRelatorios.addCell(motivo);
		tabelaRelatorios.addCell(dataagendada);
		tabelaRelatorios.addCell(relatorio);
		
		for(Agendamento a: agendamentosParaRelatorios){
			if(a.getResumo() != null){
			String nome = a.getCliente().getNome();
			String mot = a.getMotivo();
			Date d = a.getDataAgendada();
			String data = formatar.format(d);
			String rel = a.getResumo();
			//String rel = " ";
			
			/*if(a.getResumo() != null){
				rel = a.getResumo();
			}*/
			
			PdfPCell cell = new PdfPCell(new Paragraph(nome, fonttable2));
			tabelaRelatorios.addCell(cell);
			cell = new PdfPCell(new Paragraph(mot, fonttable2));
			tabelaRelatorios.addCell(cell);
			cell = new PdfPCell(new Paragraph(data, fonttable2));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tabelaRelatorios.addCell(cell);
			cell = new PdfPCell(new Paragraph(rel, fonttable2));
			tabelaRelatorios.addCell(cell);
			}
		}

		tabelaRelatorios.setSpacingAfter(20);
		
		pdf.setMargins(30, 30, 70, 30);
		pdf.open();
		pdf.add(p);
		pdf.add(linha);
		pdf.add(p3);
		pdf.add(p4);
		pdf.add(p5);
		pdf.add(tabelaRelatorios);
		pdf.newPage();
		pdf.add(p6);
		
	}
	
	public List<Cliente> CompletarNomeCliente(String query){
		
		ClienteManager cm = new ClienteManager();
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
}
