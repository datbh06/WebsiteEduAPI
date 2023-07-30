package com.tyugen.WebsiteEduAPI.service;

import com.google.gson.Gson;
import com.tyugen.WebsiteEduAPI.model.LoaiBaiViet;
import com.tyugen.WebsiteEduAPI.repository.LoaiBaiVietRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * LoaiBaiVietService is a service class that contains business logic related to LoaiBaiVietController.
 */
@Service
public class LoaiBaiVietService {
    private final LoaiBaiVietRepository loaiBaiVietRepository;

    /**
     * Constructs a new LoaiBaiVietService object with the specified LoaiBaiVietRepository.
     *
     * @param loaiBaiVietRepository the LoaiBaiVietRepository to be used by this service
     */
    public LoaiBaiVietService(LoaiBaiVietRepository loaiBaiVietRepository) {
        this.loaiBaiVietRepository = loaiBaiVietRepository;
    }

    /**
     * Adds a new LoaiBaiViet object to the database.
     *
     * @param loaiBaiViet a JSON representation of the new LoaiBaiViet object
     * @return a ResponseEntity indicating the result of the add operation
     */
    public ResponseEntity<?> addLoaiBaiViet(String loaiBaiViet) {
        Gson gson = new Gson();
        LoaiBaiViet loaiBaiVietNew = gson.fromJson(loaiBaiViet, LoaiBaiViet.class);
        Validator validator;
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            validator = validatorFactory.getValidator();
        }
        Set<ConstraintViolation<LoaiBaiViet>> violations = validator.validate(loaiBaiVietNew);
        if (!violations.isEmpty()) {
            List<String> errorMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        } else {
            loaiBaiVietRepository.save(loaiBaiVietNew);
            return ResponseEntity.ok().build();
        }
    }
}
