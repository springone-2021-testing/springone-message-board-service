package springone.messageboardservice;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface MessageRepository extends JpaRepository <Message, Integer> {

    List<Message> findAll();

}
