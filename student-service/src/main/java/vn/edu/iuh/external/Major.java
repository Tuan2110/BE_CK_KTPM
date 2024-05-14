package vn.edu.iuh.external;

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
