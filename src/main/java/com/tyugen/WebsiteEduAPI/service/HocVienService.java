package com.tyugen.WebsiteEduAPI.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tyugen.WebsiteEduAPI.Adapter.DateTypeAdapter;
import com.tyugen.WebsiteEduAPI.model.HocVien;
import com.tyugen.WebsiteEduAPI.repository.HocVienRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * HocVienService is a service class that contains business logic related to HocVienController.
 */
@Service
public class HocVienService {

    private final HocVienRepository hocVienRepository;

    /**
     * Constructs a new HocVienService object with the specified HocVienService.
     *
     * @param hocVienRepository the hocVienRepository to be used by this service
     */
    public HocVienService(HocVienRepository hocVienRepository) {
        this.hocVienRepository = hocVienRepository;
    }

    /**
     * Adds a new HocVien object to the database.
     *
     * @param hocVien a JSON representation of the new HocVien object
     * @return a ResponseEntity indicating the result of the add operation
     */
    public ResponseEntity<?> addHocVien(String hocVien) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateTypeAdapter()).create();
        HocVien hocVienNew = gson.fromJson(hocVien, HocVien.class);

        Validator validator;
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            validator = validatorFactory.getValidator();
        }

        Set<ConstraintViolation<HocVien>> violations = validator.validate(hocVienNew);

        if (!violations.isEmpty()) {
            List<String> errorMessages = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        } else {
            hocVienRepository.save(hocVienNew);
            return ResponseEntity.ok().build();
        }
    }

    /**
     * Updates an existing HocVien object in the database.
     *
     * @param id      the ID of the HocVien object to update
     * @param hocVien a JSON representation of the updated HocVien object
     * @return a ResponseEntity indicating the result of the update operation
     */
    public ResponseEntity<?> updateHocVien(int id, String hocVien) {
        // Create a Gson object with a custom TypeAdapter for the java.sql.Date type
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateTypeAdapter()).create();
        HocVien hocVienNew = gson.fromJson(hocVien, HocVien.class);
        Optional<HocVien> oldHocVien = hocVienRepository.findById(id);

        if (oldHocVien.isPresent()) {
            Validator validator;
            try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
                validator = validatorFactory.getValidator();
            }
            Set<ConstraintViolation<HocVien>> violations = validator.validate(hocVienNew);

            if (!violations.isEmpty()) {
                List<String> errorMessages = violations.stream().
                        map(ConstraintViolation::getMessage)
                        .collect(Collectors
                                .toList());
                return ResponseEntity.badRequest().body(errorMessages);
            } else {
                hocVienNew.setHocVienID(id);
                hocVienRepository.save(hocVienNew);
                return ResponseEntity.ok().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
