package org.comparus.aggregation.repository;

import org.comparus.aggregation.data.DatabaseType;
import org.comparus.aggregation.data.User;
import org.comparus.aggregation.properties.AggregationDataProperties;
import org.comparus.aggregation.repository.factory.RepositoryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
public class RepositoryConfiguration {

    @Bean
    public List<Repository<User>> userRepositories(Map<DatabaseType, RepositoryFactory<User>> userFactoriesMap,
                                                   AggregationDataProperties properties) {
        return properties.getDataSources()
                         .stream()
                         .map(dataSource -> userFactoriesMap.get(dataSource.getStrategy())
                                                            .createRepository(dataSource))
                         .toList();
    }
}
