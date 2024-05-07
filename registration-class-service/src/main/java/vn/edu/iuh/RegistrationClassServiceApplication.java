package vn.edu.iuh;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import vn.edu.iuh.creditclass.CreditClass;
import vn.edu.iuh.creditclass.CreditClassRepository;

import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication
@EnableFeignClients
public class RegistrationClassServiceApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(RegistrationClassServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner init(CreditClassRepository creditClassRepository) {
        return (args) -> {
//            CreditClass creditClass = new CreditClass();
//            creditClass.setClassCode("D17CQCN01-N");
//            creditClass.setSubjectId(1L);
//            creditClass.setMinStudent(70);
//            creditClass.setMaxStudent(100);
//            creditClass.setCreatedDate(new Date());
//            creditClass.setClosedDate(new Date());
//            creditClass.setStartDate(new Date());
//            creditClass.setEndDate(new Date());
//            creditClass.setAcceptOpen(false);
//
//            CreditClass creditClass2 = new CreditClass();
//            creditClass2.setClassCode("D17CQCN02-N");
//            creditClass2.setSubjectId(1L);
//            creditClass2.setMinStudent(70);
//            creditClass2.setMaxStudent(100);
//            creditClass2.setCreatedDate(new Date());
//            creditClass2.setClosedDate(new Date());
//            creditClass2.setStartDate(new Date());
//            creditClass2.setEndDate(new Date());
//            creditClass2.setAcceptOpen(false);
//
//            creditClassRepository.save(creditClass);
//            creditClassRepository.save(creditClass2);
        };
    }
}
