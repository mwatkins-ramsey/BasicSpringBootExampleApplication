package com.example.basicspringbootexample.messaging.repository;

import com.example.basicspringbootexample.messaging.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
