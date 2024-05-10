package vn.edu.iuh.service;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import vn.edu.iuh.dto.request.ValidateTokenRequest;
import vn.edu.iuh.dto.response.ValidateTokenResponse;
import vn.edu.iuh.repositories.StudentClient;
import vn.edu.iuh.response.ApiResponse;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class StudentService {
    StudentClient studentClient;

    public Mono<ApiResponse<ValidateTokenResponse>> validateToken(String token) {
        return studentClient.validateToken(ValidateTokenRequest.builder().token(token).build());
    }
}
