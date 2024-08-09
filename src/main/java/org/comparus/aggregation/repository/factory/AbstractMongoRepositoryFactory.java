package org.comparus.aggregation.repository.factory;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.Document;
import org.comparus.aggregation.data.DatabaseType;
import org.comparus.aggregation.properties.AggregationDataProperties.AggregationDataSource;
import org.comparus.aggregation.repository.SimpleMongoRepository;
import org.comparus.aggregation.repository.Repository;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;
import java.util.function.Function;

import static org.comparus.aggregation.data.DatabaseType.MONGO;

public abstract class AbstractMongoRepositoryFactory<T> implements RepositoryFactory<T> {

    @Override
    public Repository<T> createRepository(AggregationDataSource aggregationDataSource) {
        MongoClient mongoClient = MongoClients.create(aggregationDataSource.getUrl());
        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, aggregationDataSource.getDatabase());
        return new SimpleMongoRepository<>(mongoTemplate,
                                           createMapper(aggregationDataSource),
                                           aggregationDataSource.getTable());
    }

    @Override
    public List<DatabaseType> databaseTypes() {
        return List.of(MONGO);
    }

    protected abstract Function<Document, T> createMapper(AggregationDataSource aggregationDataSource);
}
