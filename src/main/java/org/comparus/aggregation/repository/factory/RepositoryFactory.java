package org.comparus.aggregation.repository.factory;

import org.comparus.aggregation.data.DatabaseType;
import org.comparus.aggregation.properties.AggregationDataProperties.AggregationDataSource;
import org.comparus.aggregation.repository.Repository;

import java.util.List;

public interface RepositoryFactory<T> {

    Repository<T> createRepository(AggregationDataSource aggregationDataSource);

    List<DatabaseType> databaseTypes();
}
