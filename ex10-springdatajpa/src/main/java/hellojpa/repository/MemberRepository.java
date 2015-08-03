package hellojpa.repository;

import hellojpa.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author holyeye@sk.com
 */
public interface MemberRepository extends JpaRepository<Member, Long>, QueryDslPredicateExecutor<Member> {

    Page<Member> findByName(String name, Pageable pageable);
    
}
