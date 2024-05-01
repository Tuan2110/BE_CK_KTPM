package vn.edu.iuh.creditclass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CreditClassRepository extends JpaRepository<CreditClass, Long> {
    @Query("SELECT c FROM CreditClass c WHERE c.subjectId = ?1")
    List<CreditClass> findBySubjectId(Long subjectId);
}
