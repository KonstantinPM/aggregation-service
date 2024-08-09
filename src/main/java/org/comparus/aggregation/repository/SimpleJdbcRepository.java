package org.comparus.aggregation.repository;

import lombok.RequiredArgsConstructor;
import org.comparus.aggregation.repository.query.QueryBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

@RequiredArgsConstructor
public class SimpleJdbcRepository<T> implements Repository<T> {

    private final JdbcTemplate jdbcTemplate;
    private final QueryBuilder queryBuilder;
    private final RowMapper<T> rowMapper;
    private final String table;

    @Override
    public List<T> findAll() {
        return jdbcTemplate.query(queryBuilder.findAll(table), rowMapper);
    }
}
