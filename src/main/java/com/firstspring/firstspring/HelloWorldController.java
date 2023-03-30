package com.firstspring.firstspring;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @RequestMapping("/")
    public String HelloWorld(){
        return "Hello World";

    }
    @RequestMapping("/test")
    public String Hello(){
        return "Stava";
    }
}
