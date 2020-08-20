package br.com.mmw.Agendamento.Manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.mmw.Agendamento.Model.Servico;
import br.com.mmw.Agendamento.Util.JpaUtil;

public class ServicoManager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void CadastrarServico(Servico s) {

		EntityManager em = JpaUtil.getEntityManager();
		
		s.setFinalizado(false);
		s.setDataAbertura(new Date());

		try {
			em.getTransaction().begin();
			em.persist(s);
			em.getTransaction().commit();

		} catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();

		} finally {

			em.close();
		}

	}
	
	public Servico CadastrarServicoParaConcluirAgendamento(Servico s) {

		EntityManager em = JpaUtil.getEntityManager();
		
		Servico servico = null;
		s.setFinalizado(false);
		s.setDataAbertura(new Date());

		try {
			em.getTransaction().begin();
			em.persist(s);
			em.flush();
			servico = s;
			em.getTransaction().commit();

		} catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();

		} finally {

			em.close();
		}
		
		return servico;

	}

	public List<Servico> BuscarTodosServicos(long empresaid) {

		EntityManager em = JpaUtil.getEntityManager();

		List<Servico> resultado = new ArrayList<Servico>();

		try {
			String consulta = "select s from Servico s where s.empresaCliente.id = :empresaid";
			TypedQuery<Servico> query = em.createQuery(consulta, Servico.class);
			query.setParameter("empresaid", empresaid);
			resultado = query.getResultList();

		} catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();

		} finally {

			em.close();
		}

		return resultado;
	}
	
	public List<Servico> BuscarTodosServicosDoUltimoMes(long empresaid) {

		EntityManager em = JpaUtil.getEntityManager();

		List<Servico> resultado = new ArrayList<Servico>();
		
		Date hoje = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(hoje);
		
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
		Date passado = cal.getTime();

		try {
			String consulta = "select s from Servico s where s.dataAbertura between :datainicial and :datafinal and s.empresaCliente.id = :empresaid";
			TypedQuery<Servico> query = em.createQuery(consulta, Servico.class);
			query.setParameter("datainicial", passado);
			query.setParameter("datafinal", hoje);
			query.setParameter("empresaid", empresaid);
			resultado = query.getResultList();

		} catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();

		} finally {

			em.close();
		}

		return resultado;
	}
	
	public List<Servico> BuscarServicosPorPeriodo(long empresaid, Date inicio, Date fim) {

		EntityManager em = JpaUtil.getEntityManager();

		List<Servico> resultado = new ArrayList<Servico>();

		try {
			String consulta = "select s from Servico s where s.dataAbertura between :datainicial and :datafinal and s.empresaCliente.id = :empresaid";
			TypedQuery<Servico> query = em.createQuery(consulta, Servico.class);
			query.setParameter("datainicial", inicio);
			query.setParameter("datafinal", fim);
			query.setParameter("empresaid", empresaid);
			resultado = query.getResultList();

		} catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();

		} finally {

			em.close();
		}

		return resultado;
	}
	
	public List<Servico> BuscarServicosPorPeriodoECliente(long empresaid, Date inicio, Date fim, long clienteid) {

		EntityManager em = JpaUtil.getEntityManager();

		List<Servico> resultado = new ArrayList<Servico>();

		try {
			String consulta = "select s from Servico s where s.dataAbertura between :datainicial and :datafinal and s.empresaCliente.id = :empresaid and s.cliente.id = :clienteid";
			TypedQuery<Servico> query = em.createQuery(consulta, Servico.class);
			query.setParameter("datainicial", inicio);
			query.setParameter("datafinal", fim);
			query.setParameter("empresaid", empresaid);
			query.setParameter("clienteid", clienteid);
			resultado = query.getResultList();

		} catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();

		} finally {

			em.close();
		}

		return resultado;
	}

	public List<Servico> BuscarServicoPorID(long id, long empresaid){
		
		EntityManager em = JpaUtil.getEntityManager();
		
		List<Servico> resultado = new ArrayList<Servico>();
		
		try{
			String consulta = "select s from Servico s where s.id = :id and s.empresaCliente.id = :empresaid";
			TypedQuery<Servico> query = em.createQuery(consulta, Servico.class);
			query.setParameter("id", id);
			query.setParameter("empresaid", empresaid);
			resultado = query.getResultList();
		
		}catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();
		
		}finally{
			
			em.close();
		}
		
		return resultado;
		
	}
	
	public List<Servico> BuscarServicoPorCliente(String clientenome, long empresaid){
		
		EntityManager em = JpaUtil.getEntityManager();
		
		List<Servico> resultado = new ArrayList<Servico>();
		
		try{
			String consulta = "select s from Servico s where s.cliente.nome = :clientenome and s.empresaCliente.id = :empresaid";
			TypedQuery<Servico> query = em.createQuery(consulta, Servico.class);
			query.setParameter("clientenome", clientenome);
			query.setParameter("empresaid", empresaid);
			resultado = query.getResultList();
		
		}catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();
		
		}finally{
			
			em.close();
		}
		
		return resultado;
		
	}

	public List<Servico> BuscarServicoPorStatus(String status, long empresaid){
	
	EntityManager em = JpaUtil.getEntityManager();
	
	List<Servico> resultado = new ArrayList<Servico>();
	
	try{
		String consulta = "select s from Servico s where s.status = :status and s.empresaCliente.id = :empresaid";
		TypedQuery<Servico> query = em.createQuery(consulta, Servico.class);
		query.setParameter("status", status);
		query.setParameter("empresaid", empresaid);
		resultado = query.getResultList();
	
	}catch (Exception ex) {

		ex.printStackTrace();
		em.getTransaction().rollback();
	
	}finally{
		
		em.close();
	}
	
	return resultado;
	
}
	
	public void AtualizarServico(Servico s) {

		EntityManager em = JpaUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			em.merge(s);
			em.getTransaction().commit();

		} catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();

		} finally {

			em.close();
		}

	}
	
	public void RemoverServico(long id){
		
		EntityManager em = JpaUtil.getEntityManager();
		
		Servico s = em.find(Servico.class, id);
		
		try{
			em.getTransaction().begin();
			em.remove(s);
			em.getTransaction().commit();
			

		}catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();
		
		}finally{
			
			em.close();
		}	
	}

}
