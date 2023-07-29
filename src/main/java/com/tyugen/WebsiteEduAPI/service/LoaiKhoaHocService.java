package com.tyugen.WebsiteEduAPI.service;

import com.google.gson.Gson;
import com.tyugen.WebsiteEduAPI.model.LoaiKhoaHoc;
import com.tyugen.WebsiteEduAPI.repository.LoaiKhoaHocRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LoaiKhoaHocService {
    private final LoaiKhoaHocRepository loaiKhoaHocRepository;

    /**
     * Constructs a new LoaiKhoaHocService object with the specified LoaiKhoaHocRepository.
     *
     * @param loaiKhoaHocRepository the LoaiKhoaHocRepository to be used by this service
     */
    public LoaiKhoaHocService(LoaiKhoaHocRepository loaiKhoaHocRepository) {
        this.loaiKhoaHocRepository = loaiKhoaHocRepository;
    }

    /**
     * Adds a new LoaiKhoaHoc object to the database.
     *
     * @param loaiKhoaHoc a JSON representation of the new LoaiKhoaHoc object
     * @return a ResponseEntity indicating the result of the add operation
     */
    public ResponseEntity<?> addLoaiKhoaHoc(String loaiKhoaHoc) {
        Gson gson = new Gson();
        LoaiKhoaHoc loaiKhoaHocNew = gson.fromJson(loaiKhoaHoc, LoaiKhoaHoc.class);
        Validator validator;
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            validator = validatorFactory.getValidator();
        }
        Set<ConstraintViolation<LoaiKhoaHoc>> violations = validator.validate(loaiKhoaHocNew);
        if (!violations.isEmpty()) {
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
    public ResponseEntity<?> updateLoaiKhoaHoc(Integer id,String loaiKhoaHoc) {
        Gson gson = new Gson();
        LoaiKhoaHoc loaiKhoaHocNew = gson.fromJson(loaiKhoaHoc, LoaiKhoaHoc.class);
        Optional<LoaiKhoaHoc> loaiKhoaHocOld = loaiKhoaHocRepository.findById(id);
        if (loaiKhoaHocOld.isPresent()) {
            Validator validator;
            try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
                validator = validatorFactory.getValidator();
            }
            Set<ConstraintViolation<LoaiKhoaHoc>> violations = validator.validate(loaiKhoaHocNew);
            if (!violations.isEmpty()) {
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
    public ResponseEntity<?> deleteLoaiKhoaHoc(Integer id) {
        Optional<LoaiKhoaHoc> loaiKhoaHoc = loaiKhoaHocRepository.findById(id);
        if (loaiKhoaHoc.isPresent()) {
            loaiKhoaHocRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
