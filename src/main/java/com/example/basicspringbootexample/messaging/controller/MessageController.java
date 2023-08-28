package com.example.basicspringbootexample.messaging.controller;

import com.example.basicspringbootexample.messaging.model.Message;
import com.example.basicspringbootexample.messaging.service.MessageService;
import com.example.basicspringbootexample.messaging.service.MessageVisibilityService;
import java.util.List;
import java.util.Optional;
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
public class MessageController {

  private final MessageService messageService;
  private final MessageVisibilityService messageVisibilityService;

  @Autowired
  public MessageController(
      MessageService messageService, MessageVisibilityService messageVisibilityService) {
    this.messageService = messageService;
    this.messageVisibilityService = messageVisibilityService;
  }

  @PostMapping("/message")
  public void createMessage(@RequestBody Message message) {
    messageService.createMessage(message);
  }

  @GetMapping("/message")
  public List<Message> getMessages() {
    return messageService.getMessages();
  }

  @GetMapping("/message/{id}")
  public ResponseEntity<Message> getMessage(@PathVariable long id) {
    Optional<Message> messageOptional = messageService.getMessageById(id);
    if (messageOptional.isPresent()) {
      return ResponseEntity.ok(messageOptional.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/message/{id}")
  public ResponseEntity<String> updateMessage(@PathVariable long id, @RequestBody Message message) {
    return messageService.updateMessage(message, id);
  }

  @DeleteMapping("/message/{id}")
  public void deleteMessage(@PathVariable long id) {
    messageService.deleteMessage(id);
  }

  @PostMapping("/message/{id}/visibility")
  public void updateMessageVisibility(
      @PathVariable long id, @RequestBody List<Long> userIds) {
    messageVisibilityService.addMessageVisibilityToUsers(id, userIds);
  }
}
