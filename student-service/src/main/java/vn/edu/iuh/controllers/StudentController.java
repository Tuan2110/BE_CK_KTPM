package vn.edu.iuh.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.models.Student;
import vn.edu.iuh.services.StudentService;
import vn.edu.iuh.dto.LoginDTO;
import vn.edu.iuh.dto.StudentDTO;
import vn.edu.iuh.dto.ValidateTokenRequest;
import vn.edu.iuh.dto.ValidateTokenResponse;
import vn.edu.iuh.response.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    public final StudentService studentService;


    @PostMapping("/token")
    public ApiResponse<ValidateTokenResponse> validateToken(@RequestBody ValidateTokenRequest token) {
        System.out.println("Token: " + token);
        return ApiResponse.<ValidateTokenResponse>builder()
                .data(studentService.validateToken(token))
                .status(200)
                .success(true)
                .build();
    }


    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.createStudent(student));
    }
    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<?>> getProfile(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .data(studentService.getProfile(token))
                        .status(200)
                        .success(true)
                        .build()
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .data(studentService.login(loginDTO.getStudentId(), loginDTO.getPassword()))
                        .status(200)
                        .success(true)
                        .build()
        );
    }
    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }
    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentService.getStudentById(studentId));
    }
    @PutMapping("/update")
    public ResponseEntity<Student> updateStudent(@RequestHeader("Authorization") String token,@RequestBody Student student){
        return ResponseEntity.ok(studentService.updateStudent(token, student));
    }
    @GetMapping("/calendar")
    public ResponseEntity<?> getCalendarClassByStudentId(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(studentService.getCalendarClassByStudentId(token));
    }

    @GetMapping("/total-credit")
    public ResponseEntity<?> getTotalCredit(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(studentService.getTotalCredit(token));
    }

    @GetMapping("/total-credit-completed")
    public ResponseEntity<?> getCompletedSubjects(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(studentService.getTotalCompletedCredit(token));
    }

}
