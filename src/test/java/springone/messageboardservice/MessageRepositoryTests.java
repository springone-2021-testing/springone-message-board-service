package springone.messageboardservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MessageRepositoryTests {

    @Autowired
    private MessageRepository messageRepository;

    @Test
    void findAllReturnsMessages() {
        this.messageRepository.save(new Message("Cora", "Welcome to everyone!"));
        assertThat(this.messageRepository.findAll().size()).isEqualTo(1);
        assertThat(this.messageRepository.findAll().get(0)).isInstanceOf(Message.class);
        assertThat(this.messageRepository.findAll().get(0).getUsername()).isEqualTo("Cora");
        assertThat(this.messageRepository.findAll().get(0).getText()).isEqualTo("Welcome to everyone!");
    }

}
