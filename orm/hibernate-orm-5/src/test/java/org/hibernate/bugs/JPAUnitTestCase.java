package org.hibernate.bugs;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
public class JPAUnitTestCase {

	private EntityManagerFactory entityManagerFactory;

	@Before
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory( "templatePU" );
	}

	@After
	public void destroy() {
		entityManagerFactory.close();
	}

	// Entities are auto-discovered, so just add them anywhere on class-path
	// Add your tests, using standard JUnit.
	@Test
	public void hhh16928() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		EntityB b = new EntityB();
		b.setId(2);

		EntityA a = new EntityA();
		a.setId(1);
		a.getbCollection().add(b);

		b.setA(a);

		entityManager.persist(a);
		entityManager.persist(b);

		Query query = entityManager.createQuery("select distinct a.id, b.id, c.intValue\n" +
				"                                from EntityA a\n" +
				"                                left join EntityB b on b.a.id = a.id\n" +
				"                                left join b.cCollection as c\n" +
				"                                where (c.created = (select max(c2.created)\n" +
				"                                from b.cCollection as c2) or c.created is null)");
		Query query2 = entityManager.createQuery("select distinct b.id, c.intValue\n" +
				"                                from EntityB b\n" +
				"                                left join b.cCollection as c\n" +
				"                                where (c.created = (select max(c2.created)\n" +
				"                                from b.cCollection as c2) or c.created is null)");

		List result = query.getResultList();
		List result2 = query2.getResultList();

		assertEquals(1, result.size());
		assertEquals(1, result2.size());

		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
