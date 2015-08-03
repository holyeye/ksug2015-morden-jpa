package hellojpa;

import hellojpa.entity.Member;
import hellojpa.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    Member member(@PathVariable("memberId") Long memberId) {
        return repository.findOne(memberId);
    }

    @RequestMapping("/search")
    List<Member> search(@RequestParam("name") String name) {
        return repository.findByName(name);
    }

}