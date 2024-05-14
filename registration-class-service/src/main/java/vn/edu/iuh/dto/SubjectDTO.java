package vn.edu.iuh.dto;

import lombok.*;
import vn.edu.iuh.external.SubjectType;

import java.util.Set;

@Getter
@Setter
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
