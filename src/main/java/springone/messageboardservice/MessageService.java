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

}
