package org.comparus.aggregation.repository.factory;

import org.comparus.aggregation.data.User;
import org.comparus.aggregation.properties.AggregationDataProperties;
import org.comparus.aggregation.properties.JdbcProperties;
import org.comparus.aggregation.repository.query.QueryBuilder;
import org.springframework.jdbc.core.RowMapper;

import java.util.Map;

public class UserJdbcRepositoryFactory extends AbstractJdbcRepositoryFactory<User> {

    public UserJdbcRepositoryFactory(QueryBuilder queryBuilder, JdbcProperties jdbcProperties) {
        super(queryBuilder, jdbcProperties);
    }

    @Override
    protected RowMapper<User> getRowMapper(AggregationDataProperties.AggregationDataSource aggregationDataSource) {
        Map<String, String> mapping = aggregationDataSource.getMapping() != null ?
                                      aggregationDataSource.getMapping() :
                                      Map.of();
        return (rs, rowNum) -> User.builder()
                                   .id(rs.getString(mapping.getOrDefault("id", "id")))
                                   .username(rs.getString(mapping.getOrDefault("username", "username")))
                                   .name(rs.getString(mapping.getOrDefault("name", "username")))
                                   .surname(rs.getString(mapping.getOrDefault("surname", "surname")))
                                   .build();
    }
}
