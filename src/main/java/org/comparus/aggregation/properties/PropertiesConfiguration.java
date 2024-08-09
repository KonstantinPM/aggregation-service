package org.comparus.aggregation.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({AggregationDataProperties.class, JdbcProperties.class})
public class PropertiesConfiguration {
}
