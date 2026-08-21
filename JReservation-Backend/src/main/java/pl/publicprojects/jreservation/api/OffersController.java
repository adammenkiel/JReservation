package pl.publicprojects.jreservation.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class OffersController {

    @GetMapping("/offers")
    public ResponseEntity<?> offers() {
        return ResponseEntity.ok("Test");
    }
}
