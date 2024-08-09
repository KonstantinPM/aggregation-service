package org.comparus.aggregation.repository.factory;

import lombok.RequiredArgsConstructor;
import org.comparus.aggregation.data.DatabaseType;
import org.comparus.aggregation.properties.AggregationDataProperties.AggregationDataSource;
import org.comparus.aggregation.properties.JdbcProperties;
import org.comparus.aggregation.repository.SimpleJdbcRepository;
import org.comparus.aggregation.repository.Repository;
import org.comparus.aggregation.repository.query.QueryBuilder;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

import static org.comparus.aggregation.data.DatabaseType.MYSQL;
import static org.comparus.aggregation.data.DatabaseType.POSTGRES;

@RequiredArgsConstructor
public abstract class AbstractJdbcRepositoryFactory<T> implements RepositoryFactory<T> {

    private final QueryBuilder queryBuilder;
    private final JdbcProperties jdbcProperties;

    @Override
    public Repository<T> createRepository(AggregationDataSource aggregationDataSource) {
        String driverClassName = jdbcProperties.getDrivers()
                                               .get(aggregationDataSource.getStrategy());
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DataSourceBuilder.create()
                                                                      .driverClassName(driverClassName)
                                                                      .url(aggregationDataSource.getUrl())
                                                                      .username(aggregationDataSource.getUser())
                                                                      .password(aggregationDataSource.getPassword())
                                                                      .build());

        return new SimpleJdbcRepository<>(jdbcTemplate,
                                          queryBuilder,
                                          getRowMapper(aggregationDataSource),
                                          aggregationDataSource.getTable());
    }

    @Override
    public List<DatabaseType> databaseTypes() {
        return List.of(POSTGRES, MYSQL);
    }

    protected abstract RowMapper<T> getRowMapper(AggregationDataSource aggregationDataSource);
}
