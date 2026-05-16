package com.mycompany.projectyml.web.rest;


import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api")
public class HelloWorldController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!";
    }
}
