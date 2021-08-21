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

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<Message> getMessages() {
        return this.service.getMessages();
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
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

    @DeleteMapping(value = "/{username}", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    AdminApiResponse deleteMessageByUsername(@PathVariable String username) {
        AdminApiResponse response;
        try {
            List<Message> deletedMessageList = this.service.deleteMessageByUsername(username);
            response = new AdminApiResponse("Success", "Delete", deletedMessageList.get(0).getId().toString());
        } catch (Exception e) {
            response = new AdminApiResponse("Failure", "Delete", "0");
        }
        return response;
    }

}
