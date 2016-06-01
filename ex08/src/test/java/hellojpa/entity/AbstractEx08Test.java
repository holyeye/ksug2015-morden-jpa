package hellojpa.entity;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractEx08Test {

	private EntityManagerFactory emf;
	protected EntityManager em;
	
	@Before
	public void setup() {
		
        Team team = new Team();
        team.setName("teamA");

        Member member1 = new Member();
        member1.setName("member1");
        member1.setAge(10);
        team.getMembers().add(member1);
        member1.setTeam(team); //**

        Member member2 = new Member();
        member2.setName("member2");
        member2.setAge(20);
        team.getMembers().add(member2);
        member2.setTeam(team); //**

        emf = Persistence.createEntityManagerFactory("hello");
        em = emf.createEntityManager();
        
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        
        try {
        	em.persist(team);
        	em.persist(member1);
        	em.persist(member2);
        	tx.commit();
        	em.clear();
		} catch (Exception e) {
			tx.rollback();
		}
        
        postSetup();
	}
	
	public void postSetup() {
	}
	
	@After
	public void close() {
		em.close();
		emf.close();
	}
	
	@Test
	public void testSimpleQuery() {
		doTestSimpleQuery();
	}
	

	@Test
	public void testJoinQuery() throws Exception {
		doTestJoinQuery();
	}
	
	@Test
	public void testFetchJoin() throws Exception {
		doTestFetchJoin();
	}
	
	@Test
	public void testPagingQuery() throws Exception {
		doTestPagingQuery();
	}
	
	public abstract void doTestSimpleQuery();
	public abstract void doTestJoinQuery();
	public abstract void doTestFetchJoin();
	public abstract void doTestPagingQuery();
	
	protected void doLogic(LogicExecutor logicExecutor) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			print(logicExecutor.execute());
			tx.commit();
		} catch (Exception e) {
			System.err.println(e);
			tx.rollback();
		}
	}
	
	protected void print(List<Member> members) {
		for (Member member : members) {
			System.out.println("member info : " + member);
			System.out.println("member's team info : " + member.getTeam().getName());
		}
	}
	
	protected interface LogicExecutor {
		List<Member> execute();
	}
}
