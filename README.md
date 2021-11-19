# spring-data-cassandra-ttl-example

## テーブル定義

```sql
CREATE TABLE users
(
    id         text,
    first_name text,
    last_name  text,
    PRIMARY KEY (id)
);
```

## テストの動かし方

予め、ローカルでCassandraを起動して、`test`キースペースにテーブルを作成しておく必要があります。

[DockerでCassandraを起動する](https://techblog.sasashima.works/archives/513)を参照。

また、実行時は環境変数`CASSANDRA_USER`, `CASSANDRA_PASSWORD`を設定してやる必要があります。

Cassandraの設定はapplication.yamlに書かれています。