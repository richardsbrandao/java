package com.richard.studies.security.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/greetings")
public class GreetingsController {
    @RequestMapping(method = RequestMethod.GET)
    public String sayHello(Principal principal) {
        return "Hello " + principal.getName().toUpperCase() + "!";
    }
}
