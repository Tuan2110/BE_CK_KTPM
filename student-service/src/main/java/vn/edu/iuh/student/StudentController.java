package vn.edu.iuh.student;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.student.dto.LoginDTO;
import vn.edu.iuh.student.dto.StudentDTO;
import vn.edu.iuh.student.response.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    public final StudentService studentService;


    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.createStudent(student));
    }
    @GetMapping("/profile")
    public ResponseEntity<Student> getProfile(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(studentService.getProfile(token));
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
}
