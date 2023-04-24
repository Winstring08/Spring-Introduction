package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach //작업 하나가 끝날때마다 이 메서드를 실행시킴
    public void afterEach(){
        repository.clearStore(); //작업이 끝날때마다 해시맵을 초기화
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");
        repository.save(member);
        //name에 spring이라는 값을 저장한 Member객체를 해시맵에 저장
        
       Member result = repository.findById(member.getId()).orElse(new Member());
       //해시맵에서 id가 1인 Optional<Member>객체의 값을 result에 저장
        assertThat(result).isEqualTo(member);
        //result가 member과 같다고 주장(assert)
        //import org.assertj.core.api.Assertions; 필요

    }

    @Test
    public void findByName(){ //repository에 있는 메서드와 이름만 같고 다른 메서드
        Member member1 = new Member(); 
        member1.setName("spring1");
        repository.save(member1); //name에 spring1이라는 값을 저장한 Member객체를 해시맵에 저장

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2); //name에 spring2이라는 값을 저장한 Member객체를 해시맵에 저장

        Member result = repository.findByName("spring1").get();
        //name이 spring1인 Optional<Member>의 값(member1)을 result에 저장
        assertThat(result).isEqualTo(member1);
        //result가 member1과 같다고 주장(assert)

    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        //두 객체를 저장하고 해시맵의 values List를 result에 저장

        assertThat(result.size()).isEqualTo(2);
        //그 리스트의 크기가 2와 같다고 주장
    }


}
