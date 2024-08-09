package org.comparus.aggregation.repository.query;

public class SimpleJdbcQueryBuilder implements QueryBuilder {

    private static final String FIND_ALL = "SELECT * FROM %s";

    @Override
    public String findAll(String table) {
        return String.format(FIND_ALL, table);
    }
}
