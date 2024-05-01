package vn.edu.iuh.student.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Major {
    private Long id;
    private String name;

    private Department department;
}
