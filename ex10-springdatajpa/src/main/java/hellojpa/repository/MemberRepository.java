package hellojpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.transaction.annotation.Transactional;

import hellojpa.entity.Member;

/**
 * @author holyeye@sk.com
 */
@Transactional(readOnly=true)
public interface MemberRepository extends JpaRepository<Member, Long>, QueryDslPredicateExecutor<Member> {

    Page<Member> findByName(String name, Pageable pageable);
    
}
