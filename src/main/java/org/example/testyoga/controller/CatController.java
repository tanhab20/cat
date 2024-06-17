package org.example.testyoga.controller;

import org.example.testyoga.beans.Cat;
import org.example.testyoga.beans.Course;
import org.example.testyoga.repository.CatRepository;
import org.example.testyoga.repository.CourseRepository;
import org.example.testyoga.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@RequestMapping("/cat")
@SessionAttributes("loggedUser")
public class CatController {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final CatRepository catRepository;


    public CatController(CourseRepository courseRepository, UserRepository userRepository, CatRepository catRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.catRepository = catRepository;
    }

    @ModelAttribute("cats")
    public List<Cat> initCats() {
        return catRepository.findAll();
    }

    @GetMapping
    public String home() {
        return "cat";
    }

}
