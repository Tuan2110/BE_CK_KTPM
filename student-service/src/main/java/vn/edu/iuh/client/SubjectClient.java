package vn.edu.iuh.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.dto.SubjectDTO;

import java.util.List;

@FeignClient(name = "SUBJECT-SERVICE")
public interface SubjectClient {
    @GetMapping("/subjects/major/{majorId}")
    List<SubjectDTO> getSubjectsByMajorId(@PathVariable Long majorId);
    @GetMapping("/subjects/completed")
    List<SubjectDTO> getCompletedSubjectsByMajorId(@RequestParam List<Long> subjectIds);
}
