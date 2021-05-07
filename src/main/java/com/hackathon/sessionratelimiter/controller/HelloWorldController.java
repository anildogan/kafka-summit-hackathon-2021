package com.hackathon.sessionratelimiter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
@RequiredArgsConstructor
public class HelloWorldController {

    @GetMapping()
    public String helloWorld(@RequestHeader(value = "userId") String userId) {
        return "Hello World";
    }
}
