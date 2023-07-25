package com.tyugen.WebsiteEduAPI.Controller;

import com.google.gson.Gson;
import com.tyugen.WebsiteEduAPI.model.LoaiKhoaHoc;
import com.tyugen.WebsiteEduAPI.repository.LoaiKhoaHocRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/loaikhoahoc")
public class LoaiKhoaHocController {

    private final LoaiKhoaHocRepository loaiKhoaHocRepository;

    /**
     * Constructor
     *
     * @param loaiKhoaHocRepository LoaiKhoaHocRepository
     */
    public LoaiKhoaHocController(LoaiKhoaHocRepository loaiKhoaHocRepository) {
        this.loaiKhoaHocRepository = loaiKhoaHocRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addLoaiKhoaHoc(@RequestBody String loaiKhoaHoc) {
        Gson gson = new Gson();
        LoaiKhoaHoc loaiKhoaHocNew = gson.fromJson(loaiKhoaHoc, LoaiKhoaHoc.class);
        Validator validator;
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            validator = validatorFactory.getValidator();
        }
        Set<ConstraintViolation<LoaiKhoaHoc>> violations = validator.validate(loaiKhoaHocNew);
        if (violations.size() > 0) {
            List<String> errorMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        } else {
            loaiKhoaHocRepository.save(loaiKhoaHocNew);
            return ResponseEntity.ok().build();
        }
    }


}
