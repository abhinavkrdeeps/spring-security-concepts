package com.wissen.training.springsecurityconcepts;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {



    @GetMapping("/home")
    public String returnHomePage(){
        return "Hello You Are A Authenticated User!..";
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
