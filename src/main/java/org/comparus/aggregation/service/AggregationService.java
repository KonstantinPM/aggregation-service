package org.comparus.aggregation.service;

import java.util.List;

public interface AggregationService<T> {

    List<T> findAll();
}
