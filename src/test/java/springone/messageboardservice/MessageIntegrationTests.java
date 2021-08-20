package springone.messageboardservice;

import org.hibernate.dialect.PostgreSQL9Dialect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
//@SpringBootTest(properties = "spring.sql.init.mode=always", webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringBootTest(properties = { "logging.level.org.flywaydb=debug", "spring.flyway.enabled=true", "spring.flyway.locations=classpath:db/migration/,classpath:db/integration/" }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageIntegrationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

//    static DockerImageName bitnamiPostgres = DockerImageName.parse("bitnami/postgresql").asCompatibleSubstituteFor("postgres");
//
//    @Container
//    static PostgreSQLContainer<?> db = new PostgreSQLContainer<>(bitnamiPostgres).withEnv("PGDATA", "/opt/bitnami/postgresql/conf/").withUsername("0");

    @Container
    static PostgreSQLContainer<?> db = new PostgreSQLContainer<>("postgres");

    @DynamicPropertySource
    static void registerPostgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.jpa.database-platform", PostgreSQL9Dialect.class::getName);
        registry.add("spring.datasource.url", () -> db.getJdbcUrl());
        registry.add("spring.datasource.username", () -> db.getUsername());
        registry.add("spring.datasource.password", () -> db.getPassword());
    }

    @Test
    void getMessagesSucceeds() {
        // Dynamically create type for List<Message> - required to properly
        // parse the response for a composite class in an HTTP exchange
        var ptr = new ParameterizedTypeReference<List<Message>>() {
        };
        var exchange =
                this.testRestTemplate.exchange("/message", HttpMethod.GET, null, ptr);

        assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<Message> messageList = exchange.getBody();
        Message message = messageList.get(0);
        assertThat(message.getUsername()).isEqualTo("Cora");
        assertThat(message.getText()).isEqualTo("Welcome to everyone!");
    }

}
