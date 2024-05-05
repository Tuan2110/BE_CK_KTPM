package vn.edu.iuh.creditclass;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CalendarClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date start;
    private Date end;
    private String location;
    private String teacher;
    @ManyToOne
    private CreditClass creditClass;
}
