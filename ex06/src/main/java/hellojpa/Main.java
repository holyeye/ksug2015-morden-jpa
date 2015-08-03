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

        Member member = new Member();
        member.setName("member1");
        member.setAge(10);

        //양방향 연관관계 설정
        team.getMembers().add(member);
        member.setTeam(team); //**

        em.persist(team);
        em.persist(member);

        //영속성 컨텍스트 초기화
        em.flush(); em.clear();

        //프록시 확인 - getReference
        Member findMember = em.getReference(Member.class, member.getId());
        System.out.println("findMember = " + findMember.getClass());
        System.out.println("=== 프록시 초기화 직 전 - getReference ===");
        findMember.getName(); //프록시 초기화
        System.out.println("=== 프록시 초기화 직 후 - getReference ===");

        //프록시 확인 - LAZY 연관관계
        System.out.println("findMember.getTeam() = " + findMember.getTeam().getClass());
        System.out.println("=== 프록시 초기화 직 전 - LAZY ===");
        findMember.getTeam().getName();
        System.out.println("=== 프록시 초기화 직 후 - LAZY ===");

    }
}
