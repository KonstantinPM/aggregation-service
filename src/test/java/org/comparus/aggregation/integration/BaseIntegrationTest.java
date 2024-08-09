package org.comparus.aggregation.integration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.ComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Testcontainers
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
public abstract class BaseIntegrationTest {

    private static final int DEFAULT_POSTGRES_PORT = 5432;
    private static final int DEFAULT_MYSQL_PORT = 3306;
    private static final int DEFAULT_MONGO_PORT = 27017;
    private static final String POSTGRES = "postgres";
    private static final String POSTGRES_2 = "postgres2";
    private static final String MYSQL = "mysql";
    private static final String MONGO = "mongo";

    @Autowired
    protected TestRestTemplate testRestTemplate;

    @Container
    private static final ComposeContainer composeContainer = new ComposeContainer(
            new File("src/test/resources/docker-compose-test.yml"))
            .withExposedService(POSTGRES, DEFAULT_POSTGRES_PORT, Wait.forListeningPort())
            .withExposedService(POSTGRES_2, DEFAULT_POSTGRES_PORT, Wait.forListeningPort())
            .withExposedService(MYSQL, DEFAULT_MYSQL_PORT, Wait.forListeningPort())
            .withExposedService(MONGO, DEFAULT_MONGO_PORT, Wait.forListeningPort());

    @DynamicPropertySource
    protected static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("pg1.host", () -> composeContainer.getServiceHost(POSTGRES, DEFAULT_POSTGRES_PORT));
        registry.add("pg1.port", () -> composeContainer.getServicePort(POSTGRES, DEFAULT_POSTGRES_PORT));

        registry.add("pg2.host", () -> composeContainer.getServiceHost(POSTGRES_2, DEFAULT_POSTGRES_PORT));
        registry.add("pg2.port", () -> composeContainer.getServicePort(POSTGRES_2, DEFAULT_POSTGRES_PORT));

        registry.add("mysql1.host", () -> composeContainer.getServiceHost(MYSQL, DEFAULT_MYSQL_PORT));
        registry.add("mysql1.port", () -> composeContainer.getServicePort(MYSQL, DEFAULT_MYSQL_PORT));

        registry.add("mongo1.host", () -> composeContainer.getServiceHost(MONGO, DEFAULT_MONGO_PORT));
        registry.add("mongo1.port", () -> composeContainer.getServicePort(MONGO, DEFAULT_MONGO_PORT));
    }
}
