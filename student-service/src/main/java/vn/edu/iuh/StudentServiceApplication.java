package vn.edu.iuh;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import vn.edu.iuh.student.Student;
import vn.edu.iuh.student.StudentRepository;

import java.util.Set;

@SpringBootApplication
@EnableFeignClients
public class StudentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner init(StudentRepository studentRepository) {
        return args -> {
//            Student student = new Student();
//            student.setName("Student 2");
//            student.setStudentId(123456L);
//            student.setEmail("2@gmail.com");
//            student.setPhone("1234567890");
//            student.setMajorId(1L);
//            student.setCompletedSubjects(Set.of(1L,2L,3L));
//            student.setRegisteredSubjects(Set.of(4L,5L,6L));
//            studentRepository.save(student);
        };
    }
}
