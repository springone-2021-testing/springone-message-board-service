package springone.messageboardservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class ApiResponse {
    private String parameter;
    private String status;
    private String operation;
}
