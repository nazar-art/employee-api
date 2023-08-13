package com.ukeess;

/*import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.Map;
import java.util.stream.Stream;

public class StandaloneApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    private static final MySQLContainer<?> MYSQL_SQL = new MySQLContainer<>("mysql:8.1.0")
            .withDatabaseName("ukeess_db")
            .withUsername("test")
            .withPassword("test");

    private static Map<String, Object> runDependencies() {
        Startables.deepStart(Stream.of(MYSQL_SQL)).join();

        return Map.of(
                "spring.datasource.url", MYSQL_SQL.getJdbcUrl(),
                "spring.datasource.username", MYSQL_SQL.getUsername(),
                "spring.datasource.password", MYSQL_SQL.getPassword()
        );
    }

    @Override
    public void initialize(ConfigurableApplicationContext context) {
        var env = context.getEnvironment();
        var dependencies = new MapPropertySource("dependencies", runDependencies());
        env.getPropertySources().addFirst(dependencies);
    }
}*/
