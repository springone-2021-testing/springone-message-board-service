package springone.messageboardservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class MessageServiceTests {

    private MessageService service;

    private MessageRepository repository;

    @BeforeEach
    void setup() {
        this.repository = mock(MessageRepository.class);
        this.service = new MessageService(this.repository);
    }

    @Test
    void getMessagesShouldReturnMessages() {

        List<Message> messsageList = new ArrayList<Message>();
        Message message = new Message("Cora_Iberkleid", "Welcome to everyone!");
        messsageList.add(message);

        given(this.repository.findAll()).willReturn(messsageList);

        assertThat(this.service.getMessages().get(0).getUsername()).isEqualTo("Cora_Iberkleid");
        assertThat(this.service.getMessages().get(0).getText()).isEqualTo("Welcome to everyone!");
    }


}
