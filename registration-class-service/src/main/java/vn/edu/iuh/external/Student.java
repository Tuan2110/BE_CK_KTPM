package vn.edu.iuh.external;


import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    private Long id;
    private Long studentId;
    private String password;
    private String name;
    private String email;
    private String phone;
    private Set<Long> completedSubjects;
    private Set<Long> registeredSubjects;
    private Set<Long> currentClasses;
    private Long majorId;
}
