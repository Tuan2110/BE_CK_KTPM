package vn.edu.iuh.dto;

import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.iuh.external.Major;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private Long id;
    private Long studentId;
    private String name;
    private String email;
    private String phone;

    @ElementCollection
    private Set<Long> completedSubjects;

    @ElementCollection
    private Set<Long> registeredSubjects;

    private Major major;
}
