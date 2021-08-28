package springone.messageboardservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class MessageRepositoryTests {

    @Autowired
    private MessageRepository messageRepository;

    @Test
    void findAllReturnsMessages() {
        this.messageRepository.save(new Message("Cora_Iberkleid", "Welcome to everyone!"));
        assertThat(this.messageRepository.findAll().size()).isEqualTo(1);
        assertThat(this.messageRepository.findAll().get(0)).isInstanceOf(Message.class);
        assertThat(this.messageRepository.findAll().get(0).getUsername()).isEqualTo("Cora_Iberkleid");
        assertThat(this.messageRepository.findAll().get(0).getText()).isEqualTo("Welcome to everyone!");
    }

    @Test
    void deleteByUsernameDoesNotReturnMessages() {
        this.messageRepository.save(new Message("Cora_Iberkleid", "Welcome to everyone!"));
        assertThat(this.messageRepository.findAll().size()).isEqualTo(1);
        this.messageRepository.deleteByUsername("Cora_Iberkleid");
        assertThat(this.messageRepository.findAll().size()).isEqualTo(0);
    }

}
