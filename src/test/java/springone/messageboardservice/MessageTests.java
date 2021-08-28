package springone.messageboardservice;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class MessageTests {

    @Test
    void createMessageWithLowercaseUsernameShouldThrowException() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Message("andy", "I am here too!"));
    }

    @Test
    void createMessageWithEmptyTextShouldThrowException() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Message("Gabry", ""));
    }

    @Test
    void createMessageWithUsernameAndTextShouldSucceed() {
        assertThatNoException().isThrownBy(() -> new Message("Cora_Iberkleid", "Welcome to everyone!"));
    }
    
}




