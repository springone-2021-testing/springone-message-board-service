package springone.messageboardservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
class CreateMessage {

    private String username;

    private String text;

    public CreateMessage(String username, String text) {
        this.username = username;
        this.text = text;
    }
}
