package org.example.testyoga.controller;

import org.example.testyoga.beans.Course;
import org.example.testyoga.repository.CourseRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {
    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @ModelAttribute("courses")
    public List<Course> init() {
        return courseRepository.findAll();
    }


    @GetMapping
    public String home()
    {
        return "course";
    }

}
