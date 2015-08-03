package hellojpa;

import hellojpa.entity.Member;
import hellojpa.entity.QMember;
import hellojpa.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class SampleController {

    @Autowired
    MemberRepository repository;

    @PostConstruct
    public void init() {
        repository.save(new Member("hello", 1));
        repository.save(new Member("hello", 2));
        repository.save(new Member("hello", 3));
        repository.save(new Member("hello", 4));
        repository.save(new Member("hello", 5));
    }

    @RequestMapping("/save")
    String home() {
        Member member = new Member();
        member.setName("hello");
        repository.save(member);
        return "Save Member " + member.getId() ;
    }

    @RequestMapping("/members")
    List<Member> members() {
        return repository.findAll();
    }

    @RequestMapping("/members/{memberId}")
    Member member(@PathVariable("memberId") Member member) {
        return member;
    }

    @RequestMapping("/search")
    Page<Member> search(@RequestParam("name") String name, Pageable pageable) {
        return repository.findByName(name, pageable);
    }

    @RequestMapping("/search-dsl")
    Page<Member> searchByQueryDSL(@RequestParam("name") String name, Pageable pageable) {
        return repository.findAll(QMember.member.name.eq(name), pageable);
    }

}