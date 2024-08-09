package org.comparus.aggregation.repository.factory;

import org.bson.Document;
import org.comparus.aggregation.properties.AggregationDataProperties.AggregationDataSource;
import org.comparus.aggregation.data.User;

import java.util.Map;
import java.util.function.Function;

public class UserMongoRepositoryFactory extends AbstractMongoRepositoryFactory<User> {

    @Override
    protected Function<Document, User> createMapper(AggregationDataSource aggregationDataSource) {
        Map<String, String> mapping = aggregationDataSource.getMapping() != null ?
                                      aggregationDataSource.getMapping() :
                                      Map.of();
        return document -> User.builder()
                               .id(document.getString(mapping.getOrDefault("id", "id")))
                               .username(document.getString(mapping.getOrDefault("username", "username")))
                               .name(document.getString(mapping.getOrDefault("name", "name")))
                               .surname(document.getString(mapping.getOrDefault("surname", "surname")))
                               .build();
    }
}
