package org.example.testyoga.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/cat")
public class TestController {

    @GetMapping
    public String getText()
    {
        return "register";
    }

}
