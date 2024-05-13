package vn.edu.iuh.creditclass;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.iuh.creditclass.client.StudentClient;
import vn.edu.iuh.creditclass.client.SubjectClient;
import vn.edu.iuh.creditclass.dto.ApiResponse;
import vn.edu.iuh.creditclass.dto.ClassDTO;
import vn.edu.iuh.creditclass.dto.EmailDetails;
import vn.edu.iuh.creditclass.dto.SubjectDTO;
import vn.edu.iuh.creditclass.external.Student;
import vn.edu.iuh.creditclass.messaging.MessageProducer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditClassService {
    private final CreditClassRepository creditClassRepository;
    private final SubjectClient subjectClient;
    private final StudentClient studentClient;
    private final CalendarClassRepository calendarClassRepository;
    private final MessageProducer messageProducer;

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

    public ApiResponse<?> registerClass(String token, Long classId) {
        Student student = studentClient.getProfile(token).getData();
        CreditClass creditClass = creditClassRepository.findById(classId).orElseThrow(
                () -> new RuntimeException("Class not found with id: " + classId));
        SubjectDTO subject = subjectClient.getSubjectById(creditClass.getSubjectId());
        if(creditClass.getNumberOfStudent() == creditClass.getMaxStudent()){
            return ApiResponse.builder()
                    .status(400)
                    .message("Class is full")
                    .data(null)
                    .build();
        }
        if(!student.getCompletedSubjects().containsAll(subject.getPreSubject())){
            return ApiResponse.builder()
                    .status(400)
                    .message("Student has not completed pre-subject")
                    .data(null)
                    .build();
        }
        int totalCredit = 0;
        for (Long subjectId : student.getRegisteredSubjects()) {
            SubjectDTO subjectDTO = subjectClient.getSubjectById(subjectId);
            totalCredit += subjectDTO.getCredit();
        }
        if((totalCredit + subject.getCredit()) > 30){
            return ApiResponse.builder()
                    .status(400)
                    .message("Student has exceeded the maximum number of credits")
                    .data(null)
                    .build();
        }
        student.getRegisteredSubjects().add(creditClass.getSubjectId());
        student.getCurrentClasses().add(creditClass.getId());
        System.out.println(student.getRegisteredSubjects());
        studentClient.updateStudent(token,student);
        creditClass.setNumberOfStudent(creditClass.getNumberOfStudent() + 1);
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(student.getEmail());
        emailDetails.setSubject("Register class successfully");
        emailDetails.setMsgBody("You have successfully registered subject" + subject.getName() +" class " + creditClass.getClassCode());
        messageProducer.sendMessage(emailDetails);
        CreditClass saved = creditClassRepository.save(creditClass);
        return ApiResponse.builder()
                .status(200)
                .message("Register class successfully")
                .data(saved)
                .build();
    }

    public ClassDTO getClassById(Long classId) {
        CreditClass creditClass = creditClassRepository.findById(classId).orElseThrow(
                () -> new RuntimeException("Class not found with id: " + classId));
        return toClassDTO(creditClass);
    }

    public CreditClass createClass(CreditClass creditClass) {
        CreditClass creditClass1 = creditClassRepository.save(creditClass);
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(creditClass.getStartDate());

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(creditClass.getEndDate());

        String location = "Room 101";

        String teacher = "Teacher 1";

        while (startCal.before(endCal)) {
            Date startOfWeek = startCal.getTime();
            //Add 3 hours to the start time
            Date endOfWeek = new Date(startOfWeek.getTime() + 3 * 60 * 60 * 1000);

            startCal.add(Calendar.DATE, 7); // Increment by one week

            CalendarClass calendarClass = new CalendarClass();
            calendarClass.setStart(startOfWeek);
            calendarClass.setEnd(endOfWeek);
            calendarClass.setLocation(location);
            calendarClass.setTeacher(teacher);
            calendarClass.setCreditClass(creditClass1); // Set the relation to this CreditClass

            calendarClassRepository.save(calendarClass);
        }

        return creditClass1;
    }

    public List<CalendarClass> getCalendarClassByListClassId(List<Long> classIds) {
        return calendarClassRepository.findByCreditClassIdIn(classIds);
    }

    public List<ClassDTO> getRegisterClass(String token) {
        Student student = studentClient.getProfile(token).getData();
        List<CreditClass> creditClasses = creditClassRepository.findAllById(student.getRegisteredSubjects());
        return creditClasses.stream().map(this::toClassDTO).toList();
    }
}
