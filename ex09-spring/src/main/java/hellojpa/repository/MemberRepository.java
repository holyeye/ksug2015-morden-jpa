package hellojpa.repository;

import hellojpa.entity.Member;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author holyeye@sk.com
 */
@Repository
@Transactional
public class MemberRepository {

    @PersistenceContext
    EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public void delete(Long memberId) {
        Member member = em.find(Member.class, memberId);
        em.remove(member);
    }

    public List<Member> findAll() {
        return em.createQuery("select m From Member m", Member.class).getResultList();
    }

    public Member findOne(Long memberId) {
        return em.find(Member.class, memberId);
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m From Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

}
