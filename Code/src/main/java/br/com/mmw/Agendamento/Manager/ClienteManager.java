package br.com.mmw.Agendamento.Manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.mmw.Agendamento.Model.Cliente;
import br.com.mmw.Agendamento.Model.ClienteFisico;
import br.com.mmw.Agendamento.Model.ClienteJuridico;
import br.com.mmw.Agendamento.Util.JpaUtil;

public class ClienteManager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void CadastrarCliente(Cliente c) {

		EntityManager em = JpaUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(c);
			em.getTransaction().commit();

		} catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();

		} finally {

			em.close();
		}

	}

	public List<Cliente> BuscarTodosClientes(long empresaid) {

		EntityManager em = JpaUtil.getEntityManager();

		List<Cliente> resultado = new ArrayList<Cliente>();

		try {
			String consulta = "select c from Cliente c where c.empresaCliente.id = :empresaid";
			TypedQuery<Cliente> query = em.createQuery(consulta, Cliente.class);
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
	
	public List<ClienteFisico> BuscarTodosClientesFisicos(long empresaid) {

		EntityManager em = JpaUtil.getEntityManager();

		List<ClienteFisico> resultado = new ArrayList<ClienteFisico>();

		try {
			String consulta = "select c from ClienteFisico c where c.empresaCliente.id = :empresaid";
			TypedQuery<ClienteFisico> query = em.createQuery(consulta, ClienteFisico.class);
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
	
	public List<ClienteJuridico> BuscarTodosClientesJuridicos(long empresaid) {

		EntityManager em = JpaUtil.getEntityManager();

		List<ClienteJuridico> resultado = new ArrayList<ClienteJuridico>();

		try {
			String consulta = "select c from ClienteJuridico c where c.empresaCliente.id = :empresaid";
			TypedQuery<ClienteJuridico> query = em.createQuery(consulta, ClienteJuridico.class);
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
	
	public int ContarTodosClientesFisicos(long empresaid) {

		EntityManager em = JpaUtil.getEntityManager();

		Number resultado = 0;

		try {
			String consulta = "select count(c) from ClienteFisico c where c.empresaCliente.id = :empresaid";
			TypedQuery<Number> query = em.createQuery(consulta, Number.class);
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
	
	public int ContarTodosClientesJuridicos(long empresaid) {

		EntityManager em = JpaUtil.getEntityManager();

		Number resultado = 0;

		try {
			String consulta = "select count(c) from ClienteJuridico c where c.empresaCliente.id = :empresaid";
			TypedQuery<Number> query = em.createQuery(consulta, Number.class);
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
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<ClienteFisico> BuscarTodosClientesFisicosLazy(long empresaid, int first, int maxpage, Map filtros){
		
		EntityManager em = JpaUtil.getEntityManager();
		
		List<ClienteFisico> resultado = new ArrayList<ClienteFisico>();
		
		try{
			Session s = em.unwrap(Session.class);
			Criteria criteria = s.createCriteria(ClienteFisico.class);
			Criteria empCrit = criteria.createCriteria("empresaCliente");
			empCrit.add(Restrictions.eq("id", empresaid));
			if(!filtros.isEmpty()){
				Iterator<Entry> itr = filtros.entrySet().iterator();
				while(itr.hasNext()){
					Entry entry = itr.next();
					criteria.add(Restrictions.ilike(entry.getKey().toString(), entry.getValue().toString(), MatchMode.ANYWHERE));
				}
			}
			
			criteria.setFirstResult(first);
			criteria.setMaxResults(maxpage);
			resultado = criteria.list();
			
		}catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();
		
		}finally{
			
			em.close();
		}
		
		return resultado;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<ClienteJuridico> BuscarTodosClientesJuridicosLazy(long empresaid, int first, int maxpage, Map filtros){
		
		EntityManager em = JpaUtil.getEntityManager();
		
		List<ClienteJuridico> resultado = new ArrayList<ClienteJuridico>();
		
		try{
			Session s = em.unwrap(Session.class);
			Criteria criteria = s.createCriteria(ClienteJuridico.class);
			Criteria empCrit = criteria.createCriteria("empresaCliente");
			empCrit.add(Restrictions.eq("id", empresaid));
			if(!filtros.isEmpty()){
				Iterator<Entry> itr = filtros.entrySet().iterator();
				while(itr.hasNext()){
					Entry entry = itr.next();
					criteria.add(Restrictions.ilike(entry.getKey().toString(), entry.getValue().toString(), MatchMode.ANYWHERE));
				}
			}
			
			criteria.setFirstResult(first);
			criteria.setMaxResults(maxpage);
			resultado = criteria.list();
			
		}catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();
		
		}finally{
			
			em.close();
		}
		
		return resultado;
	}
	
	public List<Cliente> BuscarClientePorID(long id, long empresaid){
		
		EntityManager em = JpaUtil.getEntityManager();
		
		List<Cliente> resultado = new ArrayList<Cliente>();
		
		try{
			String consulta = "select c from Cliente c where c.id = :id and c.empresaCliente.id = :empresaid";
			TypedQuery<Cliente> query = em.createQuery(consulta, Cliente.class);
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
	
	public Cliente BuscarClientePorIDConverter(long id){
		
		EntityManager em = JpaUtil.getEntityManager();
		
		Cliente c = null;
		List<Cliente> resultado = new ArrayList<Cliente>();
		
		try{
			String consulta = "select c from Cliente c where c.id = :id ";
			TypedQuery<Cliente> query = em.createQuery(consulta, Cliente.class);
			query.setParameter("id", id);
			resultado = query.getResultList();
			
			if(resultado.isEmpty()){
				System.out.println("Cliente Nao encontrado");
			}else{
				
				Iterator<Cliente> itr = resultado.iterator();
				for(int i=0; i<resultado.size(); i++){
					while(itr.hasNext()){
						c = itr.next();
					}
				}
			}
		
		}catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();
		
		}finally{
			
			em.close();
		}
		
		return c;
		
	}
	
	public void AtualizarCliente(Cliente c) {

		EntityManager em = JpaUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			em.merge(c);
			em.getTransaction().commit();

		} catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();

		} finally {

			em.close();
		}

	}
	
	public void RemoverCliente(long id){
		
		EntityManager em = JpaUtil.getEntityManager();
		
		Cliente c = em.find(Cliente.class, id);
		
		try{
			em.getTransaction().begin();
			em.remove(c);
			em.getTransaction().commit();
			

		}catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();
		
		}finally{
			
			em.close();
		}	
	}
}
