package br.com.cmdev.h2database.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("h2-database");

	public static EntityManager getEntityManager() {
		return FACTORY.createEntityManager();
	}

}
