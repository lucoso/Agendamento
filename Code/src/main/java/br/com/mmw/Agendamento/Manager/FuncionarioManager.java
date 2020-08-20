package br.com.mmw.Agendamento.Manager;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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

import br.com.mmw.Agendamento.Model.Funcionario;
import br.com.mmw.Agendamento.Util.JpaUtil;

public class FuncionarioManager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void CadastrarFuncionario(Funcionario f) {

		EntityManager em = JpaUtil.getEntityManager();
		
		if(f.getSalario() == null){
			f.setSalario(new BigDecimal(0));
		}
		
		if(f.getDataAdmissao() == null){
			f.setDataAdmissao(new Date());
		}

		try {
			em.getTransaction().begin();
			em.persist(f);
			em.getTransaction().commit();

		} catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();

		} finally {

			em.close();
		}

	}

	public List<Funcionario> BuscarTodosFuncionarios(long empresaid) {

		EntityManager em = JpaUtil.getEntityManager();

		List<Funcionario> resultado = new ArrayList<Funcionario>();

		try {
			String consulta = "select f from Funcionario f where f.empresaCliente.id = :empresaid";
			TypedQuery<Funcionario> query = em.createQuery(consulta, Funcionario.class);
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
	
	public int ContarTodosFuncionarios(long empresaid) {

		EntityManager em = JpaUtil.getEntityManager();

		Number resultado = 0;

		try {
			String consulta = "select count(f) from Funcionario f where f.empresaCliente.id = :empresaid";
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
	public List<Funcionario> BuscarTodosFuncionariosLazy(long empresaid, int first, int maxpage, Map filtros){
		
		EntityManager em = JpaUtil.getEntityManager();
		
		List<Funcionario> resultado = new ArrayList<Funcionario>();
		
		try{
			Session s = em.unwrap(Session.class);
			Criteria criteria = s.createCriteria(Funcionario.class);
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

	public List<Funcionario> BuscarFuncionarioPorID(long id, long empresaid){
		
		EntityManager em = JpaUtil.getEntityManager();
		
		List<Funcionario> resultado = new ArrayList<Funcionario>();
		
		try{
			String consulta = "select f from Funcionario f where f.id = :id and f.empresaCliente.id = :empresaid";
			TypedQuery<Funcionario> query = em.createQuery(consulta, Funcionario.class);
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
	
	public Funcionario BuscarFuncionarioPorIDConverter(long id){
		
		EntityManager em = JpaUtil.getEntityManager();
		
		Funcionario f = null;
		List<Funcionario> resultado = new ArrayList<Funcionario>();
		
		try{
			String consulta = "select f from Funcionario f where f.id = :id ";
			TypedQuery<Funcionario> query = em.createQuery(consulta, Funcionario.class);
			query.setParameter("id", id);
			resultado = query.getResultList();
			
			if(resultado.isEmpty()){
				System.out.println("Funcionario Nao encontrado");
			}else{
				
				Iterator<Funcionario> itr = resultado.iterator();
				for(int i=0; i<resultado.size(); i++){
					while(itr.hasNext()){
						f = itr.next();
					}
				}
			}
		
		}catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();
		
		}finally{
			
			em.close();
		}
		
		return f;
		
	}
	
	public List<Funcionario> BuscarFuncionarioPorCPF(String cpf){
		
		EntityManager em = JpaUtil.getEntityManager();
		
		List<Funcionario> resultado = new ArrayList<Funcionario>();
		
		try{
			String consulta = "select f from Funcionario f where f.cpf = :cpf";
			TypedQuery<Funcionario> query = em.createQuery(consulta, Funcionario.class);
			query.setParameter("cpf", cpf);
			resultado = query.getResultList();
		
		}catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();
		
		}finally{
			
			em.close();
		}
		
		return resultado;
		
	}
	
	public void AtualizarFuncionario(Funcionario f) {

		EntityManager em = JpaUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			em.merge(f);
			em.getTransaction().commit();

		} catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();

		} finally {

			em.close();
		}

	}
	
	public void RemoverFuncionario(long id){
		
		EntityManager em = JpaUtil.getEntityManager();
		
		Funcionario f = em.find(Funcionario.class, id);
		
		try{
			em.getTransaction().begin();
			em.remove(f);
			em.getTransaction().commit();
			

		}catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();
		
		}finally{
			
			em.close();
		}	
	}

}
