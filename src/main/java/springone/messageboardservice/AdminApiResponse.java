package springone.messageboardservice;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminApiResponse {
    private String message;
    private String type;
    private String parameter;
}
