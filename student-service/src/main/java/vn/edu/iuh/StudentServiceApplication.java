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
//            student.setName("Do Quoc Tuan");
//            student.setStudentId(11111111L);
//            student.setEmail("tuan@gmail.com");
//            student.setPhone("0365221451");
//            student.setMajorId(1L);
//            student.setPassword("123");
//            studentRepository.save(student);
//
//            Student student1 = new Student();
//            student1.setName("Nguyen Van A");
//            student1.setStudentId(22222222L);
//            student1.setEmail("a@gmail.com");
//            student1.setPhone("0365221452");
//            student1.setMajorId(2L);
//            student1.setPassword("123");
//            studentRepository.save(student1);


        };
    }
}
