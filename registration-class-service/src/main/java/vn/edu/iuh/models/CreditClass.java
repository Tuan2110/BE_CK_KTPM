package vn.edu.iuh.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CreditClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String classCode;
    private Long subjectId;
    @ManyToOne
    private Semester semester;
    private Date createdDate;
    private Date closedDate;
    private int numberOfStudent;
    private int minStudent;
    private int maxStudent;
    private Date startDate;
    private Date endDate;
    private boolean acceptOpen;

}
