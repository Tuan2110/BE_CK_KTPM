package vn.edu.iuh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.models.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}