package com.example.basicspringbootexample.messaging.controller;

import java.util.List;
import java.util.Optional;

import com.example.basicspringbootexample.messaging.model.User;
import com.example.basicspringbootexample.messaging.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/user")
  public void createUser(@RequestBody User user) {
    userService.createUser(user);
  }

  @GetMapping("/user")
  public List<User> getUsers() {
    System.out.println("HIT!");
    return userService.getUsers();
  }

  @GetMapping("/user/{id}")
  public ResponseEntity<User> getUser(@PathVariable long id) {
    Optional<User> userOptional = userService.getUserById(id);
    if (userOptional.isPresent()) {
      return ResponseEntity.ok(userOptional.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/user/{id}")
  public ResponseEntity<String> updateUser(@PathVariable long id, @RequestBody User user) {
    return userService.updateUser(user, id);
  }

  @DeleteMapping("/user/{id}")
  public void deleteMessage(@PathVariable long id) {
    userService.deleteUser(id);
  }
}
