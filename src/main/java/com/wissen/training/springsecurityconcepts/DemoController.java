package com.wissen.training.springsecurityconcepts;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class DemoController {


    @GetMapping("/")
    public String test(Principal principal){
        return principal.getName();
    }


    @GetMapping("/home")
    public String returnHomePage(){
        return "Hello All.. No Authentication is Required for this page..";
    }

    @GetMapping("/user")
    public String welcomeUser(){
        return "Hello User .. You are either an admin or a user";
    }

    @GetMapping("/admin")
    public String welcomeAdmin(){
        return "Hello Admin.. Only Admins Are Allowed";
    }
}
