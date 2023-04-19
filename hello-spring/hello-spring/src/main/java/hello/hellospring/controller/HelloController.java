package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello") //http://localhost:8080/hello를 통해 이곳에 접속 가능
    public String hello(Model model){ //spring이 모델을 만들어서 넣어줌
        model.addAttribute("data", "hello!!");
        //키를 "data"로 value를 "hello"로 model에 데이터를 저장
        return"hello"; //Thymeleaf 템플릿 엔진이 resources:templates에서 hello.html을 찾아서 
        //"hello"를 hello.html로 변환후 처리
        // resources:templates/hello.html
    }
}
