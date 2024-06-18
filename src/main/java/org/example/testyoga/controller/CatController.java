package org.example.testyoga.controller;

import org.example.testyoga.beans.Cat;
import org.example.testyoga.beans.Course;
import org.example.testyoga.beans.User;
import org.example.testyoga.repository.CatRepository;
import org.example.testyoga.repository.CourseRepository;
import org.example.testyoga.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/addCat")
    public String addCat(Model model){
        User user = (User) model.getAttribute("loggedUser");
        if(!user.getRole().equals("admin")){
            return "course";
        }

        model.addAttribute("cat", new Cat());
        return "addcat";
    }

    @PostMapping("/add")
    public String addCourse(@ModelAttribute Cat cat, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("Invalid user"));

        if (!user.getRole().equals("admin")) {
            return "course";
        }

        catRepository.save(cat);
        return  "redirect:/cat";
    }
}
