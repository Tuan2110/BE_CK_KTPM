package vn.edu.iuh;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import vn.edu.iuh.repositories.SubjectRepository;

@SpringBootApplication
@EnableFeignClients
public class SubjectServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubjectServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner init(SubjectRepository subjectRepository) {
        return (args) -> {
//            Subject subject = new Subject();
//            subject.setName("Java");
//            subject.setCredit(3);
//            subject.setType(SubjectType.OBLIGATORY);
//            subject.setMajorId(1L);

//            Subject subject2 = new Subject();
//            subject2.setName("C#");
//            subject2.setCredit(3);
//            subject2.setType(SubjectType.OBLIGATORY);
//            subject2.setMajorId(1L);
//            subject2.getPreSubject().add(subjectRepository.findById(1L).orElseThrow());
//
////            subjectRepository.save(subject);
//            subjectRepository.save(subject2);


        };
    }
}
