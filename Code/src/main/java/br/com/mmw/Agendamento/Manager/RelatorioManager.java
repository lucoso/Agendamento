package br.com.mmw.Agendamento.Manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.mmw.Agendamento.Model.Relatorio;
import br.com.mmw.Agendamento.Util.JpaUtil;

public class RelatorioManager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void CadastrarRelatorio(Relatorio r) {

		EntityManager em = JpaUtil.getEntityManager();

		try {
			r.setData(new Date());
			em.getTransaction().begin();
			em.persist(r);
			em.getTransaction().commit();

		} catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();

		} finally {

			em.close();
		}

	}
	
	public List<Relatorio> BuscarTodosRelatoriosDoServico(long servicoid) {

		EntityManager em = JpaUtil.getEntityManager();

		List<Relatorio> resultado = new ArrayList<Relatorio>();

		try {
			String consulta = "select r from Relatorio r where r.servico.id = :servicoid order by r.data";
			TypedQuery<Relatorio> query = em.createQuery(consulta, Relatorio.class);
			query.setParameter("servicoid", servicoid);
			resultado = query.getResultList();

		} catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();

		} finally {

			em.close();
		}

		return resultado;
	}

	public List<Relatorio> BuscarRelatorioPorID(long id){
		
		EntityManager em = JpaUtil.getEntityManager();
		
		List<Relatorio> resultado = new ArrayList<Relatorio>();
		
		try{
			String consulta = "select r from Relatorio r where r.id = :id";
			TypedQuery<Relatorio> query = em.createQuery(consulta, Relatorio.class);
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
	
	public void AtualizarRelatorio(Relatorio r) {

		EntityManager em = JpaUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			em.merge(r);
			em.getTransaction().commit();

		} catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();

		} finally {

			em.close();
		}

	}
	
	public void RemoverRelatorio(long id){
		
		EntityManager em = JpaUtil.getEntityManager();
		
		Relatorio r = em.find(Relatorio.class, id);
		
		try{
			em.getTransaction().begin();
			em.remove(r);
			em.getTransaction().commit();
			

		}catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();
		
		}finally{
			
			em.close();
		}
		
	}

}
