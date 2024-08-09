package org.comparus.aggregation.repository.factory;

import org.comparus.aggregation.data.DatabaseType;
import org.comparus.aggregation.data.User;
import org.comparus.aggregation.properties.JdbcProperties;
import org.comparus.aggregation.repository.query.QueryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.AbstractMap.SimpleEntry;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class FactoryConfiguration {

    @Bean
    public RepositoryFactory<User> userJdbcRepositoryFactory(QueryBuilder jdbcQueryBuilder, JdbcProperties jdbcProperties) {
        return new UserJdbcRepositoryFactory(jdbcQueryBuilder, jdbcProperties);
    }

    @Bean
    public RepositoryFactory<User> userMongoRepositoryFactory() {
        return new UserMongoRepositoryFactory();
    }

    @Bean
    public Map<DatabaseType, RepositoryFactory<User>> userFactoriesMap(List<RepositoryFactory<User>> factories) {
        return factories.stream()
                        .flatMap(factory -> factory.databaseTypes()
                                                   .stream()
                                                   .map(type -> new SimpleEntry<>(type, factory)))
                        .collect(Collectors.toMap(SimpleEntry::getKey,
                                                  SimpleEntry::getValue,
                                                  (a, b) -> a,
                                                  () -> new EnumMap<>(DatabaseType.class)));
    }
}
