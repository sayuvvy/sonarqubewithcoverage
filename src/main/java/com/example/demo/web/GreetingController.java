package com.example.demo.web;

import com.example.demo.context.RequestContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @GetMapping("/api/greet")
    public Greeting greet(@RequestParam(defaultValue = "world") String name) {
        return new Greeting(
                "Hello, " + name + "!",
                RequestContext.currentCorrelationId(),
                Runtime.version().toString());
    }
}
