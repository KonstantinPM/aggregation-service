# Aggregation-service

Main goal of the service is aggregate users data from several databases

## Supported databases
The service supported databases described in [DatabaseType](src/main/java/org/comparus/aggregation/data/DatabaseType.java) enum
```java
public enum DatabaseType {
    POSTGRES,
    MYSQL,
    MONGO
}
```

## How to use
Add database properties to [application.yml](src/main/resources/application.yml) under
```yml
aggregation:
  data-sources:
```

example:
```yml
aggregation:
  data-sources:
    - name: db1
      strategy: postgres
      user: postgres_user
      password: postgres_password
      url: jdbc:postgresql://localhost:5432/userdb
      table: users
      mapping:
        id: user_id
        username: login
        name: first_name
        surname: last_name
    - name: db2
      strategy: postgres
      user: postgres_user_2
      password: postgres_password_2
      url: jdbc:postgresql://localhost:5433/user_database
      table: user_table
      mapping:
        id: ldap_login
        username: ldap_login
        name: name
        surname: surname
    - name: db3
      strategy: mysql
      user: mysql_user
      password: mysql_password
      url: jdbc:mysql://localhost:3306/user_db
      table: users_table
      mapping:
        id: user_id
        username: user_login
        name: user_name
        surname: user_surname
    - name: db4
      strategy: mongo
      url: mongodb://mongo_user:mongo_password@localhost:27017/userdb
      database: userdb
      table: users
      mapping:
        id: userId
        username: userLogin
        name: userName
        surname: userSurname
```

For postgres and mysql need to specify driver
```yml
jdbc:
  drivers:
    postgres: org.postgresql.Driver
    mysql: com.mysql.cj.jdbc.Driver
```

For mongo you can omit user and password fields. 
Add them to url field: 
```yml
url: mongodb://{user}:{password}@{host}:{port}/{database}
```

## Api
The service provide single rest endpoint by GET /public/users

Swagger link - http://{host}:{port}/swagger-ui.html

example response:
```json
[
  {
    "id": "pg1_user_id_1",
    "username": "vp",
    "name": "Vasya",
    "surname": "Petrov"
  },
  {
    "id": "pg2_user_id_3",
    "username": "tg",
    "name": "Tatiana",
    "surname": "Golovko"
  },
  {
    "id": "mysql1_user_id_1",
    "username": "nkdbv",
    "name": "Nikolay",
    "surname": "Dubov"
  },
  {
    "id": "mongo1UserId1",
    "username": "sven1",
    "name": "Alexander",
    "surname": "Sven"
  }
]
```
