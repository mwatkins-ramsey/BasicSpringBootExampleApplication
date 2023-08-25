package com.example.basicspringbootexample.messaging.repository;

import com.example.basicspringbootexample.messaging.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

}
