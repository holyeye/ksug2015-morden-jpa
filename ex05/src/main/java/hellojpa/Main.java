package hellojpa;

import hellojpa.entity.Member;
import hellojpa.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @author holyeye@sk.com
 */
public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            logic(em);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }

    private static void logic(EntityManager em) {

        //저장
        Team team = new Team();
        team.setName("TeamA");
        em.persist(team);

        Member member = new Member();
        member.setName("member1");
        member.setAge(10);

        //양방향 연관관계 설정
        team.getMembers().add(member);
        member.setTeam(team); //**

        em.persist(member);

        //조회
        Team findTeam = em.find(Team.class, team.getId());
        int memberSize = findTeam.getMembers().size(); //역방향 조회

        System.out.println("member Size = " + memberSize);
        System.out.println("findTeam = " + findTeam.getName());
    }
}
