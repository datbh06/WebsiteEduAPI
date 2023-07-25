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
     * Constructs a new LoaiKhoaHocController object with the specified LoaiKhoaHocRepository.
     *
     * @param loaiKhoaHocRepository the LoaiKhoaHocRepository to be used by this controller
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

    /**
     * Updates a LoaiKhoaHoc object with the specified ID.
     *
     * @param id          the ID of the LoaiKhoaHoc object to be updated
     * @param loaiKhoaHoc a JSON representation of the updated LoaiKhoaHoc object
     * @return a ResponseEntity indicating the result of the update operation
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateLoaiKhoaHoc(@PathVariable("id") Integer id, @RequestBody String loaiKhoaHoc) {
        Gson gson = new Gson();
        LoaiKhoaHoc loaiKhoaHocNew = gson.fromJson(loaiKhoaHoc, LoaiKhoaHoc.class);
        Optional<LoaiKhoaHoc> loaiKhoaHocOld = loaiKhoaHocRepository.findById(id);
        if (loaiKhoaHocOld.isPresent()) {
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
                loaiKhoaHocNew.setLoaiKhoaHocID(id);
                loaiKhoaHocRepository.save(loaiKhoaHocNew);
                return ResponseEntity.ok().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes a LoaiKhoaHoc object with the specified ID.
     *
     * @param id the ID of the LoaiKhoaHoc object to be deleted
     * @return a ResponseEntity indicating the result of the delete operation
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLoaiKhoaHoc(@PathVariable("id") Integer id) {
        Optional<LoaiKhoaHoc> loaiKhoaHoc = loaiKhoaHocRepository.findById(id);
        if (loaiKhoaHoc.isPresent()) {
            loaiKhoaHocRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
