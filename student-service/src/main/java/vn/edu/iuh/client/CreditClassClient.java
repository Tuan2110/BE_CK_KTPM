package vn.edu.iuh.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.external.CalendarClass;

import java.util.List;

@FeignClient(name = "REGISTRATION-CLASS-SERVICE")
public interface CreditClassClient {
    @GetMapping("/credit-class/calendar")
    ResponseEntity<List<CalendarClass>> getCalendarClassByListClassId(@RequestParam List<Long> classIds);
}
