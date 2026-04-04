package com.ethi.village.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping("/api")
    public String greet()
    {
        return "Hello world";
    }
}
