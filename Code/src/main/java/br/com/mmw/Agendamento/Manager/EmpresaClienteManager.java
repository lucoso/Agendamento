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

import br.com.mmw.Agendamento.Model.EmpresaCliente;
import br.com.mmw.Agendamento.Util.JpaUtil;

public class EmpresaClienteManager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void CadastrarEmpresa(EmpresaCliente e) {

		EntityManager em = JpaUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(e);
			em.getTransaction().commit();

		} catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();

		} finally {

			em.close();
		}

	}

	public List<EmpresaCliente> BuscarTodasEmpresas() {

		EntityManager em = JpaUtil.getEntityManager();

		List<EmpresaCliente> resultado = new ArrayList<EmpresaCliente>();

		try {
			String consulta = "select e from EmpresaCliente e";
			TypedQuery<EmpresaCliente> query = em.createQuery(consulta, EmpresaCliente.class);
			resultado = query.getResultList();

		} catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();

		} finally {

			em.close();
		}

		return resultado;
	}
	
	public int ContarTodasEmpresas() {

		EntityManager em = JpaUtil.getEntityManager();

		Number resultado = 0;

		try {
			String consulta = "select count(e) from EmpresaCliente e";
			TypedQuery<Number> query = em.createQuery(consulta, Number.class);
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
	public List<EmpresaCliente> BuscarTodasEmpresasLazy(int first, int maxpage, Map filtros){
		
		EntityManager em = JpaUtil.getEntityManager();
		
		List<EmpresaCliente> resultado = new ArrayList<EmpresaCliente>();
		
		try{
			Session s = em.unwrap(Session.class);
			Criteria criteria = s.createCriteria(EmpresaCliente.class);
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

	public List<EmpresaCliente> BuscarEmpresaPorID(long id){
		
		EntityManager em = JpaUtil.getEntityManager();
		
		List<EmpresaCliente> resultado = new ArrayList<EmpresaCliente>();
		
		try{
			String consulta = "select e from EmpresaCliente e where e.id = :id";
			TypedQuery<EmpresaCliente> query = em.createQuery(consulta, EmpresaCliente.class);
			query.setParameter("id", id);
			resultado = query.getResultList();
		
		}catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();
		
		}finally{
			
			em.close();
		}
		
		return resultado;
		
	}
	
	public List<EmpresaCliente> BuscarEmpresaPorCPFOuCNPJ(String tipo, String busca){
		
		EntityManager em = JpaUtil.getEntityManager();
		
		List<EmpresaCliente> resultado = new ArrayList<EmpresaCliente>();
		
		try{
			if(tipo.equals("Fisico")){
				String consulta = "select e from EmpresaCliente e where e.cpf = :cpf";
				TypedQuery<EmpresaCliente> query = em.createQuery(consulta, EmpresaCliente.class);
				query.setParameter("cpf", busca);
				resultado = query.getResultList();
			}
			
			if(tipo.equals("Juridico")){
				String consulta = "select e from EmpresaCliente e where e.cnpj = :cnpj";
				TypedQuery<EmpresaCliente> query = em.createQuery(consulta, EmpresaCliente.class);
				query.setParameter("cnpj", busca);
				resultado = query.getResultList();
			}
			
		
		}catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();
		
		}finally{
			
			em.close();
		}
		
		return resultado;
		
	}
	
	public EmpresaCliente BuscarEmpresaPorIDConverter(long id){
		
		EntityManager em = JpaUtil.getEntityManager();
		
		EmpresaCliente e = null;
		List<EmpresaCliente> resultado = new ArrayList<EmpresaCliente>();
		
		try{
			String consulta = "select e from EmpresaCliente e where e.id = :id";
			TypedQuery<EmpresaCliente> query = em.createQuery(consulta, EmpresaCliente.class);
			query.setParameter("id", id);
			resultado = query.getResultList();
			
			if(resultado.isEmpty()){
				System.out.println("Empresa Nao encontrada");
			}else{
				
				Iterator<EmpresaCliente> itr = resultado.iterator();
				for(int i=0; i<resultado.size(); i++){
					while(itr.hasNext()){
						e = itr.next();
					}
				}
			}
		
		}catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();
		
		}finally{
			
			em.close();
		}
		
		return e;
		
	}
	
	public void AtualizarEmpresa(EmpresaCliente e) {

		EntityManager em = JpaUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			em.merge(e);
			em.getTransaction().commit();

		} catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();

		} finally {

			em.close();
		}

	}
	
	public void RemoverEmpresa(long id){
		
		EntityManager em = JpaUtil.getEntityManager();
		
		EmpresaCliente e = em.find(EmpresaCliente.class, id);
		
		try{
			em.getTransaction().begin();
			em.remove(e);
			em.getTransaction().commit();
			

		}catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();
		
		}finally{
			
			em.close();
		}	
	}

}
