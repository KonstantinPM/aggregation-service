package org.comparus.aggregation.service;

import org.comparus.aggregation.data.User;
import org.comparus.aggregation.repository.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ServiceConfiguration {

    @Bean
    public AggregationService<User> userAggregationService(List<Repository<User>> userRepositories) {
        return new UserAggregationService(userRepositories);
    }
}
