package com.github.massakai.spring.data.cassandra.example.repository;

import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.literal;

import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.github.massakai.spring.data.cassandra.example.model.User;
import java.time.Duration;
import org.springframework.data.cassandra.core.EntityWriteResult;
import org.springframework.data.cassandra.core.InsertOptions;
import org.springframework.data.cassandra.core.ReactiveCassandraOperations;
import reactor.core.publisher.Mono;

/**
 * UserTtlOperationsインターフェースの実装
 *
 * <p>SpringがUserRepositoryをBean定義するときにカスタム実装は インターフェース名+"Impl" で探索するのでクラス名は変更しないこと</p>
 */
@SuppressWarnings("unused")
public class UserTtlRepositoryImpl implements UserTtlRepository {

  private final ReactiveCassandraOperations reactiveCassandraOperations;

  public UserTtlRepositoryImpl(final ReactiveCassandraOperations reactiveCassandraOperations) {
    this.reactiveCassandraOperations = reactiveCassandraOperations;
  }

  @Override
  public Mono<User> save(final User user, final Duration ttl) {
    InsertOptions insertOptions = InsertOptions.builder().ttl(ttl).build();
    return reactiveCassandraOperations.insert(user, insertOptions)
        .map(EntityWriteResult::getEntity);
  }

  @Override
  public Mono<Duration> findFirstNameTtlById(final String id) {
    return findColumnTtlByIdAndColumnName(id, User.FIRST_NAME_COLUMN_NAME);
  }

  @Override
  public Mono<Duration> findLastNameTtlById(final String id) {
    return findColumnTtlByIdAndColumnName(id, User.LAST_NAME_COLUMN_NAME);
  }

  private Mono<Duration> findColumnTtlByIdAndColumnName(final String id, final String columnName) {
    SimpleStatement statement = QueryBuilder.selectFrom(User.TABLE_NAME)
        .ttl(columnName)
        .whereColumn(User.ID_COLUMN_NAME)
        .isEqualTo(literal(id))
        .build();

    return reactiveCassandraOperations.selectOne(statement, Integer.class)
        .map(Duration::ofSeconds);
  }
}
