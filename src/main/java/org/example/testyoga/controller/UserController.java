package org.example.testyoga.controller;

import lombok.RequiredArgsConstructor;
import org.example.testyoga.beans.RegisterUser;
import org.example.testyoga.beans.User;
import org.example.testyoga.service.AuthenticationService;
import org.example.testyoga.service.JwtService;
import org.example.testyoga.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Duration;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    @ModelAttribute("registeredUser")
    public RegisterUser init(){
        return new RegisterUser();
    }

    @ModelAttribute("loginUser")
    public User initLoginUser(){
        return new User();
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(User user) {
        User authenticatedUser = authenticationService.authenticate(user);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        ResponseCookie cookie = ResponseCookie.from("token", jwtToken)
                .httpOnly(false)
                .secure(false)
                .path("/")
                .maxAge(Duration.ofDays(1))
                .domain("localhost")
                .build();



        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, "http://localhost:8080/cat");
        headers.add(HttpHeaders.SET_COOKIE, cookie.toString());
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken);


        return ResponseEntity.status(HttpStatus.SEE_OTHER).headers(headers).build();
    }

    @PostMapping("/register")
    public String register(RegisterUser user)
    {
        user.setRole("admin");
        User registeredUser = authenticationService.signup(user);

        return "redirect:/auth/login";
    }

    @GetMapping("/register")
    public String register()
    {
        return "register";
    }


    @GetMapping("/login")
    public String login()
    {
        return "login";
    }

}
