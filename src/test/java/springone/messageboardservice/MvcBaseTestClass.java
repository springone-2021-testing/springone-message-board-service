package springone.messageboardservice;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;

public class MvcBaseTestClass {

    private MessageService service;

    private MessageController controller;

    @BeforeEach
    public void setup() {
        this.service = mock(MessageService.class);
        this.controller = new MessageController(this.service);
        RestAssuredMockMvc.standaloneSetup(this.controller);

        Mockito.when(this.service.getMessages())
                .thenReturn(List.of(new Message(1, "Cora_Iberkleid", "Welcome to everyone!")));

        Mockito.when(this.service.addMessage(eq("Cora_Iberkleid"),eq("Welcome to everyone!")))
                .thenReturn(new Message(1,"Cora_Iberkleid","Welcome to everyone!"));

        Mockito.when(this.service.addMessage(eq("andy"),eq("I am here too!")))
                .thenThrow(new java.lang.IllegalArgumentException("Failure: Name format must be First_Last"));

        Mockito.when(this.service.deleteMessageByUsername((eq("Cora"))))
                .thenReturn(List.of(new Message(1, "Cora", "Welcome to everyone!")));

    }

}
