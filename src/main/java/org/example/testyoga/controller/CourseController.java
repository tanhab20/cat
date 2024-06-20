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
@RequestMapping("/course")
@SessionAttributes("loggedUser")
public class CourseController {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final CatRepository catRepository;

    public CourseController(CourseRepository courseRepository, UserRepository userRepository, CatRepository catRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.catRepository = catRepository;
    }

    @ModelAttribute("courses")
    public List<Course> initCourses() {
        return courseRepository.findAll();
    }

    @GetMapping
    public String home() {
        return "course";
    }

    @GetMapping("/booked")
    public String userCourses(Authentication authentication, Model model) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("Invalid user"));


        model.addAttribute("userCourses", user.getCourses());

        return "userCourses";
    }

    @PostMapping("/register")
    public String register(@RequestParam Long courseId, Authentication authentication, Model model) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("Invalid user"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course Id:" + courseId));

        if (!user.getCourses().contains(course)) {
            user.getCourses().add(course);
            userRepository.save(user);
        }

        model.addAttribute("userCourses", user.getCourses());

        return "userCourses";
    }

    @GetMapping("/addForm")
    public String addCourse(Model model){
        User user = (User) model.getAttribute("loggedUser");
        if(!user.getRole().equals("admin")){
            return "course";
        }

        List<Cat> cats = catRepository.findAll();
        model.addAttribute("cats", cats);
        model.addAttribute("course", new Course());
        return "addcourse";
    }

    @GetMapping("/users")
    public String getUsers(Model model, Authentication authentication){
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("Invalid user"));
        if (!user.getRole().equals("admin")) {
            return "course";
        }
        model.addAttribute("users", userRepository.findAll());

        return "users";
    }


    @PostMapping("/add")
    public String addCourse(@ModelAttribute Course course, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("Invalid user"));

        if (!user.getRole().equals("admin")) {
            return "course";
        }

        courseRepository.save(course);
        return "redirect:/course";
    }
    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam Long userId, Model model) {
        userRepository.deleteById(userId);
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }
}
