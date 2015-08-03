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

        //팀 저장
        Team team = new Team();
        team.setName("TeamA");
        em.persist(team);

        //회원 저장
        Member member = new Member();
        member.setName("member1");
        member.setAge(10);
        member.setTeamId(team.getId());
        em.persist(member);

        //조회
        Member findMember = em.find(Member.class, member.getId());
        Team findTeam = em.find(Team.class, team.getId()); //연관관계가 없음
    }
}
