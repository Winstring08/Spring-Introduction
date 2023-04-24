package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired //MemberController가 생성될때 Spring Bean에 등록된 MemberService객체를 가져다 넣어줌
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
