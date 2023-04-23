package hello.hellospring.controller;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.cbor.MappingJackson2CborHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute( "name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    /*http body부에 데이터를 직접 반환*/
    /*viewResolver대신 HttpMessageConverter가 동작*/
    /*기본 문자 처리 : StringHttpMessageConverter
    기본 객체 처리 : MappingJackson2CborHttpMessageConverter*/
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; /*객체를 반환하면 JSON으로 반환하는것이 기본세팅*/
        /*HttpMessageConverter가 동작하여 객체를 JSON스타일로 변경후 요청한 곳에 응답*/
    }
    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }





}
