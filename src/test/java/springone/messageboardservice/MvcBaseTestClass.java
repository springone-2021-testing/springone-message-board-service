package springone.messageboardservice;

import io.restassured.config.EncoderConfig;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.config.RestAssuredMockMvcConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.Mockito.mock;

//@ExtendWith(SpringExtension.class)
//@SpringBootTest
public class MvcBaseTestClass {

//    @Autowired
//    private WebApplicationContext context;
//
//    @BeforeEach
//    void setup() {
//        RestAssuredMockMvc.webAppContextSetup(context);
//    }

//    @MockBean
//    private
    private MessageService service;

    private MessageController controller;

    @BeforeEach
    public void setup() {
        this.service = mock(MessageService.class);
        this.controller = new MessageController(this.service);
        // https://github.com/spring-cloud/spring-cloud-contract/issues/1428
        EncoderConfig encoderConfig = new EncoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false);
        RestAssuredMockMvc.config = new RestAssuredMockMvcConfig().encoderConfig(encoderConfig);
        RestAssuredMockMvc.standaloneSetup(this.controller);
        Mockito.when(this.service.getMessages())
                .thenReturn(List.of(new Message(1, "Cora", "Welcome to everyone!")));
    }

}
