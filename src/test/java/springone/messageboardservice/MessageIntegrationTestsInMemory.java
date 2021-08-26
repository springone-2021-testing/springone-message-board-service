package springone.messageboardservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest(properties = "spring.sql.init.mode=always", webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringBootTest(properties = { "logging.level.org.flywaydb=debug", "spring.flyway.enabled=true", "spring.flyway.locations=classpath:db/migration/,classpath:db/integration/" }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageIntegrationTestsInMemory {

    @Autowired
    private TestRestTemplate testRestTemplate;

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
        assertThat(message.getUsername()).isEqualTo("Cora_Iberkleid");
        assertThat(message.getText()).isEqualTo("Welcome to everyone!");
    }

}
