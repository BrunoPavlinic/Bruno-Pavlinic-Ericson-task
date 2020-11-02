package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.response.Response;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/login")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Response> login(@RequestBody User user) {
        return ResponseEntity.ok(userService.login(user));
    }

    @PostMapping(value = "/signUp")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Response> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping(value = "/{username}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<User> getGradeById(@PathVariable String username) {
        return ResponseEntity.ok(userService.getByUsername(username));
    }

}
