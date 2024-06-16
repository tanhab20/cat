package org.example.testyoga.controller;

import org.example.testyoga.beans.RegisterUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/course")
public class CourseController {

    @ModelAttribute("courses")
    public String init(){
        return "Katzneklo";
    }


    @GetMapping
    public String home()
    {
        return "cat";
    }

}
