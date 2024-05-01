package vn.edu.iuh.major;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/majors")
@RequiredArgsConstructor
public class MajorController {
    private final MajorService majorService;

    @GetMapping("/{majorId}")
    public ResponseEntity<Major> getMajorById(@PathVariable Long majorId) {
        return ResponseEntity.ok(majorService.getById(majorId));
    }
}
