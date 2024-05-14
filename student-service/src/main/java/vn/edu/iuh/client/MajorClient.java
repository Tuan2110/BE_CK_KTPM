package vn.edu.iuh.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vn.edu.iuh.external.Major;

@FeignClient(name = "MAJOR-SERVICE")
public interface MajorClient {
    @GetMapping("/majors/{majorId}")
    Major getMajorById(@PathVariable Long majorId);

}
