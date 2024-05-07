package vn.edu.iuh.subject;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.iuh.subject.client.StudentClient;
import vn.edu.iuh.subject.dto.SubjectDTO;
import vn.edu.iuh.subject.external.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final StudentClient studentClient;

    public List<SubjectDTO> getSubjects(String token) {
        List<Subject> subjects = subjectRepository.findAll();
        Student student = studentClient.getProfile(token);
        List<Subject> resultSubjects = new ArrayList<>();
        for (Subject subject : subjects) {
            if(subject.getMajorId().equals(student.getMajorId())
            && !student.getCompletedSubjects().contains(subject.getId())
            && !student.getRegisteredSubjects().contains(subject.getId())
            ) {
                resultSubjects.add(subject);
            }
        }
        return resultSubjects.stream().map(this::toSubjectDTO).collect(Collectors.toList());
    }
    private SubjectDTO toSubjectDTO(Subject subject) {
        return SubjectDTO.builder()
                .id(subject.getId())
                .name(subject.getName())
                .credit(subject.getCredit())
                .majorId(subject.getMajorId())
                .type(subject.getType())
                .preSubject(subject.getPreSubject().stream().map(Subject::getId).collect(Collectors.toSet()))
                .build();
    }

    public SubjectDTO getSubjectById(Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId).
                orElseThrow(() -> new RuntimeException("Subject not found with id " + subjectId));
        return toSubjectDTO(subject);
    }

    public List<SubjectDTO> getSubjectsByMajorId(Long majorId) {
        List<Subject> subjects = subjectRepository.findByMajorId(majorId);
        return subjects.stream().map(this::toSubjectDTO).collect(Collectors.toList());
    }

    public List<SubjectDTO> getCompletedSubjectsByMajorId(List<Long> subjectIds) {
        List<Subject> subjects = subjectRepository.findByIdIn(subjectIds);
        return subjects.stream().map(this::toSubjectDTO).collect(Collectors.toList());
    }
}
