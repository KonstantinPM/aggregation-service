package org.comparus.aggregation.repository.query;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueryConfiguration {

    @Bean
    public QueryBuilder jdbcQueryBuilder() {
        return new SimpleJdbcQueryBuilder();
    }
}
