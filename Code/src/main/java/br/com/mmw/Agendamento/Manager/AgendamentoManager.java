package br.com.mmw.Agendamento.Manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.mmw.Agendamento.Model.Agendamento;
import br.com.mmw.Agendamento.Util.JpaUtil;

public class AgendamentoManager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void CadastrarAgendamento(Agendamento a) {

		EntityManager em = JpaUtil.getEntityManager();

		try {
			a.setDataCriada(new Date());
			a.setCancelado(false);
			a.setFinalizado(false);
			em.getTransaction().begin();
			em.persist(a);
			em.getTransaction().commit();

		} catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();

		} finally {

			em.close();
		}

	}

	public List<Agendamento> BuscarTodosAgendamentos(long empresaid) {

		EntityManager em = JpaUtil.getEntityManager();

		List<Agendamento> resultado = new ArrayList<Agendamento>();

		try {
			String consulta = "select a from Agendamento a where a.empresaCliente.id = :empresaid";
			TypedQuery<Agendamento> query = em.createQuery(consulta, Agendamento.class);
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
	
	public List<Agendamento> BuscarTodosAgendamentosLazy(Date datainicial, Date datafinal, long empresaid) {

		EntityManager em = JpaUtil.getEntityManager();

		List<Agendamento> resultado = new ArrayList<Agendamento>();

		try {
			String consulta = "select a from Agendamento a where a.dataAgendada between :datainicial and :datafinal and a.empresaCliente.id = :empresaid";
			TypedQuery<Agendamento> query = em.createQuery(consulta, Agendamento.class);
			query.setParameter("datainicial", datainicial);
			query.setParameter("datafinal", datafinal);
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
	
	public int ContarTodosAgendamentosLazyParaRelatorio(Date datainicial, Date datafinal, long empresaid) {

		EntityManager em = JpaUtil.getEntityManager();

		Number resultado = 0;

		try {
			String consulta = "select count(a) from Agendamento a where a.dataAgendada between :datainicial and :datafinal and a.empresaCliente.id = :empresaid";
			TypedQuery<Number> query = em.createQuery(consulta, Number.class);
			query.setParameter("datainicial", datainicial);
			query.setParameter("datafinal", datafinal);
			query.setParameter("empresaid", empresaid);
			resultado = query.getSingleResult();

		} catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();

		} finally {

			em.close();
		}

		return resultado.intValue();
	}
	
	public List<Agendamento> BuscarTodosAgendamentosLazyParaRelatorio(Date datainicial, Date datafinal, long empresaid, int first, int maxpage) {

		EntityManager em = JpaUtil.getEntityManager();

		List<Agendamento> resultado = new ArrayList<Agendamento>();

		try {
			String consulta = "select a from Agendamento a where a.dataAgendada between :datainicial and :datafinal and a.empresaCliente.id = :empresaid";
			TypedQuery<Agendamento> query = em.createQuery(consulta, Agendamento.class);
			query.setParameter("datainicial", datainicial);
			query.setParameter("datafinal", datafinal);
			query.setParameter("empresaid", empresaid);
			query.setFirstResult(first);
			query.setMaxResults(maxpage);
			resultado = query.getResultList();

		} catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();

		} finally {

			em.close();
		}

		return resultado;
	}
	
	public List<Agendamento> BuscarTodosAgendamentosParaRelatorio(Date datainicial, Date datafinal, long empresaid) {

		EntityManager em = JpaUtil.getEntityManager();

		List<Agendamento> resultado = new ArrayList<Agendamento>();

		try {
			String consulta = "select a from Agendamento a where a.dataAgendada between :datainicial and :datafinal and a.empresaCliente.id = :empresaid";
			TypedQuery<Agendamento> query = em.createQuery(consulta, Agendamento.class);
			query.setParameter("datainicial", datainicial);
			query.setParameter("datafinal", datafinal);
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
	
	public List<Agendamento> BuscarAgendamentosComFiltrosLazy(Date datainicial, Date datafinal, long empresaid, long clienteid, long funcionarioid, String status) {

		EntityManager em = JpaUtil.getEntityManager();

		List<Agendamento> resultado = new ArrayList<Agendamento>();

		try {
			/**
			 * caso somente o status seja marcado
			 */
			if((clienteid == 0) && (status != null)){
				if(status.equals("Agendado")){
					String consulta = "select a from Agendamento a where a.dataAgendada between :datainicial and :datafinal and a.empresaCliente.id = :empresaid and a.finalizado = false and a.cancelado = false";
					TypedQuery<Agendamento> query = em.createQuery(consulta, Agendamento.class);
					query.setParameter("datainicial", datainicial);
					query.setParameter("datafinal", datafinal);
					query.setParameter("empresaid", empresaid);
					resultado = query.getResultList();
				}
				
				if(status.equals("Finalizado")){
					String consulta = "select a from Agendamento a where a.dataAgendada between :datainicial and :datafinal and a.empresaCliente.id = :empresaid and a.finalizado = true and a.cancelado = false";
					TypedQuery<Agendamento> query = em.createQuery(consulta, Agendamento.class);
					query.setParameter("datainicial", datainicial);
					query.setParameter("datafinal", datafinal);
					query.setParameter("empresaid", empresaid);
					resultado = query.getResultList();
				}

				if(status.equals("Cancelado")){
					String consulta = "select a from Agendamento a where a.dataAgendada between :datainicial and :datafinal and a.empresaCliente.id = :empresaid and a.finalizado = false and a.cancelado = true";
					TypedQuery<Agendamento> query = em.createQuery(consulta, Agendamento.class);
					query.setParameter("datainicial", datainicial);
					query.setParameter("datafinal", datafinal);
					query.setParameter("empresaid", empresaid);
					resultado = query.getResultList();
				}
			}
			
			/**
			 * so marcou o cliente
			 */
			if((clienteid > 0) && (status == null)){
				String consulta = "select a from Agendamento a where a.dataAgendada between :datainicial and :datafinal and a.cliente.id = :clienteid and a.empresaCliente.id = :empresaid";
				TypedQuery<Agendamento> query = em.createQuery(consulta, Agendamento.class);
				query.setParameter("datainicial", datainicial);
				query.setParameter("datafinal", datafinal);
				query.setParameter("clienteid", clienteid);
				query.setParameter("empresaid", empresaid);
				resultado = query.getResultList();
			}
			
			/**
			 * so marcou funcionario e status
			 */
			
			if((clienteid == 0) && (status != null) && (funcionarioid > 0)){
				if(status.equals("Agendado")){
					String consulta = "select a from Agendamento a where a.dataAgendada between :datainicial and :datafinal and a.funcionario.id = :funcionarioid and a.empresaCliente.id = :empresaid and a.finalizado = false and a.cancelado = false";
					TypedQuery<Agendamento> query = em.createQuery(consulta, Agendamento.class);
					query.setParameter("datainicial", datainicial);
					query.setParameter("datafinal", datafinal);
					query.setParameter("funcionarioid", funcionarioid);
					query.setParameter("empresaid", empresaid);
					resultado = query.getResultList();
				}
				
				if(status.equals("Finalizado")){
					String consulta = "select a from Agendamento a where a.dataAgendada between :datainicial and :datafinal and a.funcionario.id = :funcionarioid and  a.empresaCliente.id = :empresaid and a.finalizado = true and a.cancelado = false";
					TypedQuery<Agendamento> query = em.createQuery(consulta, Agendamento.class);
					query.setParameter("datainicial", datainicial);
					query.setParameter("datafinal", datafinal);
					query.setParameter("funcionarioid", funcionarioid);
					query.setParameter("empresaid", empresaid);
					resultado = query.getResultList();
				}

				if(status.equals("Cancelado")){
					String consulta = "select a from Agendamento a where a.dataAgendada between :datainicial and :datafinal and a.funcionario.id = :funcionarioid and a.empresaCliente.id = :empresaid and a.finalizado = false and a.cancelado = true";
					TypedQuery<Agendamento> query = em.createQuery(consulta, Agendamento.class);
					query.setParameter("datainicial", datainicial);
					query.setParameter("datafinal", datafinal);
					query.setParameter("funcionarioid", funcionarioid);
					query.setParameter("empresaid", empresaid);
					resultado = query.getResultList();
				}
			}
			
			/**
			 * marcou status e cliente
			 */
			if((clienteid > 0) && (status != null)){
				if(status.equals("Agendado")){
					String consulta = "select a from Agendamento a where a.dataAgendada between :datainicial and :datafinal and a.cliente.id = :clienteid and a.empresaCliente.id = :empresaid and a.finalizado = false and a.cancelado = false";
					TypedQuery<Agendamento> query = em.createQuery(consulta, Agendamento.class);
					query.setParameter("datainicial", datainicial);
					query.setParameter("datafinal", datafinal);
					query.setParameter("clienteid", clienteid);
					query.setParameter("empresaid", empresaid);
					resultado = query.getResultList();
				}
				
				if(status.equals("Finalizado")){
					String consulta = "select a from Agendamento a where a.dataAgendada between :datainicial and :datafinal and a.cliente.id = :clienteid and  a.empresaCliente.id = :empresaid and a.finalizado = true and a.cancelado = false";
					TypedQuery<Agendamento> query = em.createQuery(consulta, Agendamento.class);
					query.setParameter("datainicial", datainicial);
					query.setParameter("datafinal", datafinal);
					query.setParameter("clienteid", clienteid);
					query.setParameter("empresaid", empresaid);
					resultado = query.getResultList();
				}

				if(status.equals("Cancelado")){
					String consulta = "select a from Agendamento a where a.dataAgendada between :datainicial and :datafinal and a.cliente.id = :clienteid and a.empresaCliente.id = :empresaid and a.finalizado = false and a.cancelado = true";
					TypedQuery<Agendamento> query = em.createQuery(consulta, Agendamento.class);
					query.setParameter("datainicial", datainicial);
					query.setParameter("datafinal", datafinal);
					query.setParameter("clienteid", clienteid);
					query.setParameter("empresaid", empresaid);
					resultado = query.getResultList();
				}
			}
			
			/**
			 * marcou os tres filtros
			 */
			if((clienteid > 0) && (status != null) && (funcionarioid > 0)){
				if(status.equals("Agendado")){
					String consulta = "select a from Agendamento a where a.dataAgendada between :datainicial and :datafinal and a.cliente.id = :clienteid and a.funcionario.id = :funcionarioid and  a.empresaCliente.id = :empresaid and a.finalizado = false and a.cancelado = false";
					TypedQuery<Agendamento> query = em.createQuery(consulta, Agendamento.class);
					query.setParameter("datainicial", datainicial);
					query.setParameter("datafinal", datafinal);
					query.setParameter("clienteid", clienteid);
					query.setParameter("funcionarioid", funcionarioid);
					query.setParameter("empresaid", empresaid);
					resultado = query.getResultList();
				}
				
				if(status.equals("Finalizado")){
					String consulta = "select a from Agendamento a where a.dataAgendada between :datainicial and :datafinal and a.cliente.id = :clienteid and a.funcionario.id = :funcionarioid and  a.empresaCliente.id = :empresaid and a.finalizado = true and a.cancelado = false";
					TypedQuery<Agendamento> query = em.createQuery(consulta, Agendamento.class);
					query.setParameter("datainicial", datainicial);
					query.setParameter("datafinal", datafinal);
					query.setParameter("clienteid", clienteid);
					query.setParameter("funcionarioid", funcionarioid);
					query.setParameter("empresaid", empresaid);
					resultado = query.getResultList();
				}

				if(status.equals("Cancelado")){
					String consulta = "select a from Agendamento a where a.dataAgendada between :datainicial and :datafinal and a.cliente.id = :clienteid and a.funcionario.id = :funcionarioid and a.empresaCliente.id = :empresaid and a.finalizado = false and a.cancelado = true";
					TypedQuery<Agendamento> query = em.createQuery(consulta, Agendamento.class);
					query.setParameter("datainicial", datainicial);
					query.setParameter("datafinal", datafinal);
					query.setParameter("clienteid", clienteid);
					query.setParameter("funcionarioid", funcionarioid);
					query.setParameter("empresaid", empresaid);
					resultado = query.getResultList();
				}
			}

		} catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();

		} finally {

			em.close();
		}

		return resultado;
	}
	
	public List<Agendamento> BuscarAgendamentoPorID(long id, long empresaid){
		
		EntityManager em = JpaUtil.getEntityManager();
		
		List<Agendamento> resultado = new ArrayList<Agendamento>();
		
		try{
			String consulta = "select a from Agendamento a where a.id = :id and a.empresaCliente.id = :empresaid";
			TypedQuery<Agendamento> query = em.createQuery(consulta, Agendamento.class);
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
	
	public List<Agendamento> BuscarAgendamentoPorPeriodo(long empresaid, Date inicio, Date fim){
		
		EntityManager em = JpaUtil.getEntityManager();
		
		List<Agendamento> resultado = new ArrayList<Agendamento>();
		
		try{
			String consulta = "select a from Agendamento a where a.empresaCliente.id = :empresaid and a.dataAgendada between :inicio and :fim";
			TypedQuery<Agendamento> query = em.createQuery(consulta, Agendamento.class);
			query.setParameter("empresaid", empresaid);
			query.setParameter("inicio", inicio);
			query.setParameter("fim", fim);
			resultado = query.getResultList();
		
		}catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();
		
		}finally{
			
			em.close();
		}
		
		return resultado;
		
	}
	
	public List<Agendamento> BuscarAgendamentoPorServico(long servicoid){
		
		EntityManager em = JpaUtil.getEntityManager();
		
		List<Agendamento> resultado = new ArrayList<Agendamento>();
		
		try{
			String consulta = "select a from Agendamento a where a.servico.id = :servicoid";
			TypedQuery<Agendamento> query = em.createQuery(consulta, Agendamento.class);
			query.setParameter("servicoid", servicoid);
			resultado = query.getResultList();
		
		}catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();
		
		}finally{
			
			em.close();
		}
		
		return resultado;
		
	}
	
	public void AtualizarAgendamento(Agendamento a) {

		EntityManager em = JpaUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			em.merge(a);
			em.getTransaction().commit();

		} catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();

		} finally {

			em.close();
		}

	}
	
	public void RemoverAgendamento(long id){
		
		EntityManager em = JpaUtil.getEntityManager();
		
		Agendamento a = em.find(Agendamento.class, id);
		
		try{
			em.getTransaction().begin();
			em.remove(a);
			em.getTransaction().commit();
			

		}catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();
		
		}finally{
			
			em.close();
		}
		
	}

}
