package springone.messageboardservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "messages")
class Message {

    @Id
    @GeneratedValue
    private Integer id;

    private String username;

    private String text;

    public Message(String username, String text) {
        Assert.isTrue(Character.isUpperCase(username.charAt(0)), () -> "Failure: Name must be Uppercase");
        Assert.isTrue(text.length() > 0, () -> "Failure: Text must not be blank");
        this.username = username;
        this.text = text;
    }

}
