package com.github.massakai.spring.data.cassandra.example.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.massakai.spring.data.cassandra.example.model.User;
import com.github.massakai.spring.data.cassandra.example.repository.UserRepositoryIntegrationTest.TestConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;

@SpringBootTest(classes = TestConfiguration.class, webEnvironment = WebEnvironment.NONE)
class UserRepositoryIntegrationTest {

  @Configuration
  @ComponentScan("com.github.massakai.spring.data.cassandra.example.repository")
  @EnableReactiveCassandraRepositories(basePackages = "com.github.massakai.spring.data.cassandra.example.repository")
  @EnableAutoConfiguration
  static class TestConfiguration {

  }

  @Autowired
  private UserRepository userRepository;

  @Test
  @DisplayName("UserRepositoryでCRUDが実行できる")
  void testCRUD() {
    var userId = "1";
    var user1 = new User(userId, "Masashi", "Sakai");

    // Create
    userRepository.save(user1).block();

    // Read
    User user2 = userRepository.findById(userId).block();
    assertThat(user2).isEqualTo(user1);

    // Update
    user1.setLastName("Sasaki");
    userRepository.save(user1).block();
    User user3 = userRepository.findById(userId).block();
    assertThat(user3).isEqualTo(user1);
    assertThat(user3).isNotEqualTo(user2);

    // Delete
    userRepository.delete(user1).block();
    User user4 = userRepository.findById(userId).block();
    assertThat(user4).isNull();
  }

}