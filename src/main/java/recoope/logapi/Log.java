package recoope.logapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user_logs")
public class Log {
    @MongoId
    private String _id;

    private String companyId;

    private String action_Id;

    private LocalDateTime timestamp = LocalDateTime.now();
}
