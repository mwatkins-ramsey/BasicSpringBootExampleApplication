package com.example.basicspringbootexample.messaging.service;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;
import com.example.basicspringbootexample.messaging.model.User;
import com.example.basicspringbootexample.messaging.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
    System.out.println("USER SERVICE INITIALIZED");
  }

  public void createUser(User user) {
    userRepository.save(user);
  }

  public List<User> getUsers() {
    return userRepository.findAll();
  }

  public Optional<User> getUserById(long id) {
    return userRepository.findById(id);
  }

  public ResponseEntity<String> updateUser(User user, long id) {
    Optional<User> existingUserOptional = userRepository.findById(id);
    if (existingUserOptional.isPresent()) {
      // note that we do not just save the incoming user, this is to avoid Overposting issues
      // for example - the client could post a User and include an id field in the json body
      // which could change the id of the user which would not be desireable.
      User existingUser = existingUserOptional.get();
      existingUser.setEmail(user.getEmail());
      existingUser.setFirstname(user.getFirstname());
      existingUser.setLastname(user.getLastname());
      userRepository.save(existingUser);
      return ResponseEntity.ok().build();
    } else {
      String errorMessage = "User with ID " + id + " not found.";
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
  }

  public void deleteUser(long id) {
      Optional<User> userOptional = userRepository.findById(id);
      if (userOptional.isPresent()) {
          userRepository.deleteById(id);
      } else {
          throw new EntityNotFoundException();
      }
  }
}
