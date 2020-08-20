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

import br.com.mmw.Agendamento.Model.Usuario;
import br.com.mmw.Agendamento.Util.JpaUtil;

public class UsuarioManager implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void CadastrarUsuario(Usuario u) {

		EntityManager em = JpaUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(u);
			em.getTransaction().commit();

		} catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();

		} finally {

			em.close();
		}

	}

	public List<Usuario> BuscarTodosUsuarios(long empresaid) {

		EntityManager em = JpaUtil.getEntityManager();

		List<Usuario> resultado = new ArrayList<Usuario>();

		try {
			String consulta = "select u from Usuario u where u.funcionario.empresaCliente.id = :empresaid";
			TypedQuery<Usuario> query = em.createQuery(consulta, Usuario.class);
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
	
	public int ContarTodosUsuarios(long empresaid) {

		EntityManager em = JpaUtil.getEntityManager();

		Number resultado = 0;

		try {
			String consulta = "select count(u) from Usuario u where u.funcionario.empresaCliente.id = :empresaid";
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
	public List<Usuario> BuscarTodosUsuariosLazy(long empresaid, int first, int maxpage, Map filtros){
		
		EntityManager em = JpaUtil.getEntityManager();
		
		List<Usuario> resultado = new ArrayList<Usuario>();
		
		try{
			Session s = em.unwrap(Session.class);
			Criteria criteria = s.createCriteria(Usuario.class);
			Criteria funcCriteria = criteria.createCriteria("funcionario");
			Criteria empCrit = funcCriteria.createCriteria("empresaCliente");
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
	
	public int BuscarUsuarioParaInstalacao(){
		
		EntityManager em = JpaUtil.getEntityManager();
		
		Number resultado = 0;
		
		try{
			String consulta = "select COUNT(u) from Usuario u";
			TypedQuery<Number> query = em.createQuery(consulta, Number.class);
			resultado = query.getSingleResult();
		
		}catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();
		
		}finally{
			
			em.close();
		}
		
		return resultado.intValue();
		
	}

	public List<Usuario> BuscarUsuarioPorID(long id, long empresaid){
		
		EntityManager em = JpaUtil.getEntityManager();
		
		List<Usuario> resultado = new ArrayList<Usuario>();
		
		try{
			String consulta = "select u from Usuario u where u.id = :id and u.funcionario.empresaCliente.id = :empresaid";
			TypedQuery<Usuario> query = em.createQuery(consulta, Usuario.class);
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
	
	public List<Usuario> BuscarUsuarioPorLogin(String login, long empresaid){
		
		EntityManager em = JpaUtil.getEntityManager();
		
		List<Usuario> resultado = new ArrayList<Usuario>();
		
		try{
			String consulta = "select u from Usuario u where u.login = :login and u.funcionario.empresaCliente.id = :empresaid";
			TypedQuery<Usuario> query = em.createQuery(consulta, Usuario.class);
			query.setParameter("login", login);
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
	
	public List<Usuario> BuscarUsuarioPorLoginParaRecuperarSenha(String login){
		
		EntityManager em = JpaUtil.getEntityManager();
		
		List<Usuario> resultado = new ArrayList<Usuario>();
		
		try{
			String consulta = "select u from Usuario u where u.login = :login";
			TypedQuery<Usuario> query = em.createQuery(consulta, Usuario.class);
			query.setParameter("login", login);
			resultado = query.getResultList();
		
		}catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();
		
		}finally{
			
			em.close();
		}
		
		return resultado;
	}
	
	public Usuario BuscarUmUsuarioPorLogin(String login){
		
		EntityManager em = JpaUtil.getEntityManager();
		
		List<Usuario> resultado = new ArrayList<Usuario>();
		Usuario user = null;
		
		try{
			String consulta = "select u from Usuario u where u.login = :login";
			TypedQuery<Usuario> query = em.createQuery(consulta, Usuario.class);
			query.setParameter("login", login);
			resultado = query.getResultList();
			
			if(!resultado.isEmpty()){
				for(Usuario u : resultado){
					user = u;
				}
			}
		
		}catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();
		
		}finally{
			
			em.close();
		}
		
		return user;
	}
	
	public void AtualizarUsuario(Usuario u) {

		EntityManager em = JpaUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			em.merge(u);
			em.getTransaction().commit();

		} catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();

		} finally {

			em.close();
		}

	}
	
	public void RemoverUsuario(long id){
		
		EntityManager em = JpaUtil.getEntityManager();
		
		Usuario u = em.find(Usuario.class, id);
		
		try{
			em.getTransaction().begin();
			em.remove(u);
			em.getTransaction().commit();
			

		}catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();
		
		}finally{
			
			em.close();
		}
		
	}

}
