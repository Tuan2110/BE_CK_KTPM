package vn.edu.iuh.subject.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import vn.edu.iuh.subject.external.Student;

@FeignClient(name = "STUDENT-SERVICE")
public interface StudentClient {

    @GetMapping("/students/profile")
    Student getProfile(@RequestHeader("Authorization") String token);

}
