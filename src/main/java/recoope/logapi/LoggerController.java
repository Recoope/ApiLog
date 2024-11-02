package recoope.logapi;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/log")
public class LoggerController {

    private ILogRepository logRepository;
    private IActionRepository actionRepository;

    public LoggerController(ILogRepository logRepository, IActionRepository actionRepository) {
        this.logRepository = logRepository;
        this.actionRepository = actionRepository;
    }

    @Operation(summary = "Gerar Log")
    @PostMapping
    public ResponseEntity log(@RequestBody LogDTO logDTO) {
        Action action = actionRepository.findFirstByAction(logDTO.getAction());

        if (action == null) {
            action = new Action(UUID.randomUUID().toString(), logDTO.getAction());
            actionRepository.save(action);
        }

        Log log = new Log(UUID.randomUUID().toString(), logDTO.getCompanyId(), action.get_id(), LocalDateTime.now());
        logRepository.save(log);
        return ResponseEntity.ok(log);
    }

    @Operation(summary = "Listar logs")
    @GetMapping
    public ResponseEntity<List<Log>> getLogs() {
        return ResponseEntity.ok(logRepository.findAll());
    }

    @Operation(summary = "Mostrar páginas mais acessadas")
    @GetMapping("/top")
    public ResponseEntity<List<PageAccessDTO>> getTopPages() {
        List<PageAccessDTO> result = logRepository.findTopAccessedPages();
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Mostrar páginas menos acessadas")
    @GetMapping("/least")
    public ResponseEntity<List<PageAccessDTO>> getLeastPages() {
        List<PageAccessDTO> result = logRepository.findLeastAccessedPages();
        return ResponseEntity.ok(result);
    }
}
