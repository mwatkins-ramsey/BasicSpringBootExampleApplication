package com.example.basicspringbootexample.messaging.repository;

import com.example.basicspringbootexample.messaging.exceptions.InvalidUserIdMessageIdReference;
import jakarta.transaction.Transactional;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MessageVisibilityRepository {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public MessageVisibilityRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Transactional
  public void makeMessageAvailableToUsers(List<Map.Entry<Long, Long>> userId_messageId) {
    String insertString = "INSERT INTO users_messages (user_id, message_id) VALUES (?, ?)";
    var bpss =
        new BatchPreparedStatementSetter() {
          @Override
          public void setValues(PreparedStatement ps, int i) throws SQLException {
            long cUserId = userId_messageId.get(i).getKey();
            long cMessageId = userId_messageId.get(i).getValue();
            // parameterIndex does not start at 0!
            ps.setLong(1, cUserId);
            ps.setLong(2, cMessageId);
          }

          @Override
          public int getBatchSize() {
            return userId_messageId.size();
          }
        };
    try {
      jdbcTemplate.batchUpdate(insertString, bpss);
    } catch (DataIntegrityViolationException e) {
      throw new InvalidUserIdMessageIdReference("user_id or message_id is invalid", e, true);
    }
  }
}
