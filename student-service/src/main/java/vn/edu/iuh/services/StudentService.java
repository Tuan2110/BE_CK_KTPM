package vn.edu.iuh.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.edu.iuh.repositories.StudentRepository;
import vn.edu.iuh.models.Student;
import vn.edu.iuh.client.CreditClassClient;
import vn.edu.iuh.client.MajorClient;
import vn.edu.iuh.client.SubjectClient;
import vn.edu.iuh.config.JwtTokenUtil;
import vn.edu.iuh.dto.StudentDTO;
import vn.edu.iuh.dto.SubjectDTO;
import vn.edu.iuh.dto.ValidateTokenRequest;
import vn.edu.iuh.dto.ValidateTokenResponse;
import vn.edu.iuh.external.CalendarClass;
import vn.edu.iuh.external.Major;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final MajorClient majorClient;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final CreditClassClient creditClassClient;
    private final SubjectClient subjectClient;
    private final UserDetailsService userDetailsService;

    public String login(Long studentId, String password) {
        Optional<Student> optionalStudent = studentRepository.findByStudentId(studentId);
        if (optionalStudent.isEmpty()) {
            throw new RuntimeException("Invalid phone number/password");
        }
        Student existingStudent = optionalStudent.get();
        if (!passwordEncoder.matches(password, existingStudent.getPassword())) {
            throw new RuntimeException("Invalid phone number/password");
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(studentId, password);
        authenticationManager.authenticate(authenticationToken);

        return jwtTokenUtil.generateToken(existingStudent);
    }
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream().map(this::getStudentDTO).toList();
    }
    public StudentDTO getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId).
                orElseThrow(() -> new RuntimeException("Student not found with id " + studentId));
        return getStudentDTO(student);
    }

    private StudentDTO getStudentDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setStudentId(student.getStudentId());
        studentDTO.setName(student.getName());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setPhone(student.getPhone());
        studentDTO.setCompletedSubjects(student.getCompletedSubjects());
        studentDTO.setRegisteredSubjects(student.getRegisteredSubjects());
        Major major = majorClient.getMajorById(student.getMajorId());
        studentDTO.setMajor(major);
        return studentDTO;
    }

    public Student getProfile(String token) {
        token = token.substring(7);
        String studentId = jwtTokenUtil.extractStudentId(token);
        return studentRepository.findByStudentId(Long.parseLong(studentId)).
                orElseThrow(() -> new RuntimeException("Student not found with id " + studentId));
    }

    public Student updateStudent(String token, Student student) {
        Student existingStudent = getProfile(token);
        if(student.getName() != null)
            existingStudent.setName(student.getName());
        if(student.getEmail() != null)
            existingStudent.setEmail(student.getEmail());
        if(student.getPhone() != null)
            existingStudent.setPhone(student.getPhone());
        if(student.getMajorId() != null)
            existingStudent.setMajorId(student.getMajorId());
        existingStudent.setRegisteredSubjects(student.getRegisteredSubjects());
        existingStudent.setCurrentClasses(student.getCurrentClasses());
        return studentRepository.save(existingStudent);
    }

    public Student createStudent(Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return studentRepository.save(student);
    }

    public List<CalendarClass> getCalendarClassByStudentId(String token) {
        Student student = getProfile(token);
        return creditClassClient.getCalendarClassByListClassId(student.getCurrentClasses().stream().toList()).getBody();
    }

    public int getTotalCredit(String token) {
        Student student = getProfile(token);
        List<SubjectDTO> subjects = subjectClient.getSubjectsByMajorId(student.getMajorId());
        return subjects.stream().mapToInt(SubjectDTO::getCredit).sum();
    }

    public int getTotalCompletedCredit(String token) {
        Student student = getProfile(token);
        List<Long> subjectIds = student.getCompletedSubjects().stream().toList();
        if (subjectIds.isEmpty()) {
            return 0;
        }
        List<SubjectDTO> subjects = subjectClient.getCompletedSubjectsByMajorId(subjectIds);
        return subjects.stream().mapToInt(SubjectDTO::getCredit).sum();
    }

    public ValidateTokenResponse validateToken(ValidateTokenRequest token) {
        final String studentId = jwtTokenUtil.extractStudentId(token.getToken());
        Student userDetails = (Student) userDetailsService.loadUserByUsername(studentId);
        return ValidateTokenResponse.builder().valid(jwtTokenUtil.validateToken(token.getToken(), userDetails)).build();
    }
}
