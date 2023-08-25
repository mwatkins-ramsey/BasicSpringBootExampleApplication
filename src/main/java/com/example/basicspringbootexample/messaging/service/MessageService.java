package com.example.basicspringbootexample.messaging.service;

import com.example.basicspringbootexample.messaging.model.Message;
import com.example.basicspringbootexample.messaging.repository.MessageRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
  private final MessageRepository messageRepository;

  @Autowired
  public MessageService(MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  public void createMessage(Message message) {
    messageRepository.save(message);
  }

  public List<Message> getMessages() {
    return messageRepository.findAll();
  }

  public Optional<Message> getMessageById(long id) {
    return messageRepository.findById(id);
  }

  public ResponseEntity<String> updateMessage(Message message, long id) {
    Optional<Message> existingMessageOptional = messageRepository.findById(id);
    if (existingMessageOptional.isPresent()) {
      Message existingMessage = existingMessageOptional.get();
      existingMessage.setAuthor_id(message.getAuthor_id());
      existingMessage.setMessageBody(message.getMessageBody());
      messageRepository.save(existingMessage);
      return ResponseEntity.ok().build();
    } else {
      String errorMessage = "Message with ID " + id + " not found.";
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
  }

  public void deleteMessage(long id) {
    Optional<Message> messageOptional = messageRepository.findById(id);
    if (messageOptional.isPresent()) {
      messageRepository.deleteById(id);
    } else {
      throw new EntityNotFoundException();
    }
  }
}
