package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    //테스트 코드는 실제 코드에 포함되지 않기 때문에 한글로 적어도 무방(실제로 많이 그렇게 함)

    MemberService memberService;
    MemoryMemberRepository memberRepository;
    /*MemberService와 MemoryMemberRepository를 선언해두고*/

    @BeforeEach // 작업을 시작할때마다 이 메서드를 실행시킴
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
        //MemberService의 생성자에 여기서 만든 MemoryMemberRepository객체를 넣어서 동일한 객체를 다루게 함
    }

    @AfterEach //작업 하나가 끝날때마다 이 메서드를 실행시킴
    public void afterEach(){
        memberRepository.clearStore(); //작업이 끝날때마다 해시맵을 초기화
    }

    @Test
    void 회원가입() {
        //given /*주어진것*/
        Member member = new Member();
        member.setName("hello");

        //when /*조건*/
        Long saveId = memberService.join(member);
        /*join메서드에 member를 넣어 그 id를 반환시킴*/

        //then /*결과*/
        Member findMember = memberService.findOne(saveId).orElse(new Member());
        //올바른 id를 반환했는지 그 아이디로 다시 member객체를 찾아서 반환하고
        assertThat(member.getName()).isEqualTo(findMember.getName());
        //"hello"와 저장한 member객체의 name이 일치하는지 확인
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //이름을 동일하게 저장한 두 다른 객체를 생성

        //when
        memberService.join(member1); //member1을 저장하고
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        /*join을 실행했을때 IllegalStateException예외가 발생할거라고 주장*/

        //then
        assertThat(e.getMessage()).isEqualTo("이미 같은 이름의 회원이 존재합니다");
        /*그 에러의 메시지가 "이미 같은 이름의 회원이 존재합니다"와 같다고 주장*/
    }

    
    //여기서부터는 혼자 해본것
    @Test
    void findMembers() {
        //given
        Member m1 = new Member();
        m1.setName("name1");
        memberService.join(m1);

        Member m2 = new Member();
        m1.setName("name2");
        memberService.join(m2);

        //when
        List<Member> members = memberService.findMembers();

        //then
        assertThat(members.size()).isEqualTo(2);
    }

    @Test
    void findOne() {
        //given
        Member m = new Member();
        m.setName("name");
        memberService.join(m);

        //when
        Long id = m.getId();

        //then
        assertThat(m.getId()).isEqualTo(1);
    }
}