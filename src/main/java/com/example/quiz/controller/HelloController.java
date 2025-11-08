package com.example.quiz.controller;

import com.example.quiz.model.Result;
import com.example.quiz.model.SimpleUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

//    @RequestMapping("/hello") //表示如果接收到浏览器的/hello请求，就执行下面的hello()方法；
//    public String hello() {
//        System.out.println("hello");
//        return "hello World";
//    }
    @RequestMapping("/hello")
    public Result hello() {
        System.out.println("hello");
        //return Result.class;
        return null;
    }


    @RequestMapping("/simpleParam") //表示如果接收到浏览器的/simpleParam，就执行下面的getParam()方法；
    public String getParam(String name, Integer age) {
        System.out.println(name+":"+age);
        return "ok";
    }
    @RequestMapping("/simpleUser") //表示如果接收到浏览器的/simpleUser，就执行下面的getUser()方法；
    public Object getUser(SimpleUser user) {
        System.out.println(user);
        SimpleUser simpleUser = new SimpleUser();
        simpleUser.setAge(user.getAge()+1);
        simpleUser.setName(user.getName());

        return simpleUser;
    }
}
