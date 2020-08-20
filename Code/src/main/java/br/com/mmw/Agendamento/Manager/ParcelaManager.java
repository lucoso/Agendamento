package br.com.mmw.Agendamento.Manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.mmw.Agendamento.Model.Parcela;
import br.com.mmw.Agendamento.Util.JpaUtil;

public class ParcelaManager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void CadastrarParcela(Parcela p) {

		EntityManager em = JpaUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();

		} catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();

		} finally {

			em.close();
		}

	}

	public List<Parcela> BuscarTodasParcelasDoPagamento(long parceladoid) {

		EntityManager em = JpaUtil.getEntityManager();

		List<Parcela> resultado = new ArrayList<Parcela>();

		try {
			String consulta = "select p from Parcela p where p.parcelado.id = :parceladoid order by p.id";
			TypedQuery<Parcela> query = em.createQuery(consulta, Parcela.class);
			query.setParameter("parceladoid", parceladoid);
			resultado = query.getResultList();

		} catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();

		} finally {

			em.close();
		}

		return resultado;
	}

	public List<Parcela> BuscarParcelaPorID(long id){
		
		EntityManager em = JpaUtil.getEntityManager();
		
		List<Parcela> resultado = new ArrayList<Parcela>();
		
		try{
			String consulta = "select p from Parcela p where p.id = :id";
			TypedQuery<Parcela> query = em.createQuery(consulta, Parcela.class);
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
	
	public void AtualizarParcela(Parcela p) {

		EntityManager em = JpaUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			em.merge(p);
			em.getTransaction().commit();

		} catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();

		} finally {

			em.close();
		}

	}
	
	public void RemoverParcela(long id){
		
		EntityManager em = JpaUtil.getEntityManager();
		
		Parcela p = em.find(Parcela.class, id);
		
		try{
			em.getTransaction().begin();
			em.remove(p);
			em.getTransaction().commit();
			

		}catch (Exception ex) {

			ex.printStackTrace();
			em.getTransaction().rollback();
		
		}finally{
			
			em.close();
		}
		
	}


}
