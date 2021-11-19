package com.github.massakai.spring.data.cassandra.example.repository;

import com.github.massakai.spring.data.cassandra.example.model.User;
import java.time.Duration;
import reactor.core.publisher.Mono;

/**
 * UserエンティティのTTLに関するカスタムインターフェース
 */
public interface UserTtlRepository {

  /**
   * UserエンティティをTTL付きで保存する
   *
   * @param user ユーザー
   * @param ttl TTL
   * @return ユーザーのMono
   */
  Mono<User> save(User user, Duration ttl);

  /**
   * firstNameカラムのTTLを取得する
   *
   * @param id ユーザーID
   * @return TTLのMono
   */
  Mono<Duration> findFirstNameTtlById(String id);

  /**
   * lastNameカラムのTTLを取得する
   *
   * @param id ユーザーID
   * @return TTLのMono
   */
  Mono<Duration> findLastNameTtlById(String id);
}
