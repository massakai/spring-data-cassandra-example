package com.github.massakai.spring.data.cassandra.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.Table;

@Table(User.TABLE_NAME)
@Data
@AllArgsConstructor
public class User {

  public static final String TABLE_NAME = "users";
  public static final String ID_COLUMN_NAME = "id";
  public static final String FIRST_NAME_COLUMN_NAME = "first_name";
  public static final String LAST_NAME_COLUMN_NAME = "last_name";

  @Id
  @Column(ID_COLUMN_NAME)
  private String id;
  @Column(FIRST_NAME_COLUMN_NAME)
  private String firstName;
  @Column(LAST_NAME_COLUMN_NAME)
  private String lastName;
}
