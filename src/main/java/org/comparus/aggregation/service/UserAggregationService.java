package org.comparus.aggregation.service;

import lombok.RequiredArgsConstructor;
import org.comparus.aggregation.data.User;
import org.comparus.aggregation.repository.Repository;

import java.util.List;

@RequiredArgsConstructor
public class UserAggregationService implements AggregationService<User> {

    private final List<Repository<User>> userRepositories;

    @Override
    public List<User> findAll() {
        return userRepositories.stream()
                               .flatMap(repository -> repository.findAll()
                                                                .stream())
                               .toList();
    }
}
