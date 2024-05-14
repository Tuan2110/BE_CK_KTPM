package vn.edu.iuh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.models.Semester;

public interface SemesterRepository extends JpaRepository<Semester, Long> {
}