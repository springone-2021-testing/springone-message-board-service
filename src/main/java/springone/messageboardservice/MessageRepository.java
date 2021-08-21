package springone.messageboardservice;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface MessageRepository extends JpaRepository <Message, Integer> {


    List<Message> findAll();

    Message save(Message newMessage);

    List<Message> deleteByUsername(String username);

    List<Message> deleteByTextContains(String keyword);

    List<Message> deleteByTextContainsAndUsername(String keyword, String username);
}
