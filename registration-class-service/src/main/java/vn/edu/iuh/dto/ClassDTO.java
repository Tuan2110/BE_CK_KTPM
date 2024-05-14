package vn.edu.iuh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.iuh.models.Semester;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassDTO {
    private Long id;
    private String classCode;
    private SubjectDTO subjectId;
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
