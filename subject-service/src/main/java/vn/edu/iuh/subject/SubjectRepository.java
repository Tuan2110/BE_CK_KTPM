package vn.edu.iuh.subject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    @Query("SELECT s FROM Subject s WHERE s.majorId = ?1")
    List<Subject> findByMajorId(Long majorId);
    @Query("SELECT s FROM Subject s WHERE s.id IN ?1")
    List<Subject> findByIdIn(List<Long> subjectIds);
}
