package br.com.mmw.Agendamento.Manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.mmw.Agendamento.Model.Pagamento;
import br.com.mmw.Agendamento.Model.Parcelado;
import br.com.mmw.Agendamento.Util.JpaUtil;

public class PagamentoManager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Parcelado CadastrarPagamentoParcelado(Parcelado p) {

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
		return p;

	}
	
	public void CadastrarPagamento(Pagamento p) {

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

	public List<Pagamento> BuscarTodosPagamentosDoServico(long servicoid) {

		EntityManager em = JpaUtil.getEntityManager();

		List<Pagamento> resultado = new ArrayList<Pagamento>();

		try {
			String consulta = "select p from Pagamento p where p.servico.id = :servicoid";
			TypedQuery<Pagamento> query = em.createQuery(consulta, Pagamento.class);
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

	public List<Pagamento> BuscarPagamentoPorID(long id){
		
		EntityManager em = JpaUtil.getEntityManager();
		
		List<Pagamento> resultado = new ArrayList<Pagamento>();
		
		try{
			String consulta = "select p from Pagamento p where p.id = :id";
			TypedQuery<Pagamento> query = em.createQuery(consulta, Pagamento.class);
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
	
	public void AtualizarPagamento(Pagamento p) {

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
	
	public void RemoverPagamento(long id){
		
		EntityManager em = JpaUtil.getEntityManager();
		
		Pagamento p = em.find(Pagamento.class, id);
		
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
