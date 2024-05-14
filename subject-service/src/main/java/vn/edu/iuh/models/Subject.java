package vn.edu.iuh.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int credit;
    private SubjectType type;
    private Long majorId;
    @OneToMany
    private Set<Subject> preSubject;
}
