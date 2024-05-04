package vn.edu.iuh.creditclass;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.iuh.creditclass.client.StudentClient;
import vn.edu.iuh.creditclass.client.SubjectClient;
import vn.edu.iuh.creditclass.dto.ClassDTO;
import vn.edu.iuh.creditclass.dto.SubjectDTO;
import vn.edu.iuh.creditclass.external.Student;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditClassService {
    private final CreditClassRepository creditClassRepository;
    private final SubjectClient subjectClient;
    private final StudentClient studentClient;

    public List<ClassDTO> getCreditClassBySubjectId(Long subjectId, Long semesterId) {
        List<CreditClass> creditClasses = creditClassRepository.findBySubjectId(subjectId);
        List<CreditClass> result = new ArrayList<>();
        for (CreditClass creditClass : creditClasses) {
            if(creditClass.getCreatedDate().before(new Date())
                    && creditClass.getClosedDate().after(new Date())
                    && creditClass.getSemester().getId().equals(semesterId)
            ) {
                result.add(creditClass);
            }
        }
        return result.stream().map(this::toClassDTO).toList();
    }

    private ClassDTO toClassDTO(CreditClass creditClass) {
        return ClassDTO.builder()
                .id(creditClass.getId())
                .classCode(creditClass.getClassCode())
                .subjectId(subjectClient.getSubjectById(creditClass.getSubjectId()))
                .semester(creditClass.getSemester())
                .createdDate(creditClass.getCreatedDate())
                .closedDate(creditClass.getClosedDate())
                .numberOfStudent(creditClass.getNumberOfStudent())
                .minStudent(creditClass.getMinStudent())
                .maxStudent(creditClass.getMaxStudent())
                .startDate(creditClass.getStartDate())
                .endDate(creditClass.getEndDate())
                .acceptOpen(creditClass.isAcceptOpen())
                .build();
    }

    public CreditClass registerClass(String token, Long classId) {
        Student student = studentClient.getProfile(token);
        CreditClass creditClass = creditClassRepository.findById(classId).orElseThrow(
                () -> new RuntimeException("Class not found with id: " + classId));
        SubjectDTO subject = subjectClient.getSubjectById(creditClass.getSubjectId());
        if(creditClass.getNumberOfStudent() == creditClass.getMaxStudent()){
            throw new RuntimeException("Class is full");
        }
        if(!student.getCompletedSubjects().containsAll(subject.getPreSubject())){
            throw new RuntimeException("Student has not completed pre-subjects");
        }
        int totalCredit = 0;
        for (Long subjectId : student.getRegisteredSubjects()) {
            SubjectDTO subjectDTO = subjectClient.getSubjectById(subjectId);
            totalCredit += subjectDTO.getCredit();
        }
        if((totalCredit + subject.getCredit()) > 30){
            throw new RuntimeException("Student can't register more than 30 credits");
        }
        student.getRegisteredSubjects().add(creditClass.getSubjectId());
        student.getCurrentClasses().add(creditClass.getId());
        System.out.println(student.getRegisteredSubjects());
        studentClient.updateStudent(token,student);
        creditClass.setNumberOfStudent(creditClass.getNumberOfStudent() + 1);
        return creditClassRepository.save(creditClass);
    }

    public ClassDTO getClassById(Long classId) {
        CreditClass creditClass = creditClassRepository.findById(classId).orElseThrow(
                () -> new RuntimeException("Class not found with id: " + classId));
        return toClassDTO(creditClass);
    }
}
