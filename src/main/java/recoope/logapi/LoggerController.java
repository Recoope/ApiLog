package recoope.logapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/log")
public class LoggerController {

    private LogRepository logRepository;

    public LoggerController(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @PostMapping
    public ResponseEntity log(@RequestBody Log log) {
        logRepository.save(log);
        return ResponseEntity.ok(log);
    }

    @GetMapping
    public ResponseEntity<List<Log>> getLogs() {
        return ResponseEntity.ok(logRepository.findAll());
    }
}
