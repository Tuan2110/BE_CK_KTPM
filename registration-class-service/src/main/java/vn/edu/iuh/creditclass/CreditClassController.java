package vn.edu.iuh.creditclass;


import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.creditclass.dto.ClassDTO;
import vn.edu.iuh.creditclass.response.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/credit-class")
@RequiredArgsConstructor
public class CreditClassController {
    private final CreditClassService creditClassService;
    private final SemesterRepository semesterRepository;

    @GetMapping
    public ResponseEntity<List<ClassDTO>> getCreditClassBySubjectId(@PathParam("subjectId") Long subjectId, @PathParam("semesterId") Long semesterId) {
        return ResponseEntity.ok(creditClassService.getCreditClassBySubjectId(subjectId,semesterId));
    }
    @PutMapping("/{classId}")
    public ResponseEntity<?> registerClass(@RequestHeader("Authorization") String token, @PathVariable Long classId) {
        return ResponseEntity.ok(creditClassService.registerClass(token,classId));
    }
    @GetMapping("/{classId}")
    public ResponseEntity<ClassDTO> getClassById(@PathVariable Long classId) {
        return ResponseEntity.ok(creditClassService.getClassById(classId));
    }

    @GetMapping("/semester")
    public ResponseEntity<List<Semester>> getCreditClassBySemesterId() {
        return ResponseEntity.ok(semesterRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createClass(@RequestBody CreditClass creditClass) {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .status(200)
                        .message("Create class success")
                        .data(creditClassService.createClass(creditClass))
                        .build()
        );
    }
}
