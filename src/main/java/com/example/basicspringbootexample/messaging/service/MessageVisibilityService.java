package com.example.basicspringbootexample.messaging.service;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.basicspringbootexample.messaging.repository.MessageVisibilityRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageVisibilityService {

  private final MessageVisibilityRepository messageVisibilityRepository;

  public MessageVisibilityService(MessageVisibilityRepository messageVisibilityRepository) {
    this.messageVisibilityRepository = messageVisibilityRepository;
  }

  public void addMessageVisibilityToUsers(long messageId, List<Long> userIds) {
    List<Map.Entry<Long, Long>> userIds_msgIds = new ArrayList<>();

    for (Long userId : userIds) {
      System.out.println("[" + userId + ", " + messageId + "]");
      userIds_msgIds.add(new AbstractMap.SimpleEntry<>(userId, messageId));
    }
    messageVisibilityRepository.makeMessageAvailableToUsers(userIds_msgIds);
  }
}
