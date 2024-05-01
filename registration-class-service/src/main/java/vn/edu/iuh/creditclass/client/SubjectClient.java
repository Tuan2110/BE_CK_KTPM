package vn.edu.iuh.creditclass.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vn.edu.iuh.creditclass.dto.SubjectDTO;

@FeignClient(name = "SUBJECT-SERVICE")
public interface SubjectClient {
    @GetMapping("/subjects/{subjectId}")
    SubjectDTO getSubjectById(@PathVariable Long subjectId);
}
