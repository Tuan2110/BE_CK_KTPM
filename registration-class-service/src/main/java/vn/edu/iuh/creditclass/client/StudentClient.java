package vn.edu.iuh.creditclass.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import vn.edu.iuh.creditclass.external.Student;
import vn.edu.iuh.creditclass.response.ApiResponse;

@FeignClient(name = "STUDENT-SERVICE")
public interface StudentClient {

    @GetMapping("/students/profile")
    ApiResponse<Student> getProfile(@RequestHeader("Authorization") String token);

    @PutMapping("/students/update")
    ApiResponse<Student> updateStudent(@RequestHeader("Authorization") String token,@RequestBody Student student);
}
