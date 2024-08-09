package org.comparus.aggregation.repository;

import java.util.List;

public interface Repository<T> {

    List<T> findAll();
}
