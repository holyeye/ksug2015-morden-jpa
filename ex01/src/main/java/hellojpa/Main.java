package hellojpa;

import hellojpa.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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

            //등록
            Member member = new Member();
            member.setId(1L);
            member.setName("hello");

            em.persist(member);

            //검색
            String jpql = "select m From Member m where m.name like '%hello%'";
            List<Member> result = em.createQuery(jpql, Member.class)
                    .getResultList();


            //조회
            Member findMember = em.find(Member.class, 1L);

            //수정
            member.setName("hello2");

            //삭제
            em.remove(member);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }
}
