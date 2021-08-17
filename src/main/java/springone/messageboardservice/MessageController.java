package springone.messageboardservice;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
class MessageController {

    private final MessageService service;

    MessageController(MessageService service) {
        this.service = service;
    }

    @GetMapping(value = "", produces = "application/json;charset=UTF-8")
    List<Message> getMessages() {
        return this.service.getMessages();
    }

//    @RequestMapping(value = "",
//            method=RequestMethod.POST,
//            consumes="application/json",
//            produces="application/json;charset=UTF-8")
    @PostMapping(value = "", consumes = "application/json;charset=UTF-8",
        produces="application/json;charset=UTF-8")
    ApiResponse postMessage (@RequestBody CreateMessage newMessage) {
        ApiResponse response= new ApiResponse("0","Failure: Name must be Uppercase","Create");
        try {
            var result = this.service.addMessage(newMessage.getUsername(), newMessage.getText());
            response = new ApiResponse(result.getId().toString(),"Success","Create" );
        } catch (Exception e) {
            response =new ApiResponse("0",e.getMessage(), "Create");
        }
        return response;
    }
}
