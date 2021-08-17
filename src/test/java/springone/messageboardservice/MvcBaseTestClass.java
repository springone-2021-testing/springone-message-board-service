package springone.messageboardservice;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.util.List;

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
                .thenReturn(List.of(new Message(1, "Cora", "Welcome to everyone!")));
    }

}
