package br.com.mmw.Agendamento.Util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
	
private static EntityManagerFactory factory;
	
	static {
		factory = Persistence.createEntityManagerFactory("Agendamento");
	}
	
	public static EntityManager getEntityManager(){
		if(factory == null){
			factory = Persistence.createEntityManagerFactory("Agendamento");
		}
		return factory.createEntityManager();
	}
	
	public static void close(){
		factory.close();
	}

}
