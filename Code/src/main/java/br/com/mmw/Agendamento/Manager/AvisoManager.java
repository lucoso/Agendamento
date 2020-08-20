package br.com.mmw.Agendamento.Manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.mmw.Agendamento.Model.Aviso;
import br.com.mmw.Agendamento.Util.JpaUtil;

public class AvisoManager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void CadastrarAviso(Aviso a) {

		EntityManager em = JpaUtil.getEntityManager();

		a.setDataCriado(new Date());
		a.setImportante(false);
		a.setFinalizado(false);
		
		try {
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
	
	public void CadastrarAvisoImportante(Aviso a) {

		EntityManager em = JpaUtil.getEntityManager();

		a.setDataCriado(new Date());
		a.setImportante(true);
		
		try {
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
	
	public List<Aviso> BuscarTodosAvisosImportantes(long empresaid) {

		EntityManager em = JpaUtil.getEntityManager();

		List<Aviso> resultado = new ArrayList<Aviso>();

		try {
			String consulta = "select a from Aviso a where a.empresaCliente.id = :empresaid and a.importante = true";
			TypedQuery<Aviso> query = em.createQuery(consulta, Aviso.class);
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

	public List<Aviso> BuscarAvisoPorID(long id, long empresaid){
		
		EntityManager em = JpaUtil.getEntityManager();
		
		List<Aviso> resultado = new ArrayList<Aviso>();
		
		try{
			String consulta = "select a from Aviso a where a.id = :id and a.empresaCliente.id = :empresaid";
			TypedQuery<Aviso> query = em.createQuery(consulta, Aviso.class);
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
	
	public List<Aviso> BuscarAvisoDoDia(long empresaid, Date hoje){
		
		EntityManager em = JpaUtil.getEntityManager();
		
		List<Aviso> resultado = new ArrayList<Aviso>();
		
		try{
			String consulta = "select a from Aviso a where a.data = :hoje and a.empresaCliente.id = :empresaid";
			TypedQuery<Aviso> query = em.createQuery(consulta, Aviso.class);
			query.setParameter("hoje", hoje);
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
	
	/*public List<Aviso> BuscarAvisoPorPeriodo(long empresaid, Date datainicial, Date datafinal){
		
		EntityManager em = JpaUtil.getEntityManager();
		
		List<Aviso> resultado = new ArrayList<Aviso>();
		
		try{
			String consulta = "select a from Aviso a where a.data between :datainicial and :datafinal and a.empresaCliente.id = :empresaid order by a.data";
			TypedQuery<Aviso> query = em.createQuery(consulta, Aviso.class);
			query.setParameter("datainicial", datainicial);
			query.setParameter("datafinal", datafinal);
			query.setParameter("empresaid", empresaid);
			resultado = query.getResultList();
		
		}catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();
		
		}finally{
			
			em.close();
		}
		
		return resultado;
		
	}*/
	
	public int QuantidadeBuscarAvisosPorPeriodo(long empresaid, Date datainicial, Date datafinal){

		EntityManager em = JpaUtil.getEntityManager();
		
		Number resultado = 0;
		
		try{
			String consulta = "select count(a) from Aviso a where a.data between :datainicial and :datafinal and a.empresaCliente.id = :empresaid";
			TypedQuery<Number> query = em.createQuery(consulta, Number.class);
			query.setParameter("datainicial", datainicial);
			query.setParameter("datafinal", datafinal);
			query.setParameter("empresaid", empresaid);
			resultado = query.getSingleResult();
		
		}catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();
		
		}finally{
			
			em.close();
		}
		
		return resultado.intValue();
	}
	
	public List<Aviso> BuscarAvisosPorPeriodoLazy(long empresaid, Date datainicial, Date datafinal, int first, int maxpage){
		
		EntityManager em = JpaUtil.getEntityManager();
		
		List<Aviso> resultado = new ArrayList<Aviso>();
		
		try{
			String consulta = "select a from Aviso a where a.data between :datainicial and :datafinal and a.empresaCliente.id = :empresaid order by a.data";
			TypedQuery<Aviso> query = em.createQuery(consulta, Aviso.class);
			query.setParameter("datainicial", datainicial);
			query.setParameter("datafinal", datafinal);
			query.setParameter("empresaid", empresaid);
			query.setFirstResult(first);
			query.setMaxResults(maxpage);
			resultado = query.getResultList();
		
		}catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();
		
		}finally{
			
			em.close();
		}
		
		return resultado;
	}

	public void AtualizarAviso(Aviso a) {

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
	
	public void RemoverAviso(long id){
		
		EntityManager em = JpaUtil.getEntityManager();
		
		Aviso a = em.find(Aviso.class, id);
		
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
