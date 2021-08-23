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

    @DeleteMapping(value = "", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    AdminApiResponse deleteMessageByUsernameOrKeyword(@RequestParam(name="username",defaultValue = "") String username,@RequestParam(name="keyword",defaultValue = "") String keyword) {
        AdminApiResponse response;
        try {
            if(keyword.length()==0) {
                List<Message> deletedMessageList = this.service.deleteMessageByUsername(username);
                response = new AdminApiResponse("Success", "Delete", Integer.toString(deletedMessageList.size()));
            } else if(username.length()==0) {
                List<Message> deletedMessageList = this.service.deleteMessageByKeyword(keyword);
                response = new AdminApiResponse("Success", "Delete", Integer.toString(deletedMessageList.size()));
            } else {
                List<Message> deletedMessageList = this.service.deleteMessageByKeywordAndUsername(keyword, username);
                response = new AdminApiResponse("Success", "Delete", Integer.toString(deletedMessageList.size()));
            }
        } catch (Exception e) {
            response = new AdminApiResponse("Failure", "Delete", "-1");
        }
        return response;
    }

    @Deprecated
    @DeleteMapping(value = "/{username}", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    AdminApiResponse deleteMessageByUsername(@PathVariable String username) {
        AdminApiResponse response;
        try {
            List<Message> deletedMessageList = this.service.deleteMessageByUsername(username);
            response = new AdminApiResponse("Success", "Delete", Integer.toString(deletedMessageList.size()));
        } catch (Exception e) {
            response = new AdminApiResponse("Failure", "Delete", "-1");
        }
        return response;
    }

}
