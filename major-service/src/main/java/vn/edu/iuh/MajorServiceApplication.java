package vn.edu.iuh;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vn.edu.iuh.repositories.DepartmentRepository;
import vn.edu.iuh.repositories.MajorRepository;

@SpringBootApplication
public class MajorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MajorServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner init(MajorRepository majorRepository,
                           DepartmentRepository departmentRepository) {
        return args -> {
//            Department department = new Department();
//            department.setName("Công nghệ thông tin");
//            departmentRepository.save(department);

//            Major major = new Major();
//            major.setName("Kỹ thuật phần mềm");
//            major.setDepartment(departmentRepository.findById(1L).get());
//            majorRepository.save(major);
//
//            Major major2 = new Major();
//            major2.setName("Hệ thống thông tin");
//            major2.setDepartment(departmentRepository.findById(1L).get());
//            majorRepository.save(major2);
        };
    }
}
