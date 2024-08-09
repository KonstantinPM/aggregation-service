package org.comparus.aggregation.properties;

import lombok.Value;
import org.comparus.aggregation.data.DatabaseType;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Value
@ConfigurationProperties(prefix = "jdbc")
public class JdbcProperties {

    Map<DatabaseType, String> drivers;
}
