package org.comparus.aggregation.repository;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
public class SimpleMongoRepository<T> implements Repository<T> {

    private final MongoTemplate mongoTemplate;
    private final Function<Document, T> mapper;
    private final String collection;

    @Override
    public List<T> findAll() {
        return mongoTemplate.findAll(Document.class, collection)
                            .stream()
                            .map(mapper)
                            .toList();
    }
}
