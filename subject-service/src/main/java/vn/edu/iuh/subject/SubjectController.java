package vn.edu.iuh.subject;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.subject.dto.SubjectDTO;

import java.util.List;

@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;

    @GetMapping("/major/{majorId}")
    public ResponseEntity<List<SubjectDTO>> getSubjectsByMajorId(@PathVariable Long majorId) {
        return ResponseEntity.ok(subjectService.getSubjectsByMajorId(majorId));
    }
    @GetMapping("/completed")
    public ResponseEntity<List<SubjectDTO>> getCompletedSubjectsByMajorId(@RequestParam List<Long> subjectIds) {
        System.out.println(subjectIds);
        return ResponseEntity.ok(subjectService.getCompletedSubjectsByMajorId(subjectIds));
    }
    @GetMapping
    public ResponseEntity<List<SubjectDTO>> getSubjects(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(subjectService.getSubjects(token));
    }
    @GetMapping("/{subjectId}")
    public ResponseEntity<SubjectDTO> getSubjectById(@PathVariable Long subjectId) {
        return ResponseEntity.ok(subjectService.getSubjectById(subjectId));
    }
}
