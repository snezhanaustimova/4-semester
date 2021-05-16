package com.example.ustimova.controller;

import com.example.ustimova.entity.User;
import com.example.ustimova.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthorizationController {

    private final UserRepository userRepository;

    public AuthorizationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthorizationRequest request) {
        String login = request.getLogin();
        String password = request.getPassword();
        User user = userRepository.findByLogin(login);
        Map<Object, Object> response = new HashMap<>();
        response.put("firstName", user.getFirstName());
        response.put("lastName", user.getLastName());
        response.put("middleName", user.getMiddleName());
        response.put("login", login);
        response.put("password", password);
        System.out.println(response);
        return ResponseEntity.ok(response);
    }
}
