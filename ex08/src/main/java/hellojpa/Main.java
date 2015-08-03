package hellojpa;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import hellojpa.entity.Member;
import hellojpa.entity.QMember;
import hellojpa.entity.QTeam;
import hellojpa.entity.Team;

import javax.persistence.*;
import java.util.List;

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
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();

    }

    private static void logic(EntityManager em) {

        //저장
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

        em.persist(team);
        em.persist(member1);
        em.persist(member2);

        em.flush(); em.clear();

        simpleQuery(em);
//        joinQuery(em);
//        fetchJoin(em);
//        pagingQuery(em);
//        dynamicQuery(em);
    }

    private static void simpleQuery(EntityManager em) {
        //JPQL 조회, 18살 이상 JPQL 조회
        List<Member> resultList = em.createQuery("select m from Member m where m.age > 18", Member.class)
                .getResultList();

        JPAQuery query = new JPAQuery(em);

        QMember m = QMember.member;

        query.from(m).where(m.age.gt(18).and(m.name.startsWith("hi")))
                .orderBy(m.age.desc())
/*
        List<Member> list = query.from(m)
                .where(m.age.gt(18))
                .list(m);

*/
        for (Member member : list) {
            System.out.println("member = " + member);
        }
    }

    private static void joinQuery(EntityManager em) {
        //JPQL 조인
//        List<Member> resultList2 = em.createQuery("select m from Member m join m.team t where t.name = 'teamA'", Member.class)
//                .getResultList();

        JPAQuery query = new JPAQuery(em);

        QMember m = QMember.member;
        QTeam t = QTeam.team;
        List<Member> list = query.from(m)
                .join(m.team, t)
                .where(t.name.eq("teamA"))
                .list(m);

        for (Member member : list) {
            System.out.println("member = " + member);
        }
    }

    private static void fetchJoin(EntityManager em) {
        //JPQL 페치 조인 전
        System.out.println("=== 페치 조인 전 ===");
//        List<Member> resultList3 = em.createQuery("select m from Member m", Member.class)
//                .getResultList();

        JPAQuery query = new JPAQuery(em);

        QMember m = QMember.member;
        List<Member> list = query.from(m)
                .list(m);

        for (Member member : list) {
            System.out.println("member = " + member);
            System.out.println("team name = " + member.getTeam().getName());
        }

        //JPQL 페치 조인 후
        System.out.println("=== 페치 조인 후 ===");
//        List<Member> resultList4 = em.createQuery("select m from Member m join fetch m.team", Member.class)
//                .getResultList();

        JPAQuery query2 = new JPAQuery(em);

        QTeam t = QTeam.team;
        List<Member> list2 = query2.from(m)
                .join(m.team, t).fetch()
                .list(m);

        for (Member member : list2) {
            System.out.println("member = " + member);
            System.out.println("team name = " + member.getTeam().getName());
        }
    }

    private static void pagingQuery(EntityManager em) {
        //페이징 쿼리
/*
        List<Member> resultList5 = em.createQuery("select m from Member m order by m.age desc", Member.class)
                .setFirstResult(1)
                .setMaxResults(1)
                .getResultList();
*/

        JPAQuery query = new JPAQuery(em);
        QMember m = QMember.member;

        List<Member> list = query.from(m)
                .orderBy(m.age.desc())
                .offset(1)
                .limit(1)
                .list(m);
    }

    private static void dynamicQuery(EntityManager em) {

        String name = "member";
        int age = 9;

        JPAQuery query = new JPAQuery(em);
        QMember m = QMember.member;

        BooleanBuilder builder = new BooleanBuilder();
        if (name != null) {
            builder.and(m.name.contains(name));
        }
        if (age != 0) {
            builder.and(m.age.gt(age));
        }

        List<Member> list = query.from(m)
                .where(builder)
                .list(m);

        System.out.println("list = " + list);
    }
}
