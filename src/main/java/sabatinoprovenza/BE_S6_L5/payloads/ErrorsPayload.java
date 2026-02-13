package sabatinoprovenza.BE_S6_L5.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorsPayload {
    private String message;
    private LocalDateTime timestamp;

    public ErrorsPayload(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

}

