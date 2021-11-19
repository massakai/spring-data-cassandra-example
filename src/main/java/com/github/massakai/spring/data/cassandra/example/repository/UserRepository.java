package com.github.massakai.spring.data.cassandra.example.repository;

import com.github.massakai.spring.data.cassandra.example.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, String>, UserTtlRepository {

}
