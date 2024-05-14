package vn.edu.iuh.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import vn.edu.iuh.dto.ApiResponse;
import vn.edu.iuh.external.Student;

@FeignClient(name = "STUDENT-SERVICE")
public interface StudentClient {

    @GetMapping("/students/profile")
    ApiResponse<Student> getProfile(@RequestHeader("Authorization") String token);

}
