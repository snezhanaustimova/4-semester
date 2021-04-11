package com.example.ustimova.controller;

import com.example.ustimova.entity.User;
import com.example.ustimova.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> readAll(){
        final List<User> usersList = userService.findAll();
        return usersList != null && !usersList.isEmpty()
                ? new ResponseEntity<>(usersList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getOne(@PathVariable(name = "id") User user) {
        final User currentUser = user;
        return user != null
                ? new ResponseEntity<>(currentUser, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    private ResponseEntity<?> create(@RequestBody User user){
        User newUser = userService.create(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> put(@PathVariable(name = "id") User userFromDB,
                                 @RequestBody User user) {
        User updatedUser = userService.update(user, userFromDB);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<List<User>> delete(@PathVariable(name = "id") User user) {
        if (userService.delete(user)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}