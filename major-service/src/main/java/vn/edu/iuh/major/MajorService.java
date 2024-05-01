package vn.edu.iuh.major;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MajorService {
    private final MajorRepository majorRepository;

    public List<Major> getAll() {
        return majorRepository.findAll();
    }
    public Major getById(Long id) {
        return majorRepository.findById(id).orElseThrow(() -> new RuntimeException("Major not found with id " + id));
    }



}
