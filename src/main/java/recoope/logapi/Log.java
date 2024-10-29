package recoope.logapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "logs")
public class Log {
    @Id
    private UUID id = UUID.randomUUID();

    public enum Action {
        LOGIN, REGISTER, LOGOUT, SCREEN_VIEWED, BID
    }

    private String companyId;

    private Action action;

    private Map<String, Object> data;

    private LocalDateTime timestamp = LocalDateTime.now();
}
