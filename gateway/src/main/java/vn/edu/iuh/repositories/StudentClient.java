package vn.edu.iuh.repositories;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;
import vn.edu.iuh.dto.request.ValidateTokenRequest;
import vn.edu.iuh.dto.response.ValidateTokenResponse;
import vn.edu.iuh.response.ApiResponse;

public interface StudentClient {
    @PostExchange(url = "/token",contentType = MediaType.APPLICATION_JSON_VALUE)
    Mono<ApiResponse<ValidateTokenResponse>> validateToken(@RequestBody ValidateTokenRequest token);
}
