package vn.edu.iuh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubjectDTO {
    private Long id;
    private String name;
    private int credit;
    private SubjectType type;
    private Long majorId;
    private Set<Long> preSubject;
}
