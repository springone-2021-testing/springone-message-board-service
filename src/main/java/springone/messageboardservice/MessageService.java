package springone.messageboardservice;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
class MessageService {

    private final MessageRepository repository;

    MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    public List<Message> getMessages() {
        return this.repository.findAll();
    }

    public Message addMessage(String user, String text) {
        return this.repository.save(new Message(user, text));
    }

    public List<Message> deleteMessageByUsername(String username) {
        return this.repository.deleteByUsername(username);
    }

}
