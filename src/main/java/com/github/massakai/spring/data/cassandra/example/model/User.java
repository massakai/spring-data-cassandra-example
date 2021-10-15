package com.github.massakai.spring.data.cassandra.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("users")
@Data
@AllArgsConstructor
public class User {

  @Id
  private String id;
  @Column("first_name")
  private String firstName;
  @Column("last_name")
  private String lastName;
}
