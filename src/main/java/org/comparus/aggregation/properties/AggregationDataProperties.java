package org.comparus.aggregation.properties;

import lombok.Value;
import org.comparus.aggregation.data.DatabaseType;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@Value
@ConfigurationProperties(prefix = "aggregation")
public class AggregationDataProperties {

    List<AggregationDataSource> dataSources;

    @Value
    public static class AggregationDataSource {

        String name;
        DatabaseType strategy;
        String url;
        String table;
        String database;
        String user;
        String password;
        Map<String, String> mapping;
    }
}
