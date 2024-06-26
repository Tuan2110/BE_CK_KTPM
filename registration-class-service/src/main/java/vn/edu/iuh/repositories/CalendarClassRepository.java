package vn.edu.iuh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.edu.iuh.models.CalendarClass;

import java.util.List;

public interface CalendarClassRepository extends JpaRepository<CalendarClass, Long> {
   @Query("SELECT c FROM CalendarClass c WHERE c.creditClass.id IN :classIds order by c.start asc")
    List<CalendarClass> findByCreditClassIdIn(List<Long> classIds);
}
